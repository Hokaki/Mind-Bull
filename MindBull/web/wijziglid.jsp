<%-- 
    Document   : wijziglid
    Created on : Jan 5, 2014, 2:47:43 PM
    Author     : huub
--%>

<%@page import="model.Member"%>
<%@page import="dao.MemberDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wijzig ledengegevens</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <%
        Long id = (Long) request.getAttribute("id");
        Member member = MemberDao.loadMemberById(id);
        request.getSession().setAttribute("member", member);
    %>
    <body>
        <section id="main_section">
            Wijzig de gegevens van
            <strong><%= member.getFirstName()%> <%= member.getLastName()%></strong>
            <form action="wijziglidhandler" method="post">
                <table>
                    <tr>
                        <td>Voornaam</td>
                        <td><input type="text" name="firstname"></td>
                    </tr>
                    <tr>
                        <td>Achternaam</td>
                        <td><input type="text" name="lastname"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" value="OK"></td>
                        <td><input type="button" name="cancel" value="cancel"></td>
                    </tr>
                </table>
            </form>
        </section>
    </body>
</html>
