<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
  String siteID = request.getParameter("SiteID");
  if(siteID == null){
      siteID = Application.getCurrentSiteID()+"";
  }

	String alias = SiteUtil.getAlias(siteID);
	if(StringUtil.isEmpty(alias)){
	   alias = "default";
	}
	String rootPath = Config.getContextRealPath()+Config.getValue("Statical.TemplateDir")+"/"+alias+"/template";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件管理器</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style>
body{overflow:auto;}
</style>
<script src="../Framework/Main.js"></script>
<script>
	function afterClick(ele){
		var fileName =  ele.getAttribute("filename");
	  var path = "/template"+Explorer.currentPath+"/"+fileName;
	  if(!path.startWith("/")){
		  path = "/"+path;
	  }
	  
	  $S("FileType",ele.getAttribute("filetype"));
	  $S("Path",path);
	}
	
	function afterDbClick(ele){
		$S("FileType",ele.getAttribute("filetype"));
		if(ele.getAttribute("filetype")=="F"){
				var fileName =  ele.getAttribute("filename");
			  var path = "/template"+Explorer.currentPath+"/"+fileName;
			  if(!path.startWith("/")){
				  path = "/"+path;
			  }
			  
			  $S("Path",path);
			  onOK();
		}
	}
	
	function onOK(){
		if($V("Path")==""){
			alert("请选择模板文件！");
			return;
		}
		
		if($V("FileType")=="D"){
			alert("不能选择文件夹作为模板！");
			return;
		}
	  Parent.goBack($V("Path"));
	  Dialog.close();
	}
</script>
</head>
<base target="_self">
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
	<tr>
		<td><z:explorer id="e1" name="File.template" style="height:370px"
			baseDir="<%=rootPath%>" exclude="WEB-INF"
			column="checkbox,index,name,title,modifytime,size" page="true"
			size="500">
		</z:explorer></td>
	</tr>
</table>

<div style="display:none">
<table width="100%" border="0" cellpadding="3" cellspacing="4">
	<tr>
		<td>当前路径： <input type="text" name="Path" id="Path" size="60"></td>
	</tr>
</table>
</div>
<input type="hidden" id="FileType">
</body>
</html>
