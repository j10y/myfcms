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
function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确定要删除该任务项吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.datachannel.DeployJob.del",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("删除成功");
				DataGrid.setParam("dg1",Constant.PageIndex,0);
        DataGrid.loadData("dg1");
			}
		});
	});
} 

function reExecuteJob(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要重新执行的任务！");
		return;
	} else {
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.datachannel.DeployJob.reExecuteJob",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("分发成功");
				DataGrid.setParam("dg1",Constant.PageIndex,0);
        DataGrid.loadData("dg1");
			}
		});
	}
}

function executeFailJob(){
	var arr = DataGrid.getSelectedValue("dg1");
		var dc = new DataCollection();	
		Server.sendRequest("com.zving.cms.datachannel.DeployJob.executeFailJob",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("分发成功");
				DataGrid.setParam("dg1",Constant.PageIndex,0);
        DataGrid.loadData("dg1");
			}
		});
}

function refresh(){
		DataGrid.setParam("dg1",Constant.PageIndex,0);
    DataGrid.loadData("dg1");
}

function view(){
	var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请先选择要查看的行！");
		return;
	}
	if(drs.length>1){
		Dialog.alert("只能修改1条信息！");
		return;
	}
	dr = drs[0]; 
  viewDialog(dr.get("ID"));
}

function viewDialog(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 250;
	diag.Title = "查看信息";
	diag.URL = "DataChannel/DeployJobDialog.jsp?ID="+ID;
	diag.onLoad = function(){
	};
	diag.OKEvent = function(){
		$D.close();
	};
	diag.show();
}

function delAll(){
	  Dialog.confirm("确定要清空所有任务？",function(){
	  		var dc = new DataCollection();	
				Server.sendRequest("com.zving.cms.datachannel.DeployJob.delAll",dc,function(response){
					if(response.Status==0){
						Dialog.alert(response.Message);
					}else{
						Dialog.alert("删除成功");
						DataGrid.setParam("dg1",Constant.PageIndex,0);
		        DataGrid.loadData("dg1");
					}
				});
	  });
} 
</script>
</head>
<z:init method="com.zving.cms.datachannel.DeployJob.init">
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="1">
		<tr>
			<td style="padding:0 0 6px;">
			            <z:tbutton onClick="view()"> <img src="../Icons/icon018a1.gif" />查看详情</z:tbutton>
                  <z:tbutton onClick="reExecuteJob()"> <img src="../Icons/icon018a4.gif" />重新执行</z:tbutton>
                  <z:tbutton onClick="executeFailJob()"> <img src="../Icons/icon018a4.gif" />执行失败任务</z:tbutton>
                  <z:tbutton onClick="del()"> <img src="../Icons/icon018a3.gif" />删除</z:tbutton>
                  <z:tbutton onClick="delAll()"> <img src="../Icons/icon018a3.gif" />清空</z:tbutton>
                  <z:tbutton onClick="refresh()"> <img src="../Icons/icon018a13.gif" />刷新</z:tbutton>
       </td>
		</tr>
		<tr>
			<td>
			<z:datagrid id="dg1" method="com.zving.cms.datachannel.DeployJob.dg1DataBind" size="14">
				<table width="100%" cellpadding="0" cellspacing="0" class="dataTable">
					<tr ztype="head" class="dataTableHead">
						<td width="5%" ztype="RowNo">&nbsp;</td>
						<td width="4%" ztype="selector" field="id">&nbsp;</td>
						<td width="20%"><b>本地文件</b></td>
						<td width="19%"><b>目标</b></td>
						<td width="11%"><b>复制方式</b></td>
						<td width="21%"><b></b>服务器地址</td>
						<td width="11%"><b>状态</b></td>
					</tr>
					<tr onDblClick="viewDialog(${ID})">
						<td align="center">&nbsp;</td>
						<td>&nbsp;</td>
						<td>${Source}</td>
						<td>${Target}</td>
						<td>${MethodName}</td>
						<td>${Host}</td>
						<td>${statusName}</td>
					</tr>
					<tr ztype="pagebar">
						<td colspan="7">${PageBar}</td>
					</tr>
				</table>
			</z:datagrid></td>
		</tr>
	</table>
	</body>
</z:init>
</html>
