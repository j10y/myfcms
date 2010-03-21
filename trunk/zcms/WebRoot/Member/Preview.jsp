<%@page import="com.zving.cms.pub.SiteUtil"%>
<%@page import="java.io.File"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="com.zving.schema.ZCArticleSchema"%>
<%@page import="com.zving.framework.data.QueryBuilder"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%@page import="com.zving.cms.document.Article"%>
<%@page import="com.zving.cms.pub.PubFun"%>
<%@page import="com.zving.cms.pub.CatalogUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="Verify.jsp"%>
<%
String SiteID = request.getParameter("SiteID");
if(StringUtil.isEmpty(SiteID)){
	SiteID = new QueryBuilder("select ID from ZCSite order by AddTime desc").executeOneValue()+"";
}
String SiteAlias = SiteUtil.getName(SiteID);
if(StringUtil.isEmpty(SiteAlias)){
	SiteAlias = new QueryBuilder("select Alias from ZCSite Order by AddTime desc").executeOneValue()+""; 
}
long aritcleID = Long.parseLong(request.getParameter("ArticleID"));
ZCArticleSchema article = new ZCArticleSchema();
article.setID(aritcleID);
if(article.fill()){
	String articlepath = "";
	if(article.getStatus()!=Article.STATUS_PUBLISHED){
		articlepath="PreviewDoc.jsp?ArticleID="+aritcleID;
  }else{
	  long catalogID = article.getCatalogID();
		String isSingle = new QueryBuilder("select SingleFlag from zccatalog where id =? ",catalogID).executeString();
		if("Y".equals(isSingle)){
		  articlepath = Config.getValue("Statical.TargetDir")+"/"+SiteAlias+"/"+CatalogUtil.getPath(catalogID);
		}else{
		  articlepath = Config.getValue("Statical.TargetDir")+"/"+SiteAlias+"/"+PubFun.getArticleURL(aritcleID);
		}
		String realPath = Config.getContextRealPath()+articlepath;
		File f = new File(realPath);
		if(!f.exists()){
			out.println("文章没有预览");
			return;
		}else{
			articlepath = Config.getContextPath()+articlepath+"?"+System.currentTimeMillis();
		}
  }
	response.sendRedirect(articlepath);
}else{
	out.println("没有找到id为"+aritcleID+"的文章");
}
%>


