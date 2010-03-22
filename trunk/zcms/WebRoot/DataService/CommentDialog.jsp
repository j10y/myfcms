<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
</script>
</head>
<body class="dialogBody">
<form id="form2">
<table width="453" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
  <tr>
    <td width="72" height="12" class="tdgrey2" ></td>
    <td  class="tdgrey2" ></td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1" >文档标题：</td>
    <td width="325" class="tdgrey2"><input name="ArticleTitle" id="ArticleTitle" type="text" size="60"/></td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1">评论内容：</td>
    <td class="tdgrey2"><textarea id="Content" name="Content" cols="57" rows="5"></textarea></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input name="button2" type="button" onClick="window.close();" value="关 闭"/>    </td></tr>
</table>
<input name="ID" id="ID" type="hidden"/>
</form>
</body>
</html>