<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.site.PageBlock.init">
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<title></title>
	<script src="../Framework/Main.js"></script>
	<script src="../Framework/Controls/Tabpage.js"></script>
	<script>
Page.onLoad(function(){
	var blockType = '${BlockType}';
	if(blockType==''){
		blockType = 1;
	}
	$NS("Type",blockType);
	if(blockType == "1" || blockType == "4"){
		parent.$("List").hide();
		parent.$("Content").hide();
  } else if(blockType == "2"){
  	parent.$("List").show();
		parent.$("Content").hide();
  }else if(blockType == "3"){
  	parent.$("List").hide();
		parent.$("Content").show();
  }
})
function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;
	diag.Height = 440;
	diag.Title ="浏览列表页模板";
	diag.URL = "Site/TemplateExplorer.jsp?SiteID=<%=Application.getCurrentSiteID()%>";
	goBack = function(params){
		$S(ele,params);
	}
	diag.show();
}

function displayOption(blockType){
	if(blockType==1 || blockType==4){
		parent.$("List").hide();
		parent.$("Content").hide();
		$("tr_template").style.display="";
		Verify.closeTip($("Template"));
		Verify.closeTip($("FileName"));
	}else if(blockType==2){
		$("tr_sort").style.display="none";
		parent.$("List").show();
		parent.$("Content").hide();
		$("tr_template").style.display="";
		Verify.closeTip($("Template"));
		Verify.closeTip($("FileName"));
	}else{
		$("tr_sort").style.display="none";
		parent.$("List").hide();
		parent.$("Content").show();
		$("tr_template").style.display="none";
		Verify.closeTip($("Template"));
		Verify.closeTip($("FileName"));
	}
}


function docListDialog(){
	var diag  = new Dialog("Diag3");
	diag.Width = 800;
	diag.Height = 494;
	diag.Title ="浏览文章";
	diag.URL = "Document/DocListDialog.jsp";
	diag.onLoad = function(){
	  $DW.$("button").onclick = getDocList;
	  
	};
	diag.show();
}

function getDocList(){
	var dt = $DW.DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		alert("请选择文档！");
		return;
	}
	for(var i=0;i<drs.length;i++){
		var dr = drs[i];
		addItemValue(dr.get("Title"),dr.get("Link"));
	}
	$D.close();
}

function openEditor(path){
	var url = Server.ContextPath+"Framework/Controls/FileEditDialog.jsp?fileName="+path;
	var width  = (screen.availWidth-10)+"px";
	var height = (screen.availHeight-50)+"px";
    var leftm  = 0;
    var topm   = 0;
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
    var w = window.open(url,"",args);
    if ( !w ){
		Dialog.alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
		return ;
	}
}

</script>

	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
	<div id="content">
	<form id="form2">
	<div id="tabContent0">
	<table width="100%" cellpadding="3" cellspacing="0">
		<tr>
			<td width="12" colspan="2" height="10"></td>
		</tr>
		<tr>
			<td align="right">名称：</td>
			<td><input name="Name" type="text" value="${Name}"
				class="input1" id="Name" size=35 verify="名称|NotNull" /> <input
				name="CatalogID" type="hidden" id="CatalogID" /> <input name="ID"
				type="hidden" id="ID" value="${ID}" /> <input name="SiteID"
				type="hidden" id="SiteID" /></td>
		</tr>
		<tr>
			<td height="12" align="right">代码：</td>
			<td height="12"><input name="Code" type="text" value="${Code}"
				class="input1" id="Code" size=35 verify="代码|NotNull" /></td>
		</tr>
		<tr>
			<td height="6" align="right">类型：</td>
			<td height="6"><label for="Type1"> <input name="Type"
				type="radio" id="Type1" value="1" checked onClick="displayOption(1)">
			模板生成 </label> <label for="Type2"> <input name="Type" type="radio"
				value="2" id="Type2" onClick="displayOption(2)"> 自选列表 </label> <label
				for="Type3"> <input name="Type" type="radio" value="3"
				id="Type3" onClick="displayOption(3)"> 手工输入 </label>  
				</td>
		</tr>
		<tr id="tr_sort" style="display:none">
			<td height="3" align="right">文章排序：</td>
			<td height="3">
			<div ztype=select name="select" id="SortField"><span
				value="PublishDate">时间</span> <span value="HitCount">点击数</span></div>
			</td>
		</tr>
		<tr id="tr_template">
			<td align="right">模板：</td>
			<td><input name="Template" type="text" class="input1"
				id="Template" value="${Template}" size="35" /> <input name="Button"
				type="button" class="input2" id="Button" value="选择..."
				onClick="browse('Template');">  <a id='editLink' href='javascript:void(0);' onclick="openEditor('${Template}')"><img src='../Platform/Images/icon_edit.gif' width='14' height='14'></a></td>
		</tr>
		<tr>
			<td height="6" align="right">生成文件：</td>
			<td height="6">
			<p><input name="FileName" type="text" class="input1"
				id="FileName" value="${FileName}" size="35" verify="生成文件|NotNull" />
			</p>
			</td>
		</tr>

	</table>
	</div>
	</form>
	</div>
	<p>&nbsp;</p>
	</body>
	</html>
</z:init>
