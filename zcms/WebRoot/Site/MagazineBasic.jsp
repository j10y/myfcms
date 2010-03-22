<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>栏目</title>
<script src="../Framework/Main.js"></script>
<script> 
//鼠标点击当前页面时，隐藏右键菜单
var iFrame =parent.parent;
Page.onClick(function(){
	var div = iFrame.$("_DivContextMenu")
	if(div){
			$E.hide(div);
	}
});

var topFrame = window.parent;
function addIssue(){
	topFrame.addIssue();	
}

function publish(){
	topFrame.publish();	
}

function publishIndex(){
	topFrame.publishIndex();	
}

function del(){
	topFrame.del();
}

function batchAdd(){
	topFrame.batchAdd();
}

function addMag(){
	topFrame.addMag();
}

function delMag(){
	topFrame.delMag();
}

function editMag(){
	topFrame.editMag($V("ID"));
}



</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.site.Magazine.init">
	<div style="padding:2px">
	<table width="100%" cellpadding='0' cellspacing='0'>
		<tr>
			<td><z:tbutton onClick="addIssue();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />新建期号</z:tbutton> <z:tbutton
				onClick="publish();">
				<img src="../Icons/icon002a1.gif" />发布当期期刊</z:tbutton> <z:tbutton
				onClick="editMag();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />编辑</z:tbutton> <z:tbutton
				onClick="delMag();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />删除</z:tbutton></td>
		</tr>
	</table>
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
		<tr class="dataTableHead">
			<td width="31"></td>
			<td width="164" height="10"><b>属性</b></td>
			<td><b>值</b></td>
		</tr>
		<tr id="tr_ID" style="display:none">
			<td>&nbsp;</td>
			<td>ID：</td>
			<td>${ID} <input type="hidden" id="ID" value="${ID}"><input
				type="hidden" id="Name" value="${Name}"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>期刊名称：</td>
			<td>${Name} &nbsp;&nbsp;<a
				href="Preview.jsp?Type=1&CatalogID=${ID}" target="_blank">预览</a></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>英文名称：</td>
			<td>${Alias}</td>
		</tr>
		<tr>
			<!--td>&nbsp;</td>
      <td  align="right" >封面图片：</td>
      <td><img src="./${CoverImage}" align="absmiddle"/>&nbsp;</td>
    </tr-->
		<tr>
			<td>&nbsp;</td>
			<td>封面模板：</td>
			<td>${CoverTemplate}&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>当前期号：</td>
			<td>${CurrentYear}年第${CurrentPeriodNum}期</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>总期数：</td>
			<td>${Total}&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>是否开放：</td>
			<td>${OpenFlag}&nbsp;</td>
		</tr>
	</table>
	</form>
	</div>
</z:init>
</body>
</html>
