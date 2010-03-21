<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.ImageLibMain.initImageCalalogDialog">
	<body class="dialogBody">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="112" height="10"></td>
			<td></td>
		</tr>
		<tr id="tr_ID" style="display:none">
			<td align="right">栏目ID：</td>
			<td width="833"><input name="ID" type="text" class="input1"
				id="ID" value="${ID}" size="30" /></td>
		</tr>
		<tr id="tr_ParentID" style="display:none">
			<td align="right">父级栏目ID：</td>
			<td><input name="ParentID" type="text" class="input1"
				id="ParentID" value="${ParentID}" size="30" /></td>
		</tr>
		<tr>
			<td align="right">栏目名称：</td>
			<td><input name="Name" type="text" class="input1" id="Name"
				value="${Name}" size="30" /></td>
		</tr>
		<tr>
			<td height="35" colspan="2" align="center"><input name="button"
				type="button" class="button2" id="button" onClick="addsave();"
				value=" 确  定 " /> &nbsp; <input name="button2" type="button"
				class="button2" onClick="window.close();" value=" 取  消 " /></td>
		</tr>
	</table>
	</form>
	</body>
</html>
</z:init>
