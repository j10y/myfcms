<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<z:init method="com.zving.platform.Workflow.initDialog">
	<title>工作流编辑---${Name}</title>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<script src="../Framework/Main.js"></script>
	<script src="../Tools/Swfobject.js"></script>
	<script>
Page.onLoad(function(){
	$("SourceEditor").height = document.body.clientHeight-30;
});

function save(){
	var dc = new DataCollection();
	var content = $('SourceEditor').getText();
	dc.add("Definition",content);
	dc.add("ID",$V("ID"))
	Server.sendRequest("com.zving.platform.Workflow.edit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$S('Source',content);
			}
		});
	});
}

function closeX(){
	var oldContent = $V('Source').replace(/\r\n/gi,'\n').trim();
	if($('SourceEditor').getText().trim()!=oldContent){
		if(confirm("内容已经修改，还没有保存，确认关闭吗？点击”确认“关闭，点击”取消“返回。")){
			window.close();
		}
	}else{
		window.close();
	}
}
</script>

	<style>
body{ text-align:left; font-size:12px;color:#666666; margin:0px; background:#F6F9FD;}
</style>
</head>
<body scroll="no">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td><z:tbutton onClick="save()">
			<img src="../Icons/icon018a2.gif" />保存</z:tbutton> <z:tbutton onClick="find()">
			<img src="../Icons/icon018a4.gif" />查找</z:tbutton> <z:tbutton onClick="closeX()">
			<img src="../Icons/icon403a11.gif" />关闭</z:tbutton></td>
	</tr>
</table>
<div id="FlashDiv"></div>
<script type="text/javascript">
var so = new SWFObject("../Framework/Controls/ScriptEditor.swf", "SourceEditor", "100%", "400", "9", "#ffffff");
so.addVariable("Language","html");
so.addVariable("AfterInit","setContent");
so.addParam("wmode", "opaque");
so.write("FlashDiv");

function setContent(){
	$('SourceEditor').setText($V('Source').replace(/\r\n/gi,'\n'));
}
</script>

<input id="ID" type="hidden" value="${ID}">
<textarea style="display:none" id="Source">${Definition}</textarea>
</body>
</z:init>
</html>
