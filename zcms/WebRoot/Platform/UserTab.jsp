<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
var tabMap = {
	Site:"UserTabSite.jsp?PrivType=site",
	Menu:"UserTabMenu.jsp?2=2",
	Article:"UserTabCatalog.jsp?4=4"
};

var TabID = "Site";//全局的
var OldSiteID = "";
var OldPrivType = "";

Page.onLoad(onUserTabClick);

function onUserTabClick(tabid){
	if(tabid){
		TabID = tabid;
	}
	var UserName = $V("UserName");
	Tab.onChildTabClick(TabID);
	var URL = Tab.getCurrentTab().src;
	var newURL = tabMap[TabID]+"&UserName="+UserName+"&OldSiteID="+OldSiteID+"&OldPrivType="+OldPrivType;
	if(URL.indexOf(newURL)<0){
		Tab.getCurrentTab().src = newURL;
	}
	return;
}

</script>
</head>
<body >  
<z:init>
  <table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
    <tr valign="top">
    <td>
    <input id = "UserName" type="hidden" value="${UserName}">
	<z:tab>
		<z:childtab id="Site" src="" afterClick="onUserTabClick('Site')" selected="true" ><img src="../Icons/icon040a1.gif" /><b>站点权限</b></z:childtab>
	    <z:childtab id="Menu" src="" afterClick="onUserTabClick('Menu')" ><img src="../Icons/icon018a1.gif" /><b>菜单权限</b></z:childtab>
	    <z:childtab id="Article" src="" afterClick="onUserTabClick('Article')"><img src="../Icons/icon003a1.gif" /><b>文档权限</b></z:childtab>
	</z:tab>
	</td>
    </tr>
</table>
</z:init>
</body>
</html>
