package lk.ijse.gdse.config;

import lk.ijse.gdse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

   private FactoryConfiguration(){

       try{
           Configuration configuration = new Configuration();
           Properties properties = new Properties();
           properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));

           configuration.setProperties(properties)
                   .addAnnotatedClass(Patient.class)
                   .addAnnotatedClass(Payment.class)
                   .addAnnotatedClass(Therapist.class)
                   .addAnnotatedClass(TherapyProgram.class)
                   .addAnnotatedClass(TherapySession.class)
                   .addAnnotatedClass(Registration.class)
                   .addAnnotatedClass(User.class);

           sessionFactory = configuration.buildSessionFactory();

       }catch (Exception e){
           throw new RuntimeException("Failed to load hibernate.properties",e);
       }

   }

   public static FactoryConfiguration getInstance(){
       return (factoryConfiguration == null)
               ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
   }
   public static Session getSession(){
       return sessionFactory.openSession();
   }
}
