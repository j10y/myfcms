<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合报告</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Chart.js"></script>
<script>
Page.onLoad(function(){
	var startDate = DateTime.toString(new Date(new Date()-30*24*60*60*1000));
	var endDate = DateTime.toString(new Date());
	
	$S("StartDate",startDate);
	$S("EndDate",endDate);
});

function loadData(){
	DataGrid.setParam("dg1","StartDate",$V("StartDate"));
	DataGrid.setParam("dg1","EndDate",$V("EndDate"));
	DataGrid.loadData("dg1");
}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="160"><%@include file="StatTypes.jsp"%>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="blockTable">
			<tr>
				<td style="padding:4px 10px;">
				<div style="float:left;"><strong>URL点击量排行:</strong> &nbsp; 从 <input
					name="text" type="text" id="StartDate" style="width:80px; "
					ztype='date'> 至 <input name="text2" type="text"
					id="EndDate" style="width:80px; " verify="Date" ztype='date'>
				<input type="button" verify="Date" name="Submitbutton"
					id="Submitbutton" value="查看" onClick="loadData()"></div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.stat.report.URLReport.dgTopDataBind"
					autoFill="false" size="50">
					<table width="100%" align="center" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="6%" ztype="RowNo">序号</td>
							<td width="52%">URL</td>
							<td width="14%">点击量</td>
							<td width="14%">百分比</td>
							<td width="14%">趋势</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><a href="${Item}"
								target="_blank">${Item}</a></td>
							<td>${Top}</td>
							<td>${Rate}</td>
							<td>${Trend}</td>
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
