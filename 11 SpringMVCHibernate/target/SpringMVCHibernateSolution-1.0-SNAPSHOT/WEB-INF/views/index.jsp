<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>User Management SPRING</title>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>Welkom bij de SPRING Hibernate variant van de opdrachten</h2>
        <h3>Menu</h3>
        <h3>Dit is de 1 - to - many variant waarbij 1 user 1 rol heeft,</h3>
        <h3> maar een rol aan meerdere users kan worden toegekend </h3>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/user/list">Gebruikers</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/role/list">Rollen</a>
            </li>
        </ul>
    </body>
</html>
