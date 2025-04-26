package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.exception.DuplicateEntryException;
import lk.ijse.gdse.exception.NotFoundException;
import lk.ijse.gdse.util.PasswordUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("FROM Therapist");
            List<Therapist> therapists = query.list();
            transaction.commit();
            return therapists;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
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
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("T%03d", newId);
        } else {
            return "T001";
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
