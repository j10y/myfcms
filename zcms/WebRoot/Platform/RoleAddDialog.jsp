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
<z:init method="com.zving.platform.RoleTabBasic.initDialog">
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td width="613" height="10" ></td>
      <td></td>
    </tr>
    <tr>
      <td align="right" >角色代码：</td>
      <td width="802"><input name="RoleCode"  type="text" class="input1" id="RoleCode" value="${RoleCode}" verify="角色代码|NotNull" /></td>
    </tr>
    <tr>
      <td align="right" >角色名称：</td>
      <td><input name="RoleName"  type="text"  class="input1" id="RoleName" verify="角色名称|NotNull" /></td>
    </tr>
    <tr>
      <td align="right" >所属机构：</td>
      <td><z:select id="BranchInnerCode" > ${BranchInnerCode}</z:select></td>
    </tr>
    <tr>
      <td align="right" >备注：</td>
      <td><input name="Memo" type="text"  class="input1" id="Memo"/></td>
    </tr>
    <tr>
      <td height="10" colspan="2" align="center"></td>
    </tr>
</table>
</form>
</body>
</html>
</z:init>
