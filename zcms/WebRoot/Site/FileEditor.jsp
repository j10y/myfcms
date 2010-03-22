<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%@page language="java" import="com.zving.FCKeditor.*"%>
<%
	String fileContent = FileUtil.readText(request.getParameter("fileName"));
	String alias = request.getParameter("alias");
	//fileContent = fileContent.replaceAll("../images","../Template/CMS/"+alias+"/images");
	//fileContent = fileContent.replaceAll("../include","../Template/CMS/"+alias+"/include");
	//fileContent = StringUtil.rightTrim(fileContent);
	//fileContent = StringUtil.htmlEncode(fileContent);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件编辑</title>
<link href="../Include/Base.css" rel="stylesheet" type="text/css" />
<link href="../Include/Platform.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>

function htmlDecode2(str) {
	str = str.replace(/\&quot;/g,"\"").replace(/\&lt;/g,"<").replace(/\&gt;/g,">").replace(/\&nbsp;/g," ").replace(/\&amp;/g,"&");
	str = str.replace(/\<cms:\w*?[^\>]*?\>/gi,"<!--$&-->");
	str = str.replace(/\<\/cms:\w*?\>/gi,"<!--$&-->");
	return str;
}

</script>
<style>
body{ text-align:left; font-size:12px;color:#666666; margin:0px; background:#666666;}
</style>
</head>
<body>
<div id="_EditView">
<table width="100%" border="0">
	<tr>
		<td align="center">
		<form id="form1">
		<table width="780" border="0" align="center" cellpadding="10"
			bgcolor="#FFFFFF" style="margin:5px;margin-top:8px">
			<tr>
				<td>
				<table width="100%" height="600" cellpadding="2" cellspacing="0"
					class="dataTable" id="ORGTree">
					<tr>
						<td height="23" align="center"><textarea name="textarea"
							id="_Content" style="width:100%;height:100%;font-size:14px"><%=fileContent%></textarea>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</div>
</body>
</html>
