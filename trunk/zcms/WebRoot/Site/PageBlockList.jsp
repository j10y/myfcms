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
function addItem(ele){
	var row=$("tableItems").insertRow(ele.parentElement.parentElement.rowIndex+1);
	row.insertCell(0).innerHTML = "<img name=\"addimg\" src=\"../Icons/icon403a20.gif\" onclick=\"addItem(this);\"/><img name=\"delimg\" src=\"../Icons/icon403a19.gif\" onclick=\"delItem(this);\"/>";
	row.insertCell(1).innerHTML = "<input name=\"ItemTitle\" type=\"text\" size=30>";
	row.insertCell(2).innerHTML = "<input name=\"ItemURL\" type=\"text\"  size=30>";
  //Effect.initStyle($("tableItems"));
}

function addItemValue(title,url){
	var row=$("tableItems").insertRow(1);
	row.insertCell(0).innerHTML = "<img name=\"addimg\" src=\"../Icons/icon403a20.gif\" onclick=\"addItem(this);\"/><img name=\"delimg\" src=\"../Icons/icon403a19.gif\" onclick=\"delItem(this);\"/>";
	row.insertCell(1).innerHTML = "<input name=\"ItemTitle\" type=\"text\"  value='"+title+"' size=30>";
	row.insertCell(2).innerHTML = "<input name=\"ItemURL\" type=\"text\"  value='"+url+"'  size=30>";
  //Effect.initStyle($("tableItems"));
}

function delItem(ele){
	if($("tableItems").rows.length=="2"){
		alert("还剩最后一行，不能删除。");return;
	}
	$("tableItems").deleteRow(ele.parentElement.parentElement.rowIndex);
}


function goBack(params){
	alert(params);
}

function docListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 780;
	diag.Height = 460;
	diag.Title ="浏览文章";
	diag.URL = "Document/DocListDialog.jsp";
	diag.OKEvent = getDocList;
	diag.show();
}

function bbsListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 820;
	diag.Height = 460;
	diag.Title ="浏览论坛";
	diag.URL = "DataService/BBSListDialog.jsp";

	diag.OKEvent = getDocList;
	diag.show();
}

function blogListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 820;
	diag.Height = 460;
	diag.Title ="浏览博客";
	diag.URL = "DataService/BlogListDialog.jsp";

	diag.OKEvent = getDocList;
	diag.show();
}

function cmsListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 820;
	diag.Height = 460;
	diag.Title ="浏览博客";
	diag.URL = "DataService/CMSListDialog.jsp";

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
	for(var i=drs.length;i>0;i--){
		var dr = drs[i-1];
		addItemValue(dr.get("Title"),dr.get("Link"));
	}
	$D.close();
}

</script>

	</head>
	<body>
	<div id="content">
	<form id="form2">

	<div id="tabContent2">
	<div style="overflow: auto;width:99%;">
	<table width=100%>
		<tr>
			<td><z:tbutton onClick="docListDialog()">
				<img src="../Icons/icon018a2.gif" />栏目文章</z:tbutton></td>
		</tr>
	</table>
	<table width=100% cellpadding="2" cellspacing="0" id="tableItems"
		class="dataTable">
		<tr ztype="head" class="dataTableHead">
			<td width="61">操作</td>
			<td width="184"><strong>标题:</strong></td>
			<td width="165"><strong>URL:</strong></td>
		</tr>
		<%
		String sql = "";
		String id = request.getParameter("ID");
		
		if (!"".equals(id) && id != null) {
			sql = "select title,URL from zcpageblockItem where blockid="+id;
		}
		else {
			sql = "select '' as title,'' as URL from zcpageblockItem";
		}
		DataTable dt = new QueryBuilder(sql).executePagedDataTable(1,0);
		if(dt.getRowCount()<1){
		%>
		<tr>
			<td height="23"><img name="addimg" src="../Icons/icon403a20.gif"
				onClick="addItem(this);" /><img name="delimg"
				src="../Icons/icon403a19.gif" onClick="delItem(this);" /></td>

			<td><input name="ItemTitle" type="text" class="input1" value=""
				size="30" /></td>

			<td><input name="ItemURL" value="" type="text" class="input1"
				size=30 /></td>
		</tr>
		<%
		}
		for(int i = 0 ;i<dt.getRowCount();i++){
		DataRow dr = dt.getDataRow(i);
		%>
		<tr>
			<td height="23"><img name="addimg" src="../Icons/icon403a20.gif"
				onClick="addItem(this);" /><img name="delimg"
				src="../Icons/icon403a19.gif" onClick="delItem(this);" /></td>

			<td><input name="ItemTitle" type="text" class="input1"
				value="<%=dr.get("Title")%>" size="30" /></td>

			<td><input name="ItemURL" value="<%=dr.get("URL")%>" type="text"
				class="input1" size=30 /></td>
		</tr>
		<%
		}
		%>
	</table>
	</div>
	</div>
	</form>
	</div>
	<p>&nbsp;</p>
	</body>
	</html>
</z:init>
