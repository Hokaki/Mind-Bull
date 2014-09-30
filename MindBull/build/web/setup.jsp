<%-- 
    Document   : setup
    Created on : Dec 25, 2013, 4:15:19 PM
    Author     : huub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="support.HibernateUtil" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Herkansing WEB JEE Database setup</title>
  </head>
  <body>
    <h1>Hier wordt de database opgezet</h1>
    <form action="setuphandler" method="post">
      <table>
        <tr>
          <td> <button type="submit" name="command" value="1">Setup database</button></td>
          <td> <button type="submit" name="command" value="0">Cancel</button></td>
        </tr>
      </table>
    </form>
  </body>
</html>
