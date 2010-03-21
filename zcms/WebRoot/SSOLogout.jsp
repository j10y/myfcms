<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="com.zving.zas.*"%>
<%@page import="com.zving.zas.client.*"%>
<%@page import="com.zving.framework.*"%>

<%
if(session.getAttribute(com.zving.zas.Constant.UserSessionAttrName)!=null){
	UserData user = (UserData) session.getAttribute(com.zving.zas.Constant.UserSessionAttrName);
	//PGTUtil.removePGT(user.getUserName());
	session.invalidate();
	String renew = ClientConfig.isNeedNewLogin() ? "&renew=" + ClientConfig.isNeedNewLogin() : "";
	response.sendRedirect(ClientConfig.getServerURL() + com.zving.zas.Constant.LogoutPage + "?service=" + ZASFilter.getReferer(request) + renew);
}else{
	session.invalidate();
	response.sendRedirect(Config.getContextPath() + Config.getValue("App.LoginPage"));
}
%>
