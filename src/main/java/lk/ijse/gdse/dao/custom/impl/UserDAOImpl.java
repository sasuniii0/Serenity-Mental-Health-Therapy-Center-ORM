package lk.ijse.gdse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    /*@Override
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
                User user = new User("U-1", "admin@gmail.com", hashedPassword, "admin", "admin", User.UserRole.Admin);
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
    }*/


    @Override
    public void initializeDefaultUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Check if any users exist
            Query<Long> countQuery = session.createQuery("SELECT COUNT(u) FROM User u", Long.class);
            Long userCount = countQuery.uniqueResult();

            // If no users exist, create default admin
            if (userCount == 0) {
                String hashedPass = PasswordUtil.hashPassword("1234");
                User admin = new User(
                        "U-1",
                        "Admin",
                        "User",
                        "admin@gmail.com",
                        "admin",
                        hashedPass,
                        hashedPass, // confirmPassword (should match)
                        User.Role.ADMIN.toString()
                );
                session.save(admin);
                System.out.println("Default admin user created successfully");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error initializing default users: " + e.getMessage());
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
    public String getLastUserId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = null;

        try {
            lastId = session.createQuery("SELECT u.id FROM User u ORDER BY u.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return lastId;
    }

    @Override
    public String generateNextUserId() {
        String lastId = getLastUserId();
        if (lastId != null) {
            int lastNum = Integer.parseInt(lastId.substring(2)); // Changed from substring(1) to substring(2)
            return String.format("U-%d", lastNum + 1);
        }
        return "U-1";
    }

    @Override
    public UserTM getAllUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query query = session.createQuery("FROM User");
            List<User> users = query.list();
            return new UserTM(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public UserDTO getAllUser() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query query = session.createQuery("FROM User");
            List<User> users = query.list();
            return new UserDTO(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


    @Override
    public boolean save(User entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            String hashedPassword = PasswordUtil.hashPassword(entity.getPassword());
            entity.setPassword(hashedPassword);

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.persist(entity);

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public boolean update(User entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            String hashedPassword = PasswordUtil.hashPassword(entity.getPassword());
            entity.setPassword(hashedPassword);

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.merge(entity);

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        try{
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete from User where email = ?1");
            query.setParameter(1, id);
            boolean isDelete = query.executeUpdate() > 0;
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            return false;
        }
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
