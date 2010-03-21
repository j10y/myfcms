<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
Page.onLoad(changeInputType);

var input = "1";
var text = "2";
var select = "3";
var radio = "4";
var checkbox = "5";
var dateInput = "6";
var imageInput = "7";

function changeInputType(){
	var inputType = $V("InputType");
	if(input==inputType){
		$("tr_IsMandatory").show();
		$("tr_DefaultValue").show();
		$("tr_VerifyType").show();
		$("tr_MaxLength").show();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").hide();
		$("DataType").enable();
	}else if(text==inputType){
		$("tr_IsMandatory").show();
		$("tr_DefaultValue").show();
		$("tr_VerifyType").show();
		$("tr_MaxLength").show();
		$("tr_Cols_Rows").show();
		$("tr_ListOption").hide();
		$("DataType").enable();
	}else if(select==inputType){
		$("tr_IsMandatory").show();
		$("tr_DefaultValue").show();
		$("tr_VerifyType").hide();
		$("tr_MaxLength").hide();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").show();
		$("DataType").enable();
	}else if(radio==inputType){
		$("tr_IsMandatory").hide();
		$("tr_DefaultValue").show();
		$("tr_VerifyType").hide();
		$("tr_MaxLength").hide();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").show();
		$("DataType").enable();
	}else if(checkbox==inputType){
		$("tr_IsMandatory").hide();
		$("tr_DefaultValue").show();
		$("tr_VerifyType").hide();
		$("tr_MaxLength").hide();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").show();
		$("DataType").enable();
	}else if(dateInput==inputType){
		$("tr_IsMandatory").show();
		$("tr_DefaultValue").hide();
		$("tr_VerifyType").hide();
		$("tr_MaxLength").hide();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").hide();
		$("DataType").disable();
	}else if(imageInput==inputType){
		$("tr_IsMandatory").hide();
		$("tr_DefaultValue").hide();
		$("tr_VerifyType").hide();
		$("tr_MaxLength").hide();
		$("tr_Cols_Rows").hide();
		$("tr_ListOption").hide();
		$("DataType").enable();
	}
}

function setSpell(){
	if($V("Code") == ""){
	  $S("Code",getSpell($V("Name")));
  }
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.dataservice.Column.initDialog">
<form id="form2">
<table width="450" height="100" border="0" align="center" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
 	<tr>
      <td width="28%" height="10"></td>
      <td>
      <input name="ColumnID" id="ColumnID" type="hidden" value="${ID}"/> 
	  <input name="FormID" id="FormID" type="hidden" value="${FormID}"/> 
      </td>
    </tr>
    <tr>
      <td height="29"  align="right" >表现形式：</td>
      <td  >
      <z:select id="InputType" onChange="changeInputType()">
      ${InputType}
      </z:select>
      </td>
    </tr>
    <tr>
      <td width="28%" height="31"  align="right" >字段名称：</td>
      <td width="72%" ><input name="Name" id="Name" type="text" verify="字段名字|NotNull" value="${Name}" onBlur="setSpell();" />
      </td>
    </tr>
    <tr>
      <td height="31" align="right" >字段代码：</td>
      <td ><input name="Code" id="Code" type="text"  verify="字段名称|NotNull" value="${Code}" onBlur="setSpell();"/>
      </td>
    </tr>
     <tr>
      <td height="31" align="right" >数据类型：</td>
      <td ><z:select id="DataType" name="DataType"> ${DataType}</z:select></td>
    </tr>
    <tr id="tr_IsMandatory">
      <td height="33"  align="right" >是否必填：</td>
      <td  >${IsMandatory}</td>
    </tr>
    <tr id="tr_DefaultValue">
      <td   align="right" >默认值：</td>
      <td  ><input name="DefaultValue" id="DefaultValue" type="text" value="${DefaultValue}" /></td>
    </tr>
    <tr id="tr_VerifyType">
      <td height="29"  align="right" >校验类型：</td>
      <td  >
      <z:select id="VerifyType">
      ${VerifyType}
      </z:select>
      </td>
    </tr>
    <tr id="tr_MaxLength">
      <td align="right" >最大字符数：</td>
      <td ><input name="MaxLength" id="MaxLength" type="text" verify="最大字符数|NotNull&&Int" value="${MaxLength}"/> 
      </td>
    </tr>
    <tr id="tr_Cols_Rows">
      <td align="right" >宽、高：</td>
      <td >
       宽度：<input name="Cols" id="Cols" type="text" size="2" verify="宽度|NotNull&&Int" value="${Cols}"/>
       高度：<input name="Rows" id="Rows" type="text" size="2" verify="高度|NotNull&&Int" value="${Rows}"/> 
      </td>
    </tr>
	<tr id="tr_ListOption">
      <td height="83"  align="right" >列表选项：<br>
        <br>
          <font color="red">(每行为一<br>
      个列表项)</font></td>
      <td  ><textarea id="ListOption" name="ListOption" cols="43" rows="5" verify="列表选项|NotNull">${ListOption}</textarea></td>
    </tr>
</table>
</form>
</z:init>
</body>
</html>
