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
				<form method="post" action="<c:url value="/newsListQuery.do"/>"
					name="queryForm" id="queryForm"><input type="hidden"
					name="pageNo" id="pageNo"
					value="<c:out value="${formInfo.pageNo}"/>"> <input
					type="hidden" name="recPerPage" id="recPerPage"
					value="<c:out value="${formInfo.recPerPage}"/>"> <input
					type="hidden" name="returnUrl" id="returnUrl"
					value="<c:out value="${param.returnUrl}"/>"> <input
					type="hidden" name="isPass" id="isPass" value="<c:out value="${formInfo.isPass}"/>"><input
					type="hidden" name="isIssued" id="isIssued" value="<c:out value="${formInfo.isIssued}"/>"><input
					type="hidden" name="infoIds" id="infoIds">
				<table width="97%" border="0" cellspacing="0" cellpadding="0"
					class="queryConditionTable">
					<tr class="queryConditionTitleRow">
						<td width="95"><img src="images/eis_p_b1.png" width="95"
							height="50" /></td>
						<td valign="bottom">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="public.label.queryCondition" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryConditionTable">
					<tr>
						<td class="queryConditionLabelCell" width="20%"><fmt:message
							key="infoPublish.label.title" /></td>
						<td class="queryConditionDataCell" width="15%"><input
							class="inputText" type="text" name="title" id="title"
							value="<c:out value="${formInfo.title}"/>"></td>
						<td class="queryConditionLabelCell" width="10%"><fmt:message
							key="public.label.beginDate" /></td>
						<td class="queryConditionDataCell" width="10%"><input
							class="inputText" type="text" name="beginDate" id="beginDate"
							value="<c:out value="${formInfo.beginDate}"/>" size="10"></td>
						<td class="queryConditionLabelCell" width="10%"><fmt:message
							key="public.label.endDate" /></td>
						<td class="queryConditionDataCell" width="45%"><input
							class="inputText" type="text" name="endDate" id="endDate"
							value="<c:out value="${formInfo.endDate}"/>" size="10"></td>
					</tr>
					<tr>
						<td class="queryConditionBottomRow" colspan="6"><input
							type="button" name="Submit" class="button_query" value=""
							onclick="javascript:queryFormSubmit(1);"></td>
					</tr>
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td class="queryMiddleCell" width="60%"></td>
						<td class="queryMiddleCell" align="right" width="18%"><fmt:message
							key="infoPublish.label.selectItemId" /></td>
						<td class="queryMiddleCell" align="left" width="12%">
						<select	id="isItemid" name="isItemid" onchange="javascript:queryFormSubmit(1);">
							<option value=""></option>
							<c:forEach items="${treeList}" var="tree">
								<option value="<c:out value="${tree.itemId}"/>"
									<c:if test="${formInfo.isItemid == tree.itemId}">selected</c:if>><c:out
									value="${tree.itemText}" /></option>
							</c:forEach>
						</select></td>
						<td class="queryMiddleCell" width="10%">
						<c:if test="${priviMap['Add']!=null}"></c:if>
						<input type="button"  class="button_add" onclick="createCondition();">
						
						</td>
					</tr>
				</table>

				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="95"><img src="images/eis_p_b3.png" width="95"
							height="50" /></td>
						<td valign="bottom" background="images/eis_p_b4.png">
						<table width="100%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="infoPublish.label.infoPublishManagement" /></td>
								<td class="queryConditionTitleCell" width="30%"></td>
								<td class="queryConditionTitleCell" width="62" valign="bottom"><c:if
									test="${formInfo.imgCategory == '1' || formInfo.imgCategory == null}"><img src="images/common/1_on.png"></c:if><c:if test="${formInfo.imgCategory != '1' && formInfo.imgCategory != null}"><a
										href="<c:url value="/newsListQuery.do?isItemid=${formInfo.isItemid}"/>"><img
										src="images/common/1_off.png" border="0"/></a></c:if></td>
								<td class="queryConditionTitleCell" width="5"></td>
								<td class="queryConditionTitleCell" width="62" valign="bottom"><c:if test="${formInfo.imgCategory != '2'}"><a href="<c:url value="/newsListQuery.do?isPass=0&isIssued=0&isItemid=${formInfo.isItemid}"/>"><img
										src="images/common/2_off.png" border="0" valign="bottom"/></a></c:if><c:if test="${formInfo.imgCategory == '2'}"><img src="images/common/2_on.png" border="0" valign="bottom"></c:if></td>
								<td class="queryConditionTitleCell" width="5"></td>
								<td class="queryConditionTitleCell" width="62" valign="bottom"><c:if test="${formInfo.imgCategory == '3'}"><img src="images/common/3_on.png" border="0"></c:if><c:if test="${formInfo.imgCategory != '3' }"><a href="<c:url value="/newsListQuery.do?isPass=1&isIssued=0&isItemid=${formInfo.isItemid}"/>"><img src="images/common/3_off.png" border="0"></a></c:if></td>
								<td class="queryConditionTitleCell" width="5"></td>
								<td class="queryConditionTitleCell" width="62" valign="bottom"><c:if test="${formInfo.imgCategory != '4' }"><a href="<c:url value="/newsListQuery.do?isPass=1&isIssued=1&isItemid=${formInfo.isItemid}"/>"><img
										src="images/common/4_off.png" border="0"/></a></c:if><c:if test="${formInfo.imgCategory == '4'}"><img src="images/common/4_on.png" border="0"></c:if></td>
								<td class="queryConditionTitleCell" width="10"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>

				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="queryListTable">
					<tr class="queryListTitleRow">
						<td class="queryListTitleCell" width="8%"><input id="selall"
							onclick="var obj=document.getElementsByName('infoId');for(var i=0;i<obj.length;
							i++)obj[i].checked=this.checked " type="checkbox" value="true"
							name="selall"></td>
						<td class="queryListTitleCell"><fmt:message
							key="infoPublish.label.title" /></td>
						<td class="queryListTitleCell" width="16%"><fmt:message
							key="infoPublish.label.timePublish" /></td>
						<td class="queryListTitleCell" width="10%"><fmt:message
							key="infoPublish.label.status" /></td>
						<td class="queryListTitleCell" width="10%">&nbsp;</td>
						<td class="queryListTitleCell" width="10%">&nbsp;</td>
					</tr>
					<c:forEach items="${infoPublishList}" var="infoPublish">
						<tr class="queryListRow">
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><input
								type="checkbox" value="<c:out value="${infoPublish.infoId}"/>"
								id="infoId" name="infoId"></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if
								test="${infoPublish.isDoc != 1}">
								<a
									href="<c:url value="/newsListView.do?infoId=${infoPublish.infoId}&itemText=${itemText}&category=${category}&returnUrl=${requestUrl}"/>"><c:out
									value="${infoPublish.title}" /></a>
							</c:if> <c:if test="${infoPublish.isDoc == 1}">
								<a href="<c:url value="${infoPublish.contentPath}"/>"
									target="_blank"><c:out value="${infoPublish.title}" /></a>
							</c:if></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><fmt:formatDate
								value="${infoPublish.timePublish}" pattern="${dateFormatShort}" /></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if
								test="${infoPublish.isPass==1 && infoPublish.isIssued==0}">
								<fmt:message key="infoPublish.label.audited" />
							</c:if><c:if test="${infoPublish.isPass==1 && infoPublish.isIssued==1}">
								<fmt:message key="infoPublish.label.issued" />
							</c:if></td>
							<td
								<c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><c:if
								test="${infoPublish.isDoc != 1}">
								<a
									href="<c:url value="/newsListEdit.do?editFlag=modify&infoId=${infoPublish.infoId}&isItemid=${formInfo.isItemid}&returnUrl=${requestUrl}"/>"><fmt:message
									key="public.label.modify" /></a>
							</c:if></td>
							<td <c:if test="${status.count%2 == 0}">class="queryListCell"</c:if>
								<c:if test="${status.count%2 != 0}">class="queryListCell_two"</c:if>><a
								href="<c:url value="/newsCommentQuery.do?infoId=${infoPublish.infoId}&returnUrl=${requestUrl}"/>"><fmt:message
								key="infoPublish.label.comment" /></a></td>
						</tr>
					</c:forEach>
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr>
						<td colspan="4">&nbsp;
						<c:if test="${priviMap['Del']!=null}">
						<input class="button_delete" type="button" name="delAll" onclick="delId()"> &nbsp;
						</c:if>
						<c:if test="${formInfo.isPass == null && formInfo.isIssued == null}">
						<input class="button_sort" type="button" name="sortAll" onclick="sortId()"> &nbsp;
						</c:if>
						<c:if test="${priviMap['Aud']!=null}">
						<input class="button_pass" type="button_pass" name="delAll" onclick="auditId(1)"> &nbsp;
						</c:if>
						<c:if test="${priviMap['Aud']!=null}">
						<input class="button_noPass" type="button" name="delAll" onclick="auditId(0)"> &nbsp;
						<input class="button_issued" type="button" name="delAll" onclick="issuedId(1)"></c:if>
						<c:if test="${formInfo.isPass ==1 && formInfo.isIssued == 1 }">
						<input class="button_noIssued" type="button" name="delAll" onclick="issuedId(0)">
						</c:if>
						</td>
					</tr>
				</table>
				<table class="queryBottomTable" width="97%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%"><app:toPage
							pageNo="${formInfo.pageNo}" totalRec="${infoPublishCount}"
							recPerPage="${formInfo.recPerPage}" queryFormId="queryForm"
							queryFormPageNoId="pageNo" queryFormRecPerPageId="recPerPage" />
						</td>
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
	function createCondition() {		
	    if (document.getElementById("isItemid").value != null && document.getElementById("isItemid").value != ''){
	        strUrl = "<c:url value="/newsListEdit.do?editFlag=add&isItemid="/>"+document.getElementById('isItemid').value+"<c:url value="&isDoc=0&returnUrl=${requestUrl}"/>";
			self.location = strUrl;
	    }
	    else{			
			alert("<fmt:message key="infoPublish.msg.pleaseSelectNode"/>");
		}
	}
	
	function delId()
    {
      infoIds = "";
	      var obj=document.getElementsByName('infoId');
	      for (var i = 0 ; i < obj.length; i++)
	      {
		      if(obj[i].checked) {
		        infoIds = infoIds + obj[i].value + ",";
		      }
	      }
	      if (infoIds == "") {
			tip = "<fmt:message key="infoPublish.msg.pleaseSelectObject"/>";
			alert(tip);
			return false;
		  }
		  else {
		    //alert('<c:url value="/newsListDelete.do?infoIds='+infoIds+'&isItemid=${formInfo.isItemid}&category=${category}&returnUrl=${requestUrl}"/>');		    
		  	dataDeleteConfirm('<c:url value="/newsListDelete.do?infoIds='+infoIds+'&isItemid=${formInfo.isItemid}&category=${category}&returnUrl=${requestUrl}"/>');}

    }
    
    function auditId(isAudit)
    {
      infoIds = "";
	      var obj=document.getElementsByName('infoId');
	      for (var i = 0 ; i < obj.length; i++)
	      {
		      if(obj[i].checked) {
		        infoIds = infoIds + obj[i].value + ",";
		      }
	      }
	      if (infoIds == "") {
			tip = "<fmt:message key="infoPublish.msg.pleaseSelectObject"/>";
			alert(tip);
			return false;
		  }
		  else{		   
			window.location='<c:url value="/newsListPass.do?infoIds='+infoIds+'&isItemid=${formInfo.isItemid}&isPass='+isAudit+'&returnUrl=${requestUrl}"/>';
	  	}
    }    
    
    function issuedId(isIssued)
    {
      infoIds = "";
	      var obj=document.getElementsByName('infoId');
	      for (var i = 0 ; i < obj.length; i++)
	      {
		      if(obj[i].checked) {
		        infoIds = infoIds + obj[i].value + ",";
		      }
	      }
	      if (infoIds == "") {
			tip = "<fmt:message key="infoPublish.msg.pleaseSelectObject"/>";
			alert(tip);
			return false;
		  }
		  else{		   
			window.location='<c:url value="/newsListIssued.do?infoIds='+infoIds+'&isItemid=${formInfo.isItemid}&isIssued='+isIssued+'&returnUrl=${requestUrl}"/>';
	  	}
    }      

	function sortId()
    {
    	if (document.getElementById("isItemid").value != null && document.getElementById("isItemid").value != '')
	  		window.location='<c:url value="/newsListSort.do?isItemid=${formInfo.isItemid}&returnUrl=${requestUrl}"/>';
	  	else{			
			alert("<fmt:message key="infoPublish.msg.pleaseSelectNode"/>");
		}
    }  
    
	Calendar.setup({
	    inputField     :    "beginDate",   // id of the input field
	    ifFormat       :    "%Y-%m-%d",       // format of the input field
	    showsTime      :    false
	});
	Calendar.setup({
	    inputField     :    "endDate",   // id of the input field
	    ifFormat       :    "%Y-%m-%d",       // format of the input field
	    showsTime      :    false
	});	
</script>
</BODY>
</HTML>
