<%@ page contentType="application/json" %>
<%= getEvents(request) %>
<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.demo.*" %>
<%!
    String getEvents(HttpServletRequest request) throws Exception {
    EventManager evs = new EventManager(request);
    return evs.run();
  }
%>