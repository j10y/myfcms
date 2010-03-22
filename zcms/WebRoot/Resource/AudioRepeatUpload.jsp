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
<style>
.ErrorMsg {
background:#FFF2E9   scroll 5px;
border:1px solid #FF6600;
color:#000000;
padding:5px 5px 5px 25px;
}
</style>
<script type="text/javascript">
function RepeatUpload(){
	var filename = $V("RepeatFile").trim();
	if(filename!=""){
		if(filename.substring(filename.lastIndexOf(".")+1)==$V("Suffix")){
			msg();
			$("divMsg").className="ErrorMsg";
			$F("form2").submit();
		}else{
			Dialog.alert("文件格式不对请重新选择");
			return;
		}
	}else{
		Dialog.alert("请先浏览选择文件!");
		return;
	}
}
var counter=1;
function msg(){
	var txt = "正在上传处理中，请稍后...耗时";
	setInterval(function(){$("divMsg").innerHTML="<font color=red>"+txt+counter+"秒</font>";counter++}, 1000);
}

function onUploadCompleted( returnValue,returnID,errorMessage){
	switch ( returnValue )
	{
		case 0 :	// No errors
			break ;
		case 202 :
			Dialog.alert( '无效的文件类型！以下文件上传失败:'+errorMessage ) ;
			return ;
		case 203 :
			Dialog.alert( "您没有权限上传此文件，请检查服务器设置" ) ;
			return ;
		default :
			Dialog.alert( '上传失败，错误代码: ' + returnValue ) ;
			return ;
	}
	Dialog.alert("上传成功",function(){Dialog.close();});
	
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.resource.Audio.initEditDialog">
<div style="display:none"><iframe name="formTarget" src="javascript:void(0)"></iframe></div>
<form id="form2" enctype="multipart/form-data" action="../Editor/filemanager/upload/simpleuploader.jsp" method="post" target="formTarget">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr height="20">
        <td width="20%">&nbsp;</td>
        <td width="80%">&nbsp;</td>
    </tr>
	<tr>
        <td width="20%" align="right">音频名称：</td>
        <td width="80%">&nbsp;${Name}.${Suffix}<input type="hidden" id="Suffix" name="Suffix" value="${Suffix}"/></td>
    </tr>
    <tr>
        <td width="20%" align="right">重新上传：</td>
        <td width="80%"><input id="Repeat" name="Repeat" type="hidden" value="1" />
        <input id="RepeatID" name="RepeatID" type="hidden" value="${ID}" /> 
        <input type="hidden" id="FileType" name="FileType" value="Audio"> 
        <input name='RepeatFile' id='RepeatFile' type='file' value='' size='30'>
        <input type="button" class="input2" onClick="RepeatUpload();" value="上传" />
        <div id="divMsg"></div>
        </td>
    </tr>
</table>
</form>
</z:init>
</body>
</html>