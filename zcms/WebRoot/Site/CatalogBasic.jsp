<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	Application.setAllPriv("article",$V("InnerCode"));
	
	if($V("hKeywordFlag") == "Y"){
		$("KeywordFlag").checked = true;
		$("spanSetting").style.display="";
	}
});

//鼠标点击当前页面时，隐藏右键菜单
var iFrame =window.parent;
Page.onClick(function(){
	var div = iFrame.$("_DivContextMenu")
	if(div){
			$E.hide(div);
	}	
});

var topFrame = window.parent;
function add(){
	if($NV('SingleFlag')=='Y'){
		Dialog.alert("单页栏目不能创建下级栏目!");
		return;
	}
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

function refreshTree(){
	window.location.reload();
}

function move(){
	topFrame.move();
}

function edit(param){
	topFrame.edit(param);	
}

function batchAdd(){
	topFrame.batchAdd();
}

function copyConfig(){
	topFrame.copyConfig();
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

function save(){
	var dc = Form.getData("form1");
	dc.add("PublishFlag",$NV("PublishFlag"));
	dc.add("SingleFlag",$NV("SingleFlag"));
	dc.add("AllowContribute",$NV("AllowContribute"));
	dc.add("Extend",$V("Extend"));
	dc.add("WorkFlowExtend",$V("WorkFlowExtend"));
	dc.add("Workflow",$V("Workflow"));
	dc.add("ListPageSize",$V("ListPageSize"));
	if(!$("KeywordFlag").checked){
		dc.add("KeywordFlag","N");
		dc.add("KeywordSetting","");
	}
	
	if(Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Catalog.save",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("保存成功",function(){
				topFrame.Tree.loadData("tree1",function(){
						var node = topFrame.Tree.getNode("tree1","cid",Cookie.get("DocList.LastCatalog"));
						topFrame.Tree.selectNode(node,true);
						topFrame.Tree.scrollToNode(node);
				});
			});
		}
	});
}
function openEditor(path){
	topFrame.openEditor(path);
}

function preview(){
	topFrame.preview();
}

function clean(){
	topFrame.clean();
}

function changeKeywordFlag(){
//	if($("KeywordFlag").checked){
//		$("spanSetting").style.display="";
//	}else{
//		$("spanSetting").style.display="none";
//	}
} 

function showSetRule(){
   var diag=new Dialog("diag3");
   diag.Width=500;
   diag.Height=230;
   diag.Title="设置详细页命名规则";
   diag.URL = "Site/CatalogDetailNameRuleDialog.jsp";
   diag.onLoad = function(){
     $DW.$S("DetailNameRule","/${catalogpath}/${document.id}");
	};
   diag.OKEvent=editDetailNameRule;
   diag.show();
}

function editDetailNameRule(){
   $S("DetailNameRule", $DW.$V("DetailNameRule"));
   $D.close();
}   
</script>
</head>
<body>
<z:init method="com.zving.cms.site.Catalog.init">
	<div style="padding:2px;">
	<table width="100%" cellpadding='0' cellspacing='0'
		style="margin-bottom:4px;">
		<tr>
			<td><z:tbutton id="BtnSave" priv="article_manage"
				onClick="save('${ID}');">
				<img src="../Icons/icon002a4.gif" width="20" height="20" />保存</z:tbutton> <z:tbutton
				id="BtnAdd" priv="article_manage" onClick="add();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />新建子栏目</z:tbutton> <z:tbutton
				id="BtnDel" priv="article_manage" onClick="del();">
				<img src="../Icons/icon002a3.gif" width="20" height="20" />删除</z:tbutton> <z:tbutton
				id="BtnMove" priv="article_manage" onClick="move();">
				<img src="../Icons/icon002a7.gif" width="20" height="20" />移动</z:tbutton> <z:tbutton
				id="BtnBatchAdd" priv="article_manage" onClick="batchAdd();">
				<img src="../Icons/icon002a8.gif" width="20" height="20" />批量新建</z:tbutton> <z:tbutton
				id="BtnBatchAdd" priv="article_manage" onClick="clean();">
				<img src="../Icons/icon002a9.gif" width="20" height="20" />清空文档</z:tbutton> <z:tbutton
				id="BtnPublish" priv="article_manage" onClick="publish();">
				<img src="../Icons/icon002a1.gif" />发布</z:tbutton> <z:tbutton id="BtnPublish"
				priv="article_browse" onClick="preview();">
				<img src="../Icons/icon403a3.gif" />预览</z:tbutton></td>
		</tr>
	</table>
	<form id="form1">
	<table width="600" border="1" cellpadding="1" cellspacing="0"
		bordercolor="#eeeeee">
		
		<tr id="tr_ID">
			<td width="17%">ID：</td>
			<td width="83%">${ID}
			<input name="ID" type="hidden" id="ID" value="${ID}" />
			<input name="SiteID" type="hidden" id="SiteID" value="${SiteID}" />
			<input name="Type" type="hidden" id="Type" value="${Type}" />
			<input name="InnerCode" type="hidden" id="InnerCode"  value="${InnerCode}" />
			</td>
		</tr>
		<tr>
			<td>栏目名称：</td>
			<td><input name="Name" type="text" class="input1" id="Name"
				value="${Name}" size="30" verify="NotNull" /> &nbsp;<a
				href="Preview.jsp?Type=1&SiteID=${SiteID}&CatalogID=${ID}"
				target="_blank">预览</a></td>
		</tr>
		<tr>
			<td>栏目英文名：</td>
			<td><input name="Alias" type="text" class="input1" id="Alias"
				value="${Alias}" size="30"
				verify="NotNull&&请输入长度在2-10位之间的字符|Regex=^[\w]{2,10}$" /></td>
		</tr>
		<tr style="display:${IsDisplay}">
			<td>首页模板：</td>
			<td><input name="SiteID" type="text" class="input1"
				id="IndexTemplate" value="${IndexTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('IndexTemplate')"> ${edit}</td>
		</tr>
		<tr style="display:${IsDisplay}">
			<td>列表页模板：</td>
			<td><input name="SiteID" type="text" class="input1"
				id="ListTemplate" value="${ListTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('ListTemplate')"> ${edit1}</td>
		</tr>

		<tr>
			<td>详细页模板：</td>
			<td><input name="DetailTemplate" type="text" class="input1"
				id="DetailTemplate" value="${DetailTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('DetailTemplate')"> ${edit2}</td>
		</tr>
		<tr style="display:none">
			<td>列表页命名规则：</td>
			<td><input name="ListNameRule" type="text" class="input1"
				id="ListNameRule" value="${ListNameRule}" size="40" /></td>
		</tr>
		<tr>
			<td>详细页命名规则：</td>
			<td><input name="DetailNameRule" type="text" class="input1"
				id="DetailNameRule" value="${DetailNameRule}" size="40" />
				<input name="SetRuleButton" id="SetRuleButton" type="button" value="设置" onclick="showSetRule();">
				</td>
			
		</tr>
		<tr>
			<td>Rss模板：</td>
			<td><input name="SiteID" type="text" class="input1"
				id="RssTemplate" value="${RssTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('RssTemplate')"></td>
		</tr>
		<tr>
			<td>模板沿用：</td>
			<td><z:select id="Extend" style="width:150px;">
				<span value="1">仅本栏目</span>
				<span value="2">所有子栏目沿用相同设置</span>
				<span value="3">所有栏目沿用相同设置</span>
			</z:select></td>
		</tr>
		<tr>
			<td>工作流：</td>
			<td><z:select id="Workflow" style="width:150px;"> ${Workflow} </z:select>
				<a href="#" class="tip"
				onMouseOut="Tip.close(this)"
				onMouseOver="Tip.show(this,'选择文章审核工作流，可到“系统管理”->“工作流定义”菜单进行设置。');"><img
				src="../Framework/Images/icon_tip.gif" width="16" height="16"></a></td>
		</tr>
		<tr>
			<td>工作流沿用：</td>
			<td><z:select id="WorkFlowExtend" style="width:150px;">
				<span value="1">仅本栏目</span>
				<span value="2">所有子栏目沿用相同设置</span>
				<span value="3">所有栏目沿用相同设置</span>
			</z:select>  </td>
		</tr>
		<tr style="display:none">
			<td>排序字段：</td>
			<td><input name="OrderColumn" type="text" class="input1"
				id="OrderColumn" value="${OrderColumn}" size="30" /></td>
		</tr>
		<tr>
			<td>列表页项目数：</td>
			<td><z:select name="ListPageSize" id="ListPageSize"
				style="width:137px;" value="${ListPageSize}">
				<span value="10">10</span>
				<span value="20">20</span>
				<span value="30">30</span>
			</z:select></td>
		</tr>
		<tr>
			<td height=25>关键词：</td>
			<td>
				<input type="hidden" id="hKeywordFlag" value="${KeywordFlag}">
				<label for="KeywordFlag">
				<input name="KeywordFlag" type="checkbox" id="KeywordFlag" value="Y" onclick="changeKeywordFlag()">抽取关键词
				</label>
				<!-- <span id="spanSetting" style="display:none">关联分类：
					<input name="KeywordSetting"  id="KeywordSetting"  value="${KeywordSetting}" size="40"/>
				</span> -->
				</td>
		</tr>
		<tr>
			<td height=20>是否开放显示：</td>
			<td>${PublishFlag}</td>
		</tr>
		<tr>
			<td height=20>是否单页栏目：</td>
			<td>${SingleFlag}</td>
		</tr>
        <tr>
			<td height=20>是否允许投稿：</td>
			<td>${AllowContribute}</td>
		</tr>
	</table>
	</form>
	</div>
</z:init>
</body>
</html>
