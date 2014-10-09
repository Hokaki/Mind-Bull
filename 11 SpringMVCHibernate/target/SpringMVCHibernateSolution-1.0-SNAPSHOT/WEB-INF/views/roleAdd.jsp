<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${paginaTitel}</title>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>${paginaTitel}</h2>


        <form:form method="POST" commandName="role" action="${pageContext.request.contextPath}/role/add">


            <table border="0">
                <tbody>
                    <tr>
                        <td>Naam</td>
                        <td><form:input path="name" /></td>
                    </tr>

                    <tr>
                        <td><input type="submit" value="Add" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>


        </form:form>
    </body>
</html>