package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.exception.DuplicateEntryException;
import lk.ijse.gdse.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapyProgram entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram existsProgram = session.get(TherapyProgram.class, entity.getId());
            if (existsProgram != null) {
                throw new DuplicateEntryException("Program already exists");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TherapyProgram entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram program = session.get(TherapyProgram.class, id);
            if (program == null) {
                throw new NotFoundException("Program not found");
            }

            session.remove(program);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TherapyProgram> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapyProgram> query = session.createQuery("from TherapyProgram ", TherapyProgram.class);
        return query.list();
    }

    @Override
    public String getLastTherapyProId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = null;

        try {
            lastId = session.createQuery("SELECT tp.id FROM TherapyProgram tp ORDER BY tp.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return lastId;
    }

    @Override
    public List<String> loadTherapyProgramsForPatient(String patientId) {
        Session session = factoryConfiguration.getSession();
        try {
            String hql = "SELECT DISTINCT tp.id FROM TherapyProgram tp " +
                    "JOIN tp.therapySessions ts " +
                    "WHERE ts.patient.id = :patientId";

            return session.createQuery(hql, String.class)
                    .setParameter("patientId", patientId)
                    .getResultList();
        } finally {
            session.close();
        }
    }
    @Override
    public TherapyProgram getProgramId(String programId) {
        Session session = factoryConfiguration.getSession();
        TherapyProgram program = null;

        try {
            program = session.get(TherapyProgram.class, programId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return program;
    }

    @Override
    public Optional<TherapyProgram> findByPK(String programId) {
        Session session = factoryConfiguration.getSession();
        TherapyProgram program = session.get(TherapyProgram.class, programId);
        return Optional.ofNullable(program);
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT p.id FROM TherapyProgram p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(2)) + 1;
            return String.format("PR%03d", newId);
        } else {
            return "PR001";
        }
    }

    @Override
    public ArrayList<String> getAllProgramNames() {
        Session session = factoryConfiguration.getSession();
        try {
            // No transaction needed for read-only operation
            List<String> names = session.createQuery(
                            "SELECT p.programName FROM TherapyProgram p", String.class)
                    .getResultList();

            return new ArrayList<>(names);
        } catch (Exception e) {
            // Log the error for debugging
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to get program names", e);
            throw new RuntimeException("Failed to load program names", e);
        } finally {
            session.close();
        }
    }

    @Override
    public String getProgramIdByName(String selectedProgramName) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String programId = null;

        try {
            transaction = session.beginTransaction();
            programId = session.createQuery("SELECT p.id FROM TherapyProgram p WHERE p.programName = :programName", String.class)
                    .setParameter("programName", selectedProgramName)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return programId;
    }

    @Override
    public String getProgramNameById(String programId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String programName = null;

        try {
            transaction = session.beginTransaction();

            TherapyProgram program = session.get(TherapyProgram.class, programId);

            if (program != null) {
                programName = program.getProgramName();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return programName;
    }

    @Override
    public List<String> getRegisteredProgramsByPatientId(String patientId) {
        Session session = factoryConfiguration.getSession();
        List<String> programNames = new ArrayList<>();

        try {
            String hql = "SELECT p.programName " +
                    "FROM Registration r " +
                    "JOIN r.therapyProgram p " +
                    "WHERE r.patient.id = :patientId";

            Query query = session.createQuery(hql, String.class);
            query.setParameter("patientId", patientId);

            programNames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return programNames;
    }

    @Override
    public double getProgramFeeById(String programId) {
        Session session = factoryConfiguration.getSession();

        String hql = "SELECT p.fee FROM TherapyProgram p WHERE p.id = :programId";
        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("programId", programId);
        return query.uniqueResult();
    }

   /* @Override
    public Map<String, Integer> getProgramEnrollmentCounts() {
        Session session = factoryConfiguration.getSession();
        String hql = "SELECT p.id, COUNT(r.id) FROM TherapyProgram p LEFT JOIN p.id r GROUP BY p.id";
        Query<Object[]> query = session.createQuery(hql);
        List<Object[]> results = query.getResultList();
        Map<String, Integer> enrollmentCounts = new HashMap<>();
        for (Object[] result : results) {
            String programId = (String) result[0];
            Integer enrollmentCount = (Integer) result[1];
            enrollmentCounts.put(programId, enrollmentCount);
        }
        return enrollmentCounts;
    }*/
}
