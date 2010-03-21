<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function changeMethod(){
	var deployMethod = $NV("Method");
  if(deployMethod=="FTP"){
   $E.show("trHost");
	 $E.show("trPort");
	 $E.show("trUserName");
	 $E.show("trPassword");
	 if($V("Port")==""){
	 	$S("Port",21);
	 }
  }else if(deployMethod=="HTTP"){
   $E.show("trHost");
	 $E.hide("trPort");
	 $E.hide("trUserName");
	 $E.hide("trPassword");
  }else{
   $E.hide("trHost");
	 $E.hide("trPort");
	 $E.hide("trUserName");
	 $E.hide("trPassword");
  }
}

function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;
	diag.Height = 500;
	diag.Title ="文件浏览";
	diag.URL = "Site/FileExplorer.jsp";
	goBack = function(params){
		$S(ele,params);
	}
	diag.OKEvent = onSelect;
	diag.show();
}

function onSelect(){
	$DW.onOK();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td width="110" height="10" class="tdgrey2">
		<input type="hidden" id="Type" value="USER">
		<input type="hidden" id="ID" value="">
		</td>
		<td class="tdgrey2"></td>
	</tr>
	<tr>
		<td height="35" align="right" class="tdgrey1">复制方式：</td>
		<td height="35" class="tdgrey2">
		<input type="radio" name="Method" id="dirMethod" value="DIR" checked onclick="changeMethod()"><label for="dirMethod">本地目录</label> 
		<!--input type="radio" name="Method" id="httpMethod"  value="HTTP" onclick="changeMethod()">
<label for="httpMethod">HTTP</label--> 
		<input type="radio" name="Method" value="FTP" id="ftpMethod" onclick="changeMethod()"> <label for="ftpMethod">FTP</label></td>
	</tr>
	<tr>
		<td height="35" align="right" class="tdgrey1">源目录：</td>
		<td height="35" class="tdgrey2">
		<input name="SourceDir" type="text" value="" class="inputText" id="SourceDir" size=40 verify="NotNull" /> 
		<input type="button" name="choose" value="选择" onclick="browse('SourceDir')"></td>
	</tr>
	<tr>
		<td height="35" align="right" class="tdgrey1">目标目录：</td>
		<td height="35" class="tdgrey2">
		<input name="TargetDir" type="text" value="" class="inputText" id="TargetDir" size=40 verify="NotNull" /></td>
	</tr>
	<tr id="trHost" style="display:none">
		<td height="35" align="right" class="tdgrey1">服务器地址/URL：</td>
		<td height="35" class="tdgrey2">
		<input name="Host" type="text" value="" class="inputText" id="Host" size=40 verify="NotNull" condition="$NV('Method')=='HTTP' || $NV('Method')=='FTP'" /></td>
	</tr>
	<tr id="trPort" style="display:none ">
		<td height="35" align="right" class="tdgrey1">端口：</td>
		<td height="35" class="tdgrey2">
		<input name="Port" type="text" value="" class="inputText" id="Port" size=15 /></td>
	</tr>
	<tr id="trUserName" style="display:none ">
		<td height="35" align="right" class="tdgrey1">用户名：</td>
		<td height="35" class="tdgrey2">
		<input name="UserName" type="text" value="" class="inputText" id="UserName" size=30 /></td>
	</tr>
	<tr id="trPassword" style="display:none ">
		<td height="26" align="right" class="tdgrey1">密码：</td>
		<td height="26" class="tdgrey2">
		<input name="Password" type="password" class="inputText" id="Password" size=30 /></td>
	</tr>
	<tr>
		<td height="35" align="right" class="tdgrey1">状态：</td>
		<td height="35" class="tdgrey2">
		<input type="radio" name="UseFlag" value="0" id="UseFlag0"><label for="UseFlag0">停用</label>
		<input type="radio" name="UseFlag" value="1" id="UseFlag1" checked><label for="UseFlag1">启用</label></td>
	</tr>
</table>
</form>
</body>
</html>
