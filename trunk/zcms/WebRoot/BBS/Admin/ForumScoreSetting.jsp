<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ZVING</title>
<script src="../../Framework/Main.js"></script>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="../forum.css" rel="stylesheet" type="text/css">
<script>
	function save(){
		var dc = Form.getData($F("form1"));
		Server.sendRequest("com.zving.bbs.admin.ForumScoreSetting.save",dc,function(response){
			Dialog.alert(response.Message);
				if(response.Status==1){
					DataList.loadData('list1');
				}
		});
	}
</script>
</head>
<body>

<div class="wrap">
<input id="ForumID"  type="hidden" value="${ID}">
<div id="foruminfo">
	<div id="nav">
		<z:tbutton onClick="save()">
			<img src="../../Icons/icon022a16.gif" width="20" height="20" />保存
		</z:tbutton>
	</div>
</div>
<div class="forumbox">

<form id="form1" method="post" name="moderate">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
	<!-- 表头 -->
	<thead>
		<tr>
			<td width="50">初始积分</td>
			<td width="50">发主题</td>
			<td width="50">删主题</td>
			<td width="50">发回复</td>
			<td width="50">删回复</td>
			<td width="50">加精华</td>
			<td width="50">取消精华</td>
			<td width="50">设高亮</td>
			<!--td width="50">取消高亮</td-->
			<td width="50">设顶置</td>
			<td width="50">取消顶置</td>
			<td width="50">提升主题</td>
			<td width="50">下沉主题</td>
			<!--td width="50">上传附件</td>
			<td width="50">下载附件</td-->
			<!--td width="50">查找</td-->
			<!--td width="50">投票</td-->
		</tr>
	</thead>
	<!--从要循环的数据行开始插入datalist标签 -->
	<z:init method="com.zving.bbs.admin.ForumScoreSetting.init">
		<tr nowrap="nowrap">
			<td><input size="7" type="text" value="${InitScore}" id="InitScore" /></td>
			<td><input size="7" type="text" value="${PublishTheme}" id="PublishTheme" /></td>
			<td><input size="7" type="text" value="${DeleteTheme}" id="DeleteTheme" /></td>
			<td><input size="7" type="text" value="${PublishPost}" id="PublishPost" /></td>
			<td><input size="7" type="text" value="${DeletePost}" id="DeletePost" /></td>
			<td><input size="7" type="text" value="${Best}" id="Best" /></td>
			<td><input size="7" type="text" value="${CancelBest}" id="CancelBest" /></td>
			<td><input size="7" type="text" value="${Bright}" id="Bright" /></td>
			<!--td><input size="7" type="text" value="${CancelBright}" id="CancelBright" /></td-->
			<td><input size="7" type="text" value="${TopTheme}" id="TopTheme" /></td>
			<td><input size="7" type="text" value="${CancelTop}" id="CancelTop" /></td>
			<td><input size="7" type="text" value="${UpTheme}" id="UpTheme" /></td>
			<td><input size="7" type="text" value="${DownTheme}" id="DownTheme" /></td>
			<!--td><input size="7" type="text" value="${Upload}" id="Upload" /></td>
			<td><input size="7" type="text" value="${Download}" id="Download" /></td-->
			<!--td><input size="7" type="text" value="${Search}" id="Search" /></td-->
			<!--td><input size="7" type="text" value="${Vote}" id="Vote" /></td-->
		</tr>
	</z:init>
	
</table>
</form>
</div>

</body>
</html>
