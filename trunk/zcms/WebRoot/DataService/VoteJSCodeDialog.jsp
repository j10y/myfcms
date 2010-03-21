<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.Vote.JSCodeDialog">
	<form id="form1">
	<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
		<tr>
			<td><textarea id="JSCode" name="JSCode" cols="80" rows="4">${JSCode}</textarea>
			</td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
