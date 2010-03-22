<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定时任务</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 320;
	diag.Title = "新建配置";
	diag.URL = "DataChannel/DeployConfigDialog.jsp";
	diag.onLoad = function(){
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}

  dc.add("Method",$DW.$NV('Method'));
  dc.add("UseFlag",$DW.$NV("UseFlag"));
	Server.sendRequest("com.zving.cms.datachannel.DeployConfig.add",dc,function(response){
		if(response.Status==1){
			$D.close();
			DataGrid.setParam("dg1",Constant.PageIndex,0);
      DataGrid.loadData("dg1");
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function edit(){
	var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请先选择要修改的配置！");
		return;
	}
	if(drs.length>1){
		Dialog.alert("只能修改1条信息！");
		return;
	}
	dr = drs[0]; 
  editDialog(dr);
}


function editDialog(dr){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 320;
	diag.Title = "修改配置";
	diag.URL = "DataChannel/DeployConfigDialog.jsp";
	diag.onLoad = function(){
    $DW.$S("ID",dr.get("ID"));
		$DW.$S("SourceDir",dr.get("SourceDir"));
		$DW.$S("TargetDir",dr.get("TargetDir"));
		$DW.$S("Host",dr.get("Host"));
		$DW.$S("Port",dr.get("Port"));
		$DW.$S("UserName",dr.get("UserName"));
		$DW.$S("Password",dr.get("Password"));
    $DW.$NS("Method",dr.get("Method"));
		$DW.$NS("UseFlag",dr.get("UseFlag"));
		$DW.$("SourceDir").focus();
		
		$DW.changeMethod();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}

  dc.add("Method",$DW.$NV('Method'));
  dc.add("UseFlag",$DW.$NV("UseFlag"));
	Server.sendRequest("com.zving.cms.datachannel.DeployConfig.edit",dc,function(response){
		if(response.Status==1){
			Dialog.alert("修改成功",function(){
				 DataGrid.loadData("dg1");
         $D.close();
			});
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确定要删除该配置项吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.datachannel.DeployConfig.del",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("删除成功");
        DataGrid.loadData("dg1");
			}
		});
	});
} 

function deploy(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要分发的行！");
		return;
	}
	Dialog.confirm("分发任务将扫描目录，确定要立即执行选中的任务吗？",function(){
			var dc = new DataCollection();	
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.cms.datachannel.DeployConfig.deploy",dc,function(response){
				if(response.Status==0){
					Dialog.alert(response.Message);
				}else{
					Dialog.alert("已经将任务添加到队列中，请查看执行日志。");
	        DataGrid.loadData("dg1");
				}
			});
	});
}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="1">
	<tr>
		<td style="padding:0 0 6px;">
		<z:tbutton onClick="add()"><img src="../Icons/icon018a2.gif" />新建</z:tbutton> 
		<z:tbutton onClick="edit()"><img src="../Icons/icon018a4.gif" />编辑</z:tbutton> 
		<z:tbutton onClick="deploy()"><img src="../Icons/icon018a4.gif" />立即分发</z:tbutton> 
		<z:tbutton onClick="del()"><img src="../Icons/icon018a3.gif" />删除</z:tbutton></td>
	</tr>
	<tr>
		<td>
		<z:datagrid id="dg1" method="com.zving.cms.datachannel.DeployConfig.dg1DataBind" size="14">
			<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr ztype="head" class="dataTableHead">
					<td width="6%" ztype="RowNo">&nbsp;</td>
					<td width="6%" ztype="selector" field="id">&nbsp;</td>
					<td width="13%"><b>复制方式</b></td>
					<td width="26%"><b>本地目录</b></td>
					<td width="18%"><b></b>服务器地址</td>
					<td width="19%"><b></b>目标目录</td>
					<td width="12%"><b>状态</b></td>
				</tr>
				<tr onDblClick="edit()">
					<td align="center">&nbsp;</td>
					<td>&nbsp;</td>
					<td>${MethodName}</td>
					<td>${SourceDir}</td>
					<td>${Host}</td>
					<td>${TargetDir}</td>
					<td>${UseFlag}</td>
				</tr>
				<tr ztype="pagebar">
					<td colspan="7">${PageBar}</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>
