<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>地区代码管理</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script language="javascript">
Page.onLoad(function(){
	$("dg2").afterEdit = function(tr,dr){
		dr.set("Name",$V("Name"));
		dr.set("CodeOrder",$V("CodeOrder"));
		dr.set("TreeLevel",$V("TreeLevel"));
		dr.set("Type",$V("Type"));
		return true;
	}
});

function save(){
	DataGrid.save("dg2","com.zving.platform.District.dg1Edit",function(){
		DataGrid.loadData('dg2');
	});
}

function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 300;
	diag.Height = 150;
	diag.Title = "新建地区";
	diag.URL = "Platform/DistrictDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Code").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.platform.District.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData('dg2');
				$D.close();
			}
		});
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg2");
	if(!arr||arr.length==0){
		alert("请先选择要删除的行！");
		return;
	}
	if(!window.confirm("确定要删除该地区吗？")){
		return;
	}
	var dc = new DataCollection();	
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.platform.District.del",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg2");
			}
		});
	});
}
</script>
</head>
<body>
<z:init method="com.zving.platform.District.init">
<input name="parentName" id="parentName" type="hidden" value="${Name}"/>
<input name="CodePK" type="hidden" id="CodePK" value="${Code}">
	<table width="100%" border="0" cellspacing="8" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="8" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon018a1.gif"/> ${Name}地区列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
						<img src="../Icons/icon018a2.gif"/>新建</z:tbutton>
					<z:tbutton id="addButton" onClick="save()">
						<img src="../Icons/icon018a4.gif"/>保存</z:tbutton>
					<z:tbutton onClick="del()">
						<img src="../Icons/icon018a3.gif"/>删除</z:tbutton> 
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg2" method="com.zving.platform.District.dg1BindDistrictList" page="false">
						<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="5%" ztype="RowNo"><b>序号</b></td>
								<td width="5%" ztype="selector" field="Code">&nbsp;</td>
								<td width="20%"><b>地区</b></td>
								<td width="20%"><b>编号</b></td>
								<td width="20%"><b>地区顺序</b></td>
								<td width="10%"><b>级别</b></td>
								<td width="10%"><b>类型</b></td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td>&nbsp;</td>
								<td></td>
								<td>${Name}</td>
								<td>${Code}</td>
								<td>${CodeOrder}</td>
								<td>${TreeLevel}</td>
								<td>${Type}</td>
							</tr>
							<tr ztype="edit" bgcolor="#E1F3FF">
								<td bgcolor="#E1F3FF">&nbsp;</td>
								<td>&nbsp;</td>
								<td><input name="Name" type="text" id="Name"
									value="${Name}" size="20"></td>
								<td>${Code}</td>	
								<td><input name="CodeOrder" type="text" id="CodeOrder"
									value="${CodeOrder}" size="20"></td>
								<td><input name="TreeLevel" type="text" id="TreeLevel"
									value="${TreeLevel}" size="20"></td>
								<td><input name="Type" type="text" id="Type"
									value="${Type}" size="20"></td>
							</tr>
						</table></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</z:datagrid>
</z:init>
</body>
</html>