<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件部署</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/Tabpage.js"></script>
<script>
var currentTab = "Config";
var grantType ;
var currentTreeItem,lastTreeItem;
Page.onLoad(function(){
});

Page.onClick(function(){
	var div = $("_DivContextMenu");
	if(div){
		$E.hide(div);
	}
});

function onTabChange(tab){
	currentTab = tab;
	var tabUrl = Server.ContextPath+"DataChannel/Deploy"+tab+".jsp";
	Tab.getChildTab(currentTab).src = tabUrl;
}

</script>
</head>

<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td><z:tab>
			<z:childtab id="Basic" selected="true" src="DeployConfig.jsp"><img src="../Icons/icon002a1.gif" width="20" height="20" /><b>任务配置</b></z:childtab>
			<z:childtab id="Template" src="DeployJob.jsp"><img src="../Icons/icon003a12.gif" /><b>任务监视</b></z:childtab>
			<z:childtab id="Block" src="DeployLog.jsp"><img src="../Icons/icon003a1.gif" /><b>执行日志</b></z:childtab>
		</z:tab></td>
	</tr>
</table>
</body>
</html>
