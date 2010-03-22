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
<z:init method="com.zving.bbs.admin.ForumUserGroupOption.initSpecailOption">
<body>
<form id="form1" >
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<input type="hidden" value="${ID}" id="ID" >
				<tr>
					<td width="100" align="right">用户组头衔:</td>
					<td><input type="text" id="Name" value="${Name}" varify="notnull"></td>
				</tr>
				<tr>
					<td width="100" align="right">关联管理权:</td>
					<td><z:select id="RadminID"><span value='0'>无</span>${AdminGroup}</z:select></td>
				</tr>
				<tr>
					<td align="right">允许访问论坛:</td>
					<td>${AllowVisit}</td>
				</tr>
				<tr>
					<td align="right">允许搜索:</td>
					<td>${AllowSearch}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许自定义头像:</td>
					<td>${AllowHeadImage}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许查看用户信息:</td>
					<td>${AllowUserInfo}</td>
				</tr>
				<tr>
					<td width="100" align="right">允许使用昵称:</td>
					<td>${AllowNickName}</td>
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>