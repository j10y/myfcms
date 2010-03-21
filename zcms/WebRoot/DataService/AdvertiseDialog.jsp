<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/StyleToolbar.js"></script>
<script type="text/javascript">
var imgIndex = 1;
var textIndex = 1;
var flashIndex = 1;
var BrowseImg = "";
var NowSwf = "";

var url = "http://"
var txt = "";
var path = "";
var src  = "../Images/addpicture.jpg";
var color = "#333333";

Page.onLoad(function(){
	var AdType = $V("hAdType");
	if(AdType==""){
		AdType = $V("AdType");
	}
	$S("AdType",AdType);
	$S("hAdType",AdType);
	changeADType();
	if($V("imgADLinkTarget")=="Old"){
		$("imgADLinkTargetOld").checked = true;
	}else{
		$("imgADLinkTargetNew").checked = true;
		$S("imgADLinkTarget","New");
	}
	var st = new StyleToolbar("TextColor0",$("textContent_0"),"FontColor");
	st.show();
	if(AdType!="code"){
		var content = $V("AdContent");
		if(content!=""){
			var AD = eval('('+content+')');
			var UploadFilePath = $V("UploadFilePath");
			if(AdType=="image"){
				$S("imgADLinkUrl_0",AD.Images[0].imgADLinkUrl);
				$S("imgADAlt_0",AD.Images[0].imgADAlt);
				$S("Img_0_Path",AD.Images[0].ImgPath);
				$("Img_0").src = UploadFilePath+AD.Images[0].ImgPath;
				if(AD.showAlt=="Y"){
					$("showAltBox").checked = true;
					$S("showAlt","Y");
				}else{
					$("showAltBox").checked = false;
					$S("showAlt","N");
				}
				for(var i=1;i<AD.Count;i++){
					url = AD.Images[i].imgADLinkUrl;
					txt = AD.Images[i].imgADAlt;
					path = AD.Images[i].ImgPath;
					src  = UploadFilePath + AD.Images[i].ImgPath;
					var ele = $("Img_"+(imgIndex-1)+"_Path");
					addImageItem(ele);
					changeImgLinkTarget(AD.imgADLinkTarget);
				}
			}else if(AdType=="flash"){
				$S("SwfFile_0_Path",AD.Flashes[0].SwfFilePath);
				for(var i=1;i<AD.Count;i++){
					path = AD.Flashes[i].SwfFilePath;
					var ele = $("SwfFile_"+(flashIndex-1)+"_Path");
					addFlashItem(ele);
				}
			}else if(AdType=="text"){
				$S("textLinkUrl_0",AD.Text[0].textLinkUrl);
				$S("textContent_0",AD.Text[0].textContent);
				$S("TextColor0_FontColor",AD.Text[0].TextColor);
				$S("MaxDisPlay",AD.MaxDisPlay)
				for(var i=1;i<AD.Count;i++){
					url = AD.Text[i].textLinkUrl;
					txt = AD.Text[i].textContent;
					color = AD.Text[i].TextColor;
					var ele = $("textContent_"+(textIndex-1));
					addTextItem(ele);
				}
			}
		}
	}
});

function changeADType(){
	var AdType = $V("AdType");
	$("imageTable").style.display = "none";
	$("flashTable").style.display = "none";
	$("textTable").style.display = "none";
	$("codeTable").style.display = "none";
	if(AdType=="image"){
		$("imageTable").style.display = "";
		if($V("PositionType")=="imagechange"){
			$("showSpan").style.display = "";
		}
	}else if(AdType=="flash"){
		$("flashTable").style.display = "";
	}else if(AdType=="text"){
		$("textTable").style.display  = "";
	}else if(AdType=="code"){
		$("codeTable").style.display  = "";
	}
}

function getCount(){
	var Count = 0;
	var PositionType = $V("PositionType");
	if(PositionType=="banner"){
		Count = 1;
	}else if(PositionType=="fixure"){
		Count = 1;
	}else if(PositionType=="float"){
		Count = 1;
	}else if(PositionType=="couplet"){
		Count = 2;
	}else if(PositionType=="imagechange"&&$V("AdType")=="image"){
		Count = 10;
	}
	return Count;
}

function checkImgPath(){
	var Flag = true;
	var ImgPathes = $N("ImgPath");
	for(var i=0;i<ImgPathes.length;i++){
		if(ImgPathes[i].value==null||ImgPathes[i].value==""){
			Flag = false;
		}
	}
	return Flag;
}

function addImageItem(ele){
	var count = getCount();
	if($N("imgADLinkUrl").length>=count){
		Dialog.alert("已达到版位图片上限");
		return;
	}
	var row=$("imgADTable").insertRow(ele.parentElement.parentElement.rowIndex+1);
	var newtd = document.createElement("td");
	newtd.innerHTML = "&nbsp;&nbsp;链接地址：<input type='text' id='imgADLinkUrl_"+imgIndex+"' name='imgADLinkUrl' value='"+url+"' size='40' verify='NotNull' condition='$V(\"AdType\")==\"image\"'/><br/><br/>&nbsp;&nbsp;文字提示：<input type='text' id='imgADAlt_"+imgIndex+"' name='imgADAlt' size='40' value='"+txt+"'/><input type='hidden' id='Img_"+imgIndex+"_Path' name='ImgPath' value='"+path+"'/>"; 
	newtd.width = "75%";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<img style='cursor:pointer;' title='点击选择或上传图片' onClick='imageBrowse(this);' src='"+src+"' width='120' height='90' id='Img_"+imgIndex+"'>"; 
	newtd.width = "10%";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<img src='../Icons/icon403a20.gif' onClick='addImageItem(this);' style='cursor:pointer;' title='增加图片'/><br/><br/><img src='../Icons/icon403a19.gif' onClick='delItem(this,\"Image\")' style='cursor:pointer;' title='删除'/>"; 
	newtd.width = "15%";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	url = "http://";
	path = "";
	txt = "";
	src = "../Images/addpicture.jpg";
	imgIndex++;
}

function addFlashItem(ele){
	var count = getCount();
	if($N("SwfFilePath").length>=count){
		Dialog.alert("已达到版位动画上限");
		return;
	}
	var row=$("flashADTable").insertRow(ele.parentElement.parentElement.rowIndex+1);
	var newtd = document.createElement("td");
	newtd.innerHTML = "路径："; 
	newtd.width = "15%";
	newtd.align = "right";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<input name='SwfFilePath' id='SwfFile_"+flashIndex+"_Path' type='text' size='15' readonly verify='NotNull' condition='$V(\"AdType\")==\"flash\"' value='"+path+"'/>"; 
	newtd.width = "25%";
	newtd.align = "left";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "上传："; 
	newtd.width = "10%";
	newtd.align = "right";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<input type='file' name='SwfFile' id='SwfFile_"+flashIndex+"' onChange='UpLoadSwf(this);'/>"; 
	newtd.width = "35%";
	newtd.align = "left";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<img src='../Icons/icon403a20.gif' onClick='addFlashItem(this);' style='vertical-align:middle;'/> <img src='../Icons/icon403a19.gif' onClick='delItem(this,\"Flash\");' style='vertical-align:middle;'/>"; 
	newtd.width = "15%";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	path = "";
	flashIndex++;
}

function addTextItem(ele){
	var count = 20;
	if($N("textLinkUrl").length>=count){
		Dialog.alert("已达到文字广告上限");
		return;
	}
	var row=$("textADTable").insertRow(ele.parentElement.parentElement.rowIndex+1);
	var newtd = document.createElement("td");
	newtd.innerHTML = "文本："; 
	newtd.width = "15%";
	newtd.align = "right";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<input name='textContent' id='textContent_"+textIndex+"' type='text' verify='NotNull' condition='$V(\"AdType\")==\"text\"' value='"+txt+"'/>"; 
	newtd.width = "35%";
	newtd.align = "left";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "链接："; 
	newtd.width = "10%";
	newtd.align = "right";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<input name='textLinkUrl' id='textLinkUrl_"+textIndex+"' type='text' value='"+url+"'/>"; 
	newtd.width = "25%";
	newtd.align = "left";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	newtd = document.createElement("td");
	newtd.innerHTML = "<img src='../Icons/icon403a20.gif' onClick='addTextItem(this);' style='vertical-align:middle;'/> <img src='../Icons/icon403a19.gif' onClick='delItem(this,\"Text\");' style='vertical-align:middle;'/>"; 
	newtd.width = "15%";
	newtd.style.borderBottom = "1px dotted #D9D9D9";
	row.appendChild(newtd);
	
	var st = new StyleToolbar("TextColor"+textIndex,$("textContent_"+textIndex),"FontColor");
	st.show();
	$S("TextColor"+textIndex+"_FontColor",color);
	url = "http://"
	txt = "";
	color = "#333333";
	textIndex++;
	$S("textTotal",$N("textLinkUrl").length);
	$("total").innerHTML = $N("textLinkUrl").length;
}

function delItem(ele,type){
	if(type=="Image"){
		if($("imgADTable").rows.length=="1"){
			Dialog.alert("还剩最后一条，不能删除。");
			return;
		}
		$("imgADTable").deleteRow(ele.parentElement.parentElement.rowIndex);
	}else if(type=="Text"){
		if($("textADTable").rows.length=="1"){
			Dialog.alert("还剩最后一条，不能删除。");
			return;
		}
		$("textADTable").deleteRow(ele.parentElement.parentElement.rowIndex);
		$S("textTotal",$N("textLinkUrl").length);
		$("total").innerHTML = $N("textLinkUrl").length;
	}else if(type=="Flash"){
		if($("flashADTable").rows.length=="1"){
			Dialog.alert("还剩最后一条，不能删除。");
			return;
		}
		$("flashADTable").deleteRow(ele.parentElement.parentElement.rowIndex);
	}
}

function imageBrowse(ele){
	BrowseImg = ele.id;
	var diag = new Dialog("Diag4");
	diag.Width = 800;	
	diag.Height = 500;
	diag.Title ="浏览图片库";
	diag.URL = "Resource/ImageLibDialogCover.jsp?Search=Search&SelectType=radio";
	diag.OKEvent = BrowseOK;
	diag.show();
}

function BrowseOK(){
	if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageUpload")){
 		$DW.Tab.getCurrentTab().contentWindow.upload();
	}else if($DW.Tab.getCurrentTab()==$DW.Tab.getChildTab("ImageBrowse")){
	 	$DW.Tab.getCurrentTab().contentWindow.onReturnBack();
	}
}

function onReturnBack(returnID){
	var ImgID = returnID.split(",");
	var dc = new DataCollection();
	dc.add("ImgID",ImgID[0]);
	Server.sendRequest("com.zving.cms.dataservice.Advertise.getImgSrc",dc,function(response){
		$S(BrowseImg+"_Path",response.get("ImgSrc"));
		$(BrowseImg).src = $V("UploadFilePath")+response.get("ImgSrc");
	})
}

function UpLoadSwf(ele){
	NowSwf = ele;
	$("adform").submit();
}

function afterUploadSwf(returnStr,SwfFileStr){
	if(returnStr!=""){
		Dialog.alert(returnStr);
		return;
	}else{
		$S(NowSwf.id+"_Path",SwfFileStr);
		NowSwf.parentElement.innerHTML = "<input type='file' name='SwfFile' id='"+NowSwf.id+"' onChange='UpLoadSwf(this);'/>";
	}
}

function changeImgLinkTarget(type){
	$S("imgADLinkTarget",type);
	if(type=="New"){
		$("imgADLinkTargetNew").checked = true;
		$("imgADLinkTargetOld").checked = false;
	}else{
		$("imgADLinkTargetNew").checked = false;
		$("imgADLinkTargetOld").checked = true;
	}
}

function changeShowAlt(){
	var showalt = $V("showAlt");	
	var showaltbox = $("showAltBox");
	if(showalt=="Y"){
		showaltbox.checked = false;
		$S("showAlt","N");
	}else{
		showaltbox.checked = true;
		$S("showAlt","Y");
	}
}

</script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.Advertise.DialogInit">
<div style="display:none"><iframe name="formTarget" src="javascript:void(0)"></iframe></div>
<form id="adform" method="post" target="formTarget" enctype="multipart/form-data" action="AdvertiseUpload.jsp">
	<table width="100%" border="0" cellpadding="4" cellspacing=""
		bordercolor="#DEDEDC" style="border-collapse:collapse;">
		<tr>
			<td width="240" height="12"></td>
			<td width="921"></td>
		</tr>
		<tr>
			<td align="right" width="240">广告标题：</td>
			<td width="921"><input type="text" name="AdName" size="45" id="AdName" value="${AdName}" verify="广告标题|NotNull" />
            <input type="hidden" id="ID" name="ID" value="${ID}" /></td>
		</tr>
		<tr>
			<td align="right" valign="top" width="240">所属版位：</td>
			<td width="921"><b style="color:#F60;">${PositionName}</b>&nbsp;&nbsp;[${PositionTypeName}]
            <input type="hidden" id="PositionID" name="PositionID" value="${PositionID}" />
            <input type="hidden" id="PositionType" name="PositionType" value="${PositionType}" />
            </td>
		</tr>
		<tr>
			<td align="right" valign="top">广告类型：</td>
			<td>
			<z:select name="AdType" id="AdType" onChange="changeADType();"> ${AdTypeOptions}</z:select>
            <input type="hidden" id="hAdType" name="hAdType" value="${AdType}" />
            <input type="hidden" id="UploadFilePath" name="UploadFilePath" value="${UploadFilePath}" />
			</td>
		</tr>
        <tr>
			<td align="right" valign="top" width="240">上线时间：</td>
			<td width="921"><input name="StartDate" id="StartDate" value="${StartDate}" type="text" size="14" ztype="Date"/>
	  <input name="STime" id="STime" value="${STime}" type="text" size="10" ztype="Time"/></td>
		</tr>
        <tr>
			<td align="right" valign="top" width="240">下线时间：</td>
			<td width="921"><input name="EndDate" id="EndDate" value="${EndDate}" type="text" size="14" ztype="Date"/> 
	  <input name="ETime" id="ETime" value="${ETime}" type="text" size="10" ztype="Time"/></td>
		</tr>
		<tr>
			<td valign="top" colspan="2">
			<table width='100%' border='0' cellpadding='4' cellspacing='0' id="imageTable" style="display:none;">
				<tr>
					<td colspan='2' align='center' bgcolor="#E6F8FF"><b>内容设置--图片</b></td>
				</tr>
				<tr>
					<td colspan="2">
                    <div style="border:#D9D9D9 dotted 1px; border-bottom:;">
                    <table id="imgADTable" width="100%" cellpadding='2'>
                    	<tr>
                            <td width="75%" style="border-bottom:1px dotted #D9D9D9;">
                            &nbsp;&nbsp;链接地址：<input type="text" id="imgADLinkUrl_0" name="imgADLinkUrl" value="${imgADLinkUrl}" size="40" verify="NotNull" condition="$V('AdType')=='image'"/><br/><br/>
                            &nbsp;&nbsp;文字提示：<input type="text" id="imgADAlt_0" name="imgADAlt" value="" size="40"/><input type="hidden" id="Img_0_Path" name="ImgPath" />
                            </td>
                            <td width="10%" style="border-bottom:1px dotted #D9D9D9;">
                            <img style="cursor:pointer;" title="点击选择或上传图片" onClick="imageBrowse(this);" src="../Images/addpicture.jpg" width="120" height="90" id="Img_0">
                            </td>
                            <td width="15%" style="border-bottom:1px dotted #D9D9D9;">
                            <img src="../Icons/icon403a20.gif" onClick="addImageItem(this);" style="cursor:pointer;" title="增加图片"/><br/><br/>
                            <img src="../Icons/icon403a19.gif" onClick="delItem(this,'Image')" style="cursor:pointer;" title="删除"/></td>
                        </tr>
                    </table>
                    </div>
                  </td>
				</tr>
				<tr>
					<td width="27%" align='right'>链接目标：</td>
					<td width="73%" align='left'>
                    <label for="imgADLinkTargetNew">
                    <input name="imgADLinkTargetNew" type="radio" id="imgADLinkTargetNew" onClick="changeImgLinkTarget('New');" />新窗口
                    </label>&nbsp;&nbsp; 
                    <label for="imgADLinkTargetOld">
                    <input name="imgADLinkTargetOld" type="radio" id="imgADLinkTargetOld" onClick="changeImgLinkTarget('Old');" /> 原窗口</label>
                    <input type="hidden" id="imgADLinkTarget" name="imgADLinkTarget" value="${imgADLinkTarget}" />
                    <span id="showSpan" style="padding-left:20px;display:none;"><label for="showAltBox"><input type="checkbox" id="showAltBox" onClick="changeShowAlt();" checked/>显示文字提示</label></span><input type="hidden" id="showAlt" name="showAlt" value="${showAlt}"/>
                    </td>
				</tr>
			</table>
            <table id='flashTable' width='100%' border='0' cellpadding='4' cellspacing='0' style="display:none;">
				<tr>
					<td colspan='2' align='center' bgcolor="#E6F8FF"><b>内容设置--动画</b></td>
				</tr>
				<tr>
					<td align='center'>
                    <div style="border:#D9D9D9 dotted 1px; border-bottom:none;">
                    <table width="100%" cellpadding="2" cellspacing="0" id="flashADTable">
                        <tr>
                            <td width="15%" align='right' style="border-bottom:1px dotted #D9D9D9;">路径：</td>
                            <td width="25%" align='left' style="border-bottom:1px dotted #D9D9D9;">
                            <input name="SwfFilePath" id="SwfFile_0_Path" type="text" size="15" readonly verify="NotNull" condition="$V('AdType')=='flash'"/></td>
                            <td width="10%" align='right' style="border-bottom:1px dotted #D9D9D9;">上传：</td>
                            <td width="35%" align='left' style="border-bottom:1px dotted #D9D9D9;"><input type="file" name="SwfFile" id="SwfFile_0" onChange="UpLoadSwf(this);"/></td>
                            <td width="15%" style="border-bottom:1px dotted #D9D9D9;">
                            <img src="../Icons/icon403a20.gif" onClick="addFlashItem(this);" style="vertical-align:middle"/>
                            <img src="../Icons/icon403a19.gif" onClick="delItem(this,'Flash');" style="vertical-align:middle"/>
                            </td>
                        </tr>
                    </table>
                    </div>
                    </td>
                </tr>
			</table>
            <table id='textTable' width='100%' border='0' cellpadding='4' cellspacing='0' style="display:none;">
				<tr>
					<td colspan='3' align='center' bgcolor="#E6F8FF"><b>内容设置--文字</b></td>
				</tr>
				<tr>
					<td align='center' colspan="2">
                    <div style="border:#D9D9D9 dotted 1px; border-bottom:none;">
					<table width="100%" cellpadding="2" cellspacing="0" id="textADTable">
						<tr>
							<td width="15%" align="right" style="border-bottom:1px dotted #D9D9D9;">文本：</td>
							<td width="35%" align="left" style="border-bottom:1px dotted #D9D9D9;">
                            <input name="textContent" id="textContent_0" type="text" verify="NotNull" condition="$V('AdType')=='text'"/></td>
							<td width="10%" align="right" style="border-bottom:1px dotted #D9D9D9;">链接：</td>
							<td width="25%" align="left" style="border-bottom:1px dotted #D9D9D9;">
                            <input name="textLinkUrl" id="textLinkUrl_0" value="${textLinkUrl}" type="text" /></td>
							<td width="15%" style="border-bottom:1px dotted #D9D9D9;">
                            <img src="../Icons/icon403a20.gif" onClick="addTextItem(this);" style="vertical-align:middle"/>
                            <img src="../Icons/icon403a19.gif" onClick="delItem(this,'Text');" style="vertical-align:middle"/>
                            </td>
						</tr>
					</table>
                    </div>
					</td>
				</tr>
                <tr>
					<td width="73%" align="right"><input type="hidden" id="textTotal" name="textTotal"/>共有<b id="total" style="color:#FF6600">1</b>条</td>
					<td width="27%" align="right">最多显示 <input name="MaxDisPlay" id="MaxDisPlay" type="text" value="10" size="3" verify="条数|Number" /> 条&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
            <table width='100%' border='0' cellpadding='4' cellspacing='0' id="codeTable" style="display:none;">
				<tr>
					<td colspan='2' align='center' bgcolor="#E6F8FF"><b>内容设置--代码</b></td>
				</tr>
                <tr>
               		<td>
                    <div style="border:#D9D9D9 dotted 1px;">
					<table width="100%" cellpadding="2" cellspacing="0" id="codeADTable">
                        <td width="15%" align='right'>代码内容：</td>
                        <td width="85%" align='left'><textarea id="codeContent" name="codeContent" cols="60" rows="4" verify="NotNull" condition="$V('AdType')=='code'">${AdContent}</textarea></td>
                    </tr>
                    </table>
                    </div>
                    </td> 
                </tr>
			</table>
			</td>
		</tr>
	</table>
    <input type="hidden" id="AdContent" name="AdContent" value="${AdContent}"/>
    <input type="hidden" id="IsOpen" name="IsOpen" value="${IsOpen}"/>
    <input type="hidden" id="IsHitCount" name="IsHitCount" value="${IsHitCount}"/>
    <input type="hidden" id="HitCount" name="HitCount" value="${HitCount}"/>
</form>
</z:init>
</body>
</html>
