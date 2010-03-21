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
Array.prototype.unique = function(){  
 var  a = {};
 for(var i=0; i <this.length; i++){  
    if(typeof a[this[i]] == "undefined")  
      a[this[i]] = 1;  
 }  
 this.length = 0;  
 for(var i in a)  
    this[this.length] = i;  
 return this;  
}  

function add(){
	var diag  = new Dialog("Diag3");
	diag.Width = 830;
	diag.Height = 460;
	diag.Title ="浏览文章";
	diag.URL = "Document/DocListDialog.jsp";
	diag.onLoad = function(){
	};
	diag.OKEvent = getDocList;
	diag.show();
}

function getDocList(){
	var dt = $DW.DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请选择文档！");
		return;
	}
	var articleIDs =[];
	for(var i=0;i<drs.length;i++){
		var dr = drs[i];
		articleIDs.push(dr.get("ID"));
	}
	
	var allIDs;
  var oldArticleIDs = $V("ArticleIDs");
  if(oldArticleIDs != ""){
	  oldArticleIDs = oldArticleIDs.split(",");	
	  allIDs = oldArticleIDs.concat(articleIDs).unique();
  }else{
  	allIDs = articleIDs;
  }
	
	allIDs.remove($V("ArticleID"));
	
	$S("ArticleIDs",allIDs.join(","));

	DataGrid.setParam("dg1","RelativeArticle",$V("ArticleIDs"));
  DataGrid.loadData("dg1");
	$D.close();
}

function deleteRow(ID){
	var articleIDs = $V("ArticleIDs");
	articleIDs = articleIDs.split(",");	
	articleIDs.remove(ID);
	$S("ArticleIDs",articleIDs.join(","));

	DataGrid.setParam("dg1","RelativeArticle",$V("ArticleIDs"));
  DataGrid.loadData("dg1");
}

function del(){
  var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要删除的文章！");
		return;
	}
	
	var articleIDs = $V("ArticleIDs");
	articleIDs = articleIDs.split(",");	
	for(var i = 0;i<arr.length;i++){
		articleIDs.remove(arr[i]);
	}
  
	$S("ArticleIDs",articleIDs.join(","));
	DataGrid.setParam("dg1","RelativeArticle",$V("ArticleIDs"));
  DataGrid.loadData("dg1");
}

</script>
</head>
<body>
<input type="hidden" id="ArticleIDs"
	value="<%=request.getParameter("RelativeArticle")%>">
<input type="hidden" id="ArticleID"
	value="<%=request.getParameter("ArticleID")%>">
<table width="100%" border="0" cellspacing="4" cellpadding="0">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td valign="middle" class="blockTd"><img
					src="../Icons/icon018a1.gif" />相关文档列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
					<img src="../Icons/icon018a2.gif" />添加</z:tbutton> <z:tbutton onClick="del()">
					<img src="../Icons/icon018a3.gif" />删除</z:tbutton></td>
			</tr>
			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid	id="dg1" method="com.zving.cms.document.Article.relativeDg1DataBind" page="false">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" height="30" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="8%" ztype="selector" field="id">&nbsp;</td>
							<td width="76%" drag="true"><b>标题</b></td>
							<td width="8%"><b>操作</b></td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td height="15">&nbsp;</td>
							<td>&nbsp;</td>
							<td title="作者：${author} 发表时间：${addtime}">${Title}</td>
							<td><a href="#;" onClick="deleteRow(${ID})">删除</a></td>
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
