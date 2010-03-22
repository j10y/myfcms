<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Tools/Swfobject.js"></script>
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

Page.onLoad(function(){
	var so = new SWFObject("../Framework/Controls/ScriptEditor.swf", "ScriptEditor", "850", "320", "9", "#ffffff");
	so.addParam("wmode", "opaque");
	if(parent.Parent.EditFlag){
		var map = parent.Parent.Map;
		$NS("Lang",map["ScriptLang"]);
		so.addVariable("Language",map["ScriptLang"]);
		so.addVariable("AfterInit","afterInit");
	}else{
		so.addVariable("Language","java");
	}
	so.write("FlashDiv");
});

function afterInit(){
	$("ScriptEditor").setText(parent.Parent.Map["Script"])
}

function checkScript(){
	var dc = new DataCollection();
	dc.add("Language",$NV("Lang"));
	dc.add("Script",$("ScriptEditor").getText());
	Server.sendRequest("com.zving.framework.script.CheckScript.check",dc,function(response){
		if(response.Status==1){
			Dialog.alert("未发现语法错误!");
		}else{
			Dialog.alert(response.Message);
		}
	});	
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody" onselectstart="return false" onselect="document.selection.empty()">
<form id="form2">
<table width="850" cellpadding="2" cellspacing="0" style="-moz-user-select:none;" onselectstart="return false" onselect="document.selection.empty()">
	<tr>
		<td width="7">&nbsp;</td>
		<td width="536" height="30" align="left">
		<z:tbutton onClick="checkScript()"><img src="../Icons/icon403a12.gif" width="20" height="20" />检查脚本</z:tbutton>
		</td>
		<td width="96" align="left">
		<label> <input id="isAutoWrap" type="checkbox" onClick="$('ScriptEditor').setAutoWrap($('isAutoWrap').checked)" value="checkbox" checked> 自动换行 &nbsp;&nbsp;</label></td>
		<td width="213">
		脚本语言： <input type="radio" name="Lang" id="Java" value="java" checked onClick="$('ScriptEditor').setLanguage($NV('Lang'))"> 
		<label for="Java">Java</label> <input type="radio" name="Lang" id="JavaScript" value="javascript" onClick="$('ScriptEditor').setLanguage($NV('Lang'))"> 
		<label for="JavaScript">JavaScript</label></td>
	</tr>
</table>
</form>
<div id="FlashDiv"></div>
</body>
</html>
