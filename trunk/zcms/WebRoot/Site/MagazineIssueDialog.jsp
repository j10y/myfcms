<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">

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
	diag.Width = 800;	diag.Height = 500;
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
	Server.sendRequest("com.zving.cms.site.MagazineIssue.getPicSrc",dc,function(response){
		$("Pic").src = response.get("picSrc");
		$S("CoverImage", response.get("CoverImage"));
	})
}

function onUploadCompleted(returnID){
	onReturnBack(returnID);
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.site.MagazineIssue.initDialog">
	<body class="dialogBody">
	<form id="form2"><input type="hidden" id="ID">

	<table width="100%" border=0 cellPadding=2 cellSpacing=3>
		<TBODY>
			<tr>
				<td align=right height="5"></td>
				<td></td>
			</tr>
			<tr>
				<td align=right>年号:</td>
				<td><input name="Year" id="Year" type="text" class="input1"
					size=20 verify="年号|NotNull&&Int" /></td>
			</tr>
			<tr>
				<td align=right>期号:</td>
				<td><input name="PeriodNum" type="text" id="PeriodNum"
					class="input1" size=20 verify="期号|NotNull&&Int" /></td>
			</tr>
			<tr>
				<td align=right>出版日期:</td>
				<td><input name="PublishDate" type="text" id="PublishDate"
					class="input1" ztype="Date" /></td>
			</tr>
			<tr>
				<td align=right>封面图片:</td>
				<td align=left><input name="CoverImage" value="${CoverImage}"
					type="hidden" id="CoverImage" /> <img src="${PicSrc}" name="Pic"
					id="Pic" height="90" width="120"> <input type="button" name="Submit" value="浏览..."
					onClick="imageBrowse()"></td>
			</tr>
			<tr>
				<td align=right>封面页模板:</td>
				<td><input name="CoverTemplate" type="text" class="input1"
					id="CoverTemplate" size="30"> <input type="button"
					class="input2" onClick="browse('CoverTemplate');" value="浏览..." /></td>
			</tr>
			<tr>
				<td align=right>本期简介:</td>
				<td><TEXTAREA name=Memo cols=50 rows=5 id="Memo"></TEXTAREA></td>
			</tr>
			<tr>
				<td align=right>上期栏目:</td>
				<td>${LastCatalog}</td>
			</tr>
			<tr>
			</tr>
		</TBODY>
	</table>
	</form>
	</body>
</z:init>
</html>
