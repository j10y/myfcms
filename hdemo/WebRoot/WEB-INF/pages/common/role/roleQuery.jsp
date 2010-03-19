<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<%@ include file="../ymPrompt.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE><fmt:message key="mainFrame.title"/></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css"/>
<script type="text/javascript" src="scripts/common/common.js"></script>
</head>
<body class="queryBody">
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
				<form method="post" action="<c:url value="/roleQuery.do"/>"
					name="queryForm" id="queryForm"><input type="hidden"
					name="deleteUrl" id="deleteUrl"><input type="hidden"
					name="pageNo" id="pageNo"
					value="<c:out value="${formInfo.pageNo}"/>"><input
					type="hidden" name="recPerPage" id="recPerPage"
					value="<c:out value="${formInfo.recPerPage}"/>">
				<table width="97%" border="0" cellspacing="0" cellpadding="0"
					class="queryConditionTable">
					<tr class="queryConditionTitleRow">
						<td width="95"><img src="images/eis_p_b1.png" width="95"
							height="50" /></td>
						<td valign="bottom">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="public.label.queryCondition" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryConditionTable">
					<tr>
						<td class="queryConditionLabelCell" width="50%"><fmt:message
							key="role.label.roleName" /></td>
						<td class="queryConditionDataCell" width="50%"><input
							class="inputText" type="text" name="roleName" id="roleName"
							value="<c:out value="${formInfo.roleName}"/>"></td>
					</tr>
					<tr>
						<td class="queryConditionBottomRow" colspan="2"><input
							type="button" name="Submit" class="button_query"
							onclick="javascript:queryFormSubmit(1);"/></td>
					</tr>
				</table>				
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td class="queryMiddleCell"><input type="button"
							class="button_add" onclick="javascript:window.location='<c:url value="/roleEdit.do?editFlag=add&returnUrl=${requestUrl}"/>';"/>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="95"><img src="images/eis_p_b3.png" width="95"
							height="50" /></td>
						<td valign="bottom" background="images/eis_p_b4.png">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="role.label.roleManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
						<td class="queryListTitleCell" width="30%"><fmt:message
							key="role.label.roleName" /></td>
						<td class="queryListTitleCell" width="20%"><fmt:message
							key="role.label.batPrivi" /></td>
						<td class="queryListTitleCell" width="20%" colspan="2"><fmt:message
							key="public.label.action" /></td>
						<td class="queryListTitleCell" width="15%"><fmt:message
							key="role.label.rolePrivi" /></td>
						<td class="queryListTitleCell" width="15%"><fmt:message
							key="org.label.orgPrivi" /></td>
					</tr>
					<c:forEach items="${pagination.list}" var="role" varStatus="status">
						<tr class="queryListRow">
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${role.roleName}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a	href="<c:url value="/roleUser.do?roleId=${role.id}&returnUrl=${requestUrl}"/>">分配给用户</a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="<c:url value="/roleEdit.do?editFlag=modify&roleId=${role.id}&returnUrl=${requestUrl}"/>"><fmt:message
								key="public.label.modify" /></a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="#"
								onclick="del(<c:out value="${role.id}"/>);"><fmt:message
								key="public.label.delete" /></a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="<c:url value="/rolePrivilegeEdit.do?roleId=${role.id}&returnUrl=${requestUrl}"/>"><fmt:message
								key="role.label.privilegeGrant" /></a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if test="${role.publicFlag == '0'}"><a
								href="<c:url value="/treePriviEdit.do?roleId=${role.id}&returnUrl=${requestUrl}"/>"><fmt:message
								key="role.lable.treePriviGrant" /></a></c:if></td>
						</tr>
					</c:forEach>
				</table>
				<table class="queryBottomTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${pagination.totalCount}"
							recPerPage="${formInfo.recPerPage}" queryFormId="queryForm"
							queryFormPageNoId="pageNo" queryFormRecPerPageId="recPerPage" />
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
<script>
function del(id)
    {
		document.getElementById("deleteUrl").value = '<c:url value="/roleDelete.do?roleId='+id+'&returnUrl=${requestUrl}"/>';
		dataDeleteConfirm();
    }
</script>
</body>
</html>
