<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script type="text/javascript">
</script>
</head>
<%
	String votelogID = request.getParameter("ID");
	if (!StringUtil.isEmpty(votelogID)) {
		ZCVoteLogSchema zcvotelog = new ZCVoteLogSchema();
		zcvotelog.setID(votelogID);
		if(zcvotelog.fill()) {
			String[] resultArr = StringUtil.splitEx(zcvotelog.getResult(), "$|");
%>
<body>
<input type='hidden' id='ID' name='ID' value='<%=request.getParameter("ID")%>'>
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd"><img
						src="../Icons/icon032a1.gif" />投票明细（IP：<%=zcvotelog.getIP()%>,投票人：<%if(StringUtil.isNotEmpty(zcvotelog.getAddUser()))out.print(zcvotelog.getAddUser());%>）</td>
				</tr>
				
				<% for(int i = 0; i < resultArr.length - 1; i++) { 
					DataTable item = new QueryBuilder("select * from ZCVoteItem where ID = ?", StringUtil.splitEx(resultArr[i], "$&")[0]).executeDataTable();
					if(item.getRowCount() > 0) {
				%>
				<tr>
					<td>
						<%=new QueryBuilder("select Subject from ZCVoteSubject where VoteID = ? and ID = ?", zcvotelog.getVoteID(), item.getString(0, "SubjectID")).executeString() %>
						：<%=item.getString(0, "Item") %>
						<% if("1".equals(item.getString(0, "ItemType")) && StringUtil.splitEx(resultArr[i], "$&").length > 1) { 
							 %>
							<br/>选择原因：<%=StringUtil.splitEx(resultArr[i], "$&")[1] %>
						<%}%>
					</td>
				</tr>
				<%}
			}
		}
	}%>
			</table>
			</td>
		</tr>
	</table>

</body>
</html>