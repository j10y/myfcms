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
	function importColumn(){
		var file=trim($V("TemplateFile"));
		var txt=format($V("batchColumn"));
		//如果选择了导入文件按上传文件导入
		if(file!=''&&/\S+\.txt$/.test(file)){
			$("form2").submit();
			return;
		}
		//如果没有导入文件,且填写了栏目,按填写栏目导入
		if(txt==''||txt==$("batchColumn").getAttribute("oldValue")){
			Dialog.alert("请选择上传的txt文件<br/>或填写栏目名称(更改示例内容)");
			return;
		}
		if(!checkFormat(txt)){
			return;
		}
		$("form3").submit();
	}
	
	function closeDialog(){
	   Dialog.close();
	}
</script>
<style type="text/css">
<!--
.style1 {color: #445566; font-weight:bold;}
-->
</style>
</head>
<body class="dialogBody">
<p>&nbsp;<iframe src="javascript:void(0);" name="targetFrame"
	width="0" height="0" frameborder="0"></iframe></p>
    <form id="form2" enctype="multipart/form-data" target="targetFrame"
		action="CatalogImportSave.jsp?ParentID=<%=request.getParameter("ParentID")%>&Type=<%=request.getParameter("Type")%>"
		method="POST">
<table width="100%" align="center" cellpadding="1" cellspacing="0"	>
	<tr>
		<td width="2%" height="30" align="left">&nbsp;&nbsp;</td>
		<td width="98%" height="30" align="left">选择文件： <input
			name="TemplateFile" type="file" class="input1" id="TemplateFile"
			size="45">
		<p></p>
		</td>
	</tr>
</table>
	</form>
    <form id="form3" target="targetFrame" method="POST"
			action="CatalogImportSave2.jsp?ParentID=<%=request.getParameter("ParentID")%>&Type=<%=request.getParameter("Type")%>">
<table width="100%" align="center" cellpadding="1" cellspacing="0"	> 
	<tr>
		<td align="center">
		<div style="text-align:left;">
		</div>
		</td>
		<td valign="top"><br>
		<p><span class="style1">要求：</span> 1.上传文件必须为文本文件；<br>
		<span style="margin-left:3.6em;">2.文件要求每行一个栏目；</span><br>
		<span style="margin-left:3.6em;">3.不能有空行；</span><br>
		<span style="margin-left:3.6em;">4.子栏目相对父栏目使用两个英文空格缩进。</span><br />
		<span style="margin-left:3.6em;">5.您可以选择上传.txt文件,或在下面的示例中直接更改内容,然后点击"下一步"提交。</span>
		<p><span class="style1">示例：</span>
		<textarea cols="50" style="height:150px; vertical-align:top;"
			id="batchColumn" name="batchColumn">时政要闻
经济观察
  股市风云
  地产信息			
信息产业
  应用软件
  游戏产业               
</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"></td>
	</tr>

	<tr>
		<td colspan="2" align="center" class="dialogButtonBg"><input
			name="button" type="button" class="inputButton" id="button"
			value=" 下一步 " onClick="importColumn()" /> &nbsp; <input
			name="button2" type="button" class="inputButton"
			onClick="Dialog.close();" value=" 取 消 " /></td>
	</tr>

</table>
</form>
<!--设定textarea的初始值 -->
<script type="text/javascript">
	function init(){
		var obj=document.getElementById('batchColumn');
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
	//检查填写的字符串格式是否正确
	function checkFormat(str){
		if(!str){
			return false;
		}
		var reg=/([ ]{2})*\S+/g;
		var arr=str.match(reg);
		
		if(arr.length==0){
			return false;
		}
		if(arr.length==1){
			return true;
		}
		if(arr.length>1){
			var previous=arr[0];
			for(var i=1;i<arr.length;i++){
				var cur=arr[i];
				var count1=0;
				var count2=0;
				for(var j=0;j<previous.length;j++){
					if(previous.charAt(j)!=' '){
						break;
					}
					count1++;
				}
				for(var j=0;j<cur.length;j++){
					if(cur.charAt(j)!=' '){
						break;
					}
					count2++;
				}
				if(count2%2!=0){
					alert("第"+(i+1)+"行格式错误");
					return false;
				}
				count1=count1/2;
				count2=count2/2;
				if(Math.abs(count1-count2)>1){
					alert("第"+(i+1)+"行格式错误");
					return false;
				}
				previous=cur;
			}
		}
		return true;
	}
	init();
	
</script>
</body>
</html>
