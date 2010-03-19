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
				<form method="post" action="<c:url value="/dictionaryQuery.do"/>"
					name="queryForm" id="queryForm"><input type="hidden"
					name="pageNo" id="pageNo"
					value="<c:out value="${formInfo.pageNo}"/>"> <input
					type="hidden" name="recPerPage" id="recPerPage"
					value="<c:out value="${formInfo.recPerPage}"/>"><input
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
						<td class="queryConditionLabelCell" width="25%"><fmt:message
							key="dictionary.label.dictClass" /></td>
						<td class="queryConditionDataCell" width="15%"><select
							name="dictClass" id="dictClass" class="inputSelectMenu" onchange="queryForm.submit()">
							<option value=""></option>
							<c:forEach items="${dictCategoryList}" var="dictCategory">
								<option value="<c:out value="${dictCategory.categoryId}"/>"
									<c:if test="${dictCategory.categoryId == formInfo.dictClass}">selected</c:if>><c:out
									value="${dictCategory.title}" /></option>
							</c:forEach>
						</select></td>
						<td class="queryConditionLabelCell" width="12%"><fmt:message
							key="dictionary.label.dictItem" /></td>
						<td class="queryConditionDataCell" width="38%"><input
							class="inputText" name="dictItem" type="text" id="dictItem"
							size="27" value="<c:out value="${formInfo.dictItem}"/>"></td>
					</tr>
					<tr>
						<td class="queryConditionBottomRow" colspan="4"><input
							type="button" name="Submit" class="button_query"
							onclick="javascript:queryFormSubmit(1);"></td>
					</tr>
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td class="queryMiddleCell"><input class="button_add"
							type="button"
							onclick="javascirpt:window.location='<c:url value="/dictionaryEdit.do?editFlag=add&dictClass=${formInfo.dictClass}&returnUrl=${requestUrl}"/>'">
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
									key="dictionary.label.dictionaryManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
						<td width="40%" class="queryListTitleCell"><fmt:message
							key="dictionary.label.dictClass" /></td>
						<td width="30%" class="queryListTitleCell"><fmt:message
							key="dictionary.label.dictItem" /></td>
						<td width="10%" class="queryListTitleCell"><fmt:message
							key="dictionary.label.dictOrder" /></td>
						<td width="10%" class="queryListTitleCell">&nbsp;</td>
						<td width="10%" class="queryListTitleCell">&nbsp;</td>
					</tr>
					<c:forEach items="${dictList}" var="dict" varStatus="status">
						<tr class="queryListRow">
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${dict.dictClassName}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${dict.dictItem}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${dict.dictOrder}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if
								test="${dict.flag == '0'}">
								<a
									href="<c:url value="/dictionaryEdit.do?editFlag=modify&recordId=${dict.recordId}&returnUrl=${requestUrl}"/>"><fmt:message
									key="public.label.modify" /></a>
							</c:if></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if
								test="${dict.flag == '0'}"><a href="#" onclick="del(<c:out value="${dict.recordId}"/>);"><fmt:message
									key="public.label.delete" /></a>
							</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<table class="queryBottomTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${dictCount}"
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
<script type="text/javascript">  
function del(id)
    {
		document.getElementById("deleteUrl").value = '<c:url value="/dictionaryDelete.do?recordId='+id+'&returnUrl=${requestUrl}"/>';
		dataDeleteConfirm();
    }
</script>
</BODY>
</HTML>
