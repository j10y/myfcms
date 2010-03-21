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
<%	String catalogID =  request.getParameter("CatalogID");
	String catalogName = CatalogUtil.getName(catalogID);
	%>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td height="10"><input id="CatalogID" type="hidden"
			value="<%=catalogID%>" /></td>
	</tr>
	<tr>
		<td align="center"><b style="color:#F00"><%=catalogName%></b> 移动<input
			id="Move" type="text" size="5" value="1" verify="Int" />位</td>
	</tr>
	<tr>
		<td align="center">填<b>正数</b>表示向<b style="color:#FF6600;">上</b>移动，<b>负数</b>表示向<b
			style="color:#FF6600;">下</b>移动</td>
	</tr>
</table>
</form>
</body>
</html>
