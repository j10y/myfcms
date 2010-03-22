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

function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 450;
	diag.Height = 350;
	diag.Title = "新建自定义字段";
	diag.URL = "DataService/ColumnDialog.jsp";
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
	}
	if($DW.Verify.hasError(noCheckArr,"form2")){
		return;
	}
	if(!$DW.$NV("IsMandatory")){
		dc.add("IsMandatory","N");
	}
	dc.add("FormID",$V("FormID"));
	Server.sendRequest("com.zving.cms.dataservice.Column.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function edit(){
  	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要修改的行！");
		return;
	}
	var diag = new Dialog("Diag2");
	diag.Width = 450;
	diag.Height = 350;
	diag.Title = "修改字段";
	diag.URL = "DataService/ColumnDialog.jsp?ColumnID="+arr[0];
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
		noCheckArr.push($DW.$("ListOption"));
		noCheckArr.push($DW.$("MaxLength"));
	}else if($DW.imageInput==inputType){
		noCheckArr.push($DW.$("Cols"));
		noCheckArr.push($DW.$("Rows"));
		noCheckArr.push($DW.$("ListOption"));
		noCheckArr.push($DW.$("MaxLength"));
	}
	if($DW.Verify.hasError(noCheckArr,"form2")){
		return;
	}
	if(!$DW.$NV("IsMandatory")){
		dc.add("IsMandatory","N");
	}
	dc.add("FormID",$V("FormID"));
	Server.sendRequest("com.zving.cms.dataservice.Column.edit",dc,function(response){
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
	Dialog.alert("确定要删除该字段吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.Column.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData("dg1");
				}
			});
		});
	});
}

function preview(){
	if(screen.width==800){
		var width = 800,height = 600,leftm = 0,topm = 0;
	}
	else if(screen.width>800){
	  	var width  = Math.floor( screen.width  * .78 );
  		var height = Math.floor( screen.height * .8 );
  		var leftm  = Math.floor( screen.width  * .1);
 		var topm   = Math.floor( screen.height * .1);
	}
	else{
		return;
	}
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
  	var url="FormPreview.jsp?ID=" + $V("FormID");
  	window.open(url,"",args);
}

function back(){
	window.location="Form.jsp";
}

function afterRowDragEnd(rowIndex,oldIndex){
	if(rowIndex==oldIndex){
		return;
	}
	var order = $("dg1").DataSource.get(rowIndex-1,"OrderFlag");
	var target = "";
	var dc = new DataCollection();
	var ds = $("dg1").DataSource;
	var i = rowIndex-1;
	if(i!=0){
		target = ds.get(i-1,"OrderFlag");
		dc.add("Type","Before");		
	}else{
		dc.add("Type","After");
		target = $("dg1").DataSource.get(1,"OrderFlag");
	}
	dc.add("Target",target);
	dc.add("Orders",order);
	dc.add("FormID",$V("FormID"));
	DataGrid.showLoading("dg1");
	Server.sendRequest("com.zving.cms.dataservice.Column.sortColumn",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
			}
		});
	});
}

function createTb(){
	 var dc = new DataCollection();
	 dc.add("FormID",$V("FormID"))
		Server.sendRequest("com.zving.cms.dataservice.FormTable.create",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
				}
			});
		});
}
</script>
</head>
<body>
<z:init>
<input name="FormID" id="FormID" value="${FormID}"	type="hidden" />
</z:init>
<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
	<tr>
		<td style="padding:8px 10px;" class="blockTd">
		<z:tbutton onClick="add()"><img src="../Icons/icon024a2.gif" />新建</z:tbutton>
		<z:tbutton onClick="edit()"><img src="../Icons/icon024a4.gif" />编辑</z:tbutton>
		<z:tbutton onClick="del()"><img src="../Icons/icon024a3.gif" />删除</z:tbutton>
		<z:tbutton onClick="preview()"><img src="../Icons/icon024a15.gif"/>预览</z:tbutton>
		<z:tbutton onClick="createTb()"><img src="../Icons/icon024a2.gif" />创建物理表</z:tbutton>
	</tr>
	<tr>   <td style="padding:0px 5px;">
<z:datagrid id="dg1"
			method="com.zving.cms.dataservice.Column.dg1DataBind">
			<table width="100%" cellpadding="2" cellspacing="0"
				class="dataTable" afterdrag="afterRowDragEnd">
				<tr ztype="head" class="dataTableHead">
					<td width="4%" ztype="RowNo" drag="true"><img src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
					<td width="4%" ztype="selector" field="id">&nbsp;</td>
					<td width="12%"><b>名称</b></td>
					<td width="11%"><b>代码</b></td>
					<td width="10%"><b>展现形式</b></td>
					<td width="10%"><b>校验类型</b></td>
					<td width="9%"><b>数据类型</b></td>
					<td width="4%" ztype="Checkbox" field ="IsMandatory" checkedvalue="Y"><b>必填</b></td>
					<td width="8%"><b>最大字数</b></td>
					<td width="9%"><b>默认值</b></td>
					<td width="19%"><b>列表选项</b></td>
				</tr>
				<tr onDblClick="edit();">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>${Name}</td>
					<td>${Code}</td>
					<td>${InputTypeName}</td>
					<td>${VerifyTypeName}</td>
					<td>${DataTypeName}</td>
					<td></td>
					<td>${MaxLength}</td>
					<td>${DefaultValue}</td>
					<td>${ListOption}</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>