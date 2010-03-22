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
var No = 1;
function add(){
	var arr = [];
	arr.push("<table id='Table"+No+"' width='860' border='0' align='center' cellpadding='2' cellspacing='0'>");
	arr.push("  <tr>");
	arr.push("    <td><div id='FlashDiv"+No+"'></div></td>");
	arr.push("  </tr>");
	arr.push("</table>");
	var div = document.createElement("div");
	div.id = "DIV"+No;
	div.innerHTML = arr.join('');
	document.body.appendChild(div);	
	var so = new SWFObject("../Framework/Controls/ScriptEditor.swf", "FilterBlock"+No, "850", "160", "9", "#ffffff");
	if(isIE){
		so.addParam("wmode", "transparent");
	}
	so.addVariable("Language","html");	
	so.addVariable("AfterInit","afterInit");	
  	so.write("FlashDiv"+No);
	if(No>1){
		$("RemoveButton").enable();
	}
	No++;
}

function del(){
	if(No>2){
		No--;
		$("DIV"+No).outerHTML = "";
		if(No==2){
			$("RemoveButton").disable();
		}
	}
}

Page.onLoad(function(){
	var flag = false;
	if(parent.Parent.EditFlag){
		var map = parent.Parent.Map;
		for(var k in map){
			var v = map[k];			
			if(k.startWith("FilterBlock")){
				add();
				$S(k,v);
				flag = true;
			}
		}
	}else{
		add();
		flag = true;
	}
	if(!flag){
		add();
	}
	if(No==2){
		$("RemoveButton").disable();
	}
});

function afterInit(id){
	if(parent.Parent.EditFlag){
		$(id).setText(parent.Parent.Map[id]);
	}
}

function setAutoWrap(){
	for(var i=1;i<No;i++){
		$('FilterBlock'+i).setAutoWrap($('isAutoWrap').checked)
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<table width="860" align="center" cellpadding="2" cellspacing="0">
	<tr>
		<td width="5">&nbsp;</td>
		<td width="0" height="30" align="left">
		<z:tbutton onClick="add()"><img src="../Icons/icon404a8.gif" width="20" height="20" />新增过滤块</z:tbutton> 
		<z:tbutton id="RemoveButton" onClick="del()"><img src="../Icons/icon404a5.gif" width="20" height="20" />删除过滤块</z:tbutton></td>
		<td width="65">&nbsp;</td>
		<td align="right">
		<label><input id="isAutoWrap" type="checkbox" onClick="setAutoWrap();" value="checkbox" checked>自动换行&nbsp;&nbsp;</label></td>
	</tr>
</table>
</body>
</html>
