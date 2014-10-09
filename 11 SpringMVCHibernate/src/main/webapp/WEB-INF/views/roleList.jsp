<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Rollen</title>
          <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>Rollen</h2>
        <h3>${message}</h3>
        <c:choose>
            <c:when test="${roleList.size() != 0}">
                <!-- Wanneer er gebruikers opgeslagen zijn, worden ze hier getoond -->
                <table class="roleList">
                    <tr>
                        <td>
                            <strong>Naam</strong>
                        </td>
                        <td>
                            <strong>Id</strong>
                        </td>
                    </tr>
                    <c:forEach var="role" items="${roleList}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr> 
                            <td>${role.name}</td>
                            <td>${role.roleId}</td>
                            <td><a href="${pageContext.request.contextPath}/role/edit/${role.roleId}">Wijzig</a> | 
                                <a href="${pageContext.request.contextPath}/role/remove/${role.roleId}">Verwijder</a>
                            </td>    
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
             <c:otherwise>
                <!-- Als er geen gebruikers zijn, wordt deze melding getoond -->
                Er zijn geen rollen gevonden.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="${pageContext.request.contextPath}/role/add">Voeg een nieuwe rol toe</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/index">Terug naar de index</a>
        </p>
    </body>
</html>
