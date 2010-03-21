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
<z:init method="com.zving.cms.document.Message.initReplyDialog">
	<body class="dialogBody">
	<form id="formMsg">
	<table width="100%" border="0" cellpadding="4" cellspacing=""
		bordercolor="#DEDEDC" style="border-collapse:collapse;">
		<tr>
			<td width="72" height="12" class="tdgrey2"></td>
			<td class="tdgrey2"></td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1">接收人：</td>
			<td width="325" class="tdgrey2">&nbsp;<b>${FromUser}</b>&nbsp;<input
				name="ToUser" id="ToUser" type="hidden" value="${FromUser}" /></td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1">标题：</td>
			<td width="325" class="tdgrey2"><input name="Subject"
				id="Subject" type="text" size="49" value="RE:${Subject}"
				verify="消息标题|NotNull" /></td>
		</tr>
		<tr>
			<td align="right" valign="top" class="tdgrey1">内容：</td>
			<td class="tdgrey2"><textarea id="Content" name="Content"
				cols="50" rows="5" verify="消息内容|NotNull" /></textarea></td>
		</tr>
		<tr></tr>
	</table>
	</form>
	</body>
</z:init>
</html>
