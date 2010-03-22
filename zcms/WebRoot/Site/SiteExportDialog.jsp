<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body>
<table width="90%" align="center" cellpadding="2"
	cellspacing="0">
	<tr>
	  <td height="15" align="left">&nbsp;</td>
  </tr>
	<tr>
	  <td height="30" align="left">确认导出站点 &nbsp;<span id="Name" style="color:#FF0000"></span>&nbsp;的数据和文件？</td>
  </tr>
	<tr>
	  <td height="30" align="left"><input name="ExportMediaFile" type="checkbox" id="ExportMediaFile" value="Y" checked>
      <label for="ExportMediaFile">导出所有上传的文件</label></td>
  </tr>
	<tr>
		<td height="30" align="left">注意：该站点的回收站数据不会被导出。</td>
	</tr>
</table>
</body>
</html>
