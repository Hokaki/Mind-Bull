/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author huub
 */
public class HibernateUtil {

  private static SessionFactory sessionFactory;
  private static ServiceRegistry serviceRegistry;

  private static SessionFactory createSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();
      serviceRegistry = 
              new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Exception ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    return sessionFactory;
  }

//  static {
//    try {
//      // Create the SessionFactory from standard (hibernate.cfg.xml) 
//      // config file.
//      
//      sessionFactory = new Configuration().configure().buildSessionFactory();
//    } catch (Throwable ex) {
//      // Log the exception. 
//      System.err.println("Initial SessionFactory creation failed." + ex);
//      throw new ExceptionInInitializerError(ex);
//    }
//  }
  
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      createSessionFactory();
      System.out.println("Sessionfactory created.");
    }
    return sessionFactory;
  }
}
