<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	String rootPath = Config.getContextRealPath()+Config.getValue("Statical.TemplateDir")+"/"+Application.getCurrentSiteAlias();
%>
<title>文件管理器</title>

<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
body{ _overflow:hidden;}
</style>
<script src="../Framework/Main.js"></script>
<script>

var explorerType = "";
function add(){
   addFile();	
}

function addDir(){
	addDirectory();
}

function up(){
   Explorer.goParent();	
}

function editFile(){
	if(Explorer.lastRowClickEle){
		var param = [Explorer.lastRowClickEle.getAttribute("filename")];
	 	edit(param,"");

	}else{
		alert("请单击选择文件");
	}
}

function renameFile(){
	 if(Explorer.lastRowClickEle){
	 	rename(Explorer.lastRowClickEle.getAttribute("filename"));
	 }else{
		alert("请单击选择文件");
	 }
}

function delFile(){
	if(Explorer.lastRowClickEle){
		del(Explorer.lastRowClickEle.getAttribute("filename"));
	}else{
		alert("请单击选择文件");
	}
}

function importFile(){
	var currentPath = Explorer.baseDir+"/"+Explorer.currentPath;
	var diag = new Dialog("Diag1");
	diag.Width = 320;
	diag.Height = 250;
	diag.Title = "上传文件";
	diag.URL =  "Site/FileImportDialog.jsp?SiteID="+$V("SiteID")+"&Path="+currentPath;
	diag.ShowButtonRow = false;
	diag.show();
}


function exportFiles(){
   if(Explorer.lastRowClickEle){
		exportFile(Explorer.lastRowClickEle.getAttribute("filename"));
	}else{
		alert("请单击选择文件");
	}
}

</script>
</head>
<body>
<input type="hidden" id="SiteID"
	value="<%=Application.getCurrentSiteID()%>">
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td valign="middle" class="blockTd"><img
					src="../Icons/icon018a1.gif" /> 文件列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 0;"><z:tbutton onClick="add()">
					<img src="../Icons/icon018a2.gif" />新建文件</z:tbutton> <z:tbutton
					onClick="addDir()">
					<img src="../Icons/icon018a2.gif" />新建目录</z:tbutton> <z:tbutton
					onClick="editFile()">
					<img src="../Icons/icon018a2.gif" />编辑</z:tbutton> <z:tbutton
					onClick="renameFile()">
					<img src="../Icons/icon018a2.gif" />重命名</z:tbutton> <z:tbutton
					onClick="delFile()">
					<img src="../Icons/icon018a3.gif" />删除</z:tbutton> <z:tbutton onClick="up()">
					<img src="../Icons/icon400a4.gif" />上级</z:tbutton> <z:tbutton
					onClick="importFile()">
					<img src="../Icons/icon026a7.gif" />上传</z:tbutton> <z:tbutton
					onClick="exportFiles()">
					<img src="../Icons/icon026a13.gif" />导出</z:tbutton> <z:tbutton
					onClick="deploy()">
					<img src="../Icons/icon018a2.gif" />分发</z:tbutton></td>
			</tr>
			<tr>
				<td><z:explorer id="e1" style="height:400px;"
					name="File.template" baseDir="<%=rootPath%>" exclude="WEB-INF"
					column="checkbox,index,name,title,modifytime,size" page="true"
					size="500">
				</z:explorer></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
