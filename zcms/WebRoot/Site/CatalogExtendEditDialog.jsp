<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script src="../Editor/fckeditor.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
Page.onLoad(changeInputType);

var input = "1";
var imageInput = "7";
var htmlinput  = "8";

function changeInputType(){
	var inputType = $V("InputType");
	if(input==inputType){
		$("tr_input").show();
		$("tr_imageInput").hide();
		$("tr_html").hide();
	}else if(imageInput==inputType){
		$("tr_input").hide();
		$("tr_imageInput").show();
		$("tr_html").hide();
	}else if(htmlinput==inputType){
		$("tr_input").hide();
		$("tr_imageInput").hide();
		$("tr_html").show();
	}
}

function setSpell(){
	if($V("Code") == ""){
	  $S("Code",getSpell($V("Name")));
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

function ok(){
	if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageUpload")){
 		$DW.Tab.getCurrentTab().contentWindow.upload();
	}
	else if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageBrowse")){
	 	$DW.Tab.getCurrentTab().contentWindow.onReturnBack();
	}
}
function onUploadCompleted(returnID){
	onReturnBack(returnID);
}

function onReturnBack(returnID){
	var dc = new DataCollection();
	dc.add("PicID",returnID);
	Server.sendRequest("com.zving.cms.site.CatalogExtend.getPicSrc",dc,function(response){
		$("Pic").src = response.get("picSrc");
		$("ImagePath").value = response.get("ImagePath");
	})
}

var editor;
function getContent(){
	editor = FCKeditorAPI.GetInstance('Content');
    return editor.GetXHTML(false);	
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.site.CatalogExtend.initDialog">
	<form id="form2">
	<table width="450" height="100" border="0" align="center"
		cellpadding="4" cellspacing="" bordercolor="#DEDEDC"
		style="border-collapse:collapse;">
		<tr>
			<td width="114" height="10"></td>
			<td><input name="ColumnID" id="ColumnID" type="hidden"
				value="${ColumnID}" /> <input name="ValueID" id="ValueID"
				type="hidden" value="${ValueID}" /></td>
		</tr>
		<tr>
			<td align="right">属性类型：</td>
			<td><z:select id="InputType" onChange="changeInputType()"
				disabled="true">
      ${InputType}
      </z:select></td>
		</tr>
		<tr>
			<td width="114" align="right">属性名称：</td>
			<td width="320"><input name="Name" id="Name" type="text"
				verify="属性名字|NotNull" value="${Name}" onBlur="setSpell();" /></td>
		</tr>
		<tr>
			<td align="right">属性代码：</td>
			<td><input name="Code" id="Code" type="text"
				verify="属性名称|NotNull" value="${Code}" onBlur="setSpell();" /></td>
		</tr>
		<tr id="tr_input">
			<td align="right">属性内容：</td>
			<td><textarea id="TextValue" name="TextValue" cols="43"
				rows="5" verify="属性内容|NotNull">${Text}</textarea></td>
		</tr>
		<tr id="tr_imageInput">
			<td align="right">属性内容：</td>
			<td align="left" width="320"><input name="ImagePath"
				value="${ImagePath}" type="hidden" id="ImagePath" size=8 /> <img
				src="${ImageFile}" name="Pic" width="100" height="75" id="Pic">
			<input name="button" type="button" onClick="imageBrowse();"
				value="浏览..." /></td>
		</tr>
        <tr id="tr_html">
			<td align="right">属性内容：</td>
			<td>
            <div  id="Toolbar" style="height:26px; width:95%"></div>
            <textarea id="_Content" name="_Content" style=" display:none;">${Content}</textarea>
            <script type="text/javascript">
				var sBasePath = <%=Config.getContextPath()%>+"Editor/" ;
				var oFCKeditor = new FCKeditor( 'Content' ) ;
				oFCKeditor.BasePath	= sBasePath ;
				oFCKeditor.ToolbarSet="Basic"
				oFCKeditor.Width  = '95%';
				oFCKeditor.Height = 90;
				oFCKeditor.Config['EditorAreaCSS'] = '${CssPath}';
				oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:Toolbar' ;
				oFCKeditor.Value = $V("_Content");
				oFCKeditor.Create();
			</script>            
            </td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
