<%-- 
    Document   : teamlijst
    Created on : Jan 4, 2014, 6:48:29 PM
    Author     : huub
--%>

<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="support.HibernateUtil"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="model.Team"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Teamlijst</title>
  </head>
  <body>
    <%
      List<Team> teams = new LinkedList();
      Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
      Transaction tx = hibernateSession.beginTransaction();
      Criteria criteria = hibernateSession.createCriteria(Team.class);
      teams = criteria.list();
      tx.commit();
    %>
    <h2>Dit is de teamlijst van de Jeu de Boules Club</h2>
    <form action="teamlijsthandler" method="post">
      <table>
        <tr>
          <td><strong>Team naam</strong></td>
          <td><strong>Yell</strong></td>
        </tr>
        <%
          for ( Team team : teams ) {
            Long id = team.getId();
            %> <tr>
                <td><%= team.getName() %></td>
                <td><%= team.getYell() %></td>
                <td>
                  <button type="submit" name="edit" value="<%=id %>">
                    Wijzig <%= team.getName() %>
                  </button>
                </td>
              </tr> <%
          }
        %>
      </table>
    </form>
  </body>
</html>
