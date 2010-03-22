<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../../Include/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%String path = request.getParameter("path");
  path=path.replaceAll("///","/");
  path=path.replaceAll("//","/");
%>
<title>下载</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="3" cellspacing="0">
	<tr>
		<td width="986" align="center">
		<p align="center">&nbsp;</p>
		<p>文件压缩完毕，请下载：</p>
		<div style="text-align:center;">
		<p>&nbsp;</p>
		<p>下载地址:<a href="<%=path%>">点击下载</a></p>
		</div>
	</tr>
	<tr>
		<td width="62" height="5" align="right"></td>
		<td width="8" height="5"></td>
	</tr>
</table>
</form>
</body>
</html>
