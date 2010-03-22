<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信管理</title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../../Framework/Main.js"></script>
<script type="text/javascript">
function reloadPage(ele){	
	var dg1 = Tab.getChildTab(ele).contentWindow.$("dg1");
	if(dg1){
		Tab.getChildTab(ele).contentWindow.DataGrid.loadData("dg1");	
	}
}
</script>
</head>
<body>
<z:tab>
	<z:childtab id="MessageReceive" src="PostAudit.jsp" selected="true" onClick="reloadPage('MessageReceive')">
		<img src="../../Icons/icon003a13.gif"/><b>审核帖子</b>
	</z:childtab>
	<z:childtab id="Message" src="UserApplyDel.jsp" onClick="reloadPage('Message')">
		<img src="../../Icons/icon003a1.gif"/><b>用户申请删除</b>
	</z:childtab>
</z:tab>
</body>
</html>