<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.site.PageBlock.init">
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<title></title>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="../Framework/Main.js"></script>
	<script>
Page.onLoad(function(){
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Title",$V("Title"));
		dr.set("URL",$V("URL"));
		return true;
	}
});
function addItem(ele){
	var index = ele.parentElement.parentElement.rowIndex;
	DataGrid.insertRow($("dg1"),index,true);
}

function addItemValue(title,url){
	var row=$("tableItems").insertRow(1);
	row.insertCell(0).innerHTML = "<img name=\"addimg\" src=\"../Icons/icon403a20.gif\" onclick=\"addItem(this);\"/><img name=\"delimg\" src=\"../Icons/icon403a19.gif\" onclick=\"delItem(this);\"/>";
	row.insertCell(1).innerHTML = "<input name=\"ItemTitle\" type=\"text\"  value='"+title+"' size=30>";
	row.insertCell(2).innerHTML = "<input name=\"ItemURL\" type=\"text\"  value='"+url+"' > <input name=\"SelectArticle\" type=\"button\" class=\"input2\" value=\"...\" onclick='docListDialog()'>";
  Effect.initStyle($("tableItems"));
}

function delItem(ele){
	var index = ele.parentElement.parentElement.rowIndex;
	DataGrid.deleteRow($("dg1"),index);
}


function goBack(params){
	alert(params);
}

function docListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 800;
	diag.Height = 494;
	diag.Title ="浏览文章";
	diag.URL = "Document/DocListDialog.jsp";

	diag.OKEvent = getDocList;
	diag.show();
}

function getDocList(){
	var dt = $DW.DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请选择文档！");
		return;
	}
	for(var i=0;i<drs.length;i++){
		var dr = drs[i];
		addItemValue(dr.get("Title"),dr.get("Link"));
	}
	$D.close();
}

</script>

	</head>
	<body>
	<form id="form2"><z:datagrid id="dg1"
		method="com.zving.cms.site.PageBlock.blockItemDataBind" size="15"
		page="false">
		<table width=95% cellpadding="2" cellspacing="0" id="tableItems"
			class="dataTable">
			<tr ztype="head" class="dataTableHead">
				<td width="6%" height="30" ztype="RowNo" drag="true"><img
					src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
				<td width="10%"></td>
				<td width="40%"><strong>标题:</strong></td>
				<td width="34%"><strong>地址:</strong></td>
				<td width="10%" ztype="edit"><strong>编辑</strong></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><img name="addimg" src="../Icons/icon403a20.gif"
					onClick="addItem(this);" /><img name="delimg"
					src="../Icons/icon403a19.gif" onClick="delItem(this);" /></td>
				<td>${Title}</td>
				<td>${URL}</td>
				<td></td>
			</tr>

			<tr ztype="edit">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input name="Keyword" type="text" class="input1" id="Title"
					value="${Title}" size="35"></td>
				<td><input name="Keyword" type="text" class="input1" id="URL"
					value="${URL}" size="25"></td>
				<td></td>
			</tr>
		</table>
	</z:datagrid></form>
	<p>&nbsp;</p>
	</body>
	</html>
</z:init>
