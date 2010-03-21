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
	qb = new QueryBuilder("select id,name from zccatalog where type=6 and id =?",CatalogID);
}else{
	qb = new QueryBuilder("select id,name from zccatalog where type=6 and siteid=?",Application.getCurrentSiteID());
}
DataTable dt = qb.executePagedDataTable(1,0);
String value = dt.getString(0,0);
String text = dt.getString(0,1);
%>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
Page.onLoad(function(){
	if($V("hasColumn")!=""){
		$("column").style.display="";
	}
	Selector.setValueEx("SelectCatalogID",'<%=value%>','<%=text%>');
});

function setCatalogID(){
	$S("CatalogID",$V("SelectCatalogID"));
}

<%
	List extList = (List)SimpleUploaderServlet.allowedExtensions.get(SimpleUploaderServlet.AUDIO);
%>

var extsStr = '<%=StringUtil.join(extList)%>';
var exts = extsStr.split(",");

function upload(){
	var sid = $V("SelectCatalogID");
	var flag = false;
	var count = 5;
	for(var i=1;i<=count;i++){
		var audName = $V("File"+i);
		var ext = audName.substring(audName.lastIndexOf(".")+1).toLowerCase();
		if(audName==""){
			continue;
		}
		for(var j=0; j<exts.length; j++) {
			if(ext.trim()==exts[j].toLowerCase().trim()) {
				flag = true;
			}
		}
		if(!flag) {
			Dialog.alert("音频上传不支持"+ext+"文件，请重新选择！");
			return;
		}
	}
	if(flag){
		if(sid==null||sid=="0"){
			Dialog.alert("您没有选择 音频 分类，请选择。");
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
	//$S(id+"Info",name);
}

function doPreview(sender){   
    var flag = 0;
    for(var i=0; i<exts.length; i++) {
	  	if(sender.value.toLowerCase().trim().endsWith("." + exts[i].toLowerCase())) {
			flag = 1;
	  	}
	}
    if(flag == 0) {
    	Dialog.alert('音频格式无效！'); 
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
			alert( '无效的文件类型' ) ;
			return ;
		case 203 :
			alert( "Security error. You probably don't have enough permissions to upload. Please check your server." ) ;
			return ;
		default :
			alert( 'Error on file upload. Error number: ' + returnValue ) ;
			return ;
	}
	window.parent.Parent.onUploadCompleted(returnID);
	window.parent.Dialog.close();
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.resource.Audio.initUploadDialog">
	<div style="display:none"><iframe name="formTarget"
		src="javascript:void(0)"></iframe></div>
	<form enctype="multipart/form-data" id="form2"
		action="../Editor/filemanager/upload/simpleuploader.jsp" method="post"
		target="formTarget"><input type="hidden" id="FileType"
		name="FileType" value="Audio"> <input type="hidden"
		id="CatalogID" name="CatalogID" value="$(CatalogID)">
	<table width="100%" align="center" cellpadding="2" cellspacing="0">
		<tr>
			<td>
			<table width="100%" cellpadding="2" cellspacing="0">
				<tr>
					<td width="100%" align="left"><label>所属分类:<z:select
						id="SelectCatalogID" listWidth="200" listHeight="300"
						listURL="Resource/AudioLibSelectList.jsp"> </z:select></label></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend> <label><strong>音频文件上传:(目前只支持<%=StringUtil.join(extList)%>文件的上传)</strong></label>
			</legend>
			<table width="720px" align="center" cellpadding="2" cellspacing="0"
				class="dataTable" id="imagetable0" style="display:">
				<tr class="dataTableHead">
					<td width="4%" align="right"></td>
					<td width="32%">音频浏览</td>
					<td width="17%">音频名称</td>
					<!--<td width="17%">音频描述</td>-->
					<td width="15%">音频标签</td>
					<td width="8%">是否原创</td>
				</tr>
				<tr>
					<td width='7%' align="right"><label>1.</label></td>
					<td><input name='File1' id='File1' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
					<td><input name="File1Name" id="File1Name" type="text"
						value=""></td>
					<!--<td><input name="File1Info" id="File1Info" type="text"
						value=""></td>-->
					<td><input name="File1Tag" id="File1Tag" type="text"
						style="width:100px;" value=""></td>
					<td align="center"><label for="File1IsOriginal"><input
						name="File1IsOriginal" id="File1IsOriginal" type="checkbox"
						value="Y">是</label></td>
				</tr>
				<tr>
					<td align="right"><label>2.</label></td>
					<td><input name='File2' id='File2' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
					<td><input name="File2Name" id="File2Name" type="text"
						value=""></td>
					<!--<td><input name="File2Info" id="File2Info" type="text"
						value=""></td>-->
					<td><input name="File2Tag" id="File1Tag" type="text"
						style="width:100px;" value=""></td>
					<td align="center"><label for="File2IsOriginal"><input
						name="File2IsOriginal" id="File2IsOriginal" type="checkbox"
						value="Y">是</label></td>
				</tr>
				<tr>
					<td align="right"><label>3.</label></td>
					<td><input name='File3' id='File3' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
					<td><input name="File3Name" id="File3Name" type="text"
						value=""></td>
					<!--<td><input name="File3Info" id="File3Info" type="text"
						value=""></td>-->
					<td><input name="File3Tag" id="File1Tag" type="text"
						style="width:100px;" value=""></td>
					<td align="center"><label for="File3IsOriginal"><input
						name="File3IsOriginal" id="File3IsOriginal" type="checkbox"
						value="Y">是</label></td>
				</tr>
				<tr>
					<td align="right"><label>4.</label></td>
					<td><input name='File4' id='File4' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
					<td><input name="File4Name" id="File4Name" type="text"
						value=""></td>
					<!--<td><input name="File4Info" id="File4Info" type="text"
						value=""></td>-->
					<td><input name="File4Tag" id="File1Tag" type="text"
						style="width:100px;" value=""></td>
					<td align="center"><label for="File4IsOriginal"><input
						name="File4IsOriginal" id="File4IsOriginal" type="checkbox"
						value="Y">是</label></td>
				</tr>
				<tr>
					<td align="right"><label>5.</label></td>
					<td><input name='File5' id='File5' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
					<td><input name="File5Name" id="File5Name" type="text"
						value=""></td>
					<!--<td><input name="File5Info" id="File5Info" type="text"
						value=""></td>-->
					<td><input name="File5Tag" id="File1Tag" type="text"
						style="width:100px;" value=""></td>
					<td align="center"><label for="File5IsOriginal"><input
						name="File5IsOriginal" id="File5IsOriginal" type="checkbox"
						value="Y">是</label></td>
				</tr>
			</table>
			</fieldset>
            <fieldset id="column" style="display:none;">
            <legend> <label><strong>音频自定义属性:</strong></label></legend>
            <table width="720" align="center" cellpadding="2" cellspacing="0">
            ${CustomColumn}
            </table>
            </fieldset>
            <input type="hidden" id="hasColumn" name="hasColumn" value="${hasColumn}"/>
			</td>
		</tr>
	</table>
	<div id="divMsg" align="center"></div>
	</form>
</z:init>
</body>
</html>
