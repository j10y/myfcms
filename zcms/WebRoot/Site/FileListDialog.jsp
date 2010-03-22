<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function checkFileName(ele){
	var value = $V(ele);
	var re = /[\\\/\:\*\?\"\<\>\|]/g;
	if(re.test(value)){
		show();
		$S(ele,value.replace(re,""));
	}else{
		if($("div_hint").style.display==""){
			setTimeout("hide()",3000);
		}
	}
}

function show(){
	$("div_hint").style.display="";
}

function hide(){
	$("div_hint").style.display="none";
}
</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td width="24%" height="20" align="right"></td>
		<td width="76%"></td>
	</tr>
	<tr id="tr_file">
		<td align="right">文件名称：</td>
		<td><input name="fileName" type="text" value="" id="fileName"
			size=30 onKeyUp="checkFileName(this)" /></td>
	</tr>
	<tr id="tr_directory">
		<td align="right">目录名称：</td>
		<td><input name="directoryName" type="text" value=""
			id="directoryName" size=30 onKeyUp="checkFileName(this)" /></td>
	</tr>
	<tr id="tr_file_old">
		<td align="right">旧名称：</td>
		<td><input name="oldFileName" type="text" value="" class="input1"
			id="oldFileName" size=30 onKeyUp="checkFileName(this)" /></td>
	</tr>
	<tr id="tr_file_new">
		<td align="right">新名称：</td>
		<td><input name="newFileName" type="text" value="" class="input1"
			id="newFileName" size=30 onKeyUp="checkFileName(this)" /></td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="div_hint" style="display:none"><font color="red">名称不能包含下列字符:\/:*?"<>|</font></div>
		</td>
	</tr>
</table>
</form>
</body>
</html>
