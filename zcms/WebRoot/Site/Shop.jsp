<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/Tabpage.js"></script>
<script>
var currentTab = "Basic";
var currentTreeItem,lastTreeItem;
Page.onLoad(function(){
	onTabChange("Basic");
});

Page.onClick(function(){
	var div = $("_DivContextMenu");
	if(div){
		$E.hide(div);
	}
});

function onTabChange(tab){
	currentTab = tab;
	var catalogID = $V("CatalogID");
	var url;
	if(catalogID == ""){
		url = Server.ContextPath+"Site/Site"+tab+".jsp";
	}else{
		url = Server.ContextPath+"Site/Catalog"+tab+".jsp?CatalogID="+$V("CatalogID");
		if(tab == "Field"){
			url = Server.ContextPath+"Site/CatalogColumn.jsp?CatalogID="+$V("CatalogID");
		}
	}
	Tab.getChildTab(tab).src = url;
}

function onTreeClick(ele){
	lastTreeItem = currentTreeItem;
	currentTreeItem = ele;
	var cid=  ele.getAttribute("cid");
	$S("CatalogID",cid);
	$S("Name",ele.innerText);
	
	onTabChange(currentTab);
}

function save(){
	if(DataGrid.EditingRow!=null){
		DataGrid.changeStatus(DataGrid.EditingRow);
	}
	DataGrid.save("dg1","com.zving.cms.site.Shop.dg1Edit",function(){window.location.reload();});
}

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 800;
	diag.Height = 360;
	diag.Title = "新建栏目";
	var parentID;
	if($V("CatalogID")==""){
		parentID = 0;
	}else {
		parentID = $V("CatalogID");
	}

	diag.URL = "Site/CatalogDialog.jsp?Type=9&ParentID=" + parentID;
	diag.onLoad = function(){
		$DW.$S("ParentID",parentID);
		//$DW.$S("SiteID",$V("SiteID"));
		$DW.$S("ParentName",$V("Name"));
	};
	diag.OKEvent = addSave;
	diag.show();
}


function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	dc.add("PublishFlag",$DW.$NV("PublishFlag"));

	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Shop.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("新建栏目成功",function(){
			  $D.close();
				Tree.loadData("tree1");
			});
		}
	});
}

function move(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 400;
	diag.Title = "转移栏目";
	
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 400;
	diag.Title = "选择转移栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=3";
	diag.OKEvent = moveSave;
	diag.show();
}

function moveSave(){
	var catalogID = $V("CatalogID");
	if(catalogID==""){
		Dialog.alert("请先选择要转移栏目！");
		return;
	}
	
  var arrDest = $DW.$NV("CatalogID");
  if(arrDest == null || arrDest.length==0){
		Dialog.alert("请先选择转移的目标位置！");
		return;
	}
	
	var newParentID = arrDest[0];
	if(newParentID==catalogID){
		Dialog.alert("不能将栏目转移到本栏目下！");
		return;
	}
	
	if(newParentID.indexOf("site")!=-1){
		newParentID = 0;
	}
	
	var dc = new DataCollection();
	dc.add("CatalogID",$V("CatalogID"));
	dc.add("ParentID",newParentID);
	
	Server.sendRequest("com.zving.cms.site.Shop.move",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			//window.location.reload();
			Tree.loadData("tree1");
			onTabChange(currentTab);
		}
	});
}

function del(){
	var dc = new DataCollection();
	if($("Type")=="0"){
		Dialog.alert("只能删除栏目");
		return;
	}
	Dialog.confirm('警告：您确认要删除"'+$V("Name")+'"这个栏目吗？\n这样会把栏目下的所有相关文章都删除掉！',function(){
			dc.add("CatalogID",$V("CatalogID"));
			Server.sendRequest("com.zving.cms.site.Shop.del",dc,function(response){
				if(response.Status==0){
					Dialog.alert(response.Message);
				}else{
					Dialog.alert("删除栏目成功",function(){
						Tree.loadData("tree1");
						$S("CatalogID","");
						onTabChange("Basic");
					});
				}
			});
	});
}

function edit(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 520;
	diag.Height = 580;
	diag.Title = "修改栏目";
	diag.URL = "Site/CatalogDialog.jsp?CatalogID=" + ID;
	diag.onLoad = function(){
		$DW.$("ParentName").parentNode.parentNode.style.display = "none";
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	dc.add("PublishFlag",$DW.$NV("PublishFlag"));

	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Shop.save",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("保存成功",function(){
				$D.close();
				Tree.loadData("tree1");
				onTabChange("Basic");
			});
		}
	});
}

function publish(){
	var diag = new Dialog("Diag1");
	var nodeType = $V("CatalogID")=="" ? "0":"1";
	diag.Width = 340;
	diag.Height = 150;
	diag.Title = "生成静态页面";
	diag.URL = "Site/CatalogGenerateDialog.jsp";
	diag.onLoad = function(){
		if(nodeType == "0"){
			$DW.$("tr_Flag").style.display="none";
			
		}
	};
	diag.OKEvent = publishSave;
	diag.show();
}

function publishSave(){
	$E.disable($D.OKButton);
	$E.disable($D.CancelButton);
	$E.show($DW.$("Message"));
	$DW.msg();

	var dc = Form.getData($DW.$F("form2"));
	var nodeType = $V("CatalogID")=="" ? "0":"1";
	dc.add("type",nodeType);
	//dc.add("SiteID",$V("SiteID"));
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.Shop.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
			$D.close();
		}else{
			$D.close();
			var p = new Progress(response.get("TaskID"),"正在生成静态文件...");
			p.show();
		}
	});
}

function publishIndex(){
	var dc = new DataCollection();
	//dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.cms.site.CatalogSite.publishIndex",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("生成页面成功");
		}
	});
}

function batchAdd(){
	var diag = new Dialog("Diag1");
	diag.Width = 550;
	diag.Height = 450;
	diag.Title = "批量导入栏目";
	var parentID;
	if($V("CatalogID")==""){
		parentID = 0;
	}else {
		parentID = $V("CatalogID");
	}

	diag.URL = "Site/CatalogImport.jsp?ParentID=" + parentID+"&Type=9";
	diag.ShowButtonRow = false;
	diag.show();
}

function reloadTree(){
  Tree.loadData("tree1");
}
</script>
</head>
<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="Name">
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="160">
		<table width="160" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td style="padding:6px;"><z:tree id="tree1"
					style="height:450px;width:160px;"
					method="com.zving.cms.site.Shop.treeDataBind" level="2" lazy="true">
					<p cid='${ID}' onClick="onTreeClick(this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td><z:tab>
			<z:childtab id="Basic" selected="true" onClick="onTabChange('Basic')"
				src="SiteBasic.jsp">
				<img src="../Icons/icon002a1.gif" />
				<b>基础信息</b>
			</z:childtab>
			<z:childtab id="Template" onClick="onTabChange('Template')"
				src="about:blank">
				<img src="../Icons/icon003a12.gif" />
				<b>模板设置</b>
			</z:childtab>
			<z:childtab id="Block" onClick="onTabChange('Block')"
				src="about:blank">
				<img src="../Icons/icon003a1.gif" />
				<b>区块设置</b>
			</z:childtab>
			<z:childtab id="Publish" onClick="onTabChange('Publish')"
				src="about:blank">
				<img src="../Icons/icon002a6.gif" />
				<b>发布设置</b>
			</z:childtab>
			<z:childtab id="Field" onClick="onTabChange('Field')"
				src="SiteField.jsp">
				<img src="../Icons/icon002a4.gif" />
				<b>自定义字段</b>
			</z:childtab>
		</z:tab></td>
	</tr>
</table>
</body>
</html>
