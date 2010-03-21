<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ZVING</title>
<script src="../../Framework/Main.js"></script>
<script src="../Images/js/Color.js"></script>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="../forum.css" rel="stylesheet" type="text/css">
<script>
function add(){
		var diag = new Dialog("Diag1");
		diag.Width = 300;
		diag.Height =60;
		diag.Title = "添加板块";
		diag.URL = "BBS/Admin/ForumSpecailGroupAdd.jsp";
		diag.OKEvent = addSave;
		diag.show();
	}
	function addSave(){
		var dc = Form.getData($DW.$F("form1"));
		if($DW.Verify.hasError()){
			return;
		}
		Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.addSpecialGroup",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				$D.close();
				DataList.loadData('list1');
			}
		});
	}
	function eidtColor(){
		var dc = Form.getData($F("form1"));

		Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.editSpecialGroup",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				DataList.loadData('list1');
			}
		});
	}
	function del(ID){
		Dialog.confirm("删除后不可恢复，确认要删除？",function(){
			var dc = new DataCollection();
			dc.add("ID",ID);
			Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.delSpecailGroup",dc,function(response){
				Dialog.alert(response.Message);
				if(response.Status==1){
					DataList.loadData('list1');
				}
			});
		});
	}
	function save(){
		var dc = Form.getData($F("form1"));
		Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.save",dc,function(response){
			Dialog.alert(response.Message);
				if(response.Status==1){
					DataList.loadData('list1');
				}
		});
	}
	
	function edit(ID){
		var diag = new Dialog("Diag1");
		diag.Width = 550;
		diag.Height =400;
		diag.Title = "板块设置";
		diag.URL = "BBS/Admin/ForumSpecialGroupOptionDialog.jsp?ID="+ID;
		diag.OKEvent = editSave;
		diag.show();
	}
	function editSave(){
		var basicWin = $DW.Tab.getChildTab("ForumUserBasic").contentWindow;
		var postWin = $DW.Tab.getChildTab("ForumUserPostOption").contentWindow;
		if(basicWin.Verify.hasError() || postWin.Verify.hasError()){
			return;
		}
		var dc1 = basicWin.Form.getData("form1");
		var dc2 = postWin.Form.getData("form2");
		
		dc1.addAll(dc2);
		Server.sendRequest("com.zving.bbs.admin.ForumUserGroupOption.editSpecialSave",dc1,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					$D.close();
					DataGrid.loadData("dg1");
				}
			});
		});
	}
	
	function chooseImage(ID) {
		var diag = new Dialog("Diag1");
		diag.Width = 470;
		diag.Height =400;
		diag.Title = "选择组头像";
		diag.URL = "BBS/Admin/UserGroupImageDialog.jsp?GroupID=" + ID;
		diag.OKEvent = imageSave;
		diag.show();
	}

	function imageSave() {
		var dc = Form.getData($DW.$F("form1"));

		Server.sendRequest("com.zving.bbs.admin.ForumUserGroup.imageSave",dc,function(response){
			Dialog.alert(response.Message);
			if(response.Status==1){
				$D.close();
				DataList.loadData('list1');
			}
		});
	}
</script>
</head>
<body>

<div class="wrap">
<input id="ForumID"  type="hidden" value="${ID}">
<div id="foruminfo">
	<div id="nav">
		<z:tbutton onClick="add()" >
			<img src="../../Icons/icon022a2.gif" width="20" height="20" />新建
		</z:tbutton>
		<z:tbutton onClick="eidtColor()">
			<img src="../../Icons/icon022a16.gif" width="20" height="20" />保存
		</z:tbutton>
	</div></div>
<div class="forumbox">

<form id="form1" method="post" name="moderate">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
	<!-- 表头 -->
	<thead>
		<tr>
			<td width="50">组头衔</td>
			<td width="60">头衔颜色</td>
			<td width="60">组头像</td>
			<td width="30">操作</td>
		</tr>
	</thead>
	<!--从要循环的数据行开始插入datalist标签 -->
	<z:datalist id="list1" method="com.zving.bbs.admin.ForumUserGroup.getListSpecialGroup" page="false">
		<tr nowrap="nowrap">
			<td valign="middle">${Name}</td>
			<td><input type="text" value="${Color}" id="Color_${ID}" name="Color" style="background:${Color}"  onclick="coloropen(event,${ID})"/></td>
			<td valign="middle"><img src="${Image}" title="${Image}" id="Image" name="Image" width='30' height='30'"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="chooseImage('${ID}');"/>更换头像</a></td>
			<td width="80" ><a href="#" onclick="edit(${ID})">详情</a>&nbsp;&nbsp;<a href="#" onclick="del(${ID})">删除</a></td>
		</tr>
	</z:datalist>
</table>
	<div id="colorpane" style="position:absolute;z-index:999;display:none"></div>
</form>
</div>

</body>
</html>