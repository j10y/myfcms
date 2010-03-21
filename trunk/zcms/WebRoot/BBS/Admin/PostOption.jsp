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
<z:init method="com.zving.bbs.admin.ForumOption.postOptionInit">
<body>
<form id="form2" >
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td width="100" align="right">发帖审核:</td><br/>
					<td>${Verify}</td>
				</tr>
				<tr>
					<td align="right">允许发帖:</td><br/>
					<td>${AllowTheme}</td>
				</tr>
				<tr>
					<td align="right">允许回复帖子</td>
					<td>${ReplyPost}</td>
				</tr>
				<tr>
					<td align="right">编辑个人帖子</td>
					<td>${EditPost}</td>
				</tr>
				<tr>
					<td align="right">主题回收站</td>
					<td>${Recycle}</td>
				</tr>
				<tr>
					<td align="right">允许使用表情</td>
					<td>${AllowFace}</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>