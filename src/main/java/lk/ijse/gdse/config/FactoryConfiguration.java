package lk.ijse.gdse.config;

import lk.ijse.gdse.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

   private FactoryConfiguration(){
       Configuration configuration = new Configuration();

       //add property
       Properties properties = new Properties();

       //add already created hibernate file to properies in current thread
       try {
           properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));   //set path file is found no matter where the code is running
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       //add properties to configure
       configuration.setProperties(properties);

       //add annotated class to configure
       configuration.addAnnotatedClass(Student.class);

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
