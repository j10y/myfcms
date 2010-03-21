<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评论管理</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function showEditDialog(dr){
	var diag = new Dialog("Diag1");
	diag.Width = 365;
	diag.Height = 245;
	diag.Title = "修改评论";
	diag.URL = "CommentDialog.jsp";
	diag.onLoad = function(){
		$DW.$S("ID",dr.get("ID"));
	};
	diag.show();
}
function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("您确认要删除评论吗?",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.Comment.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataGrid.loadData("dg1");
				}
			});
		});
	})
}

function changeVerifyStatus(){
	DataGrid.setParam("dg1","VerifyStatus",$V("VerifyStatus"));
	DataGrid.loadData("dg1");
}

function changeCatalogList(){
	if(isIE){
		$("SelectCatalogID").listURL="DataService/CommentCatalogSelector.jsp?Type="+$V("CatalogType");
	}else{
		$("SelectCatalogID").setAttribute("listURL","DataService/CommentCatalogSelector.jsp?Type="+$V("CatalogType"));
	}
	Selector.setValueEx("SelectCatalogID",'','');
	$S("CatalogID","");
	DataGrid.setParam("dg1","CatalogID","");
	DataGrid.setParam("dg1","CatalogType",$V("CatalogType"));
	DataGrid.loadData("dg1");
}

function setCatalogID(){
	if($V("SelectCatalogID")!=0){
		$S("CatalogID",$V("SelectCatalogID"));
		DataGrid.setParam("dg1","CatalogID",$V("SelectCatalogID"));
		DataGrid.setParam("dg1","CatalogType",$V("CatalogType"));
		DataGrid.loadData("dg1");
	}
}

function showDetail(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请选择需要查看的评论！");
		return;
	}
	var diag =new Dialog("diag1");
	diag.Width = 500;
	diag.Height = 300;
	diag.Title = "评论详细";
	diag.URL = "DataService/CommentDetailDialog.jsp?ID="+arr[0];
	diag.ShowButtonRow = false;
	diag.show();
}

function reloadList(){
	DataGrid.loadData("dg1");
}

function Pass(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请选择需要审核的评论！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join(","));
	dc.add("Type","Pass");
	Server.sendRequest("com.zving.cms.dataservice.Comment.Verify",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				DataGrid.loadData("dg1");
			});
		}else{
		   Dialog.alert(response.Message);
		}
	})
}
function NoPass(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请选择需要审核的评论！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join(","));
	dc.add("Type","NoPass");
	Server.sendRequest("com.zving.cms.dataservice.Comment.Verify",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message,function(){
				DataGrid.loadData("dg1");
			});
		}else{
		   Dialog.alert(response.Message);
		}
	})
}
</script>
</head>
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img src="../Icons/icon002a1.gif" /> 评论管理</td>
				</tr>
				<tr>
					<td style="padding:0 8px 4px;">
                    <z:tbutton onClick="Pass()"><img src="../Icons/icon018a2.gif" />审核通过</z:tbutton>
                    <z:tbutton onClick="NoPass()"><img src="../Icons/icon018a1.gif" />审核不通过</z:tbutton>
                    <z:tbutton onClick="del()"><img src="../Icons/icon018a3.gif" />删除</z:tbutton>
                    &nbsp;&nbsp;<font color="#999999">选择栏目类型</font>
                    <z:select id="CatalogType" style="width:60px;" onChange="changeCatalogList();">
                        <span value="1">文档</span>
                        <span value="4">图片</span>
                        <span value="5">视频</span>
                        <span value="6">音频</span>
                        <span value="7">附件</span>
                    </z:select>
                    &nbsp;<font color="#999999">选择栏目</font>
                    <z:select id="SelectCatalogID" style="width:120px" onChange="setCatalogID()" listWidth="200" listHeight="300" listURL="DataService/CommentCatalogSelector.jsp?Type=1"> </z:select>
                     &nbsp;<font color="#999999">审核状态</font>
                    <z:select id="VerifyStatus" style="width:60px;" onChange="changeVerifyStatus();">
                        <span value="X">未审核</span>
                        <span value="Y">已通过</span>
                        <span value="N">未通过</span>
                        <span value="All">全部</span>
                    </z:select>
                    <input type="hidden" id="CatalogID" name="CatalogID" value=""/>
                    </td>
				</tr>
				<tr>   
					<td style="padding:0px 5px;">
					<z:datagrid id="dg1" method="com.zving.cms.dataservice.Comment.dg1DataBind" size="15">
						<table width="100%" cellpadding="2" cellspacing="0"	class="dataTable">
							<tr ztype="head"  class="dataTableHead">
								<td width="2%">&nbsp;</td>
								<td width="3%" ztype="selector" field="id">&nbsp;</td>
								<td width="6%"><b>关联ID</b></td>
								<td width="17%"><b>关联标题</b></td>
								<td width="9%"><b>评论标题</b></td>
                                <td width="23%"><b>评论内容</b></td>
								<td width="8%"><b>评论者</b></td>
                                <td width="10%"><b>所属栏目</b></td>
								<td width="8%">审核状态</td>
								<td width="14%"><b>时间</b></td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC" onDblClick="showDetail();">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td style="cursor:default;"><span title="${RelaID}">${RelaID}</span></td>
								<td><a href="${PreLink}" title="${Name}" target="_blank">${Name}</a></td>
								<td style="cursor:default;"><span title="${Title}">${Title}</span></td>
                                <td style="cursor:default;"><span title="${Content}">${Content}</span></td>
								<td style="cursor:default;"><span title="${AddUser}">${AddUser}</span></td>
                                <td style="cursor:default;"><span title="${CatalogName}">${CatalogName}</span></td>
								<td style="cursor:default;"><span title="${VerifyFlagName}">${VerifyFlagName}</span></td>
								<td style="cursor:default;"><span title="${addTime}">${addTime}</span></td>
							</tr>
							<tr ztype="pagebar">
								<td height="13" colspan="10" align="center">${PageBar}</td>
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
