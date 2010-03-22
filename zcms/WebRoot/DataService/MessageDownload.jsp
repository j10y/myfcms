<%@page import="com.zving.platform.Application"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%
String ID = request.getParameter("id");
String SiteAlias = Application.getCurrentSiteAlias();
if(StringUtil.isEmpty(SiteAlias)){
	SiteAlias = new QueryBuilder("select Alias from ZCSite Order by AddTime desc").executeOneValue()+""; 
}
if(StringUtil.isNotEmpty(ID)&&StringUtil.isNotEmpty(SiteAlias)){
	DataTable dt = new QueryBuilder("select AttachPath,Prop1 from ZCMessageBoard where ID = ?",ID).executeDataTable();
	String FilePath = (Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/" + dt.get(0, "AttachPath")).replaceAll("//", "/");
	if(!new File(FilePath).exists()){
		out.println("<script>alert('文件不存在或已经被删除！');window.close();</script>");
		return;
	}
	try {
		OutputStream os = response.getOutputStream();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+new String((dt.getString(0,"Prop1")).getBytes("UTF-8"),"ISO-8859-1"));
		FileInputStream in = new FileInputStream(FilePath);
		IOUtils.copy(in,os);
		os.flush();
		os.close();
		in.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
out.println("<script>window.close();</script>");
%>
