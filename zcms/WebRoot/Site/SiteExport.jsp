<%@page import="com.zving.framework.utility.DateUtil"%>
<%@page import="com.zving.cms.pub.SiteUtil"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="com.zving.cms.pub.SiteExporter"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%@include file="../../Include/Init.jsp"%>
<%
	String ID = request.getParameter("ID");
	boolean flag  = "Y".equals(request.getParameter("ExportMediaFile"));
	if(StringUtil.isEmpty(ID)){
		out.println("ID不能为空!");
		return;
	}
	if(StringUtil.isEmpty(ID)){
		out.println("没有管理本站点的权限!");
		return;
	}
	request.setCharacterEncoding("UTF-8");
	response.reset();
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+SiteUtil.getAlias(ID)+"_"+DateUtil.getCurrentDateTime("yyyyMMddhhmm")+".dat");	
	SiteExporter se = new SiteExporter(Long.parseLong(ID),flag);
	se.exportSite(response);
%>
