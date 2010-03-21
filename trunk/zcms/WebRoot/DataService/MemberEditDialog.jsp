<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script type="text/javascript">

function checkPWD(){
	var password=$V("NewPassword");
	var confirmPassword=$V("ConfirmPassword");
	if(password==confirmPassword){
		return true;
	} else{
		return false;
	}
}

</script>

</head>
<body class="dialogBody">
<z:init method="com.zving.cms.dataservice.Member.initDialog">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0" align="center">
		<tr>
			<td width="492" height="10"></td>
			<td width="612"></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">登录名：</td>
			<td height="25"><b>${UserName}</b><input type="hidden" id="UserName" name="UserName" value="${UserName}"/></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">密码：</td>
			<td height="25">
            <input name="NewPassword" type="password" value="******" style="width:150px" class="inputText" id="NewPassword" verify="密码至少为六位|Regex=\S{6,}&&NotNull" />
            </td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">重复密码：</td>
			<td height="25">
            <input name="ConfirmPassword" type="password" value="******" style="width:150px" class="inputText" id="ConfirmPassword" verify="两次密码必须相同|Script=checkPWD()" /></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">姓名：</td>
			<td height="25">
            <input name="Name" type="text" value="${Name}" style="width:150px" class="inputText" id="Name" verify="姓名|NotNull" />
            </td>
		</tr>
        <tr>
			<td height="25" align="right" class="tdgrey1">电子邮件：</td>
			<td height="25">
            <input name="Email" type="text" value="${Email}" style="width:150px" class="inputText" id="Email" verify="电子邮件|NotNull" />
            </td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">会员类型：</td>
			<td height="25">
            <z:select id="Type" name="Type" style="width:150px" listWidth="150"> ${Type}</z:select>
			</td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">会员级别：</td>
			<td height="25">
            <z:select id="MemberLevel" name="MemberLevel" style="width:150px" listWidth="150"> ${MemberLevel}</z:select>
			</td>
		</tr>
		<tr>
			<td height="25" align="right"><strong>性别：</strong></td>
			<td>${Gender}</td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">积分：</td>
			<td height="25">
            <input name="Score" type="text" value="${Score}" style="width:150px" class="inputText" id="Score" verify="积分|NotNull&&Number" /></td>
		</tr>
		<tr>
			<td height="25" align="right"><strong>审核状态：</strong></td>
			<td><z:select id="Status" name="Status" style="width:150px" listWidth="150"> ${Status}</z:select></td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">密码问题：</td>
			<td height="25">
            <input name="PWQuestion" type="text" value="${PWQuestion}" style="width:150px" class="inputText" id="PWQuestion"/>
            </td>
		</tr>
		<tr>
			<td height="25" align="right" class="tdgrey1">密码回答：</td>
			<td height="25">
            <input name="PWAnswer" type="text" value="${PWAnswer}" style="width:150px" class="inputText" id="PWAnswer" />
            </td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
