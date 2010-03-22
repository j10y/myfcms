<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>扩展属性</title>
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
	diag.Height = 250;
	diag.Title = "新建扩展属性";
	diag.URL = "Site/CatalogExtendDialog.jsp";
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
	var content = $DW.getContent();
	if($DW.htmlinput==inputType){
		if(content==""||content=="<br/>"){
			alert("属性内容不能为空");
			return;
		}else{
			dc.add("Content",content);
		}
	}else{
		if($DW.input==inputType){
			noCheckArr.push($DW.$("ImagePath"));
		}else if($DW.imageInput==inputType){
			noCheckArr.push($DW.$("TextValue"));
		}
		if($DW.Verify.hasError(noCheckArr,"form2")){
			return;
		}
	}
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.CatalogExtend.add",dc,function(response){
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
  editDialog(dr.get("ColumnID"));
}

function editDialog(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 250;
	diag.Title = "编辑扩展属性";
	diag.URL = "Site/CatalogExtendEditDialog.jsp?ColumnID="+ID+"&CatalogID="+$V("CatalogID");
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
	var content = $DW.getContent();
	if($DW.htmlinput==inputType){
		if(content==""||content=="<br/>"){
			alert("属性内容不能为空");
			return;
		}else{
			dc.add("Content",content);
		}
	}else{
		if($DW.input==inputType){
			noCheckArr.push($DW.$("ImagePath"));
		}else if($DW.imageInput==inputType){
			noCheckArr.push($DW.$("TextValue"));
		}
		if($DW.Verify.hasError(noCheckArr,"form2")){
			return;
		}
	}
	dc.add("CatalogID",$V("CatalogID"));
	Server.sendRequest("com.zving.cms.site.CatalogExtend.save",dc,function(response){
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
	Dialog.confirm("您确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("CatalogID",$V("CatalogID"));
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.site.CatalogExtend.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataGrid.loadData("dg1");
				}
			});
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
			<input name="CatalogID" id="CatalogID" value="${CatalogID}" type="hidden" />
			<input name="InnerCode" type="hidden" id="InnerCode"  value="${InnerCode}" />
			<z:tbutton id="BtnAdd" priv="article_manage"
				onClick="add()">
				<img src="../Icons/icon018a2.gif" />新建</z:tbutton> <z:tbutton id="BtnEdit"
				priv="article_manage" onClick="edit()">
				<img src="../Icons/icon018a4.gif" />编辑</z:tbutton> <z:tbutton id="BtnDel"
				priv="article_manage" onClick="del()">
				<img src="../Icons/icon018a3.gif" />删除</z:tbutton></td>
		</tr>
	</table>
	<z:datagrid id="dg1"
		method="com.zving.cms.site.CatalogExtend.dg1DataBind" page="false"
		autoFill="true">
		<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
			<tr ztype="head" class="dataTableHead">
				<td width="6%" ztype="RowNo">&nbsp;</td>
				<td width="5%" ztype="selector" field="ColumnID">&nbsp;</td>
				<td width="13%"><b>属性名称</b></td>
				<td width="12%"><b>代码</b></td>
				<td width="14%"><b>类型</b></td>
				<td width="50%"><b>属性内容</b></td>
			</tr>
			<tr style1="background-color:#FFFFFF"
				style2="background-color:#F9FBFC"
				onDblClick="editDialog(${ColumnID})">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>${Name}</td>
				<td>${Code}</td>
				<td>${InputTypeName}</td>
				<td>${TextValue}</td>
			</tr>
		</table>
	</z:datagrid></div>
</z:init>
</body>
</html>
