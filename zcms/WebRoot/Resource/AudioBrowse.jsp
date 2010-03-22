<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片库</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function search(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Search","Search");
	DataGrid.setParam("dg1","_CatalogID",$V("CatalogID"));
	DataGrid.setParam("dg1","Name",$V("Name"));
	DataGrid.setParam("dg1","Info",$V("Info"));
	DataGrid.loadData("dg1");
}

function onReturnBack(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要返回的音频！");
		return;
	}
	if(window.parent.Parent.onReturnBack){
		window.parent.Parent.onReturnBack(arr.join());
	}
	window.parent.Dialog.close();
}

</script>
</head>
<body oncontextmenu="return false;">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td>
		<table width="100%" cellpadding="2" cellspacing="0">
			<tr>
				<td width="20%" align="left"><label> 所属分类:<z:select
					id="CatalogID" listWidth="200" listHeight="300"
					listURL="Resource/AudioLibSelectList.jsp"> </z:select> </label></td>
				<td width="6%">名称：</td>
				<td width="16%"><input name="Name" id="Name" type="text"
					value=""></td>
				<td width="8%">描述：</td>
				<td width="17%"><input name="Info" id="Info" type="text"
					value=""></td>
				<td width="20%"><input type="button" name="Submit" value="查询"
					onClick="search()"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td style="padding:0px 5px;"><z:datagrid id="dg1"
			method="com.zving.cms.resource.AudioLib.dg1DataBindBrowse" size="10">
			<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr ztype="head" class="dataTableHead">
					<td width="5%" ztype="RowNo">序号</td>
					<td width="5%" ztype="selector" field="ID">&nbsp;</td>
					<td width="22%"><b>音频名称</b></td>
					<td width="33%"><b>音频描述</b></td>
					<td width="8%"><b>音频大小</b></td>
					<td width="13%"><b>上传用户</b></td>
					<td width="14%"><b>上传时间</b></td>
				</tr>
				<tr style1="background-color:#FFFFFF"
					style2="background-color:#F9FBFC">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><a href=# onClick="edit('${ID}');">${Name}</a></td>
					<td>${Info}</td>
					<td>${Size}</td>
					<td>${AddUser}</td>
					<td>${AddTime}</td>
				</tr>
				<tr ztype="pagebar">
					<td colspan="8">${PageBar}</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>
