<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自定义业务库预览</title>
<script src="../Framework/Main.js"></script>
<script>
</script>
</head>
<body scroll="no">
<z:init method="com.zving.platform.CustomColumn.initPreview">
	<div>
	<table width="100%" cellpadding="2" cellspacing="0" class="dataTable" id="table1">
		<tr>
			<td align="center" height="60"><font size="+2">${Title}</font></td>
		</tr>
	</table>
	<br>
	<br>
	<table width="60%" align="center" cellpadding="4" cellspacing="1" class="dataTable" id="table2">
	  	${CustomContent}
	</table>
	<br>
	<br>
	</div>
</body>
</html>
</z:init>