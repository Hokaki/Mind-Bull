<%@page import="model.Excercise"%>
<%@page import="dao.ExcerciseDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change excercise</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <%
        Long id = (Long) request.getAttribute("id");
        Excercise excercise = ExcerciseDao.loadExcerciseById(id);
        request.getSession().setAttribute("excercise", excercise);
    %>
    <body>
        <section id="main_section">
            <h1>Change excercise</h1>
            <strong><%= excercise.getName()%>,  <%= excercise.getDescription()%></strong>
            <form action="changeexcercisehandler" method="post">
                <table>
                    <tr>
                        <td>Excercise name</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td>Excercise description</td>
                        <td><input type="text" name="description"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" value="OK"></td>
                        
                        <td><form><input type="button" name="cancel" value="cancel" onClick="history.go(-1);return true;"></form></td>
                    </tr>
                </table>
            </form>
        </section>
    </body>
</html>
