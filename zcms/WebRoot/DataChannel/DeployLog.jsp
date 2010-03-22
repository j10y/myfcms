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
	Dialog.confirm("确定要删除该日志项吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.datachannel.DeployLog.del",dc,function(response){
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

function delAll(){
	  Dialog.confirm("确定要清除日志吗？",function(){
	  		var dc = new DataCollection();	
				Server.sendRequest("com.zving.cms.datachannel.DeployLog.delAll",dc,function(response){
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
	diag.Height = 300;
	diag.Title = "查看信息";
	diag.URL = "DataChannel/DeployLogDialog.jsp?ID="+ID;
	diag.onLoad = function(){
	};
	diag.OKEvent = function(){
		$D.close();
	};
	diag.show();
}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="1">
	<tr>
		<td style="padding:0 0 6px;">
		<z:tbutton onClick="view()"><img src="../Icons/icon018a1.gif" />查看详情</z:tbutton> 
		<z:tbutton onClick="delAll()"><img src="../Icons/icon018a3.gif" />清空</z:tbutton> 
		<z:tbutton onClick="del()"><img src="../Icons/icon018a3.gif" />删除</z:tbutton> 
		<z:tbutton onClick="refresh()"><img src="../Icons/icon018a13.gif" />刷新</z:tbutton></td>
	</tr>
	<tr>
		<td>
		<z:datagrid id="dg1" method="com.zving.cms.datachannel.DeployLog.dg1DataBind" size="14">
			<table width="100%" cellpadding="0" cellspacing="0" class="dataTable">
				<tr ztype="head" class="dataTableHead">
					<td width="5%" ztype="RowNo">&nbsp;</td>
					<td width="5%" ztype="selector" field="id">&nbsp;</td>
					<td width="9%"><b>复制方式</b></td>
					<td width="17%"><b>本地目录</b></td>
					<td width="8%"><b></b>目标目录</td>
					<td width="10%"><b></b>服务器地址</td>
					<td width="29%"><b>消息</b></td>
					<td width="17%"><b>执行时间</b></td>
				</tr>
				<tr onDblClick="viewDialog(${ID})">
					<td align="center">&nbsp;</td>
					<td>&nbsp;</td>
					<td>${methodDesc}</td>
					<td>${Source}</td>
					<td>${Target}</td>
					<td>${Host}</td>
					<td>${Message}</td>
					<td>${BeginTime}</td>
				</tr>
				<tr ztype="pagebar">
					<td colspan="8">${PageBar}</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>
