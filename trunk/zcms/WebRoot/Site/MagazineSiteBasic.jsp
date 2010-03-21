<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>栏目</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
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
function add(){
	topFrame.add();	
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



</script>
</head>
<body>
<z:init method="com.zving.cms.site.CatalogSite.init">
	<div style="padding:2px">
	<table width="100%" cellpadding='0' cellspacing='0'>
		<tr>
			<td><z:tbutton onClick="addMag();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />新建期刊</z:tbutton> <z:tbutton
				onClick="batchAdd();">
				<img src="../Icons/icon002a9.gif" width="20" height="20" />批量新建</z:tbutton></td>
		</tr>
	</table>
	<form id="form2">
	<table width="100%" cellpadding="0" cellspacing="0" class="dataTable">
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
			<td>站点名称：</td>
			<td>${Name} &nbsp;&nbsp;<a
				href="Preview.jsp?Type=0&SiteID=${ID}" target="_blank">预览</a></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>英文名称：</td>
			<td>${Alias}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>站点URL：</td>
			<td>${URL}</td>
		</tr>
		<!--tr>
	  <td>&nbsp;</td>
      <td  align="right" >站点Logo：</td>
      <td><img src="./${LogoFileName}" align="absmiddle"/>&nbsp;</td>
    </tr-->
		<tr>
			<td>&nbsp;</td>
			<td>首页模板ID：</td>
			<td>${TemplateID}&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>排序权值：</td>
			<td>${OrderFlag}&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>留言板标志：</td>
			<td>${MessageBoardFlag}&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>频道数：</td>
			<td>${ChannelCount}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>期刊数：</td>
			<td>${MagzineCount}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>专题数：</td>
			<td>${SpecialCount}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>点击数：</td>
			<td>${HitCount}</td>
		</tr>
	</table>
	</form>
	</z:init>
	</div>
</body>
</html>

