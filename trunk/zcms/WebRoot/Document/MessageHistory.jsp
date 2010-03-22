<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function messageDetail(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请选择需要查看的消息！");
		return;
	}
	var diag =new Dialog("diag3");
	diag.Width = 500;
	diag.Height = 250;
	diag.Title = "查看消息";
	diag.URL = "Document/MessageDetailDialog.jsp?ID="+arr[0]+"&Type=history";
	diag.ShowButtonRow = false;
	diag.show();
	DataGrid.loadData("dg1");
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6">
			<tr>
				<td valign="middle"><img
					src="../Icons/icon018a16.gif" align="absmiddle" /><b>已发消息</b></td>
			</tr>
			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.document.Message.historyDataBind" size="10">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead ">
							<td width="2%">&nbsp;</td>
							<td width="4%" ztype="selector" field="id">&nbsp;</td>
							<td width="17%">消息标题</td>
							<td width="11%" sortField="readflag" direction="ASC">已读</td>
							<td width="30%">消息内容</td>
							<td width="15%">接收人</td>
							<td width="21%" sortField="addtime" direction="DESC"><strong>发送时间</strong></td>
						</tr>
						<tr onDblClick="messageDetail();">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${Subject}</td>
							<td><font color="${Color}">${ReadFlagStr}</font></td>
							<td>${Content}</td>
							<td>${ToUser}</td>
							<td>${addTime}</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="8" align="center">${PageBar}</td>
						</tr>
					</table>
				</z:datagrid></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
