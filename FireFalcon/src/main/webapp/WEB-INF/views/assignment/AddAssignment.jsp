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
        <title>Add exercise</title>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>Assignment</h2>
                    <form:form class="form-horizontal" role="form" method="POST" commandName="assignment" action="${pageContext.request.contextPath}/assignment/add">  
                        <div class="form-group">
                            <label for="bsn" class="col-sm-2 control-label">bsn</label>
                            <div class="col-sm-10">
                                <label class="control-label">${assignment.bsn.bsn}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputName" class="col-sm-2 control-label">name</label>
                            <div class="col-sm-10">     
                                <form:input path="name" class="form-control" id="inputName" placeholder="name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputExercise" class="col-sm-2 control-label">exercise</label>
                            <div class="col-sm-10">
                                <form:input path="exercise" class="form-control" id="inputExercise" placeholder="exercise" />
                            </div>
                        </div>
                        <div class="form-group">                                
                            <div class="col-sm-2"></div>
                            <div class="col-sm-4">     
                                <input class="btn btn-default" type="submit" value="Add" />
                            </div>
                        </div>
                           <form:hidden path="bsn" value="${assignment.bsn.bsn}"/>
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