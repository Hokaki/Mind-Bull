<%-- 
    Document   : ledenlijst
    Created on : Jan 4, 2014, 6:48:29 PM
    Author     : huub
--%>

<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="support.HibernateUtil"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ledenlijst</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <section id="main_section">
            <%
                List<Member> members = new LinkedList();
                Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = hibernateSession.beginTransaction();
                Criteria criteria = hibernateSession.createCriteria(Member.class);
                members = criteria.list();
                tx.commit();
            %>
            <h2>Dit is de ledenlijst van de Jeu de Boules Club</h2>
            <form action="ledenlijsthandler" method="post">
                <table>
                    <tr>
                        <td><strong>Voornaam</strong></td>
                        <td><strong>Achternaam</strong></td>
                    </tr>
                    <%
                        for (Member member : members) {
                            Long id = member.getId();
                    %> <tr>
                        <td><%= member.getFirstName()%></td>
                        <td><%= member.getLastName()%></td>
                        <td>
                            <button type="submit" name="edit" value="<%= id%>">
                                Wijzig <%= member.getFirstName()%>
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
