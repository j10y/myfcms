<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
Page.onLoad(function(){
	var dc = new DataCollection();
	dc.add("PicID",$V("PaperImage"));
	Server.sendRequest("com.zving.cms.site.NewspaperIssue.getPicSrc",dc,function(response){
		$("Pic").src = response.get("picSrc");
	})
});
function afterSelectIcon(){
	$("Logo").src = $DW.Icon;
	$D.close();
}

function goBack(params){
	alert(params)
}

function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;	diag.Height = 450;
	diag.Title ="浏览列表页模板";
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

function setAlias(){
	if($V("Alias") == ""){
	  $S("Alias",getSpell($V("Name"),true));
  }
}

function imageBrowse(){
	var diag = new Dialog("Diag4");
	diag.Width = 780;
	diag.Height = 460;
	diag.Title ="浏览图片库";
	diag.URL = "Resource/ImageLibDialog.jsp?Search=Search&SelectType=radio";
	diag.OKEvent = ok;
	diag.show();
}

function fileBrowse(){
	var diag = new Dialog("Diag4");
	diag.Width = 820;	diag.Height = 500;
	diag.Title ="浏览图片库";

diag.URL = "Resource/AttachmentLibDialog.jsp?Search=Search&SelectType=radio";
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
	$S("PaperImage",returnID);
	var dc = new DataCollection();
	dc.add("PicID",returnID);
	Server.sendRequest("com.zving.cms.site.MagazineIssue.getPicSrc",dc,function(response){
		$("Pic").src = response.get("picSrc");
	})
}

function onUploadCompleted(returnID){
	onReturnBack(returnID);
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2"><input type="hidden" id="ID">
<table width="100%" border=0 cellpadding=2 cellspacing=3>
	<tbody>
		<tr>
			<td align=right height="5"></td>
			<td></td>
		</tr>
		<tr>
			<td align=right>版面号:</td>
			<td><input name="PageNo" id="PageNo" type="text" class="input1"
				size=20 verify="版面号|NotNull" /></td>
		</tr>
		<tr>
			<td align=right>版面标题:</td>
			<td><input name="Title" type="text" id="Title" class="input1"
				size=40 verify="版面标题|NotNull" /></td>
		</tr>
		<tr>
			<td align=right>版面图片:</td>
			<td><input name="PaperImage" value="${PaperImage}"
				type="hidden" id="PaperImage" /> <img src="${PicSrc}" name="Pic"
				width="100" height="75" id="Pic"> <input type="button"
				name="Submit" value="浏览..." onClick="imageBrowse()"></td>
		</tr>

		<tr>
			<td align=right>版面PDF:</td>
			<td><input name="PDFFile" type="text" id="PDFFile" size="30" />
			<input type="button" name="Submit" value="浏览..."
				onClick="fileBrowse()"></td>
		</tr>
		<tr>
			<td align=right>列表页模板:</td>
			<td><input name="ListTemplate" type="text" class="input1"
				id="ListTemplate" size="30"> <input type="button"
				class="input2" onClick="browse('ListTemplate');" value="浏览..." /></td>
		</tr>
		<tr>
			<td align=right>详细页模板:</td>
			<td><input name="DetailTemplate" type="text" class="input1"
				id="DetailTemplate" size="30"> <input type="button"
				class="input2" onClick="browse('DetailTemplate');" value="浏览..." /></td>
		</tr>
		<tr>
			<td align=right>简介:</td>
			<td><textarea name=Memo cols=50 rows=5 id="Memo"></textarea></td>
		</tr>
		<tr>
		</tr>
	</tbody>
</table>
</form>
</body>
</html>
