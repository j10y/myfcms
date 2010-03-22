<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.zving.framework.data.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import=" com.zving.cms.pub.SiteUtil"%>
<%@ taglib uri="controls" prefix="z"%>
<%
	String siteID = request.getParameter("SiteID");
	boolean commentFlag = SiteUtil.getCommentAuditFlag(siteID);
	String WherePart = "";
	if (commentFlag) {
		WherePart = " and verifyflag='Y'"; //评论需要审核
	}
	String relaID = request.getParameter("RelaID");
	String sql = "select count(*) from zccomment where RelaID=" + relaID + WherePart;
	int count = new QueryBuilder(sql).executeInt();
	out.println("if(navigator.userAgent.toLowerCase().indexOf(\"gecko\") != -1)document.getElementById(\"CmntCount\").textContent=\"" + count + "\";else document.getElementById(\"CmntCount\").innerText=\"" + count + "\";");
%>
