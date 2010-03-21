<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>

<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
</style>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.document.Weekwork.initDialog">
	<form id="form2" method="post">
	<input name="ID" type="hidden" id="ID" value="${ID}" />
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="100"></td>
			<td width="10" height="10"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">日期：</td>
			<td>&nbsp;</td>
			<td>${Wdate}</td>			  
		</tr>
		<tr>
			<td height="39" align="right">内容：</td>
		  <td>&nbsp;</td>
			<td><textarea name="Content" id="Content" cols="50" >${Content}</textarea></td>
		</tr>
		<tr>
			<td height="51" align="right">参加人员：</td>
		  <td>&nbsp;</td>
			<td><textarea name="Partner" id="Partner" cols="50">${Partner}</textarea></td>
		</tr>
		<tr>
			<td height="56" align="right">地点：</td>
	    <td>&nbsp;</td>
			<td><textarea name="Workplace" id="Workplace" cols="50" >${Workplace}</textarea></td>
		</tr>
			
	</table>
	</form>
</z:init>
</body>
</html>
