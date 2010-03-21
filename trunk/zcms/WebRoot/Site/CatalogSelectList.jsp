<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
*{ box-sizing: border-box; -moz-box-sizing: border-box; -khtml-box-sizing: border-box; -webkit-box-sizing: border-box; }
body{_overflow:auto;}
</style>
<script src="../Framework/Main.js"></script>
<script>
function onTreeClick(ele){
	var v =  ele.getAttribute("cid");
	v = v? v:"";
	var t = ele.innerText;
	Selector.setReturn(t,v);
}
</script>
</head>
<body class="dialogBody">
<z:tree id="tree1" method="com.zving.cms.site.Catalog.treeDataBind"
	level="2" lazy="true" expand="true">
	<p cid='${ID}' onClick="onTreeClick(this);">${Name}</p>
</z:tree>
</body>
</html>
