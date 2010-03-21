<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
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
	diag.Width = 700;
	diag.Height = 440;
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
	}else if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageBrowse")){
	 	$DW.Tab.getCurrentTab().contentWindow.onReturnBack();
	}
}

function onReturnBack(returnID){
	var dc = new DataCollection();
	dc.add("PicID",returnID);
	Server.sendRequest("com.zving.cms.site.Catalog.getPicSrc",dc,function(response){
		$("PicSrc").value = response.get("picSrc");
		$S("ImagePath",response.get("ImagePath"));
	})
}

function onUploadCompleted(returnID){
	onReturnBack(returnID);
}

function changeKeywordFlag(){
//	if($("KeywordFlag").checked){
//		$("spanSetting").style.display="";
//	}else{
//		$("spanSetting").style.display="none";
//	}
} 
</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.site.Catalog.initDialog">
	<body class="dialogBody">
	<form id="form2"><input type="hidden" id="ID" value="${ID}" />
	<input type="hidden" id="ParentID" value="${ParentID}" /> <input
		type="hidden" id="SiteID" value="${SiteID}" /> <input type="hidden"
		id="Level" value="${Level}" /> <input type="hidden" id="Type"
		value="${Type}"> <input type="hidden" id="ChildCount"
		value="${ChildCount}" /> <input type="hidden" id="IsLeaf"
		value="${IsLeaf}" />
	<table width="740" align="center" cellpadding="4" cellspacing="0">
		<tr valign="top">
			<td width="343" align=center>

			<fieldset><legend> 基本属性 </legend>
			<table width=96%>
				<tr>
					<td width="31%" height="30" align="right">上级结点：</td>
					<td colspan="2" align="left"><input disabled name="ParentName"
						type="text" class="input1" id="ParentName" value="${ParentName}"
						size="30" /></td>
				</tr>
				<tr>
					<td width="31%" height="30" align="right">栏目名称：</td>
					<td colspan="2" align="left"><input name="Name" type="text"
						class="input1" id="Name" value="${Name}" size="30"
						onChange="setAlias();" onBlur="setAlias();" verify="名称|NotNull" /></td>
				</tr>
				<tr id="tr_InnerCode" style="display:none">
					<td height="30" align="right" nowrap>栏目内部编码：</td>
					<td colspan="2" align="left"><input name="InnerCode"
						type="text" class="input1" id="InnerCode" value="${InnerCode}"
						size="30"/ ></td>
				</tr>
				<tr>
					<td height="30" align="right">栏目英文名：</td>
					<td colspan="2" align="left"><input name="Alias" type="text"
						class="input1" id="Alias" value="${Alias}" size="30"
						verify="栏目英文名|NotNull&&请输入长度在2-10位之间的字符|Regex=^[\w]{2,10}$" /></td>
				</tr>
				<tr>
					<td height="30" align="right">栏目URL：</td>
					<td align="left"><input name="URL" type="text" class="input1"
						id="URL" value="${URL}" size="40" /></td>
				</tr>
				<!--tr>
      <td  align="right" >栏目类别：</td>
      <td  colspan="2" ><z:select id="Type"  style="width:137px;" > ${optionCatalogType}</z:select></td>
    </tr-->
				<!--tr>
      <td  align="right" >引导图：</td>
      <td>
		  <input name="PicSrc" value="${PicSrc}" type="text" id="PicSrc" size="30" />
		  <input name="ImagePath" value="${ImagePath}" type="hidden" id="ImagePath" size=8 /> 
		  <input name="button" type="button" onClick="imageBrowse();" value="..." />
	  </td>
	</tr-->
			</table>
			</fieldset>
			<fieldset><legend>扩展属性 </legend>
			<table width="100%" style="margin:3px auto">
				<!--<tr>
					<td width="31%" height="25" align="right">排序权值：</td>
					<td width="69%" align="left"><input name="OrderFlag"
						type="text" class="input1" id="OrderFlag" value="${OrderFlag}"
						size="30" /></td>
				</tr>  -->
				<tr>
					<td width="30%" height="30" align="right" nowrap>列表页项目数：</td>
					<td width="70%" align="left">
					<z:select name="ListPageSize" id="ListPageSize"
						style="width:137px;" value="${ListPageSize}"><span
						value="10">10</span> <span value="20">20</span> <span value="30">30</span>
					</z:select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">是否开放显示：</td>
					<td align="left">${PublishFlag}</td>
				</tr>
				<tr>
					<td height="30" align="right">是否单页栏目：</td>
					<td align="left">${SingleFlag}</td>
				</tr>
                <tr>
					<td height="30" align="right">是否允许投稿：</td>
					<td align="left">${AllowContribute}</td>
				</tr>
				<tr  style="display:none">
					<td height="30" align="right">排序字段：</td>
					<td align="left"><input name="OrderColumn" type="text"
						class="input1" id="OrderColumn" value="${OrderColumn}" size="30" /></td>
				</tr>
				<tr>
					<td height="30" align="right">关键词：</td>
					<td align="left">	
						<input type="hidden" id="hKeywordFlag" value="${KeywordFlag}">
						<label for="KeywordFlag">
						<input name="KeywordFlag" type="checkbox" id="KeywordFlag" value="Y" onclick="changeKeywordFlag()">抽取
						</label>
						<!--<span id="spanSetting" style="display:none">关联分类：
							<input name="KeywordSetting"  id="KeywordSetting" value="${KeywordSetting}" size="16"/>
						</span> -->
						</td>
				</tr>
			</table>
			</fieldset>
			</td>
			<td width="379" align=center>
			<fieldset><legend>模板及命名 </legend>
			<table width=100%>
				<tr>
					<td height="30" align="right">首页模板：</td>
				  <td width="72%" align="left"><input name="IndexTemplate" type="text"
						class="input1" id="IndexTemplate" value="${IndexTemplate}"
						size="30" /> <input type="button" name="Submit" value="..."
						onClick="browse('IndexTemplate')"></td>
				</tr>
				<tr>
					<td width="28%" height="30" align="right">列表页模板：</td>
					<td align="left"><input name="ListTemplate" type="text"
						class="input1" id="ListTemplate" value="${ListTemplate}" size="30">
					<input type="button" onClick="browse('ListTemplate');" value="..." /></td>
				</tr>
				<!--tr>
            <td height="30"  align="right" nowrap>列表页命名规则：</td>
            <td  align="left"><input name="ListNameRule" type="text"  class="input1" id="ListNameRule" value="${ListNameRule}" size="30"/></td>
          </tr-->
				<tr>
					<td height="30" align="right">详细页模板：</td>
					<td align="left"><input name="DetailTemplate" type="text"
						class="input1" id="DetailTemplate" value="${DetailTemplate}"
						size="30" /> <input type="button" class="input2"
						onClick="browse('DetailTemplate');" value="..." /></td>
				</tr>
				<tr>
					<td height="30" align="right">详细页命名规则：</td>
					<td align="left"><input name="DetailNameRule" type="text"
						class="input1" id="DetailNameRule" value="${DetailNameRule}"
						size="30" /></td>
				</tr>
				<tr>
					<td height="30" align="right">Rss模板：</td>
					<td align="left"><input name="RssTemplate" type="text"
						class="input1" id="RssTemplate" value="${RssTemplate}" size="30" />
					<input type="button" class="input2"
						onClick="browse('RssTemplate');" value="..." /></td>
				</tr>
				<tr>
					<td height="30" align="right">模板沿用：</td>
					<td align="left"><z:select id="Extend" style="width:150px;">
						<span value="1">仅本栏目</span>
						<span value="2">所有子栏目沿用相同设置</span>
						<span value="3">所有栏目沿用相同设置</span>
					</z:select></td>
				</tr>
			
			</table>
			</fieldset>
			<fieldset><legend>工作流 </legend>
			<table width=100%>
				<tr>
					<td width="31%" height="30" align="right">工作流：</td>
					<td width="69%" align="left">
					<z:select id="Workflow" style="width:150px;"> ${Workflow} </z:select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
					<div style="text-align:right;" nowrap>工作流沿用：</div>
					</td>
					<td align="left"><z:select id="WorkFlowExtend"
						style="width:150px;">
						<span value="1">仅本栏目</span>
						<span value="2">所有子栏目沿用相同设置</span>
						<span value="3">所有栏目沿用相同设置</span>
					</z:select></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</form>
	</body>
	</z:init>
</html>
