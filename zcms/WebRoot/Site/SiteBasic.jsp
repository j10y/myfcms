<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
	//如果有DocList.LastCatalog则重定向到CatalogBasic.jsp
	CatalogSite.onRequestBegin(request, response);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>栏目</title>
<script src="../Framework/Main.js"></script>
<script> 
//鼠标点击当前页面时，隐藏右键菜单
var iFrame =parent.parent;

Page.onLoad(function(){
	setAllPriv("article",$E.getTopLevelWindow().$V("_SiteSelector"));
});

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

function preview(){
	
}

function addMagazine(){
}

function openEditor(path){
	topFrame.openEditor(path);
}

function preview(){
	topFrame.preview();
}

function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;
	diag.Height = 440;
	diag.Title ="选择首页模板";
	diag.URL = "Site/TemplateExplorer.jsp";
	goBack = function(params){
		$S(ele,params);
	}
	diag.OKEvent = afterSelect;
	diag.show();
}

function afterSelect(){
	$DW.onOK();
}

function changeIndexTemplate(){
	var indexTemplate=$V('IndexTemplate');
	if(!indexTemplate){
		Dialog.alert("请选择模板!");
		return;
	}
	var dc=new DataCollection();
	dc.add("IndexTemplate",indexTemplate);
	Server.sendRequest("com.zving.cms.site.CatalogSite.changeTemplate",dc,function(response){
		Dialog.alert(response.Message);
		if(response.Status==1){
			$('editLink').onclick=function(){openEditor(indexTemplate);}
		}
	});
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.site.CatalogSite.init">
	<div style="padding:2px">
	<table width="100%" cellpadding='4' cellspacing='0'>
		<tr>
			<td><z:tbutton id="BtnAdd" priv="article_manage"
				onClick="add();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />新建栏目</z:tbutton> <z:tbutton
				id="BtnBatchAdd" priv="article_manage" onClick="batchAdd();">
				<img src="../Icons/icon002a9.gif" width="20" height="20" />批量新建</z:tbutton> <z:tbutton
				id="BtnPublish" priv="article_manage" onClick="publish();">
				<img src="../Icons/icon002a1.gif" />发布全站</z:tbutton> <z:tbutton
				id="BtnPublishIndex" priv="article_manage" onClick="publishIndex();">
				<img src="../Icons/icon002a1.gif" />发布首页</z:tbutton> <z:tbutton id="BtnPublish"
				priv="article_manage" onClick="preview();">
				<img src="../Icons/icon403a3.gif" />预览</z:tbutton></td>
		</tr>
	</table>
	<form id="form2">
	<table width="600" border="1" cellpadding="3" cellspacing="0"
		bordercolor="#eeeeee">
		<tr>
			<td>站点名称：</td>
			<td>${Name} &nbsp;&nbsp;<a
				href="Preview.jsp?Type=0" target="_blank">预览</a> <input
				type="hidden" id="ID" value="${ID}"> <input type="hidden"
				id="Name" value="${Name}"></td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td>${Alias}</td>
		</tr>
		<tr>
			<td>站点URL：</td>
			<td>${URL}</td>
		</tr>
		<!--tr>
	  <td>&nbsp;</td>
      <td  align="right" >站点Logo：</td>
      <td><img src="./${LogoFileName}" align="absmiddle"/>&nbsp;</td>
    </tr-->
		<tr>
			<td>首页模板：</td>
			<td><input name="IndexTemplate" type="text" class="input1"
				id="IndexTemplate" value="${IndexTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择模板"
				onClick="browse('IndexTemplate')"> <input type="button"
				name="Submit" value="更新" onClick="changeIndexTemplate()">&nbsp;&nbsp;${edit}
			</td>
		</tr>
		<tr>
			<td>编辑器样式：</td>
			<td>${EditorCss}&nbsp;</td>
		</tr>
		<tr>
			<td>描述：</td>
			<td>${Info}&nbsp;</td>
		</tr>
		<!--tr>
	  <td>&nbsp;</td>
      <td  align="right" >频道数：</td>
      <td>${ChannelCount}</td>
    </tr>
    <tr>
	  <td>&nbsp;</td>
      <td  align="right" >期刊数：</td>
      <td>${MagzineCount}</td>
    </tr>
    <tr>
	  <td>&nbsp;</td>
      <td  align="right" >专题数：</td>
      <td>${SpecialCount}</td>
    </tr>
    <tr!-->
	</table>
	</form>
	</div>
</z:init>
</body>
</html>
