<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员信息管理</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>

function edit(ID){
    var arr = DataGrid.getSelectedValue("dg1");
	if(ID&&ID!=""){
		arr = new Array();
		arr[0] = ID;
	}
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要回复的留言！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 380;
	diag.Title = "留言回复";
	diag.URL = "DataService/MessageDialog.jsp?ID="+arr[0];
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	Server.sendRequest("com.zving.cms.dataservice.MessageBoard.save",dc,function(){
		var response = Server.getResponse();
		if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
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
	Dialog.confirm("确定要删除选中的留言吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.MessageBoard.del",dc,function(){
			var response = Server.getResponse();
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert(response.Message);
				DataGrid.loadData("dg1");
			}
		});
	});
} 

function doSearch(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","PublishFlag",$V("PublishFlag"));
	DataGrid.setParam("dg1","ReplyFlag",$V("ReplyFlag"));
	DataGrid.loadData("dg1");
}

function doCheck(publishflag){
	if(publishflag==""){
		return;
	}
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要审核的留言！");
		return;
	}
	var dc = new DataCollection();	
	dc.add("IDs",arr.join(","));
	dc.add("PublishFlag",publishflag);
	Server.sendRequest("com.zving.cms.dataservice.MessageBoard.doCheck",dc,function(){
		var response = Server.getResponse();
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			DataGrid.loadData('dg1');
		}
	});
}

function download(attid){
	if(!attid||attid==""){
		return;
	}else{
		window.open(Server.ContextPath + "DataService/MessageDownload.jsp?id="+attid,"_blank");
	}
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
					<td valign="middle" class="blockTd"><img src="../Icons/icon034a12.gif" />留言列表</td>
				</tr>
				<tr>
					<td style="padding:8px 10px;">
                    <div style="float: right">
                    回复状态：
                    <z:select id="ReplyFlag" name="ReplyFlag" style="width:80px" listWidth="80" onChange="doSearch();">
					<select><option value=""></option>
						<option value="0">未回复</option>
						<option value="1">已回复</option></select>
					</z:select>&nbsp;&nbsp;
                    审核状态：
                    <z:select id="PublishFlag" name="PublishFlag" style="width:80px" listWidth="80" onChange="doSearch();">
					<select><option value=""></option>
						<option value="0">等待审核</option>
						<option value="1">审核通过</option></select>
					</z:select></div>
                    <z:tbutton onClick="edit()"><img src="../Icons/icon034a4.gif" />回复</z:tbutton> 
                    <z:tbutton onClick="del()"><img src="../Icons/icon034a3.gif" />删除</z:tbutton>
                    <z:tbutton onClick="doCheck('1')"><img src="../Icons/icon404a4.gif" width="20" height="20" />审核通过</z:tbutton>
					</td>
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1" method="com.zving.cms.dataservice.MessageBoard.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="3%" ztype="RowNo">&nbsp;</td>
								<td width="3%" ztype="selector" field="ID">&nbsp;</td>
								<td width="16%"><b>留言人</b></td>
								<td width="20%"><b>标题</b></td>
								<td width="38%"><b>留言内容</b></td>
								<td width="14%"><b>附件</b></td>
								<td width="6%"><b>审核状态</b></td>
							</tr>
							<tr onDblClick="edit(${ID})" style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>${AddUser}</td>
								<td><span title="${Title}">${Title}</span>&nbsp;${ReplyFlagName}</td>
								<td><span title="${Content}">${Content}</span></td>
								<td><a href="#;" title="${Prop1}" onClick="download(${ID});">${Prop1}</a></td>
								<td width="10%">${PublishFlagName}</td>
							</tr>
							<tr ztype="pagebar">
								<td height="25" colspan="7">${PageBar}</td>
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
