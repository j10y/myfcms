<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<%@ include file="../calendar.jsp"%>
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
				<form method="post" name="flinkEditForm"
					action="<c:url value="/flinkEdit.do"/>"><input type="hidden"
					name="id" value="<c:out value="${formInfo.id}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"> <input
					type="hidden" name="editFlag"
					value="<c:out value="${formInfo.editFlag}"/>">
					<spring:bind path="formInfo.*">
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
									key="flink.label.flinkManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">					
							<tr class="bellContentRow">
								<td class="bellContentLabelCell" width="30%"><fmt:message
									key="flink.label.title" /></td>
								<td class="bellContentDataCell"><input class="inputText"
									type="text" name="title" id="title"
									value="<c:out value="${formInfo.title}"/>" size="50"></td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="flink.label.link" /></td>
								<td class="bellContentDataCell">
									<input class="inputText" type="text" name="link" id="link"
										value="<c:out value="${formInfo.link}"/>" size="50">
								 http://</td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="flink.label.category" /></td>
								<td class="bellContentDataCell"><select id="category"
									name="category">
									<c:forEach items="${dictList}" var="dict">
										<option value="<c:out value="${dict.recordId}"/>"
											<c:if test="${formInfo.category == dict.recordIdString}"> selected</c:if>><c:out
											value="${dict.dictItem}" /></option>
									</c:forEach>
								</select></td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="flink.label.orderNo" /></td>
								<td class="bellContentDataCell">
									<input class="inputText" type="text" name="inorder" id="inorder"
										value="<c:out value="${formInfo.inorder}"/>"></td>
							</tr>
							<tr class="bellBottomRow">
								<td colspan="2" class="bellBottomCell"><input type="submit"
									name="Submit" value="" class="button_save">&nbsp;&nbsp;&nbsp;&nbsp;<input
									value="" type="button"
									onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"
									class="button_return"></td>
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
