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
		if($V("File")==""){
			alert("请选择上传的文件！");
			return false;
		}
		return true;
	}
	
	function closeDialog(){
	   Dialog.close();
	}
</script>
</head>
<body>
<p>&nbsp;<iframe src="javascript:void(0);" name="targetFrame"
	width="0" height="0" frameborder="0"></iframe></p>
<form id="form2" enctype="multipart/form-data" target="targetFrame"
	action="FileImportSave.jsp?SiteID=<%=request.getParameter("SiteID")%>&Path=<%=request.getParameter("Path")%>"
	method="POST" onSubmit="return check();">
<table width="90%" height="209" align="center" cellpadding="2"
	cellspacing="0">
	<tr>
		<td height="55" colspan="2" align="left">
		<p>选择文件： <input name="File" type="file" id="File"> <br>
		<font color="#FF6633">支持HTML、HTM、JSP、PHP、ASP、CSS、JS、JPG、GIF格式文件</font></p>
		</td>
	</tr>
	<!--
    <tr>
      <td  colspan="2" align="center" ><div style="text-align:left;">
        <p>如果文件重名：<br>
          <label for="Overwrite">
            <input name="DealType" id="Overwrite" type="radio"  value="0">
            覆盖</label>
            <label for="Rename">
            <input name="DealType" id="Rename"type="radio" value="1" checked> 
            重命名            </label>
            <label for="Note"> 
            <input name="DealType" id="Note" type="radio" value="2">
提示</label></p>
        </div></td>
    </tr>
    -->
	<tr>
		<td colspan="2" align="center"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input name="button"
			type="Submit" class="inputButton" id="button" value=" 确 定 " /> &nbsp;
		<input name="button2" type="button" class="inputButton"
			onClick="Dialog.close();" value=" 取 消 " /></td>
	</tr>
</table>
</form>
</body>
</html>
