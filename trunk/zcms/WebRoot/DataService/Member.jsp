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

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 360;
	diag.Title = "新增会员";
	diag.URL = "DataService/MemberDialog.jsp";
	diag.onLoad = function(){
		$DW.$("UserName").focus();
	};
	diag.OKEvent = addSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建会员信息";
	diag.Message = "新建会员级别、姓名、分数、折扣等";
	diag.show();
}

function addSave(){
	if($DW.Verify.hasError()){
	  return;
     }
	var dc = Form.getData($DW.$F("form2"));
	dc.add("Gender",$DW.$NV("Gender"));
	Server.sendRequest("com.zving.cms.dataservice.Member.add",dc,function(){
		var response = Server.getResponse();
		Dialog.alert(response.Message,function(){
		if(response.Status==1){
			$D.close();
			DataGrid.loadData('dg1');
		}
		});
	});
}

function edit(UserName){
    var arr = DataGrid.getSelectedValue("dg1");
	if(UserName&&UserName!=""){
		arr = new Array();
		arr[0] = UserName;
	}
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要编辑的会员！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 360;
	diag.Title = "会员信息修改";
	diag.URL = "DataService/MemberEditDialog.jsp?UserName="+arr[0];
	diag.OKEvent = editSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "修改会员信息";
	diag.Message = "修改会员级别、姓名、分数、折扣等";
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	dc.add("Gender",$DW.$NV("Gender"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.dataservice.Member.dg1Edit",dc,function(){
		var response = Server.getResponse();
		Dialog.alert(response.Message);
		if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
		}
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确定要删除选中的会员吗？",function(){
		var dc = new DataCollection();	
		dc.add("UserNames",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.Member.del",dc,function(){
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
	DataGrid.setParam("dg1","Status",$V("Status"));
	DataGrid.setParam("dg1","SearchUserName",$V("SearchUserName"));
	DataGrid.setParam("dg1","Search","search");
	DataGrid.loadData("dg1");
}

function doCheck(status){
	if(status==""){
		return;
	}
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要审核的会员！");
		return;
	}
	var dc = new DataCollection();	
	dc.add("UserNames",arr.join(","));
	dc.add("Status",status);
	Server.sendRequest("com.zving.cms.dataservice.Member.doCheck",dc,function(){
		var response = Server.getResponse();
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			DataGrid.loadData('dg1');
		}
	});
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
					<td valign="middle" class="blockTd"><img src="../Icons/icon018a6.gif" />会员列表</td>
				</tr>
				<tr>
					<td style="padding:8px 10px;">
                    <div style="float: right">状态：
                    <z:select id="Status" name="Status" style="width:80px" listWidth="80" onChange="doSearch();">
					<select><option value=""></option>
						<option value="X">等待审核</option>
						<option value="Y">审核通过</option>
						<option value="N">审核不通过</option></select>
					</z:select>                    
                    登录名：<input name="SearchUserName" type="text" id="SearchUserName" value="" style="width:90px"> 
                    <input type="button" name="Submit" value="查询" onClick="doSearch()"></div>
                    <z:tbutton onClick="add()"><img src="../Icons/icon021a2.gif" />新建</z:tbutton> 
                    <z:tbutton onClick="edit()"><img src="../Icons/icon021a4.gif" />修改</z:tbutton> 
                    <z:tbutton onClick="del()"><img src="../Icons/icon021a3.gif" />删除</z:tbutton>
                    <z:tbutton onClick="doCheck('Y')"><img src="../Icons/icon404a4.gif" width="20" height="20" />审核通过</z:tbutton>
                    <z:tbutton onClick="doCheck('N')"><img src="../Icons/icon404a3.gif" width="20" height="20" />不通过</z:tbutton>
					</td>
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1" method="com.zving.cms.dataservice.Member.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="4%" ztype="RowNo"><strong>序号</strong></td>
								<td width="4%" ztype="selector" field="UserName">&nbsp;</td>
								<td width="19%"><b>登录名</b></td>
								<td width="15%"><b>姓名</b></td>
								<td width="14%"><b>会员类型</b></td>
								<td width="12%"><b>会员级别</b></td>
								<td width="10%"><b>性别</b></td>
								<td width="11%"><b>积分</b></td>
								<td width="11%"><b>审核状态</b></td>
							</tr>
							<tr onDblClick="edit('${UserName}')" style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td width="4%">&nbsp;</td>
								<td>&nbsp;</td>
								<td>${UserName}</td>
								<td>${Name}</td>
								<td>${TypeName}</td>
								<td>${MemberLevelName}</td>
								<td>${GenderName}</td>
								<td>${Score}</td>
								<td>${StatusName}</td>
							</tr>
							<tr ztype="pagebar">
								<td height="25" colspan="11">${PageBar}</td>
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
