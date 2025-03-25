package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {
    /*@Override
    public boolean searchUserByEmail(String email) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            if (user!= null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }*/
}
