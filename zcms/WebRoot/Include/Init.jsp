<%@ page contentType="text/html; charset=utf-8"%> 
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="com.zving.framework.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%@page import="com.zving.framework.cache.*"%>
<%@page import="com.zving.framework.orm.*"%>
<%@page import="com.zving.framework.controls.*"%>
<%@page import="com.zving.schema.*"%>
<%@page import="com.zving.platform.*"%>
<%@page import="com.zving.cms.site.*"%>
<%@page import="com.zving.cms.document.*"%>
<%@page import="com.zving.cms.dataservice.*"%>
<%@page import="com.zving.cms.pub.*"%>
<%@page import="com.zving.platform.pub.*"%>
<%
if(!com.zving.framework.User.isLogin()||!com.zving.framework.User.isManager()){
	out.println("<script>alert('用户会话己失效，请重新登陆!');window.parent.location=\""+Config.getContextPath()+Config.getValue("App.LoginPage")+"\";</script>");
	return;
}else if(!com.zving.platform.Priv.isValidURL(request.getServletPath())){
	out.println("<script>alert('您没有访问此页面的权限!请联系管理员.');window.parent.location=\""+Config.getContextPath()+Config.getValue("App.LoginPage")+"\";</script>");
	return;
}
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires", 0);
%>
