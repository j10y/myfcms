<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告预览</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" align="center" >
<%
String ID = request.getParameter("ID");
DataTable dt = new QueryBuilder("select JSName from zcadposition where ID = ?",ID).executeDataTable();
String JSSrc = Config.getContextPath() + Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias() + "/" +dt.getString(0,0);
%>
<tr align="center" valign="middle">
	<td align="center" valign="middle"><script language='javascript' src='<%=JSSrc %>'></script></td>
</tr>
</table>
</body>
</html>
