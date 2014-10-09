<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Gebruikers</title>
         <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>Gebruikers</h2>
        <h3>${message}</h3>
        <c:choose>
              <c:when test="${userList.size() != 0}">
                <!-- Wanneer er gebruikers opgeslagen zijn, worden ze hier getoond -->
                <table class="userList">
                    <tr>
                         <td>
                            <strong>Id</strong>
                        </td>
                        <td>
                            <strong>Naam</strong>
                        </td>
                        <td>
                            <strong>Adres</strong>
                        </td>
                        <td>
                            <strong>Huisnummer</strong>
                        </td>
                        <td>
                            <strong>Plaats</strong>
                        </td>
                         <td>
                            <strong>Rol</strong>
                        </td>
                       
                    </tr>
                    <c:forEach var="user" items="${userList}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.name}</td>
                            <td>${user.streetAddress}</td>
                            <td>${user.houseNumber}</td>
                            <td>${user.city}</td>
                            <td>${user.role.name}</td>
                            
                            <td><a href="${pageContext.request.contextPath}/user/edit/${user.userId}">Wijzig</a> </td>
                            <td><a href="${pageContext.request.contextPath}/user/remove/${user.userId}">Verwijder</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <!-- Als er geen gebruikers zijn, wordt deze melding getoond -->
                Er zijn geen gebruikers gevonden.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="${pageContext.request.contextPath}/user/add">Maak nieuwe gebruiker aan</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/index">Terug naar de index</a>
        </p>
    </body>
</html>
