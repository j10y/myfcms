<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ZVING</title>
<script src="../../Framework/Main.js"></script>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="../forum.css" rel="stylesheet" type="text/css">
<script>
function add(){
		var diag = new Dialog("Diag1");
		diag.Width = 350;
		diag.Height =300;
		diag.Title = "添加板块";
		diag.URL = "BBS/Admin/ForumSystemGroupAdd.jsp";
		diag.OKEvent = addSave;
		diag.show();
	}
	function addSave(){
		var dc = Form.getData($DW.$F("form1"));
		if($DW.Verify.hasError()){
			return;
		}
		Server.sendRequest("com.zving.bbs.amdin.ForumUserGroup.addSystemGroup",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataList.loadData('list2');
				}
			});
		});
	}
	function del(ID){
		Dialog.confirm("删除后不可恢复，确认要删除？",function(){
			var dc = new DataCollection();
			dc.add("ID",ID);
			Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.del",dc,function(response){
				Dialog.alert(response.Message);
				if(response.Status==1){
					DataList.loadData('list2');
				}
			});
		});
	}
	
	function basicEdit(ID){
		var diag = new Dialog("Diag1");
		diag.Width = 550;
		diag.Height =400;
		diag.Title = "板块设置";
		diag.URL = "BBS/Admin/ForumSystemGroupOption.jsp?ID="+ID;
		diag.OKEvent = basicEditSave;
		diag.show();
	}
	function basicEditSave(){
		var basicWin = $DW.Tab.getChildTab("ForumUserBasic").contentWindow;
		var postWin = $DW.Tab.getChildTab("ForumUserPostOption").contentWindow;
		if(basicWin.Verify.hasError() || postWin.Verify.hasError()){
			return;
		}
		var dc1 = basicWin.Form.getData("form1");
		var dc2 = postWin.Form.getData("form2");
		
		dc1.addAll(dc2);
		Server.sendRequest("com.zving.bbs.admin.ForumUserGroupOption.editSave",dc1,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataList.loadData("list1");
				}
			});
		});
	}
	
	function manageEdit(ID){
		var diag = new Dialog("Diag1");
		diag.Width = 550;
		diag.Height =400;
		diag.Title = "板块设置";
		diag.URL = "BBS/Admin/ForumManageGroupOption.jsp?ID="+ID;
		diag.OKEvent = manageEditSave;
		diag.show();
	}
	function manageEditSave(){
		var basicWin = $DW.Tab.getChildTab("ForumManageBasic").contentWindow;
		var themeWin = $DW.Tab.getChildTab("ForumUserThemeOption").contentWindow;
		
		var dc1 = basicWin.Form.getData("form1");
		var dc2 = themeWin.Form.getData("form2");
		
		dc1.addAll(dc2);
		Server.sendRequest("com.zving.bbs.admin.ForumManageGroupOption.editSave",dc1,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataList.loadData("list1");
				}
			});
		});
	}
</script>
</head>
<body>

<div class="wrap">
<div id="foruminfo">
	<div id="nav">
						<z:tbutton onClick="add()" >
						<img src="../../Icons/icon022a2.gif" width="20" height="20" />新建</z:tbutton> <z:tbutton
						onClick="save()">
						<img src="../../Icons/icon022a16.gif" width="20" height="20" />保存</z:tbutton>
	</div></div>
<div class="forumbox">

<form id="form1" method="post" name="moderate">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
	<!-- 表头 -->
	<thead>
		<tr>
			<td width="50">名称</td>
			<td width="60">类型</td>
			<td width="60">管理级别</td>
			<td width="30">操作</td>
		</tr>
	</thead>
	<!--从要循环的数据行开始插入datalist标签 -->
	<z:datalist id="list1" method="com.zving.bbs.admin.ForumManageGroup.getListManageGroup" page="false">
		<tr nowrap="nowrap">
			<td valign="middle">${Name}</td>
			<td>${Type}</td>
			<td>${SystemName}</td>
			<td width="120" ><a href="#" onclick="basicEdit(${GroupID})">基本设置</a>&nbsp;&nbsp;<a href="#" onclick="manageEdit(${GroupID})">权限管理</a></td>
		</tr>
	</z:datalist>
	
</table>
</form>
</div>

</body>
</html>
