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
<script type="text/javascript" src="FCKeditor/fckeditor.js"></script>

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
				<form method="post" action="<c:url value="/newsListEdit.do"/>"
					name="editForm" enctype="multipart/form-data"><input
					type="hidden" name="infoId"
					value="<c:out value="${formInfo.infoId}"/>"> <input
					type="hidden" name="category"
					value="<c:out value="${formInfo.category}"/>"> <input
					type="hidden" name="editFlag"
					value="<c:out value="${formInfo.editFlag}"/>"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"> <input
					type="hidden" name="fileExt"><spring:bind path="formInfo.*">
					<c:if test="${status.error}">
						<table width="700" border="0" align="center" cellpadding="0"
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
									key="infoPublish.label.infoPublishManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentLabelCell" width="30%"><fmt:message
							key="infoPublish.label.title" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="title" size="50" id="title"
							value="<c:out value="${formInfo.title}"/>"> <input
							type="button" class="button_open"
							onClick="javascript:showsubmenu(2);">&nbsp;&nbsp;<input
							type="button" class="button_hidden" onClick="javascript:hidesubmenu(2);"></td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable" id="submenu2" style="DISPLAY: none">
					<tr class="bellContentRow">
						<td width="30%" class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.deputyTitle" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="deputyTitle" size="50" id="deputyTitle"
							value="<c:out value="${formInfo.deputyTitle}"/>"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.author" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="author" size="50" id="author"
							value="<c:out value="${formInfo.author}"/>"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.source" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="source" size="50" id="source"
							value="<c:out value="${formInfo.source}"/>"></td>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.keyword" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="keyword" id="keyword" size="40"
							value="<c:out value="${formInfo.keyword}"/>">
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"></td>
						<td class="bellContentDataCell"><input type="radio"
							name="specialKey" id="specialKey"
							value="<fmt:message
							key="infoPublish.label.specialKey"/>"<c:if test="${formInfo.specialKey == '推荐'}"> checked</c:if>><fmt:message
							key="infoPublish.label.specialKey" /></td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td width="30%" class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.itemId" /></td>
						<td class="bellContentDataCell"><select id="isItemid"
							name="isItemid">
							<option value=""></option>
							<c:forEach items="${treeList}" var="tree">
								<option value="<c:out value="${tree.itemId}"/>"
									<c:if test="${formInfo.isItemid == tree.itemIdStr}">selected</c:if>><c:out
									value="${tree.itemText}" /></option>
							</c:forEach>
					</tr>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.timePublish" /></td>
						<td class="bellContentDataCell"><input type="text"
							name="timePublish" id="timePublish"
							value="<c:out value="${formInfo.timePublish}"/>"></td>
					</tr>
					<!-- 
					<tr class="bellContentRow">
						<td width="30%" class="bellContentLabelCell"><fmt:message
							key="infoPublish.label.subject" /></td>
						<td class="bellContentDataCell"><select id="isSubject"
							name="isSubject">
							<option value=""></option>
							<c:forEach items="${subjectList}" var="tree">
								<option value="<c:out value="${tree.itemId}"/>"
									<c:if test="${formInfo.isSubject == tree.itemIdStr}">selected</c:if>><c:out
									value="${tree.itemText}" /></option>
							</c:forEach>
					</tr> -->
					<c:if test="${formInfo.isImg=='1'}">
						<tr class="bellContentRow">
							<td class="bellContentLabelCell"><fmt:message
								key="infoPublish.label.indeximg" /></td>
							<td class="bellContentDataCell"><img
								src="<c:url value="${formInfo.imgPath}"/>" width="141"
								height="100">
						</tr>
					</c:if>
					<tr class="bellContentRow">
						<td class="bellContentLabelCell"><c:if
							test="${formInfo.isImg=='1'}">
							<fmt:message key="public.label.isReLoadImg" />
						</c:if><c:if test="${formInfo.isImg!='1'}">
							<fmt:message key="infoPublish.label.isImgNews" />
						</c:if></td>
						<td class="bellContentDataCell"><c:if
							test="${formInfo.imgNo!=null}">
							<img
								src="<c:url value="/archiveQuery.do?archiveId=${formInfo.imgNo}"/>"
								width="141" height="100">
						</c:if> <input type="checkbox" id="isImg" name="isImg" value="1"><fmt:message
							key="public.select.yes" /> <script for="upload"
							event="onpropertychange">    
				pic.src=this.value;
				if (this.value == "") 
				  document.getElementById('pic').style.display = "none"; 
				</script> <script for="pic" event="onload"> 
				this.style.display="" 
				</script> <script for="pic" event="onerror">  
				this.style.display="none" 
				alert("<fmt:message key="public.msg.notImg"/>") 
				</script> <input style="display:none" type="file" name="upLoadFile"
							style="display:block" id="upload"
							value="<c:out value="${formInfo.upLoadFile}"/>"
							onChange=CheckChange("upLoadFile","fileExt")> <img
							id="pic" style="display:none" width="141" height="100"></td>
					</tr>
					<tr class="bellContentRow">
						<td colspan="2" class="bellContentDataCell" valign="top"><FCK:editor
							id="content" basePath="/FCKeditor/" height="400"
							skinPath="editor/skins/silver/">
							<c:out value="${formInfo.content}" escapeXml="false" />
						</FCK:editor> <input type="hidden" name="contentNo"
							value="<c:out value="${formInfo.contentNo}"/>"></td>
					</tr>
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2"><input
							class="button_save" type="submit" name="saveButton" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
							class="button_clear" type="button" name="saveButton"
							onclick="clearAll();">&nbsp;&nbsp;&nbsp;&nbsp;<input
							onclick="javascript:window.location='<c:url value ="${formInfo.returnUrl}"/>';"
							class="button_return" type="button" name="return" value=""></td>
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
Calendar.setup({
        inputField     :    "timePublish",   // id of the input field
        ifFormat       :    "%Y-%m-%d",       // format of the input field
        showsTime      :    false
});
function clearAll()
{	
	document.editForm.title.value='';
	document.editForm.deputyTitle.value='';
	document.editForm.author.value='';
	document.editForm.source.value='';
	document.editForm.keyword.value='';
	document.editForm.specialKey.value='';
	document.editForm.isImg.checked=false;
	window.content___Frame.FCK.SetHTML('');
	<%//调用内容的方法window.content___Frame.FCK.EditorDocument.body.innerHTML;%>	
}
</script>
<script for="isImg" event="onclick">
  document.getElementById('upload').style.display = (this.checked == 1 ? '' : 'none');
  document.getElementById('pic').style.display = ((this.checked==1 && document.getElementById('upload').value != "") ? '' : 'none');
</script>
</BODY>
</HTML>
