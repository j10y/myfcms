<%@page import="java.io.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%
String ids = request.getParameter("ids");
String sql = "";
if(StringUtil.isNotEmpty(ids)){
	sql = "select KeyWord,LinkUrl,LinkAlt,LinkTarget from ZCKeyWord where id in ("+ids+")";
}else{
	sql = "select KeyWord,LinkUrl,LinkAlt,LinkTarget from ZCKeyWord";
}
DataTable dt = new QueryBuilder(sql).executeDataTable();
for(int i=0;i<dt.getRowCount();i++){
	if(dt.getString(i,"LinkTarget").equals("_self")){
		dt.set(i,"LinkTarget",1);
	}else if(dt.getString(i,"LinkTarget").equals("_blank")){
		dt.set(i,"LinkTarget",2);
	}else if(dt.getString(i,"LinkTarget").equals("_parent")){
		dt.set(i,"LinkTarget",3);
	}else{
		dt.set(i,"LinkTarget",1);
	}
}
String now = DateUtil.getCurrentDate();
try {
	OutputStream os = response.getOutputStream();
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+ new String(("关键词导出"+now+".txt").getBytes("UTF-8"),"ISO-8859-1"));
	DataTableUtil.dataTableToTxt(dt,os,new String[0]);
	os.flush();
	os.close();
} catch (IOException e) {
	e.printStackTrace();
}	
%>
