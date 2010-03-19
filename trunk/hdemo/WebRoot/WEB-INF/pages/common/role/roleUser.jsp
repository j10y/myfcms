<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">

<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>

<%@ include file="../../common/appStaticTreeSetup.jsp"%>
<script type="text/javascript"
	src="scripts/tree/appStaticPrivilegeCheckBoxTreeItem.js"></script>
<script type="text/javascript" src="scripts/pubjs/pubjs.js"></script>

<SCRIPT language="javascript" src="../scripts/dwr/dwrs.js"></SCRIPT>
<SCRIPT language="javascript" src="../scripts/dwr/json2.js"></SCRIPT>
<SCRIPT language="javascript" src="../scripts/dwr/popupWin.js"></SCRIPT>
<SCRIPT language="javascript" src="../scripts/dwr/prototype.js"></SCRIPT>
<SCRIPT language="javascript" src="../dwr/interface/roleUserManagerDWR.js"></SCRIPT>
<SCRIPT language="javascript" src="../dwr/engine.js"></SCRIPT>
<SCRIPT language="javascript" src="../dwr/util.js"></SCRIPT>
<SCRIPT language="javascript" src="../scripts/pubjs/lilo.js"></SCRIPT>
<script>
	function changeUsers() {
		var orgId = document.getElementById("selectUser").options[document.getElementById("selectUser").selectedIndex].id;
		roleUserManagerDWR.findUserOption(orgId,updateUser);
	}

    function updateUser(outstr) {
    	document.getElementById("selectUser").innerHTML = outstr;
    }

</script>
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
				<form  method="post"  action="<c:url value="/roleUser.do"/>" name="queryForm" id="queryForm" onsubmit="return gosubmit();">
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
								<td class="bellTitleCell" style="text-align: left;">授予权限</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="0" class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="10%" height="20"></td>
						<td class="bellContentLabelCell" width="80%" style="text-align: center;"><h4>“<c:out value="${role.roleName}"/>”授予用户</h4></td>
						<td class="bellContentLabelCell" width="10%"></td>
					</tr>
					<!--tr class="bellContentRow">
						<td class="bellContentLabelCell" width="10%" height="20"></td>
						<td class="bellContentLabelCell" width="80%" style="text-align: center;">用户分类
						  <select id="selectUser" onchange="changeUsers()">
						  	<option value=""></option>
				  	      	<c:forEach items="${orgList}" var="org" varStatus="status">
						      <option value="<c:out value="${org.orgId}"/>"><c:out value="${org.orgName}"/></option>
					      	</c:forEach>
						  </select>
						</td>
						<td class="bellContentLabelCell" width="10%"></td>
					</tr-->
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"></td>
						<td class="bellContentLabelCell">
						<table align="center" width="100%"  border="0" cellpadding="5" cellspacing="1" class="bellContentTable">
							<tr>
								<td width="40%" height="250" class="bellContentDataCell" >
								  <center>
									<SELECT class="" id="personId" 
                        				style="WIDTH: 200px;" multiple size="12" name="personId">
										<c:forEach items="${userList}" var="user">
											<option value="<c:out value="${user.id}"></c:out>"><c:out value="${user.name}"></c:out></option>
										</c:forEach>
                        			</SELECT>
                        		  </center>
								</td>
								<td width="10%" class="bellContentDataCell" align="center">
									<input type="button" class="button_selectAll" value=""  onClick="JavaScript:AddAllItemOther('personId', 'userId')"/>
										<br><br><input type="button" class="button_add1" value=""  onClick="JavaScript:AddItemOther('personId','userId')"/>
								</td>
								<td width="10%" class="bellContentDataCell" align="center">
								  <input type="button" class="button_clearAll" value=""  onClick="JavaScript:AddAllItemOther('userId', 'personId')"/>
									<br><br><input type="button" class="button_delete1" value=""  onClick="JavaScript:AddItemOther('userId','personId')"/>									
								</td>
								<td width="40%" class="bellContentDataCell">
								  <center>
									<SELECT class="" id="userId" 
                        				style="WIDTH: 200px;" multiple size="12" name="userId">
										<c:forEach items="${roleUserList}" var="roleUser">
											<option value="<c:out value="${roleUser.id}"></c:out>"><c:out value="${roleUser.name}"></c:out></option>
										</c:forEach>
                        			</SELECT>
                        		  </center>
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
							name="Submit"  class="button_save" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
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
  function gosubmit() {
	for (var i=0; i<document.queryForm.userId.length; i++)
		document.queryForm.userId[i].selected = true;
  }
</script>
</BODY>
</HTML>
