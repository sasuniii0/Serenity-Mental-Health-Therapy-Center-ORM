package lk.ijse.gdse.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

   private FactoryConfiguration(){
       Configuration configuration = new Configuration();

       //add property
       Properties properties = new Properties();

       //add already created hibernate file to properties in current thread
       try {
           properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));   //set path file is found no matter where the code is running
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       //add properties to configure
       configuration.setProperties(properties);

       //build session factory
       sessionFactory = configuration.buildSessionFactory();


   }

   public static FactoryConfiguration getInstance(){
       return (factoryConfiguration == null)
               ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
   }
   public Session getSession(){
       return sessionFactory.openSession();
   }
}
