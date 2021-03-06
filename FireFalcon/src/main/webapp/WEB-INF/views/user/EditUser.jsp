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
        <title>Edit User</title>
    </head>
    <body>  
        <div id="wrapper">
                <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Activeindex">Administrator control</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${oldUser.username}<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/login"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div>
            <c:choose>
                <c:when test="${isAdmin == 'true'}">
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav side-nav">
                            <li class="active">
                                <a href=""><i class="fa fa-fw fa-dashboard"></i> Control Panel</a>
                            </li>

                            <li>
                                <a href="javascript:;" data-toggle="collapse" data-target="#demo2"><i class="fa fa-fw fa-arrows-v"></i> Users <i class="fa fa-fw fa-caret-down"></i></a>
                                <ul id="demo2" class="collapse">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/user/list">Users overview</a>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav side-nav">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/Activeindex"><i class="fa fa-fw fa-dashboard"></i> Control Panel</a>
                            </li>
                            <li>
                                <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Users <i class="fa fa-fw fa-caret-down"></i></a>
                                <ul id="demo" class="collapse">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/user/list">Users overview</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
            
            
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>Edit User</h2>
                    <form:form class="form-horizontal" role="form" method="POST" commandName="user" action="${pageContext.request.contextPath}/user/edit">  
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
                            <label for="inputLN" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-10">
                                <form:password path="password" class="form-control" id="inputPW" placeholder="password" />
                            </div>
                        </div>

                            <div class="form-group">                                
                            <div class="col-sm-2"></div>
                            <div class="col-sm-4">
                                <input class="btn btn-default" type="submit" value="Edit" />
                            </div>
                        </div>
                            <form:hidden path="username"/>
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
