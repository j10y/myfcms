<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body onContextMenu="return false;">
<z:tree id="tree1" style="height:380px" method="com.zving.cms.resource.VideoLib.treeDataBind">
	<p cid='${ID}'><label><input type="checkbox" name="ID" value='${ID}'><span id="span_${ID}">${Name}</span></label></p>
</z:tree>
</body>
</html>
