<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告管理</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 520;
	diag.Height = 460;
	diag.Title = "新建广告";
	diag.URL = "DataService/AdvertiseDialog.jsp?PosID="+$V("PositionID")+"&Type=New";
	diag.onLoad = function(){
		$DW.$("AdName").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
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

function editAD(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择需要编辑的广告！");
		return;
	}
	var diag = new Dialog("Diag2");
	diag.Width = 520;
	diag.Height = 460;
	diag.Title = "编辑广告内容";
	diag.URL = "DataService/AdvertiseDialog.jsp?ID="+arr[0];
	diag.OKEvent = addSave;
	diag.show();
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的广告！");
		return;
	}
	Dialog.confirm("确定要删除广告吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.Advertise.del",dc,function(response){
			if(response.Status==1){
				Dialog.alert("删除广告成功",function(){
					DataGrid.loadData("dg1");
				});
			}else{
				Dialog.alert(response.Message);
			}
		});
	})
}

function start(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要启用的广告！");
		return;
	}
	if(arr.length>1){
		Dialog.alert("版位下只能有一个广告处于启动状态");
		return;
	}
	var dc = new DataCollection();
	dc.add("ID",arr[0]);
	Server.sendRequest("com.zving.cms.dataservice.Advertise.start",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				DataGrid.loadData("dg1");
			});
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function doSearch(){
	var name = "";
	if($V("SearchContent").trim()!= "请输入要查询的广告名称"){
		name = $V("SearchContent");
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchContent",name);
	DataGrid.loadData("dg1");
}

function delKeyWord() {
	var keyWord = $V("SearchContent");
	if (keyWord == "请输入要查询的广告名称") {
		$S("SearchContent","");
	}
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
    <table width="100%" border="0" cellpadding="0" class="blockTable">
    <z:init>
      <tr>
        <td valign="middle" class="blockTd"><img src="../Icons/icon010a1.gif"/> 
        <b style="color:#F60;">&nbsp;${PosName}&nbsp;</b>下的广告
        <input id="PositionID" name="PositionID" type="hidden" value="${PosID}"/>
        </td>
      </tr>
    </z:init>
      <tr>
        <td style="padding:4px 5px;"><z:tbutton onClick="add();"><img src="../Icons/icon010a2.gif"/>新建</z:tbutton>
        <z:tbutton onClick="editAD();"><img src="../Icons/icon010a4.gif"/>编辑</z:tbutton>
        <z:tbutton onClick="del();"><img src="../Icons/icon010a3.gif"/>删除</z:tbutton>
        <z:tbutton onClick="start();"><img src="../Icons/icon010a8.gif" />启用广告</z:tbutton>
        <z:tbutton onClick="Dialog.close();"><img src="../Icons/icon010a7.gif" />返回版位列表</z:tbutton>
        <div style="float: right; white-space: nowrap; padding-right:6px;">
		<input name="SearchContent" type="text" id="SearchContent" value="请输入要查询的广告名称" onFocus="delKeyWord();" style="width:150px">
		<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onClick="doSearch()">
		</div>
        </td>
      </tr>
      <tr>
        <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
        <z:datagrid id="dg1" method="com.zving.cms.dataservice.Advertise.dg1DataBind" size="10">
            <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
            <tr ztype="head" class="dataTableHead">
              <td width="3%" ztype="RowNo">&nbsp;</td>
              <td width="3%" ztype="selector" field="id">&nbsp;</td>
              <td width="25%"><b>广告名称</b></td>
              <td width="11%"><b>广告类型</b></td>
              <td width="21%"><b>版位名称</b></td>
              <td width="7%"><b>活动</b></td>
              <td width="10%"><b>点击数</b></td>
              <td width="20%"><b>添加时间</b></td>
            </tr>
            <tr onDblClick="editAD();" style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
              <td  width="3%">&nbsp;</td>
              <td width="3%" >&nbsp; </td>
              <td>${AdName}</td>
              <td>${AdTypeName}</td>
              <td>${PositionName}</td>
              <td><b>${IsOpen}</b></td>
              <td>${HitCount}</td>
              <td>${AddTime}</td>
            </tr>
            <tr ztype="pagebar">
                <td colspan="9" align="center">${PageBar}</td>
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
