<%@ include file="../tagLib.jsp"%>
<HTML>
<HEAD>
<TITLE></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css">
<!--[if gte IE 5.5000]><script type="text/javascript" src="scripts/common/fixPng.js"></script><![endif]--> 
</HEAD>
<BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0
	MARGINHEIGHT=0>
<table width="100%" height="152" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="114" align="center" valign="bottom"
			background="images/eis2_r1_c1.png">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15">&nbsp;</td>
				<td width="436" background="images/eis_r2_c24.png"><img
					src="images/index_t_r2_c2l.png" width="443" height="102" /></td>
				<td align="center" background="images/index_t_r2_c2t.png">
				<table width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="30" align="right" valign="middle">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50" align="center"><img
									src="images/MSN-Groups.png" width="25" height="25" /></td>
								<td align="right" class="fffff_t"><c:out
									value="${userInfo.user.name}" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td align="right" class="fffff_t_14"><fmt:message
							key="mainFrame.copyright" /></td>
					</tr>
				</table>
				</td>
				<td width="10"><img src="images/index_t_r2_c2.png" width="10"
					height="102" /></td>
				<td width="15">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="38" align="center" valign="bottom"
			background="images/eis2_r2_c1.png">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15">&nbsp;</td>
				<td width="10"><img src="images/index_t_r4_c2.png" width="10"
					height="30" /></td>
				<td height="30" align="left" background="images/index_t_r4_c7b.png">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15%" align="left">
						<table width="172" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="46" align="center"><img src="images/Away.png"
									width="25" height="25" /></td>
								<td width="126" class="fffff_t"><c:out value="${today}" />&nbsp;</td>
							</tr>
						</table>
						</td>
						<td width="85%" align="right">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="100" align="right"><img
									src="images/Msn_Butterfly.png" width="25" height="25" /></td>
								<td width="70" align="center" class="m_td"><a
									href="<c:url value="/passwordModify.do"/>" target="mainFrame"
									class="m_td"><fmt:message
									key="person.label.modifyPassword" /></a></td>
								<td width="70" align="center" class="m_td"><a
									href="<c:url value="/logout.do"/>" target=_parent class="m_td"><fmt:message
									key="public.label.logout" /></a></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
				<td width="10"><img src="images/index_t_r4_c7.png" width="10"
					height="30" /></td>
				<td width="15">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</BODY>
</HTML>
