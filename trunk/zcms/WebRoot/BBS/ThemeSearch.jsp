<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zving BBS</title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function doSearch(){
	var SearchAddUser = "";
	if ($V("SearchAddUser") != "请输入相关信息") {
		SearchAddUser = $V("SearchAddUser").trim();
	}
	var SearchSubject = "";
	if($V("SearchSubject") != "请输入相关信息"){
		SearchSubject = $V("SearchSubject").trim();
	}
	if(SearchSubject =="" && SearchAddUser==""){
		Dialog.alert("请输入关键字或者用户名");
		return;
	}

	window.location = "./ThemeSearchResult.jsp?SiteID="+$V("SiteID")+"&SearchAddUser="+SearchAddUser+"&SearchSubject="+SearchSubject;
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchAddUser'||ele.id == 'SearchSubject'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

</script>

</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div class="wrap">
<z:init method="com.zving.bbs.ThemeSearch.init">
<input id="SiteID"  type="hidden" value="${SiteID}">
<div id="menu" class="forumbox"><span class="fl"><a href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; 搜索 </span> <span class="fr">${Priv}</span></div>

<div id="previewtable" style="display: none">
<h1>预览帖子</h1>
<table cellspacing="0" cellpadding="0">
	<tr>
		<td>${AddUser}</td>
		<td>
		<div id="previewmessage">
		<h2></h2>
		</div>
		</td>
	</tr>
</table>
</div>
</z:init>
<form method="post">
	<div class="forumbox" style="margin:50px auto; width:600px;">
	<h4>搜索</h4>
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"
		bordercolor="#eeeeee" class="forumTable" style="table-layout: auto; margin:20px 0;">
		<tr>
			<th align="right" style="text-align:right"><label for="SearchSubject">关键字</label></th>
			<td><input type="text" name="SearchSubject" id="SearchSubject" size="30"
				tabindex="3" ></td>
			<td>关键字中可使用通配符  可根据发表主题人的标题进行模糊查询</td>  
		</tr>
		<tr>
			<th align="right" style="text-align:right"><label for="SearchAddUser">用户名</label></th>
			<td><input type="text" name="SearchAddUser" id="SearchAddUser" size="30"
				tabindex="3" ></td>
			<td>用户名中可使用通配符 "*"，如 *user*</td>  
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td colspan="6" align="middle"><input name="Submitbutton" id="Submitbutton"
				type="button" value="搜索" onClick="doSearch()" > &nbsp;&nbsp; &nbsp;</td>
		</tr>
	</table>
	</div>
</form>

</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
