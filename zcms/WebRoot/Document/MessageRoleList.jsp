<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function doSearch(){
	var name = "";
	if ($V("SearchRoleName") != "请输入角色代码或角色名") {
		name = $V("SearchRoleName").trim();
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchRoleName",name);
	DataGrid.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchRoleName'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	if ($V("SearchRoleName") == "请输入角色代码或角色名") {
		$S("SearchRoleName","");
	}
}
</script>
</head>
<body onContextMenu="return false;">
<table width="380" align="center" cellpadding="2" cellspacing="0"
	border="0">
	<tr>
		<td>
		<div style="float: right; white-space: nowrap; padding-right:5px;">
		<input name="SearchRoleName" type="text" id="SearchRoleName"
			value="请输入角色代码或角色名" onFocus="delKeyWord();" style="width:150px">
		<input type="button" name="Submitbutton" id="Submitbutton" value="查询"
			onClick="doSearch()"></div>
		</td>
	</tr>
	<tr>
		<td
			style="padding-top:4px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
		<z:datagrid id="dg1"
			method="com.zving.cms.document.Message.bindRoleList" size="10">
			<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr ztype="head" class="dataTableHead">
					<td width="8%" ztype="selector" field="RoleCode">&nbsp;</td>
					<td width="29%"><b>角色代码</b></td>
					<td width="31%"><b>角色名</b></td>
					<td width="32%"><b>用户数</b></td>
				</tr>
				<tr style1="background-color:#FFFFFF"
					style2="background-color:#F9FBFC">
					<td>&nbsp;</td>
					<td>${RoleCode}</td>
					<td>${RoleName}</td>
					<td>${UserCount}</td>
				</tr>
				<tr ztype="pagebar">
					<td colspan="6" align="left">${PageBar}</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>
