<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<style type="text/css">
<!--
.style1 {color: #D4D0C8}
-->
</style>
</head>
<body class="dialogBody">
<z:init method="com.zving.cms.resource.Video.initPlayDialog">
	<form id="form2">
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td height="240" colspan="2" align="center" valign="middle">
			<div id="container"><script type='text/javascript'
				src='../Tools/Swfobject.js'></script> <script type='text/javascript'>
var s1 = new SWFObject('../Tools/player.swf','player','340','250','9',"#FFFFFF");
s1.addParam('allowfullscreen','true');
s1.addParam('allowscriptaccess','always');
s1.addParam('flashvars','file=${files}');
s1.write('container');
</script></div>
			<!--object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="320" height="200">
          <param name="movie" value="../Tools/player.swf">
		  <param name="FlashVars" value="file=${files}&IsAutoPlay=1">
          <param name="quality" value="high">
	  	  <param name="wmode" value="transparent" />
		  <param name="allowFullScreen" value="true" />
		  <param name="IsAutoPlay" value="true">
          <embed wmode="transparent" FlashVars="file=${files}&IsAutoPlay=1" src="../Tools/player.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" allowFullScreen="true" IsAutoPlay="true" type="application/x-shockwave-flash" width="320" height="200"></embed>
	      </object--></td>
		</tr>
	</table>
	</form>
</z:init>
</body>
</html>
