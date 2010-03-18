<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../ymPrompt.jsp"%>
<HTML>
<HEAD>
<TITLE><fmt:message key="mainFrame.title"/></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css">
<script type="text/javascript">
ymPrompt.succeedInfo({message:'<fmt:message key="person.label.passwordModifySucceed"/>',width:300,height:200,handler:handler2});
function handler2(tp){
			if(tp=='ok'){
				window.location = '<c:url value="/passwordModify.do"/>';
			}
			if(tp=='close'){
				window.location = '<c:url value="/passwordModify.do"/>';
			}
}
</script>
</HEAD>
<BODY class="billBody">

</BODY>
</HTML>