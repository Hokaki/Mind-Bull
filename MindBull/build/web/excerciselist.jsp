<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="support.HibernateUtil"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="model.Excercise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excerciselist</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <section id="main_section">
            <%
                List<Excercise> excercises = new LinkedList();
                Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = hibernateSession.beginTransaction();
                Criteria criteria = hibernateSession.createCriteria(Excercise.class);
                excercises = criteria.list();
                tx.commit();
            %>
            <h2>This is the excercise list of mindbull physiotherapy</h2>
            <form action="excerciselisthandler" method="post">
                <table>
                    <tr>
                        <td><strong>Excercise name</strong></td>
                        <td><strong>Description</strong></td>
                    </tr>
                    <%
                        for (Excercise excercise : excercises) {
                            Long id = excercise.getId();
                    %> <tr>
                        <td><%= excercise.getName()%></td>
                        <td><%= excercise.getDescription()%></td>
                        <td>
                            <button type="submit" name="edit" value="<%=id%>">
                                Change
                            </button>
                        </td>
                    </tr> <%
                        }
                    %>
                </table>
            </form>
        </section>
    </body>
</html>
