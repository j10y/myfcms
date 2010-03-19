<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
<%@ include file="../appStaticTreeSetup.jsp"%>
<script type="text/javascript"
	src="scripts/tree/appStaticPrivilegeCheckBoxTreeItem.js"></script>
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
				<form method="post" action="<c:url value="/rolePrivilegeEdit.do"/>">
				<input type="hidden" name="roleId"
					value="<c:out value="${formInfo.roleId}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>">
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
									key="role.label.privilegeGrant" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="role.label.roleName" /></td>
						<td class="bellContentDataCell" width="70%"><c:out
							value="${role.roleName}" /></td>
					</tr>
					<tr class="bellContentRow">
						<td colspan="2" class="bellContentDataCell">
						<table width="400" align="center">
							<tr>
								<td><script type="text/javascript">
					              var tree = new appTree("<fmt:message key="role.label.privilege"/>","");
					              var treeItems = new Array;
					              <c:forEach items="${privilegeList}" var="privilege" varStatus="status">
					                 treeItems[<c:out value="${status.count - 1}"/>] = new appStaticPrivilegeCheckBoxTreeItem("<c:out value="${privilege.privName}"/>", "privileges", "<c:out value="${privilege.id}"/>", "<c:out value="${privilege.parent.id}"/>", "<c:if test="${formInfo.privilege[privilege.id] == null}">0</c:if><c:if test="${formInfo.privilege[privilege.id] != null}">1</c:if>");
					              </c:forEach>
					               genTree(tree, treeItems);
					               document.write(tree);
					                tree.expandAll();
					           </script></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr class="bellBottomRow">
						<td colspan="2" class="bellBottomCell"><input type="submit"
							name="Submit" value="" class="button_save">&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="button_return"
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';">
						</td>
					</tr>					
				</table>
				</form>
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
