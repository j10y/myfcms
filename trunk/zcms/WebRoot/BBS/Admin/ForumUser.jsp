<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../../Framework/Main.js"></script>
<script src="../../Framework/Spell.js"></script>


</head>

<body>
	<z:tab>
		<z:childtab id="ForumUserGroup" src="ForumUserGroup.jsp" selected="true"><img src="../../Icons/icon002a1.gif" /><b>用户组</b></z:childtab>
		<z:childtab id="ForumSystemGroup" src="ForumSystemGroup.jsp"  ><img src="../../Icons/icon002a1.gif" /><b>系统组</b></z:childtab>
		<z:childtab id="ForumSpecialGroup" src="ForumSpecialGroup.jsp"><img src="../../Icons/icon002a1.gif" /><b>特殊组</b></z:childtab>
	</z:tab>

</body>
</html>
