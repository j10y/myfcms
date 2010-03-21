<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script type="text/javascript">

function selectIcon(){
	var diag = new Dialog("Diag2");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "选择图标";
	diag.URL = "Platform/Icon.jsp";
	diag.onLoad = function(){
	};
	diag.show();
}

function afterSelectIcon(){
	$("LogoFileName").src = $DW.Icon;
	$D.close();
}

function setAlias(){
	if($V("Alias") == ""){
	  $S("Alias",getSpell($V("Name"),true));
  }
}

function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;
	diag.Height = 440;
	diag.Title ="浏览列表页模板";
	diag.URL = "Site/TemplateExplorer.jsp?SiteID="+$V("ID")+"&Alias=default";
	goBack = function(params){
		$S(ele,params);
	}
	diag.OKEvent = afterSelect;
	diag.show();
}

function browseFile(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 700;
	diag.Height = 440;
	diag.Title ="浏览文件";
	diag.URL = "Site/FileExplorer.jsp?SiteID="+$V("ID")+"&Alias=default";
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
<body class="dialogBody">
<z:init method="com.zving.cms.site.Site.initDialog">
	<form id="form2" method="post">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td width="100"></td>
			<td width="10" height="10"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">站点名称：</td>
			<td>&nbsp;</td>
			<td><input name="Name" type="text" class="input1" id="Name"
				onBlur="setAlias();" onChange="setAlias();" value="${Name}"
				size="40" verify="站点名称|NotNull" /> <input name="ID" type="hidden"
				id="ID" value="${ID}" /></td>
		</tr>
		<tr>
			<td align="right">英文名称：</td>
			<td>&nbsp;</td>
			<td><input name="Alias" type="text" class="input1" id="Alias"
				value="${Alias}" size="40"
				verify="英文名称|NotNull&&只允许大小写字母、数字、英文句号、下划线及英文破折线|Regex=^[\w\.\_\-]+$" /></td>
		</tr>
		<tr>
			<td align="right">站点描述：</td>
			<td>&nbsp;</td>
			<td><input name="Info" type="text" class="input1" id="Info"
				value="${Info}" size="40" /></td>
		</tr>
		<tr>
			<td align="right">站点URL：</td>
			<td>&nbsp;</td>
			<td><input name="URL" type="text" class="input1" id="URL"
				value="${URL}" size="40" verify="URL|NotNull" /></td>
		</tr>
		<tr>
			<td align="right">首页模板：</td>
			<td>&nbsp;</td>
			<td><input name="IndexTemplate" type="text" class="input1"
				id="IndexTemplate" value="${IndexTemplate}" size="30" /> <input
				type="button" class="input2" onClick="browse('IndexTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right">列表页模板：</td>
			<td>&nbsp;</td>
			<td><input name="ListTemplate" type="text" class="input1"
				id="ListTemplate" value="${ListTemplate}" size="30" /> <input
				type="button" class="input2" onClick="browse('ListTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right">详细页模板：</td>
			<td>&nbsp;</td>
			<td><input name="DetailTemplate" type="text" class="input1"
				id="DetailTemplate" value="${DetailTemplate}" size="30" /> <input
				type="button" class="input2" onClick="browse('DetailTemplate');"
				value="浏览..." /></td>
		</tr>
		<tr>
			<td align="right">编辑器样式：</td>
			<td>&nbsp;</td>
			<td><input id="EditorCss" name="EditorCss" type="text"
				class="input1" value="${EditorCss}" size="30" /> <input
				type="button" class="input2" onClick="browseFile('EditorCss');"
				value="浏览..." /> <a href="#" class="tip"
				onMouseOut="Tip.close(this)"
				onMouseOver="Tip.show(this,'本样式文件将会被文章编辑器引入，使文章编辑时的效果更接近最后发布的效果。');"><img
				src="../Framework/Images/icon_tip.gif" width="16" height="16"></a></td>
		</tr>
		<tr>
			<td align="right">是否自动索引：</td>
			<td>&nbsp;</td>
			<td>${radioAutoIndexFlag}</td>
		</tr>
		<tr>
			<td align="right">是否自动统计：</td>
			<td>&nbsp;</td>
			<td>${radioAutoStatFlag}</td>
		</tr>
		<tr>
			<td align="right">评论是否需审核：</td>
			<td>&nbsp;</td>
			<td>${radioCommentAuditFlag}</td>
		</tr>
		<tr>
			<td align="right">排序权值：</td>
			<td>&nbsp;</td>
			<td><input id="OrderFlag" name="OrderFlag" type="text"
				class="input1" value="${OrderFlag}" /></td>
		</tr>
		<!--tr>
      <td>留言板标志：</td>
      <td>&nbsp;</td>
      <td>
      <label><input type="radio" name="MessageBoardFlag" value="Y"  />是</label>
      <label><input type="radio" name="MessageBoardFlag" value="N" checked/>否</label>      </td>
    </tr-->
	</table>
	</form>
</z:init>
</body>
</html>
