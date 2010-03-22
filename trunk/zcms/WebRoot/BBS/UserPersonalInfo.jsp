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
		if(Verify.hasError()){
		 	 return;
	    }
		Server.sendRequest("com.zving.bbs.ControlPanel.perInfoSave",dc,function(response){
			Dialog.alert(response.Message, function(){
				if(response.Status==1){
					window.location="ControlPanel.jsp";
				}
			});
		});
	}
</script>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.bbs.UserPersonalInfo.init">
<div class="wrap">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">查看用户${UserName}个人信息 </a> </b></span> <span class="fr">${Priv}</span> </div><div id="nav"><a href="Index.jsp?SiteID=${SiteID}">ZvingBBS</a>  &raquo; 查看用户个人信息</div>
		<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td>
	<div class="forumbox">
    <form method="post" id="postform">
	  <h4>个人资料</h4>
   <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">基本信息</h5><table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
   <tr>
    <td width="80" align="right">登陆名：</td>
    <td aling="left">${UserName}</td>
  </tr>
  <tr>
    <td width="80" align="right">E-mail：</td>
    <td aling="left">${Email}</td>
  </tr>
  

</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">扩展信息</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <input type="hidden" id="UserName" value="${UserName}" />
  <tr>
    <td width="80" align="right">用户组：</td>
    <td aling="left">${group}</td>
  </tr>
  <tr>
    <td width="80" align="right">昵称：</td>
    <td aling="left">${NickName}</td>
  </tr>
  <tr>
    <td width="100" align="right">生日：</td>
    <td aling="left">${Birthday}</td>
  </tr>
  <tr>
    <td width="100" align="right">QQ：</td>
    <td aling="left">${QQ}</td>
  </tr>
  <tr>
    <td width="100" align="right">MSN：</td>
    <td aling="left">${MSN}</td>
  </tr>
  <tr>
    <td width="100" align="right">TEL：</td>
    <td aling="left">${Tel}</td>
  </tr>
  <tr>
    <td width="100" align="right">手机：</td>
    <td aling="left">${Mobile}</td>
  </tr>
  <tr>
    <td width="100" align="right">地址：</td>
    <td aling="left">${Address}</td>
  </tr>
  <tr>
    <td width="100" align="right">邮编：</td>
    <td aling="left">${ZipCode}</td>
  </tr>
  <tr>
    <td width="100" align="right">注册日期：</td>
    <td aling="left">${RegTime}</td>
  </tr>
  <tr>
    <td width="80" align="right">注册IP：</td>
    <td aling="left">${RegIP}</td>
  </tr>
  <tr>
    <td width="80" align="right">最后登陆：</td>
    <td aling="left">${LastLoginTime}</td>
  </tr>
  <tr>
    <td width="80" align="right">积分：</td>
    <td>${ForumScore}</td>
  </tr>
  <tr>
    <td width="80" align="right">发帖数：</td>
    <td aling="left">${ThemeCount}</td>
  </tr>
  <tr>
    <td width="80" align="right">回复数：</td>
    <td aling="left">${ReplyCount}</td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
	
</form></div>
</td>
  </tr>
</table>
</div>
</z:init>
<%@ include file="../Include/foot.jsp" %>

</body>

</html>