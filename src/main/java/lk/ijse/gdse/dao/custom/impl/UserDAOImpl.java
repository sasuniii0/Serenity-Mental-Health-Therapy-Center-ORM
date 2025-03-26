package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void initializeDefaultUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("Checking users table...");

            NativeQuery<?> nativeQuery = session.createNativeQuery("SELECT COUNT(*) FROM users");
            Object resultObj = nativeQuery.uniqueResult();  // Ensure result is not null

            long result = resultObj != null ? ((Number) resultObj).longValue() : 0;  // Safe casting
            System.out.println("User count: " + result);

            if (result == 0) {
                String hashedPassword = PasswordUtil.hashPassword("1234");
                User user = new User("U-1", "admin@gmail.com", hashedPassword, "admin", "admin", User.UserRole.ADMIN);
                session.persist(user);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public User searchUserByEmail(String email) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            if (transaction != null) {
                transaction.commit();
            }
            return user;
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null ;
        }finally {
            session.close();
        }
    }

    @Override
    public User searchUserByAdminMail(String adMail) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {

            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User WHERE email=:mail");
            query.setParameter("mail", adMail);
            User user = (User) query.uniqueResult();
            if (user != null) {
                return user;
            }else{

                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

    }

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User");
            List<User> users = query.list();
            session.close();
            return users;
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}
