<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员中心</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css">
<link href="../Include/front-end.css" rel="stylesheet" type="text/css" />
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript">

function doLogout(){
	Dialog.confirm("您确认退出吗？",function(){
		var dc = new DataCollection();
		Server.sendRequest("com.zving.member.Login.Logout",dc,function(response){
			window.location = "Login.jsp?SiteID="+response.Message;
		});													
	});
}

function save(){
	var dc = Form.getData("InfoForm");
	Server.sendRequest("com.zving.member.MemberInfo.doSave",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.location.reload();
			}						   
		});
	});
}

</script>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<%@ include file="Verify.jsp"%>
<div class="wrap">
<div id="menu" class="forumbox"> <span class="fl"> <b> <%=User.getValue("Name")%> </b> <a href="#;" onClick="doLogout();">退出</a> </span> <span class="fr"> <a href="#;">标签</a> | <a href="#;">帮助</a> </span> </div>
  <div id="nav"><a href="#">首页</a> &raquo; 编辑个人资料</div>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td width="200"><div class="side">
          <h4>会员中心</h4>
          <div class="sideinner">
            <hr class="shadowline"/>
            <ul class="sidemenu">
              <li class="current"><a href="#;">编辑个人资料</a></li>
              <li><a href="WriteArticle.jsp">网站文章投稿</a></li>
              <li><a href="MemberArticles.jsp">我投稿的文章</a></li>
              <li><a href="Password.jsp">修改密码</a></li>
            </ul>
          </div>
        </div></td>
      <td width="20">&nbsp;</td>
      <td>
      <z:init method="com.zving.member.MemberInfo.init">
      <form id="InfoForm">
          <div class="forumbox"> <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span>
            <h4>编辑个人资料</h4>
            <ul class="tabs ">
              <li class="current"><a href="#action=profile&amp;typeid=2">基本资料</a></li>
              <li><a href="MemberInfoDetail.jsp">详细资料</a></li>
              <li><a href="Logo.jsp">上传头像</a></li>
            </ul>
            <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">基本信息</h5>
            <table width="80%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
              <tr>
                <td width="18%" align="right">用户名：</td>
                <td width="39%"><b>${UserName}</b><input type="hidden" id="UserName" name="UserName" value="${UserName}"/></td>
                <td width="43%" rowspan="5" align="left" valign="middle"><img id="LogoPic" src="${Logo}" width="100" height="100" style="border:#DFDFDF 1px solid"/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="Logo.jsp">上传头像</a></td>
              </tr>
               <tr>
                <td width="18%" align="right">验证状态：</td>
                <td width="39%"><b style="color:#999999">${StatusName}</b></td>
              </tr>
              <tr>
                <td width="18%" align="right">会员级别：</td>
                <td width="39%"><b>${MemberLevelName}</b></td>
              </tr>
              <tr>
                <td width="18%" align="right">注册时间：</td>
                <td width="39%"><b>${RegTime}</b></td>
                </tr>
              <tr>
                <td width="18%" align="right">上次登录：</td>
                <td width="39%"><b>${LastLoginTime}</b></td>
                </tr>
            </table>
            <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">扩展信息</h5>
             <table width="99%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
              <tr>
                <td width="14%" align="right">昵称/公司名称：</td>
                <td width="31%"><input id="Name" name="Name" type="text" class="textInput" size="35" value="${Name}"/></td>
                <td width="55%"><div class="paraphrastic">3个以上字母或2个以上汉字。</div></td>
              </tr>
                <tr>
                <td align="right">E-mail：</td>
                <td><input id="Email" name="Email" type="text" class="textInput" size="35" value="${Email}"/></td>
                <td><div class="paraphrastic">您的邮箱是找回密码的重要途径，请您仔细填写。</div></td>
              </tr>
              <tr>
                <td width="14%" align="right">性别：</td>
                <td width="31%">${Gender}</td>
                <td><div class="paraphrastic">&nbsp;</div></td>
              </tr>
            </table>
            <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
            <div style=" padding-left:150px;">
              <button type="button" onClick="save()" name="tiajiao" value="true" class="submit">提交</button>
              &nbsp;
              <button type="reset" name="reset" value="false" class="button">重 置</button>
            </div>
          </div>
        </form></z:init></td>
    </tr>
  </table>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>