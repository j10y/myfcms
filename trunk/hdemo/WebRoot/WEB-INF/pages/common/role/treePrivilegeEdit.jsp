<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../calendar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
<%@ include file="../appStaticTreeSetup.jsp"%>
<script type="text/javascript"
	src="scripts/tree/appStaticPrivilegeCheckBoxTreeItem.js"></script>
<script language="javascript"> 
//代码说明(lulu163.com)：form1为表单名，memberId为复选框，selectbutton为全选按钮 
   function selectAll()  
   { 
    for (var i=0;i<document.form1.elements.length;i++) { 
    var temp=document.form1.elements[i]; 
    temp.checked=true; 
   } 
   }
   function conSelect()  
   { 
    for (var i=0;i<document.form1.elements.length;i++) { 
    var temp=document.form1.elements[i]; 
    temp.checked=false; 
   } 
   }
<!--
//根据序列选择项目;
function selectByIndex(IndexArr){
var ckArr = new Array();
ckArr = IndexArr.split(',');
var eleSet = document.form1.privileges;
for(var i = 0;i < eleSet.length;i++){
		for(var j = 0;j < ckArr.length;j++)
		{
			if(i == ckArr[j])
			{
				eleSet[i].checked=eleSet[i].checked==true?false:true;
			}
		}
	}
}
-->
</script>
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
				<form method="post" action="<c:url value="/treePriviEdit.do"/>"
					name="form1"><input type="hidden" name="roleId"
					value="<c:out value="${formInfo.roleId}"/>"> <input
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
								<td class="bellTitleCell"><fmt:message
									key="role.label.privilegeGrant" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="role.label.roleName" /></td>
						<td class="bellContentDataCell" width="70%"><c:out
							value="${role.roleName}" /></td>
					</tr>
					<!-- <tr class="bellContentRow">
						<td class="bellContentLabelCell" width="50%"><fmt:message
							key="tree.label.category" /></td>
						<td class="bellContentDataCell" width="50%"><select
							name="category1" id="category1"
							onchange="MM_jumpMenu('self',this,0)" size=1
							class="inputSelectMenu">
							<option value="<c:url value="/tree.do"/>"><fmt:message
								key="public.msg.pleaseSelect" /></option>
							<c:forEach items="${treeCategoryList}" var="trees">
								<option
									value="<c:url value="/treePriviEdit.do?roleId=${role.roleId}&getcategory=${trees.category}&returnUrl=${formInfo.returnUrl}"/>"
									<c:if test="${trees.category == getcategory}">selected</c:if>><c:out
									value="${trees.itemText}" /></option>
							</c:forEach>
						</select></td>
					</tr> -->
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2"><input
							class="button_selall" type="button" name="selectButton"
							onClick="selectAll()">&nbsp;&nbsp;&nbsp;&nbsp; <input
							class="button_nosel" type="button" name="conSelectButton"
							onClick="conSelect()">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"
							name="Submit" value="" class="button_save">&nbsp;&nbsp;&nbsp;&nbsp;<input
							value="" type="button" class="button_return"
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"></td>
					</tr>
					<tr class="bellContentRow">
						<td colspan="2" class="bellContentDataCell">
						<table width="550" border="0" cellspacing="0" align="center">
							<%int j=0;%>
							<c:forEach items="${treeList}" var="tree" varStatus="status">
								<tr height="25">
									<td><c:out value="${tree.itemText}" /></td>
									<td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdAddStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdAddStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.add" />
									</c:if></td>
									<td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdEditStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdEditStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.modify" />
									</c:if></td>
									<td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdDelStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdDelStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.delete" />
									</c:if></td>
									<td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdAudStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdAudStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.audit" />
									</c:if></td>

									<!-- <td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdTopStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdTopStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.sort" />
									</c:if></td>
									<td><c:if test="${tree.clientNodeInfo == '1' && tree.isOpen == '0'}">
										<input type="checkbox" name="privileges"
											value="<c:out value="${tree.itemIdOpenStr}"/>"
											<c:if test="${formInfo.privilege[tree.itemIdOpenStr] != null}"> checked</c:if>>
										<fmt:message key="public.label.open" />
									</c:if></td> -->
									<td><c:if test="${tree.clientNodeInfo == '1'}">
										<input type="checkbox" onclick="javascript:selectByIndex('<%=j++%>,<%=j++%>,<%=j++%>,<%=j++%>')">
										</a>
									</c:if></td>
								</tr>
							</c:forEach>
						</table>
						</td>
					</tr>
					<tr class="bellBottomRow">
						<td colspan="2" class="bellBottomCell"><input
							class="button_selall" type="button" name="selectButton"
							onClick="selectAll()">&nbsp;&nbsp;&nbsp;&nbsp; <input
							class="button_nosel" type="button" name="conSelectButton"
							onClick="conSelect()">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"
							name="Submit" value="" class="button_save">&nbsp;&nbsp;&nbsp;&nbsp;<input
							value="" type="button" class="button_return"
							onclick="javascript:window.location='<c:url value="${formInfo.returnUrl}"/>';"></td>
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
