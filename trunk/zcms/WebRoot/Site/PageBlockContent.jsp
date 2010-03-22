<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<z:init method="com.zving.cms.site.PageBlock.init">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<title></title>
	<script src="../Framework/Main.js"></script>
	<script src="../Framework/Controls/Tabpage.js"></script>
	<script src="../Editor/fckeditor.js"></script>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
	<div id="content">
	<form id="form2">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td><textarea name="textarea" id="_Content" style="display:none"
				verify="自定义内容|NotNull"
				conditon="parent.Tab.getChildTab('Basic').contentWindow.$NV('Type')==3">
				  ${Content}
				  </textarea> <script type="text/javascript">
					var sBasePath = Server.ContextPath+"Editor/" ;
					var oFCKeditor = new FCKeditor( 'Content' ) ;
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.Width = 470 ;
					oFCKeditor.Height = 220 ;
					oFCKeditor.ToolbarSet = "Basic";
					oFCKeditor.Value = $V("_Content");
					oFCKeditor.Create() ;
				  </script></td>
		</tr>
	</table>


	</form>
	</div>
	<p>&nbsp;</p>
	</body>
</html>
</z:init>
