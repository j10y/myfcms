<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../ymPrompt.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
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
				<form method="post" action="<c:url value="/flinkQuery.do?type=1"/>"
					name="queryForm" id="queryForm"><input type="hidden"
					name="pageNo" id="pageNo"
					value="<c:out value="${formInfo.pageNo}"/>"> <input
					type="hidden" name="recPerPage" id="recPerPage"
					value="<c:out value="${formInfo.recPerPage}"/>"> <input
					type="hidden" name="returnUrl" id="returnUrl"
					value="<c:out value="${param.returnUrl}"/>"><input
					type="hidden" name="deleteUrl" id="deleteUrl" />
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
							key="flink.label.category" /></td>
						<td class="queryConditionDataCell" width="50%"><select
							id="category" name="category" onchange="queryForm.submit()">
							<c:forEach items="${dictList}" var="dict">
								<option value="<c:out value="${dict.recordId}"/>"
									<c:if test="${formInfo.category == dict.recordIdString}"> selected</c:if>><c:out
									value="${dict.dictItem}" /></option>
							</c:forEach>
						</select></td>
					</tr>
					<!--tr>
						<td class="queryConditionBottomRow" colspan="2"><input
							type="button" name="Submit" class="button_query" value=""
							onclick="javascript:queryFormSubmit(1);"></td>
					</tr-->
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td width="47%" class="queryMiddleCell"><input
							class="button_add" type="button"
							onclick="javascript:window.location='<c:url value="/flinkEdit.do?category=${formInfo.category}&editFlag=add&returnUrl=${requestUrl}"/>';"></td>
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
									key="flink.label.flinkManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
						<td width="20%" class="queryListTitleCell"><fmt:message
							key="flink.label.title" /></td>
						<td class="queryListTitleCell"><fmt:message
							key="flink.label.link" /></td>
						<td width="8%" class="queryListTitleCell"><fmt:message
							key="flink.label.orderNo" /></td>
						<td width="16%" class="queryListTitleCell" colspan="2"><fmt:message
							key="public.label.action" /></td>
					</tr>
					<c:forEach items="${flinkList}" var="flinks" varStatus="status">
						<tr class="queryListRow">
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="<c:out value="${flinks.link}"/>" target="_blank"><c:out
								value="${flinks.title}" /></a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${flinks.link}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${flinks.inorder}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="<c:url value="/flinkEdit.do?id=${flinks.id}&type=1&editFlag=modify&returnUrl=${requestUrl}"/>"><fmt:message
								key="public.label.modify" /></a></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="#" onclick="del(<c:out value="${flinks.id}"/>)" ><fmt:message
								key="public.label.delete" /></a></td>
						</tr>
					</c:forEach>
				</table>
				<table class="queryBottomTable" width="97%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${count}"
							recPerPage="${formInfo.recPerPage}" queryFormId="queryForm"
							queryFormPageNoId="pageNo" queryFormRecPerPageId="recPerPage" /></td>
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
		document.getElementById("deleteUrl").value = '<c:url value="/flinkDelete.do?id='+id+'&returnUrl=${requestUrl}"/>';
		dataDeleteConfirm();
    }
</script>
</BODY>
</HTML>
