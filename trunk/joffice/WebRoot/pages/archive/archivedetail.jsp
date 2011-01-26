<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.oa.service.archive.ArchivesService"%>
<%@page import="com.htsoft.oa.model.archive.Archives"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String archiveId = request.getParameter("archiveId");
	ArchivesService arService = (ArchivesService)AppUtil.getBean("archivesService");
	Archives arch=new Archives();
	if(StringUtils.isNotEmpty(archiveId)&&archiveId.indexOf("$")==-1){
		arch=arService.get(new Long(archiveId));
	}
	request.setAttribute("arch",arch);
%>



<h1 align="center" title="公文标题">${arch.subject}</h1> 
<div>
  <span style="font-weight: bolder">公文原件：</span>
  <c:forEach var="doc" items="${arch.archivesDocs}">
      <a href="<%=request.getContextPath()%>/attachFiles/${doc.fileAttach.filePath}" target="_blank">${doc.fileAttach.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:forEach>
</div>
<span style="float:right;padding-right:12%;">创建日期:<fmt:formatDate value="${arch.createtime}" pattern="yyyy-MM-dd"/></span>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
  <tr>
     <th width="15%">来文文字号</th>
     <td>${arch.archivesNo}</td>
     <th width="15%">来文机关</th>
     <td>${arch.issueDep}</td>
     <th width="15%">文件数目</th>
     <td>${arch.fileCounts}</td>
  </tr>
  <tr>
     <th width="15%">来文类型</th>
     <td nowrap="nowrap">${arch.archRecType.typeName}</td>
      <th width="15%">公文来源</th>
     <td nowrap="nowrap">${arch.sources}</td>
     <th width="15%">紧急程度</th>
     <td nowrap="nowrap">${arch.urgentLevel}</td>
  </tr>
   <tr>
     <th width="15%">主题词</th>
     <td colspan="3">${arch.keywords}</td>
     <th width="15%">秘密等级</th>
     <td>${arch.privacyLevel}</td>
    
  </tr>
  <tr>
     <th width="15%">内容</th>
     <td colspan="5">${arch.shortContent}</td>
  </tr>
  <tr>
     <th width="15%">拟办意见</th>
     <td colspan="5">
       <ul>
       <c:forEach var="handle" items="${arch.archivesHandles}">
          <li>${handle.handleOpinion}&nbsp;&nbsp; ${handle.userFullname}&nbsp;&nbsp; <fmt:formatDate
			value="${handle.fillTime}" pattern="yyy-MM-dd HH:mm:ss" /> </li>
       </c:forEach>
       </ul>
    </td>
  </tr>
  <tr>
     <th width="15%">领导意见</th>
     <td colspan="5">
       <ul>
       <c:forEach var="leader" items="${arch.leaders}">
           <li>${leader.leaderOpinion}&nbsp;&nbsp; ${leader.leaderName}&nbsp;&nbsp;<fmt:formatDate
			value="${leader.createtime}" pattern="yyy-MM-dd HH:mm:ss" /></li>
       </c:forEach>
       </ul>
    </td>
  </tr>
  <tr>
     <th width="15%">承办意见</th>
     <td colspan="5">
       <ul>
       <c:forEach var="undertake" items="${arch.archivesDispatch}">
           <c:if test="${undertake.archUserType==1&&undertake.readFeedback!=null}"><li>${undertake.readFeedback}&nbsp;&nbsp;${undertake.fullname}&nbsp;&nbsp;<fmt:formatDate
			value="${undertake.dispatchTime}" pattern="yyy-MM-dd HH:mm:ss" /></li></c:if>
       </c:forEach>
       </ul>
    </td>
  </tr>
</table>

