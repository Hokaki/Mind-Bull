<%-- 
    Document   : wijziglid
    Created on : Jan 5, 2014, 2:47:43 PM
    Author     : huub
--%>

<%@page import="model.Team"%>
<%@page import="dao.TeamDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Wijzig teamgegevens</title>
  </head>
  <%
    Long id = (Long)request.getAttribute("id");
    Team team = TeamDao.loadTeamById(id);
    request.getSession().setAttribute("team", team);
  %>
  <body>
    Wijzig de gegevens van
    <strong><%= team.getName()%>,  <%= team.getYell()%></strong>
    <form action="wijziglidhandler" method="post">
      <table>
        <tr>
          <td>Team name</td>
          <td><input type="text" name="firstname"></td>
        </tr>
        <tr>
          <td>Team yell</td>
          <td><input type="text" name="lastname"></td>
        </tr>
        <tr>
          <td><input type="submit" name="submit" value="OK"></td>
          <td><input type="button" name="cancel" value="cancel"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
