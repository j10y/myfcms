<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ZVING</title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div class="wrap">
<z:init method="com.zving.bbs.ThemeSearch.init">
<input id="SiteID"  type="hidden" value="${SiteID}">
<div id="menu" class="forumbox"><span class="fl"><a id="forumlist" href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; 搜索 </span> <span class="fr"> ${Priv}</span></div>
<div id="foruminfo">
</div>
<div class="mainbox forumbox">
<h4><a >ZvingCMS</a></h4>
<form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
	<thead class="category">
		<tr>
			<th width="36">&nbsp;</th>
			<th>标题</th>
			<th width="150">作者</th>
			<th width="60">回复/查看</th>
		</tr>
	</thead>
	<z:datalist id="list1" method="com.zving.bbs.ThemeSearch.dg1DataBind" page="true" size="12">
		<tr>
			<td>[原创]</td>
			<td valign="middle"><a href="Post.jsp?ThemeID=${ID}&ForumID=${ForumID}&SiteID=${SiteID}"><b>${Subject}</b></a></td>
			<td><cite>${AddUser}</cite><br> <em>${Addtime}</em></td>
			<td><strong>${ReplyCount}/${ViewCount}</strong></td>
		</tr>
	</z:datalist>
	<tr><td colspan="4"><z:pagebar target="list1"></z:pagebar></td></tr>  
</table>
</form>
</div>
</z:init>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
