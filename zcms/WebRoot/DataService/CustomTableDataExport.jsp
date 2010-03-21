<%@include file="../../Include/Init.jsp"%>
<%
CustomTable.exportExcel(request,response);
out.clear();
out = pageContext.pushBody();
%>