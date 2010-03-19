<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../tagLib.jsp"%>
<html>
<head>
<title><fmt:message key="mainFrame.title" /></title>
</head>
<frameset rows="152,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="<c:url value = "/head.do" />" name="topFrame"
		scrolling="No" noresize="noresize" id="topFrame" />
	<frameset cols="210,*" frameborder="no" border="0" framespacing="0">
		<frame src="<c:url value = "/left.do" />" name="leftFrame"
			scrolling="No" noresize="noresize" id="leftFrame" />
		<frame src="<c:url value = "/welcome.do" />" name="mainFrame"
			id="mainFrame" />
	</frameset>
</frameset>
<noframes>
<body></body>
</noframes>
</html>
