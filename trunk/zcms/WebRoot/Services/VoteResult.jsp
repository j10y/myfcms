<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="controls" prefix="z"%>
<%@page import="com.zving.cms.dataservice.VoteResult"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=utf-8");
if(!VoteResult.vote(request,response)){
	return;	
}
%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投票调查结果</title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Chart.js"></script>
<style type="text/css">

#voteresult{
width:850px;
margin:0 auto;
margin-top:10px;
background:#E1F4FF;
padding:10px;}
#voteresult h2{
font-size:14px;
font-weight:bold;
line-height:30px;
padding:0;
margin:0;
padding-left:5px;
}
.voteresultb{
	padding:10px;
	border:1px solid #58CDFA;
	background:#FFFFFF;
}
.voteresultb h3{
font-size:14px;
font-weight:bold;
line-height:30px;
padding:0;
padding-left:10px;
margin:0;
}
.voteresultb table{
width:100%;
font-size:12px;
border-collapse:collapse;}
.voteresultb th {
background-color:#F6F6F6;
border-top:medium none;
color:#666666;
font-weight:normal;
line-height:24px;
padding:0;
text-align:center;
}
.voteresultb td.col1 {
line-height:normal;
padding:6px 10px;
text-indent:0;
}
.col1 span {
background:url(../Images/imgbg02.gif) no-repeat;
color:#FFFFFF;
display:block;
float:left;
height:12px;
line-height:12px;
margin:0 6px 0 0;
padding:0 2px 2px 0;
text-align:center;
width:18px;
}
.col1 p {
font-size:14px;
margin:-1px 0 0 28px;
}
.percent_bg {
background-color:#D9E4F8;
position:absolute;
right:20px;
top:12px;
width:67px;
}
.percent_bg, .percent {
display:block;
height:8px;
overflow:hidden;
text-align:left;
text-indent:-999em;
}
.voteresultb td {
color:#363636;
line-height:33px;
}
.percent{
background:url(../Images/imgbg01.gif) no-repeat scroll 0 0;
}
.percent_bg, .percent {
display:block;
height:8px;
overflow:hidden;
text-align:left;
text-indent:-999em;
}
th.col2, th.col3, td.col2, td.col3 {
border-left-width:1px;
}
.voteresultb td {
background-color:#FFFFFF;
color:#363636;
line-height:33px;
}
.voteresultb td, .voteresultb th{
border-collapse:collapse;
border-color:#D5D9D8;
border-style:solid;
border-width:1px;
}
.col2 {
table-layout:fixed;
width:200px !important;
}
.col2 div {
height:33px;
padding-left:10px;
position:relative;
text-align:left;
width:45px;
}
</style>

</head>

<body style=" text-align:center">

<z:init method="com.zving.cms.dataservice.VoteReport.init">
	<div id="voteresult" style=" text-align:left">
		<h2>${Title}：调查结果</h2>
		<z:simplelist method="com.zving.cms.dataservice.VoteReport.getList1">
			<div class="voteresultb">
			<h3>${_RowNo}.${Subject}[${Type1}]</h3>
				<table>
					<script>
					Page.onLoad(function(){
						var chart = new Chart("Pie3D","Chart${ID}",350,200,"com.zving.cms.dataservice.VoteReport.getVoteData");
						chart.addParam("SubjectID","${ID}");
						chart.show("divChart${ID}");
						
					});
					</script>
					<tbody>
						<tr class="row0">
							<th class="col1" scope="col">选项</th>
							<th class="col2" scope="col">比例</th>
				            <th width="250" valign="top" rowspan="${count1}"  style="padding:0px;text-align:center">
								<div id="divChart${ID}" style="width:350px;height:200px;text-align:center"></div>
								</th>
						</tr>
						<z:simplelist method="com.zving.cms.dataservice.VoteReport.getList2">
							<tr class="row1">
								<td class="col1"><span>${count}</span>
								<p>${Item}</p>
								</td>
								<td class="col2">
								<div style="width:190px; line-height:33px;position:relative;">
								${Score}票 比例:${percent}%<span class="percent_bg"><span class="percent"
									style="width: ${percent}%;" /></span></span></div>
								</td>
							</tr>
						</z:simplelist>
					</tbody>
				</table>
			</div>
		</z:simplelist>
	</div>
</z:init>
</body>
</html>
