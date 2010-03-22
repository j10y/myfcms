<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>
<body class="dialogBody">
<form action="DBUploadSave.jsp" enctype="multipart/form-data" method="post" id="f1">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td height="30" colspan="2" align="center">
	    <span class="STYLE1">
	      <%
		  if(StringUtil.isNotEmpty(request.getParameter("Error"))){
			out.println(request.getParameter("Error"));
		  }
		  if(StringUtil.isNotEmpty(request.getParameter("TaskID"))){
		  %>
			<script>
				var p = new Progress(<%=request.getParameter("TaskID")%>,"正在导入数据...",500,150);
				p.show(function(){
					$D.close();
					window.Parent.Dialog.alert("导入成功!");
					window.Parent.$D.close();
				});			
				p.Dialog.OKButton.hide();
				p.Dialog.CancelButton.hide();
				p.Dialog.CancelButton.onclick = function(){}
			</script>
		  <%}%>
	  &nbsp;</span></td>
    </tr>
    <tr>
      <td align="right">己导出的数据库文件：</td>
      <td height="30"><input id="DBFile" name="DBFile" type="file" size="40"></td>
    </tr>
</table>
</form>
</body>
</html>