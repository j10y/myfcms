<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>论坛板块管理</title>
	<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
	<script src="../../Framework/Main.js"></script>
<script>

	
	function add(){
		var diag = new Dialog("Diag1"); 
		diag.Width = 450;
		diag.Height =400;
		diag.Title = "添加板块";
		diag.URL = "BBS/Admin/ForumAddAdmin.jsp";
		diag.OKEvent = addSave;
		diag.show();
	}
	function addSave(){
		var dc = Form.getData($DW.$F("form1"));
		if($DW.Verify.hasError()){
			return;
		}
		Server.sendRequest("com.zving.bbs.admin.ForumAdmin.add",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});
	}
	function editForum(ID){
		var diag = new Dialog("Diag1");
		diag.Width = 550;
		diag.Height =400;
		diag.Title = "板块设置";
		diag.URL = "BBS/Admin/ForumOption.jsp?ID="+ID;
		diag.OKEvent = editForumSave;
		diag.show();
	}
	function editForumSave(){
		var basicWin = $DW.Tab.getChildTab("ForumBasic").contentWindow;
		var postWin = $DW.Tab.getChildTab("PostOption").contentWindow;
		if(basicWin.Verify.hasError() || postWin.Verify.hasError()){
			return;
		}
		var dc1 = basicWin.Form.getData("form1");
		var dc2 = postWin.Form.getData("form2");
		dc1.addAll(dc2);
		Server.sendRequest("com.zving.bbs.admin.ForumAdmin.editSave",dc1,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataGrid.loadData("dg1");
				}
			});
		});
	}

	function del(ID){
		if(!ID){	
			var arr = DataGrid.getSelectedValue("dg1");
			if(!arr||arr.length==0){
				Dialog.alert("请先选择要删除的行！");
				return;
			}
		}else{
			var arr = new Array(1);
			arr[0]=ID;
		}	
		Dialog.confirm("删除后不可恢复，确认要删除？",function(){
			var dc = new DataCollection();
			dc.add("IDs",arr.join());
			Server.sendRequest("com.zving.bbs.admin.ForumAdmin.del",dc,function(response){
				Dialog.alert(response.Message,function(){
					if(response.Status==1){
						DataGrid.loadData("dg1");
					}
				});
			});
		});
		
	}
	function save(){
		var dt = DataGrid.getSelectedData("dg1");
		var drs = dt.Rows;
		if(!drs||drs.length==0){
			Dialog.alert("新选择要修改的板块");
			return;
		}else if(drs.length>1){
			Dialog.alert("每次只能修改一个板块");
			return;
		}
		dr = drs[0];
		if(!$("Name") || !$("ForumAdmin") ){
			Dialog.alert("请双击编辑后再进行保存");
			return;
		}
		var dc = new DataCollection();
		dc.add("ID",dr.get("ID"));
		dc.add("Name",$V("Name"));
		dc.add("ForumAdmin",$V("ForumAdmin"));
		Server.sendRequest("com.zving.bbs.admin.ForumAdmin.dg1Edit",dc,function(response){
				Dialog.alert(response.Message,function(){
					if(response.Status==1){
						DataGrid.loadData("dg1");
					}
				});
			});
		
	}
	function discard(){
		DataGrid.discard("dg1");
	}
	
	

function clickAllSelect(){
	var f = $("AllSelect").checked;
	var menuTree = $N("MenuID");
	var menuTreeLength = menuTree.length;
	for(var i=0;i<menuTreeLength;i++){
		menuTree[i].checked = f;
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
					<td valign="middle" class="blockTd"><img
						src="../../Icons/icon022a1.gif" width="20" height="20" />板块列表</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()" >
						<img src="../../Icons/icon022a2.gif" width="20" height="20" />新建</z:tbutton> <z:tbutton
						onClick="save()">
						<img src="../../Icons/icon022a16.gif" width="20" height="20" />保存</z:tbutton> <z:tbutton
						onClick="discard()">
						<img src="../../Icons/icon400a8.gif" />放弃</z:tbutton> <z:tbutton onClick="del()">
						<img src="../../Icons/icon022a3.gif" width="20" height="20" />批量删除</z:tbutton></td>
				</tr>
				<tr>
					<td style="padding:0px 5px;">
					<z:datagrid id="dg1" method="com.zving.bbs.admin.ForumAdmin.dg1DataBind" page="false">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable" afterdrag="sortMenu">
							<tr ztype="head" class="dataTableHead">
								<td width="6%" ztype="RowNo" drag="true">
								<img src="../../Framework/Images/icon_drag.gif" width="16" height="16"></td>
								<td width="7%" ztype="selector" field="id">&nbsp;</td>
								<td width="36%" ztype="tree" level="treelevel"><b>板块名称</b></td>
								<td width="" ><b>版主</b></td>
								<td width="" ><b>操作</b></td>
								
								
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;${Name}</td>
								<td>${ForumAdmin}</td>
								<td>${EditButton}</td>
							</tr>
							<tr ztype="edit" bgcolor="#E1F3FF">
								<td>&nbsp;</td>
								<td bgcolor="#E1F3FF">&nbsp;</td>
								<td bgcolor="#E1F3FF">
								<input name="Name" type="text" class="input1" id="Name" value="${Name}" size="16"></td>
								<td bgcolor="#E1F3FF">
								<input  type="text" class="input1" name="ForumAdmin" id="ForumAdmin" value="${ForumAdmin}" size="16"></td>
								<td>${EditButton}</td>
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
