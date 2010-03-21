<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>调查</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 180;
	diag.Title = "新建调查";
	diag.URL = "DataService/VoteDialog.jsp?ID=";
	diag.onLoad = function(){
		$DW.$("Title").focus();
	};
	diag.OKEvent = addSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建调查";
	diag.Message = "新建调查，设置调查的主题、截止时间等。";
	diag.show();
}

function addSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("com.zving.cms.dataservice.Vote.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确定要删除该调查吗？",function(){
		var dc = new DataCollection();
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.cms.dataservice.Vote.del",dc,function(response){
				Dialog.alert(response.Message,function(){
					if(response.Status==1){
						DataGrid.loadData("dg1");
					}
				});
			});
	})	
}

function handStop(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要终止的投票！");
		return;
	}
	Dialog.confirm("确定要终止该投票吗？",function(){
		var dc = new DataCollection();
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.cms.dataservice.Vote.handStop",dc,function(response){
				Dialog.alert(response.Message,function(){
					if(response.Status==1){
						DataGrid.loadData("dg1");
					}
				});
			});
	})	
}

function edit(){
  	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要修改的调查！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 180;
	diag.Title = "调查修改";
	diag.URL = "DataService/VoteDialog.jsp?ID="+arr[0];
	diag.onLoad = function(){
		$DW.$("Title").focus();
	};
	diag.OKEvent = editSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "修改调查";
	diag.Message = "设置调查的主题、截止时间等。";
	diag.show();
}

function editSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("com.zving.cms.dataservice.Vote.edit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function editItemDialog(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 800;
	diag.Height = 450;
	diag.Title = "调查问卷列表";
	diag.URL = "DataService/VoteItem.jsp?ID="+ID;
	diag.onLoad = function(){
	};
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value = "关闭";
}

function JSCode(ID){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 120;
	diag.Title = "JS调用代码";
	diag.URL = "DataService/VoteJSCodeDialog.jsp?ID="+ID;
	diag.onLoad = function(){
		$DW.$("JSCode").focus();
	};
	diag.ShowMessageRow = true;
	diag.MessageTitle = "JS调用代码";
	diag.Message = "直接把下面的JS代码粘贴到html网页中即可";
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value = "关闭";
}

function preview(ID){
	if(screen.width==800){
		var width = 800,height = 600,leftm = 0,topm = 0;
	}else if(screen.width>800){
	  	var width  = Math.floor( screen.width  * .78 );
  		var height = Math.floor( screen.height * .8 );
  		var leftm  = Math.floor( screen.width  * .1);
 		var topm   = Math.floor( screen.height * .1);
	}else{
		return;
	}
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
  	var url="VotePreview.jsp?ID=" + ID;
  	window.open(url,"VotePreview");
}

getResult = function() {
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要查看结果的行！");
		return;
	} else if(arr.lenght > 1) {
		Dialog.alert("请选择要查看结果的一行！");
		return;
	}
	result(arr[0]);
}

function result(ID){
	if(screen.width==800){
		var width = 800,height = 600,leftm = 0,topm = 0;
	}
	else if(screen.width>800){
	  	var width  = Math.floor( screen.width  * .78 );
  		var height = Math.floor( screen.height * .8 );
  		var leftm  = Math.floor( screen.width  * .1);
 		var topm   = Math.floor( screen.height * .1);
	}
	else{
		return;
	}
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
  	var url= Server.ContextPath+"Services/VoteResult.jsp?ID=" + ID;
  	window.open(url,"VoteResult");
}

resultDetails = function() {
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要查看结果的行！");
		return;
	} else if(arr.lenght > 1) {
		Dialog.alert("请选择要查看结果的一行！");
		return;
	}
	var ID = arr[0];
	var diag = new Dialog("Votelogs");
	diag.Width = 700;
	diag.Height = 450;
	diag.Title = "投票明细";
	diag.URL = "DataService/VoteLogs.jsp?ID=" + ID;
	diag.ShowButtonRow = false;
	diag.show();
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
						src="../Icons/icon032a1.gif" />调查投票列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon032a2.gif" />新建</z:tbutton> 
					<z:tbutton onClick="edit()"><img src="../Icons/icon032a4.gif" />修改</z:tbutton> 
					<z:tbutton onClick="del()"><img src="../Icons/icon032a3.gif" />删除</z:tbutton>
					<z:tbutton onClick="handStop()"><img src="../Icons/icon032a5.gif" />手工终止</z:tbutton>
					<z:tbutton onClick="getResult()"><img src="../Icons/icon032a1.gif" />结果</z:tbutton>
					<z:tbutton onClick="resultDetails()"><img src="../Icons/icon032a1.gif" />结果明细</z:tbutton>
					</td>
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1" method="com.zving.cms.dataservice.Vote.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="4%" ztype="RowNo">序号</td>
								<td width="3%" align="center" ztype="selector" field="id">&nbsp;</td>
								<td width="5%"><b>ID</b></td>
								<td width="23%"><b>调查主题</b></td>
								<td width="7%"><b>JS代码</b></td>
								<td width="7%"><b>投票人数</b></td>
								<td width="9%"><b>限制IP</b></td>
								<td width="16%"><b>开始时间</b></td>
								<td width="16%"><b>截止时间</b></td>
								<td width="5%"><b>预览</b></td>
							</tr>
							<tr onDblClick="edit();">
								<td align="center">&nbsp;</td>
								<td align="center">&nbsp;</td>
								<td>${ID}</td>
								<td><a href="#" onClick="editItemDialog('${ID}')">${Title}</a></td>
								<td><a href="#" onClick="JSCode('${ID}')">JS代码</a></td>
								<td>${Total}</td>
								<td>${IPLimitName}</td>
								<td>${StartTime}</td>
								<td>${EndTime}</td>
								<td><a href="#" onClick="preview('${ID}')">预览</a></td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="12" align="center">${PageBar}</td>
							</tr>
					  	</table>
					  </z:datagrid>		
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</body>
</html>
