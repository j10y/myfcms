<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.lang.reflect.Method"%>
<%@include file="../../Include/Init.jsp"%>
<%
DataGridPage.toExcel(request,response);
out.clear();
out = pageContext.pushBody();
%>