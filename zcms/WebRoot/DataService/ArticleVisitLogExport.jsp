<%@page import="java.io.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.framework.data.*"%>
<%
String ids = request.getParameter("ids");
String at = request.getParameter("at");
String rn = request.getParameter("rn");
String sd = request.getParameter("sd");
String ed = request.getParameter("ed");
String sql = "";
if(StringUtil.isNotEmpty(ids)){
	sql = "select ArticleID as 文章ID,ArticleTitle as 文章标题,UserName as 用户姓名,IP as 访问IP,AddTime as 访问时间 from zcarticlevisitlog where id in ("+ids+")";
}else{
	StringBuffer conditions = new StringBuffer();
	if(StringUtil.isNotEmpty(at)){
		conditions.append(" and ArticleTitle like '%");
		conditions.append(at.trim()+"%'");
	}
	if(StringUtil.isNotEmpty(rn)){
		conditions.append(" and RealName like '%");
		conditions.append(rn.trim()+"%'");
	}
	if(StringUtil.isNotEmpty(sd)){
		conditions.append(" and AddTime > '"+sd.trim()+" 00:00:00'");
	}
	if(StringUtil.isNotEmpty(ed)){
		conditions.append(" and AddTime < '"+ed.trim()+" 23:59:59'");
	}
	sql = "select ArticleID as 文章ID,ArticleTitle as 文章标题,UserName as 用户姓名,IP as 访问IP,AddTime as 访问时间 from zcarticlevisitlog where 1=1" + conditions + " order by ID desc";
}
DataTable dt = new QueryBuilder(sql).executeDataTable();
String now = DateUtil.getCurrentDate();
try {
	OutputStream os = response.getOutputStream();
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+new String(("文章访问日志"+now+".xls").getBytes("UTF-8"),"ISO-8859-1"));
	DataTableUtil.dataTableToExcel(dt,os);
	os.flush();
	os.close();
} catch (IOException e) {
	e.printStackTrace();
}	
%>
