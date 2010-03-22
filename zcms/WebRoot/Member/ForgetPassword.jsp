<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.framework.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
<link href="default.css" rel="stylesheet" type="text/css">
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript">

function getPassword(){
	var UserName = $V("UserName");
	if(UserName != ""){
		var dc = Form.getData("form2");
		Server.sendRequest("com.zving.member.MemberInfo.getPassWord",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.location = "Login.jsp?SiteID="+response.get("SiteID");
				}					   
			});
		});
	}
}

</script>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div style="width:950px; margin:auto;">
  <table width="100%" border="0" cellspacing="15" cellpadding="0">
    <tr valign="top">
      <td><div style="border:1px solid #ddd; padding:30px 20px;zoom:1;">
      <div style="border-bottom:1px solid #ddd; padding-bottom:4px; margin-bottom:10px;"><h4>密码找回</h4></div>
      <form id="form2" style="margin:20px 15px;">
      <b>请在这里填写您的注册信息以找回密码</b><br><br />
      <table width="100%" border="0" cellspacing="9" cellpadding="0">
        <tr>
        	<td width="25%" align="right">用户名:</td>
        	<td width="75%"><input type="text" class="name" name="UserName" id="UserName" value=""/>
        	<input type="hidden" id="URL" name="URL" value="<%=request.getRequestURL()%>"/></td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td><input type="button" onclick="getPassword();" value=" 提 交 "/></td>
        </tr>
        </table></form>
      </div></td>
    </tr>
  </table>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
