package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.RegistrationDAO;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.exception.DuplicateEntryException;
import lk.ijse.gdse.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Registration entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Registration existsRegistration = session.get(Registration.class, entity.getId());
            if(existsRegistration != null){
                throw new DuplicateEntryException("This Registration number is  already exists");
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
    public boolean update(Registration entity) {
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
            Registration registration = session.get(Registration.class,id);
            if(registration == null){
                throw new NotFoundException("Record not found");
            }

            session.remove(registration);
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
    public List<Registration> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Registration> query = session.createQuery("from Registration ", Registration.class);
        return query.list();
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT r.id FROM Registration r ORDER BY r.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("R%03d", newId);
        } else {
            return "R001";
        }
    }

    @Override
    public double getAdvancePaymentByPatientAndProgram(String patientId, String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String hql = "SELECT r.advancePayment FROM Registration r " +
                    "WHERE r.patient.id = :patientId " +
                    "AND r.therapyProgram.id = :programId " +
                    "ORDER BY r.date DESC";

            Query<Double> query = session.createQuery(hql, Double.class)
                    .setParameter("patientId", patientId)
                    .setParameter("programId", programId)
                    .setMaxResults(1);

            List<Double> results = query.getResultList();
            return results.isEmpty() ? 0.0 : results.get(0);
        } finally {
            session.close();
        }
    }

}
