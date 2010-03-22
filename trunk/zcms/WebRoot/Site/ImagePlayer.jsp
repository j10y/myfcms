<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html> 
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片播放器</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 800;
	diag.Height = 400;
	diag.Title = "新建"+this.document.title;
	diag.URL = "Site/ImagePlayerDialog.jsp?ImagePlayerID=";
	diag.ShowButtonRow = false;
	diag.show();
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("您确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.site.ImagePlayer.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData("dg1");
				}
			});
		});
	});
}

function edit(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要编辑的播放器！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = this.document.title;
	diag.URL = "Site/ImagePlayerDialog.jsp?ImagePlayerID="+arr[0];
	diag.ShowButtonRow = false;
	diag.show();
}
</script>
</head>
<body scroll="no">
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td valign="middle" class="blockTd"><img
					src="../Icons/icon039a1.gif" /> 播放器列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
					<img src="../Icons/icon039a2.gif" />新建</z:tbutton> <z:tbutton onClick="edit()">
					<img src="../Icons/icon039a4.gif" />编辑</z:tbutton> <z:tbutton onClick="del()">
					<img src="../Icons/icon039a3.gif" />删除</z:tbutton></td>
			</tr>
			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.site.ImagePlayer.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" ztype="RowNo">序号</td>
							<td width="5%" ztype="selector" field="id">&nbsp;</td>
							<td width="30%"><strong>名称</strong></td>
							<td width="30%"><strong>代码</strong></td>
							<td width="15%"><strong>高度</strong></td>
							<td width="15%"><strong>宽度</strong></td>
						</tr>
						<tr onDblClick="edit();">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${Name}</td>
							<td>${Code}</td>
							<td>${Height}px</td>
							<td>${Width}px</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="6" align="center">${PageBar}</td>
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
