<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="492" height="10"></td>
			<td width="612"></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">级别名称:</td>
			<td>
            <input type="text" value="" id="Name" name="Name" class="inputText" verify="必须是合法的名称.|NotNull&&Regex=\S+">
            </td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">级别：</td>
			<td><input type="text" value="" id="TreeLevel" name="TreeLevel" class="inputText" verify="级别|NotNull&&Int"></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">默认积分：</td>
			<td><input type="text" value="" id="Score" name="Score" class="inputText" verify="积分|NotNull&&Int"></td>
		</tr>
	</table>
	</form>
</body>
</html>
