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
	function PassWordSave() {
		var dc = Form.getData($F("postform"));
		Server.sendRequest("com.zving.bbs.ControlPanel.passwordSave",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				$D.close();
			}
		});
	}
	
</script>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.bbs.ControlPanel.init">
	<div class="wrap">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">${UserName},个人信息管理 </a> </b></span> <span class="fr"> ${Priv} </span> </div><div id="nav"><a href="Index.jsp?SiteID=${SiteID}">ZvingBBS</a>  &raquo; 控制面板</div>
		<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" value="${SiteID}" id="SiteID">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="ControlPanel.jsp?SiteID=${SiteID}">个人资料</a></li>
                <li><a href="SelfSetting.jsp?SiteID=${SiteID}">个性化设定</a></li>
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
                <li><a href="MyArticles.jsp?SiteID=${SiteID}">我发表的文章</a></li>
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
                <!--li><a href="MyPriv.jsp">我的权限</a></li-->
                <li class="current"><a href="MyScore.jsp?SiteID=${SiteID}">我的积分</a></li>
              </ul>

            </div>
          </div></td>
    <td width="20">&nbsp;</td>
    <td>
	<div class="forumbox">
	<h2>积分记录</h2><br/>
	<h3>您目前总积分: ${ForumScore}</h3>&nbsp;&nbsp;<br/><br/><br/>
	</div>
	</td></tr>
</table>
</div>
</z:init>
<%@ include file="../Include/foot.jsp" %>

</body>

</html>