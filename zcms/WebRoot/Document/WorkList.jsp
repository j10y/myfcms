<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色</title>
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

function onTreeClick(ele){
	var cid=  ele.getAttribute("cid");
	DataGrid.setParam("dg1","CatalogID",cid);
	DataGrid.loadData("dg1");
	$S("CatalogID",cid);
}

function search(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	if ($V("Keyword") != "请输入要查询的文档名称" && $V("Keyword") && $V("Keyword").trim()) {
		DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	} else {
		DataGrid.setParam("dg1","Keyword","");
	}
	DataGrid.loadData("dg1");
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

function delKeyWord() {
	var keyWord = $V("Keyword");
	if (keyWord == "请输入要查询的文档名称") {
		$S("Keyword","");
	}
}

function doAction(EntryID,ActionID,ArticleID){
	var dc = new DataCollection();
	dc.add("EntryID",EntryID);
	dc.add("ActionID",ActionID);
	dc.add("ArticleID",ArticleID);
	Server.sendRequest("com.zving.cms.document.Article.doAction",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
   				DataGrid.loadData("dg1");
			}
		});
	});
}

function changeType(){
   	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.setParam("dg1","Type",$V("Type"));
	DataGrid.loadData("dg1");
}
</script>
</head>
<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="ListType" value='${ListType}'>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td></td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="4"
			class="blockTable">
			<tr>
				<td colspan="2" valign="middle" class="blockTd"><img
					src="../Icons/icon018a1.gif" />文档审核列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 4px;">
				<z:tbutton 
					priv="true" onClick="DataGrid.edit(event,'dg1')">
					<img src="../Icons/icon018a4.gif" />浏览</z:tbutton>
				<z:tbutton id="article_manage"
					priv="true" onClick="del()">
					<img src="../Icons/icon018a3.gif" />删除</z:tbutton> &nbsp;列出:<z:select id="Type"
					onChange="changeType()" value="ALL" style="width:120px;">
					<span value="ALL" selected="true">所有流转中的文档</span>
					<span value="TOME">待我处理的文档</span>
				</z:select> &nbsp;关键词: <input name="Keyword" type="text" id="Keyword">
				<input type="button" name="Submitbutton" id="Submitbutton"
					value="查询" onClick="search()"></td>
			</tr>
			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.document.WorkList.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="4%" height="30" ztype="RowNo"></td>
							<td width="4%" ztype="selector" field="id">&nbsp;</td>
							<td width="8%"><b>所属栏目</b></td>
							<td width="30%"><b>标题</b></td>
							<td width="6%"><strong>创建者</strong></td>
							<td width="6%"><strong>接收者</strong></td>
							<td width="12%"><strong>添加时间</strong></td>
							<td width="5%"><strong>状态</strong></td>
							<td width="7%"><strong>流转状态</strong></td>
							<td width="17%"><strong>可进行的操作</strong></td>
						</tr>
						<tr onDblClick="edit(${ID});">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${CatalogIDName}</td>
							<td>${Title}</td>
							<td>${Author}</td>
							<td>${Owner}</td>
							<td>${AddTime}</td>
							<td>${StatusName}</td>
							<td><font color='red'>${WorkFlowStatus}</font></td>
							<td>${Actions}</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="10" align="left">${PageBar}</td>
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
