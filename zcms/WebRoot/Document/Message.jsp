<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag =new Dialog("diag1");
	diag.Width = 500;
	diag.Height = 250;
	diag.Title = "新消息";
	diag.URL = "Document/MessageDialog.jsp";
	diag.onLoad = function(){
		$DW.$("ToUser").focus();
	};
	diag.OKEvent = addSave
	diag.show();
}

function reply(){
    var dt = DataGrid.getSelectedData("dg1");
	var dr = dt.Rows;
	if(!dt||dr.length==0){
		Dialog.alert("请先选择要回复的信息！");
		return;
	}
	if(dr.length>1){
		Dialog.alert("只能回复1条信息！");
		return;
	}

	var diag =new Dialog("diag1");
	diag.Width = 500;
	diag.Height = 200;
	diag.Title = "回复消息";
	diag.URL = "Document/MessageReplyDialog.jsp?ID="+dr[0].get("ID");
	diag.OKEvent = ReplySave;
	diag.show();
}

function addSave(){
	if($DW.Verify.hasError()){
		return;
    }
	var dc = Form.getData($DW.$F("formMsg"));
	Server.sendRequest("com.zving.cms.document.Message.add",dc,function(response){
		if(response.Status==1){
			DataGrid.setParam("dg1",Constant.PageIndex,0);
			DataGrid.loadData("dg1");
			$D.close();
		}else{
		   Dialog.alert(response.Message);
		}
	})
}

function ReplySave(){
	if($DW.Verify.hasError()){
		return;
    }
	var dc = Form.getData($DW.$F("formMsg"));
	Server.sendRequest("com.zving.cms.document.Message.reply",dc,function(response){
		if(response.Status==1){
			DataGrid.setParam("dg1",Constant.PageIndex,0);
			DataGrid.loadData("dg1");
			Dialog.alert(response.Message);
			$D.close();
		}else{
		   Dialog.alert(response.Message);
		}
	})
}

function MessageHistory(){
	var diag =new Dialog("diag1");
	diag.Width = 700;
	diag.Height = 370;
	diag.Title = "已发消息";
	diag.URL = "Document/MessageHistory.jsp";
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value="关闭";
}

function messageDetail(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请选择需要查看的消息！");
		return;
	}
	var diag =new Dialog("diag1");
	diag.Width = 500;
	diag.Height = 250;
	diag.Title = "查看消息";
	diag.URL = "Document/MessageDetailDialog.jsp?ID="+arr[0];
	diag.ShowButtonRow = false;
	diag.show();
	DataGrid.loadData("dg1");
}


function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("您确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.document.Message.del",dc,function(response){
			if(response.Status==1){
				DataGrid.loadData("dg1");
			}
		});
	});
}

setInterval(function(){DataGrid.loadData("dg1");},60000);
</script>
</head>
<z:init method="com.zving.cms.document.Message.init">
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon018a1.gif" /> 短消息列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()">
						<img src="../Icons/icon018a2.gif" />新建</z:tbutton> <z:tbutton
						onClick="reply()">
						<img src="../Icons/icon018a4.gif" />回复</z:tbutton> <z:tbutton onClick="del()">
						<img src="../Icons/icon018a3.gif" />删除</z:tbutton> <z:tbutton
						onClick="MessageHistory()">
						<img src="../Icons/icon018a16.gif" />已发信息</z:tbutton></td>
				</tr>
				<tr>
					<td
						style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:2px;">
					<z:datagrid id="dg1"
						method="com.zving.cms.document.Message.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="3%" height="30">&nbsp;</td>
								<td width="5%" ztype="selector" field="id">&nbsp;</td>
								<td width="16%">消息标题</td>
								<td width="16%" sortField="readflag" direction="ASC">已读标记</td>
								<td width="30%"><strong>消息内容</strong></td>
								<td width="15%"><strong>发送人</strong></td>
								<td width="15%" sortField="addtime" direction="DESC"><strong>发送时间</strong></td>
							</tr>
							<tr onDblClick="messageDetail();">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>${Subject}</td>
								<td><font color="${Color}">${ReadFlagStr}</font></td>
								<td>${Content}</td>
								<td>${FromUser}</td>
								<td>${addTime}</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="7" align="center">${PageBar}</td>
							</tr>
						</table>
					</z:datagrid></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</body>
</z:init>
</html>
