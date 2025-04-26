package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(TherapyProgram entity) {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(TherapyProgram entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
            session.remove(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<TherapyProgram> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM TherapyProgram");
            List<TherapyProgram> therapyPrograms = query.list();
            transaction.commit();
            return therapyPrograms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT tp.id FROM TherapyProgram tp WHERE tp.patient_id = :patientId");
            query.setParameter("patientId", patientId);
            List<String> therapyPrograms = query.list();
            transaction.commit();
            return therapyPrograms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
