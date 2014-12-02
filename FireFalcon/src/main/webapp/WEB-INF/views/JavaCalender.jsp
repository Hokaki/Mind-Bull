<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>
    
    <script src="<c:url value="/css/codebase/dhtmlxscheduler.js" />" type="text/javascript" charset="utf-8"></script>
    <link href="<c:url value="/css/codebase/dhtmlxscheduler.css" />" rel="stylesheet" type="text/css">
    <!--<link href="<c:url value="/css/codebase/dhtmlxscheduler_classic.css" />" rel="stylesheet" type="text/css">-->
    <!--<link href="<c:url value="/css/codebase/dhtmlxscheduler_glossy.css" />" rel="stylesheet" type="text/css">-->
<body>
    <h2>Patient Planner</h2>
    <label>Exercise</label>
    <select>
        <option>Exercise1</option>
        <option>Exercise2</option>
        <option>Exercise3</option>
    </select>
<div class="planner" id="planner">${body}</div>
</body>
</html>