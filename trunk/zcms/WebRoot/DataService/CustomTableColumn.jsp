<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择列</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	DataGrid.insertRow("dg1");
}

function checkData(){
	if($("dg1").DataSource.Rows.length==0){
		Dialog.alert("自定义表没有任何列!");
		return false;
	}
	var flag = false;
	var pks = $NV("isPrimaryKey");
	if(pks){
		for(var i=0;i<pks.length;i++){
			if(pks[i]=="Y"){
				flag = true;
				break;
			}
		}
	}
	if(!flag){
		Dialog.alert("必须有主键!");
		return false;
	}
	return true;
}

function getDataTable(){
	var dg = $("dg1");
	var dt = dg.DataSource;
	var names = ["OldCode","ListOptions","Code","Name","DataType","InputType","Length","isMandatory","isPrimaryKey","isAutoID"];
	var map = {};
	for(var i=0;i<names.length;i++){
		var eles = $N(names[i]);
		var arr = [];
		for(var j=0;j<eles.length;j++){
			arr.push($V(eles[j]));
		}
		map[names[i]] = arr;
	}
	for(var i=0;i<dt.getRowCount();i++){
		for(var j=0;j<names.length;j++){
			dt.Rows[i].set(names[j],map[names[j]][i]);
		}
	}
	return dt;
}

function del(){
	var dg = $("dg1");
	var arr = dg.getSelectedRows();
	if(!arr){
		Dialog.alert("没有选中的列!");
		return;
	}
	Dialog.confirm("确认删除？",function(){
		for(var i=arr.length-1;i>=0;i--){
			dg.deleteRow(arr[i].rowIndex);
		}
	});
}

function add2(){
	var dg = $("dg1");
	var arr = DataGrid.getSelectors(dg);
	if(!arr){
		Dialog.alert("没有列!");
		return;
	}
	dg.DataSource = getDataTable();
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			DataGrid.insertRow(dg,i);
			return;
		}
	}
	Dialog.alert("没有选中的列!");
}

function optionList(){
	var dt = DataGrid.getSelectedData("dg1");
	if(dt.getRowCount()==0){
		return;
	}
	var rows = DataGrid.getSelectedRows("dg1");
	var tr = rows[0];
	var dr = dt.getDataRow(0);
	var diag = new Dialog("Options");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "为字段"+dr.get("Name")+"设置选项";
	diag.URL = "DataService/CustomTableColumnOptions.jsp";
	diag.onLoad = function(){
		$DW.$S("ListOptions",$NV("ListOptions")[tr.rowIndex-1]);
	}
	diag.OKEvent = function(){
		$S($N("ListOptions")[tr.rowIndex-1],$DW.$V("ListOptions"));
		$D.close();
	}
	diag.show();
}

function checkOptionsButtonState(){
	var rows = DataGrid.getSelectedRows("dg1");
	if(!rows){
		return;
	}
	var tr = rows[0];
	var inputType = $NV("InputType")[tr.rowIndex-1];
	if(inputType=='S'||inputType=='R'||inputType=='C'){//下拉框，多选框，单选框
		$("listButton").enable();
	}else{
		$("listButton").disable();
	}
}

Page.onLoad(function(){
	$("listButton").disable();
});
</script>
</head>
<body>
  <table width="100%" border="0" cellspacing="0" cellpadding="6">
    <tr>
      <td style="padding:4px 5px;"><z:tbutton onClick="add()"><img src="../Icons/icon005a2.gif" width="20" height="20"/>添加列</z:tbutton>
	  	  <z:tbutton onClick="add2()"><img src="../Icons/icon005a2.gif" width="20" height="20"/>在选中列之上添加列</z:tbutton>
          <z:tbutton onClick="del()"><img src="../Icons/icon005a3.gif" width="20" height="20"/>删除列</z:tbutton>
          <z:tbutton id="listButton" onClick="optionList()"><img src="../Icons/icon005a18.gif" width="20" height="20"/>数据列表</z:tbutton>
      </td>
    </tr>
    <tr>
      <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
<z:init method="com.zving.cms.dataservice.CustomTable.initColumn">
<z:datagrid id="dg1" method="com.zving.cms.dataservice.CustomTable.columnDataBind" page="false">
  <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
    <tr ztype="head" class="dataTableHead">
      <td  width="3%" ztype="rowno">&nbsp;</td>
      <td width="3%" ztype="selector" field="_RowNo">&nbsp;</td>
      <td width="16%">代码</td>
      <td width="17%">名称</td>
      <td width="16%"><b>数据类型</b></td>
      <td width="16%"><b>展现类型</b></td>
      <td width="8%" align="center">数据长度</td>
	  <td width="5%" align="center">非空</td>
      <td width="5%" align="center">主键</td>
      <td width="7%" align="center">自动编号</td>
    </tr>
    <tr style1="background-color:#FFFFFF" style2="background-color:#F7F8FF" onclick="checkOptionsButtonState()">
      <td align="center">&nbsp;</td>
      <td>&nbsp;</td>
      <td><input name="Code" type="text" value="${Code}"><input name="OldCode" type="hidden" value="${Code}">
	  <textarea name="ListOptions" style="display:none">${listOptions}</textarea></td>
      <td><input name="Name" type="text" value="${Name}"></td>
      <td><z:select style="width:100px;" name="DataType" value="${DataType}"> ${@DataTypeOptions}</z:select></td>
      <td><z:select style="width:100px;" name="InputType" value="${InputType}" onChange="checkOptionsButtonState()"> ${@InputTypeOptions}</z:select></td>
      <td><input name="Length" type="text" value="${Length}" size="4"></td>
      <td align="center"><input type="checkbox" name="isMandatory" ${isMandatory} value="Y"></td>
      <td align="center"><input type="checkbox" name="isPrimaryKey" ${isPrimaryKey} value="Y"></td>
      <td align="center"><input type="checkbox" name="isAutoID" ${isAutoID} value="Y"></td>
      </tr>
	</table>
</z:datagrid>
</z:init>	  
	  </td>
    </tr>
  </table>
</body>
</html>
