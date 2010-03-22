<%@page import="com.zving.cms.stat.VisitHandler"%>
<%
request.setCharacterEncoding("GBK");
response.setContentType("text/html;charset=GBK");
VisitHandler.deal(request,response);
%>