<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../calendar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="mainFrame.title" /></title>
<meta HTTP-EQUIV="Content-Type" content="text/html; charset=gb2312"/>
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
</head>
<body class="bellBody">
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
				<c:if test="${formInfo.childsiteId == 1}">
				<form name="form1" method="post" action="<c:url value="/treeEdit.do"/>"
					enctype="multipart/form-data">
				</c:if>
				<c:if test="${formInfo.childsiteId != 1}">
				<form name="form1" method="post" action="<c:url value="/treeChildEdit.do"/>"
					enctype="multipart/form-data">
				</c:if>
				<input type="hidden" name="category" id="category" value="<c:out value="${formInfo.category}"/>" /> 
				<input type="hidden" name="itemId" id="itemId" value="<c:out value="${formInfo.itemId}"/>"/> 
				<input type="hidden" name="isBrother" id="isBrother" value="<c:out value="${formInfo.isBrother}"/>"/> 
				<input type="hidden" name="referenceNodeId" id="referenceNodeId" value="<c:out value="${formInfo.referenceNodeId}"/>"/> 
				<input type="hidden" name="editFlag" id="editFlag" value="<c:out value="${formInfo.editFlag}"/>"/> 
				<input type="hidden" name="returnUrl" value="<c:out value="${formInfo.returnUrl}"/>"/>
				<spring:bind path="formInfo.*">
					<c:if test="${status.error}">
						<table width="548" border="0" align="center" cellpadding="0"
							cellspacing="1" class="billMsgTable">
							<tr class="billMsgRow">
								<td class="billMsgCell"><c:forEach
									items="${status.errorMessages}" var="errorMessage">
									<c:out value="${errorMessage}" />
									<br />
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
								<td class="bellTitleCell">
								<fmt:message key="tree.label.treeManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="tree.label.showText" /></td>
						<td class="bellContentDataCell">
						<input class="inputText" type="text" name="itemText" value="<c:out value="${formInfo.itemText}"/>"></input></td>
					</tr>					
					</tr>
				</table>
				
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2">
						<input	class="button_save" type="submit" name="Submit" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input	onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"
							class="button_return" type="button" name="<fmt:message key="public.label.return"/>" value=""/></td>
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
</body>
</html>
