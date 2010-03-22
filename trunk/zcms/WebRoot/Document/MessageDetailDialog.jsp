<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
Page.onLoad(function(){
	var type = $V("Type");			 
	if(type=="history"){
		$("replybutton").style.display="none";
	}
});

function reply(){
	var ID = $V("ID");
	if(!ID||ID==""){
		return;
	}
	var diag =new Dialog("diag3");
	diag.Width = 500;
	diag.Height = 200;
	diag.Title = "回复消息";
	diag.URL = "Document/MessageReplyDialog.jsp?ID="+ID;
	diag.OKEvent = ReplySave;
	diag.show();
}

function ReplySave(){
	if($DW.Verify.hasError()){
		return;
    }
	var dc = Form.getData($DW.$F("formMsg"));
	Server.sendRequest("com.zving.cms.document.Message.reply",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message);
			$D.close();
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
<z:init method="com.zving.cms.document.Message.initDetailDialog">
	<form id="formMsg">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="center" valign="top">
			<table width="96%" border="1" cellspacing="0" bordercolor="#e3e3e3"
				style="margin:10px auto">
				<tr>
					<td width="80" align="right" valign="top" class="tdgrey1"><input
						type="hidden" id="ID" name="ID" value="${ID}"> <input
						type="hidden" id="Type" name="Type" value="${Type}">
					${UserType}信人：</td>
					<td align="left" class="tdgrey2">${FromUser}${ToUser}</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="tdgrey1">发送时间：</td>
					<td align="left" class="tdgrey2">${AddTime}</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="tdgrey1">标题：</td>
					<td align="left" class="tdgrey2">${Subject}</td>
				</tr>
				<tr>
					<td align="right" valign="middle" class="tdgrey1">内容：</td>
					<td align="left" valign="top" class="tdgrey2">${Content}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="30" colspan="2" align="center" valign="bottom">
			<div class="dialogButtonBg" style=" padding:8px;"><input
				name="replybutton" id="replybutton" type="button" onClick="reply();"
				value="回 复" />&nbsp;&nbsp;&nbsp; <input name="button2" type="button"
				onClick="Dialog.close();" value="关 闭" /></div>
			</td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
