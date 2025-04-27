package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.exception.DuplicateEntryException;
import lk.ijse.gdse.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TherapistDAOImpl implements TherapistDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public boolean save(Therapist entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist existsTherapist = session.get(Therapist.class, entity.getId());
            if(existsTherapist != null){
                throw new DuplicateEntryException("Therapist already exists");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(Therapist entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,id);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }

            session.remove(therapist);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Therapist> getAll() {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            // No transaction needed for read-only operations in some Hibernate versions
            return session.createQuery("FROM Therapist", Therapist.class)
                    .getResultList();
        } catch (Exception e) {
            // Log the error properly
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error fetching therapists", e);
            throw new RuntimeException("Failed to retrieve therapists", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public String getLastTherapistId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = null;

        try {
            lastId = session.createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return lastId;
    }

    @Override
    public List<TherapyProgram> getAllPrograms() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("FROM TherapyProgram");
            List<TherapyProgram> therapyPrograms = query.list();
            transaction.commit();
            return therapyPrograms;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<TherapyProgram> getAllTherapyPrograms() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("FROM TherapyProgram");
            List<TherapyProgram> therapyPrograms = query.list();
            transaction.commit();
            return therapyPrograms;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        try {
            // Get the highest existing ID (e.g., "T-5")
            String maxId = session.createQuery("SELECT max(t.id) FROM Therapist t", String.class)
                    .uniqueResult();

            if (maxId != null) {
                // Extract the number part after "T-"
                int lastNum = Integer.parseInt(maxId.substring(2)); // Skip "T-"
                return String.format("T-%d", lastNum + 1); // Increment by 1
            } else {
                return "T-1"; // Default first ID
            }
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<String> getAllTherapistNames() {
        Session session = factoryConfiguration.getSession();
        ArrayList<String> therapistNames = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<String> names = session.createQuery("select th.name from Therapist th", String.class).getResultList();

            therapistNames.addAll(names);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return therapistNames;
    }

    @Override
    public String getTherapistNameById(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String therapistName = null;

        try{
            transaction = session.beginTransaction();

            Therapist therapist = session.get(Therapist.class, therapistId);

            if(therapist != null) {
                therapistName = therapist.getName();
            }
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return therapistName;
    }

    @Override
    public ArrayList<String> getTherapistNameByProgramId(String programId) {
        Session session = factoryConfiguration.getSession();
        ArrayList<String> therapistNames = new ArrayList<>();

        try{
            List<String> names = session.createQuery(
                            "SELECT th.name FROM Therapist th WHERE th.therapyProgram.id = :programId", String.class)
                    .setParameter("programId",programId)
                    .getResultList();
            therapistNames.addAll(names);
        }finally {
            session.close();
        }
        return therapistNames;
    }

    @Override
    public String getTherapistIdByName(String selectedTherapistName) {
        Session  session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String therapistId = null;

        try{
            transaction = session.beginTransaction();
            therapistId = session.createQuery("SELECT th.id FROM Therapist th WHERE th.name = :name",String.class)
                    .setParameter("name", selectedTherapistName)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return therapistId;
    }

    @Override
    public Optional<Therapist> findByPK(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Therapist therapist = session.get(Therapist.class, therapistId);
        return Optional.ofNullable(therapist);
    }

    /*@Override
    public Map<String, Integer> getTherapistSessionCounts() {
        Session session = factoryConfiguration.getSession();
        Map<String, Integer> therapistSessionCounts = new HashMap<>();

        try {
            List<Object[]> results = session.createQuery("SELECT th.id, COUNT(ts.id) FROM Therapist th LEFT JOIN th.id ts GROUP BY th.id").getResultList();
            for (Object[] result : results) {
                String therapistId = (String) result[0];
                Integer sessionCount = (Integer) result[1];
                therapistSessionCounts.put(therapistId, sessionCount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return therapistSessionCounts;
    }*/
}
