<%-- 
    Document   : login
    Created on : 3-okt-2014, 12:47:57
    Author     : Mohamed
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            li {
                color: #ff0000;
            }            
        </style>
    </head>
    <body>
        <h2>Log into a new world</h2>
        <c:if test="${errors != null}">
            <c:forEach var="error" items="${errors}">
                <li>${error}</li>
            </c:forEach>
        </c:if>
        <form method="post" action="loginhandler">
            Username: <input type="text" name="username">
            Password: <input type="password" name="password">
            <input type="Submit" name="submit" value="Login">
        </form>
    </body>
</html>
