<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片播放器</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>

var ImagePlayerID = "<%=request.getParameter("ImagePlayerID")%>";
Page.onLoad(function(){
	if(ImagePlayerID==""){
		tabClick("ImagePlayerBasic");
	}else{
		tabClick("ImagePlayerRela");
	}
});

function tabClick(tabid){
	Tab.onChildTabClick(tabid);
	Tab.getCurrentTab().src = tabid+".jsp?ImagePlayerID="+ImagePlayerID;
	return;
}

</script>
</head>
<body>
<z:init>
	<z:tab>
		<z:childtab id="ImagePlayerBasic" src="" afterClick="tabClick('ImagePlayerBasic')" selected="true">
			<img src="../Icons/icon039a1.gif" />
			<b>基本信息</b>
		</z:childtab>
		<z:childtab id="ImagePlayerRela" src="" afterClick="tabClick('ImagePlayerRela')">
			<img src="../Icons/icon039a13.gif" />
			<b>关联图片</b>
		</z:childtab>
		<z:childtab id="ImagePlayerPreview" src="" afterClick="tabClick('ImagePlayerPreview')">
			<img src="../Icons/icon039a12.gif" />
			<b> 预&nbsp;&nbsp;&nbsp;&nbsp;览 </b>
		</z:childtab>
	</z:tab>
</z:init>
</body>
</html>
