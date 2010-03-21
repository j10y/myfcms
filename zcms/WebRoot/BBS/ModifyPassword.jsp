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
		if(Verify.hasError()){
		 	 return;
	    }
		Server.sendRequest("com.zving.bbs.ControlPanel.modifyPassword",dc,function(response){
			Dialog.alert(response.Message, function() {
				if(response.Status==1){
					window.location="ControlPanel.jsp";
				}
			});
		});
	}
	
</script>
<body>
	<div class="wrap">
<z:init method="com.zving.bbs.ControlPanel.init">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">${UserName},个人信息管理 </a> </b></span> <span class="fr"> <a href="ThemeSearch.jsp">搜索</a> | <!--a href="tag.jsp">标签</a> |--> <a href="ControlPanel.jsp">控制面板</a> | <a href="MySubject.jsp">我的话题</a> | <!--a target="_blank" href="modcp.jsp?fid=0">版主管理面板</a> | <a href="faq.jsp">帮助</a--> </span> </div>
        </z:init>
        <div id="nav"><a href="Index.jsp?SiteID=${SiteID}">ZvingBBS</a>  &raquo; 控制面板</div>
		<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="ControlPanel.jsp">编辑个人资料</a></li>
                <li class="current"><a href="ModifyPassword.jsp">修改密码</a></li>
                <li><a href="SelfSetting.jsp">个性化设定</a></li>
              </ul>
            </div>
            <div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <li><a href="#;">站内短信</a></li>
              </ul>
            </div>
            <div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <li><a href="#;">发表文章</a></li>
                <li><a href="MyArticles.jsp">我发表的文章</a></li>
              </ul>

            </div>
            <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="#;">我的好友</a></li>
                <li><a href="#">我的空间</a></li>
                <li><a href="#;">我的收藏</a></li>

              </ul>
            </div>
            <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="MyPriv.jsp">我的权限</a></li>
                <li><a href="MyScore.jsp">我的积分</a></li>
              </ul>

            </div>
          </div></td>
    <td width="20">&nbsp;</td>
    <td>
    <z:init method="com.zving.bbs.ControlPanel.init">
    <form method="post" id="postform">
	<div class="forumbox">
	<span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>编辑个人资料</h4>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">密码修改</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <input type="hidden" id="UserName" value="${UserName}" />
  <tr>
    <td width="100" align="right">原密码：</td>
    <td width="200"><input id="Password" type="password" class="textInput" verify="NotNull"/></td>
    <td><div class="gray">请正确填写原始密码。</div></td>
  </tr>
  <tr>
    <td align="right">新密码：</td>
    <td><input id="NewPwd1" type="password" class="textInput" verify="密码为4-20位|Regex=\S{4,20}&&NotNull" /></td>
    <td><div class="gray">密码为4~20位</div></td>
  </tr>
  <tr>
    <td width="100" align="right">确认新密码：</td>
    <td width="200"><input id="NewPwd2" type="password" class="textInput" verify="密码为4-20位|Regex=\S{4,20}&&NotNull"/></td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
	<div style=" padding-left:120px;">
        <button type="button" name="tiajiao" class="submit" onclick="PassWordSave();">提交</button> &nbsp; 
        <button type="reset" name="reset" value="false" class="button">重 置</button></div>
    </div>
</form>
</z:init></td>
  </tr>
</table>

</div>
</body>

</html>