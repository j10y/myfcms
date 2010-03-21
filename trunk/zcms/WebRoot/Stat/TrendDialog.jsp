<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.stat.report.TrendReport.init">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时间趋势</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Chart.js"></script>
<script>
var chart;

var Type = '${Type}';
var SubType = '${SubType}';
var Item = '${Item}';

Page.onLoad(function(){
	var startDate = DateTime.toString(new Date(new Date()-30*24*60*60*1000));
	var endDate = DateTime.toString(new Date());
	$S("StartDate",startDate);
	$S("EndDate",endDate);

	chart = new Chart("Line2D","Chart1",680,250,"com.zving.cms.stat.report.TrendReport.getChartData");
	loadData();
});

function loadData(){
	chart.addParam("StartDate",$V("StartDate"));
	chart.addParam("EndDate",$V("EndDate"));
	chart.addParam("Type",Type);
	chart.addParam("SubType",SubType);
	chart.addParam("Item",Item);
	chart.show("divChart");
}
</script>
</head>
<body>
<input type="hidden" id="CatalogID">
<input type="hidden" id="ListType" value='${ListType}'>
<table width="680" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="blockTable">
			<tr>
				<td style="padding:4px 10px;">
				<div style="float:left;"><strong>时间趋势:</strong> &nbsp;从 <input
					type="text" id="StartDate" style="width:80px; " ztype='date'>
				至 <input type="text" id="EndDate" style="width:80px; " verify="Date"
					ztype='date'> <input type="button" verify="Date"
					name="Submitbutton" id="Submitbutton" value="查看"
					onClick="loadData()"></div>				</td>
			</tr>
			<tr>
				<td style="padding:0;">
				<p>&nbsp;</p>
				<div id="divChart" style="width:680px;height:250px"></div>				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
</z:init>
