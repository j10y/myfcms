<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.platform.UserLog.init">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户日志</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function doSearch(){
	var name = "";
	name = $V("SearchUser").trim();
	var ip = "";
	ip = $V("IP").trim();
	var logMessage = "";
	logMessage = $V("LogMessage").trim();
	var startDate = "";
	startDate = $V("StartDate").trim();
	var endDate = "";
	endDate = $V("EndDate").trim();
	var logType = "";
	logType = $V("LogType").trim();
	var subType = "";
	subType = $V("SubType").trim();
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchUser",name);
	DataGrid.setParam("dg1","IP",ip);
	DataGrid.setParam("dg1","LogType",logType);
	DataGrid.setParam("dg1","SubType",subType);
	DataGrid.setParam("dg1","LogMessage",logMessage);
	DataGrid.setParam("dg1","StartDate",startDate);
	DataGrid.setParam("dg1","EndDate",endDate);
	DataGrid.loadData("dg1");
}

function changeLogType(){
	$("SubType").setParam("LogType",$V("LogType"));
	$("SubType").loadData();
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
					src="../Icons/icon018a1.gif" />用户日志
					<z:init method="com.zving.platform.UserLog.initDialog">
					<div style="float: left; white-space: nowrap;">
		                <span >
		                  从&nbsp;<input value="" type="text" id="StartDate" size="12" name="StartDate" ztype="Date"  class="inputText" verify="NotNull">
						至&nbsp;<input value="${Time}" type="text" id="EndDate" size="12" name="EndDate" ztype="Date"  class="inputText" verify="NotNull" >
		                </span>
		                  用户名：<input type="text" name="SearchUser" id="SearchUser"  value="" style="width:60px">
						IP:<input type="text" name="IP" id="IP" value="" style="width:65px">
						<span>
	                  	类型:<z:select id="LogType" style="width:70px" onChange="changeLogType()">${LogType}</z:select>
	                    </span>
	                    <span>
	                  	子类型:
	                  			 <z:select id="SubType" style="width:70px"  method="com.zving.platform.UserLog.getSubType" lazy="true"></z:select>
	                  			
	                    </span>
						消息:<input type="text" name="LogMessage" id="LogMessage" value="" style="width:80px">
						<input type="button" name="Submit" 
						value="查询" onClick="doSearch()">
						</div>
					</z:init>
					</td>
			</tr>
			<tr>
				<td style="padding:0px 5px;"><z:datagrid id="dg1"
					method="com.zving.platform.UserLog.dg1DataBind" page="true"
					size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						style="margin-top:8px" class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" ztype="RowNo">&nbsp;</td>
							<td type="Tree" width="15%"><b>用户名</b></td>
							<td type="Data" width="20%" field="count"><strong>时间</strong></td>
							<td type="Data" width="20%" field="type"><strong>消息类型</strong></td>
							<td type="Data" width="20%" field="addtime"><strong>消息</strong></td>
							<td type="Data" width="20%" field="ip"><strong>IP</strong></td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td align="center">&nbsp;</td>
							<td bordercolor="#0066FF">${UserName}</td>
							<td>${AddTime}</td>
							<td>${LogTypeName}</td>
							<td title="${LogMessage}">${LogMessage}</td>
							<td>${IP}</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="5">${PageBar}</td>
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
</z:init>
