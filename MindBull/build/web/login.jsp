<%-- 
    Document   : login
    Created on : 3-okt-2014, 12:47:57
    Author     : Mohamed
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Login</title>
        <style>
            li {
                color: #ff0000;
            }            
        </style>
    </head>
    
    <body>
        <h2>Log into a new world</h2>
        
         <spring:form method="POST" action="login">
            
            <table>
                <tr>
                    <td>UserName : </td>
                    <td><spring:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><spring:password path="password" /></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
