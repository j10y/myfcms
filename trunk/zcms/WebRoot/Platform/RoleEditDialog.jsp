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
<z:init method="com.zving.platform.RoleTabBasic.initEditDialog">
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td width="448" height="10" ></td>
      <td></td>
    </tr>
    <tr>
      <td align="right" >角色名称：</td>
      <td width="581">
	  <input name="RoleName"  type="text"  class="input1" id="RoleName" value="${RoleName}" verify="角色名称|NotNull" />
	  <input type="hidden" id="RoleCode" value="${RoleCode}" /></td>
    </tr>
    <tr>
      <td align="right" >备注：</td>
      <td><input name="Memo" type="text"  class="input1" id="Memo" value="${Memo}"/></td>
    </tr>
    <tr>
      <td height="10" colspan="2" align="center"></td>
    </tr>
</table>
</form>
</body>
</html>
</z:init>