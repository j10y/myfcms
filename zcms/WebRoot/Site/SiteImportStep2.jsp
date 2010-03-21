<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>站点</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	if(_DialogInstance){
		_DialogInstance.resize(800,360);
	}
	DataGrid.select($("dg1"),"0");
	_DialogInstance.OKButton.onclick = Parent.executeImportSite;
	Dialog.endWait();
});
</script>
</head>
<body scroll="no">
<input type="hidden" id="FileName" value="${FileName}">
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6">
				<tr>
					<td valign="middle" class="blockTd"> <img
						src="../Icons/icon040a1.gif" width="20" height="20" />&nbsp;请选择导入到哪个站点： </td>
				</tr>
				<tr>
					<td
						style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg1" method="com.zving.cms.site.Site.importStep2DataBind" multiSelect="false" size="10">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="5%" ztype="rowNo">序号</td>
								<td width="4%" ztype="selector" field="id">&nbsp;</td>
								<td width="12%"><b>导入方式</b></td>
								<td width="23%"><b>站点名称</b></td>
								<td width="15%"><b>站点代码</b></td>
								<td width="41%"><b>域名</b></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>${Type}</td>
								<td>${Name}</td>
								<td>${Alias}</td>
								<td>${URL}</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="9" align="center">${PageBar}</td>
							</tr>
						</table>
					</z:datagrid></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html>
</z:init>