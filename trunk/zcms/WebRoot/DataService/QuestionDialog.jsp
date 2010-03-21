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
<z:init method="com.zving.cms.dataservice.Question.initDialog">
<input type ="hidden" name="QuestionInnerCode" id ="QuestionInnerCode" value ="${QuestionInnerCode}">
<input type ="hidden" name="ID" id ="ID" value ="${ID}">
<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="448" height="10"></td>
			<td></td>
		</tr>
		<tr>
		  <td align="right">分类节点：</td>
		  <td width="581"><input disabled="disabled" name="QuestionName" type="text" class="input1" id="QuestionName" value="${QuestionName}" verify="标题|NotNull" /> </td>
		</tr>
		<tr>
		  <td align="right">标题：</td>
		  <td width="581"><input name="Title" type="text" class="input1" id="Title" value="${Title}" verify="标题|NotNull" /> </td>
		</tr>
		<tr>
			<td align="right">内容：</td>
		    <td width="581"><textarea rows="50" cols="40" id="Content">${Content}</textarea> </td>
		</tr>
		<tr>
			<td height="10" colspan="2" align="center"></td>
		</tr>
	</table>
	</z:init>
</form>
</body>
</html>
