<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function check(){
	if($V("ExportFile")==""){
		alert("请选择导入的文件！");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<z:init>
  <form id="form1" enctype="multipart/form-data" action="CustomTableDataImportSave.jsp" method="post" onSubmit="return check();">
    <table width="90%" height="80" align="center" cellpadding="2" cellspacing="0">
      <tr>
        <td height="55" align="left" valign="middle">
		<%
		if("1".equals(request.getParameter("Error"))){
			out.println("<font color='red'>只支持后缀为xls的Excel文件!</font><br><br>");
			out.println("<script>Dialog.endWait();</script>");
		}
		%>		
		<p>选择要导入的文件：
	 	  <input name="ID" type="hidden" id="ID" value="${ID}">
		  <input name="ExportFile" type="file" id="ExportFile">
		</p></td>
      </tr>
    </table>
  </form>
</z:init>
</body>
</html>
