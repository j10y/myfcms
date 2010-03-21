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

function change(event){
 $("DetailNameRule").value=event.value;
}


</script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
	<body class="dialogBody">
	<form id="form2"><input type="hidden" id="ID" value="${ID}" />
	<table width="97%"  cellpadding="4" cellspacing="0" style="margin:10px auto;">
		<tr valign="top">
			<td  height="30" align="left">详细页命名规则：</td>
			<td align="left"><input  name="DetailNameRule" id="DetailNameRule"
						type="text" class="input1" value="${DetailNameRule}"
						size="55" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<fieldset><legend>可选详细页命名规则 </legend>
				<table width="100%" style="margin:3px auto">
				 <tr valign="top">
					<td align="left"><input on type="radio" name="rule" id="rule_1" value="/${catalogpath}/${document.id}.shtml" checked="checked" onclick="change(this)"/></td>
					<td height="30" align="left">按频道路径生成： /${catalogpath}/${document.id}.shtml</td>
	            </tr>
				<tr>
					<td align="left"><input type="radio" name="rule" id="rule_2" value="/${year}/${document.id}.shtml" onclick="change(this)"/></td>	
					<td height="30" align="left">年/文章ID.shtml生成： /${year}/${document.id}.shtml</td>
				</tr>
				<tr>
					<td align="left"><input type="radio" name="rule" id="rule_3" value="/${year}/${month}/${document.id}.shtml" onclick="change(this)"/></td>	
					<td height="30" align="left">年/月/文章ID.shtml生成： /${year}/${month}/${document.id}.shtml</td>
				</tr>
				<tr>
					<td align="left"><input type="radio" name="rule" id="rule_3" value="/${year}/${month}/${day}/${document.id}.shtml" onclick="change(this)"/></td>	
					<td height="30" align="left">年/月/日/文章ID.shtml生成： /${year}/${month}/${day}/${document.id}.shtml</td>
				</tr>
	               
				</table>
				</fieldset>
			</td>
		</tr>
	</table>
	</form>
	</body>
</html>
