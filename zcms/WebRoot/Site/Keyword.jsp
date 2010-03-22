<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.site.Keyword.init">
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<script src="../Framework/Main.js"></script>
	<script>
Page.onLoad(function(){
	$("dg1").beforeEdit = function(tr,dr){
	    $S("LinkTarget",$V("hLinkTarget"));
	}
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Keyword",$V("Keyword"));
		dr.set("LinkURL",$V("LinkURL"));
		dr.set("LinkAlt",$V("LinkAlt"));
		dr.set("LinkTarget",$V("LinkTarget"));
		return true;
	}
});

function save(){
	DataGrid.save("dg1","com.zving.cms.site.Keyword.dg1Edit",function(){		
		DataGrid.setParam("dg1",Constant.PageIndex,0);
		DataGrid.loadData("dg1");});
}

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 200;
	diag.Title = "新建关键词";
	diag.URL = "Site/KeywordDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Keyword").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Keyword.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});
	});
}


function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("您确认要删除吗？",function(){
			var dc = new DataCollection();
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.cms.site.Keyword.del",dc,function(response){
				if(response.Status==0){
					Dialog.alert(response.Message);
				}else{
					Dialog.alert("删除关键词成功");
					DataGrid.loadData("dg1");
				}
			});
	});
}


function importWord(){
	var diag = new Dialog("Diag1");
	diag.Width = 550;
	diag.Height = 370;
	diag.Title = "批量导入关键词";
	diag.URL = "Site/KeyWordImport.jsp";
	diag.OKEvent = importSave;
	diag.show();
}

function importSave(){
	$DW.importWords();
}

function exportWord(){
	var arr = DataGrid.getSelectedValue("dg1");
	var ids;
	if(!arr||arr.length==0){
		ids = "";
	}else{
		ids = arr.join(",");	
	}
	window.location = "KeyWordExport.jsp?ids="+ids;
}

function loadWordData(){
	DataGrid.loadData("dg1");	
}

function doSearch(){
	var Word = "";
	if ($V("Word") != "请输入关键词") {
		Word = $V("Word");
	}	
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Word",Word);
	DataGrid.loadData("dg1");
}

function delKeyWord() {
	var keyWord = $V("Word");
	if (keyWord == "请输入关键词") {
		$S("Word","");
	}
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'Word'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}
</script>
	</head>
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon048a1.gif" /> 热点词列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
						<img src="../Icons/icon048a2.gif" />新建</z:tbutton> <z:tbutton
						onClick="save()">
						<img src="../Icons/icon048a16.gif" />保存</z:tbutton> <z:tbutton
						onClick="del()">
						<img src="../Icons/icon048a3.gif" />删除</z:tbutton> <z:tbutton
						onClick="importWord()">
						<img src="../Icons/icon048a8.gif" />导入</z:tbutton> <z:tbutton
						onClick="exportWord()">
						<img src="../Icons/icon048a7.gif" />导出</z:tbutton>
					<div style="float: right; white-space: nowrap;"><input
						name="Word" type="text" id="Word" value="请输入关键词"
						onFocus="delKeyWord();" style="width:110px"> <input
						type="button" name="Submitbutton" value="查询" onClick="doSearch()"
						id="Submitbutton"></div>
					</td>
				</tr>
				<tr>
					<td
						style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1"
						method="com.zving.cms.site.Keyword.dg1DataBind" size="13">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="4%" height="30" ztype="RowNo">序号</td>
								<td width="3%" ztype="selector" field="id">&nbsp;</td>
								<td width="17%" sortfield="Keyword">热点词</td>
								<td width="21%">链接地址</td>
								<td width="22%">提示</td>
								<td width="18%">打开方式</td>
								<td width="15%">添加时间</td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td width="4%">&nbsp;</td>
								<td width="3%">&nbsp;</td>
								<td>${Keyword}</td>
								<td>${LinkURL}</td>
								<td>${LinkAlt}</td>
								<td>
								<div ztype="select" name="LinkTarget${ID}" id="LinkTarget${ID}"
									value="${LinkTarget}" style="width:80px;display:none"
									disabled="true"><span value="_self" selected>原窗口</span> <span
									value="_blank">新窗口</span> <span value="_parent">父窗口</span></div>
								</td>
								<td>${AddTime}</td>
							</tr>
							<tr ztype="edit" bgcolor="#E1F3FF">
								<td width="4%" bgcolor="#E1F3FF">&nbsp;</td>
								<td width="3%" bgcolor="#E1F3FF">&nbsp;</td>
								<td><input name="Keyword" type="text" class="input1"
									id="Keyword" value="${Keyword}" size="15"></td>
								<td><input name="LinkURL" type="text" class="input1"
									id="LinkURL" value="${LinkURL}" size="30"></td>
								<td><input name="LinkAlt" type="text" class="input1"
									id="LinkAlt" value="${LinkAlt}" size="30"></td>
								<td><input type="hidden" id="hLinkTarget"
									value="${LinkTarget}">
								<div ztype="select" name="LinkTarget" id="LinkTarget"
									style="width:80px;"><span value="_self" >原窗口</span>
								<span value="_blank">新窗口</span> <span value="_parent">父窗口</span>
								</div>
								</td>
								<td>${AddTime}</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="8" align="center">${PageBar}</td>
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
</z:init>
