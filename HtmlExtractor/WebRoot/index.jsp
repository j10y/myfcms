<%@ page language="java" session="false" pageEncoding="UTF-8"
	import="java.util.*" import="java.io.*" import="java.util.*"
	import="java.net.*" import="org.HtmlExtractor" import="org.Html"
	import="org.apache.commons.lang.StringUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>网页标题正文提取器</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="网页,标题,正文,提取器">
		<meta http-equiv="description" content="网页标题正文提取器">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		  $("#reportForm").submit(function(e){
		    $("#reportForm").ajaxSubmit();
		    alert("谢谢反馈！");
		    return false;
		  });
		});
		</script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<%
		request.setCharacterEncoding("UTF-8");
		String url = request.getParameter("url");
		Html html = null;
		if (StringUtils.isNotEmpty(url)) {
			html = new HtmlExtractor().processURL(url);
		}
	%>
	<body>
		<div style="width: 800px">
			<center>
				<h3>
					网页标题正文提取器
				</h3>
			</center>
			<br>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;现有的网页标题提取方法主要是通过直接提取网页源文件中的&lt;title&gt;&lt;/title&gt;标签中的文字作为网页标题。
				然而，很多网站为了提高网页在搜索引擎中被检索的可能，添加了很多与网页主题无关但是热门的关键词，甚至有的网站的&lt;title&gt;&lt;/title&gt;标签根本不是网页主题，这给网页标题提取带来了很大的困难。
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;本提取器提供一种网页标题和正文的提取方法，旨在解决现有技术在进行网页标题正文提取时，造成网页搜索的准确率较低的问题。
				
			</p>

			<center>
				<form action="index.jsp">
					请输入网址：
					<input name="url" type="text" size="90"
						value="<%if (url != null) {%><%=url%><%} %>" />
					<input type="submit" />
				</form>
				<br>
			</center>


			<%
				if (html != null) {
			%>
			<form id="reportForm" action="report" name="reportForm" method="get"
				target="_self">
				<input type="hidden" name="title"
					value="<%if (html.getTitle() != null) {%><%=html.getTitle()%><%} %>" />
				<input type="hidden" name="url"
					value="<%if (url != null) {%><%=url%><%} %>" />
				<input type="submit" value="报告标题或内容错误" />
			</form>
			<%
				if (StringUtils.isNotEmpty(html.getTitle())
							&& StringUtils.isNotEmpty(html.getContent())) {
			%>
			<font color="blue"><strong>标题：</strong> </font><%=html.getTitle()%>
			<br />
			<br />
			<font color="blue"><strong>正文：</strong> </font><%=html.getContent()%>
			<%
				} else {
			%>
			网页不是主题类网页，或者解析错误！
			<%
				}
				}
			%>
		</div>
	</body>
</html>