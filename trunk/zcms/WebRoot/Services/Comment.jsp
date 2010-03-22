<%@page import="com.zving.cms.site.BadWord"%>
<%@page import="java.util.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%@page import="com.zving.schema.*"%>
<%@page import="com.zving.platform.pub.*"%>
<%@page import="com.zving.cms.pub.SiteUtil"%>
<%@page import="com.zving.member.Login"%>
<%@page import="com.zving.framework.User"%>
<%@ taglib uri="controls" prefix="z"%>

<%
		Login.checkAndLogin(request);
		String relaID = request.getParameter("RelaID");
		String title = request.getParameter("Title");
		String content = request.getParameter("CmntContent");

		if(!StringUtil.isEmpty(content)) {
			String catalogID = request.getParameter("CatalogID");
			String catalogType = request.getParameter("CatalogType");
			String siteID = request.getParameter("SiteID");
			String ip = request.getRemoteAddr();
			String anonymous = request.getParameter("CmntCheckbox");
			String parentID = request.getParameter("ParentID");
			String user = request.getParameter("CmntUserName");
			String password = request.getParameter("CmntPwd");
			if(User.isLogin()){
				user = User.getUserName();
			}else{
				QueryBuilder qb = null;
				qb = new QueryBuilder("select count(*) from ZDMember where UserName=? and Password=?");
				qb.add(user);
				qb.add(StringUtil.md5Hex(password).trim());
				if("on".equals(anonymous)) {
					user = "匿名网友";
				} else if(StringUtil.isEmpty(user) || StringUtil.isEmpty(password)){
					out.print("<script type='text/javascript'>alert('请输入用户名和密码，或选匿名发表！');window.history.go(-1);</script>");
					return;				
				} else if(qb.executeString().equals("0")) {
					out.print("<script type='text/javascript'>alert('用户名或密码输入不正确！');window.history.go(-1);</script>");
					return;
				} else if(qb.executeInt()>0){
					Login login = new Login();
					login.loginComment(request,user,password);
				}
			}
			if(StringUtil.isEmpty(title)){
				title = "无";
			}
			boolean commentFlag = SiteUtil.getCommentAuditFlag(siteID);
			
			DataTable dt = new QueryBuilder("select * from ZCComment where ID = ?",parentID).executeDataTable();
			String CommentAddUser = "";
			String CommentAddTime = "";
			String CommentAddUserIp = "";
			String CommentContent = "";
			if(dt.getRowCount()>0){
				CommentAddUser = dt.getString(0,"AddUser");
				CommentAddTime = dt.getString(0,"AddTime");
				CommentAddUserIp = dt.getString(0,"AddUserIP");
				CommentContent = dt.getString(0,"Content");
			}
			
			ZCCommentSchema comment = new ZCCommentSchema();
			comment.setID(NoUtil.getMaxID("CommentID"));
			comment.setCatalogID(catalogID);
			comment.setCatalogType(catalogType);
			comment.setRelaID(relaID);
			comment.setSiteID(siteID);
			comment.setTitle(BadWord.filterBadWord(title));
			if(parentID != "" && parentID != null) {
				comment.setContent(BadWord.filterBadWord("<div class='huifu'>"+CommentAddUser+" "+CommentAddTime+" IP:"+CommentAddUserIp+"<br>"+CommentContent+"</div><br>"+content));
			} else {
				comment.setContent(BadWord.filterBadWord(content));
			}
			comment.setAddUser(user);
			comment.setAddTime(new Date());
			comment.setAddUserIP(ip);
			if(commentFlag){
				comment.setVerifyFlag("X");
			}else{
				comment.setVerifyFlag("Y");
			}
			if(comment.insert()){
				if(commentFlag){
					out.println("<script type='text/javascript'>alert('您的评论已经提交,请等待管理员审核');window.location='"+request.getHeader("REFERER")+"';</script>");
				}else{
					out.println("<script type='text/javascript'>alert('发表评论成功');window.location='"+request.getHeader("REFERER")+"';</script>");
				}
				//out.println("发表评论成功");
			}else{
				out.println("<script type='text/javascript'>alert('发表评论失败');window.location='"+request.getHeader("REFERER")+"';</script>");
				//out.println("发表评论失败");
			}
			//System.out.println(request.getHeader("REFERER"));
			//response.sendRedirect(request.getHeader("REFERER"));
			
		}else{
			out.print("<script type='text/javascript'>alert('提交的内容不能空');window.location='"+request.getHeader("REFERER")+"';</script>");
		}
%>
