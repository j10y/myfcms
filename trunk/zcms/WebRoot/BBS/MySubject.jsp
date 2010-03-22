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
<script>
	function orderBy(){
		var dc = Form.getData($F("form3"));
		window.location ="./MySubject.jsp?ForumID="+$V("ForumID")+"&addtime="+dc.get("time")+"&orderby="+dc.get("orderby")+"&ascdesc="+dc.get("ascdesc");
	}
	Page.onLoad(function(){
		var addtime = $V("addtime");
		var orderBy = $V("order");
		var ascdesc = $V("desc");
		if(addtime!="null" && addtime.length>0 && orderBy!="null" && orderBy.length>0 && ascdesc!="null" && ascdesc.length>0){
			$("time").value = "" + addtime;
			$("orderby").value = ""+orderBy;
			$("ascdesc").value = "" +ascdesc;
		}
	});

</script>
</head>
<body>
<div class="wrap">
<z:init method="com.zving.oa.commonality.MySubject.init">
<input id="ForumID"  type="hidden" value="${ID}">
<div id="menu" class="forumbox"><span class="fl"> <p><a id="forumlist" href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; 我的话题</p> </span> <span class="fr"> <a href="ThemeSearch.jsp">搜索</a> | <a href="ControlPanel.jsp">控制面板</a><!--a href="tag.jsp">标签</a--> | <a href="MySubject.jsp?AddUser=${AddUser}">我的话题</a> | <!-- | <a target="_blank" href="modcp.jsp?fid=0">版主管理面板</a> | <a href="faq.jsp">帮助</a-->  <a href="MyPost.jsp?AddUser=${AddUser}">我参与的话题</a> </span></div>
<div id="foruminfo">
	<div id="nav">
	</div></div>
<div class="forumbox">
<h4><a >ZvingCMS</a></h4>
<form method="post" name="moderate" action="topicadmin.jsp?action=moderate&amp;fid=5">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
	<thead>
		<tr>
			<th width="36">&nbsp;</th>
			<th>标题</th>
			<td width="150">作者</td>
			<td width="60">回复/查看</td>
		</tr>
	</thead>
	<z:datalist id="list1" method="com.zving.oa.commonality.MySubject.getList" page="true" size="12">
		<tr>
			<td>[原创]</td>
			<td valign="middle"><a href="./Post.jsp?ThemeID=${ID}&ForumID=${ForumID}&User=current" ><b>${Subject}</b></a></td>
			<td><cite>${AddUser}</cite> <br><em>${Addtime}</em></td>
			<td><strong>${ReplyCount}/${ViewCount}</strong></td>
		</tr>
	</z:datalist>
		<tr>
			<td colspan="3"><z:pagebar target="list1" /></td>
		</tr>

</table>
</form>
</div>
<div style="padding:5px;">
<div><span id="newspecial" class="fr"> <a href="MyPost.jsp?AddUser=${AddUser}">我参与的话题</a> | <a href="ThemeAdd.jsp?ForumID=<%=request.getParameter("ForumID")%>">发表新话题</a> </span></div>
</div>

<input type="hidden" value="<%=request.getParameter("addtime")%>" id="addtime">
<input type="hidden" value="<%=request.getParameter("orderby")%>" id="order">
<input type="hidden" value="<%=request.getParameter("ascdesc")%>" id="desc">
<div id="footfilter">
<form method="get"  id="form3"><input type="hidden" name="fid" value="5"> 查看 <select name="filter" id="time">
	<option value="0">全部主题</option>
	<option value="86400000">1 天以来主题</option>
	<option value="172800000">2 天以来主题</option>
	<option value="604800000">1 周以来主题</option>
	<option value="2592000000">1 个月以来主题</option>
	<option value="7948800000">3 个月以来主题</option>
	<option value="15897600000">6 个月以来主题</option>
	<option value="31536000000">1 年以来主题</option>
</select> 排序方式 <select name="orderby" id="orderby">
	<option value="LastPostTime" >回复时间</option>
	<option value="AddTime">发布时间</option>
	<option value="ReplyCount">回复数量</option>
	<option value="ViewCount">浏览次数</option>
</select> <select name="ascdesc" id="ascdesc">
	<option value="DESC"  >按降序排列</option>
	<option value="ASC">按升序排列</option>
</select> &nbsp;
<button type="button" onclick="orderBy()">提交</button>
</form>
</div>
</z:init></div>
</body>
</html>
