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
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
        <title>Login page</title>

    </head>
    <body>
        <c:choose>
            <c:when test="${hasValue == true}">
                <div class="container">    
                    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="panel panel-info" >
                            <div class="panel-heading">
                                <div class="panel-title">Sign In</div>
                            </div>
                            <div style="padding-top:30px" class="panel-body" >
                            <form:form class="form-horizontal" role="form" method="POST" commandName="login" action="${pageContext.request.contextPath}/index">
                                            <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                            <form:input path="username" class="form-control" placeholder="username" />
                                        </div>
                                        <div style="margin-bottom: 25px" class="input-group">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <form:password path="password" class="form-control" placeholder="password" />
                                        </div>
                                        <div class="col-sm-12 controls">
                                            <form:button type="submit" value="login" class="btn btn-danger">Login</form:button>
                                            <p class="message">Unsuccesvol login, user does not exist</p>
                                        </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container">    
                    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="panel panel-info" >
                            <div class="panel-heading">
                                <div class="panel-title">Sign In</div>
                            </div> 
                           <div style="padding-top:30px" class="panel-body" >
                            <form:form class="form-horizontal" role="form" method="POST" commandName="login" action="${pageContext.request.contextPath}/index">
                                            <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                            <form:input path="username" class="form-control" placeholder="username" />
                                        </div>
                                        <div style="margin-bottom: 25px" class="input-group">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <form:password path="password" class="form-control" placeholder="password" />
                                        </div>
                                        <div class="col-sm-12 controls">
                                        <form:button type="submit" value="login" class="btn btn-primary">Login</form:button>
                                        </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>
