<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../Framework/Main.js"></script>
<script type="text/javascript">

</script>
</head>
<z:init method="com.zving.bbs.admin.ForumOption.basicInit">
<body>
<form id="form1" >
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<input type="hidden" value="${ID}" id="ID" >
				<tr>
					<td width="100" align="right">板块名称:</td>
					<td><input type="text" id="Name" value="${Name}"></td>
				</tr>
				<tr>
					<td align="right">上级分区:</td>
					<td><z:select name="ParentID" id="ParentID"
						style="width:100px;">${ParentForum}</z:select>
					</td>
				</tr>
				<tr>
					<td align="right">显示板块:</td>
					<td>${Visible}</td>
				</tr>
				<tr>
					<td align="right">锁定板块:</td>
					<td>${Locked}</td>
				</tr>
				<tr>
					<td width="100" align="right">访问密码:</td>
					<td><input type="password" id="Password"  name="Password" value="${Password}">
				</tr>
				<tr>
					<td width="100" align="right">URL:</td>
					<td><input type="text" name="URL" id="URL" value="${URL}">
				</tr>
				<tr>
					<td width="100" align="right">图片地址:</td>
					<td><input type="text" id="Image" value="${Image}">
				</tr>
				<tr>
					<td width="100" align="right">关键字:</td>
					<td><input type="text" id="Work" value="${Word}">
				</tr>
				<tr>
					<td width="100" align="right">板块简介:</td>
					<td align="left"><textarea name="Memo" style="height:80px; width:200px;"
							id="Memo"/>${Info}</textarea></td>
				</tr>
			</table>
			</td>
			
		</tr>
	</table>
</form>
</body>
</z:init>
</html>