<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="3" cellspacing="0">
	<tr>
		<td width="12" colspan="3" height="10"></td>
	</tr>
	<tr>
		<td width="12"></td>
		<td align="right">关键字：</td>
		<td><input name="Keyword" type="text" value="" style="width:100px" class="input1" id="Keyword" verify="关键字|NotNull" size=15 /> <input name="ID" type="hidden" id="ID" /></td>
	</tr>
	<tr>
		<td></td>
		<td align="right">链接地址：</td>
		<td><input name="LinkURL" type="text" value="" class="input1" id="LinkURL" size=36 /></td>
	</tr>
	<tr>
		<td></td>
		<td align="right">链接提示：</td>
		<td><input name="LinkAlt" type="text" class="input1" id="LinkAlt" value="" size="36" /></td>
	</tr>
	<tr>
		<td rowspan="2"></td>
		<td height="12" align="right">打开方式：</td>
		<td height="12">
		<z:select name="LinkTarget" id="LinkTarget"><span value="_self" selected>原窗口</span> <span value="_blank">新窗口</span> <span value="_parent">父窗口</span></z:select>
		</td>
	</tr>
	<tr>
		<td height="5" align="right"></td>
		<td height="5"></td>
	</tr>
</table>
</form>
</body>
</html>
