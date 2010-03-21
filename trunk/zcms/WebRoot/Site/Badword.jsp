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
Page.onLoad(function(){
	$("dg1").beforeEdit = function(tr,dr){
	    $S("Level",$V("hLevel"));
	}
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Word",$V("Word"));
		dr.set("ReplaceWord",$V("ReplaceWord"));
		dr.set("TreeLevel",$V("Level"));		
		return true;
	}
});

function save(){
	DataGrid.save("dg1","com.zving.cms.site.BadWord.dg1Edit",function(){
		DataGrid.setParam("dg1",Constant.PageIndex,0);
		DataGrid.loadData("dg1");
		});
}

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 200;
	diag.Title = "新建敏感词";
	diag.URL = "Site/BadwordDialog.jsp";
	diag.onLoad = function(){
		$DW.$("BadWord").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.BadWord.add",dc,function(response){
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
	Server.sendRequest("com.zving.cms.site.BadWord.del",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("删除敏感词成功");
			DataGrid.loadData('dg1');
		}
	});
});
}

function importWord(){
	var diag = new Dialog("Diag1");
	diag.Width = 550;
	diag.Height = 370;
	diag.Title = "批量导入敏感词";
	diag.URL = "Site/BadWordImport.jsp";
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
	window.location = "BadWordExport.jsp?ids="+ids;
}

function loadWordData(){
	DataGrid.loadData("dg1");	
}

function doSearch(){
	var Word = "";
	if ($V("BadWord") != "请输入敏感词") {
		Word = $V("BadWord");
	}	
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Word",Word);
	DataGrid.loadData("dg1");
}

function delKeyWord() {
	var keyWord = $V("BadWord");
	if (keyWord == "请输入敏感词") {
		$S("BadWord","");
	}
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'BadWord'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}
</script>
</head>
<body>
<z:init method="com.zving.cms.site.BadWord.init">
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon007a1.gif" /> 敏感词列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
						<img src="../Icons/icon007a2.gif" />新建</z:tbutton> <z:tbutton
						onClick="save()">
						<img src="../Icons/icon007a16.gif" />保存</z:tbutton> <z:tbutton
						onClick="del()">
						<img src="../Icons/icon007a3.gif" />删除</z:tbutton> <z:tbutton
						onClick="importWord()">
						<img src="../Icons/icon007a8.gif" />导入</z:tbutton> <z:tbutton
						onClick="exportWord()">
						<img src="../Icons/icon007a7.gif" />导出</z:tbutton>
					<div style="float: right; white-space: nowrap;"><input
						name="BadWord" type="text" id="BadWord" value="请输入敏感词"
						onFocus="delKeyWord();" style="width:110px"> <input
						type="button" name="Submitbutton" value="查询" onClick="doSearch()"
						id="Submitbutton"></div>
					</td>
				</tr>
				<tr>
					<td
						style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1"
						method="com.zving.cms.site.BadWord.dg1DataBind" size="13">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="5%" ztype="RowNo"><strong>序号</strong></td>
								<td width="5%" ztype="selector" field="id">&nbsp;</td>
								<td width="20%" sortfield="Word"><b>敏感词</b></td>
								<td width="23%"><b>替换词</b></td>
								<td width="20%"><b>敏感级别</b></td>
								<td width="15%"><b>添加时间</b></td>
								<td width="12%"><b>添加人</b></td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td width="5%">&nbsp;</td>
								<td width="5%">&nbsp;</td>
								<td>${Word}</td>
								<td>${ReplaceWord}</td>
								<td>
								<div ztype=select name="LevelName${ID}" id="Level${ID}"
									style="width:100px;display:none" value="${TreeLevel}"
									disabled="true"><span value=1>一般</span> <span value=2>比较敏感</span>
								<span value=3>特别敏感</span></div>
								</td>
								<td>${AddTime}</td>
								<td>${AddUser}</td>
							</tr>
							<tr ztype="edit" bgcolor="#E1F3FF">
								<td width="5%" bgcolor="#E1F3FF">&nbsp;</td>
								<td width="5%" bgcolor="#E1F3FF">&nbsp;</td>
								<td><input name="Word" type="text" class="input1" id="Word"
									value="${Word}" size="20"></td>
								<td><input name="ReplaceWord" type="text" class="input1"
									id="ReplaceWord" value="${ReplaceWord}" size="30"></td>
								<td><input id="hLevel" type="hidden" value="${TreeLevel}">
								<div ztype=select name="Level" id="Level" style="width:100px">
								<span value=1>一般</span> <span value=2>比较敏感</span> <span value=3>特别敏感</span>
								</div>
								</td>
								<td>${AddTime}</td>
								<td>${AddUser}</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="7" align="center">${PageBar}</td>
							</tr>
						</table>
					</z:datagrid></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</z:init>
</body>
</html>