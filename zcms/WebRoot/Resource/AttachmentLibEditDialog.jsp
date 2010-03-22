<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script type="text/javascript">

function goBack(params){
	alert(params)
}

function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;	diag.Height = 440;
	diag.Title ="浏览模板";
diag.URL = "Site/TemplateExplorer.jsp";
	goBack = function(params){
		$S(ele,params);
	}
	diag.OKEvent = afterSelect;
	diag.show();
}

function afterSelect(){
	$DW.onOK();
}

</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.cms.resource.AttachmentLib.initEditDialog">
	<body class="dialogBody">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td height="10"></td>
			<td></td>
		</tr>
		<tr id="tr_ID" style="display:none">
			<td align="right">栏目ID：</td>
			<td><input name="ID" type="text" class="input1" id="ID"
				value="${ID}" /></td>
		</tr>
		<tr>
			<td align="right">附件分类名称：</td>
			<td><input name="Name" type="text" class="input1" id="Name"
				value="${Name}" verify="附件分类名称|NotNull" /></td>
		</tr>
        <tr>
			<td align="right" width="31%">首页模板:</td>
			<td><input name="IndexTemplate" type="text" class="input1"
				id="IndexTemplate" value="${IndexTemplate}" size="30"> <input
				type="button" class="input2" onClick="browse('IndexTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right" width="31%">列表页模板:</td>
			<td><input name="ListTemplate" type="text" class="input1"
				id="ListTemplate" value="${ListTemplate}" size="30"> <input
				type="button" class="input2" onClick="browse('ListTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right">详细页模板:</td>
			<td><input name="DetailTemplate" type="text" class="input1"
				id="DetailTemplate" value="${DetailTemplate}" size="30" /> <input
				type="button" class="input2" onClick="browse('DetailTemplate')"
				value="浏览..." /></td>
		</tr>
	</table>
	</form>
	</body>
</html>
</z:init>
