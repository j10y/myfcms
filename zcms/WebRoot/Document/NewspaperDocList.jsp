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

function config(){
/*
	var catalogID = $V("CatalogID");
	if(catalogID==""){
		Dialog.alert("请选择栏目！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 850;
	diag.Height = 480;
	diag.Title = "配置";
	diag.URL = "Document/NewspaperPageConfig.jsp?CatalogID=" + catalogID;
	//diag.OKEvent = moveSave;
	diag.show();
*/
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
  var url="NewspaperPageConfig.jsp?CatalogID=" + catalogID;
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

function openArticle(ArticleID){
	var width  = (screen.availWidth-10)+"px";
  var height = (screen.availHeight-50)+"px";
  var leftm  = 0;
  var topm   = 0;
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
  var url="Article.jsp?ArticleID=" + ArticleID;
  var w = window.open(url,"",args);
  if ( !w ){
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
			Dialog.alert("成功发布文档");
			DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
			DataGrid.setParam("dg1",Constant.PageIndex,0);
      DataGrid.loadData("dg1");
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
	diag.Height = 400;
	diag.Title = "选择转移栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=1";
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
	
	var dc = new DataCollection();
	dc.add("ArticleIDs",arr.join());
	dc.add("CatalogIDs",arrDest.join());
	Server.sendRequest("com.zving.cms.document.ArticleList.move",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("成功转移文档");
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
	diag.Height = 400;
	diag.Title = "选择复制到的栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=1";
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
	Server.sendRequest("com.zving.cms.document.ArticleList.copy",dc,function(response){
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

function showMenu (event,name){
	var evt = getEvent(event);
	var menu = new Menu();
	menu.setEvent(evt);
	var param = [];
	param.push(name);
	menu.setParam(param);
	menu.addItem("新建",window.parent.ArticleTopFrame.add,"Icons/icon018a2.gif");
	menu.addItem("打开",openA,"Icons/icon029a2.gif");
	menu.addItem("删除",del,"Icons/icon018a3.gif");
	menu.addItem("-");
	menu.addItem("签发",publish,"Icons/icon018a3.gif");
	menu.addItem("预览",preview,"Icons/icon018a3.gif");
	menu.addItem("-");
	menu.addItem("锁定",lock,"Icons/icon018a4.gif");
	menu.addItem("解锁",unLock,"Icons/icon018a4.gif");
	
	menu.addItem("-");
	menu.addItem("批注",note,"Icons/icon018a3.gif");
	menu.addItem("历史版本",historyVersion,"Icons/icon018a3.gif");
	menu.show();
	
}

function edit(dr){
	var articleID = dr.get("ID");
	openArticle(articleID);
}

function save(){
}


function openA(param){
	openArticle(param.childNodes[0]);
}
function paste(param){
	alert(param.childNodes[0]);
	alert("粘贴"+param);
}

function lock(param){
	alert("锁定"+param);
}
function unLock(param){
	alert("解锁"+param);
}

function note(param){
	alert("批注"+param);
}

function historyVersion(param){
	alert("历史版本"+param);
}
function refreshX(){
	window.location.reload();
}

function preview(){
}

function onTreeClick(ele){
	var cid=  ele.getAttribute("cid");
	//DataGrid.setParam("dg1",Constant.PageIndex,0);
	//DataGrid.setParam("dg1","IssueID",$V("IssueID"));
	DataGrid.setParam("dg1","CatalogID",cid);
	DataGrid.loadData("dg1");
	$S("CatalogID",cid);
	dealBtn("Article",ele.getAttribute("innerCode"));
}

function search(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	//DataGrid.setParam("dg1","IssueID",$V("IssueID"));
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.loadData("dg1");
}

function changeIssue(){
	if($V("CatalogID")==""){
		return
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","IssueID",$V("IssueID"));
	DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.loadData("dg1");
}

function dealBtn(IDType,ID){
	window.top.Privilege.setBtn(IDType,ID,"article_inserted",$("article_inserted"));
	window.top.Privilege.setBtn(IDType,ID,"article_updated",$("article_updated"));
	window.top.Privilege.setBtn(IDType,ID,"article_deleted",$("article_deleted"));
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
					style="height:450px;width:160px;"
					method="com.zving.cms.site.Newspaper.docTreeDataBind">
					<p cid='${ID}' innercode='${InnerCode}'
						onClick="onTreeClick(this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="blockTable">
			<tr>
				<td style="padding:8px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="article_inserted"
					onClick="config()">
					<img src="../Icons/icon018a2.gif" />配置</z:tbutton> <z:tbutton
					id="article_inserted" onClick="add()">
					<img src="../Icons/icon018a2.gif" />新建</z:tbutton> <z:tbutton
					id="article_updated" onClick="save()">
					<img src="../Icons/icon018a16.gif" />保存</z:tbutton> <z:tbutton
					id="article_deploy" onClick="publish()">
					<img src="../Icons/icon018a4.gif" />发布</z:tbutton> <z:tbutton onClick="copy()">
					<img src="../Icons/icon018a8.gif" />复制</z:tbutton> <z:tbutton onClick="move()">
					<img src="../Icons/icon018a7.gif" />转移</z:tbutton> <z:tbutton
					id="article_deleted" onClick="del()">
					<img src="../Icons/icon018a3.gif" />删除</z:tbutton></div>
				<div style="float: right; white-space: nowrap;"></div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.document.ArticleList.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="4%" height="30" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="4%" ztype="selector" field="id">&nbsp;</td>
							<td width="35%" sortfield="title"><b>标题</b></td>
							<td width="10%"><strong>作者</strong></td>
							<td width="18%" sortfield="addtime"><strong>发布时间</strong></td>
							<td width="8%"><strong>状态</strong></td>
							<td width="6%"><strong>头条</strong></td>
							<td width="6%" ztype="editDialog" function="edit"><strong>编辑</strong></td>
							<td width="9%"><strong>预览</strong></td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${Title}</td>
							<td>${Author}</td>
							<td>${PublishDate}</td>
							<td>${Status}</td>
							<td>${TopFlag}</td>
							<td></td>
							<td><a href="./Preview.jsp?ArticleID=${ID}" target="_blank">预览</a></td>
						</tr>

						<tr ztype="pagebar">
							<td align="left">&nbsp;</td>
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
