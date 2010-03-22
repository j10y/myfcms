<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.dataservice.CustomTable.initImportStep2">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>站点</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	Dialog.endWait();
	_DialogInstance.OKButton.onclick = Parent.executeImportData;
});
</script>
</head>
<body scroll="no">
<input type="hidden" id="ID" value="${ID}">
<input type="hidden" id="FileName" value="${FileName}">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="6"
	style="border-collapse: separate; border-spacing: 6px;">
<tr valign="top">
  <td align="center" valign="middle"><span style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;"> ${Message}</span></td>
</tr>
</table>
</body>
</html>
</z:init>
