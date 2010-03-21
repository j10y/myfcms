<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
out.println(com.zving.cms.stat.VisitCount.getInstance().toString().replaceAll("\\n","<br>"));
%>
