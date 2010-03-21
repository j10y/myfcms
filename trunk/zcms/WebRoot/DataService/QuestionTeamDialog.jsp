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
<form id="form1">
<z:init method="com.zving.cms.dataservice.QuestionTeam.initDialog">
<input type ="hidden" name="ParentInnerCode" id ="ParentInnerCode" value ="${ParentInnerCode}">
<input type ="hidden" name="InnerCode" id ="InnerCode" value ="${InnerCode}">
<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="448" height="10"></td>
			<td></td>
		</tr>
		<tr>
		  <td align="right">上级节点：</td>
		  <td width="581"><input disabled="disabled" name="ParentName" type="text" class="input1" id="ParentName" value="${ParentName}" verify="标题|NotNull" /> </td>
		</tr>
		<tr>
		  <td align="right">节点名称：</td>
		  <td width="581"><input name="Name" type="text" class="input1" id="Name" value="${Name}" verify="节点名称|NotNull" /> </td>
		</tr>
		<tr>
			<td height="10" colspan="2" align="center"></td>
		</tr>
	</table>
	</z:init>
</form>
</body>
</html>
