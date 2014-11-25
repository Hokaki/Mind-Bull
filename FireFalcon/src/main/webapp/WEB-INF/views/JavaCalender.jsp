<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>
    <script src="<c:url value="/css/codebase/dhtmlxscheduler.js" />" type="text/javascript" charset="utf-8"></script>
    <link href="<c:url value="/css/codebase/dhtmlxscheduler.css" />" rel="stylesheet" type="text/css">
    <!--<link href="<c:url value="/css/codebase/dhtmlxscheduler_classic.css" />" rel="stylesheet" type="text/css">-->
    <!--<link href="<c:url value="/css/codebase/dhtmlxscheduler_glossy.css" />" rel="stylesheet" type="text/css">-->
<body>
<div class="planner" id="planner">${body}</div>
</body>
</html>