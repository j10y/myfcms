<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="../../tagLib.jsp"%> 
<HTML>
<HEAD>
<TITLE></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
</HEAD>
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
				<table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">
			        <tr class="bellContentRow"> 
			          <td class="bellContentLabelCell" width="80"><fmt:message key="infoPublish.label.title"/></td>
			          <td class="bellContentDataCell"><c:out value="${newsList.title}"/></td>
			        </tr>
			        <tr class="bellContentRow"> 
			          <td class="bellContentLabelCell" width="80"><fmt:message key="infoPublish.label.whoPublish"/></td>
			          <td class="bellContentDataCell"><c:out value="${newsList.whoPublish}"/></td>
			        </tr>
			        <tr class="bellContentRow"> 
			          <td class="bellContentLabelCell" width="80"><fmt:message key="infoPublish.label.timePublish"/></td>
			          <td class="bellContentDataCell"><fmt:formatDate value="${newsList.timePublish}" pattern="${dateFormatShort}"/></td>
			        </tr>
			        <tr class="bellContentRow"> 
			          <td class="bellContentLabelCell" width="80"><fmt:message key="infoPublish.label.indeximg"/></td>
			          <td class="bellContentDataCell"><c:if test="${newsList.imgPath!=null}">
			                <img src="<c:url value="${newsList.imgPath}"/>" width="141" height="100">
			              </c:if></td>
			        </tr>
			        <tr class="bellContentRow"> 
			          <td class="bellContentLabelCell" width="80"><fmt:message key="infoPublish.label.keyword"/></td>
			          <td class="bellContentDataCell">
			                <c:out value="${newsList.keyword}"/>
			           </td>
			        </tr>
			      </table>
	  			  <table width="97%" border="0" cellpadding="5" cellspacing="1" class="bellContentTable">
			        <tr class="bellContentRow"> 
			          <td class="bellContentDataCell">
					      <c:out value="${newsList.contentString}" escapeXml="false"/>
					  </td>
			        </tr>
			        <tr class="bellBottomRow"> 
					  <td class="bellBottomCell">
					      <input class="button_return" type="button" value="" onclick="javascript:window.location='<c:url value="${param.returnUrl}"/>';">
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
</BODY>
</HTML>