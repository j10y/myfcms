<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function Pass(){
	var ID = $V("ID");
	var dc = new DataCollection();
	dc.add("ID",ID);
	dc.add("Type","Pass");
	Server.sendRequest("com.zving.cms.dataservice.Comment.Verify",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				window.Parent.reloadList();
				Dialog.close();
			});
		}else{
		   Dialog.alert(response.Message);
		}
	})
}

function NoPass(){
	var ID = $V("ID");
	var dc = new DataCollection();
	dc.add("ID",ID);
	dc.add("Type","NoPass");
	Server.sendRequest("com.zving.cms.dataservice.Comment.Verify",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				window.Parent.reloadList();
				Dialog.close();
			});
		}else{
		   Dialog.alert(response.Message);
		}
	})
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
.tdgrey1{ background-color:#f9f9f9}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.dataservice.Comment.initDetail">
<form id="formMsg">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td align="center" valign="top">
<table width="96%" border="1" cellspacing="0" bordercolor="#e3e3e3" style="margin:10px auto">
  <tr>
    <td  width="250" align="right" valign="top" class="tdgrey1" >
    <input type="hidden" id="ID" name="ID" value="${ID}">
    评论人：</td>
    <td width="866" align="left" class="tdgrey2">${AddUser}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1" >评论时间：</td>
    <td align="left" class="tdgrey2">${AddTime}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1" >标题：</td>
    <td align="left" class="tdgrey2">${Title}</td>
  </tr>
  <tr>
    <td  align="right" valign="middle" class="tdgrey1">内容：</td>
    <td align="left" valign="top" class="tdgrey2">${Content}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1">评论人IP：</td>
    <td align="left" valign="top" class="tdgrey2">${AddUserIP}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1">审核状态：</td>
    <td align="left" valign="top" class="tdgrey2">${VerifyFlag}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1">审核人：</td>
    <td align="left" valign="top" class="tdgrey2">${VerifyUser}</td>
  </tr>
  <tr>
    <td  align="right" valign="top" class="tdgrey1">审核时间：</td>
    <td align="left" valign="top" class="tdgrey2">${VerifyTime}</td>
  </tr>
</table>
</td></tr>  <tr>
    <td height="30" colspan="2" align="center" valign="bottom">
	<div class="dialogButtonBg" style=" padding:8px;">
    <input name="replybutton" id="replybutton" type="button" onClick="Pass();" value="审核通过"/>&nbsp;&nbsp;&nbsp;
    <input name="replybutton" id="replybutton" type="button" onClick="NoPass();" value="审核不通过"/>&nbsp;&nbsp;&nbsp;
    <input name="button2" type="button" onClick="Dialog.close();" value="关 闭"/></div></td></tr>
</table>
</form>
</z:init>
</body>
</html>