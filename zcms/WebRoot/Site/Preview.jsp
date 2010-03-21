<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
int type = Integer.parseInt(request.getParameter("Type"));
long siteID = Application.getCurrentSiteID();
String path = "";
path = com.zving.framework.Config.getContextPath()+com.zving.framework.Config.getValue("Statical.TargetDir")
		+"/"+SiteUtil.getAlias(siteID)+"/";
path = path.replaceAll("///","/");
path = path.replaceAll("//","/");
if(type==1){
	long catalogID = Long.parseLong(request.getParameter("CatalogID"));
	path += CatalogUtil.getPath(catalogID);
	path += "index.shtml";
}else if(type==2){
	path += request.getParameter("File");
}else{
	path += "index.shtml";
}

response.sendRedirect(path+"?"+System.currentTimeMillis());
%>
