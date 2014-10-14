<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>patients</title>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>patients</h2>
        <h3>${message}</h3>
        <c:choose>
            <c:when test="${patientList.size() != 0}">
                <table>
                    <tr>
                        <td>
                            <strong>BSN</strong>
                        </td>
                        <td>
                            <strong>First Name</strong>
                        </td>
                        <td>
                            <strong>Last Name</strong>
                        </td>
                    </tr>
                    <c:forEach var="patient" items="${patientList}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr>
                            <td>${patient.bsn}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                There were no patients found.
            </c:otherwise>
        </c:choose>
    </body>
</html>
