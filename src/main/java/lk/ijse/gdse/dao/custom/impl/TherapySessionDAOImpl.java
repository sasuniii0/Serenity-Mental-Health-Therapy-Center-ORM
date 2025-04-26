package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.TherapySession;
import lk.ijse.gdse.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public boolean save(TherapySession entity) {
        return false;
    }

    @Override
    public boolean update(TherapySession entity) {
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
            TherapySession therapySession = session.get(TherapySession.class,id);
            if(therapySession == null){
                throw new NotFoundException("Session not found");
            }
            session.remove(therapySession);
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
    public List<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapySession> query = session.createQuery("from TherapySession ", TherapySession.class);
        return query.list();
    }

    @Override
    public TherapySession search(String sessionId) {
        return null;
    }

    @Override
    public boolean saveSessionWithPayment(Session session, TherapySession therapySession) {
        try {
            session.merge(therapySession);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT ts.id FROM TherapySession ts ORDER BY ts.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("S%03d", newId);
        } else {
            return "S001";
        }
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
        return null;
    }

    /*@Override
    public Map<String, Integer> getPatientSessionCounts() {
        Session session = factoryConfiguration.getSession();
        Map<String, Integer> patientSessionCounts = new HashMap<>();

        try {
            List<Object[]> results = session.createQuery("SELECT p.id, COUNT(ts.id) FROM Patient p LEFT JOIN p.id ts GROUP BY p.id").getResultList();
            for (Object[] result : results) {
                String patientId = (String) result[0];
                Integer sessionCount = (Integer) result[1];
                patientSessionCounts.put(patientId, sessionCount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return patientSessionCounts;
    }*/
}
