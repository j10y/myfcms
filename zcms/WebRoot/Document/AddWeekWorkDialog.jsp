<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script type="text/javascript">
function change(number){
	window.location="./AddWeekWorkDialog.jsp?dayTime="+number;
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.new_content{ margin:0 auto;}
.new_content input{ width:95%}
</style>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.document.Weekwork.initAdd">

	<form id="form2" method="post">
	<input name="ID" type="hidden" id="ID" value="${ID}" />
	<table width="95%" cellpadding="2" cellspacing="0" class="new_content">
		<tr>
			<td width="100"></td>
			<td width="10" height="10"></td>
			<td></td>
		</tr>
        <tr><td><select onChange="change(this.options[this.options.selectedIndex].value)">
        <option value="0" >本周</option>
        <option value="1" <%if(request.getParameter("dayTime")!=null && request.getParameter("dayTime").equals("1")){%>selected<% }%>>第二周</option>
        <option value="2" <%if(request.getParameter("dayTime")!=null && request.getParameter("dayTime").equals("2")){%>selected<% }%>>第三周</option>
        <option value="3" <%if(request.getParameter("dayTime")!=null && request.getParameter("dayTime").equals("3")){%>selected<% }%>>第四周</option>
        </select></td></tr>
		<tr><td width="10%">日期</td><td nowrap="nowrap">星期</td><td width="45%">内容</td><td width="24%">参与人员</td><td width="17%">地点</td> </tr>
        <tr><td>${day0}<input type="hidden" id="day0" value="${day0}"></td><td nowrap="nowrap">星期一</td><td><input type="text" id="Content0"></td>
            <td><input type="text" id="Partner0"></td><td><input type="text" id="Workplace0"></td></tr>
        <tr><td>${day1}<input type="hidden" id="day1" value="${day1}"></td><td nowrap="nowrap">星期二</td><td><input type="text" id="Content1"></td>
        	<td><input type="text" id="Partner1"></td><td><input type="text" id="Workplace1"></td></tr>
        <tr><td>${day2}<input type="hidden" id="day2" value="${day2}"></td><td nowrap="nowrap">星期三</td><td><input type="text" id="Content2"></td>
        	<td><input type="text" id="Partner2"></td><td><input type="text" id="Workplace2"></td></tr>
        <tr><td>${day3}<input type="hidden" id="day3" value="${day3}"></td><td nowrap="nowrap">星期四</td><td><input type="text" id="Content3"></td>
        	<td><input type="text" id="Partner3"></td><td><input type="text" id="Workplace3"></td></tr>
        <tr><td>${day4}<input type="hidden" id="day4" value="${day4}"></td><td nowrap="nowrap">星期五</td><td><input type="text" id="Content4"></td>
        	<td><input type="text" id="Partner4"></td><td><input type="text" id="Workplace4"></td></tr>
        <tr><td>${day5}<input type="hidden" id="day5" value="${day5}"></td><td nowrap="nowrap">星期六</td><td><input type="text" id="Content5"></td>
        	<td><input type="text" id="Partner5"></td><td><input type="text" id="Workplace5"></td></tr>
        <tr><td>${day6}<input type="hidden" id="day6" value="${day6}"></td><td nowrap="nowrap">星期日</td><td><input type="text" id="Content6"></td>
        	<td><input type="text" id="Partner6"></td><td><input type="text" id="Workplace6"></td></tr>
		<tr>
			<td colspan="5" valign="middle">备注：<textarea name="textarea" cols="100" rows="10" id="textarea" >${Notes}</textarea></td>
		</tr>													
	</table>
	</form>
</z:init>
</body>
</html>
