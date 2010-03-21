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
	
	var startDate = DateTime.toString(new Date(new Date()-30*24*60*60*1000));
	var endDate = DateTime.toString(new Date());		
	var chart = new Chart("Line2D","Chart1",680,250,"com.zving.cms.stat.report.SummaryReport.getChartData");
	chart.addParam("StartDate",startDate);
	chart.addParam("EndDate",endDate);
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
				<td style="padding:4px 10px;" class="blockTd">
				<div style="float:left;"><strong>综合报告</strong><strong>:&nbsp;&nbsp;</strong>
				</div>
				</td>
			</tr>
			<tr>
				<td style="padding:0px;">
				<div id="divChart" style="width:680px;height:250px"></div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:8px;"><strong>
				<z:init method="com.zving.cms.stat.report.SummaryReport.init">
              从 ${StartDate} 开始统计，总计 ${TotalDays} 天            </z:init> </strong></td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.stat.report.SummaryReport.dg1DataBind"
					autoFill="false" page="false">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="13%" sortfield="orderflag" direction="">&nbsp;</td>
							<td width="19%"><strong>PV数量</strong></td>
							<td width="18%"><strong>UV数量</strong></td>
							<td width="16%"><strong>IP数量</strong></td>
							<td width="17%">回头率</td>
							<td width="17%">平均访问时长</td>
						</tr>
						<tr>
							<td>${Type}</td>
							<td>${PV}</td>
							<td>${UV}</td>
							<td>${IP}</td>
							<td>${ReturningRate}</td>
							<td>${StickTotalTime}</td>
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
