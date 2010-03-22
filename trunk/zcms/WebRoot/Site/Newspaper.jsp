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
var grantType ;
var currentTreeItem,lastTreeItem;
Page.onLoad(function(){
	$("Template").hide();
	$("Block").hide();
	$("Field").hide();
	$("Priv").hide();
	$("Issue").hide();
	$("Page").hide();
});

Page.onClick(function(){
	var div = $("_DivContextMenu");
	if(div){
		$E.hide(div);
	}
});

function onTabChange(tab){
	var catalogID = $V("CatalogID");
	var level = currentTreeItem.getAttribute("level");
	var pageUrl;
	if(catalogID == ""){
		pageUrl = Server.ContextPath+"Site/NewspaperSiteBasic.jsp";
		$("Template").hide();
		$("Block").hide();
		$("Field").hide();
		$("Priv").hide();
		$("Issue").hide();
		$("Page").hide();
		
		if(tab!="Basic"){
			tab = "Basic";
			Tab.onChildTabClick(tab);
		}
	}else{
		//alert([level,catalogID,tab]);
		if(level==1){
			$("Template").hide();
			$("Block").hide();
			$("Field").hide();
			$("Priv").hide();
			$("Issue").show();
			if(tab=="Issue"){
				pageUrl = Server.ContextPath+"Site/NewspaperIssue.jsp?NewspaperID="+catalogID;
			}else{
				tab ="Basic";
				Tab.onChildTabClick(tab);
				pageUrl = Server.ContextPath+"Site/NewspaperBasic.jsp?NewspaperID="+catalogID;
			}
			
		}else{
			$("Template").show();
			$("Block").show();
			$("Field").show();
			$("Priv").show();
			$("Issue").hide();
			
			if(tab=="Issue"){
				tab = "Basic";
				Tab.onChildTabClick(tab);
			}
			
			if("Grant"==tab){
				if(!grantType||"Site"==grantType){
				  grantType = "Catalog";
			  }
			  pageUrl = Server.ContextPath+"Site/Catalog"+tab+grantType+".jsp?CatalogID="+$V("CatalogID")+"&IDType="+grantType;
		  }if("Basic"==tab){
		  	pageUrl = Server.ContextPath+"Site/NewspaperPage.jsp?CatalogID="+$V("CatalogID");
		  }else{
			  pageUrl = Server.ContextPath+"Site/Catalog"+tab+".jsp?CatalogID="+$V("CatalogID");
			  if(tab == "Field"){
			    pageUrl =Server.ContextPath+"Site/CatalogColumn.jsp?CatalogID="+$V("CatalogID");;
			  }
		  }
		}
	}
	currentTab = tab;
	Tab.getChildTab(tab).src = pageUrl;
}

function changeGrantType(){
	grantType = Tab.getChildTab("Grant").contentWindow.$V("GrantType");
	if(grantType=="0"){
		grantType = "Site";
	}else if(grantType=="1"){
		grantType = "Catalog";
	}else if(grantType=="2"){
		grantType = "Template";	
	}else if(grantType=="3"){
		grantType = "Article";
	}
	onTabChange("Grant");
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
	DataGrid.save("dg1","com.zving.cms.site.Catalog.dg1Edit",function(){window.location.reload();});
}

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 480;
	diag.Title = "新建栏目";
	var parentID;
	if($V("CatalogID")==""){
		parentID = 0;
	}else {
		parentID = $V("CatalogID");
	}

	diag.URL = "Site/CatalogDialog.jsp?Type=8&ParentID=" + parentID;
	diag.onLoad = function(){
		$DW.$("Name").focus();
		$DW.$S("ParentID",parentID);
		$DW.$S("Type",$V("CatalogType"));
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
	Server.sendRequest("com.zving.cms.site.Catalog.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("新建栏目成功",function(){
			  $D.close();
			  var catalogType = $V("CatalogType");
	      Tree.setParam("tree1","CatalogType",catalogType);
				Tree.loadData("tree1");
			});
		}
	});
}


function addIssue(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 300;
	diag.Title = "新建期号";
	diag.URL = "Site/NewspaperIssueDialog.jsp";
	diag.OKEvent = addIssueSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建期号";
	diag.Message = "设置报纸年、期号、首页图片、模板文件等";
	diag.show();
}

function addIssueSave(){
	var dc = $DW.Form.getData($F("form2"));
  dc.add("NewspaperID",$V("CatalogID"));
  if($DW.Verify.hasError()){
	    return;
	}

	Server.sendRequest("com.zving.cms.site.NewspaperIssue.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("新建成功",function(){
				$D.close();
			  var catalogType = $V("CatalogType");
	      Tree.setParam("tree1","CatalogType",catalogType);
				Tree.loadData("tree1");
			});

		}
	});
}


function addNewspaper(){
  var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 300;
	diag.Title = "新建报纸";
	var parentID;
	if($V("CatalogID")==""){
		parentID = 0;
	}else {
		parentID = $V("CatalogID");
	}

	diag.URL = "Site/NewspaperDialog.jsp?ParentID=" + parentID;
	diag.OKEvent = addNewspaperSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建报纸";
	diag.Message = "设置报纸基本信息等";
	diag.show();
}

function addNewspaperSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Newspaper.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("新建成功",function(){
			  $D.close();
			  var catalogType = $V("CatalogType");
	      Tree.setParam("tree1","CatalogType",catalogType);
				Tree.loadData("tree1");
			});
		}
	});
}

function editNewspaper(NewspaperID){
  var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 300;
	diag.Title = "编辑报纸";
	diag.URL = "Site/NewspaperDialog.jsp?NewspaperID=" + NewspaperID;
	
	diag.OKEvent = editNewspaperSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "修改报纸";
	diag.Message = "修改报纸基本信息等";
	diag.show();
}

function editNewspaperSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Newspaper.edit",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("修改成功",function(){
			  $D.close();
			  var catalogType = $V("CatalogType");
	      Tree.setParam("tree1","CatalogType",catalogType);
				Tree.loadData("tree1");
			});
		}
	});
}

function move(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 400;
	diag.Title = "选择转移栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=3&CatalogType="+$V("CatalogType");
	diag.onLoad = function(){
		try{
			 $DW.$("btnOK").onclick = moveSave;
	  }catch(ex){}
	};
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
	dc.add("CatalogType",$V("CatalogType"));
	Server.sendRequest("com.zving.cms.site.Catalog.move",dc,function(response){
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
	if($V("Type")=="0"){
		Dialog.alert("只能删除栏目");
		return;
	}
	Dialog.confirm('警告：您确认要删除"'+$V("Name")+'"这个栏目吗？\n这样会把栏目下的所有相关文章都删除掉！',function(){
			dc.add("CatalogID",$V("CatalogID"));
			dc.add("CatalogType",$V("CatalogType"));
			Server.sendRequest("com.zving.cms.site.Catalog.del",dc,function(response){
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

function delNewspaper(){
	var dc = new DataCollection();
	Dialog.confirm('警告：您确认要删除"'+$V("Name")+'"这个期刊吗？\n这样会把期刊下的所有相关文章都删除掉！',function(){
			dc.add("CatalogID",$V("CatalogID"));
			dc.add("CatalogType",$V("CatalogType"));
			Server.sendRequest("com.zving.cms.site.Newspaper.del",dc,function(response){
				if(response.Status==0){
					Dialog.alert(response.Message);
				}else{
					Dialog.alert("删除成功",function(){
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
	diag.Width = 500;
	diag.Height = 480;
	diag.Title = "修改栏目";
	diag.URL = "Site/CatalogDialog.jsp?CatalogID=" + ID;
	diag.onLoad = function(){
		$DW.$("Name").focus();
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
	Server.sendRequest("com.zving.cms.site.Catalog.save",dc,function(response){
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
	dc.add("CatalogType",$V("CatalogType"));
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.Catalog.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
			$D.close();
		}else{
			Dialog.alert("生成页面成功");
			$D.close();
		}
	});
}

function publishIndex(){
	var dc = new DataCollection();
	dc.add("CatalogType",$V("CatalogType"));
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
	diag.Height = 400;
	diag.Title = "批量导入栏目";
	var parentID;
	if($V("CatalogID")==""){
		parentID = 0;
	}else {
		parentID = $V("CatalogID");
	}

	diag.URL = "Site/CatalogImportDialog.jsp?ParentID=" + parentID;
	//diag.ShowButtonRow = false;
	diag.show();
}

function reloadTree(){
  Tree.loadData("tree1");
}

function  onTypeChange(){
	var catalogType = $V("CatalogType");
	Tree.setParam("tree1","CatalogType",catalogType);
	Tree.loadData("tree1");
	Tab.onChildTabClick("Basic");
  Tab.getChildTab("Basic").src = Server.ContextPath+"Site/SiteBasic.jsp";
}
</script>
</head>

<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="Name">
<input type="hidden" id="CatalogType" value="8">
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd"><z:tree id="tree1"
					style="height:440px"
					method="com.zving.cms.site.Newspaper.treeDataBind" level="2"
					lazy="true">
					<p cid='${ID}' onClick="onTreeClick(this);" level="${level}">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td><z:tab>
			<z:childtab id="Basic" selected="true" onClick="onTabChange('Basic')"
				src="NewspaperSiteBasic.jsp">
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
				<b>页面片段</b>
			</z:childtab>
			<z:childtab id="Field" onClick="onTabChange('Field')"
				src="SiteField.jsp">
				<img src="../Icons/icon002a4.gif" />
				<b>自定义字段</b>
			</z:childtab>
			<z:childtab id="Priv" onClick="onTabChange('Priv')" src="about:blank">
				<img src="../Icons/icon010a8.gif" />
				<b>权限管理</b>
			</z:childtab>
			<z:childtab id="Issue" onClick="onTabChange('Issue')"
				src="about:blank">
				<img src="../Icons/icon010a8.gif" />
				<b>期号管理</b>
			</z:childtab>
			<z:childtab id="Page" onClick="onTabChange('Page')" src="about:blank">
				<img src="../Icons/icon010a8.gif" />
				<b>版面管理</b>
			</z:childtab>
		</z:tab></td>
	</tr>
</table>
</body>
</html>
