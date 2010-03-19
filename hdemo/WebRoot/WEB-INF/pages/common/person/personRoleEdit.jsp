<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
</HEAD>
<BODY class="bellBody">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="12" class="topCell"></td>
	</tr>
	<tr>
		<td valign="top" class="topCell">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="15" height="12"></td>
				<td width="12"><img src="images/index_t_r6_c10.png" width="12"
					height="12" /></td>
				<td height="12" background="images/index_t_r6_c17b.png"></td>
				<td width="12"><img src="images/index_t_r6_c17.png" width="12"
					height="12" /></td>
				<td width="15" height="12"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td background="images/index_t_r8_c10.png">&nbsp;</td>
				<!-- center -->
				<td class="centerCell">
				<form method="post" action="<c:url value="/personRoleEdit.do"/>">
				<input type="hidden" name="userId"
					value="<c:out value="${formInfo.userId}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>">
				<table width="97%" border="0" cellspacing="0" cellpadding="0"
					class="bellTable">
					<tr class="bellTitleRow">
						<td width="95"><img src="images/eis_p_b1.png" width="95"
							height="50" /></td>
						<td valign="bottom">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="bellTitleCell"><fmt:message
									key="person.label.roleGrant" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="person.label.name" /></td>
						<td class="bellContentDataCell" width="70%"><c:out
							value="${person.name}" /></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="person.label.role" /></td>
						<td class="bellContentDataCell">
						<table width="90%" border="0">
							<c:forEach items="${roleList}" var="role">
								<tr>
									<td><input type="checkbox" name="roles" value="<c:out value="${role.id}"/>"
										<c:if test="${formInfo.role[role.id] != null}">checked</c:if>>
									<c:out value="${role.roleName}" /></td>
								</tr>
							</c:forEach>
						</table>
						</td>
					</tr>
					<tr class="bellBottomRow">
						<td colspan="2" class="bellBottomCell"><input type="submit"
							name="Submit" value="<fmt:message key="public.label.save"/>">&nbsp;&nbsp;&nbsp;&nbsp;<input
							value="<fmt:message key="public.label.return"/>" type="button"
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';">
						</td>
					</tr>
				</table>
			</form>
			</td>
			<!-- center end -->
			<td background="images/index_t_r8_c19.png">&nbsp;</td>
			<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td><img src="images/index_t_r6_c10d.png" width="12"
					height="12" /></td>
				<td background="images/index_t_r6_c17bd.png"></td>
				<td><img src="images/index_t_r6_c17d.png" width="12"
					height="12" /></td>
				<td></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</BODY>
</HTML>
