<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作流列表</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Name",$V("Name"));
		dr.set("Memo",$V("Memo"));
		return true;
	}
});

function save(){
	DataGrid.save("dg1","com.zving.platform.Workflow.save",function(){DataGrid.loadData('dg1');});
}

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 150;
	diag.Title = "新建工作流";
	diag.URL = "Platform/WorkflowDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = $DW.Form.getData("form2");
	Server.sendRequest("com.zving.platform.Workflow.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function editDialog(ID){
	var url = Server.ContextPath+"Platform/WorkflowEditDialog.jsp?ID="+ID;
	var width  = (screen.availWidth-10)+"px";
    var height = (screen.availHeight-50)+"px";
    var leftm  = 0;
    var topm   = 0;
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
    var w = window.open(url,"",args);
    if ( !w ){
		Dialog.alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
		return ;
	}
}


function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Dialog.confirm("您确认要删除选中的工作流吗？",function(){
		Server.sendRequest("com.zving.platform.Workflow.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData('dg1');
				}				
			});
		});
	})
}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td valign="middle" class="blockTd"><img
					src="../Icons/icon018a1.gif" /> 工作流列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
					<img src="../Icons/icon018a2.gif" />新建</z:tbutton> <z:tbutton onClick="save()">
					<img src="../Icons/icon018a4.gif" />保存</z:tbutton> <z:tbutton onClick="del()">
					<img src="../Icons/icon018a3.gif" />删除</z:tbutton></td>
			</tr>
			<tr>
				<td style="padding:0px 5px;"><z:datagrid id="dg1"
					method="com.zving.platform.Workflow.dg1DataBind" size="10">
					<table cellpadding="2" cellspacing="0" class="dataTable"
						width="100%">
						<tr ztype="head" class="dataTableHead">
							<td width="4%" ztype="RowNo">序号</td>
							<td width="3%" ztype="selector" field="ID">&nbsp;</td>
							<td width="22%"><b>工作流名称</b></td>
							<td width="67%">描述</td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td></td>
							<td><a href="#" onClick="editDialog('${ID}');">${Name}</a></td>
							<td>${Memo}</td>
						</tr>
						<tr ztype="edit" bgcolor="#E1F3FF">
							<td bgcolor="#E1F3FF">&nbsp;</td>
							<td></td>
							<td><input name="Name" type="text" class="input1" id="Name"
								value="${Name}"></td>
							<td><input name="Memo" type="text" class="input1" id="Memo"
								value="${Memo}" size="100"></td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="4" align="center">${PageBar}</td>
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
