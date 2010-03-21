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
<script>
</script>
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
	<tr>
		<td width="72" height="12" class="tdgrey2" ></td>
		<td  class="tdgrey2" ></td>
	</tr>
    <tr>
      <td  align="right" valign="top" class="tdgrey1" >业务库标题：</td>
      <td width="259" class="tdgrey2"><input name="ClassName" id="ClassName" type="text" size="20" verify="业务库标题|NotNull"/> 
      </td>
    </tr>
    <tr>
      <td  align="right" valign="top" class="tdgrey1" >业务库名字：</td>
      <td class="tdgrey2"><input name="ClassCode" id="ClassCode" type="text" size="20" verify="业务库名字|NotNull"/></td>
    </tr>
    <tr>
      <td  align="right" valign="top" class="tdgrey1">备注：</td>
      <td class="tdgrey2"><textarea id="Memo" name="Memo" cols="40" rows="3"></textarea></td>
    </tr>
</table>
<input name="ID" id="ID" type="hidden"/>
</form>
</body>
</html>