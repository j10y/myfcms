<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择列</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
</head>
<body>
<z:datagrid id="dg1" method="com.zving.cms.dataservice.CustomTable.dg1DataBind" page="false">
  <table width="100%" cellpadding="2" cellspacing="0" class="dataTable" >
    <tr ztype="head" class="dataTableHead">
      <td  width="4%" ztype="rowno">&nbsp;</td>
      <td width="5%" ztype="selector" field="Column_Name" checkedField="isKey" checkValue="">&nbsp;</td>
      <td width="8%" align="center">主键</td>
      <td width="22%"><b>列代码</b></td>
      <td width="20%"><b>数据类型</b></td>
      <td width="11%" ztype="checkbox" checkedValue="0" field="Nullable">是否必填</td>
      <td width="30%">列代码重命名</td>
    </tr>
    <tr style1="background-color:#FFFFFF" style2="background-color:#F7F8FF">
      <td align="center">&nbsp;</td>
      <td isKey="${isKey}">&nbsp;</td>
      <td align="center">${KeyImage}</td>
      <td>${Column_Name}</td>
      <td>${Type_Name}</td>
      <td>&nbsp;</td>
      <td><input type="text" name="textfield"></td>
    </tr>
</table>
</z:datagrid>
</body>
</html>
