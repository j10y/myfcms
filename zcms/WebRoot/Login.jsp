<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%> 
<%@page import="com.zving.framework.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.platform.*"%>
<%@page import="com.zving.framework.license.*"%>
<%
//if(session.getAttribute(com.zving.zas.Constant.UserSessionAttrName)!=null){
//	Login.ssoLogin(request,response,((UserData)session.getAttribute(com.zving.zas.Constant.UserSessionAttrName)).getUserName());
//}
%>
<%@page import="com.zving.zas.UserData"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Config.getAppCode()%><%=Config.getAppName()%></title>
<link href="Include/Default.css" rel="stylesheet" type="text/css">
<style>
.input1{ border:1px solid #84A1BD; width:100px; height:20px; line-height:23px;}
.input2{ border:1px solid #84A1BD; width:68px; height:20px; line-height:23px;}
.button1{
	border:none;
	width:70px;
	height:27px;
	line-height:23px;
	color:#525252;
	font-size:12px;
	font-weight:bold;
	background-image: url(images/bt001.jpg);
	background-repeat: no-repeat;
	background-position: 0px 0px;
}
.button2{
	border:none;
	width:70px;
	height:27px;
	line-height:23px;
	color:#525252;
	font-size:12px;
	font-weight:bold;
	background-image: url(images/bt002.jpg);
	background-repeat: no-repeat;
	background-position: 0px 0px;
}
.STYLE3 {
	color: #FF0000;
	font-weight: bold;
}
</style>
<script src="Framework/Main.js"></script>
<script>
function login(){
	var dc = Form.getData("form1");
	Server.sendRequest("com.zving.platform.Login.submit",dc,function(response){
		if(response&&response.Status==0){
			alert(response.Message);
		}
	});
}
document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		login();
	}
}
Page.onLoad(function(){
	if(window.top.location != window.self.location){
		window.top.location = window.self.location;
	}
	else{
		$("UserName").focusEx();
		//测试开发用
		Server.sendRequest("com.zving.platform.Login.getVerifyCode",null,function(response){
			$S("UserName","admin");
			$S("Password","admin");
			$S("VerifyCode",response.get("VerifyCode"));
			var loginImg = $("LoginImg");
			//loginImg.onclick.apply(loginImg);
		});
	}
});
</script>
</head>
<body>
<form id="form1" method="post" style=" display:block;height:100%;">
<table width="100%" height="100%">
	<tr>
		<td align="center" valign="middle">
		<table
			style=" height:283px; width:620px; background:url(Platform/Images/loginbg.jpg) no-repeat 0px 0px;">
			<tr>
				<td>
				<div style="height:213px; width:620px;"></div>
				<div style="height:70px; width:620px;margin:0px auto 0px auto;">
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0" style="margin-top:8px;">
					<tr>
						<td align="right">用户名：</td>
						<td><input name="UserName" type="text" style="width:80px"
							id="UserName" class="inputText" onfocus="this.select();"/></td>
						<td align="right">密码：</td>
						<td><input name="Password" type="password" style="width:80px"
							id="Password" class="inputText" onfocus="this.select();"/></td>
						<td align="right">验证码：</td>
						<td><img src="AuthCode.jsp" alt="点击刷新验证码" height="16"
							align="absmiddle" style="cursor:pointer;"
							onClick="this.src='AuthCode.jsp'" />&nbsp; <input
							name="VerifyCode" type="text" style="width:60px" id="VerifyCode"
							class="inputText" onfocus="this.select();"/></td>
						<td>&nbsp;</td>
						<td><img id="LoginImg" src="Platform/Images/loginbutton.jpg"
							onClick="login();" style="cursor:pointer" /></td>
					</tr>
					<tr>
						<td height="23" colspan="8" align="center" valign="bottom">Copyright
						&copy; 2007-2009 Zving.com Inc. All rights reserved. 泽元软件 版权所有</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
		</table>
		<br>
		<%if(License.needWarning()){%> 请注意，当前许可证将于 <span class="STYLE3"><%=DateUtil.toString(LicenseInfo.getEndDate())%></span>
		失效！ <a href="LicenseRequest.jsp">点击此处获得新的许可证。</a> <%}%>
		</td>
	</tr>
</table>
</form>
</body>
</html>
