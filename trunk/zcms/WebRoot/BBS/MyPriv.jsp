<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>Zving BBS</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
</head>
<script type="text/javascript">
	function perInfoSave() {
		var dc = Form.getData($F("postform"));
		Server.sendRequest("com.zving.bbs.ControlPanel.perInfoSave",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				$D.close();
			}
		});
	}
	
	var tabMap = {
	Menu:"UserTabMenu.jsp?2=2",
	Site:"UserTabSite.jsp?PrivType=site",
	Article:"UserTabCatalog.jsp?4=4"
};

var TabID = "Menu";//全局的
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
<body>
<%@ include file="../Include/head.jsp" %>
	<div class="wrap">
<z:init method="com.zving.bbs.ControlPanel.init">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">${UserName},个人信息管理 </a> </b></span> <span class="fr"> <a href="ThemeSearch.jsp">搜索</a> | <!--a href="tag.jsp">标签</a> |--> <a href="ControlPanel.jsp">控制面板</a> | <a href="MySubject.jsp">我的话题</a> | <!--a target="_blank" href="modcp.jsp?fid=0">版主管理面板</a> | <a href="faq.jsp">帮助</a--> </span> </div></z:init><div id="nav"><a href="Index.jsp">ZvingBBS</a>  &raquo; 控制面板</div>
		<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="ControlPanel.jsp">个人资料</a></li>
                <li><a href="SelfSetting.jsp">个性化设定</a></li>
              </ul>
            </div>
            <!--div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <li><a href="#;">站内短信</a></li>
              </ul>
            </div-->
            <div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <!--li><a href="#;">发表文章</a></li-->
                <li><a href="MyArticles.jsp">我发表的文章</a></li>
              </ul>
            </div>
            <!--hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="#;">我的好友</a></li>
                <li><a href="#">我的空间</a></li>
                <li><a href="#;">我的收藏</a></li>
              </ul>
            </div-->
            <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <!--li class="current"><a href="MyPriv.jsp">我的权限</a></li-->
                <li><a href="MyScore.jsp">我的积分</a></li>
              </ul>

            </div>
          </div></td>
    <td>
<z:init>
  <table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
    <tr valign="top">
    <td>
    <input id = "UserName" type="hidden" value="${UserName}" />
	<z:tab>
	    <z:childtab id="Menu" src="" afterClick="onUserTabClick('Menu')" selected="true" ><img src="../Icons/icon018a1.gif" /><b>菜单权限</b></z:childtab>
	    <z:childtab id="Site" src="" afterClick="onUserTabClick('Site')"><img src="../Icons/icon040a1.gif" /><b>站点权限</b></z:childtab>
	    <z:childtab id="Article" src="" afterClick="onUserTabClick('Article')"><img src="../Icons/icon003a1.gif" /><b>文档权限</b></z:childtab>
	</z:tab>
	</td>
    </tr>
</table>
</z:init></td>
  </tr>
</table>
</div>
<%@ include file="../Include/foot.jsp" %>

</body>

</html>