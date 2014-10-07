<%-- 
    Document   : index
    Created on : Dec 14, 2013, 2:16:47 PM
    Author     : huub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="support.HibernateUtil" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JDBC Login Pagina</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <section id="main_section">
        <h1>Welcome to project: MindBull</h1>
        <p>
            First you need to login <a href="indexhandler?action=login">Login</a>
        </p>
        <p>
            Or click <a href="indexhandler?action=dba">here</a> for the database setup.
        </p>
        </section>
    </body>
</html>
