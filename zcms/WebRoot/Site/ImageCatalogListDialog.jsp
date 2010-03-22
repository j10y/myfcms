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
<z:init method="com.zving.cms.ImageLibMain.initImageCalalogListDialog">
	<body class="dialogBody">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="619" height="10"></td>
			<td></td>
		</tr>
		<tr id="tr_ID">
			<td align="right">所属分类:</td>
			<td width="762"><select id="CatalogID" name="CatalogID">
				${optionCatalogID}
			</select></td>
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
