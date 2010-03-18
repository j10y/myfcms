<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<HTML>
<HEAD>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css">
</HEAD>
<BODY class="bellBody">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="12" class="topCell"></td>
	</tr>
	<tr>
		<td height="600" valign="top" class="topCell">
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
				<form method="post" action="<c:url value="/personEdit.do"/>">
				<input type="hidden" name="id"
					value="<c:out value="${formInfo.id}"/>"> <input
					type="hidden" name="editFlag"
					value="<c:out value="${formInfo.editFlag}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"> <br>
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
									key="person.label.personManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">
							<tr class="bellContentRow">
								<td class="bellContentLabelCell" width="30%"><fmt:message
									key="person.label.code" /></td>
								<td class="bellContentDataCell"><input
									class="inputText"
									type="<c:if test="${formInfo.editFlag == 'add'}">text</c:if><c:if test="${formInfo.editFlag == 'modify'}">hidden</c:if>"
									name="code" id="code" value="<c:out value="${formInfo.code}"/>"><c:if
									test="${formInfo.editFlag == 'modify'}">
									<c:out value="${formInfo.code}" />
								</c:if></td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="person.label.name" /></td>
								<td class="bellContentDataCell"><input
									class="inputText" type="text" name="name" id="name"
									value="<c:out value="${formInfo.name}"/>"></td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="person.label.password" /></td>
								<td class="bellContentDataCell"><input
									class="inputText" type="password" name="password" id="password"
									value="<c:out value="${formInfo.password}"/>" maxlength="20"><fmt:message
									key="person.label.passwordFormat" /></td>
							</tr>
							<tr class="bellContentRow">
								<td class="bellContentLabelCell"><fmt:message
									key="person.label.passwordrRepeat" /></td>
								<td class="bellContentDataCell"><input
									class="inputText" type="password" name="passwordRepeat"
									id="passwordRepeat"
									value="<c:out value="${formInfo.passwordRepeat}"/>"
									maxlength="20"></td>
							</tr>
                            <tr class="bellContentRow">
                              <td class="bellContentLabelCell"><fmt:message
                               key="person.label.dept" />
                               </td>
                               <td class="bellContentDataCell">
                               <select id="orgId" name="orgId" class="inputText">
                                 <option value=""><fmt:message key="public.msg.pleaseSelect"/></option>
                                 <c:forEach items="${organization}" var="orgs">        
                                  <option value="<c:out value="${orgs.orgId}"></c:out>"
                                   <c:if test="${formInfo.orgId == orgs.orgIdStr}">selected</c:if>><c:out
                                   value="${orgs.orgName}"></c:out></option>
                                 </c:forEach>
                                 
                                 </select></td>
                            </tr>
							<tr class="bellBottomRow">
								<td class="bellBottomCell" colspan="2"><input
									type="submit" name="Submit" class="button_save" value="">&nbsp;&nbsp;<input
									 class="button_return" type="button"
									onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"></td>
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
