<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合报告</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Chart.js"></script>
<script>
Page.onLoad(function(){
	var path = window.location.pathname;
	path = path.substring(path.lastIndexOf("/")+1);
	Tree.select("tree1","target",path);
	
	var chart = new Chart("Pie3D","Chart1",680,300,"com.zving.cms.stat.report.ClientReport.getChartData");
	chart.addParam("Type","Screen");
	chart.show("divChart");
});
</script>
</head>
<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="ListType" value='${ListType}'>
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
				<div style="float:left;"><strong>屏幕分辨率:</strong></div>
				</td>
			</tr>
			<tr>
				<td style="padding:0;">
				<div id="divChart" style="width:680px;height:300px"></div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.stat.report.ClientReport.dgScreenDataBind"
					autoFill="false" page="false">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="33%" sortfield="orderflag" direction="">名称</td>
							<td width="15%"><strong>PV数</strong></td>
							<td width="16%"><strong>比例</strong></td>
							<td width="14%">趋势</td>
						</tr>
						<tr>
							<td>${Item}</td>
							<td>${Screen}</td>
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
