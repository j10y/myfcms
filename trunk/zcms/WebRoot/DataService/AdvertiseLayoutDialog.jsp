<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	if($V("hPositionType")!=""){
		$S("PositionType",$V("hPositionType"));
	}
});

function  ChangePositionType(){
	var PositionType = $V("PositionType");
	if(PositionType=="couplet"||PositionType=="float"||PositionType=="fixure"){
		$("trPosition").show();
	}else{
		$("trPosition").hide();
	}
	if(PositionType=="code"){
		$("trSize").hide();
	}else{
		$("trSize").show();
	}
	if($V("ID")!=""){
		if(PositionType!=$V("hPositionType")){
			$("alertTr").style.display="";
		}else{
			$("alertTr").style.display="none";
		}
	}
}
</script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.AdvertiseLayout.DialogInit">
<form id="form2">
<table width="100%" border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
	<tr>
		<td width="24%" height="12"></td>
		<td >
        <input type="hidden" id="ID" name="ID" value="${ID}"/>
        <input type="hidden" id="hPositionType" name="hPositionType" value="${PositionType}"/>
        </td>
	</tr>
    <tr>
      <td  align="right" valign="top">版位名称：</td>
      <td width="76%"><input name="PositionName" type="text" id="PositionName" value="${PositionName}" size="40" verify="广告版位名称|NotNull"/></td>
    </tr>
    <tr>
      <td  align="right" valign="top">版位类型：</td>
      <td><z:select name="PositionType" id="PositionType" onChange="ChangePositionType();">
        <span value="banner" selected>矩形横幅</span>
        <span value="fixure">固定位置</span>
        <span value="float">漂浮移动</span>
        <span value="couplet">对联广告</span>
        <span value="imagechange">图片轮换广告</span>
        <span value="code">代码调用</span>
      </z:select></td>
    </tr>
    <tr id="trPosition" style="display:none;">
    	<td align="right"  valign="top">版位位置：</td>
        <td valign="top">
        左边距：<input name='PaddingLeft' id='PaddingLeft' type='text' size='5' value='${PaddingLeft}' verify="左边距|Number&&NotNull" condition="$V('PositionType')!='banner'&&$V('PositionType')!='imagechange'&&$V('PositionType')!='code'"> px&nbsp;&nbsp;
        上边距：<input name='PaddingTop' id='PaddingTop' type='text' size='5' value='${PaddingTop}' verify="上边距|Number&&NotNull" condition="$V('PositionType')!='banner'&&$V('PositionType')!='imagechange'&&$V('PositionType')!='code'"> px
        </td>
    </tr>
    <tr id="trSize">
      <td align="right" valign="top">版位尺寸：</td>
      <td >&nbsp;&nbsp;&nbsp;宽度：<input name="PositionWidth" type="text" id="PositionWidth"  value="${PositionWidth}" size="5" verify="版位宽度|Number&&NotNull" condition="$V('PositionType')!='code'"/> px&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      高度：<input name="PositionHeight" type="text" id="PositionHeight" value="${PositionHeight}" size="5" verify="版位高度|Number&&NotNull" condition="$V('PositionType')!='code'" /> px</td>
    </tr>
    <tr>
      <td  align="right" valign="top">版位描述：</td>
      <td><textarea id="Description" name="Description" cols="50" rows="4">${Description}</textarea></td>
    </tr>
    <tr id="alertTr" style="display:none;">
      <td>&nbsp;</td>
      <td align="left"><font color="#FF0000">注意：变更版位类型将导致版位下已有的广告内容清空</font></td>
    </tr>
</table>
</form>
</z:init>
</body>
</html>