<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
.tdgrey1 { background-color:#f9f9f9;}
</style>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.datachannel.DeployJob.initDialog">
	<table width="100%" border="1" cellpadding="4" cellspacing="0" bordercolor="#dddddd" style="margin:10px auto;">
		<tr>
			<td width="100" align="right" valign="top" class="tdgrey1"><strong>复制方式：</strong></td>
			<td class="tdgrey2">${MethodName}</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1"><strong>本地目录：</strong></td>
			<td class="tdgrey2">${source}</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1"><strong>服务器地址：</strong></td>
			<td class="tdgrey2">${host}</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1"><strong>目标目录：</strong></td>
			<td class="tdgrey2">${target}</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1" height=24><strong>状态：</strong></td>
			<td class="tdgrey2" valign="top">${statusname}</td>
		</tr>
	</table>
</z:init>
</body>
</html>
