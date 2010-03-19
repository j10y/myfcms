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
				<c:if test="${formInfo.childsiteId == 1}">
				<form name="form1" method="post" action="<c:url value="/treeEdit.do"/>"
					enctype="multipart/form-data">
				</c:if>
				<c:if test="${formInfo.childsiteId != 1}">
				<form name="form1" method="post" action="<c:url value="/treeChildEdit.do"/>"
					enctype="multipart/form-data">
				</c:if><input type="hidden"
					name="category" id="category"
					value="<c:out value="${formInfo.category}"/>"> <input
					type="hidden" name="itemId" id="itemId"
					value="<c:out value="${formInfo.itemId}"/>"> <input
					type="hidden" name="isBrother" id="isBrother"
					value="<c:out value="${formInfo.isBrother}"/>"> <input
					type="hidden" name="referenceNodeId" id="referenceNodeId"
					value="<c:out value="${formInfo.referenceNodeId}"/>"> <input
					type="hidden" name="editFlag" id="editFlag"
					value="<c:out value="${formInfo.editFlag}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"><input
					type="hidden" name="childsiteId"
					value="<c:out value="${formInfo.childsiteId}"/>"><spring:bind
					path="formInfo.*">
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
								<td class="bellTitleCell"><c:if test="${formInfo.childsiteId != 1}"><fmt:message
														key="infoPublish.label.orgCategoryOne" /></c:if><fmt:message
									key="tree.label.treeManagement" /></td>
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
						<td class="bellContentDataCell"><input class="inputText"
							type="text" name="itemText"
							value="<c:out value="${formInfo.itemText}"/>"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="tree.label.isNav" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							type="radio" name="isNav" id="isNav" value="1"
							<c:if test="${formInfo.isNav == 1}">checked</c:if>><fmt:message
							key="public.select.yes" /> <input class="inputText" type="radio"
							name="isNav" id="isNav" value="0"
							<c:if test="${formInfo.isNav == 0}">checked</c:if>
							<c:if test="${formInfo.editFlag == 'add'}">checked</c:if>><fmt:message
							key="public.select.no" /></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="tree.label.isLink" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							type="radio" name="isLink" id="isLink" value="1"
							<c:if test="${formInfo.isLink == 1}">checked</c:if>
							onClick="mem.style.display='';"><fmt:message
							key="public.select.yes" /> <input class="inputText" type="radio"
							name="isLink" id="isLink" value="0"
							<c:if test="${formInfo.isLink != 1 || formInfo.editFlag == 'add'}">checked</c:if>
							onClick="mem.style.display='none';"><fmt:message
							key="public.select.no" />
						<div id="mem" style="display:'';"><input name="memo"
							id="memo" value="<c:out value="${formInfo.memo}"/>" size="40"
							class="inputText"></div>
						</td>
					</tr>
				</table>
				<c:if test="${formInfo.childsiteId == 1}">
				<div id="d1"<c:if test="${formInfo.category == 'subjectChannel'}"> style="display:none"</c:if>>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="tree.label.isIndexNav" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							type="radio" name="isIndexnav" id="isIndexnav" value="1"
							<c:if test="${formInfo.isIndexnav == 1}">checked</c:if>
							onClick="loc.style.display='';"><fmt:message
							key="public.select.yes" /> <input class="inputText" type="radio"
							name="isIndexnav" id="isIndexnav" value="0"
							<c:if test="${formInfo.isIndexnav == 0}">checked</c:if>
							<c:if test="${formInfo.editFlag == 'add'}">checked</c:if>
							onClick="loc.style.display='none';"><fmt:message
							key="public.select.no" />
						<div id="loc" style="display:'';"><select name="loca"
							id="loca">
							<option value="1"
								<c:if test="${formInfo.loca=='1'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />1</option>
							<option value="2"
								<c:if test="${formInfo.loca=='2'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />2</option>
							<option value="3"
								<c:if test="${formInfo.loca=='3'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />3</option>
							<option value="4"
								<c:if test="${formInfo.loca=='4'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />4</option>
							<option value="5"
								<c:if test="${formInfo.loca=='5'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />5</option>
							<option value="6"
								<c:if test="${formInfo.loca=='6'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />6</option>
							<option value="7"
								<c:if test="${formInfo.loca=='7'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />7</option>
							<option value="8"
								<c:if test="${formInfo.loca=='8'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />8</option>
							<option value="9"
								<c:if test="${formInfo.loca=='9'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />9</option>
							<option value="10"
								<c:if test="${formInfo.loca=='10'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />10</option>
							<option value="11"
								<c:if test="${formInfo.loca=='11'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />11</option>
							<option value="12"
								<c:if test="${formInfo.loca=='12'}"> selected</c:if>><fmt:message
								key="tree.label.loca" />12</option>
						</select></div>
						</td>
					</tr>
				</table>
				</div>
				<div id="d2"<c:if test="${formInfo.category == 'newsChannel'}"> style="display:none"</c:if>>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="tree.label.isSitelist" /></td>
						<td class="bellContentDataCell"><input class="inputText"
							type="radio" name="isSitelist" id="isSitelist" value="1"
							<c:if test="${formInfo.isSitelist == 1}">checked</c:if>><fmt:message
							key="public.select.yes" /> <input class="inputText" type="radio"
							name="isSitelist" id="isSitelist" value="0"
							<c:if test="${formInfo.isSitelist == 0}">checked</c:if>
							<c:if test="${formInfo.editFlag == 'add'}">checked</c:if>><fmt:message
							key="public.select.no" /></td>
					</tr>
					<tr class="bellContentRow">
						<td width="20%" class="bellContentLabelCell"><fmt:message
							key="tree.label.imgPath" /></td>
						<td class="bellContentDataCell"><input type="file"
							name="upLoadFile" id="upLoadFile"
							value="<c:out value="${formInfo.upLoadFile}"/>"><c:if
							test="${formInfo.imgPath != null && formInfo.imgPath != ''}">
							<br>
							<img src="<c:url value="${formInfo.imgPath}"/>" height="100">
						</c:if></td>
					</tr>
				</table>
				</div>
				</c:if>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2"><input
							class="button_save" type="submit" name="Submit" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"
							class="button_return" type="button"
							name="<fmt:message key="public.label.return"/>" value=""></td>
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
	<c:if test="${formInfo.isLink == 0 || formInfo.isLink == null}">
	mem.style.display='none';
	</c:if>
	<c:if test="${formInfo.isLink == 1}">
	mem.style.display='';
	</c:if>	
	<c:if test="${formInfo.isIndexnav == 0 || formInfo.isIndexnav == null}">
	loc.style.display='none';
	</c:if>
	<c:if test="${formInfo.isIndexnav == 1}">
	loc.style.display='';
	</c:if>	
</script>
</BODY>
</HTML>
