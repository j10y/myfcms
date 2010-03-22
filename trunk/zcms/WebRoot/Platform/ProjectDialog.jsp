<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
      <td width="40%" height="10" align="right" ></td>
      <td height="10"></td>
    </tr>
    <tr>
      <td align="right" >项目名称：</td>
      <td width="60%"><input name="Name"  type="text" class="input1" id="Name" size=20 verify="项目名称|NotNull"/></td>
    </tr>
    <tr>
      <td align="right" >项目开始时间：</td>
      <td><input name="BeginTime" ztype="date" type="text" class="input1" id="BeginTime" size=20 verify="项目开始时间|NotNull"/></td>
    </tr>
    <tr>
      <td align="right" >项目结束时间：</td>
      <td><input name="EndTime" ztype="date" type="text" class="input1" id="EndTime" size=20 verify="项目结束时间|NotNull"/></td>
    </tr>
    <tr>
      <td align="right" >备注：</td>
      <td><input name="Prop1" type="text"  class="input1" id="Prop1" size=20/></td>
    </tr>
</table>
</form>
</body>
</html>