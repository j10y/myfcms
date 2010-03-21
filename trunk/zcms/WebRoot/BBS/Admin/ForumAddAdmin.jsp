<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../Framework/Main.js"></script>
<script src="../../Framework/Spell.js"></script>
</head>
<z:init method="com.zving.bbs.admin.ForumAdmin.initAddDialog">
<body>
<form id="form1">
	<table width="100%" border="0" cellspacing="6" cellpadding="0" >
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" >
				<tr>
					<td align="left">板块名称:</td>
					<td align="left"><input type="text" name="Name" id="Name" verify="NotNull" style="width: 100"/></td>
				</tr>
				<tr>
					<td align="left">上级分区:</td>
					<td><z:select name="ParentID" id="ParentID"
						style="width:100px;"><span
						value="0"></span> ${ParentForum}</z:select>
					</td>
				</tr>
				<tr>
					<td align="left">图片选择</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="left">板块介绍:</td>
					<td align="left"><textarea name="Info" id="Info" cols="25" width="5"/></textarea></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>