/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Excercise;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import support.HibernateUtil;

/**
 *
 * @author huub
 */
public class ExcerciseDao {
  
  public static Excercise loadExcerciseById(Long id) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Excercise.class);
    criteria.add(Restrictions.eq("id", id));
    List<Excercise> excercises = (List<Excercise>)criteria.list();
    Excercise excercise = null;
    if ( excercises != null && excercises.size() > 0 ) {
      excercise = excercises.get(0);
    }
    tx.commit();
    return excercise;
  }
  
  public static List<Excercise> loadAllExcercises() {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Excercise.class);
    List<Excercise> excercises = (List<Excercise>)criteria.list();
    tx.commit();
    return excercises;
  }
  
  public static void storeExcercise(Excercise excercise) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    hibernateSession.saveOrUpdate(excercise);
    tx.commit();
  }
  
  public static void storeAllExcercises(List<Excercise> excercises) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    for ( Excercise excercise : excercises ) {
      hibernateSession.saveOrUpdate(excercise);
    }
    tx.commit();
  }
}
