<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
Page.onLoad(function(){
	var type = $V("Type");
	if (type == "image") {
		$("checkhidden").style.display="";
	} else {
		$("checkhidden").style.display="none";
	}
})

function imageBrowse(){
	var diag = new Dialog("Diag4");
	diag.Width = 780;
	diag.Height = 460;
	diag.Title ="浏览图片库";
	diag.URL = "Resource/ImageLibDialog.jsp?Search=Search&SelectType=radio";
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
	Server.sendRequest("com.zving.cms.site.Link.getPicSrc",dc,function(response){
		$("ImagePath").src = response.get("picSrc");
		$("ImageID").value = returnID;
	})
}

function onUploadCompleted(returnID){
	onReturnBack(returnID);
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>
<z:init method="com.zving.cms.site.Link.initDialog">
	<body class="dialogBody">
	<div style="display:none"><iframe name="formTarget"
		src="javascript:void(0)"></iframe></div>
	<form id="myform" method="post" target="formTarget"><input
		type="hidden" id="LinkGroupID" name="LinkGroupID"
		value="${LinkGroupID}"> <input type="hidden" id="Type"
		name="Type" value="${Type}"> <input name="ID" type="hidden"
		id="ID" value="${ID}" />
	<table width="100%" cellpadding="2" cellspacing="0" id="checktable">
		<tr>
			<td width="27%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">链接名称：</td>
			<td width="73%"><input name="Name" type="text" class="input1"
				id="Name" value="${Name}" verify="链接分类名称|NotNull"
				style="width:200px" /></td>
		</tr>
		<tr>
			<td align="right">链接URL：</td>
			<td><input name="URL" type="text" class="input1" id="URL"
				value="${URL}" verify="链接URL|NotNull" style="width:250px" /></td>
		</tr>
		<tr id="checkhidden" style="display:none">
			<td align="right">图片：</td>
			<td><input name="ImageID" type="hidden" id="ImageID" size=8 /> <img
				src="${ImageSrc}" name="ImagePath" id="ImagePath" width="100">
			<input name="button" type="button" onClick="imageBrowse();"
				value="浏览..." /></td>
		</tr>
	</table>
	</form>
	</body>
</z:init>
</html>
