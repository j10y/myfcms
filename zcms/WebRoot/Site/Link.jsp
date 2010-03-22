<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>链接</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	$("dg2").afterEdit = function(tr,dr){
		dr.set("Name",$V("Name"));
		dr.set("URL",$V("URL"));
		return true;
	}
});

function add(){
	var diag = new Dialog("Diag2");
	diag.Width = 400;
	diag.Height = 150;
	diag.MessageTitle = "请填写链接名称和链接URL";
	diag.Title = "新建链接";
	diag.URL = "Site/LinkDialog.jsp?LinkGroupID="+$V("LinkGroupID")+"&Type="+$V("Type");
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = addSave;
	diag.ShowMessageRow = true;
	diag.show();
}

function addSave(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = Form.getData($DW.$F("myform"));
	Server.sendRequest("com.zving.cms.site.Link.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg2');
			}
		});
	});
}

function editDialog(ID){
	var diag = new Dialog("Diag2");
	diag.Width = 400;
	diag.Height = 150;
	diag.Title = "友情链接修改";
	diag.URL = "Site/LinkDialog.jsp?ID="+ID+"&LinkGroupID="+$V("LinkGroupID")+"&Type="+$V("Type");
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("myform"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.cms.site.Link.edit",dc,function(response){
		Dialog.alert(response.Message);
		if(response.Status==1){
			$D.close();
			DataGrid.loadData('dg2');
		}
	});
}

function save(){
	DataGrid.save("dg2","com.zving.cms.site.Link.save",function(){DataGrid.loadData('dg2');});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg2");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("您确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.site.Link.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData('dg2');
				}
			});
		});
	});
}

function afterRowDragEnd(type,targetDr,sourceDr,rowIndex,oldIndex){
	if(rowIndex==oldIndex){
		return;
	}
	
	var order = $("dg2").DataSource.get(rowIndex-1,"OrderFlag");
	var target = "";
	var dc = new DataCollection();
	var ds = $("dg2").DataSource;
	var i = rowIndex-1;
	if(i!=0){
		target = ds.get(i-1,"OrderFlag");
		dc.add("Type","After");		
	}else{
		dc.add("Type","Before");
		target = $("dg2").DataSource.get(1,"OrderFlag");
	}
	dc.add("Target",target);
	dc.add("Orders",order);
	dc.add("LinkGroupID",$V("LinkGroupID"));
	DataGrid.showLoading("dg2");
	Server.sendRequest("com.zving.cms.site.Link.sortColumn",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg2");
			}
		});
	});
}
</script>
</head>
<body>
<z:init>
<input type="hidden" id="LinkGroupID" value="${LinkGroupID}">
<input type="hidden" id="Type" name="Type" value="${Type}">
<%
String path = (Config.getContextPath() + Config.getValue("UploadDir") + "/" 
		+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/").replaceAll("//","/");
String width = Config.getValue("LinkPicWidth");
if (StringUtil.isEmpty(width) && !StringUtil.isDigit(width)) {
	width = "101";
}
String height = Config.getValue("LinkPicHeight");
if (StringUtil.isEmpty(height) && !StringUtil.isDigit(height)) {
	width = "50";
}
%>
	<table width="100%" border="0" cellspacing="0" cellpadding="6">
		<tr>
			<td style="padding:4px 5px;"><z:tbutton onClick="add();">
				<img src="../Icons/icon028a2.gif" />新建</z:tbutton> <z:tbutton onClick="save();">
				<img src="../Icons/icon028a16.gif" />保存</z:tbutton> <z:tbutton onClick="del();">
				<img src="../Icons/icon028a3.gif" />删除</z:tbutton></td>
		</tr>
		<tr>
			<td
				style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
			<z:datagrid id="dg2" method="com.zving.cms.site.Link.dg1DataBind">
				<table width="100%" cellpadding="2" cellspacing="0"
					class="dataTable" afterDrag="afterRowDragEnd">
					<%String type = request.getParameter("Type");
					  if ("image".equalsIgnoreCase(type)) {%>
					<tr ztype="head" class="dataTableHead">
						<td width="6%" ztype="RowNo" drag="true"><img
							src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
						<td width="5%" ztype="selector" field="id">&nbsp;</td>
						<td width="15%"><strong>链接分类</strong></td>
						<td width="28%"><strong>链接名称</strong></td>
						<td width="30%"><strong>链接地址</strong></td>
						<td width="16%"><strong>链接图片</strong></td>
					</tr>
					<tr onDblClick="editDialog(${ID})" height="60px">
						<td width="6%">&nbsp;</td>
						<td width="5%">&nbsp;</td>
						<td>${LinkGroupName}</td>
						<td>${Name}</td>
						<td title="${URL}">${URL}</td>
						<td align="center" valign="middle" style="height:auto;"><img
							src="<%=path%>${ImagePath}" width="100"></td>
					</tr>
					<%} else { %>
					<tr ztype="head" class="dataTableHead">
						<td width="8%" ztype="RowNo" drag="true"><img
							src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
						<td width="8%" ztype="selector" field="id">&nbsp;</td>
						<td width="13%"><strong>链接分类</strong></td>
						<td width="30%"><strong>链接名称</strong></td>
						<td width="41%"><strong>链接地址</strong></td>
					</tr>
					<tr>
						<td width="8%">&nbsp;</td>
						<td width="8%">&nbsp;</td>
						<td>${LinkGroupName}</td>
						<td>${Name}</td>
						<td>${URL}</td>
					</tr>
					<tr ztype="edit">
						<td width="8%">&nbsp;</td>
						<td width="8%">&nbsp;</td>
						<td>${LinkGroupName}</td>
						<td><input type="text" class="input1" id="Name"
							value="${Name}" style="width:200px"></td>
						<td><input type="text" class="input1" id="URL" value="${URL}"
							style="width:250px"></td>
					</tr>
					<%} %>
				</table>
			</z:datagrid></td>
		</tr>
	</table>
</z:init>
</body>
</html>
