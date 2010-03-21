<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function ChgSite(){
	if($("isSite").checked){
		$("Catalog").disabled=true;
	}
	else{
		$("Catalog").disabled=false;
	}
}
</script>
</head>
<body class="dialogBody">
<z:init method="com.zving.platform.CustomColumnItem.initCopyTo">
<form id="form2">
<table border="0" cellpadding="4" cellspacing="" bordercolor="#DEDEDC" style="border-collapse:collapse;">
	<tr>
		<td width="96" height="12" class="tdgrey2" ></td>
		<td  class="tdgrey2" ></td>
	</tr>
	<tr>
      <td  align="right" valign="top" class="tdgrey1" >该站点所有栏目：</td>
      <td width="136" class="tdgrey2"><input name="isSite" type="checkbox" id="isSite" onClick="ChgSite();"/>
      </td>
    </tr>
	<tr>
      <td  align="right" valign="top" class="tdgrey1" >复制到其他栏目：</td>
      <td width="136" class="tdgrey2"><select name="Catalog" id="Catalog">
        ${optCatalog}
      </select></td>
    </tr>
    <tr>
		<td colspan="2" align="center">
			<input name="button" type="button" id="button" onClick="add3Save();" value="确  认"/>&nbsp;
			<input name="button2" type="button" onClick="window.close();" value="返  回"/>			</td>
	</tr>
</table>
<input name="IDs" id="IDs" type="hidden" value="${IDs}"/>
<input name="SiteID" id="SiteID" type="hidden" value="${SiteID}"/>
<input name="CatalogID" id="CatalogID" type="hidden" value="${CatalogID}"/>
</form>
</z:init>
</body>
</html>