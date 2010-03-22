<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 600;
	diag.Height = 380;
	diag.Title = "新建"+this.document.title;
	diag.URL = "Platform/UserAddDialog.jsp";
	diag.onLoad = function(){
		$DW.$NS("IsBranchAdmin","N");
		$DW.$NS("Status","N");
		$DW.$S("Password","");
		$DW.$("UserName").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
     }
	if ($DW.$V("ConfirmPassword") != $DW.$V("Password")){
	   Dialog.alert("两次输入密码不相同，请重新输入...");
	   return ;
	}
	Server.sendRequest("com.zving.platform.UserList.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});	
	});
}

function save(){
	DataGrid.save("dg1","com.zving.platform.UserList.dg1Edit",function(){DataGrid.loadData('dg1');});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的用户！");
		return;
	}
	Dialog.confirm("您确认要删除这些用户吗？</br><b style='color:#F00'>"+arr.join(',</br>')+"</b>",function(){
		var dc = new DataCollection();
		dc.add("UserNames",arr.join());
		Server.sendRequest("com.zving.platform.UserList.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData('dg1');
				}
			});
		});
	});
}

function stopUser(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要停用的用户！");
		return;
	}
	Dialog.confirm("您确认要停用这些用户吗？</br><b style='color:#F00'>"+arr.join(',</br>')+"</b>",function(){
		var dc = new DataCollection();
		dc.add("UserNames",arr.join());
		Server.sendRequest("com.zving.platform.UserList.stopUser",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData('dg1');
				}
			});
		});
	});
}

function setPriv(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要编辑的用户！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 700;
	diag.Height = 400;
	diag.Title = "设置用户"+arr[0]+"权限";
	diag.URL = "Platform/UserTab.jsp?UserName="+arr[0];
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value="关闭"; 
}

function doSearch(){
	var name = "";
	if ($V("SearchUserName") != "请输入用户名或真实姓名") {
		name = $V("SearchUserName").trim();
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchUserName",name);
	DataGrid.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchUserName'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	if ($V("SearchUserName") == "请输入用户名或真实姓名") {
		$S("SearchUserName","");
	}
}

function edit(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要编辑的用户！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 600;
	diag.Height = 380;
	diag.Title = "编辑用户"+arr[0];
	diag.URL = "Platform/UserEditDialog.jsp?UserName="+arr[0];
	diag.onLoad = function(){
		$DW.$("RealName").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
    }
    if ($DW.$V("NewConfirmPassword") != $DW.$V("NewPassword")){
	   Dialog.alert("两次输入密码不相同，请重新输入",function(){
	       $DW.$S("NewPassword","");
		   $DW.$S("NewConfirmPassword","");
		   return;
	   });
	   return;
	}
	Server.sendRequest("com.zving.platform.UserList.save",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		})
	});
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
						src="../Icons/icon021a1.gif" /> 用户列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon021a2.gif"/>新建</z:tbutton>
				<z:tbutton onClick="edit()"><img src="../Icons/icon021a4.gif"/>编辑</z:tbutton>
				<z:tbutton onClick="stopUser()"><img src="../Icons/icon021a8.gif"/>停用</z:tbutton>
				<z:tbutton onClick="del()"><img src="../Icons/icon021a3.gif"/>删除</z:tbutton>
				<z:tbutton onClick="setPriv()"><img src="../Icons/icon021a9.gif"/>修改权限</z:tbutton>				
				<div style="float: right; white-space: nowrap;">
				  <input name="SearchUserName" type="text" id="SearchUserName" value="请输入用户名或真实姓名" onFocus="delKeyWord();" style="width:150px">
            	  <input type="button" name="Submitbutton" id="Submitbutton" value="查询" onClick="doSearch()">
            	</div>
				</td>
			</tr>
			<tr>
				<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1" method="com.zving.platform.UserList.dg1DataBind" size="15">
				     <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
						<tr ztype="head" class="dataTableHead">
				   	        <td width="4%" ztype="RowNo">序号</td>
					        <td width="4%" ztype="selector" field="UserName">&nbsp;</td>
				            <td width="17%" ><b>用户名</b></td>
							<td width="16%" ><b>真实姓名</b></td>
							<td width="9%" ><b>用户状态</b></td>
							<td width="9%" ><b>所属机构</b></td>
							<td width="17%" ><b>电子邮件</b></td>
							<td width="15%" ><b>联系电话</b></td>
							<td width="18%" ><b>手机号码</b></td>
					    </tr>
					    <tr onDblClick="edit(this)" style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
				          <td >&nbsp;</td>
					      <td>&nbsp;</td>
						  <td>${UserName}</td>
						  <td>${RealName}</td>
						  <td>${StatusName}</td>
						  <td>${BranchInnercodeName}</td>
						  <td>${Email}</td>
						  <td>${Tel}</td>
						  <td>${Mobile}</td>
					    </tr>
						<tr ztype="pagebar">
						  <td colspan="8" align="center">${PageBar}</td>
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
