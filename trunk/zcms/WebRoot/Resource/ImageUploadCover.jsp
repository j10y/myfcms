<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%@page import="com.zving.cms.resource.ConfigImageLib"%>
<%@page import="com.zving.framework.utility.Mapx"%>
<%@page import="com.zving.FCKeditor.uploader.SimpleUploaderServlet"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
.ErrorMsg {
background:#FFF2E9   scroll 5px;
border:1px solid #FF6600;
color:#000000;
padding:5px 5px 5px 25px;
}

#preview_wrapper{   
    display:inline-block;   
    width:125px;   
    height:340px;   
}   
.preview_fake{ /* 该对象用户在IE下显示预览图片 */   
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);   
}
#preview_size_fake{ /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途 */
position:absolute;
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);     
}   

</style>
<%
	String CatalogID = request.getParameter("CatalogID");
	QueryBuilder qb = null;
	if(CatalogID!=null&&!"".equals(CatalogID)&&!"null".equals(CatalogID)){
		qb = new QueryBuilder("select id,name from zccatalog where type=4 and id =?",CatalogID);
	}else{
		qb = new QueryBuilder("select id,name from zccatalog where type=4 and siteid=?",Application.getCurrentSiteID());
	}
	DataTable dt = qb.executePagedDataTable(1,0);
	String value = dt.getString(0,0);
	String text = dt.getString(0,1);
%>
<script src="../Framework/Main.js"></script>
<script type="text/javascript"><!--
Page.onLoad(function(){
	Selector.setValueEx("SelectCatalogID",'<%=value%>','<%=text%>');
});

<%
	List extList = (List)SimpleUploaderServlet.allowedExtensions.get(SimpleUploaderServlet.IMAGE);
%>

var extsStr = '<%=StringUtil.join(extList)%>';
var exts = extsStr.split(",");

function upload(){
	var sid = $V("SelectCatalogID");
	var flag = false;
	var count = 5;
	for(var i=1;i<=count;i++){
			var imgName = $V("File"+i);
			var ext = imgName.substring(imgName.lastIndexOf(".")+1).toLowerCase();
			if(imgName==""){
				continue;
			}
			flag=false;
			for(var j=0; j<exts.length; j++) {
				if(ext.trim()==exts[j].toLowerCase().trim()) {
					flag = true;
				}
			}
			if(!flag) {
				Dialog.alert("图片上传不支持"+ext+"文件，请重新选择！");
				return;
			}
		}
	if(flag){
	  	if(sid==null){
		Dialog.alert("您没有选择 图片 分类，请选择。");
		return;
		}
	    setCatalogID();
		$S('OtherIDs',$NV('OtherID'));
	    $E.disable(window.parent.Parent.$D.CancelButton);
	    $E.disable(window.parent.Parent.$D.OKButton);
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

function getExtname(filename){
	var pos = filename.lastIndexOf("\\");
	return (filename.substr(pos+1));
}

function setNameInfo(ele){
	var name = getExtname(ele.value);
	name = name.substring(0,name.lastIndexOf("."));
	var id =ele.id;
	$S(id+"Name",name);
	$S(id+"Info",name);
}

function doPreview(sender){   
    var flag = 0;
    for(var i=0; i<exts.length; i++) {
	  	if(sender.value.toLowerCase().trim().endsWith("." + exts[i].toLowerCase().trim())) {
			flag = 1;
	  	}
	}
    if(flag == 0) {
    	Dialog.alert('图片格式无效！');
        return false;   
	}

	if(!sender.value.toLowerCase().trim().endsWith(".zip")) {
	    var objPreview = document.getElementById( 'preview'+sender.id );
	    var objPreviewFake = document.getElementById( 'preview_fake'+sender.id );   
	    var objPreviewSizeFake = document.getElementById( 'preview_size_fake' );   
	       
	    if( sender.files &&  sender.files[0] ){
	        objPreview.style.display = 'block';   
	        objPreview.style.width = 'auto';   
	        objPreview.style.height = 'auto';   
	           
	        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
	        objPreview.src = sender.files[0].getAsDataURL();       
	    }else if( objPreviewFake.filters ){    
	        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果   
	        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决   
	           
	        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
	        sender.select();   
	        var imgSrc = document.selection.createRange().text;   
	           
	        objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
	        objPreviewSizeFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
	        autoSizePreview( objPreviewFake,    
	        objPreviewSizeFake.offsetWidth, objPreviewSizeFake.offsetHeight );   
	        objPreview.style.display = 'none';
	    }
	} 
}   
  
function onPreviewLoad(sender){   
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );   
}   
  
function autoSizePreview( objPre, originalWidth, originalHeight ){
    var zoomParam = clacImgZoomParam( 120, 120, originalWidth, originalHeight );  
    objPre.style.width = zoomParam.width + 'px';   
    objPre.style.height = zoomParam.height + 'px';   
    objPre.style.marginTop = zoomParam.top + 'px';   
    objPre.style.marginLeft = zoomParam.left + 'px';   
}   
  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = { width:width, height:height, top:0, left:0 };   
    if( width>maxWidth || height>maxHeight ){   
        rateWidth = width / maxWidth;   
        rateHeight = height / maxHeight;   
           
        if( rateWidth > rateHeight ){   
            param.width =  maxWidth;   
            param.height = height / rateWidth;   
        }else{   
            param.width = width / rateHeight;   
            param.height = maxHeight;   
        }   
    }   
       
    param.left = (maxWidth - param.width) / 2;   
    param.top = (maxHeight - param.height) / 2;   
       
    return param;   
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
	window.parent.Parent.onReturnBack(returnID);
	try {
	  window.parent.Dialog.close();
  }catch(ex){}
}

function onImageOver(ele){
	ele.style.backgroundColor='#fffabf';
}

function onImageOut(ele){
	ele.style.backgroundColor='';
}

function clickAbbrImageFlag(){
	if($("AbbrImageFlag").checked){
		$("AbbrImagesLabel").show();
	}else{
		$("AbbrImagesLabel").hide();
		$("AbbrImagesFlag").checked=false;
		$("AbbrImagesTable").hide();
	}
}

function clickAbbrImagesFlag(){
	if($("AbbrImagesFlag").checked){
		$("AbbrImagesTable").show();
	}else{
		$("AbbrImagesTable").hide();
	}
}

function setCatalogID(){
	$S("CatalogID",$V("SelectCatalogID"));
}

function browse(){
	var diag = new Dialog("Diag_ImageCheckboxList");
	diag.Width = 300;
	diag.Height = 400;
	diag.Title = "图片分类列表";
	diag.URL = "Resource/ImageLibCheckboxList.jsp";
	diag.OKEvent = browseSave;
	diag.show();
}

function browseSave(){
	var arr = $DW.$NV("ID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选中图片分类！");
		return;
	}
	var OtherLibTable = $("OtherLibTable");
	var sb = [];
	var catalogid=$V("SelectCatalogID");
	for(var i=0;i<arr.length;i++){
		if(arr[i]==catalogid){
			continue;
		}
		sb.push("<tr><td><input type='hidden' name='OtherID' value='"+arr[i]+"'>"+$DW.$("span_"+arr[i]).innerHTML+"</td><td><img src='../Framework/Images/icon_cancel.gif' title='删除' onclick='deleteOtherID(this);' /></td></tr>");	
	}
	OtherLibTable.outerHTML="<table width='100%' border='1' cellspacing='0' id='OtherLibTable' bordercolor='#eeeeee'>"+sb.join('')+"</table>";
	$D.close();
}

function deleteOtherID(ele){
	ele.parentElement.parentElement.parentElement.deleteRow(ele.parentElement.parentElement.rowIndex);
	return false;
}
--></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div style="display:none"><iframe name="formTarget"
	src="javascript:void(0)"></iframe></div>
<form enctype="multipart/form-data" id="form2" action="../Editor/filemanager/upload/simpleuploader.jsp" method="post" target="formTarget">
<input type="hidden" id="FileType" name="FileType" value="Image">
<input type="hidden" id="CatalogID" name="CatalogID" value="">
<input type="hidden" id="OtherIDs" name="OtherIDs" value="">
<table width="760" cellpadding="3" cellspacing="0">
	<tr>
		<td width="75%" valign="top">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td style="height:190px" valign="top">
				<fieldset><legend>图片上传:(支持<%=StringUtil.join(extList)%>文件上传)</legend>
				<table id="imagetable0" style="display:" width="100%"
					cellpadding="2" cellspacing="0">
					<tr>
						<td width="6%" align="right"><label> </label></td>
						<td width="34%"><label> 图片浏览</label></td>
						<td width="30%">图片名称</td>
						<td width="30%">图片描述</td>
					</tr>
					<tr>
						<td width='6%' align="right"><label>1:</label></td>
						<td><input name='File1' id='File1' type='file' value=''
							size='30' onChange="setNameInfo(this);doPreview(this);"></td>
						<td><input name="File1Name" id="File1Name" type="text"
							value=""></td>
						<td><input name="File1Info" id="File1Info" type="text"
							value=""></td>
					</tr>
					<tr style="display:none">
						<td align="right"><label>2:</label></td>
						<td><input name='File2' id='File2' type='file' value=''
							size='30' onChange="setNameInfo(this);doPreview(this);"></td>
						<td><input name="File2Name" id="File2Name" type="text"
							value=""></td>
						<td><input name="File2Info" id="File2Info" type="text"
							value=""></td>
					</tr>
					<tr style="display:none">
						<td align="right"><label>3:</label></td>
						<td><input name='File3' id='File3' type='file' value=''
							size='30' onChange="setNameInfo(this);doPreview(this);"></td>
						<td><input name="File3Name" id="File3Name" type="text"
							value=""></td>
						<td><input name="File3Info" id="File3Info" type="text"
							value=""></td>
					</tr>
					<tr style="display:none">
						<td align="right"><label>4:</label></td>
						<td><input name='File4' id='File4' type='file' value=''
							size='30' onChange="setNameInfo(this);doPreview(this);"></td>
						<td><input name="File4Name" id="File4Name" type="text"
							value=""></td>
						<td><input name="File4Info" id="File4Info" type="text"
							value=""></td>
					</tr>
					<tr style="display:none">
						<td align="right"><label>5:</label></td>
						<td><input name='File5' id='File5' type='file' value=''
							size='30' onChange="setNameInfo(this);doPreview(this);"></td>
						<td><input name="File5Name" id="File5Name" type="text"
							value=""></td>
						<td><input name="File5Info" id="File5Info" type="text"
							value=""></td>
					</tr>
				</table>
				</fieldset>
				</td>
			</tr>
			<tr>
				<td style="height:190px">
				<fieldset><legend> <label>参数设置</label></legend>
				<table width="100%" cellpadding="2" cellspacing="0">
					<tr>
						<td width="53%" align="left" valign="top">
						<table width="100%" cellpadding="2" cellspacing="0">
							<tr>
								<td width="28%" align="right">所属主分类:</td>
								<td width="72%"><z:select id="SelectCatalogID"
									style="width:80px" onChange="setCatalogID()" listWidth="200"
									listHeight="300" listURL="Resource/ImageLibSelectList.jsp"></z:select>
								</td>
							</tr>
							<% 
                  Mapx map = ConfigImageLib.getImageLibConfig(Application.getCurrentSiteID()); 
                  int count = Integer.parseInt(map.get("Count").toString());
                  String HasWaterMarkChecked ="";
                  String HasWaterMark ="0";
                  if("1".equals(map.get("HasWaterMark"))){
                	  HasWaterMarkChecked = "checked";
                	  HasWaterMark ="1";
                  }
                  %>
							<tr>
								<td align="right"><label>原图水印:</label></td>
								<td><input type="hidden" id="HasWaterMark"
									name="HasWaterMark" value="<%=HasWaterMark%>"> <input
									type="checkbox" id="chHasWaterMark" name="chHasWaterMark"
									<%=HasWaterMarkChecked %>
									onClick="if(this.checked)$S('HasWaterMark','1');else $S('HasWaterMark','0')">
								<input type="hidden" id="Count" name="Count" value="<%=count%>"></td>
							</tr>
							<tr id="AbbrImagesTable">
								<td align="right"><label>缩略图:</label></td>
								<td>
								<%
						for(int i =1 ;i<=count;i++){
							String HasAbbrImageChecked = "";
							String HasAbbrImage = "0";
							if("1".equals(map.get("HasAbbrImage"+i))){
								HasAbbrImageChecked = "checked";
								HasAbbrImage ="1";
							}
						%> <label> <input type="hidden" id="HasAbbrImage<%=i%>"
									name="HasAbbrImage<%=i%>" value="<%=HasAbbrImage%>"> <input
									type="checkbox" id="boxHasAbbrImage<%=i%>"
									name="boxHasAbbrImage<%=i%>" <%=HasAbbrImageChecked %>
									onClick="if(this.checked)$S('HasAbbrImage<%=i%>','1');else $S('HasAbbrImage<%=i%>','0')"><%=i %>&nbsp;&nbsp;&nbsp;</label>
								<%}%>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" nowrap>缩略图水印:</td>
								<td>
								<%
						for(int i =1 ;i<=count;i++){
							HasWaterMarkChecked = "";
							HasWaterMark = "0";
							if("1".equals(map.get("HasWaterMark"+i))){
								HasWaterMarkChecked = "checked";
								HasWaterMark = "1";
							}
						%> <label> <input type="hidden" id="HasWaterMark<%=i%>"
									name="HasWaterMark<%=i%>" value="<%=HasWaterMark%>"> <input
									type="checkbox" id="chHasWaterMark<%=i%>"
									name="chHasWaterMark<%=i%>" <%=HasWaterMarkChecked %>
									onClick="if(this.checked)$S('HasWaterMark<%=i%>','1');else $S('HasWaterMark<%=i%>','0')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<%}%>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">缩略图宽:</td>
								<td>
								<%
						for(int i =1 ;i<=count;i++){
						%> <label> <input name="Width<%=i%>" id="Width<%=i%>"
									type="text" value="<%=map.get("Width"+i)%>" style="width:28px"
									maxlength="4"> </label> <%}%>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">缩略图高:</td>
								<td>
								<%
						for(int i =1 ;i<=count;i++){
						%> <label> <input name="Height<%=i%>" id="Height<%=i%>"
									type="text" value="<%=map.get("Height"+i)%>" style="width:28px"
									maxlength="4"> </label> <%}%>
								</td>
							</tr>

						</table>
						</td>
						<td width="47%" align="right" valign="top">
						<div id="divMsg"></div>
						<table width="100%" cellpadding="2" cellspacing="0">
							<tr>
								<td width="36%" align="right" valign="top">所属其他分类:</td>
								<td width="64%">
								<table width="100%" border="1" cellspacing="0"
									id="OtherLibTable" bordercolor="#eeeeee">
									<tr>
										<td width="55%">&nbsp;</td>
										<td width="45%">&nbsp;</td>
									</tr>
									<tr>
										<td width="55%">&nbsp;</td>
										<td width="45%">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">&nbsp;</td>
								<td><input type="button" class="input2" onClick="browse();"
									value="选择" /></td>
							</tr>
						</table>
						</td>
					</tr>

				</table>
				</fieldset>
				</td>
			</tr>
		</table>
		</td>
		<td width="25%" valign="top">
		<fieldset><legend>预览</legend>
		<div id="preview_wrapper">
        <div id="preview_fakeFile1" class="preview_fake">
        <img id="previewFile1" src="../Framework/Images/blank.gif"  onload="onPreviewLoad(this)"/><br/>
        </div>  
        <div id="preview_fakeFile2" class="preview_fake">
        <img id="previewFile2" src="../Framework/Images/blank.gif"  onload="onPreviewLoad(this)"/><br/>
        </div>  
        <div id="preview_fakeFile3" class="preview_fake">
        <img id="previewFile3" src="../Framework/Images/blank.gif"  onload="onPreviewLoad(this)"/><br/>
        </div>
        <div id="preview_fakeFile4" class="preview_fake">
        <img id="previewFile4" src="../Framework/Images/blank.gif"  onload="onPreviewLoad(this)"/><br/>
        </div>
        <div id="preview_fakeFile5" class="preview_fake">
        <img id="previewFile5" src="../Framework/Images/blank.gif"  onload="onPreviewLoad(this)"/><br/>
        </div>
        
		</div>
		<div style="position:relative;height:1px;overflow:hidden;">
		<img id="preview_size_fake"/>
		</div>
		</fieldset>
		</td>
	</tr>
</table>
</form>
</body>
</html>
