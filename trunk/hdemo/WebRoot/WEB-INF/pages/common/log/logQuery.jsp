<%@ include file="../../tagLib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
<%@ include file="../calendar.jsp"%>
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
				<form method="post" action="<c:url value="/logQuery.do"/>"
					name="queryForm" id="queryForm"><input type="hidden"
					name="pageNo" id="pageNo"
					value="<c:out value="${formInfo.pageNo}"/>"> <input
					type="hidden" name="recPerPage" id="recPerPage"
					value="<c:out value="${formInfo.recPerPage}"/>"> <input
					type="hidden" name="returnUrl" id="returnUrl" value=""> <input
					type="hidden" name="queryUrl" id="queryUrl"
					value="<c:url value="/logQuery.do"/>">
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
					<tr class="queryConditionRow">
						<td class="queryConditionLabelCell"><fmt:message
							key="public.label.beginDateTime" /></td>
						<td class="queryConditionDataCell"><input class="inputText"
							name="beginDateTime" type="text" id="beginDateTime"
							value="<c:out value="${formInfo.beginDateTime}"/>" size="16">
						</td>
						<td class="queryConditionLabelCell"><fmt:message
							key="public.label.endDateTime" /></td>
						<td class="queryConditionDataCell"><input class="inputText"
							name="endDateTime" type="text" id="endDateTime"
							value="<c:out value="${formInfo.endDateTime}"/>" size="16">
						</td>
						<td class="queryConditionLabelCell"><fmt:message
							key="log.label.user" /></td>
						<td class="queryConditionDataCell"><input class="inputText"
							name="user" type="text" id="user"
							value="<c:out value="${formInfo.user}"/>" size="12"></td>
					</tr>
					<tr class="queryConditionRow">
						<td class="queryConditionLabelCell"><fmt:message
							key="log.label.logObject" /></td>
						<td class="queryConditionDataCell"><select id="logObject"
							name="logObject">
							<option></option>						
							<c:forEach items="${objectList}" var="log">
								<option value="<c:out value="${log.logObject}"/>"
									<c:if test="${formInfo.logObject == log.logObject}"> selected</c:if>>--<c:out
									value="${log.logObject}" /></option>
							</c:forEach>
						</select></td>
						<td class="queryConditionLabelCell"><fmt:message
							key="log.label.logAction" /></td>
						<td class="queryConditionDataCell"><select id="logAction"
							name="logAction">
							<option></option>
							<c:forEach items="${actionList}" var="log">
								<option value="<c:out value="${log.logAction}"/>"
									<c:if test="${formInfo.logAction == log.logAction}"> selected</c:if>><c:out
									value="${log.logAction}" /></option>
							</c:forEach>
						</select></td>
						<td class="queryConditionLabelCell"><fmt:message
							key="log.label.user" /><fmt:message key="log.label.childsiteId" /></td>
						<td class="queryConditionDataCell"><select id="childsiteId"
							name="childsiteId">
							<option><fmt:message key="log.label.org"/></option>
							<c:forEach items="${orgList}" var="org">
								<option value="<c:out value="${org.orgId}"/>"
									<c:if test="${formInfo.childSiteId == org.orgId}"> selected</c:if>>--<c:out
									value="${org.orgName}" /></option>
							</c:forEach>
							<option><fmt:message key="log.label.childsite"/></option>
							<c:forEach items="${childsiteList}" var="org">
								<option value="<c:out value="${org.orgId}"/>"
									<c:if test="${formInfo.childSiteId == org.orgId}"> selected</c:if>>--<c:out
									value="${org.orgName}" /></option>
							</c:forEach>
						</select></td>
					</tr>
					<tr class="queryConditionBottomRow">
						<td colspan="6" class="queryConditionBottomCell"><input
							class="button_query" type="button" name="Submit"
							onclick="javascript:queryFormSubmit(1);" /></td>
					</tr>
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td class="queryMiddleCell"><input class="button_export"
							type="button" onclick="javascript:exportLog();" />&nbsp;&nbsp;<input
							class="button_clear1" type="button"
							onclick="javascript:deleteLog();" /></td>
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
									key="log.label.logQuery" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
						<td width="8%" class="queryListTitleCell"><fmt:message
							key="log.label.user" /></td>
						<td width="17%" class="queryListTitleCell"><fmt:message
							key="log.label.logTime" /></td>
						<td width="15%" class="queryListTitleCell"><fmt:message
							key="log.label.logObject" /></td>
						<td width="12%" class="queryListTitleCell"><fmt:message
							key="log.label.logAction" /></td>
						<td width="48%" class="queryListTitleCell"><fmt:message
							key="log.label.detail" /></td>
					</tr>
					<c:forEach items="${logList}" var="log" varStatus="status">
						<tr class="queryListRow">
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${log.user.name}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><fmt:formatDate
								value="${log.logTime}" pattern="${dateFormatLongSecond}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${log.logObject}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:out
								value="${log.logAction}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><app:out
								value="${log.detail}" /></td>
						</tr>
					</c:forEach>
				</table>
				<table class="queryBottomTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${logCount}"
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
<script type="text/javascript">
    Calendar.setup({
        inputField     :    "beginDateTime",   // id of the input field
        ifFormat       :    "%Y-%m-%d %H:%M",       // format of the input field
        showsTime      :    true
    });
    Calendar.setup({
        inputField     :    "endDateTime",   // id of the input field
        ifFormat       :    "%Y-%m-%d %H:%M",       // format of the input field
        showsTime      :    true
    });
    
    function deleteLog() {
      if (showDataDeleteConfirm() == "0"){
        return;
      }
      var submitForm = document.getElementById("queryForm");
      if (submitForm != null) {
        submitForm.action = "<c:url value="/logDelete.do"/>";
        submitForm.method = "get";
        document.getElementById("returnUrl").value = "<c:out value="${requestUrl}"/>";
        submitForm.submit();
      }
    }
    function exportLog() {
      var submitForm = document.getElementById("queryForm");
      if (window.urlSubmit != null && window.urlSubmit() == false) 
  		return;
      if (submitForm != null) {
        submitForm.action = "<c:url value="/logExport.do"/>";
        submitForm.method = "get";
        submitForm.target = "_blank";
        document.getElementById("returnUrl").value = "<c:out value="${requestUrl}"/>";
        submitForm.submit();
      }
    }
    
    window.urlSubmit = function(){
		document.queryForm.action = document.getElementById("queryUrl").value;
		document.queryForm.target = "_self";
	}
    
</script>
</BODY>
</HTML>
