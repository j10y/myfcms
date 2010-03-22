<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
long siteID = Application.getCurrentSiteID();
long aritcleID = Long.parseLong(request.getParameter("ArticleID"));
ZCArticleSchema article = new ZCArticleSchema();
article.setID(aritcleID);
if(article.fill()){
	String path = "";
	if((int)article.getStatus()!=Article.STATUS_PUBLISHED){
		path="../Services/PreviewDoc.jsp?ArticleID="+aritcleID;
	}else{
	  	long catalogID = article.getCatalogID();
		String isSingle = CatalogUtil.getSingleFlag(catalogID);
		if("Y".equals(isSingle)){
		  	path = Config.getValue("Statical.TargetDir")+"/"+SiteUtil.getAlias(siteID)+"/"+CatalogUtil.getPath(catalogID);
		} else {
		  	path = Config.getValue("Statical.TargetDir")+"/"+SiteUtil.getAlias(siteID)+"/"+PubFun.getArticleURL(aritcleID);
		}
		String realPath = Config.getContextRealPath()+path;
		File f = new File(realPath);
		if(!f.exists()){
			path="../Services/PreviewDoc.jsp?ArticleID="+aritcleID;
		}else{
			path = Config.getContextPath()+path+"?"+System.currentTimeMillis();
		}
  }
	response.sendRedirect(path);
}else{
	out.println("没有找到id为"+aritcleID+"的文章");
}
%>


