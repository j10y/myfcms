<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版位管理</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 260;
	diag.Title = "新建版位";
	diag.URL = "DataService/AdvertiseLayoutDialog.jsp";
	diag.onLoad = function(){
		$DW.$("PositionName").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}
function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.dataservice.AdvertiseLayout.add",dc,function(response){
		if(response.Status==1){
			Dialog.alert("新增版位成功",function(){
				$D.close();
				DataGrid.loadData("dg1");	
			});
		}
		else{
			Dialog.alert(response.Message);
		}
	});
}
function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的版位！");
		return;
	}
	
	Dialog.confirm("确定要删除版位吗？",function(){
		var dc = new DataCollection();
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.cms.dataservice.AdvertiseLayout.del",dc,function(response){
				if(response.Status==1){
					Dialog.alert(response.Message,function(){
						DataGrid.loadData("dg1");
					});
				}
				else{
					Dialog.alert(response.Message);
				}
			});
	})
}

function copy(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的版位！");
		return;
	}
	if(arr.length>1){
		Dialog.alert("复制操作只能针对一个版位！");
		return;
	}
	var dc = new DataCollection();
	dc.add("ID",arr[0]);
	Server.sendRequest("com.zving.cms.dataservice.AdvertiseLayout.copy",dc,function(response){
		if(response.Status==1){
			Dialog.alert("复制版位成功",function(){
				DataGrid.loadData("dg1");
			});
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function editDialog(ID){
	var arr = DataGrid.getSelectedValue("dg1");
	if(ID&&ID!=""){
		arr = new Array();
		arr[0] = ID;
	}
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要编辑的版位！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 260;
	diag.Title = "编辑版位基本信息";
	diag.URL = "DataService/AdvertiseLayoutDialog.jsp?ID="+arr[0];
	diag.onLoad = function(){
    $DW.$S("ID",arr[0]);
		$DW.$("PositionName").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.dataservice.AdvertiseLayout.add",dc,function(response){
		if(response.Status==1){
			Dialog.alert("修改版位成功",function(){
				$D.close();
				DataGrid.loadData("dg1");
			});
		}
		else{
			Dialog.alert(response.Message);
		}
	});
}

function preview(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要预览的广告版位！");
		return;
	}
	window.open("AdvertisePreview.jsp?ID="+arr[0])
}

function doSearch(){
	var name = "";
	if($V("SearchContent").trim()!= "请输入要查询的版位名称"){
		name = $V("SearchContent");
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchContent",name);
	DataGrid.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchContent'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	var keyWord = $V("SearchContent");
	if (keyWord == "请输入要查询的版位名称") {
		$S("SearchContent","");
	}
}

function editAdContent(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择广告版位！");
		return;
	}
	var diag = new Dialog("diag1");
	diag.Width = 520;
	diag.Height = 460;
	diag.Title = "编辑广告内容";
	diag.URL = "DataService/AdvertiseDialog.jsp?PosID="+arr[0];
	diag.OKEvent = editAdSave;
	diag.show();
}

function editAdSave(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = Form.getData($DW.$F("adform"));
	if($DW.$V("AdType")=="image"){
		if($DW.checkImgPath()){
			dc.add("imgADLinkUrl",$DW.$NV("imgADLinkUrl").join("^"));
			dc.add("imgADAlt",$DW.$NV("imgADAlt").join("^"));
			dc.add("ImgPath",$DW.$NV("ImgPath").join("^"));
		}else{
			Dialog.alert("请为图片广告选择图片");
			return;
		}
	}else if($DW.$V("AdType")=="flash"){
		dc.add("SwfFilePath",$DW.$NV("SwfFilePath").join("^"));
	}else if($DW.$V("AdType")=="text"){
		dc.add("textContent",$DW.$NV("textContent").join("^"));
		dc.add("textLinkUrl",$DW.$NV("textLinkUrl").join("^"));
		dc.add("TextColor",$DW.$NV("TextColor").join("^"));
	}
	Server.sendRequest("com.zving.cms.dataservice.Advertise.add",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){$D.close();DataGrid.loadData("dg1");});
		}else{
			Dialog.alert(response.Message);
			return;
		}
	});
}

function showAdList(ID,PositionName){
	var diag = new Dialog("Diag1");
	diag.Width = 700;
	diag.Height = 380;
	diag.Title = "广告列表";
	diag.URL = "DataService/AdvertiseListDialog.jsp?PosID="+ID+"&PosName="+PositionName;
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value="关闭";
}

function loadAdData(){
	DataGrid.loadData("dg1");	
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
<tr valign="top">
    <td>
	<table width="100%" border="0" cellpadding="4" cellspacing="0" class="blockTable">
      <tr>
        <td valign="middle" class="blockTd"><img src="../Icons/icon010a1.gif"/> 广告版位列表</td>
      </tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add();"><img src="../Icons/icon010a2.gif" />新建</z:tbutton> 
        <z:tbutton onClick="del();"><img src="../Icons/icon010a3.gif" />删除</z:tbutton> 
        <z:tbutton onClick="copy();"><img src="../Icons/icon010a12.gif" />复制</z:tbutton>
        <z:tbutton onClick="editDialog();"><img src="../Icons/icon010a16.gif" />修改基本信息</z:tbutton>
        <z:tbutton onClick="editAdContent();"><img src="../Icons/icon010a4.gif" />修改广告内容</z:tbutton>
        <z:tbutton onClick="preview();"><img src="../Icons/icon010a15.gif" />预览</z:tbutton>
		<div style="float: right; white-space: nowrap;">
		<input name="SearchContent" type="text" id="SearchContent" value="请输入要查询的版位名称" onFocus="delKeyWord();" style="width:150px">
		<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onClick="doSearch()">
		</div>
        </td>
      </tr>
      <tr>
        <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
		<z:datagrid id="dg1" method="com.zving.cms.dataservice.AdvertiseLayout.dg1DataBind"	size="15">
		<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
			<tr ztype="head" class="dataTableHead">
				<td width="4%" ztype="RowNo">序号</td>
				<td width="3%" ztype="selector" field="id">&nbsp;</td>
				<td width="17%"><b>广告名称</b></td>
				<td width="15%"><b>JS路径</b></td>
                <td width="10%"><b>版位类型</b></td>
                <td width="10%"><b>广告类型</b></td>
				<td width="10%"><b>尺寸</b></td>
                <td width="30%"><b>广告描述</b></td>
			</tr>
			<tr onDblClick="showAdList(${ID},'${PositionName}')" style1="background-color:#FFFFFF"
				style2="background-color:#F9FBFC">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><a href="#;" onClick="editDialog(${ID});">${PositionName}</a></td>
				<td>${JSName}</td>
                <td>${PositionTypeName}</td>
                <td>${ADTypeName}</td>
				<td>${PositionSizeWidth}*${PositionSizeHeight}</td>
                <td title="${Description}">${Description}</td>
			</tr>
			<tr ztype="pagebar">
				<td colspan="8" align="center">${PageBar}</td>
			</tr>
		</table>		
		</z:datagrid>
    </td>
  </tr>
</table>
</td>
</tr>
</table>
</body>
</html>