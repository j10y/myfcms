<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自定义表单</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/DateTime.js"></script>
<script>
Page.onLoad(function(){
	Application.setAllPriv("article",$V("InnerCode"));
});

function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 450;
	diag.Height = 320;
	diag.Title = "新建自定义字段";
	diag.URL = "Site/CatalogColumnDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = $DW.Form.getData("form2");
	var noCheckArr = [];
	var inputType = $DW.$V("InputType");
	if($DW.input==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.text==inputType){
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.select==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.radio==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.checkbox==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.dateInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.imageInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.htmlInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
		var HTML = $DW.getHTML();
		if(HTML==""||HTML=="<br/>"){
			Dialog.alert("HTML内容不能为空");
			return;
		}else{
			dc.add("HTML",HTML);
		}
	}
	if($DW.Verify.hasError(noCheckArr,"form2")){
		return;
	}
	if(!$DW.$NV("IsMandatory")){
		dc.add("IsMandatory","N");
	}
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.CatalogColumn.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function edit(){
	var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请先选择要修改的行！");
		return;
	}
	if(drs.length>1){
		Dialog.alert("只能修改1条信息！");
		return;
	}
	dr = drs[0];
  editDialog(dr.get("ID"));
}

function editDialog(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 320;
	diag.Title = "编辑自定义字段";
	diag.URL = "Site/CatalogColumnEditDialog.jsp?ColumnID="+ID;
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = $DW.Form.getData("form2");
	var noCheckArr = [];
	var inputType = $DW.$V("InputType");
	if($DW.input==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.text==inputType){
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.select==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.radio==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.checkbox==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.dateInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.imageInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
	}else if($DW.htmlInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("MaxLength"));
		noCheckArr.push($DW.$("ListOption"));
		var HTML = $DW.getHTML();
		if(HTML==""||HTML=="<br/>"){
			Dialog.alert("HTML内容不能为空");
			return;
		}else{
			dc.add("HTML",HTML);
		}
	}
	if($DW.Verify.hasError(noCheckArr,"form2")){
		return;
	}
	if(!$DW.$NV("IsMandatory")){
		dc.add("IsMandatory","N");
	}
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.CatalogColumn.save",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
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
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 120;
	diag.Title = "删除自定义字段";
	diag.URL = "Site/CatalogColumnDelDialog.jsp?ColumnID="+arr[0];
	diag.ShowMessageRow = true;
	diag.Message = "删除自定义字段，删除时请选择是否同时删除沿用栏目的自定义字段";
	diag.OKEvent = delSave;
	diag.show();
}

function delSave(){
	var dc = $DW.Form.getData("form2");
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.CatalogColumn.del",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
				$D.close();
			}
		});
	});
}

</script>
</head>
<body>
<z:init>
	<div style="padding:2px;">
	<table width="100%" cellpadding="0" cellspacing="0"
		style="margin-bottom:4px;">
		<tr>
			<td>
			<input name="CatalogID" id="CatalogID" value="${CatalogID}"type="hidden" />
			<input name="InnerCode" type="hidden" id="InnerCode"  value="${InnerCode}" />
			<z:tbutton id="BtnAdd" priv="article_manage"
				onClick="add()">
				<img src="../Icons/icon024a2.gif" />新建</z:tbutton> <z:tbutton id="BtnEdit"
				priv="article_manage" onClick="edit()">
				<img src="../Icons/icon024a4.gif" />编辑</z:tbutton> <z:tbutton id="BtnDel"
				priv="article_manage" onClick="del()">
				<img src="../Icons/icon024a3.gif" />删除</z:tbutton></td>
		</tr>
	</table>
	<z:datagrid id="dg1"
		method="com.zving.cms.site.CatalogColumn.dg1DataBind" page="false">
		<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
			<tr ztype="head" class="dataTableHead">
				<td width="4%" ztype="RowNo">&nbsp;</td>
				<td width="5%" ztype="selector" field="id">&nbsp;</td>
				<td width="15%"><b>字段ID</b></td>
				<td width="15%"><b>名称</b></td>
				<td width="14%"><b>代码</b></td>
				<td width="15%"><b>表现形式</b></td>
				<td width="12%"><b>校验类型</b></td>
				<td width="10%" align="center" ztype="Checkbox" field="IsMandatory"
					checkedvalue="Y"><b>是否必填</b></td>
				<td width="10%"><b>最大字数</b></td>
			</tr>
			<tr onDblClick="editDialog(${ID})">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>${ID}</td>
				<td>${Name}</td>
				<td>${Code}</td>
				<td>${InputTypeName}</td>
				<td>${VerifyTypeName}</td>
				<td align="center"></td>
				<td>${MaxLength}</td>
				<td>${OrderFlag}</td>
			</tr>
		</table>
	</z:datagrid></div>

</z:init>
</body>
</html>
