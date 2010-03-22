<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>

<script type="text/javascript">
function selectIcon(){
	var diag = new Dialog("Diag2");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "选择图标";
	diag.URL = "Platform/Icon.jsp";
	diag.onLoad = function(){
	};
	diag.show();
}

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
	$S("CoverImage",returnID);
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
<z:init method="com.zving.cms.site.Magazine.initDialog">
	<body class="dialogBody">
	<form id="form2"><input name="MagazineID" type="hidden"
		id="MagazineID" value="${ID}" /> <input name="Type" type="hidden"
		id="Type" value="3" /> <input name="SiteID" type="hidden" id="SiteID"
		value="${SiteID}" /> <input name="ChildCount" type="hidden"
		id="ChildCount" value="${ChildCount}" />
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
		<td height="10" align=center>
		<table width=100% cellpadding="2" cellspacing="4">
			<tr>
				<td align="right"></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right">期刊名称：</td>
				<td align="left"><input name="Name" type="text" class="input1"
					id="Name" value="${Name}" size="30" onChange="setAlias();"
					onBlur="setAlias();" verify="期刊名称|NotNull" /></td>
			</tr>
			<tr>
				<td align="right">英文名称：</td>
				<td align="left"><input name="Alias" type="text" class="input1"
					id="Alias" value="${Alias}" size="30" verify="期刊英文名|NotNull" /></td>
			</tr>
			<tr>
				<td align="right">期刊封面:</td>
				<td align="left"><input name="CoverImage" value="${CoverImage}"
					type="hidden" id="CoverImage" /> <img src="${PicSrc}" name="Pic"
					width="100" height="75" id="Pic"> <input type="button"
					name="Submit" value="浏览..." onClick="imageBrowse()"></td>
			</tr>
            <tr>
				<td align=right>期刊简介:</td>
				<td><TEXTAREA name=Memo cols=50 rows=5 id="Memo"></TEXTAREA></td>
			</tr>
			<tr>
				<td align="right" width="31%">封面页模板:</td>
				<td align="left"><input name="CoverTemplate" type="text"
					class="input1" id="CoverTemplate" value="${CoverTemplate}"
					size="30"> <input type="button" class="input2"
					onClick="browse('CoverTemplate');" value="浏览..." /></td>
			</tr>
			<tr>
				<td align="right">是否开放:</td>
				<td align="left"><span class="tdgrey2"> <input
					type="radio" name="UseFlag" value="0" id="UseFlag0"> <label
					for="UseFlag0">停用</label> <input type="radio" name="UseFlag"
					value="1" id="UseFlag1" checked> <label for="UseFlag1">启用</label>
				</span></td>
			</tr>
		</table>
		</td>
		</tr>
	</table>
	</form>
	</z:init>
	</body>
</html>

