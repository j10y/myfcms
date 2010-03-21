<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">

function goBack(params){
	alert(params)
}
/**
function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;	
	diag.Height = 440;
	diag.Title ="浏览模板";
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
*/

function imageBrowse(){
	var diag = new Dialog("Diag4");
	diag.Width = 780;
	diag.Height = 460;
	diag.Title ="浏览图片库";
	diag.URL = "Resource/ImageLibDialogCover.jsp?Search=Search&SelectType=radio";
	diag.OKEvent = ok;
	diag.show();
}

function ok(){
	if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageUpload")){
 		$DW.Tab.getCurrentTab().contentWindow.upload();
	}
	else if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageBrowse")){
	 	$DW.Tab.getCurrentTab().contentWindow.onReturnBack();
	}
}

function onReturnBack(returnID){
	var dc = new DataCollection();
	dc.add("PicID",returnID);
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.getPicSrc",dc,function(response){
		$("Pic").src = response.get("picSrc");
		$("ImagePath").value = response.get("ImagePath");
	})
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="dialogBody">
	<z:init method="com.zving.cms.resource.AttachmentLib.initDialog">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td height="10"></td>
			<td colspan="2"></td>
		</tr>
		<tr id="tr_ID" style="display:none">
			<td align="right">附件ID：</td>
			<td colspan="2"><input name="ID" type="text" class="input1"
				id="ID" value="${ID}" /></td>
		</tr>
		<tr>
			<td align="right">附件名称：</td>
			<td colspan="2"><input name="Name" type="text" class="input1"
				id="Name" value="${Name}" verify="附件名称|NotNull" /></td>
		</tr>
		<tr>
			<td align="right">附件描述：</td>
			<td colspan="2"><input name="Info" type="text" class="input1"
				id="Info" value="${Info}" /></td>
		</tr>
		<tr>
			<td align="right">附件索引图片：</td>
			<td align="left" width="137"><input name="ImagePath"
				value="${ImagePath}" type="hidden" id="ImagePath" size=8 /> <img
				src="${PicSrc}" name="Pic" width="120" height="90" id="Pic"></td>
			<td align="left" valign="middle"><input name="button"
				type="button" onClick="imageBrowse();" value="浏览..." /></td>
		</tr>
		<!--
		<tr>
			<td align="right" width="31%">列表页模板:</td>
			<td colspan="2"><input name="ListTemplate" type="text"
				class="input1" id="ListTemplate" value="${ListTemplate}" size="30">
			<input type="button" class="input2" onClick="browse('ListTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right">图片详细页模板:</td>
			<td colspan="2"><input name="DetailTemplate" type="text"
				class="input1" id="DetailTemplate" value="${DetailTemplate}"
				size="30" /> <input type="button" class="input2"
				onClick="browse('DetailTemplate')" value="浏览..." /></td>
		</tr>
		  -->
	</table>
	</form>
	</z:init>
	</body>
</html>