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
<script type="text/javascript">
function importWords(){
	var file=trim($V("WordsFile"));
	var txt=format($V("BadWords"));
	//如果选择了导入文件按上传文件导入
	if(file!=''&&/\S+\.txt$/.test(file)){
		$("form2").submit();
		return;
	}
	//如果没有导入文件,且填写了栏目,按填写栏目导入
	if(txt==''||txt==$("BadWords").getAttribute("oldValue")){
		Dialog.alert("请选择上传的txt文件<br/>或填写敏感词(更改示例内容)");
		return;
	}
	doImport();
}

function doImport(){
	var BadWords = $V("BadWords");
	var dc = new DataCollection();
	dc.add("BadWords",BadWords);
	Server.sendRequest("com.zving.cms.site.BadWord.importWords",dc,function(response){
	Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.Parent.loadWordData();
				Dialog.close();
			}
		});
	});
}

function afterUpload(FilePath){
	var dc = new DataCollection();
	dc.add("FilePath",FilePath);
	Server.sendRequest("com.zving.cms.site.BadWord.importWords",dc,function(response){
	Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.Parent.loadWordData();
				Dialog.close();
			}
		});
	});
}
</script>
<style type="text/css">
<!--
.style1 {color: #445566; font-weight:bold;}
-->
</style>
</head>
<body class="dialogBody">
<div style="display:none;"><iframe src="javascript:void(0);"
	name="targetFrame" width="0" height="0" frameborder="0"></iframe></div>
    <form id="form2" enctype="multipart/form-data" target="targetFrame"
		action="BadWordImportSave.jsp" method="POST">
<table width="100%" align="center" cellpadding="1" cellspacing="0"	>
	<tr>
		<td width="11%" height="30" align="left">&nbsp;</td>
		<td width="89%" height="30" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="11%" height="30" align="left">&nbsp;&nbsp;</td>
		<td width="89%" height="30" align="left">选择文件： <input
			name="WordsFile" type="file" class="input1" id="WordsFile" size="45">
		</td>
	</tr>
</table>
	</form>
    <form id="form3">
<table width="100%" align="center" cellpadding="1" cellspacing="0"	> 
	<tr>
		<td width="11%" align="center"></td>
		<td width="89%" valign="top"><br>
		<p><span class="style1">要求：</span><br>
		<span style="margin-left:3.6em;">1.上传文件必须为文本文件；</span><br>
		<span style="margin-left:3.6em;">2.文件要求每行一个敏感词条目；</span><br>
		<span style="margin-left:3.6em;">3.请使用英文标点，参数之间用英文"<font
			color="#FF0000">,</font>"或者<font color="#FF0000">空格</font>隔开。</span><br />
		<span style="margin-left:3.6em;">4.敏感级别由数字 1,2,3代替，<font
			color="#FF6600">1--一般；2--比较敏感；3--特别敏感。</font></span><br />
		<span style="margin-left:3.6em;">5.您可以选择上传.txt文件,或在下面的示例中直接更改内容,然后点击"确定"提交。</span>
		<p><span class="style1">示例：</span>
		<textarea cols="50"
			style="height:150px; vertical-align:top;" id="BadWords"
			name="BadWords">
我靠,我的天,3
TMD,***,1
日,太阳,2
</textarea>
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">

	init();
	<!--设定textarea的初始值 -->
	function init(){
		var obj=document.getElementById("BadWords");
		var str=obj.value;
		obj.setAttribute('oldValue',format(str));
	}
	
	function trim(str){
		str=str.replace(/^\s*/g,'');
		str=str.replace(/\s*$/g,'');
		return str;
	}
	//格式化,去掉左边空格,右边空格,空字符(换行符除外)
	function format(str){
		str=trim(str);
		str=str.replace(/[\t\r\f]/g,'');
		str=str.replace(/[ \t\r\f]*\n[ \t\r\f]*\n+/g,'\n');
		return str;
	}
</script>
</body>
</html>
