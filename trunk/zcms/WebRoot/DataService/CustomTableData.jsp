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
function save(){
	if(Verify.hasError()){
		return;
	}
	var inputs = $($("dg1").rows[1]).$T("input");
	var cols = [];
	var arrs = [];
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].type=="checkbox"){
			continue;
		}
		cols.push([inputs[i].name,1]);//1表示String类型
		arrs.push($NV(inputs[i].name));
	}
	if(inputs.length==0){
		return;
	}
	var data = [];
	for(var i=0;i<arrs[0].length;i++){
		var row = [];
		for(var j=0;j<cols.length;j++){
			row.push(arrs[j][i]);
		}
		data.push(row);
	}
	var dt = new DataTable();
	dt.init(cols,data);
	var dc = new DataCollection();
	dc.add("Data",dt);
	dc.add("ID",$V("ID"));
	Server.sendRequest("com.zving.cms.dataservice.CustomTable.saveData",dc,function(response){
		Dialog.alert(response.Message);
	});
}

function del(){
	var dt = $("dg1").getSelectedData();
	var dc = new DataCollection();
	dc.add("Data",dt);
	dc.add("ID",$V("ID"));
	Server.sendRequest("com.zving.cms.dataservice.CustomTable.delData",dc,function(response){
		Dialog.alert(response.Message);
		$("dg1").loadData();
	});
}

function query(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 100;
	diag.Title = "导入自定义数据";
	diag.URL = "DataService/CustomTableDataImportStep1.jsp";
	diag.OKEvent = function(){
		$DW.$("form1").submit();
		Dialog.wait("开始上传文件，请稍等....");
	}
	diag.onLoad = function(){
		$DW.$("Name").innerHTML = dr.get("Name");
	}
	diag.show();
}

function importData(){
	var diag = new Dialog("DialogImport");
	diag.Width = 400;
	diag.Height = 100;
	diag.Title = "导入自定义数据";
	diag.URL = "DataService/CustomTableDataImportStep1.jsp?ID="+$V("ID");
	diag.OKEvent = function(){
		$DW.$("form1").submit();
		Dialog.wait("开始上传文件，请稍等....");
	}
	diag.show();
}

function executeImportData(){
	var dc = new DataCollection();
	dc.add("ID",$DW.$V("ID"));
	dc.add("FileName",$DW.$V("FileName"));
	Dialog.wait("正在导入数据...");
	Server.sendRequest("com.zving.cms.dataservice.CustomTable.importData",dc,function(response){
		Dialog.endWait();
		$D.close();
		Dialog.alert(response.Message);
		$("dg1").loadData();
	});
}

function exportData(){
	window.location = "CustomTableDataExport.jsp?ID="+$V("ID");
}
</script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.CustomTable.initDataEditGrid">
  <table width="100%" border="0" cellspacing="0" cellpadding="6">
    <tr>
      <td style="padding:4px 5px;">
	  <z:tbutton onClick="$('dg1').insertRow()"><img src="../Icons/icon005a2.gif" width="20" height="20"/>添加</z:tbutton>
	  <z:tbutton onClick="save()"><img src="../Icons/icon005a4.gif" width="20" height="20"/>保存</z:tbutton>
	  <!--<z:tbutton onClick="query()"><img src="../Icons/icon005a15.gif" width="20" height="20"/>查询</z:tbutton>-->
	  <z:tbutton onClick="del()"><img src="../Icons/icon005a3.gif" width="20" height="20"/>删除</z:tbutton>
	  <z:tbutton onClick="exportData()"><img src="../Icons/icon005a7.gif" width="20" height="20"/>导出数据</z:tbutton>
	  <z:tbutton onClick="importData()"><img src="../Icons/icon005a9.gif" width="20" height="20"/>导入数据</z:tbutton>
      </td>
    </tr>
   <tr>
      <td style="padding:2px;">
	  	<input type="hidden" id="ID" value="${ID}">
		${HTML}
	  </td>
    </tr>
  </table>
</z:init>
</body>
</html>
