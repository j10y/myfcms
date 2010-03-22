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
<table width="346" height="100" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
 	<tr>
      <td width="21%" height="10"></td>
      <td></td>
    </tr>
    <tr>
      <td width="21%" height="31"  align="right" valign="top">字段名称：</td>
      <td width="79%" valign="top"><input name="Name" id="Name" type="text" size="20" verify="字段名字|NotNull" />
      </td>
    </tr>
    <tr>
      <td height="31" align="right" valign="top">字段代码：</td>
      <td valign="top"><input name="Code" id="Code" type="text" size="12"  verify="字段名称|NotNull" />
      </td>
    </tr>
	<tr>
      <td height="29"  align="right" valign="top">字段类型：</td>
      <td valign="top" ><z:select name="Type" id="Type">
        <span value="1">int-整型</span>
        <span value="2">long-长整型</span>
        <span value="3" selected>string-字符串</span>
        <span value="4">float-浮点</span>
        <span value="5">double-双字节</span>
		<span value="6">date-日期</span>
		<span value="7">text-文本</span>
      </z:select>
      </td>
    </tr>
	<tr>
      <td align="right" valign="top">长度：</td>
      <td valign="top"><input name="Length" id="Length" type="text" size="12" /> 
      </td>
    </tr>
	<tr>
      <td height="29"  align="right" valign="top">表现形式：</td>
      <td valign="top" ><z:select name="ShowMod" id="ShowMod">
        <span value="1" selected>单行文本</span>
        <span value="2">多行文本</span>
        <span value="3">下拉列表框</span>
        <span value="4">单选框</span>
        <span value="5">多选框</span>
        <span value="6">媒体库图片框</span>
        <span value="7">附件框</span>
      </z:select>
      </td>
    </tr>
	<tr>
      <td   align="right" valign="top">默认值：</td>
      <td valign="top" ><input name="DefaultValue" id="DefaultValue" type="text" size="20" /></td>
    </tr>
	<tr>
      <td height="83"  align="right" valign="top">列表选项：<br>
        <br>
          <font color="red">(每行为一<br>
      个列表项)</font></td>
      <td valign="top" ><textarea id="ListOpt" name="ListOpt" cols="43" rows="5"></textarea></td>
    </tr>
	<tr>
      <td height="33"  align="right" valign="top">是否非空：</td>
      <td valign="top" ><label for="MandatoryFlag"><input name="MandatoryFlag" type="checkbox" id="MandatoryFlag" checked />不为空</label></td>
    </tr>
</table>
<input name="ID" id="ID" type="hidden"/>
</form>
</body>
</html>