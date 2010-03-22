<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/Tabpage.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init>
	<z:tab>
		<z:childtab id="Basic" src="PageBlockBasic.jsp?ID=${ID}"
			selected="true">
			<img src="../Icons/icon002a1.gif" width="20" height="20" />
			<b>基础信息</b>
		</z:childtab>
		<z:childtab id="Content" src="PageBlockContent.jsp?ID=${ID}">
			<img src="../Icons/icon002a7.gif" width="20" height="20" />
			<b>区块内容</b>
		</z:childtab>
		<z:childtab id="List" src="PageBlockList.jsp?ID=${ID}">
			<img src="../Icons/icon002a5.gif" width="20" height="20" />
			<b>自选列表</b>
		</z:childtab>
	</z:tab>
</z:init>
</body>
</html>
