<%@ page import="com.zving.schema.ZCForumSchema" %>
<%@ page import="com.zving.framework.Config" %>
<%@ page import="com.zving.framework.utility.StringUtil" %>
<%@ page import="com.zving.bbs.Forum" %>
<%@ page import="com.zving.bbs.admin.ForumUtil" %>
<%@ page import="com.zving.bbs.ForumPriv" %>
<%@ page import="com.zving.framework.User" %>

<%
	String ForumID = request.getParameter("ForumID");
	String SiteID = request.getParameter("SiteID");
		
	if(User.getValue("AllowMemberVisit")!=null){
		if(!User.getValue("AllowMemberVisit").equals("Y")){
			out.println("<script>alert('您所在的用户组禁止访问论坛'); window.location='"+Config.getContextPath()+"/BBS';</script>");
		}	
	}else{
		ForumUtil.allowVisit(SiteID);
	}
	
	if(StringUtil.isNotEmpty(ForumID)){
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID(ForumID);
		forum.fill();
		if(forum.getLocked().equals("Y")){
			out.println("<script>alert('该板块已被锁定'); window.location='"+Config.getContextPath()+"/BBS';</script>");
		}else if (StringUtil.isNotEmpty(forum.getPassword())) {
			if(User.getValue("pass_"+forum.getID())==null || !((String)User.getValue("pass_"+forum.getID())).equals("Y")){
				out.println("<script>alert('该板块已设定密码，您不能直接进入'); window.location='"+Config.getContextPath()+"/BBS';</script>");
			}
		}
	}
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
%>