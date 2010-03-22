<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.dataservice.Vote.initDialog">
<form id="form2">
<table width="500" border="0" align="center" cellpadding="0" cellspacing="6" style="border-collapse: separate; border-spacing: 6px;">
	<tr>
      <td width="22%" align="right" ></td>
      <td width="78%"></td>
    </tr>
    <tr>
      <td align="right" >调查主题：</td>
      <td>
		  <input name="Title" id="Title" value="${Title}" type="text" size="50" verify="调查主题|NotNull" />
		  <input type="hidden" name="ID" id="ID" value="${ID}" />
	  </td>
    </tr>
	<tr>
      <td align="right" >是否限制IP：</td>
      <td>${IPLimit}</td>
    </tr>
    <tr>
      <td align="right" >验证码标志：</td>
      <td>${VerifyFlag}</td>
    </tr>
    <tr style="display:none">
      <td align="right" >宽度显示：</td>
      <td><input name="Width" id="Width" value="${Width}" type="text" size="1" verify="显示宽度|NotNull&&Int" />像素</td>
    </tr>
    <tr>
	  <td align="right">开始时间：</td>
	  <td>
	  <input name="StartDate" id="StartDate" value="${StartDate}" type="text" size="14" ztype="Date"/>
	  <input name="StartTime" id="StartTime" value="${StartTime}" type="text" size="10" ztype="Time"/><font color="red">为空则立即开始</font>
	  </td>
	</tr>
	<tr>
	  <td align="right">终止时间：</td>
	  <td >
	  <input name="EndDate" id="EndDate" value="${EndDate}" type="text" size="14" ztype="Date"/> 
	  <input name="EndTime" id="EndTime" value="${EndTime}" type="text" size="10" ztype="Time"/><font color="red">为空则需要手动终止</font>
	  </td>
	</tr>
</table>
</form>
</z:init>
</body>
</html>
