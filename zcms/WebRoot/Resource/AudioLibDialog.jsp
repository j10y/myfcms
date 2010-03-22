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
<z:init>
	<z:tab>
		<z:childtab id="AudioUpload"
			src="AudioUpload.jsp?CatalogID=${CatalogID}" selected="true">
			<img src="../Icons/icon051a2.gif" />
			<b>上传音频</b>
		</z:childtab>
		<z:childtab id="AudioBrowse" src="AudioBrowse.jsp">
			<img src="../Icons/icon051a10.gif" />
			<b>音频浏览</b>
		</z:childtab>
	</z:tab>
</z:init>
</body>
</html>
