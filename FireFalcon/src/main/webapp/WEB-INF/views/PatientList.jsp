<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link href="<c:url value="/css/font-awesome-4.1.0/css/font-awesome.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/therapist-control.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
        <title>Patient list</title>

    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>patients</h2>
                    <h3>${message}</h3>
                    <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/patient/add">add</a></td>
                    <c:choose>
                        <c:when test="${patientList.size() != 0}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>BSN</th>
                                            <th>Name</th>
                                            <th>Enroll</th>
                                            <th>Edit</th>
                                            <th>Affliction</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="patient" items="${patientList}">
                                            <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                            <tr>
                                                <td>${patient.BSN}</td>
                                                <td>${patient.firstName} ${patient.lastName}</td>
                                                <td><a class="btn btn-primary">Enroll</a></td>
                                                <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/patient/edit/${patient.BSN}">Edit</a></td>
                                                <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/affliction/add/">Add</a></td>
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
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>