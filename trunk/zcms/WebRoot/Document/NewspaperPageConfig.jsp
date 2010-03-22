<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style type="text/css">

/* Required CSS classes: must be included in all pages using this script */

/* Apply the element you want to drag/resize */
.drsElement {
 position: absolute;
 border: 1px solid #333;
}

/*
 The main mouse handle that moves the whole element.
 You can apply to the same tag as drsElement if you want.
*/
.drsMoveHandle {
 height: 20px;
 border-bottom: 1px solid #666;
 cursor: move;
 background-color: #99CCFF;
 filter:alpha(opacity=50);
 opacity:0.5;
}

/*
 The DragResize object name is automatically applied to all generated
 corner resize handles, as well as one of the individual classes below.
*/
.dragresize {
 position: absolute;
 width: 8px;
 height: 8px;
 font-size: 1px;
 border: 1px solid #333;
}

/*
 Individual corner classes - required for resize support.
 These are based on the object name plus the handle ID.
*/
.dragresize-tl {
 top: -8px;
 left: -8px;
 cursor: nw-resize;
}
.dragresize-tm {
 top: -8px;
 left: 50%;
 margin-left: -4px;
 cursor: n-resize;
}
.dragresize-tr {
 top: -8px;
 right: -8px;
 cursor: ne-resize;
}

.dragresize-ml {
 top: 50%;
 margin-top: -4px;
 left: -8px;
 cursor: w-resize;
}
.dragresize-mr {
 top: 50%;
 margin-top: -4px;
 right: -8px;
 cursor: e-resize;
}

.dragresize-bl {
 bottom: -8px;
 left: -8px;
 cursor: sw-resize;
}
.dragresize-bm {
 bottom: -8px;
 left: 50%;
 margin-left: -4px;
 cursor: s-resize;
}
.dragresize-br {
 bottom: -8px;
 right: -8px;
 cursor: se-resize;
}

</style>

<script src="../Framework/Main.js"></script>
<script type="text/javascript" src="dragresize.js"></script>
<script>
var isSelect = false;
var rect;
var downX = 0;
var downY = 0;
var i = 0;
var mouseX2=downX;
var mouseY2=downY;

Page.onLoad(function(){
	rect = $("rect");
	rect.style.width = 0;
	rect.style.height = 0;
	rect.style.visibility = 'hidden';
	rect.style.zIndex = 1000;
});

function down(evt){
	 //alert("down");
	 evt = getEvent(evt);
   isSelect = true;
   downX = evt.clientX;
   downY = evt.clientY; 
   rect.style.left = downX;
   rect.style.top = downY;
}

function up(evt){
	 evt = getEvent(evt);
   isSelect = false;
   rect.style.visibility = 'hidden';
   
	 var div = document.createElement('div');
	 var handle = document.createElement('div');
	 handle.className = "drsMoveHandle";
	 
	 div.id = "_DragProxy"+(i++);

	 div.style.zIndex = 999;
	 div.style.top =  rect.style.top;		
	 div.style.left =  rect.style.left;
	 div.appendChild(handle);
	 	
	 document.body.appendChild(div) ; 
	 div.style.border="2px solid #0000FF";
	 
	 div.style.width = rect.style.width;
   div.style.height = rect.style.height;
   div.className="drsElement";

   var dragresize = new DragResize('dragresize',{
   	minWidth: 50, minHeight: 50, minLeft: 20, minTop: 20, maxLeft: 1000, maxTop: 200 
   });
	 dragresize.isElement = function(ele){
		 if (ele.className && ele.className.indexOf('drsElement') > -1) return true;
	 };
	 dragresize.isHandle = function(ele){
	 if (ele.className && ele.className.indexOf('drsMoveHandle') > -1) return true;
	 };

   dragresize.ondragfocus = function() { };
   dragresize.ondragstart = function(isResize) {};
   dragresize.ondragmove = function(isResize) {
	  $("imagePic").onmousemove=null;
   };
	 dragresize.ondragend = function(isResize) {
	 };               
	 dragresize.ondragblur = function() {
		$("imagePic").onmousemove=function(){move(event)};
	 };

   dragresize.apply(document);
}

function move(evt){
	  evt = getEvent(evt);
    mouseX2 = evt.clientX;
    mouseY2 = evt.clientY;
    
    rect.style.width = Math.abs( mouseX2 - downX );
    rect.style.height = Math.abs( mouseY2 - downY );    
    
    if(isSelect){
       rect.style.visibility = 'visible';
        // A part
        if( mouseX2 < downX && mouseY2 < downY ){
            rect.style.left = mouseX2;
            rect.style.top = mouseY2 ;    
        }
        // B part
        if( mouseX2 > downX && mouseY2 < downY){
            rect.style.left = downX ;
            rect.style.top = mouseY2;    
        }
        // C part
        if( mouseX2 < downX && mouseY2 > downY){
            rect.style.left = mouseX2;
            rect.style.top = downY ;    
        }
        // D part
        if( mouseX2 > downX && mouseY2 > downY ){
            rect.style.left = downX ;
            rect.style.top = downY;
        }
    }
    
	  evt.cancelBubble = true;
    evt.returnValue = false; 
}

</script>
</head>
<body>
<div id='rect'
	style='border:1px solid #0000FF;position:absolute;background-color: #99CCFF;filter:alpha(opacity=50);opacity:0.5;'>
</div>
<input type="hidden" id="CatalogID">
<input type="hidden" id="ListType" value='${ListType}'>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="blockTable">
			<tr>
			</tr>
			<tr>
				<td style="padding:4px 8px;"><img src="../Icons/icon018a1.gif" />图片</td>
				<td style="padding:8px 10px;" class="blockTd"><img
					src="../Icons/icon018a1.gif" />文档列表</td>
			</tr>
			<tr>
				<td width="65%" valign="top"
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;"
					align="center">

				<div
					style="overflow:auto; height:100% border:#666 1px solid; background-color:#999999; text-align:center; padding:10px"
					id="PaperImage"><img id="imagePic"
					src="Images/2008070301_brief.jpg" style="border:#000 1px solid "
					galleryimg="no" onMouseDown='down(event)' onMouseUp='up(event)'
					onMouseMove='move(event)'></div>
				</td>
				<td width="35%" valign="top"
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.cms.document.ArticleList.dg1DataBind" size="15"
					page="false">
					<table width="100%" height="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="13%" height="30" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="35%" sortfield="title"><b>标题</b></td>
							<td width="27%"><strong>坐标</strong></td>
							<td width="15%"><strong>预览</strong></td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td>${Title}</td>
							<td>${Author}</td>
							<td><a href="./Preview.jsp?ArticleID=${ID}" target="_blank">预览</a></td>
						</tr>
					</table>
				</z:datagrid></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
