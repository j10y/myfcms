<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%@page import="com.zving.FCKeditor.uploader.SimpleUploaderServlet"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<%
	String CatalogID = request.getParameter("CatalogID");
	QueryBuilder qb = null;
	if(CatalogID!=null&&!"".equals(CatalogID)&&!"null".equals(CatalogID)){
		qb = new QueryBuilder("select id,name from zccatalog where type=7 and id =?",CatalogID);
	}else{
		qb = new QueryBuilder("select id,name from zccatalog where type=7 and siteid=? order by id",Application.getCurrentSiteID());
	}
	DataTable dt = qb.executePagedDataTable(1,0);
	String value = dt.getString(0,0);
	String text = dt.getString(0,1);
%>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
Page.onLoad(function(){
	Selector.setValueEx("SelectCatalogID",'<%=value%>','<%=text%>');
});

function setCatalogID(){
	$S("CatalogID",$V("SelectCatalogID"));
}

<%
	List extList = (List)SimpleUploaderServlet.allowedExtensions.get(SimpleUploaderServlet.ATTACH);
%>

var extsStr = 'swf';
var exts = extsStr.split(",");
function upload(){
	var sid = $V("SelectCatalogID");
	var flag = false;
	var count = 5;
	for(var i=1;i<=count;i++){
		var flashName = $V("File"+i);
		if(flashName==""){
			continue;
		}
		flag = false;
		var ext = flashName.substring(flashName.lastIndexOf(".")+1).toLowerCase();
		for(var j=0; j<exts.length; j++) {
			if(ext.trim()==exts[j].trim()) {
				flag = true;
			}
		}
		if(!flag) {
			Dialog.alert("Flash上传不支持"+ext+"文件，请重新选择！");
			return;
		}
	}
	if(flag){
		if(sid==null){
			Dialog.alert("您没有选择 Flash 分类，请选择。");
			return;
		}
		setCatalogID();
	    $E.disable(window.parent.Parent.$D.CancelButton);
	    $E.disable(window.parent.Parent.$D.OKButton);
		msg();
		$S('CatalogID',$V('SelectCatalogID'));
		
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
	  	if(sender.value.trim().endsWith("." + exts[i])) {
			flag = 1;
	  	}
	}
    if(flag == 0) {
	    Dialog.alert('不支持此格式！');    
        return false;   
	}
}

function onUploadCompleted(returnValue,returnID,errorMessage){
	switch ( returnValue )
	{
		case 0 :	// No errors
			//alert( 'Your file has been successfully uploaded' ) ;
			break ;
		//case 1 :	// Custom error
		//	alert( customMsg ) ;
		//	return ;
		//case 101 :	// Custom warning
		//	alert( customMsg ) ;
		//	break ;
		//case 201 :
		//	alert( 'A file with the same name is already available. The uploaded file has been renamed to "' + fileName + '"' ) ;
		//	break ;
		case 202 :
			Dialog.alert( '无效的文件类型' ) ;
			return ;
		case 203 :
			Dialog.alert( "Security error. You probably don't have enough permissions to upload. Please check your server." ) ;
			return ;
		default :
			Dialog.alert( 'Error on file upload. Error number: ' + returnValue ) ;
			return ;
	}
	window.parent.Parent.onFlashUploadCompleted(returnID);
	window.parent.Dialog.close();
}

function changeLock(n){
	if($("File"+n+"Password").style.display==""){
		$("File"+n+"Password").style.display = "none";
		$S("File"+n+"Locked","N");
	}else{
		$("File"+n+"Password").style.display = "";
		$S("File"+n+"Locked","Y");
	}
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div style="display:none"><iframe name="formTarget"
	src="javascript:void(0)"></iframe></div>
<form enctype="multipart/form-data" id="form2"
	action="../Editor/filemanager/upload/simpleuploader.jsp" method="post"
	target="formTarget">
<input type="hidden" id="FileType" name="FileType" value="Attach">
<input type="hidden" id="CatalogID" name="CatalogID"
	value="${CatalogID}">
<table width="100%" align="center" cellpadding="2" cellspacing="0">
	<tr>
		<td>
		<table width="100%" cellpadding="2" cellspacing="0">
			<tr>
				<td width="100%" align="left"><label>所属分类:<z:select
					id="SelectCatalogID" listWidth="200" listHeight="300"
					listURL="Resource/AttachmentLibSelectList.jsp"> </z:select></label></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<fieldset><legend> <label><strong>Flash文件上传:(支持swf文件的上传)</strong></label>
		</legend>
		<table width="720" align="center" cellpadding="2" cellspacing="0"
			class="dataTable" id="imagetable0">
			<tr class="dataTableHead">
				<td width="6%" align="right" nowrap>&nbsp;</td>
				<td width="34%"><label> Flash浏览</label></td>
				<td width="25%">Flash名称</td>
				<td width="35%">Flash描述</td>
			</tr>
			<tr>
				<td width='6%' align="right"><label>1.</label></td>
				<td><input name='File1' id='File1' type='file' value=''
					size='25' onChange="setNameInfo(this);doPreview(this);"></td>
				<td><input name="File1Name" id="File1Name" type="text" value=""></td>
				<td><input name="File1Info" id="File1Info" type="text" value=""
					size="25"></td>
			
			</tr>
			<tr>
				<td align="right"><label>2.</label></td>
				<td><input name='File2' id='File2' type='file' value=''
					size='25' onChange="setNameInfo(this);doPreview(this);"></td>
				<td><input name="File2Name" id="File2Name" type="text" value=""></td>
				<td><input name="File2Info" id="File2Info" type="text" value=""
					size="25"></td>
			
			</tr>
			<tr>
				<td align="right"><label>3.</label></td>
				<td><input name='File3' id='File3' type='file' value=''
					size='25' onChange="setNameInfo(this);doPreview(this);"></td>
				<td><input name="File3Name" id="File3Name" type="text" value=""></td>
				<td><input name="File3Info" id="File3Info" type="text" value=""
					size="25"></td>
			
			</tr>
			<tr>
				<td align="right"><label>4.</label></td>
				<td><input name='File4' id='File4' type='file' value=''
					size='25' onChange="setNameInfo(this);doPreview(this);"></td>
				<td><input name="File4Name" id="File4Name" type="text" value=""></td>
				<td><input name="File4Info" id="File4Info" type="text" value=""
					size="25"></td>
				
			</tr>
			<tr>
				<td align="right"><label>5.</label></td>
				<td><input name='File5' id='File5' type='file' value=''
					size='25' onChange="setNameInfo(this);doPreview(this);"></td>
				<td><input name="File5Name" id="File5Name" type="text" value=""></td>
				<td><input name="File5Info" id="File5Info" type="text" value=""
					size="25"></td>
			</tr>
		</table>
		</fieldset>
		</td>
	</tr>
</table>
</form>
<div id="divMsg" align="center"></div>
</body>
</html>
