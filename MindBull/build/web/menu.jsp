<%-- 
    Document   : menuform
    Created on : Dec 15, 2013, 12:58:25 AM
    Author     : huub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.MemberDao" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>main menu</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <section id="main_section">
            <h2>This is the main menu</h2>
            <form action="menuhandler" method="post">
                <table>
                    <tr>
                        <td><button type="submit" name="command" value="0">Patient list</button></td>
                    </tr>
                    <tr>
                        <td><button type="submit" name="command" value="1">Team list</button></td>
                    </tr>
                    <tr>
                        <td><button type="submit" name="command" value="2">Calendar</button></td>
                    </tr>
                </table>
            </form>
        </section>
    </body>
</html>
