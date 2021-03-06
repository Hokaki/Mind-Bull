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
            <%@ include file="../navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>Assignment view</h2>
                    <c:choose>
                        <c:when test="${assignments.size() != 0}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr class="info">
                                            <th>ID Assignment</th>
                                            <th>Patient Name</th>
                                            <th>Exercise Name</th>
                                            <th>Repetition</th>
                                            <th>Start date</th>
                                            <th>End date</th>
                                             <th>edit</th>
                                            <th>delete</th>        
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="assignments" items="${assignment}">
                                            <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                            <tr>
                                                <td>${assignments.idAssignment}</td>
                                                <td>${assignments.bsn.firstName} ${assignments.bsn.lastName}</td>
                                                <td>${assignments.exerciseId.name}</td>
                                                <td>${assignments.repetitions}</td>
                                                <td>${assignments.start_date}</td>
                                                <td>${assignments.end_date}</td>
                                                <td><a class="btn btn-info glyphicon glyphicon-pencil" href="${pageContext.request.contextPath}/assignment/edit/${assignments.idAssignment}"></a></td>
                                                <td><a href="${pageContext.request.contextPath}/assignment/delete/${assignments.idAssignment}">delete</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>        
                        </c:when>
                        <c:otherwise>
                            There were no Assingment found.
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