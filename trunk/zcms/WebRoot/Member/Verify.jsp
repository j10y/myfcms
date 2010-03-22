<%@page import="com.zving.framework.User"%>
<%@page import="com.zving.framework.data.QueryBuilder"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%
//判断是否登录
if(!User.isMember()||!User.isLogin()){
	String SiteID = request.getParameter("SiteID");
	if(StringUtil.isEmpty(SiteID)){
		SiteID = new QueryBuilder("select ID from ZCSite order by AddTime desc").executeOneValue()+"";
	}
	out.println("<script>alert('您未登录，请登陆!');window.parent.location='Login.jsp?SiteID="+SiteID+"';</script>");
	return;
}
%>