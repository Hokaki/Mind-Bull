/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import support.HibernateUtil;
/**
 *
 * @author Mohamed
 */
public class UserDao {
    
    public static User loadUserById(Long id) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(User.class);
    criteria.add(Restrictions.eq("id", id));
    List<User> users = (List<User>)criteria.list();
    User user = null;
    if ( users != null && users.size() > 0 ) {
      user = users.get(0);
    }
    tx.commit();
    return user;
  }
  
  public static List<User> loadAllUsers() {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(User.class);
    List<User> users = (List<User>)criteria.list();
    tx.commit();
    return users;
  }
  
  public static void storeUsers(User user) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    hibernateSession.saveOrUpdate(user);
    tx.commit();
  }
  
  public static void storeAllUsers(List<User> users) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    for ( User user : users ) {
      hibernateSession.saveOrUpdate(user);
    }
    tx.commit();
  }
    
}
