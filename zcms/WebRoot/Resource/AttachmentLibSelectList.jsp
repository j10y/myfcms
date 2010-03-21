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
	var t = ele.innerText;
	if(v==null){
		Selector.setReturn(t,"0");
	}else{
		Selector.setReturn(t,v);
	}
}
</script>
</head>
<body>
<z:tree id="tree1" style="height:200px" method="com.zving.cms.resource.AttachmentLib.treeDataBind" level="2" lazy="true">
	<p cid='${ID}' onClick="onTreeClick(this);">${Name}</p>
</z:tree>
</body>
</html>
