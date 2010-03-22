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
	if($V("RepeatFile")){
		msg();
		$("divMsg").className="ErrorMsg";
		$F("form2").submit();
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
	window.Parent.DataList.loadData("dg1");
	window.location = window.location;
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.resource.Image.initEditDialog">
<div style="display:none">
	<iframe name="formTarget" src="javascript:void(0)"></iframe></div>
<form id="form2" enctype="multipart/form-data"
		action="../Editor/filemanager/upload/simpleuploader.jsp" method="post"
		target="formTarget">
<input id="IDs" type="hidden" value="${IDs}"/>
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td height="120" colspan="2" align="center" valign="middle"><br>
			<img src="${Alias}${Path}s_${FileName}?${ModifyTime}"></td>
		</tr>
		<tr>
			<td height="10" align="right"></td>
			<td></td>
		</tr>
		<tr>
			<td height="20" colspan="2" align="center" valign="middle"><br>
			您将要同时修改 <b style="color:#FF6600;">${IDCount}</b> 张图片的信息</td>
		</tr>
		<tr>
			<td align="right">重新上传：</td>
			<td><input id="Repeat" name="Repeat" type="hidden" value="1" />
			<input id="RepeatID" name="RepeatID" type="hidden" value="${ID}" /> <input
				type="hidden" id="FileType" name="FileType" value="Image"> <input
				name='RepeatFile' id='RepeatFile' type='file' value='' size='30'>
			<input type="button" class="input2" onClick="RepeatUpload();"
				value="上传" />
			<div id="divMsg"></div>
			</td>
		</tr>
		<tr>
			<td align="right">图片名称：</td>
			<td>
			<div align="left"><input id="Name" name="Name" type="text"
				value="${Name}" class="input1" size="30" /></div>
			</td>
		</tr>

		<tr style="display:none ">
			<td align="right">图片原名：</td>
			<td><input id="OldName" name="OldName" type="text"
				class="input1" value="${OldName}" /></td>
		</tr>
		<tr style="display:none ">
			<td align="right">图片链接文本：</td>
			<td><input id="LinkText" name="LinkText" type="text"
				class="input1" value="${LinkText}" /></td>
		</tr>
		<tr style="display:none ">
			<td align="right">图片链接:</td>
			<td><input id="LinkURL" name="LinkURL" type="text"
				class="input1" value="${LinkURL}" /></td>
		</tr>
		<tr>
			<td align="right">图片描述：</td>
			<td><textarea id="Info" name="Info" cols="50" rows="2"
				class="input3" value="">${Info}</textarea></td>
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
