<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2">
<table cellpadding="2" cellspacing="0">
    <tr>
      <td width="116" height="10" align="right" ></td>
      <td></td>
    </tr>
    <tr>
      <td align="right" >工作流名称：</td>
      <td><input  type="text" value="" class="input1" id="Name" verify="工作流名称|NotNull" /></td>
    </tr>
    <tr>
      <td align="right" >描述：</td>
      <td><textarea id="Memo" cols=40></textarea></td>
    </tr>
</table>
</form>
</body>
</html>