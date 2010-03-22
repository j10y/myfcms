<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.resource.Video.initEditDialog">
	<form id="form2">
	<table width="400px" align="center" cellpadding="2" cellspacing="0" id="edit">
		<tr>
			<td height="5" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<img id="AbbrImage"
						name="AbbrImage" src="..${Alias}${Path}${ImageName}?${ModifyTime}">
			</td>
		</tr>
		<tr align="center">
			<td width="26%" height="20%" align="right">视频名称：</td>
			<td height="75%" align="left">
			<div align="left"><input id="Name" name="Name" type="text"
				value="${Name}" class="input1" /> <input id="ID" type="hidden"
				value="${ID}" /></div>
			</td>
		</tr>
		<tr>
			<td align="right">视频描述：</td>
			<td width="74%" align="left"><textarea id="Info" name="Info"
				cols="40" rows="2" class="input3">${Info}</textarea></td>
		</tr>
		<tr>
			<td align="right">视频标签：</td>
			<td width="74%" align="left"><input id="Tag" name="Tag"
				type="text" value="${Tag}" class="input1" /></td>
		</tr>
		<tr>
			<td align="right">积分：</td>
			<td width="74%" align="left"><input id="Integral"
				name="Integral" type="text" value="${Integral}" class="input1" /></td>
		</tr>
		<tr align="center">
			<td align="right">是否原创：</td>
			<td width="74%" align="left">${IsOriginal}</td>
		</tr>
		<tr align="center">
			<td align="right">重新抓图：</td>
			<td width="74%" align="left"><input id="StartSecond"
				name="StartSecond" type="text" value="" class="input1" size="1" />秒</td>
		</tr>
		<tr>
			<td align="right">最后修改人：</td>
			<td>${ModifyUser}</td>
		</tr>
		<tr>
			<td align="right">最后修改时间：</td>
			<td>${ModifyTime}</td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
