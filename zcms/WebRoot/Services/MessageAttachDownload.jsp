<%@page import="com.zving.framework.Config"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%
String ID = request.getParameter("id");
String SiteID = request.getParameter("SiteID");
if(StringUtil.isNotEmpty(ID)&&StringUtil.isNotEmpty(SiteID)){
	String SiteAlias = new QueryBuilder("select Alias from ZCSite where ID = ?",SiteID).executeString();
	DataTable dt = new QueryBuilder("select AttachPath,Prop1 from ZCMessageBoard where ID = ?",ID).executeDataTable();
	String FilePath = (Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/" + dt.get(0, "AttachPath")).replaceAll("//", "/");
	try {
		OutputStream os = response.getOutputStream();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+new String((dt.getString(0,"Prop1")).getBytes("GBK"),"ISO-8859-1"));
		FileInputStream in = new FileInputStream(FilePath);
		IOUtils.copy(in,os);
		os.flush();
		os.close();
		in.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
%>
