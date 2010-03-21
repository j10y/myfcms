<%@page import="com.zving.framework.messages.LongTimeTask"%>
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
		long id = Long.parseLong(request.getParameter("TaskID").trim());
		LongTimeTask ltt = LongTimeTask.getInstanceById(id);
		if (ltt != null && ltt.isAlive()) {
			//$S("CurrentInfo", ltt.getCurrentInfo());
			out.println(ltt.getPercent());
			out.println(ltt.getCurrentInfo());
			System.out.println(ltt.getCurrentInfo());
		} else {
			out.println("100");
			out.println("转换成功");
			LongTimeTask.removeInstanceById(id);
			System.out.println("task:100");
		}
%>
