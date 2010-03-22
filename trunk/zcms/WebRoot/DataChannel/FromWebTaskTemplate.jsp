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
	arr.push("<table width='860' border='0' align='center' cellpadding='2' cellspacing='0'>");
	arr.push("  <tr style='display:none'>");
	//arr.push("    <td width='92'>引用代码：</td>");
	arr.push("    <td width='758'><input type='text' verify='NotNull' id='RefCode"+No+"' value='Ref"+No+"' name='RefCode'></td>");
	arr.push("  </tr>");
	arr.push("  <tr>");
	//arr.push("    <td valign='top'><div class='divbtn' onClick='del(event)' onMouseOver='Effect.onBtnMouseover(this)' onMouseOut='Effect.onBtnMouseout(this)'><img src='../Icons/icon404a5.gif' width='20' height='20' /><b>删除&nbsp;</b></div></td>");
	arr.push("    <td><div id='FlashDiv"+No+"'></div></td>");
	arr.push("  </tr>");
	arr.push("</table>");
	var div = document.createElement("div");
	div.innerHTML = arr.join('');
	document.body.appendChild(div);	
	var so = new SWFObject("../Framework/Controls/ScriptEditor.swf", "Template"+No, "850", "320", "9", "#ffffff");
	if(isIE){
		so.addParam("wmode", "transparent");
	}
	so.addVariable("Language","html");	
	so.addVariable("AfterInit","afterInit");	
  	so.write("FlashDiv"+No);
	Verify.initCtrl("RefCode"+No);
	No++;
}

function del(event){
	var evt = getEvent(event);
	$(evt.srcElement).getParent("table").parentElement.outerHTML = "";
}

Page.onLoad(function(){
	var flag = false;
	if(parent.Parent.EditFlag){
		var map = parent.Parent.Map;
		for(var k in map){
			var v = map[k];			
			if(k.startWith("RefCode")){
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
});

function afterInit(id){
	if(parent.Parent.EditFlag){
		$(id).setText(parent.Parent.Map[id]);
	}
}

function setAutoWrap(){
	for(var i=No;i>0;i--){
		if($('Template'+No)){
			$('Template'+No).setAutoWrap($('isAutoWrap').checked)	
		}
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
		<div style="display:none">
		<z:tbutton onClick="add()"> <img src="../Icons/icon404a8.gif" width="20" height="20" />新增匹配块</z:tbutton></div>
		</td>
		<td width="65">&nbsp;</td>
		<td align="right">
		<label> <input id="isAutoWrap" type="checkbox" onClick="setAutoWrap();" value="checkbox" checked>自动换行&nbsp;&nbsp;</label></td>
	</tr>
</table>
</body>
</html>
