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
<body >
<z:tab>
	<z:childtab id ="Info" src="CustomTableInfo.jsp" selected="true"><img src="../Icons/icon002a1.gif" width="20" height="20"/><b>基础信息</b></z:childtab>
	<z:childtab id ="Column" visible="false" src="CustomTableColumn.jsp"><img src="../Icons/icon018a7.gif" width="20" height="20"/><b>字段信息</b></z:childtab>
</z:tab>
</body>
</html>
