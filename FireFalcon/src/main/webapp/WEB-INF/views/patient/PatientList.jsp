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
        <link href="/FireFalcon/css/fontello/css/fontello.css" rel="stylesheet">
        <link href="/FireFalcon/css/fontello/css/animation.css" rel="stylesheet">
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
        <title>Patient list</title>
        <style>
            btn-info{
                width: 45px;
            }
        </style>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>Patients</h2>
                    <h3>${message}</h3>
                    <c:choose>
                        <c:when test="${patientList.size() != 0}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr class="info">
                                            <th>BSN</th>
                                            <th>Name</th>
                                            <th>Edit</th>
                                            <th>Affliction</th>
                                            <th>Assign</th>
                                            <th>Chart</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="patient" items="${patientList}">
                                            <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                            <tr>
                                                <td>${patient.bsn}</td>
                                                <td><a href="${pageContext.request.contextPath}/patient/view/${patient.encrypt()}">${patient.firstName} ${patient.lastName}</a></td>
                                                <td><a class="btn btn-info glyphicon glyphicon-pencil" href="${pageContext.request.contextPath}/patient/edit/${patient.encrypt()}"></a></td>
                                                <td><a class="btn btn-info fa fa-medkit" style="line-height:1.5" href="${pageContext.request.contextPath}/affliction/list/${patient.encrypt()}"></a></td>
                                                <td><a class="btn btn-info glyphicon glyphicon-plus-sign" href="${pageContext.request.contextPath}/assignment/add/${patient.bsn}"></a></td>
                                                <td><a class="btn btn-info fa fa-bar-chart-o" href="${pageContext.request.contextPath}/patient/view/${patient.bsn}" style="padding-top:9px;height:34px;"></a></td>
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
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/patient/add">Add Patient</a>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>