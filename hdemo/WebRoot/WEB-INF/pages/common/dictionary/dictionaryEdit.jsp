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
				<form method="post" action="<c:url value="/dictionaryEdit.do"/>">
				<input type="hidden" name="recordId"
					value="<c:out value="${formInfo.recordId}"/>"> <input
					type="hidden" name="editFlag"
					value="<c:out value="${formInfo.editFlag}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"><spring:bind
					path="formInfo.*">
					<c:if test="${status.error}">
						<table width="548" border="0" align="center" cellpadding="0"
							cellspacing="1" class="billMsgTable">
							<tr class="billMsgRow">
								<td class="billMsgCell"><c:forEach
									items="${status.errorMessages}" var="errorMessage">
									<c:out value="${errorMessage}" />
									<br>
								</c:forEach></td>
							</tr>
						</table>
					</c:if>
				</spring:bind>
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
									key="dictionary.label.dictionaryManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">	
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="dictionary.label.dictClass" /></td>
						<td class="bellContentDataCell"><select name="dictClass"
							id="dictClass" class="inputSelectMenu">
							<c:forEach items="${dictClassList}" var="dictClass">
								<option value="<c:out value="${dictClass.categoryId}"/>"
									<c:if test="${dictClass.categoryId == formInfo.dictClass}">selected</c:if>><c:out
									value="${dictClass.title}" />
							</c:forEach>
						</select></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="dictionary.label.dictItem" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							name="dictItem" type="text" id="dictItem"
							value="<c:out value="${formInfo.dictItem}"/>"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="dictionary.label.dictOrder" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							name="dictOrder" type="text" id="dictOrder"
							value="<c:out value="${formInfo.dictOrder}"/>"></td>
					</tr>
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2"><input class="button_save"
							type="submit" name="submit" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"
							class="button_return" type="button"
							name="<fmt:message key="public.label.return"/>"></td>
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
