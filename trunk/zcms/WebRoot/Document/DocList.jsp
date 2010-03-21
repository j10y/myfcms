<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档列表</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var catalogID = $V("CatalogID");
	if(catalogID==""){
		Dialog.alert("请选择栏目！");
		return;
	}
	var width  = (screen.availWidth-10)+"px";
	var height = (screen.availHeight-50)+"px";
	var leftm  = 0;
	var topm   = 0;
	
	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
	var url="Article.jsp?CatalogID=" + catalogID;
	var w = window.open(url,"",args);
	if ( !w ){
		Dialog.alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
		return ;
	}
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	
	Dialog.confirm("确认删除选中的文档吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		
		dc.add("PageIndex",DataGrid.getParam("dg1",Constant.PageIndex));
		dc.add("PageSize",DataGrid.getParam("dg1",Constant.Size));
		
		Server.sendRequest("com.zving.cms.document.ArticleList.del",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("成功删除文档");
				DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
				DataGrid.setParam("dg1",Constant.PageIndex,0);
	      DataGrid.loadData("dg1");
			}
		});
	});
}

function edit(id){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要编辑的文章！");
		return;
	}
	var id = arr[0];
	var width  = (screen.availWidth-10)+"px";
	var height = (screen.availHeight-50)+"px";
	var leftm  = 0;
	var topm   = 0;
	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
	var url="Article.jsp?ArticleID=" + id;
	var w = window.open(url,"",args);
	if(!w){
		Dialog.alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
		return ;
	}
}

function publish(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要发布的文章！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.cms.document.ArticleList.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			var p = new Progress(response.get("TaskID"),"正在发布...");
			p.show(function(){
				$("dg1").loadData();
			});
		}
	});
}

function publishCatalog(){
	var diag = new Dialog("Diag1");
	var nodeType = $V("CatalogID")=="" ? "0":"1";
	diag.Width = 340;
	diag.Height = 150;
	diag.Title = "发布";
	diag.URL = "Site/CatalogGenerateDialog.jsp";
	diag.onLoad = function(){
		if(nodeType == "0"){
			$DW.$("tr_Flag").style.display="none";
			
		}
	};
	diag.OKEvent = publishCatalogSave;
	diag.show();
}

function publishCatalogSave(){
	$E.disable($D.OKButton);
	$E.disable($D.CancelButton);
	$E.show($DW.$("Message"));
	$DW.msg();
	var dc = Form.getData($DW.$F("form2"));
	var nodeType = $V("CatalogID")=="" ? "0":"1";
	dc.add("type",nodeType);
	dc.add("CatalogID",Tree.CurrentItem.getAttribute("cid"));
	Server.sendRequest("com.zving.cms.site.Catalog.publish",dc,function(response){
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
	var diag = new Dialog("Diag1");
	diag.Width = 340;
	diag.Height = 150;
	diag.Title = "发布";
	diag.URL = "Site/CatalogGenerateDialog.jsp";
	diag.onLoad = function(){
		$DW.$("tr_Flag").style.display="none";
	};
	diag.OKEvent = publishIndexSave;
	diag.show();
}

function publishIndexSave(){
	$E.disable($D.OKButton);
	$E.disable($D.CancelButton);
	$E.show($DW.$("Message"));
	$DW.msg();
  var dc = new DataCollection();
	Server.sendRequest("com.zving.cms.site.CatalogSite.publishIndex",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
			$D.close();
		}else{
			$D.close();
			var taskID = response.get("TaskID");
			var p = new Progress(taskID,"发布首页...");
			p.show();
		}
	});
}

function move(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要转移的文档！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 390;
	diag.Title = "选择转移栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=0";
	diag.OKEvent = moveSave;
	diag.show();
}

function moveSave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要转移的文档！");
		return;
	}
	
	var arrDest = $DW.$NV("CatalogID");
	if(arrDest == null || arrDest.length==0){
		Dialog.alert("请先选择转移的目标栏目！");
		return;
	}
	
	if ($V("CatalogID") == arrDest[0]) {
		Dialog.alert("您所选择的文档已经在该栏目下，请选择其他栏目！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("ArticleIDs",arr.join());
	dc.add("CatalogID",arrDest[0]);
	Dialog.wait("正在转移文档...");
	Server.sendRequest("com.zving.cms.document.ArticleList.move",dc,function(response){
		Dialog.closeEx();
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("转移文档成功");
			$D.close();
			DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
			DataGrid.setParam("dg1",Constant.PageIndex,0);
	  		DataGrid.loadData("dg1");
		}
	});
}


function copy(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要复制的文档！");
		return;
	}
	
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 420;
	diag.Title = "选择复制到的栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=1&Action=copy";
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要复制的文档！");
		return;
	}
	
	var arrDest = $DW.$NV("CatalogID");
	if(arrDest == null || arrDest.length==0){
		Dialog.alert("请先选择复制的目标栏目！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("ArticleIDs",arr.join());
	dc.add("CatalogIDs",arrDest.join());
	dc.add("ReferType",$DW.$NV("ReferType"));
	Dialog.wait("正在复制文档...");
	Server.sendRequest("com.zving.cms.document.ArticleList.copy",dc,function(response){
		Dialog.closeEx();
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("成功复制文档",function(){
				$D.close();
				DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
				DataGrid.setParam("dg1",Constant.PageIndex,0);
	      DataGrid.loadData("dg1");
			});
		}
	});
}

Page.onLoad(function(){
$("dg1").onContextMenu = function(tr,evt){
	evt = getEvent(evt);
	var menu = new Menu();
	menu.Width = 150;
	menu.setEvent(evt);
	menu.setParam([]);
	
	var arr = DataGrid.getSelectedValue("dg1");
	var flag = false;
	if(arr && arr.length>1){
		flag = true;
	}

	if(Application.getPriv("article",Tree.CurrentItem.getAttribute("innerCode"),"article_modify")) {
		menu.addItem("新建",add,"Icons/icon003a2.gif");
		menu.addItem("编辑",edit,"Icons/icon003a4.gif",flag);
		menu.addItem("删除",del,"Icons/icon003a3.gif");
	
		menu.addItem("-");
		menu.addItem("发布",publish,"Icons/icon003a13.gif");
		menu.addItem("排序",sortOrder,"Icons/icon400a4.gif");
		if(!flag){
			var dr = $("dg1").DataSource.Rows[tr.rowIndex-1];
			if(!dr.get("TopFlag")){
				menu.addItem("置顶",setTop);
			}else{
				menu.addItem("取消置顶",setNotTop);
			}
		}else{
			menu.addItem("置顶",setTop,null,true);
		}
		menu.addItem("-");
		menu.addItem("复制",copy,"Icons/icon003a7.gif");
		menu.addItem("转移",move,"Icons/icon003a7.gif");
		menu.addItem("-");
		menu.addItem("版本",historyVersion,"Icons/icon026a12.gif",flag);
		menu.addItem("处理历史",articleLog,"Icons/icon_column.gif",flag);
		if(!flag){
			var dr = $("dg1").DataSource.Rows[tr.rowIndex-1];
			if(dr.get("Status")=="40"){
				menu.addItem("-");
				menu.addItem("上线",up,"Icons/icon026a7.gif");
			}else if(dr.get("Status")=="30"){
				menu.addItem("-");
				menu.addItem("下线",down,"Icons/icon026a5.gif");
			}
		}
	}
	menu.addItem("预览",preview,"Icons/icon403a3.gif");
	menu.addItem("导出成Excel",function(){DataGrid.toExcel("dg1")},"Framework/Images/FileType/xls.gif");
	
	menu.show();
}
});

function down(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择文档！");
		return;
	}
	var dc = new DataCollection();
	dc.add("ArticleIDs",arr.join(","));
	Server.sendRequest("com.zving.cms.document.ArticleList.down",dc,function(response){
		if(response.Status==1){
			Dialog.alert("操作成功!",function(){
				DataGrid.loadData("dg1");
			});
		}																			
	});
}

function up(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择文档！");
		return;
	}
	var dc = new DataCollection();
	dc.add("ArticleIDs",arr.join(","));
	Server.sendRequest("com.zving.cms.document.ArticleList.up",dc,function(response){
		if(response.Status==1){
			Dialog.alert("操作成功!",function(){
				DataGrid.loadData("dg1");
			});
		}																			
	});
}

function historyVersion(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择文档！");
		return;
	}
	var id = arr[0];
	var diag = new Dialog("Diag1");
	diag.Width = 650;
	diag.Height = 380;
	diag.Title = "版本";
	diag.URL = "Document/ArticleVersionDialog.jsp?ArticleID="+id;
	diag.onLoad = function(){
	};	
	diag.show();
	$E.hide(diag.OKButton);
	diag.CancelButton.value = "确 定";
}

function articleLog(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择文档！");
		return;
	}
	var id = arr[0];
	var diag = new Dialog("Diag1");
	diag.Width = 650;
	diag.Height = 350;
	diag.Title = "处理历史";
	diag.URL = "Document/ArticleLogDialog.jsp?ArticleID="+id;
	diag.onLoad = function(){
	};
	diag.show();
	$E.hide(diag.OKButton);
	diag.CancelButton.value = "确 定";
}

function sortOrder(){
	if(DataGrid.getParam("dg1",Constant.SortString)){
		Dialog.confirm("默认排序下才可能调整顺序，是否要切换到默认排序？",function(){
			DataGrid.setParam("dg1",Constant.SortString,"");
			DataGrid.loadData("dg1",function(){
				Dialog.alert("切换成功!");
			});
		});
		return;
	}
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择文档！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 650;
	diag.Height = 310;
	diag.Title = "调整文档顺序";
	diag.URL = "Document/ArticleSortDialog.jsp?CatalogID="+DataGrid.getParam("dg1","CatalogID");
	diag.onLoad = function(){
	};
	diag.OKEvent =  sortOrderSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "调整文档顺序";
	diag.Message = "调整文档顺序，使文档排列在当前对话框中选中的文档之前。";
	diag.show();
}

function sortOrderSave(){
	var dt = $DW.DataGrid.getSelectedData("dg1");
	if(!dt||dt.Rows.length<1){
		Dialog.alert("请先选择行!");
		return;
	}
	var dc = new DataCollection();
	dc.add("TopFlag","false");
	dc.add("Target",dt.get(0,"OrderFlag"));
	if(dt.get(0,"TopFlag")){
		dc.add("TopFlag","true");
	}
	var arr = [];
	dt = DataGrid.getSelectedData("dg1");
	for(var i=0;i<dt.Rows.length;i++){
		arr.push(dt.get(i,"OrderFlag"));
	}
	dc.add("Orders",arr.join(","));
	dc.add("Type","Before");
	dc.add("CatalogID",$V("CatalogID"));
	Dialog.wait("正在调整文档顺序...");
	Server.sendRequest("com.zving.cms.document.ArticleList.sortArticle",dc,function(response){
		Dialog.closeEx();
		if(response.Status==1){
			Dialog.alert("操作成功!",function(){
				$D.close();
				DataGrid.loadData("dg1");
			});
		}
	});
}

function setTop(){	
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr || arr.length<1){
		Dialog.alert("请先选择文档!");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 100;
	diag.Title = "文章置顶";
	diag.URL = "Document/ArticleTopDialog.jsp?IDs=" + arr.join(",");
	diag.OKEvent = setTopSave;
	diag.show();
}

function setTopSave(){	
	var dc = Form.getData($DW.$F("form2"));
	Server.sendRequest("com.zving.cms.document.ArticleList.setTop",dc,function(response){
		if(response.Status==1){
			Dialog.alert("操作成功!",function(){
				$D.close();
				DataGrid.loadData("dg1");
			});
		}
	});
}

function setNotTop(id){	
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr || arr.length<1){
		Dialog.alert("请先选择文档!");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join(","));
	Server.sendRequest("com.zving.cms.document.ArticleList.setNotTop",dc,function(response){
		if(response.Status==1){
			Dialog.alert("操作成功!",function(){
				DataGrid.loadData("dg1");
			});
		}
	});
}

function changeType(){
   	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.setParam("dg1","Type",$V("Type"));
	DataGrid.loadData("dg1");
}

function preview(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr || arr.length<1){
		if($V("CatalogID")){
			window.open("../Site/Preview.jsp?Type=1&SiteID="+Cookie.get("SiteID")+"&CatalogID="+$V("CatalogID"));
		}else{
			window.open("../Site/Preview.jsp?Type=0&SiteID="+Cookie.get("SiteID"));
		}
	}else{
		window.open("Preview.jsp?ArticleID="+arr[0]);
	}	
}


function onTreeClick(ele){
	var cid = ele.getAttribute("cid");
	var code = ele.getAttribute("innercode");
	$S("CatalogID",cid);
	if(!Tree.isRoot(ele)){
		Cookie.set("DocList.LastCatalog",cid,"2100-01-01");
		Cookie.set("DocList.LastCatalogCode",code,"2100-01-01");
	}
	DataGrid.setParam("dg1","CatalogID",cid);
	DataGrid.setParam("dg1","StartDate","");//栏目切换时需清掉时间和关键字选项
	DataGrid.setParam("dg1","EndDate","");
	DataGrid.setParam("dg1","Keyword","");
	$S("StartDate","");
	$S("EndDate","");
	$S("Keyword","");
	DataGrid.loadData("dg1");
	if(!initButtons(ele)){
		return;
	}
	Application.setAllPriv("article",code);
}

function initButtons(ele){
	if(Tree.hasChild(ele)||Tree.isRoot(ele)){//有可能根节点下没有子节点
		if(Tree.isRoot(ele)){
			Misc.setButtonText("BtnPublish","发布站点首页");
			$("BtnPublish").onclick = publishIndex;
			$("BtnAdd").disable();
			$("BtnEdit").disable();
			$("BtnPreview").enable();
			$("BtnMove").disable();
			$("BtnCopy").disable();
			$("BtnDel").disable();
			$("BtnOrder").disable();
			return false;
		}else{
			Misc.setButtonText("BtnPublish","发布");
			$("BtnPublish").onclick = publish;
			$("BtnAdd").enable();
			$("BtnEdit").enable();
			$("BtnPreview").enable();
			$("BtnMove").enable();
			$("BtnCopy").enable();
			$("BtnDel").enable();
			$("BtnOrder").enable();	
			return true;
		}
	}else{
		Misc.setButtonText("BtnPublish","发布");
		$("BtnPublish").onclick = publish;
		$("BtnAdd").enable();
		$("BtnEdit").enable();
		$("BtnPreview").enable();
		$("BtnMove").enable();
		$("BtnCopy").enable();
		$("BtnDel").enable();
		$("BtnOrder").enable();	
		return true;
	}
}

Page.onLoad(init);

function init(){
	var node = Tree.getNode("tree1","cid",Cookie.get("DocList.LastCatalog"));
	//如果没有展开则载入
	if(!node){
		var code = Cookie.get("DocList.LastCatalogCode");
		while(code&&code.length>6){
			code = code.substring(0,code.length-6);
			node = Tree.getNode("tree1","innercode",code);
			if(node){
				Tree.lazyLoad(node,function(){
					var img = Tree.getLastBranchIcon(node);
					Tree.changeIcon(img,node);
					node = Tree.getNode("tree1","cid",Cookie.get("DocList.LastCatalog"));
					Tree.selectNode(node);
					if(!initButtons(node)){
						return;
					}
					Application.setAllPriv("article",code);
					Tree.scrollToNode(node);
				});
				break;
			}
		}
	}else{
		Tree.selectNode(node);
		if(!initButtons(node)){
			return;
		}
		Application.setAllPriv("article",node.getAttribute("innercode"));
		Tree.scrollToNode(node);
	}
	
	$S("CatalogID",Cookie.get("DocList.LastCatalog"));
	if(Tree.CurrentItem){
		initButtons(Tree.CurrentItem);
		Application.setAllPriv("article",Tree.CurrentItem.getAttribute("innerCode"));
	}else{
		Misc.setButtonText("BtnPublish","发布站点首页");
		$("BtnPublish").onclick = publishIndex;
		$("BtnAdd").disable();
		$("BtnEdit").disable();
		$("BtnPreview").enable();
		$("BtnMove").disable();
		$("BtnCopy").disable();
		$("BtnDel").disable();
		$("BtnOrder").disable();
	}
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'Keyword'||ele.id == 'Submitbutton'){
			search();
		}
	}
}

function search(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.setParam("dg1","StartDate",$V("StartDate"));
	DataGrid.setParam("dg1","EndDate",$V("EndDate"));
	DataGrid.loadData("dg1");
}

function afterRowDragEnd(type,targetDr,sourceDr,rowIndex,oldIndex){
	if(rowIndex==oldIndex){
		return;
	}
	if(DataGrid.getParam("dg1",Constant.SortString)){
		Dialog.confirm("默认排序下才可能调整顺序，是否要切换到默认排序？",function(){
			DataGrid.setParam("dg1",Constant.SortString,"");
			DataGrid.loadData("dg1",function(){
				Dialog.alert("切换成功!");
			});
		});
		return;
	}
	var order = $("dg1").DataSource.get(rowIndex-1,"OrderFlag");
	var target = "";
	var dc = new DataCollection();
	var ds = $("dg1").DataSource;
	dc.add("TopFlag","false");
	var i = rowIndex-1;
	if(i!=0){
		target = ds.get(i-1,"OrderFlag");
		dc.add("Type","After");
		if(rowIndex<ds.getRowCount()){
			var topFlag = ds.get(i+1,"TopFlag");
			if(topFlag){
				dc.add("TopFlag","true");
			}
		}		
	}else{
		dc.add("Type","Before");
		target = $("dg1").DataSource.get(1,"OrderFlag");
		var topFlag = ds.get(1,"TopFlag");
		if(topFlag){
			dc.add("TopFlag","true");
		}
	}
	dc.add("Target",target);
	dc.add("Orders",order);
	dc.add("CatalogID",$V("CatalogID"));
	DataGrid.showLoading("dg1");
	Server.sendRequest("com.zving.cms.document.ArticleList.sortArticle",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
			}
		});
	});	
}

function afterTreeDragEnd(evt){
	var item = this;
	var catalogID = item.$A("cid");
	var row = DragManager.DragSource.parentNode;
	var dc = new DataCollection();
	dc.add("ArticleIDs",$("dg1").DataSource.get(row.rowIndex-1,"ID"));
	dc.add("CatalogID",catalogID);
	Dialog.wait();
	Server.sendRequest("com.zving.cms.document.ArticleList.move",dc,function(response){
		Dialog.endWait();
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				item.onclick.apply(item);
			}
		});
	});
}

function showMenu(event,ele){
	var cid = ele.getAttribute("cid");
	if(!cid){
		return ;
	}
	Tree.selectNode(ele,true);
	var menu = new Menu();
	menu.setEvent(event);
	menu.setParam(cid);
	menu.addItem("预览栏目",preview,"Icons/icon403a3.gif");
	if(Application.getPriv("article",ele.getAttribute("innercode"),"article_modify")) {
		menu.addItem("发布栏目",publishCatalog,"/Icons/icon003a13.gif");
		menu.addItem("导出成Excel",function(){DataGrid.toExcel("dg1")},"Framework/Images/FileType/xls.gif");
	}
	menu.show();
}

</script>
</head>
<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="ListType" value='${ListType}'>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd"><z:tree id="tree1"
					style="height:440px;width:160px;"
					method="com.zving.cms.document.ArticleList.treeDataBind" level="2"
					lazy="true">
					<p cid='${ID}' innercode='${InnerCode}'
						onClick="onTreeClick(this);" afterdrag='afterTreeDragEnd'
						oncontextmenu="showMenu(event,this);stopEvent(event);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="blockTable">
			<tr>
				<td width="65%" style="padding:6px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="BtnAdd"
					priv="article_modify" onClick="add()">
					<img src="../Icons/icon003a2.gif" width="20" height="20" />新建</z:tbutton> <z:tbutton
					id="BtnEdit" priv="article_modify"
					onClick="DataGrid.edit(event,'dg1')">
					<img src="../Icons/icon003a4.gif" width="20" height="20" />编辑</z:tbutton> <z:tbutton
					id="BtnPublish" priv="article_modify" onClick="publish()">
					<img src="../Icons/icon003a13.gif" width="20" height="20" />发布</z:tbutton> <z:tbutton
					id="BtnPreview" priv="article_browse" onClick="preview()">
					<img src="../Icons/icon403a3.gif" width="20" height="20" />预览</z:tbutton> <z:tbutton
					id="BtnCopy" priv="article_modify" onClick="copy()">
					<img src="../Icons/icon003a12.gif" width="20" height="20" />复制</z:tbutton> <z:tbutton
					id="BtnMove" priv="article_modify" onClick="move()">
					<img src="../Icons/icon003a7.gif" width="20" height="20" />转移</z:tbutton> <z:tbutton
					id="BtnOrder" priv="article_modify" onClick="sortOrder()">
					<img src="../Icons/icon400a4.gif" width="20" height="20" />排序</z:tbutton> <z:tbutton
					id="BtnDel" priv="article_modify" onClick="del()">
					<img src="../Icons/icon003a3.gif" width="20" height="20" />删除</z:tbutton></div>
				</td>
			</tr>
			<tr>
				<td style="padding:2px 10px;">
				<div style="float:left;">列出: <z:select id="Type"
					onChange="changeType()" value="ALL" style="width:90px;">
					<select>
					<option value="ALL" selected="true">所有文档</option>
					<option value="ADD">我创建的文档</option>
					<option value="WORKFLOW">流转中的文档</option>
					<option value="TOPUBLISH">待发布的文档</option>
					<option value="PUBLISHED">已发布的文档</option>
					<option value="OFFLINE">已下线的文档</option>
					<option value="ARCHIVE">已归档的文档</option>
                    <option value="Editer">所有编辑投稿</option>
                    <option value="Member">所有会员投稿</option>
					</select>
		  </z:select> &nbsp;从 <input type="text" id="StartDate" style="width:90px; "
					ztype='date'> 至 <input type="text" id="EndDate"
					style="width:90px; " ztype='date'> &nbsp;关键词: <input
					name="Keyword" type="text" id="Keyword"> <input
					type="button" name="Submitbutton" id="Submitbutton" value="查询"
					onClick="search()"></div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:2px;">
				<z:datagrid id="dg1" method="com.zving.cms.document.ArticleList.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable" afterdrag="afterRowDragEnd">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" height="30" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="4%" height="30" ztype="Selector" field="id">&nbsp;</td>
							<td width="56%" sortfield="orderflag" direction=""><b>标题</b></td>
							<td width="7%"><strong>创建者</strong></td>
							<td width="6%"><strong>置顶</strong></td>
							<td width="7%"><strong>状态</strong></td>
							<td width="15%" sortfield="publishdate" direction=""><strong>发布时间</strong></td>
						</tr>
						<tr onDblClick="edit(${ID});">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td style="${TitleStyle}">${Title}</td>
							<td>${AddUser}</td>
							<td>${TopFlag}</td>
							<td>${StatusName}</td>
							<td title="${PublishDate}">${PublishDate}</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="9" align="left">${PageBar}</td>
						</tr>
					</table>
				</z:datagrid></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
