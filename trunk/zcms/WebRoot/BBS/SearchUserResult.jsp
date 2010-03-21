<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>Zving BBS</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script src="../Framework/Main.js"></script>
</head> 
<script type="text/javascript">
	function searchUser() {
		var dc = Form.getData($F("searchUser"));
		if(Verify.hasError()){
		 	 return;
	    }
		Server.sendRequest("com.zving.bbs.MasterPanel.searchUser",dc,function(response){
			if(response.Status==1){
				window.location="SearchUserResult.jsp";
			}
		});
	}
</script>
<body>
	<div class="wrap">
<z:init method="com.zving.bbs.ControlPanel.init">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">${UserName},个人信息管理 </a> </b></span> <span class="fr"> <a href="ThemeSearch.jsp">搜索</a> | <!--a href="tag.jsp">标签</a> |--> <a href="ControlPanel.jsp">控制面板</a> | <a href="MySubject.jsp">我的话题</a> | <a href="MasterPanel.jsp">版主面板管理</a> <!--a target="_blank" href="modcp.jsp?fid=0">版主管理面板</a> | <a href="faq.jsp">帮助</a--> </span> </div><div id="nav"><a href="Index.jsp">ZvingBBS</a>  &raquo; 控制面板</div>
		<br>
</z:init>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li class="current"><a href="ControlPanel.jsp">编辑用户</a></li>
                <li><a href="SelfSetting.jsp">用户权限</a></li>
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
<z:init method="com.zving.bbs.MasterPanel.searchUser">
    <form method="post" id="searchUser">
	<div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>编辑用户-搜索</h4>
   <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">基本信息</h5><table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
   <tr>
    <td width="50" align="right">用户名：</td>
    <td width="200"><input type="text" name="userName" id="userName"/></td>
    <td><input type="button" name="search" id="search" onclick="searchUser()" value="搜索"/></td>
  </tr>  

</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">搜索结果</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <input type="hidden" id="UserName" value="${UserName}" />
 
  <tr>
    <td width="100" align="right">用户组：</td>
    <td width="200">${group}</td>
  </tr>
  <tr>
    <td width="100" align="right">昵称：</td>
    <td>${NickName}</td>
  </tr>
  <tr>
    <td width="100" align="right">注册日期：</td>
    <td>${RegTime}</td>
  </tr>
  <tr>
    <td width="100" align="right">注册IP：</td>
    <td>${RegIP}</td>
  </tr>
  <tr>
    <td width="100" align="right">最后登陆：</td>
    <td>${LastLoginTime}</td>
  </tr>
  <tr>
    <td width="100" align="right">积分：</td>
    <td>${ForumScore}</td>
  </tr>
  <tr>
    <td width="100" align="right">发帖数：</td>
    <td>${ThemeCount}</td>
  </tr>
  <tr>
    <td width="100" align="right">回复数：</td>
    <td>${ReplyCount}</td>
  </tr>
  
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
	</div>
</form>
</z:init></td>
  </tr>
</table>

</div>
</body>

</html>