<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function onProxyFlagClick(){
	var flag = $V("ProxyFlag");
	if(flag=="1"){
		$("ProxyHost").enable();
		$("ProxyPort").enable();
		$("ProxyUserName").enable();
		$("ProxyPassword").enable();
		$("ProxyHost").removeClassName("inputTextDisabled");
		$("ProxyPort").removeClassName("inputTextDisabled");
		$("ProxyUserName").removeClassName("inputTextDisabled");
		$("ProxyPassword").removeClassName("inputTextDisabled");
	}else{
		$("ProxyHost").disable();
		$("ProxyPort").disable();
		$("ProxyUserName").disable();
		$("ProxyPassword").disable();
		$("ProxyHost").addClassName("inputTextDisabled");
		$("ProxyPort").addClassName("inputTextDisabled");
		$("ProxyUserName").addClassName("inputTextDisabled");
		$("ProxyPassword").addClassName("inputTextDisabled");
	}
}

function onFilterFlagClick(){
	var flag = $V("FilterFlag");
	if(flag=="1"){
		$("FilterExpr").enable();
	}else{
		$("FilterExpr").disable();
	}
}

function onTypeClick(){
	if($NV("Type")=="D"){
		$("trCatalog").show();
		$("trCatalogOptions").show();
		parent.$("Template").show();
	}else{
		$("trCatalogOptions").hide();
		$("trCatalog").hide();
		parent.$("Template").hide();
	}
}

function addLevel(){
	var table = $("LevelTable");
	var no = table.rows.length+1;
	var row = table.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.align = "right";
	cell.innerHTML = no+"级URL：";
	cell = row.insertCell(-1);
	cell.innerHTML = "<textarea name='URL"+no+"' id='URL"+no+"' style='width:600px;height:70px' verify='NotNull'></textarea>";
	Verify.initCtrl("URL"+no);
	$("RemoveButton").enable();
}

function removeLevel(){
	var table = $("LevelTable");
	if(table.rows.length>1){
		table.deleteRow(table.rows.length-1);
	}
	if(table.rows.length<=1){		
		$("RemoveButton").disable();		
	}
}

Page.onLoad(function(){
	if(parent.Parent.EditFlag){
		var map = parent.Parent.Map;
		$NS("Type",map["Type"]);
		for(k in map){
			var v = map[k];
			if(!k.startWith("FilterBlock")&&!k.startWith("RefCode")&&!k.startWith("Script")&&!k.startWith("Template")&&k!="Type"){
				if(k.startWith("URL")&&k!="URL1"){
					addLevel();
				}
				if(k=="CatalogID"&&v){
					Server.getOneValue("com.zving.datachannel.FromWeb.getCatalogName",v,function(dc){
						Selector.setValueEx("CatalogID",map["CatalogID"],dc.get("Name"));
					});
				}else{
					$S(k,v);
				}
			}
		}
		onTypeClick();
		onProxyFlagClick();
		onFilterFlagClick();
	}
});

function compare(ele,min,max){
	var v = $V(ele);
	if(v>=min&&v<=max){
		return true;
	}
	return false;
}
</script>
</head>
<body>
<form id="form2">
<table width="820" align="center" cellpadding="2" cellspacing="0">
	<tr>
		<td width="13%" height="9"><input name="hidden" type="hidden" id="ID" value=""></td>
		<td width="40%"></td>
		<td width="47%"></td>
	</tr>
	<tr>
		<td colspan="2" valign="top">
		<table width="100%" border="0">
			<tr>
				<td width="32%" height="24" align="right">采集类别：</td>
				<td width="68%">
				<label><input name="Type" type="radio" onClick='onTypeClick()' value="D" checked> 文档采集 </label>&nbsp;&nbsp;&nbsp; 
				<label><input type="radio" name="Type" value="T" onClick='onTypeClick()'> 自定义采集</label></td>
			</tr>
			<tr>
				<td height="24" align="right">任务名称：</td>
				<td><input name="Name" type="text" value="" class="inputText" id="Name" size=40 verify="NotNull" /></td>
			</tr>
			<tr>
				<td height="24" align="right">任务描述：</td>
				<td><input name="text2" type="text" class="inputText" id="Intro" value="" size=50 /></td>
			</tr>
			<tr>
				<td height="24" align="right">最大采集数：</td>
				<td><input name="MaxPageCount" type="text" class="inputText" id="MaxPageCount" value="-1" size=15 verify="NotNull&&Int&&Script=compare('ThreadCount',1,100)" />
				(-1表示不限制)</td>
			</tr>
			<tr>
				<td height="24" align="right">采集线程数：</td>
				<td><input name="ThreadCount" type="text" class="inputText" id="ThreadCount" value="30" size=15 verify="NotNull&&Int&&Script=compare('ThreadCount',1,100)" />(1-100)</td>
			</tr>
			<tr>
				<td height="24" align="right">超时等待时间：</td>
				<td><input name="TimeOut" type="text" class="inputText" id="TimeOut" value="30" size=15 verify="NotNull&&Int&&Script=compare('TimeOut',1,600)" /> 秒 (1-600)</td>
			</tr>
			<tr>
				<td height="24" align="right">发生错误时重试次数：</td>
				<td><input name="RetryTimes" type="text" class="inputText" id="RetryTimes" value="2" size=15 verify="NotNull&&Int&&Script=compare('RetryTimes',1,10)" /> (1-10)</td>
			</tr>
			<tr>
				<td height="24" align="right">发布日期格式：</td>
				<td><input name="PublishDateFormat" type="text" class="inputText" id="PublishDateFormat" value="yyyy-MM-dd" size=30 /></td>
			</tr>
			<tr>
				<td height="24" align="right">采集选项：</td>
				<td><label> <input name="CopyImage" type="checkbox" id="CopyImage" value="1"> 下载远程图片</label> &nbsp;&nbsp; 
				<label id="trCatalogOptions"> <input name="CleanLink" type="checkbox" id="CleanLink" value="1"> 去掉内容中的超链接</label></td>
			</tr>
			<tr id="trCatalog">
				<td height="24" align="right">采集到此栏目：</td>
				<td><z:select id="CatalogID" listWidth="300" verify="NotNull" condition="$NV('Type')=='D'" listHeight="300" listURL="Site/CatalogSelectList.jsp"> </z:select></td>
			</tr>
			<tr id="trCatalog">
				<td height="24" align="right">&nbsp;</td>
				<td>
				<z:tbutton onClick="addLevel()"><img src="../Icons/icon404a8.gif" width="20" height="20" />增加URL层级</z:tbutton>
				<z:tbutton id="RemoveButton" onClick="removeLevel()"><img src="../Icons/icon404a5.gif" width="20" height="20" />减少URL层级</z:tbutton></td>
			</tr>
		</table>
		</td>
		<td rowspan="2" valign="top">
		<fieldset><legend> 
		<label> <input name="ProxyFlag" type="checkbox" id="ProxyFlag" onClick="onProxyFlagClick();" value="1"> 使用代理服务器</label> </legend>
		<table id="ProxyTable" width="100%" border="0" cellpadding="2">
			<tr>
				<td height="24" align="right">服务器地址：</td>
				<td><input name="ProxyHost" type="text" class="inputText" id="ProxyHost" disabled="disabled" value="" size=40 verify="NotNull" condition="$V('ProxyFlag')=='1'" /></td>
			</tr>
			<tr id="trPort">
				<td height="24" align="right">端口：</td>
				<td><input name="ProxyPort" type="text" class="inputText" id="ProxyPort" disabled="disabled" value="" size=15 verify="NotNull&&Int" condition="$V('ProxyFlag')=='1'" /></td>
			</tr>
			<tr id="trUserName">
				<td height="24" align="right">用户名：</td>
				<td><input name="ProxyUserName" type="text" class="inputText" id="ProxyUserName" disabled="disabled" value="" size=30 verify="NotNull" condition="$V('ProxyFlag')=='1'" /></td>
			</tr>
			<tr id="trPassword">
				<td height="24" align="right">密码：</td>
				<td><input name="ProxyPassword" type="password" class="inputText" id="ProxyPassword" disabled="disabled" size=30 verify="NotNull" condition="$V('ProxyFlag')=='1'" /></td>
			</tr>
		</table>
		</fieldset>
		<fieldset><legend> 
		<label> <input name="FilterFlag" type="checkbox" id="FilterFlag" onClick="onFilterFlagClick();" value="1"> 过滤URL</label> </legend>
		<table id="ProxyTable" width="100%" border="0" cellpadding="2">
			<tr>
				<td height="24" align="right">过滤表达式：</td>
				<td><textarea name="FilterExpr" id="FilterExpr" style="width:250px;height:80px" disabled="true" verify="NotNull" condition="$V('FilterFlag')=='1'"></textarea></td>
			</tr>
		</table>
		</fieldset>
		</td>
	</tr>
</table>
<table width="820" border="0" align="center" cellpadding="2"
	cellspacing="0" id='LevelTable'>
	<tr>
		<td align="right">起始URL：</td>
		<td valign="middle"><textarea name="URL1" id="URL1" style="width:600px;height:70px" verify="NotNull"></textarea></td>
	</tr>
</table>
</form>
</body>
</html>
