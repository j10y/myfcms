<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代码</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<z:init method="com.zving.platform.Code.initList">
<script language="javascript">
Page.onLoad(function(){
	$("dg1").afterEdit = function(tr,dr){
		dr.set("CodeValue",$V("CodeValue"));
		dr.set("CodeName",$V("CodeName"));
		dr.set("Memo",$V("Memo"));
		return true;
	}
});

function save(){
	DataGrid.save("dg1","com.zving.platform.Code.dg1Edit",function(){DataGrid.loadData('dg1');});
}

function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 350;
	diag.Height = 150;
	diag.Title = "新建代码";
	diag.URL = "Platform/CodeDialog.jsp";
	diag.onLoad = function(){
		$DW.$("CodeType").value="${CodeType}";
		$DW.$("CodeType").disabled=true;
		$DW.$("CodeValue").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	dc.add("ParentCode","${CodeType}");
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.platform.Code.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});
	});
}

function del(){
	var dt = DataGrid.getSelectedData("dg1");
	if(dt.getRowCount()==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	var dc = new DataCollection();
	dc.add("DT",dt);
	Server.sendRequest("com.zving.platform.Code.del",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData('dg1');
			}		
		});
	});
}

function afterRowDragEnd(type,targetDr,sourceDr,rowIndex,oldIndex){
	if(rowIndex==oldIndex){
		return;
	}
	var order = $("dg1").DataSource.get(rowIndex-1,"CodeOrder");
	var target = "";
	var dc = new DataCollection();
	var ds = $("dg1").DataSource;
	var i = rowIndex-1;
	if(i!=0){
		target = ds.get(i-1,"CodeOrder");
		dc.add("Type","Before");		
	}else{
		dc.add("Type","After");
		target = $("dg1").DataSource.get(1,"CodeOrder");
	}
	dc.add("Target",target);
	dc.add("Orders",order);
	dc.add("ParentCode",ds.get(0,"ParentCode"));
	DataGrid.showLoading("dg1");
	Server.sendRequest("com.zving.platform.Code.sortColumn",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
			}
		});
	});
}
</script>
</head>
<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon018a1.gif" /> ${CodeType}: <b>${CodeName}</b> 的代码列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon018a2.gif" />新建</z:tbutton> 
					<z:tbutton onClick="save()"><img src="../Icons/icon018a4.gif" />保存</z:tbutton> 
					<z:tbutton onClick="del()"><img src="../Icons/icon018a3.gif" />删除</z:tbutton>
					</td>
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1"
						method="com.zving.platform.Code.dg1BindCodeList">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable" afterdrag="afterRowDragEnd">
							<tr ztype="head" class="dataTableHead">
								<td  width="5%" ztype="RowNo" drag="true"><img src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
								<td width="5%" ztype="selector" field="id">&nbsp;</td>
								<td width="15%"><strong>代码值</strong></td>
								<td width="15%"><b>代码名称</b></td>
								<td width="20%"><strong>所属代码类别</strong></td>
								<td width="30%">备注</td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td >&nbsp;</td>
								<td>&nbsp;</td>
								<td>${CodeValue}</td>
								<td>${CodeName}</td>
								<td>${CodeType}</td>
								<td>${Memo}</td>
							</tr>
							<tr ztype="edit" bgcolor="#E1F3FF">
								<td  bgcolor="#E1F3FF">&nbsp;</td>
								<td>&nbsp;</td>
								<td><input name="CodeValue" type="text" id="CodeValue"
									value="${CodeValue}" size="10"></td>
								<td><input name="CodeName" type="text" id="CodeName"
									value="${CodeName}" size="10"></td>
								<td>${CodeType}</td>
								<td><input name="Memo" type="text" id="Memo"
									value="${Memo}" size="30"></td>
							</tr>
						</table></z:datagrid></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</z:init>
</html>
