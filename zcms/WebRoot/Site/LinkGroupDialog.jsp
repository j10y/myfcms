<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.site.LinkGroup.initDialog">
	<body>
	<form id="form2"><input name="ID" type="hidden" id="ID" />
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="28%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">链接分类名称：</td>
			<td width="72%"><input name="Name" type="text" class="input1"
				id="Name" value="${Name}" verify="链接分类名称|NotNull"
				style="width:200px" /></td>
		</tr>
		<tr>
			<td align="right">类型：</td>
			<td>${Type}</td>
		</tr>
	</table>
	</form>
	</body>
</z:init>
</html>
