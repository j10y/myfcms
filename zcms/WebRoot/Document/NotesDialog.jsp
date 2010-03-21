<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<z:init method="com.zving.cms.document.Notes.initDialog">
	<body>
	<form id="form2">
	<table width="100%" height="100%" border="0" cellpadding="2"
		cellspacing="0" bordercolor="#DEDEDC"
		style="border-collapse:collapse;">
		<tr>
			<td height="15"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">主题：</td>
			<td><input name="Title" id="Title" type="text" size="60"
				value="${Title}" verify="NotNull" /></td>
		</tr>
		<tr>
			<td align="right">描述：</td>
			<td><textarea id="Content" name="Content" cols="60"
				style="height:90">${Content}</textarea></td>
		</tr>
	</table>
	<input name="ID" id="ID" type="hidden" /> <input name="DateOfMonth"
		id="DateOfMonth" type="hidden" /></form>
	</body>
</z:init>
</html>
