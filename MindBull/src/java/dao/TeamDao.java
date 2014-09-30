/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Team;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import support.HibernateUtil;

/**
 *
 * @author huub
 */
public class TeamDao {
  
  public static Team loadTeamById(Long id) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Team.class);
    criteria.add(Restrictions.eq("id", id));
    List<Team> teams = (List<Team>)criteria.list();
    Team team = null;
    if ( teams != null && teams.size() > 0 ) {
      team = teams.get(0);
    }
    tx.commit();
    return team;
  }
  
  public static List<Team> loadAllTeams() {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    Criteria criteria = hibernateSession.createCriteria(Team.class);
    List<Team> teams = (List<Team>)criteria.list();
    tx.commit();
    return teams;
  }
  
  public static void storeTeam(Team team) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    hibernateSession.saveOrUpdate(team);
    tx.commit();
  }
  
  public static void storeAllTeams(List<Team> teams) {
    Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = hibernateSession.beginTransaction();
    for ( Team team : teams ) {
      hibernateSession.saveOrUpdate(team);
    }
    tx.commit();
  }
}
