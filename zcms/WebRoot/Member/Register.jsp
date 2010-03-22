<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.framework.Config"%>
<%@page import="com.zving.framework.data.QueryBuilder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员注册</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css">
<link href="../Include/front-end.css" rel="stylesheet" type="text/css" />
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<%
String SiteID = request.getParameter("SiteID");
if(StringUtil.isEmpty(SiteID)||SiteID==null||SiteID=="null"){
	SiteID = new QueryBuilder("select ID from ZCSite Order by AddTime Desc").executeOneValue()+"";
}

%>
<script type="text/javascript">

function TipMsg(){
	this.tips = [];
	this.addTips = function(ele,Str){
		var flag = false;
		for(var i=0;i<this.tips.length;i++){
			if(this.tips[i].substring(0,this.tips[i].indexOf(":"))==ele.id){
				flag = true;
			}
		}
		if(!flag){
			this.tips[this.tips.length] = ele.id+":"+ele.innerHTML;
		}
		ele.innerHTML = Str;
	}
	this.restore = function(ele){
		var flag = false;
		var index;
		for(var i=0;i<this.tips.length;i++){
			if(this.tips[i].substring(0,this.tips[i].indexOf(":"))==ele.id){
				index = i;
				flag = true;
			}
		}
		if(flag){
			var Str = this.tips[index].substring(this.tips[index].indexOf(":")+1);
			ele.innerHTML = Str;
		}
	}
}

var tipMsg = new TipMsg();
var checkUserFlag = true;

function checkUser(){
	var dc = new DataCollection();
	var userName = $V("UserName");
	if(userName.trim()==""){
		tipMsg.addTips($("UserNameNote"),"<font color='red'>用户名不能为空!用户名长度4-16个字符(包括4、16)， 请用英文小写、数字、下划线。</font>");
		return false;
	}
	var ulength = userName.trim().length;
	var rlength = userName.trim().replace(/[\W]/g,'').length;
	if(ulength>16||ulength<4){
		tipMsg.addTips($("UserNameNote"),"<font color='red'>长度错误！用户名长度4-16个字符(包括4、16)， 请用英文小写、数字、下划线。</font>");
		return false;
	}
	if(rlength!=ulength){
		tipMsg.addTips($("UserNameNote"),"<font color='red'>不能使用中文和特殊符号！请用英文小写、数字、下划线。</font>");
		return false;
	}
	dc.add("UserName",userName);
	Server.sendRequest("com.zving.member.Register.checkUser",dc,function(response){
		if(response.Status!=1){
			tipMsg.addTips($("UserNameNote"),"<font color='red'>"+response.Message+"</font>");
			checkUserFlag = false;
		}else{
		    tipMsg.addTips($("UserNameNote"),"<font color='green'>"+response.Message+"</font>");
			checkUserFlag = true;
		}
	});
	return true;
}	

function checkPassWord(){
	var passWord = $V("PassWord");
	if(passWord.trim()==""){
		tipMsg.addTips($("PassWordNote"),"<font color='red'>用户密码不能为空，请输入4-16位数字和字母的组合</font>");
		return false;
	}else if(passWord.trim().length>16||passWord.trim().length<4){
		tipMsg.addTips($("PassWordNote"),"<font color='red'>用户密码长度不正确，请输入4-16位数字和字母的组合</font>");
		return false;
	}else{
		tipMsg.restore($("PassWordNote"));
	}
	return true
}

function checkConfirmPassword(){
	var passWord = $V("PassWord");
	var confirmPassword = $V("ConfirmPassword");
	if(passWord.trim()!=confirmPassword.trim()){
		tipMsg.addTips($("ConfirmPasswordNote"),"<font color='red'>两次输入的密码不一致，请重新输入</font>");
		return false;
	}else{
		tipMsg.restore($("ConfirmPasswordNote"));
	}
	return true
}

function checkEmail(){
	var email = $V("Email");
	var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(email&&email.match(pattern)==null){
		tipMsg.addTips($("EmailNote"),"<font color='red'>电子邮箱的格式不正确，请输入正确的电子邮箱地址</font>");
		return false;
	}else if(email.trim()==""){
		tipMsg.addTips($("EmailNote"),"<font color='red'>电子邮箱不能为空，请输入正确的电子邮箱地址</font>");
		return false;	
	}else{
		tipMsg.restore($("EmailNote"));
		return true
	}
}

function checkName(){
	var Name = $V("Name");
	if(RealName.trim()==""){
		tipMsg.addTips($("NameNote"),"<font color='red'>真实姓名不能为空</font>");
		return false;	
	}else{
		tipMsg.restore($("NameNote"));
		return true
	}
}

function checkVerifyCode(){
	var verifyCode = $V("VerifyCode");
	if(verifyCode.trim()==""){
		tipMsg.addTips($("VerifyCodeNote"),"<font color='red'>验证码不能为空</font>");
		return false;	
	}else{
		return true;
	}
}

function doReg(){
	if(checkUser()&&checkPassWord()&&checkConfirmPassword()&&checkEmail()&&checkVerifyCode()&&checkUserFlag){
		if($("Agree").checked==true){
			if($V("SiteID")!=""&&$V("SiteID")!="null"){
				var dc = Form.getData("regform");
				Server.sendRequest("com.zving.member.Register.register",dc,function(response){
					if(response.Status!=1){
						Dialog.alert(response.Message);
					}else{
						window.location = "MemberInfo.jsp";
					}
				});
			}else{
				Dialog.alert("错误，找不到站点");
				return;	
			}
		}else{
			Dialog.alert("未同意服务协议，不能注册");
			return;
		}
	}else{
		return;
	}
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		doReg();
	}
}

function changeNameTitle(type){
	if(type==1){
		$("NameTitle").innerHTML = "真实姓名：";
	}else{
		$("NameTitle").innerHTML = "公司名称：";
	}
}
</script>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div class="wrap">
  <div id="menu" class="forumbox"> <span class="fl"> <a href="#;">注册</a> <a href="Login.jsp?SiteID=<%=SiteID%>">登录</a> </span> <span class="fr"><a href="#;">标签</a> | <a href="#;">帮助</a>    </span> </div>
  <div id="nav" style="margin-bottom:15px;"><a href="#">首页</a> &raquo; 注册</div>
  <form id="regform">
	<div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>注册</h4>
           	  <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">基本信息</h5><table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <tr>
    <td width="100" align="right">账号类型：</td>
    <td width="200">&nbsp;&nbsp;<label for="Type1"><input type="radio" name="Type" id="Type1" value="Person" checked onClick="changeNameTitle(1);">个人</label>
    <label for="Type2"><input type="radio" name="Type" id="Type2" value="Company" onClick="changeNameTitle(2);">企业</label></td>
    <td><input type="hidden" id="SiteID" name="SiteID" value="<%=SiteID%>"/></td>
  </tr>
  <tr>
    <td width="100" align="right">用户名：</td>
    <td width="200"><span style=" font-family:'宋体'; color:#F60">*</span>      <input id="UserName" name="UserName" type="text" class="textInput" onblur="checkUser();"/></td>
    <td><div id="UserNameNote" class="gray">4-16个字符(包括4、16)或2-8个汉字， 请用英文小写、数字、下划线，以字母开头。</div></td>
  </tr>
  <tr>
    <td align="right">密码：</td>
    <td><span style=" font-family:'宋体'; color:#F60">*</span> <input id="PassWord" name="PassWord" type="password" class="textInput" onblur="checkPassWord();" /></td>
    <td><div id="PassWordNote" class="gray">字母有大小写之分。4—16位（包括4、16），限用英文、数字。</div></td>
  </tr>
  <tr>
    <td align="right">确认密码：</td>
    <td><span style=" font-family:'宋体'; color:#F60">*</span> <input id="ConfirmPassword" name="ConfirmPassword" type="password" class="textInput" onblur="checkConfirmPassword();"/></td>
    <td><div id="ConfirmPasswordNote" class="gray">请您再确认一次密码。</div></td>
  </tr>
  <tr>
    <td align="right">E-mail：</td>
    <td><span style=" font-family:'宋体'; color:#F60">*</span> <input id="Email" name="Email" type="text" class="textInput" onblur="checkEmail();"/></td>
    <td><div id="EmailNote" class="gray">您的邮箱是找回密码的重要途径，请您仔细填写。</div></td>
  </tr>
  <tr>
    <td align="right">验证码：</td>
    <td><span style=" font-family:'宋体'; color:#F60">*</span>
      <input id="VerifyCode" name="VerifyCode" type="text" class="textInput" size="5" onblur="checkVerifyCode();"/>
<img src="../AuthCode.jsp" alt="点击刷新验证码" name="yzm" height="20" align="middle" id="yzm" class="yanheng" style="cursor:pointer;" onClick="yzm.src='../AuthCode.jsp'" /></td>
    <td><div id="VerifyCodeNote" class="gray">为了防止机器自动注册行为，请输入图片上的验证码。</div></td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">扩展信息</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <tr>
    <td width="100" align="right" id="NameTitle">真实姓名：</td>
    <td width="200"><input id="Name" name="Name" type="text" class="textInput"/></td>
    <td><div id="NameNote" class="gray">3个以上字母或2个以上汉字。</div></td>
  </tr>
  <tr>
    <td width="100" align="right">安全问题：</td>
    <td width="200">
    <select id="PWQuestion" name="PWQuestion" style="width:130px;">
    <option value="" selected>没安全问题</option>
    <option value="你家乡的名称是什么？">你家乡的名称是什么？</option>
    <option value="你读的小学叫什么？">你读的小学叫什么？</option>
    <option value="你的父亲叫什么名字？">你的父亲叫什么名字？</option>
    <option value="你的母亲叫什么名字？">你的母亲叫什么名字？</option>
    <option value="你最喜欢的一首歌曲？">你最喜欢的一首歌曲？</option>
    </select>
    </td>
    <td><div class="gray">此问题用于找回密码</div></td>
  </tr>
  <tr>
    <td width="100" align="right">问题答案：</td>
    <td width="200"><input type="text" id="PWAnswer" name="PWAnswer" value=""/></td>
    <td><div class="gray">输入安全问题的答案</div></td>
  </tr>
  <tr>
    <td width="100" align="right">性别：</td>
    <td width="200">
    <label for="Gender1"><input type="radio" name="Gender" id="Gender1" value="M" checked>男</label>
    <label for="Gender2"><input type="radio" name="Gender" id="Gender2" value="F">女</label></td>
    <td><div class="gray">&nbsp;</div></td>
  </tr>
  <tr>
    <td align="right">手机：</td>
    <td><input id="Mobile" name="Mobile" type="text" class="textInput" /></td>
    <td><div class="gray">格式如 +8613412345678 或 13412345678。</div></td>
  </tr>
  <tr>
    <td align="right">地址：</td>
    <td><input id="Address" name="Address" type="text" class="textInput" style="width:180px;"/></td>
    <td><div class="gray">请按普通邮件收件人格式填写。</div></td>
  </tr>
  <tr>
    <td align="right">邮编：</td>
    <td><input id="ZipCode" name="ZipCode" type="text" class="textInput" /></td>
    <td><div class="gray">&nbsp;</div></td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
              <div style="text-align:center;">　<a href="#;">点此阅读《服务条款》</a>　　　
                <span style=" font-family:'宋体'; color:#F60">*</span>
                <input type="checkbox" name="Agree" id="Agree" checked="checked" />&nbsp;我已看过并同意《服务条款》<br /><br />
                <button type="button" onClick="doReg();" name="tiajiao" value="true" class="btn">提交</button> &nbsp; 
        <button type="reset" name="reset" value="false" class="btn">重 置</button></div>
            </div>
</form>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>