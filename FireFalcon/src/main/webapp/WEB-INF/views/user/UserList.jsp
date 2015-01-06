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
        <title>User list</title>

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
            <h2>Users</h2>
            <h3>${message}</h3>
            <c:choose>
                <c:when test="${userList.size() != 0}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr class="info">
                                    <th>Username</th>
                                    <th>Name</th>
                                    <th>Admin</th>
                                    <th>Edit</th>
                                    <!--<th>Delete</th>-->
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td>${user.username}</td>
                                        <td>${user.firstName} ${user.lastName}</td>
                                        <td>${user.isAdmin}</td>
                                        <td><a class="btn btn-info glyphicon glyphicon-pencil" href="${pageContext.request.contextPath}/user/edit/${user.username}"></a></td>
                                        <!--<td><a class="btn btn-danger glyphicon glyphicon-remove" href="${pageContext.request.contextPath}/user/delete/${user.username}"></a></td>-->
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>        
                </c:when>
                <c:otherwise>
                    There were no users found.
                </c:otherwise>
            </c:choose>
            <a class="btn btn-success" href="${pageContext.request.contextPath}/user/add">Add User</a>
        </div>
        <!--.container-fluid--> 
    </div>
    <!--#page-wrapper--> 
    </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>