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
<z:init method="com.zving.bbs.admin.ForumManageGroupOption.initThemeOption">
<body>
<form id="form2" >
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				
				<tr>
					<td align="right">删除主题:</td>
					<td>${RemoveTheme}</td>
				</tr>
				<tr>
					<td align="right">移动主题:</td>
					<td>${MoveTheme}</td>
				</tr>
				<tr>
					<td width="100" align="right">置顶主题:</td>
					<td>${TopTheme}</td>
				</tr>
				<tr>
					<td width="100" align="right">高亮显示:</td>
					<td>${BrightTheme}</td>
				</tr>
				<tr>
					<td width="100" align="right">提升/下沉主题:</td>
					<td>${UpOrDownTheme}</td>
				</tr>
				<tr>
					<td width="100" align="right">精华主题:</td>
					<td>${BestTheme}</td>
				</tr>

				
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>