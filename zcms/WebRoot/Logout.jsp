<%@page import="com.zving.framework.*"%>
<% 
	session.invalidate();
	response.sendRedirect(Config.getContextPath() + Config.getValue("App.LoginPage"));
%>