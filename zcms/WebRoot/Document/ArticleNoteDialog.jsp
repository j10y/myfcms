<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function closeX(){
   window.close();
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	
	Dialog.confirm("确认删除选中的批注吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.document.ArticleNote.del",dc,function(response){
			if(response.Status==0){
				alert(response.Message);
			}else{
				DataGrid.setParam("dg1",Constant.PageIndex,0);
	      DataGrid.loadData("dg1");
			}
		});
	});
}

function add(){
	var diag  = new Dialog("Diag");
	diag.Width = 400;
	diag.Height = 250;
	diag.Title ="批注";
	diag.URL = "Document/ArticleNoteAddDialog.jsp";
	diag.onLoad = function(){
	};
	
  diag.OKEvent = addSave;
	diag.show();
}


function addSave(){
	var dc = new DataCollection();
	var content = $DW.$V("Content");
	if(content == ""){
		Dialog.alert("批注内容不能为空");
		$DW.$("Content").focus();
		return;
	}
	dc.add("Content",content);
	dc.add("ArticleID",$V("ArticleID"));
	Server.sendRequest("com.zving.cms.document.ArticleNote.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("成功添加批注",function(){
				$D.close();
				DataGrid.setParam("dg1",Constant.PageIndex,0);
	      DataGrid.loadData("dg1");
			});
		}
	});
}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" class="cellspacing"
	cellpadding="0">
	<tr>
		<td>
		<div><z:tbutton onClick="add()">
			<img src="../Icons/icon018a16.gif" />添加</z:tbutton> <z:tbutton onClick="del()">
			<img src="../Icons/icon018a5.gif" />删除</z:tbutton></div>
</td></tr><tr><td>
		<input type="hidden" name="AritcleID" id="ArticleID"
			value="<%=request.getParameter("ArticleID")%>"> <z:datagrid
			id="dg1" method="com.zving.cms.document.ArticleNote.noteDataBind"
			size="10">
			<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr ztype="head" class="dataTableHead">
					<td width="5%" ztype="selector" field="id">&nbsp;</td>
					<td width="35%""><b>批注</b></td>
					<td width="30%"><strong>批注人</strong></td>
					<td width="30%"><strong>批注时间</strong></td>
				</tr>
				<tr style1="background-color:#FFFFFF"
					style2="background-color:#F9FBFC">
					<td>&nbsp;</td>
					<td>${ActionDetail}</td>
					<td>${addUser}</td>

					<td>${addTime}</td>
				</tr>

				<tr ztype="pagebar">
					<td colspan="4" align="left">
					<div align="center">${PageBar}</div>
					</td>
				</tr>
			</table>
		</z:datagrid></td>
	</tr>
</table>
</body>
</html>
