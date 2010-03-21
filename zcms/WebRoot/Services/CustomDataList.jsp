<%@ taglib uri="controls" prefix="z"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=utf-8");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自定义数据列表</title>
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
.voteresultb table{
	width:100%;
	font-size:12px;
	border-collapse:collapse;
}
.voteresultb td {
	padding:4px 8px;
	text-indent:0;
	background-color:#FFFFFF;
	color:#363636;
}
.voteresultb td, .voteresultb th{
	border-collapse:collapse;
	border-color:#D5D9D8;
	border-style:solid;
	border-width:1px;
}
</style>
</head>
<body style=" text-align:center">
<div id="voteresult" style=" text-align:left">
  <h2>数据列表</h2>
  <div class="voteresultb">
    <table width="100%" cellspacing="2">
		<z:datalist id="dg1" method="com.zving.cms.dataservice.CustomTableAjax.dataBindAllColumns" size="15" page="true">
			${RowHTML}
		</z:datalist>
    </table>
    <table width="100%">
      <tr><z:pagebar target="dg1" /></tr>
    </table>
  </div>
</div>
</body>
</html>
