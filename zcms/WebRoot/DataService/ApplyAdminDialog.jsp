<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.framework.Config"%>
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.ApplyAdmin.initDialog">
<input type="hidden" id="aa" name="aa" value="${ID}">
<form id="form" method="post">
<table width="660" border="1" cellpadding="4" cellspacing="0" style="margin:10px auto;" bordercolor="#e8e8e8">
    <tr>
        <td width="22%" align="right" >姓名：</td>
        <td colspan="2">${Name}</td>
        <td colspan="3" rowspan="10" align="center" valign="middle"><a href="${PictureFile}" target="_blank"><img src="${PictureFile}" width="200" height="200" border="0"/></a> </td>
    </tr>
    <tr>
        <td align="right">性别：</td>
        <td colspan="2"> ${Gender}</td>
    </tr>
    <tr>
        <td align="right">出生日期：</td>
        <td colspan="2">${BirthDate}</td>
    </tr>
    <tr>
        <td align="right">身份证号：</td>
        <td colspan="2">${CertNumber}</td>
    </tr>
    <tr>
        <td align="right">民族：</td>
        <td colspan="2">${Ethnicity}</td>
    </tr>
    <tr>
        <td align="right">籍贯：</td>
        <td colspan="2">${NativePlace}</td>
    </tr>
    <tr>
        <td align="right">政治面貌：</td>
        <td colspan="2">${Political} </td>
    </tr>
    <tr>
        <td align="right">最高学历：</td>
        <td colspan="2">${EduLevel}</td>
    </tr>


    <tr>
        <td width="22%" align="right">手机：</td>
        <td colspan="2">${Mobile}</td>
    </tr>
    <tr>
        <td width="22%" align="right">固定电话：</td>
        <td colspan="2">${Phone}</td>
    </tr>
    <tr>
        <td align="right">联系地址：</td>
        <td colspan="5">${Address}</td>
    </tr>
    <tr>
        <td align="right">电子邮箱：</td>
        <td align="left" colspan="2">${Email}</td>
        <td colspan="3">邮编：${Postcode}</td>
    </tr>
    <tr>
        <td width="22%" align="right">户籍所在地：</td>
        <td colspan="5">${RegisteredPlace}</td>
    </tr>
    <tr>
        <td width="22%" align="right">毕业院校：</td>
        <td colspan="2">${University}</td>
        <td width="45%" colspan="3">专业：${Speacility}</td>
    </tr>
    <tr>
        <td align="right">资格认证：</td>
        <td colspan="5">${Authentification}</td>
    </tr>
    <tr>
        <td align="right">个人简介：</td>
        <td colspan="5" style="height: 100px">${PersonIntro}</td>
    </tr>
    <tr>
        <td align="right">获奖情况：</td>
        <td colspan="5" style="height: 70px">${Honour}</td>
    </tr>
    <tr>
        <td align="right">实习经历：</td>
        <td colspan="5" style="height: 70px">${PracticeExperience}</td>
    </tr>
</table>
</form>
</z:init>
</body>
</html>
