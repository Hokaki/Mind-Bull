<html>
<body>
<div class="planner" id="planner"><%= getPlanner(request) %></div>
<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*" %>
<%!
           String getPlanner(HttpServletRequest request) throws Exception {
           DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
           s.setWidth(900);
           s.setInitialDate(2013, 0, 21);
           s.load("events.jsp", DHXDataFormat.JSON);
           return s.render();
}
%>
</body>
</html>