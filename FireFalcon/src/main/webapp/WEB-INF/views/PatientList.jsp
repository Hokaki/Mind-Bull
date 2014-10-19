<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>patients</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <div class="container">
            <h2>patients</h2>
            <h3>${message}</h3>
            <c:choose>
                <c:when test="${patientList.size() != 0}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>BSN</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="patient" items="${patientList}">
                                    <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                    <tr>
                                        <td>${patient.BSN}</td>
                                        <td>${patient.firstName}</td>
                                        <td>${patient.lastName}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>        
                </c:when>
                <c:otherwise>
                    There were no patients found.
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
