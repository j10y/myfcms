<%@page import="com.zving.platform.Login"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%
	String username = request.getParameter("u");
	String time = request.getParameter("t");
	String str = request.getParameter("s");
	String key = "WIU%&*DJAJKL%^*W(DLJIST";
        String s = StringUtil.md5Hex(username + time + key);
	if (s.equals(str)) {
		Login.ssoLogin(request, response, username);
	}
%>