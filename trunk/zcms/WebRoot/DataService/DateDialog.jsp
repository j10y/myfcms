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
<body class="dialogBody">
	<form id="form2">
	<table width="378" cellpadding="2" cellspacing="0">
		<tr>
			<td height="10">&nbsp;</td>
			<td></td>
		</tr>
		<tr>
			<td align="right">ID：</td>
			<td height="30"><input id="ID" name="ID" type="text"
				 class="input1" verify="ID|NotNull" size=30 /></td>
		</tr>
		<tr>
			<td align="right">名称：</td>
			<td height="30"><input id="Name" name="Name" type="text"
				 class="input1" verify="名称|NotNull" size=30 /></td>
		</tr>
					<tr>
				<td align="right">数据类型：</td>
				<td >
				<z:select id="DataType" style="width:150px;">
										<span value="string" selected>文本型</span>
										<span value="datetime" >日期</span>
				</z:select>
				</td>
			</tr>
	</table>
	</form>
</body>
</html>
