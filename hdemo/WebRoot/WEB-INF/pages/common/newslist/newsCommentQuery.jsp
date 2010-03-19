<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../calendar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
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
				<form method="post" action="<c:url value="/newsCommentQuery.do"/>" name="queryForm" id="queryForm">
				<input type="hidden" name="pageNo" id="pageNo" value="<c:out value="${formInfo.pageNo}"/>">
				<input type="hidden" name="recPerPage" id="recPerPage" value="<c:out value="${formInfo.recPerPage}"/>">
				<input type="hidden" name="returnUrl" id="returnUrl" value="<c:out value="${param.returnUrl}"/>">
				<input type="hidden" name="infoIds" id="infoIds">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="95"><img src="images/eis_p_b3.png" width="95"
							height="50" /></td>
						<td valign="bottom" background="images/eis_p_b4.png">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="newscomment.label.commentManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
      			<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
			          <td class="queryListTitleCell" width="8%"><input id="selall" onclick="var obj=document.getElementsByName('id');for(var i=0;i<obj.length;i++)obj[i].checked=this.checked" type="checkbox" value="true" name="selall"></td>
			          <td class="queryListTitleCell"><fmt:message key="infoPublish.label.title"/></td>
			        </tr>
					<c:forEach items="${commentList}" var="comment" varStatus="status">
			        <tr class="queryListRow"> 
			          <td <c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
						<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><input type="checkbox" value="<c:out value="${comment.id}"/>" id="id" name="id"></td>
			          <td <c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
						<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><table width="98%"><tr><td><fmt:message key="newscomment.label.commentName" /><c:out value="${comment.username}"/>&nbsp;&nbsp;|&nbsp;&nbsp;<fmt:message
								key="newscomment.label.commentTime" /><fmt:formatDate value="${comment.dater}" pattern="${dateFormatShort}"/><br><br><app:out value="${comment.content}"/></td></tr></table></td>
			        </tr>
					</c:forEach>
					</table>
					<table class="queryMiddleTable" width="95%" border="0" align="center" cellpadding="0" cellspacing="1">
					<tr><td><!--input id="selall" onclick="var obj=document.getElementsByName('id');for(var i=0;i<obj.length;i++)obj[i].checked=this.checked" type="checkbox" value="true" name="selall"><fmt:message key="public.label.selAllorNoSel"/>-->
					&nbsp;<input class="button_delete" type="button" name="delAll" value="" onclick="delId()">&nbsp;&nbsp;<input
									 class="button_return" type="button"
									onclick="javascript:window.location='<c:url value="${param.returnUrl}"/>';">
					</td></tr>
			        </table>
      				<table class="queryBottomTable" width="97%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${count}"
							recPerPage="${formInfo.recPerPage}" queryFormId="queryForm"
							queryFormPageNoId="pageNo" queryFormRecPerPageId="recPerPage" />
						</td>
					</tr>
				</table>
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
	function delId()
    {
      infoIds = "";
	      var obj=document.getElementsByName('id');
	      for (var i = 0 ; i < obj.length; i++)
	      {
		      if(obj[i].checked) {
		        infoIds = infoIds + obj[i].value + ",";
		      }
	      }
	      if (infoIds == "") {
			tip = "请至少选择一条信息！";
			alert(tip);
			return false;
		  }
		  else {
		  	dataDeleteConfirm('<c:url value="/newsCommentDelete.do?ids='+infoIds+'&isItemid=${formInfo.isItemid}&returnUrl=${requestUrl}"/>');}
    } 
</script>
</form>
</BODY>
</HTML>