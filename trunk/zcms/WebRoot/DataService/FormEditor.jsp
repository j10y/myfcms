<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; gbk" />
<title>ZCMS智能表单设计器</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
#wrapper{margin:0; border:0; height:100%; overflow:hidden;}
-->
</style>
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
<script src="../Editor/fckeditor.js"></script>
<script language="JavaScript" type="text/javascript">
function getHTML(){
	var editor = FCKeditorAPI.GetInstance('Content');
    return editor.GetXHTML(false);
}
function setHTML(html){
	var editor = FCKeditorAPI.GetInstance('Content');
    return editor.SetHTML(html,true);
}
</script>
</head>
<body>
<z:init method="com.zving.cms.dataservice.Form.initContentDialog">
  <div id="wrapper">
    <input type="hidden" id="ID" value="${ID}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#F6F9FD" style="border:#B7D8ED 1px solid;">
      <tr>
        <td valign='top'>
		<div id="_DivContainer"	style="text-align:center;overflow:auto;height:398px;width:100%; position:relative">
            <input name="textarea" id="_Content" type="hidden" value="${Content}">
            <script type="text/javascript">
				var sBasePath = Server.ContextPath+"Editor/" ;
				var oFCKeditor = new FCKeditor( 'Content' ) ;
				oFCKeditor.BasePath	= sBasePath ;
				oFCKeditor.ToolbarSets = "Deisigner";
				oFCKeditor.Width = "98%" ;
				oFCKeditor.Height = "100%" ;
				oFCKeditor.Value = $V("_Content");
				oFCKeditor.Create() ;
		  </script>
	  </div></td>
      </tr>
    </table>
  </div>
</z:init>
</body>
</html>
