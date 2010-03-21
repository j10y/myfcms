<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>图片播放器</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function add(){
	var dc = Form.getData($F("form2"));
	if(Verify.hasError()){
		return;
	}
	dc.add("Prop1",$NV("Prop1"));
	Server.sendRequest("com.zving.cms.site.ImagePlayerBasic.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.parent.Parent.DataGrid.loadData("dg1");
				window.parent.location = Server.ContextPath+"Site/ImagePlayerDialog.jsp?ImagePlayerID="+response.get("ImagePlayerID");
			}
		});
	});
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.site.ImagePlayerBasic.init">
	<form id="form2" method="post">
	<table width="100%"
		style="border-bottom:1px solid #DFE3EE; line-height:34px;">
		<tr>
			<td style="padding:4px 5px;"><z:tbutton onClick="add();">
				<img src="../Icons/icon039a16.gif" />保存</z:tbutton></td>
		</tr>
		<tr>
			<td style="padding:0px 5px;">
			<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr class="dataTableHead">
					<td height="30" width="3%">&nbsp;</td>
					<td height="30" width="15%"><b>项目</b></td>
					<td height="30" width="82%"><b>值</b></td>
				</tr>
				<tr style="background-color:#FFFFFF">
					<td>&nbsp;</td>
					<td align="right">图片播放器名称：</td>
					<td><input name="Name" type="text" value="${Name}"
						class="input1" id="Name" verify="图片播放器名称|NotNull" /> <input
						name="ImagePlayerID" type="hidden" id="ImagePlayerID"
						value="${ImagePlayerID}" /></td>
				</tr>
				<tr style="background-color:#FFFFFF">
					<td>&nbsp;</td>
					<td align="right"><span style="text-align:right;">图片播放器代码：</span></td>
					<td><input name="Code" type="text" value="${Code}"
						class="input1" id="Code" verify="图片播放器代码|NotNull" /></td>
				</tr>
				<tr style="background-color:#FFFFFF">
					<td>&nbsp;</td>
					<td align="right"><span style="text-align:right;">宽度：</span></td>
					<td><input name="Width" type="text" value="${Width}"
						class="input1" id="Width" verify="宽度|NotNull&&Int" />像素(px)</td>
				</tr>
				<tr style="background-color:#FFFFFF">
					<td>&nbsp;</td>
					<td align="right"><span style="text-align:right;">高度：</span></td>
					<td><input name="Height" type="text" value="${Height}"
						class="input1" id="Height" verify="高度|NotNull&&Int" />像素(px)</td>
				</tr>
				<tr>
				  <td>&nbsp;</td>
				  <td align="right"><span style="text-align:right;">是否显示文字</span></td>
				  <td>${radiosProp1}</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
