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
<body>
<input type="hidden" name="AritcleID" id="ArticleID"
	value="<%=request.getParameter("ArticleID")%>">
<div style="padding:5px;"><z:datagrid id="dg1"
	method="com.zving.cms.document.ArticleNote.logDataBind" size="12">
	<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
		<tr ztype="head" class="dataTableHead">
			<td width="4%" height="28">&nbsp;</td>
			<td width="16%""><b>操作</b></td>
			<td width="41%""><b>操作明细</b></td>
			<td width="19%"><strong>操作人</strong></td>
			<td width="22%"><strong>操作时间</strong></td>
		</tr>
		<tr style1="background-color:#FFFFFF"
			style2="background-color:#F9FBFC">
			<td>&nbsp;</td>
			<td>${Action}</td>
			<td>${ActionDetail}</td>
			<td>${addUser}</td>
			<td>${addTime}</td>
		</tr>

		<tr ztype="SimplePageBar">
			<td colspan="5" align="left">
			<div align="center">${PageBar}</div>
			</td>
		</tr>
	</table>
</z:datagrid></div>
</body>
</html>
