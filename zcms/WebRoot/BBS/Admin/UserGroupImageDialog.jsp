<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../Framework/Main.js"></script>
<script src="../../Framework/Spell.js"></script>
<script type="text/javascript">
function selectImage(value) {
	if(value == 'InternetImage') {
		$("selectedImage").src=$("InternetImage").value;
		$S("select",$("InternetImage").value);
	} else {
		$("selectedImage").src= value;
		$S("select",value);
	}
}

</script>
</head>
<z:init method="com.zving.bbs.admin.ForumUserGroup.init">
<body>
<form id="form1">
	<input id="select" type="hidden" value="${originalImage}">
	<input id="ID" type="hidden" value="${ID}">
	<table width="100%" border="0" cellspacing="6" cellpadding="0" >
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_1.jpg');"><img src="../Images/SystemHeadImage/headImage_1.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_2.jpg');"><img src="../Images/SystemHeadImage/headImage_2.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_3.jpg');"><img src="../Images/SystemHeadImage/headImage_3.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_4.jpg');"><img src="../Images/SystemHeadImage/headImage_4.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_5.jpg');"><img src="../Images/SystemHeadImage/headImage_5.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_6.jpg');"><img src="../Images/SystemHeadImage/headImage_6.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_7.jpg');"><img src="../Images/SystemHeadImage/headImage_7.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_8.jpg');"><img src="../Images/SystemHeadImage/headImage_8.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_9.jpg');"><img src="../Images/SystemHeadImage/headImage_9.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_10.jpg');"><img src="../Images/SystemHeadImage/headImage_10.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_11.jpg');"><img src="../Images/SystemHeadImage/headImage_11.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_12.jpg');"><img src="../Images/SystemHeadImage/headImage_12.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_13.jpg');"><img src="../Images/SystemHeadImage/headImage_13.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_14.jpg');"><img src="../Images/SystemHeadImage/headImage_14.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_15.jpg');"><img src="../Images/SystemHeadImage/headImage_15.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_16.jpg');"><img src="../Images/SystemHeadImage/headImage_16.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_17.jpg');"><img src="../Images/SystemHeadImage/headImage_17.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_18.jpg');"><img src="../Images/SystemHeadImage/headImage_18.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_19.jpg');"><img src="../Images/SystemHeadImage/headImage_19.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_20.jpg');"><img src="../Images/SystemHeadImage/headImage_20.jpg" width="60" height="60"></a>
		<a href="javascript:void(0)" onclick="selectImage('../Images/SystemHeadImage/headImage_21.gif');"><img src="../Images/SystemHeadImage/headImage_21.gif" width="60" height="60"></a>
		<br/><br/>
		<p align="center">当前组图像:</p>
		<p align="center"><input id="selectedImage" type="image" src="${originalImage}" width="60" height="60" disabled="disabled" /></p><br/>
		
		<p>&nbsp;使用网络连接图片:</p>
		<p>&nbsp;<input type="text" id="InternetImage" size="40" />&nbsp;<input type="button" value="替换" onclick="selectImage('InternetImage');" /></p>
	</table>
</form>
</body>
</z:init>
</html>