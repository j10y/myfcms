<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	
});

function browse(ele){
	var diag  = new Dialog("Diag1");
	diag.Width = 700;	diag.Height = 450;
	diag.Title ="浏览列表页模板";
diag.URL = "Site/TemplateExplorer.jsp?SiteID="+$V("SiteID");
	goBack = function(params){
		$S(ele,params);
	}
	diag.show();
}

function save(){
	var dc = Form.getData($F("form2"));
	Server.sendRequest("com.zving.cms.site.Catalog.saveTemplate",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==0){
				$D.close();
				window.location.reload();
			}
		});
	});
}
</script>
</head>
<body>
<z:init method="com.zving.cms.site.Catalog.init">
	<div style="padding:2px;">
	<table width="100%" cellpadding="4" cellspacing="0">
		<tr>
			<td><z:tbutton onClick="save();">
				<img src="../Icons/icon002a2.gif" width="20" height="20" />保存</z:tbutton></td>
		</tr>
	</table>
	<form id="form2">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"
		class="dataTable">
		<tr class="dataTableHead">
			<td width="20%"><b>属性</b></td>
			<td height="10"><b>值</b></td>
		</tr>
		<tr id="tr_SiteID" style="display:none">
			<td>站点ID：</td>
			<td><input name="SiteID" type="hidden" id="SiteID"
				value="${SiteID}" /> <input name="ID" type="hidden" id="ID"
				value="${ID}" /></td>
		</tr>
		<tr>
			<td>栏目名称：</td>
			<td>${Name} <input type="hidden" id="Name" value="${Name}"></td>
		</tr>
		<tr style="display:${IsDisplay}">
			<td>首页模板:</td>
			<td><input name="SiteID" type="text" class="input1"
				id="IndexTemplate" value="${IndexTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('IndexTemplate')"></td>
		</tr>
		<tr style="display:${IsDisplay}">
			<td>列表页模板:</td>
			<td><input name="SiteID" type="text" class="input1"
				id="ListTemplate" value="${ListTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('ListTemplate')"></td>
		</tr>

		<tr>
			<td>详细页模板:</td>
			<td><input name="SiteID" type="text" class="input1"
				id="DetailTemplate" value="${DetailTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('DetailTemplate')"></td>
		</tr>
		<tr>
			<td>Rss模板:</td>
			<td><input name="SiteID" type="text" class="input1"
				id="RssTemplate" value="${RssTemplate}" size="30" /> <input
				type="button" name="Submit" value="选择.."
				onClick="browse('RssTemplate')"></td>
		</tr>
		<tr>
			<td>模板沿用:</td>
			<td><z:select id="Extend" style="width:150px;"> ${Extend} </z:select>
			</td>
		</tr>
	</table>
	</form>
	</div>
</z:init>
</body>
</html>
