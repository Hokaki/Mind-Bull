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
        <title>JSP Page</title>
        
    </head>
    <body>
        <h1>Welcome</h1>
        
        <form:form method="POST" commandName="login" action="${pageContext.request.contextPath}/login">
            <div class="col-sm-3 col-xs-12">
            <div class="form-group">
                <label for="username">Username</label> 
                <form:input path="username" class="form-control" placeholder="username" />
            </div>
            <div class="form-group">
                <label for="password">Password</label> 
                <form:password path="password" class="form-control" placeholder="username" />
            </div>
                <form:button type="submit" value="login" class="btn btn-primary">Login</form:button>
            </div>
        </form:form>
        
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>
