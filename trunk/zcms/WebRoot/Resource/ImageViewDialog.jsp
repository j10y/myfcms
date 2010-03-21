<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
html,body {
	scrollbar-arrow-color: #888;
	scrollbar-3dlight-color: #ccc;
	scrollbar-shadow-color: #bbb;
	scrollbar-face-color: #eee;
	scrollbar-darkshadow-color: #eee;
	scrollbar-highlight-color: #fff;
	scrollbar-track-color: #eee;
}
html{height:100%;_height: auto;}
body{
	height:100%;
	color: #ccc;
	background: transparent;
}
.tagList{border: none 0;}
.tagList a{border:#333 1px solid;background:transparent; border-bottom:none; color:#B6B966; height:auto;}
.tagList a:hover{background:#444; border-color:#444}
.tagList a.cur,.taglist a.cur:visited{background:#444;border:#444 1px solid; border-bottom:none; color: #FFF433; height:auto; margin-bottom:0;}

a.btn:link,a.btn:visited {
	display:block; width:75px; height:20px; text-align:center; line-height:19px; background:url(../Framework/Images/nextprev.gif) no-repeat; color:#cc6;
	text-decoration:none;
}
a.btn:hover {
	display:block; width:75px; height:20px; text-align:center; line-height:19px; background:url(../Framework/Images/nextprev.gif) no-repeat; color:#F3722C; text-decoration:none;
}

a.btn1:link,a.btn1:visited, a.btn1:hover{
	color:#666; cursor:not-allowed;
}

a.cancelbtn:link,a.cancelbtn:visited {
	display:block; width:110px; height:20px; text-align:center; line-height:19px; background:url(../Framework/Images/cancel.gif) no-repeat; color:#FFF433;
	text-decoration:none;
}
a.cancelbtn:hover {
	display:block; width:110px; height:20px; text-align:center; line-height:19px; background:url(../Framework/Images/cancel.gif) no-repeat; color:#FF0000; text-decoration:none;
}
</style>
<script src="../Framework/Main.js"></script>
<script language=javascript> 
Page.onLoad(function(){
	if(!$V("cured")){
		$S("cured","1");
	}
	var cured = $V("cured");
	var bk = $V("backID");
	var nt = $V("nextID");
	$(cured).className="cur";
	$("img"+cured).style.display="block"
	if(bk==""){
		$("backButton").addClassName("btn1");
		$("backButton").onclick=null;
	}else{
		$("backButton").removeClassName("btn1");
		$("backButton").onClick="backButton()";
	}
	if(nt==""){
		$("nextButton").addClassName("btn1");
		$("nextButton").onclick=null;
	}else{
		$("nextButton").removeClassName("btn1");
		$("nextButton").onClick="backButton()";
	}
});

function iframeAutoFit()
{
	try
	{
		if(window!=parent)
		{
					var doc=parent.document;
					var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
					var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;
					window.frameElement.parentNode.style.width = cw +"px";
					window.frameElement.parentNode.style.height = ch +"px";
					window.frameElement.style.width = cw +"px";
					window.frameElement.style.height = ch +"px";
			resetzoombgdiv();
		}
	}
	catch (ex){alert("脚本无法跨域操作！");} 
}

//if(document.attachEvent)  window.attachEvent("onload",  iframeAutoFit);   
//else  window.addEventListener('load',  iframeAutoFit,  false);   
   
function resetzoombgdiv(){
var pw = window.top;
var doc = pw.document;
var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);;
var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);;
var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;
pw.$("_zoomBGDiv").style.width=sw;
pw.$("_zoomBGDiv").style.height=sh;
}

function setImgContainerH(){
var pw = window.top;
var doc = pw.document;
var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);;
var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);;
var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;
$("imgoriginal").style.height=(ch-80)+"px";
}
if(document.attachEvent)  window.attachEvent("onload",  setImgContainerH);   
else  window.addEventListener('load',  setImgContainerH,  false);   

function onTabClick(num){
	var arr = $N("tab");
	for(var i=0;i<arr.length;i++){
		$(arr[i].id).className="block";
		$("img"+arr[i].id).style.display="none";
	}
	$(num.id).className="cur";
	$S("cured",num.id);
	$("img"+num.id).style.display="block";
}

function backButton(){
	window.location = "ImageViewDialog.jsp?ID="+$V("backID")+"&cured="+$V("cured");
}

function nextButton(){
	window.location = "ImageViewDialog.jsp?ID="+$V("nextID")+"&cured="+$V("cured");
}

function del(){
	var imageID = $V("imageID");
	Dialog.confirm("你确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",imageID);
		Server.sendRequest("com.zving.cms.resource.Image.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.top.$("_MainArea").contentWindow.DataList.loadData("dg1");
					if($V("nextID")!=""){
						window.location = Server.ContextPath+"Resource/ImageViewDialog.jsp?ID="+$V("nextID")+"&cured="+$V("cured");
					}else if($V("backID")!=""){
						window.location = Server.ContextPath+"Resource/ImageViewDialog.jsp?ID="+$V("backID")+"&cured="+$V("cured");
					}else{
						window.top.$E.hide('_zoomdiv');
					}
				}
			});
		});
	});
}
</script>
</head>
<body>
<z:init method="com.zving.cms.resource.Image.initViewDialog">
	<form id="form2"><input type="hidden" id="backID" name="backID"
		value="${backID}"> <input type="hidden" id="nextID"
		name="nextID" value="${nextID}"> <input type="hidden"
		id="imageID" name="imageID" value="${imageID}"> <input
		type="hidden" id="cured" name="cured" value="${cured}"> <input
		type="hidden" id="count" name="count" value="${Count}">
	<table width="80%" height="80" border="0" cellspacing="0"
		cellpadding="0" align="center">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center" valign="bottom"
				style=" border-bottom:3px #444 solid;">
			<table width="900" border="0" align="center" cellspacing="0"
				cellpadding="0">
				<tr align="center">
					<td align="left">
					<div class="tagList"
						style=" width:600; margin:0; padding-left:0; border:none; text-align:center">
					${XunHuan1} <a href="#;" hidefocus id="original" name="tab"
						onClick="onTabClick(this);">原图<br>
					(${Width}×${Height})</a></div>
					</td>
					<td align="left" width="80"><a href="#" class="btn"
						onClick="del()">删除</a></td>
					<td align="right">&nbsp;</td>
					<td align="left" width="80"><a href="#" id="backButton"
						class="btn" hidefocus onClick="backButton();">&lt;上一张</a></td>
					<td align="left" width="80"><a href="#" id="nextButton"
						class="btn" hidefocus onClick="nextButton();">下一张&gt;</a></td>
					<td align="right">&nbsp;</td>
					<td width="110"><a href="#"
						onClick="window.top.$E.hide('_zoomdiv')" class="cancelbtn"
						hidefocus>&lt;&lt;返回图片列表</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table width="80%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		${XunHuan2}
	</table>
	<div id="imgoriginal" name="Image"
		style="display:none; overflow: auto; height:500px; width:100%;">
	<div style="padding:0 20px 20px 0;text-align:center"><img
		src="..${Alias}${Path}${SrcFileName}?${ModifyTime}"></div>
	</div>
	</form>
</z:init>
</body>
</html>
