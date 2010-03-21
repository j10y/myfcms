<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
long aritcleID = Long.parseLong(request.getParameter("ArticleID"));
ZCArticleSchema article = new ZCArticleSchema();
article.setID(aritcleID);
if(!article.fill()){
	out.println("没有找到id为"+aritcleID+"的文章");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章预览--<%=article.getTitle()%></title>
<style>
div{ line-height:1.5; font-size: 14px;}
div div{ padding:5px; background-color:#f9f9f9;border:#e6e6e6 1px solid;}
div div div{ padding:5px 10px; background-color:#f5f5f5; border:none;}
</style>
</head>
<body style="text-align:center; background:#fff">
<div style="margin:20px auto; width:70%; text-align:left;">
<div style="margin-bottom:10px;">当前位置：<%=PubFun.getCurrentPage(article.getCatalogID(),0," > ","")%>
</div>
<div>
<div style=" float:right; font-size:12px;">作者：<%=article.getAuthor()%></div>
<div style="text-align:center; margin:0;"><b><%=article.getTitle()%></b></div>
<div style="padding:20px; margin:10px 0 0 0;"><%=article.getContent()%></div>
</div>
</div>
</body>
</html>

