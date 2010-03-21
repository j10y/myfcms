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
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td width="619" height="10"></td>
		<td></td>
	</tr>
	<tr id="tr_ID">
		<td align="right">所属分类:</td>
		<td width="762"><z:select id="CatalogID" listWidth="200"
			listHeight="300" listURL="Resource/AudioLibSelectList.jsp"></z:select>
		</td>
	</tr>
</table>
</form>
</body>
</html>
