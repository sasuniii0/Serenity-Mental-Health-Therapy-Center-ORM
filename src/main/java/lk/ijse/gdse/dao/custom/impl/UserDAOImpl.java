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

public class UserDAOImpl implements UserDAO {
    @Override
    public void initializeDefaultUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("njasd");
            NativeQuery nativeQuery = session.createNativeQuery("SELECT COUNT(*) FROM users");
            // System.out.println("njksndf");
            // System.out.println("res:"+nativeQuery.uniqueResult());
            Long result = (Long) nativeQuery.uniqueResult();

            boolean isUserTableExist = result ==0;
            if(isUserTableExist){
                String hashedPassword = PasswordUtil.hashPassword("1234");
                User user = new User("U-1","admin@gmail.com",hashedPassword,"admin","admin", User.UserRole.ADMIN);
                session.persist(user);
            }

            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public User searchUserByEmail(String email) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User  WHERE email = :email");
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

}
