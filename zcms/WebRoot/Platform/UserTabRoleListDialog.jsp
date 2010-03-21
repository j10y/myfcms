<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body   oncontextmenu = "return false;">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td><img src="../Icons/icon018a1.gif" />角色列表
    </tr>
    <tr>
      <td height="300" colspan="2" align="center" ><div style="text-align:left;">
      <z:tree id="tree1" style="height:350px;width:280px" method="com.zving.platform.UserList.bindRoleTree">
		  	<p cid='${RoleCode}' ><input type="checkbox" name="RoleCode" value='${RoleCode}'>${RoleCode}(${RoleName})</p>
	  </z:tree>
	  </div>
	  </td>
    </tr>
</table>
</body>
</html>
