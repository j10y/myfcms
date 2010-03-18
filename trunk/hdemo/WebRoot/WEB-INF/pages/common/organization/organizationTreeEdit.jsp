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
<script type="text/javascript" src="scripts/pubjs/pubjs.js"></script>
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
				<form method="post"
					action="<c:url value="/organizationTreeEdit.do"/>" name="queryForm"
					id="queryForm" onsubmit="return gosubmit();"><input
					type="hidden" name="orgId"
					value="<c:out value="${formInfo.orgId}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"> <spring:bind
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
								<td class="bellTitleCell" style="text-align: left;">授予权限</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="0"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="10%" height="20"></td>
						<td class="bellContentLabelCell" width="80%"
							style="text-align: center;">
						<h4>授予“<c:out value="${organization.orgName}" />”管理的栏目</h4>
						</td>
						<td class="bellContentLabelCell" width="10%"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"></td>
						<td class="bellContentLabelCell">
						<table align="center" width="100%" border="0" cellpadding="5"
							cellspacing="1" class="bellContentTable">
							<tr>
								<td width="40%" height="250" class="bellContentDataCell">
								<center><SELECT class="" id="treeDictId"
									style="WIDTH: 200px;" multiple size="12" name="treeDictId">
									<c:forEach items="${treeList}" var="tree">
										<option value="<c:out value="${tree.itemId}"></c:out>"><c:out
											value="${tree.itemText}"></c:out></option>
									</c:forEach>
								</SELECT></center>
								</td>
								<td width="10%" class="bellContentDataCell" align="center">
								<input type="button" class="button_selectAll" value=""
									onClick="JavaScript:AddAllItem('treeDictId', 'orgTreeId')" /> <br>
								<br>
								<input type="button" class="button_add1" value=""
									onClick="JavaScript:AddItem('treeDictId','orgTreeId', '')" /></td>
								<td width="10%" class="bellContentDataCell" align="center">
								<input type="button" class="button_clearAll" value=""
									onClick="JavaScript:DeleteAllItem('orgTreeId', '')" /> <br>
								<br>
								<input type="button" class="button_delete1" value=""
									onClick="JavaScript:DeleteItem('orgTreeId')" /></td>
								<td width="40%" class="bellContentDataCell">
								<center><SELECT class="" id="orgTreeId"
									style="WIDTH: 200px;" multiple size="12" name="orgTreeId">
									<c:forEach items="${orgTreeList}" var="orgTree">
										<option value="<c:out value="${orgTree.itemId}"/>"><c:out
											value="${orgTree.itemText}" /></option>
									</c:forEach>
								</SELECT></center>
								</td>
							</tr>
						</table>
						</td>
						<td class="bellContentLabelCell"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" height="20"></td>
						<td class="bellContentLabelCell"></td>
						<td class="bellContentLabelCell"></td>
					</tr>
					<tr class="bellBottomRow">
						<td colspan="3" class="bellBottomCell"><input type="submit"
							name="Submit" class="button_save" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
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
<script>
  function goResume() {
      gosubmit();
      document.queryForm.action += "?re=1";
      document.queryForm.submit();
  }

  function gosubmit() {
	for (var i=0; i<document.queryForm.orgTreeId.length; i++)
		document.queryForm.orgTreeId[i].selected = true;
  }

	
</script>
</BODY>
</HTML>
