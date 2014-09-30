/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Member;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import support.HibernateUtil;

/**
 *
 * @author huub
 */
public class MemberDao {
  
  public static Member loadMemberById(Long id) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Member.class);
    criteria.add(Restrictions.eq("id", id));
    List<Member> members = (List<Member>)criteria.list();
    Member member = null;
    if ( members != null && members.size() > 0 ) {
      member = members.get(0);
    }
    tx.commit();
    return member;
  }
  
  public static List<Member> loadAllMembers() {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Member.class);
    List<Member> members = (List<Member>)criteria.list();
    tx.commit();
    return members;
  }
  
  public static void storeMember(Member member) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    hibernateSession.saveOrUpdate(member);
    tx.commit();
  }
  
  public static void storeAllMembers(List<Member> members) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    for ( Member member : members ) {
      hibernateSession.saveOrUpdate(member);
    }
    tx.commit();
  }
}
