<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
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

function modifyPassword(){
	if($V("OldPassWord").replace(/(^\s*)|(\s*$)/g,"")==""||$V("NewPassWord").replace(/(^\s*)|(\s*$)/g,"")==""||$V("ConfirmPassword").replace(/(^\s*)|(\s*$)/g,"")==""){
		Dialog.alert("请填写密码信息");
		return;
	}
	if($V("NewPassWord").replace(/(^\s*)|(\s*$)/g,"").length<4){
		Dialog.alert("密码位数太少");
		return;
	}
	var dc = Form.getData("passForm");
	Server.sendRequest("com.zving.member.MemberInfo.modifyPassword",dc,function(response){
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
<div id="menu" class="forumbox"> <span class="fl"> <b> <%=User.getValue("Name")%> </b> <a href="#;" onClick="doLogout();">退出</a> </span> <span class="fr"> <a href="#;">标签</a> | <a href="#;">帮助</a> </span> </div><div id="nav"><a href="#;">首页</a>  &raquo; 修改密码</div>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
            <div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
              	<li><a href="MemberInfo.jsp">编辑个人资料</a></li>
                <li><a href="WriteArticle.jsp">网站文章投稿</a></li>
                <li><a href="MemberArticles.jsp">我投稿的文章</a></li>
                <li class="current"><a href="#;">修改密码</a></li>
              </ul>
            </div>
          </div></td>
    <td width="20">&nbsp;</td>
    <td>
    <form id="passForm">
    <div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>修改密码</h4>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="forumTable">
        <tr>
            <td width="100" align="right">原密码：</td>
            <td width="200"><i>*</i> <input type="password" class="textInput" id="OldPassWord" name="OldPassWord" value="" /></td>
            <td><input type="hidden" id="UserName" name="UserName" value="<%=User.getValue("UserName")%>"/></td>
          </tr>
          <tr>
            <td align="right">新密码：</td>
            <td><i>*</i> <input type="password" class="textInput" id="NewPassWord" name="NewPassWord" value="" /></td>
            <td><div class="paraphrastic">字母有大小写之分。4—16位（包括4、16），限用英文、数字。</div></td>
          </tr>
          <tr>
            <td align="right">确认密码：</td>
            <td><i>*</i> <input type="password" class="textInput" id="ConfirmPassword" name="ConfirmPassword" value=""/></td>
            <td><div id="ConfirmPasswordNote" class="paraphrastic">请您再确认一次密码。</div></td>
          </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
              <div style=" padding-left:120px;">
                <button type="button" name="tiajiao"  onclick="modifyPassword();" value="true" class="submit">修改</button> &nbsp;</div>
          </div>
</form></td>
  </tr>
</table>


</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>