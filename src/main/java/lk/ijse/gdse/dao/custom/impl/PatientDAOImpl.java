package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patient entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Generate new ID only if not already set
            if (entity.getId() == null || entity.getId().isEmpty()) {
                entity.setId(getLastPatientId());
            }

            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save patient: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }
    @Override
    public boolean update(Patient entity) {
       Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(entity);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Patient patient = session.get(Patient.class, id);
            session.remove(patient);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Patient> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Patient> patients = session.createQuery("FROM Patient", Patient.class).list();
            transaction.commit();
            return patients;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


    @Override
    public String getLastPatientId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = null;

        try {
            lastId = session.createQuery("SELECT p.id FROM Patient p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return lastId;
    }

    @Override
    public List<Patient> searchPatient(String searchText) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM Patient p WHERE p.contactNumber LIKE :searchText OR p.id LIKE :searchText");
            query.setParameter("searchText", "%" + searchText + "%");
            List<Patient> patients = query.getResultList();
            session.close();
            return patients;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getPatientIdAndNames() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT p.id, p.name FROM Patient p");
            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                String id = (String) result[0];
                String name = (String) result[1];
                System.out.println("ID: " + id + ", Name: " + name);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Patient search(String patientId) {
        return null;
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public ArrayList<String> getAllPatientNames() {
        return null;
    }

    @Override
    public String getPatientNameById(String patientId) {
        return "";
    }

    @Override
    public String getPatientIdByName(String selectedPatientName) {
        return "";
    }

    @Override
    public Patient getPatientId(String patientId) {
        return null;
    }

    @Override
    public Optional<Patient> findByPK(String patientId) {
        return Optional.empty();
    }


}
