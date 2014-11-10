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
        <title>Edit patient</title>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>Edit patients</h2>
                    <form:form class="form-horizontal" role="form" method="POST" commandName="patient" action="${pageContext.request.contextPath}/patient/edit">  
                        <div class="form-group">
                            <label for="inputFN" class="col-sm-2 control-label">First name</label>
                            <div class="col-sm-10">     
                                <form:input path="firstName" class="form-control" id="inputFN" placeholder="First name" />
                            </div>
                        </div>
                         <div class="form-group">
                            <label for="inputLN" class="col-sm-2 control-label">Last name</label>
                            <div class="col-sm-10">
                                <form:input path="lastName" class="form-control" id="inputLN" placeholder="Last name" />
                            </div>
                        </div>
                            <div class="form-group">                                
                            <div class="col-sm-2"></div>
                            <div class="col-sm-4">     
                                <input class="btn btn-default" type="submit" value="Edit" />
                            </div>
                        </div>
                         <form:hidden path="BSN"/>
                    </form:form>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>
