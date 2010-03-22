<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目</title>

<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	
});

//鼠标点击当前页面时，隐藏右键菜单
var iFrame =parent.parent;
Page.onClick(function(){
	var div = iFrame.$("_DivContextMenu")
	if(div){
			$E.hide(div);
	}
});


function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 360;
	diag.Title = "新建期号";
	diag.URL = "Site/NewspaperPageDialog.jsp";
	
	diag.OKEvent = addSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建版面";
	diag.Message = "设置版面属性";
	diag.show();
}

function addSave(){
	var dc = $DW.Form.getData($F("form2"));
  dc.add("IssueID",$V("IssueID"));
  if($DW.Verify.hasError()){
	    return;
	}

	Server.sendRequest("com.zving.cms.site.NewspaperPage.add",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("新建成功",function(){
				$D.close();
			  DataGrid.setParam("dg1",Constant.PageIndex,0);
			  DataGrid.loadData("dg1");
			});

		}
	});
}

function copy(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 400;
	diag.Title = "复制期刊";
	diag.URL = "Site/PageBlockCopyDialog.jsp";
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	
	Dialog.confirm("确认删除选中的期号吗？",function(){
			var dc = new DataCollection();
			dc.add("IDs",arr.join());
			
			Server.sendRequest("com.zving.cms.site.NewspaperPage.del",dc,function(response){
				if(response.Status==0){
					alert(response.Message);
				}else{
					Dialog.alert("删除成功",function(){
						DataGrid.setParam("dg1",Constant.PageIndex,0);
						DataGrid.loadData("dg1");
					});
				}
			});
	});

}

function edit(){
  var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请先选择要修改的刊号！");
		return;
	}
	if(drs.length>1){
		Dialog.alert("只能修改1条信息！");
		return;
	}
	dr = drs[0]; 
  editDialog(dr);
}

function editDialog(dr){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr || arr.length==0){
		Dialog.alert("请先选择要修改的行！");
		return;
	}
	var ID = arr[0];
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 300;
	diag.Title = "修改期刊";
	diag.URL = "Site/NewspaperPageDialog.jsp";
	
	diag.onLoad = function(){
		$DW.Form.setValue(dr);
		$DW.$S("PublishDate",dr.get("PubDate"));
	};
	
	diag.OKEvent = editSave;
	diag.ShowMessageRow = true;
	diag.MessageTitle = "修改版面";
	diag.Message = "设置版面属性";
	diag.show();
	diag.show();
}

function editSave(){
	var dc = $DW.Form.getData($F("form2"));
  dc.add("NewspaperID",$V("NewspaperID"));
  if($DW.Verify.hasError()){
	    return;
	}
	
	Server.sendRequest("com.zving.cms.site.NewspaperPage.edit",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("修改成功",function(){
				$D.close();
				DataGrid.setParam("dg1",Constant.PageIndex,0);
				DataGrid.loadData("dg1");
			});
		}
	});
}

function generate(){
		var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要生成的行！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	
	Server.sendRequest("com.zving.cms.site.NewspaperPage.generate",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("发布期刊成功",function(){
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
<z:init method="com.zving.cms.site.NewspaperPage.init">

	<div style="padding:2px;">
	<table width="100%" cellpadding="0" cellspacing="0"
		style="margin-bottom:4px;">
		<tr>
			<td><z:tbutton onClick="add();">
				<img src="../Platform/Images/tab1_tri.gif" />新建</z:tbutton> <z:tbutton
				onClick="edit();">
				<img src="../Platform/Images/tab1_tri.gif" />编辑</z:tbutton> <z:tbutton
				onClick="del();">
				<img src="../Platform/Images/tab1_tri.gif" />删除</z:tbutton> <z:tbutton
				onClick="copy();">
				<img src="../Platform/Images/tab1_tri.gif" />复制</z:tbutton> <z:tbutton
				onClick="generate();">
				<img src="../Platform/Images/tab1_tri.gif" />出版</z:tbutton></td>
		</tr>
	</table>
	<input name="IssueID" type="hidden" id="IssueID" value="${CatalogID}" />
	<z:datagrid id="dg1"
		method="com.zving.cms.site.NewspaperPage.dg1DataBind" size="14">
		<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
			<tr ztype="head" class="dataTableHead">
				<td width="7%" ztype="RowNo">序号</td>
				<td width="5%" ztype="selector" field="id">&nbsp;</td>
				<td width="16%"><strong>版面号</strong></td>
				<td width="15%"><strong>名称</strong></td>
				<td width="16%"><strong>版面图片</strong></td>
				<td width="15%"><strong>PDF文件</strong></td>
				<td width="18%"><strong>添加时间</strong></td>
				<td width="8%"><strong>操作</strong></td>
			</tr>
			<tr style1="background-color:#FFFFFF"
				style2="background-color:#F9FBFC">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>${PageNo}</td>
				<td>${Title}</td>
				<td>${Image}</td>
				<td>${File}</td>
				<td>${AddTime}</td>
				<td><a
					href="Preview.jsp?File=${FileName}&Type=2&SiteID=${SiteID}"
					target="_blank">预览</a></td>
			</tr>
			<tr ztype="pagebar">
				<td colspan="8" align="center">${PageBar}</td>
			</tr>
		</table>
	</z:datagrid></div>
</z:init>
</body>
</html>
