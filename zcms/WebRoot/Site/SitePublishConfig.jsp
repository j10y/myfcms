<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	
});
</script>
</head>
<body scroll="no">

<div>
<table width="100%" cellpadding="4">
	<tr>
		<td><z:tbutton onClick="add();">
			<img src="../Platform/Images/tab1_tri.gif" />保存</z:tbutton></td>
	</tr>
</table>
</div>

<input type="hidden" id="type" value="${type}">
<input name="ID" type="hidden" class="input1" id="ID" value="${ID}"
	size="30" />


</body>
</html>
