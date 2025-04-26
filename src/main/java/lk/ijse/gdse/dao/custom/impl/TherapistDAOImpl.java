package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.util.PasswordUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.persist(entity);
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
    public boolean update(Therapist entity) {
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
            Therapist therapist = session.get(Therapist.class, id);
            session.remove(therapist);
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
        return "";
    }

    @Override
    public ArrayList<String> getAllTherapistNames() {
        return null;
    }

    @Override
    public String getTherapistNameById(String therapistId) {
        return "";
    }

    @Override
    public ArrayList<String> getTherapistNameByProgramId(String programId) {
        return null;
    }

    @Override
    public String getTherapistIdByName(String selectedTherapistName) {
        return "";
    }
}
