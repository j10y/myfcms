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
	var diag = new Dialog("Diag1");
	diag.Width = 380;
	diag.Height = 310;
	diag.Title = "新建字段";
	diag.URL = "DataService/CustomColumnItemDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = Save;
	diag.show();
}
function Save(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	if(($DW.$V("ShowMod")=="1" || $DW.$V("ShowMod")=="2")&&$DW.$V("ListOpt")!=""){
		Dialog.alert("单行文本与多行文本不需要填写列表选项!");
		return;
	}
	dc.add("ClassCode",$V("ClassCode"));	
	Server.sendRequest("com.zving.platform.CustomColumnItem.save",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				$D.close();
				window.location.reload();	
			});
		}
		else{
			Dialog.alert(response.Message);
		}
	});
}
function editDialog(dr){
	var diag = new Dialog("Diag1");
	diag.Width = 380;
	diag.Height = 310;
	diag.Title = "修改字段";
	diag.URL = "DataService/CustomColumnItemDialog.jsp";
	diag.onLoad = function(){
		$DW.$S("ID",dr.get("ID"));
		$DW.$S("Name",dr.get("Name"));
		$DW.$S("Code",dr.get("Code"));
		$DW.$S("Type",dr.get("Type_Code"));
		$DW.$S("Length",dr.get("Length"));
		$DW.$S("ShowMod",dr.get("ShowMod_Code"));
		$DW.$S("DefaultValue",dr.get("DefaultValue"));
		$DW.$S("ListOpt",dr.get("ListOpt"));
		if(dr.get("MandatoryFlag_Code")=="0"){
			$DW.$("MandatoryFlag").checked="";
		}
		$DW.$("Name").focus();
	};
	diag.OKEvent = Save;
	diag.show();
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
		Server.sendRequest("com.zving.platform.CustomColumnItem.del",dc,function(response){
			if(response.Status==1){
				Dialog.alert("删除成功",function(){
					window.location="DataService/CustomColumnItem.jsp?ClassCode=" + $V("ClassCode");
				});
			}
			else{
				Dialog.alert(response.Message);
			}
		});
	})
}
function back(){
	window.location="CustomColumn.jsp";
}
</script>
</head>
<body>
<z:init method="com.zving.platform.CustomColumnItem.init">
<input name="ClassCode" id="ClassCode" value="${ClassCode}"
		type="hidden" />
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon002a1.gif" /> ${ClassCode} 自定义列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
						<img src="../Icons/icon024a2.gif" />新增</z:tbutton> <z:tbutton onClick="del()">
						<img src="../Icons/icon024a3.gif" />删除</z:tbutton> <z:tbutton
						onClick="back()">
						<img src="../Icons/icon400a8.gif" />返回</z:tbutton></td>
				</tr>
				<tr>   <td style="padding:0px 5px;">
<z:datagrid id="dg1"
						method="com.zving.platform.CustomColumnItem.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="4%" ztype="RowNo">&nbsp;</td>
								<td width="4%" ztype="selector" field="id">&nbsp;</td>
								<td width="10%"><b>业务库代码</b></td>
								<td width="17%"><b>字段名称</b></td>
								<td width="16%"><b>字段代码</b></td>
								<td width="12%"><b>数据类型</b></td>
								<td width="12%"><b>字段长度</b></td>
								<td width="10%"><b>显示形式</b></td>
								<td width="10%"><b>是否非空</b></td>
								<td width="5%" ztype="editDialog" function='editDialog'><b>编辑</b></td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td  width="5">&nbsp;</td>
								<td width="20">&nbsp;</td>
								<td>${ClassCode}</td>
								<td>${Name}</td>
								<td>${Code}</td>
								<td>${Type}</td>
								<td>${Length}</td>
								<td>${ShowMod}</td>
								<td>${MandatoryFlag}</td>
								<td>&nbsp;</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="10" align="center">${PageBar}</td>
							</tr>
						</table>
					</z:datagrid></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html>
</z:init>
