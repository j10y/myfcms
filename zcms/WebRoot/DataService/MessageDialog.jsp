<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function download(attid){
	if(!attid||attid==""){
		return;
	}else{
		window.open(Server.ContextPath + "DataService/MessageDownload.jsp?id="+attid,"_blank");
	}
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.dataservice.MessageBoard.initDialog">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0" align="center">
		<tr>
			<td width="22%" height="10">&nbsp;</td>
			<td width="15%"><input type="hidden" id="ID" name="ID" value="${ID}"/></td>
            <td width="15%">&nbsp;</td>
            <td width="48%">&nbsp;</td>
		</tr>
		<tr>
			<td height="25" align="right" >留言人：</td>
			<td height="25">&nbsp;<b>${AddUser}</b></td>
            <td height="25" align="right">留言时间：</td>
            <td width="48%">&nbsp;<b>${AddTime}</b></td>
		</tr>
		<tr>
			<td height="25" align="right" >电话：</td>
			<td height="25">&nbsp;<b>${Tel}</b></td>
            <td width="15%" align="right">E-mail：</td>
            <td width="48%">&nbsp;<a href="mailto:${Email}">${Email}</a></td>
		</tr>
		<tr>
			<td height="25" align="right" >地址：</td>
			<td height="25" colspan="3">&nbsp;<b>${Address}</b></td>
		</tr>
		<tr>
			<td height="25" align="right" >标题：</td>
			<td height="25" colspan="3">
            <input name="Title" type="text" value="${Title}" class="inputText" id="Title" size="60" readonly/>
            </td>
		</tr>
        <tr>
			<td height="25" align="right" >内容：</td>
			<td height="25" colspan="3">
            <textarea id="Content" name="Content" style="width:320px; height:100px;" readonly>${Content}</textarea>
            </td>
		</tr>
        <tr>
			<td height="25" align="right" >回复：</td>
			<td height="25" colspan="3">
            <textarea id="ReplyContent" name="ReplyContent" style="width:320px; height:50px;">${ReplyContent}</textarea>
            </td>
		</tr>
        <tr>
			<td height="25" align="right" >附件：</td>
			<td height="25" colspan="3">&nbsp;<a href="#;" title="${Prop1}" onClick="download(${ID});">${Prop1}</a></td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
