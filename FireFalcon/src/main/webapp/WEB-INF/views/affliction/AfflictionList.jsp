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
        <title>Add affliction</title>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <h2>Afflictions</h2>
                    <h3>${message}</h3>

                    <c:choose>
                        <c:when test="${affliction.size() != 0}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>BSN</th>
                                            <th>Description</th>
                                            <th>Side Note</th>
                                            <th>Edit</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="affliction" items="${affliction}">
                                            <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                            <tr>
                                                <td>${affliction.bsn}</td>
                                                <td>${affliction.description}</td>
                                                <td>${affliction.sideNote} </td>
                                                <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/affliction/edit/${affliction.bsn}">Edit</a></td>

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
