<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.AdvertiseLayout.Dialog2Init">
<form id="form3">
<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
    <tr>
      <td class="tdgrey2">
      <textarea id="jscode" name="jscode" cols="58" rows="4"><!-- 广告：${adname} -->
${jscode}</textarea>
      </td>
    </tr>
</table>
<input name="ID" id="ID" type="hidden" value="" />
</form>
</z:init>
</body>
</html>