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
	qb = new QueryBuilder("select id,name from zccatalog where type=" + Catalog.VIDEOLIB + " and id =?",CatalogID);
}else{
	qb = new QueryBuilder("select id,name from zccatalog where type=" + Catalog.VIDEOLIB + " and siteid=?",Application.getCurrentSiteID());
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
	List extList = (List)SimpleUploaderServlet.allowedExtensions.get(SimpleUploaderServlet.VIDEO);
%>

var extsStr = '<%=StringUtil.join(extList)%>';
var exts = extsStr.split(",");
function upload(){
	var sid = $V("SelectCatalogID");
	var flag = false;
	var vidName = $V("File1");
	var Integral = $V("Integral");
	var ext = vidName.substring(vidName.lastIndexOf(".")+1).toLowerCase();
	if(vidName==""){
			flag = false;
	}else{
		for(var i=0; i<exts.length; i++) {
			if(ext.trim()==exts[i].toLowerCase().trim()) {
				flag = true;
			}
		}
		if(!flag) {
			Dialog.alert("视频上传不支持"+ext+"文件，请重新选择！");
			return;
		}
	}

	if(flag){
		if(sid==null||sid=="0"){
		Dialog.alert("您没有选择 视频 分类，请选择。");
		return;
		}
		if(isNaN(Integral)){
			Dialog.alert("积分只能是数字，请重新填写。");
			return;
		}
		setCatalogID();
	  $E.disable(window.parent.Parent.$D.CancelButton);
	  $E.disable(window.parent.Parent.$D.OKButton);
		msg();
		$S('CatalogID',$V('SelectCatalogID'));
		$S('OtherIDs',$NV('OtherID'));
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
	$S("Name",name);
	$S("Info",name);
}

function doPreview(sender){   
    var flag = 0;
    for(var i=0; i<exts.length; i++) {
	  	if(sender.value.toLowerCase().trim().endsWith("." + exts[i].toLowerCase())) {
			flag = 1;
	  	}
	}
    if(flag == 0) {
	    alert('不支持此格式！');   
        return false;   
	}
}

function onUploadCompleted( returnValue,returnID,errorMessage){
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
	window.parent.Parent.onUploadVideoCompleted(returnID);
	window.parent.Dialog.close();
}

function browse(){
	var diag = new Dialog("Diag2");
	diag.Width = 300;
	diag.Height = 400;
	diag.Title = "视频分类列表";
	diag.URL = "Resource/VideoLibListDialog.jsp";
	diag.OKEvent = browseSave;
	diag.show();
}

function browseSave(){
	var arr = $DW.$NV("ID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选中视频分类！");
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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.resource.Video.initUploadDialog">
	<body>
	<div style="display:none"><iframe name="formTarget"
		src="javascript:void(0)"></iframe></div>
	<form enctype="multipart/form-data" id="form2" action="../Editor/filemanager/upload/simpleuploader.jsp" method="post" target="formTarget">
	<input type="hidden" id="FileType" name="FileType" value="Video">
	<input type="hidden" id="CatalogID" name="CatalogID" value="$(CatalogID)">
	<input type="hidden" id="OtherIDs" name="OtherIDs" value="">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td height="240px">
			<fieldset><legend> <strong>视频上传:(支持<%=StringUtil.join(extList)%>等文件的上传)</strong></legend>
			<table id="Videotable0" style="display:" width="100%" cellpadding="2"
				cellspacing="0">
				<tr>
					<td width="16%"><label></label></td>
					<td width="84%"><label></label></td>
				</tr>
				<tr>
					<td width='16%'><label>视频浏览：</label></td>
					<td><input name='File1' id='File1' type='file' value=''
						size='30' onChange="setNameInfo(this);doPreview(this);"></td>
				</tr>
				<tr>
					<td width='16%'><label>视频缩略图：</label></td>
					<td><input name='File2' id='File2' type='file' value=''
						size='30'></td>
				</tr>
				<tr>
					<td width="16%"><label>视频名称：</label></td>
					<td width="84%"><input id="Name" name="Name" type="text"
						class="input1" value="" /></td>
				</tr>
				<tr>
					<td width="16%">视频标签：</td>
					<td width="84%"><input id="Tag" name="Tag" type="text"
						value="" class="input1" /></td>
				</tr>
				<tr>
					<td width="16%">积分：</td>
					<td width="84%"><input id="Integral" name="Integral"
						type="text" value="" class="input1" /></td>
				</tr>
				<tr>
					<td width="16%"><label>是否原创：</label></td>
					<td width="84%">${IsOriginal}</td>
				</tr>
				<tr>
					<td width="16%"><label> 视频描述：</label></td>
					<td width="84%"><textarea id="Info" name="Info" cols="100"
						rows="1" class="input3" style="height:45px "></textarea></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td style="height:150px">
			<fieldset><legend> <label><strong>参数设置</strong></label></legend>
			<table width="100%" cellpadding="2" cellspacing="0">
				<tr>
					<td width="58%" align="left">
					<table width="100%" cellpadding="2" cellspacing="0">
						<tr>
							<td width="21%">所属主分类:</td>
							<td colspan="2"><z:select id="SelectCatalogID"
								listWidth="200" listHeight="300"
								listURL="Resource/VideoLibSelectList.jsp"> </z:select></td>
						</tr>
						<tr>
							<td valign="top">所属其他分类:</td>
							<td width="21%">
							<div style="overflow:auto;height:70px;width:122px;">
							<table width="100%" border="1" cellspacing="0" id="OtherLibTable" bordercolor="#eeeeee">
								<tr>
									<td width="55%">&nbsp;</td>
									<td width="45%">&nbsp;</td>
								</tr>
								<tr>
									<td width="55%">&nbsp;</td>
									<td width="45%">&nbsp;</td>
								</tr>
								<tr>
									<td width="55%">&nbsp;</td>
									<td width="45%">&nbsp;</td>
								</tr>
							</table>
							</div>
							</td>
							<td width="58%" valign="top"><input type="button"
								class="input2" onClick="browse();" value="选择" /></td>
						</tr>
					</table>
					<div id="divMsg" align="center"></div>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</form>
	</body>
</z:init>
</html>
