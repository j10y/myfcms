<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../Framework/Main.js"></script>
<script type="text/javascript">

</script>
</head>
<z:init method="com.zving.bbs.admin.ForumManageGroupOption.init">
<body>
<form id="form1" >
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<input type="hidden" value="${GroupID}" id="GroupID" >
				<tr>
					<td width="100" align="right">用户组头衔:</td>
					<td>${Name}</td>
				</tr> 
				<tr>
					<td align="right">允许编辑用户:</td>
					<td>${AllowEditUser}</td>
				</tr>
				<tr>
					<td align="right">允许禁止用户:</td>
					<td>${AllowForbidUser}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许编辑板块:</td>
					<td>${AllowEditForum}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许审核帖子:</td>
					<td>${AllowVerfyPost}</td>
				</tr>
								<tr>
					<td width="100" align="right">允许删除帖子:</td>
					<td>${AllowDel}</td>
				</tr>
				</tr>
								<tr>
					<td width="100" align="right">允许编辑帖子:</td>
					<td>${AllowEdit}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许屏蔽帖子:</td>
					<td>${Hide}</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>