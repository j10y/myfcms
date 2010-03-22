<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.framework.User"%>
<%@page import="com.zving.platform.Application"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%@page import="com.zving.schema.ZCForumAttachmentSchema"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function getPassword(ID, IsFirst) {
	if (IsFirst == 'true') {
		var password = window.prompt("请输入密码：");
		if (password == null) {
			window.close();
		}
		window.location = Server.ContextPath + "Services/AttachDownLoad.jsp?id="+ID+"&Password="+password;
	} else {
		var password = window.prompt("您输入的密码有误！请重新输入：");
		if (password == null) {
			window.close();
		}
		window.location = Server.ContextPath + "Services/AttachDownLoad.jsp?id="+ID+"&Password="+password;
	}
}
</script>
</head>
<body>
<%
	String ID = request.getParameter("id");
	String fileName = request.getParameter("FileName");
	String password = request.getParameter("Password");
	if(StringUtil.isNotEmpty(ID)){
		ZCForumAttachmentSchema attachment = new ZCForumAttachmentSchema();
		attachment.setID(ID);
		if (!attachment.fill()){
			out.println("<script>alert('数据传入错误！');window.close();</script>");
		}
		
		
		String FilePath = (Config.getContextRealPath()+ attachment.getPath()).replaceAll("//", "/");
		try {
			if(new File(FilePath).exists()) {
				OutputStream os = response.getOutputStream();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=" 
						+ new String(attachment.getName().getBytes("UTF-8"),"ISO-8859-1"));
				FileInputStream in = new FileInputStream(FilePath);
				new QueryBuilder("update ZCForumAttachment set DownCount = DownCount+1 where ID = ?",ID).executeNoQuery();
				IOUtils.copy(in,os);
				os.flush();
				os.close();
				in.close();
			} else {
				out.println("<script>alert('文件不存在或已经被删除！');window.close();</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} else {
		String FilePath = (Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias() 
				+ "/upload/Attach/" + fileName).replaceAll("//", "/");
		try {
			if(new File(FilePath).exists()) {
				OutputStream os = response.getOutputStream();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
				FileInputStream in = new FileInputStream(FilePath);
				IOUtils.copy(in,os);
				os.flush();
				os.close();
				in.close();
			} else {
				out.println("<script>alert('文件不存在或已经被删除！');window.close();</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	out.println("<script>window.close();</script>");
%>
</body>
</html>