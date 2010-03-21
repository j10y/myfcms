<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.document.ArticleHistory.basicInit">
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>文章编辑---${Title}</title>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<script src="../Framework/Main.js"></script>
	<script src="../Editor/fckeditor.js"></script>
	<script>
var CatalogID = '';
var ChannelID = '';
var TempArticleID = '';
var pWin = window.parent;

function htmlDecode(str) {
	return str.replace(/\&quot;/g,"\"").replace(/\&lt;/g,"<").replace(/\&gt;/g,">").replace(/\&nbsp;/g," ").replace(/\&amp;/g,"&");
}


Page.onLoad(function(){
	var ieHeight = 187;
	if(navigator.appVersion.indexOf("MSIE 7")!=-1){
		ieHeight= 260;
	}
	var height = isIE?(window.screen.height-ieHeight)+"px":(window.screen.height-185)+"px";
	//alert(height);
	window.parent.$("f1").height=height;
});

</script>
	<style>
body{ text-align:left; font-size:12px;color:#666666; margin:0px;}
</style>
	</head>
	<body>
	<div id="_EditView" style="background-color:#666666"><br>
	<table width="100%" border="0">
		<tr>
			<td align="center">
			<form id="form1">
			<table width="750" border="0" align="center" cellpadding="20"
				bgcolor="#FFFFFF" style="margin:5px">
				<tr>
					<td>
					<table width="100%" cellpadding="2" cellspacing="0" id="ORGTree">

						<tr>
							<td width="8%" height="23" align="center"><strong>短标题</strong></td>
							<td height="23" align="left"><input name="ShortTitle"
								type="text" class="input1"
								style="background:url(Images/rule.gif) repeat-x left bottom;"
								id="ShortTitle" size="50" value="${ShortTitle}" /> <input
								type="hidden" ID="CatalogID" value="${CatalogID}"> <input
								type="hidden" ID="ArticleID" value="${ID}"> <input
								type="hidden" ID="ID" value="${ID}"> <input
								type="hidden" ID="TempID" value="${TempID}"></td>
						</tr>
						<tr>
							<td width="8%" height="23" align="center"><strong>标题</strong></td>
							<td height="23" align="left"><input name="Title"
								type="text" class="input1" id="Title" size="100"
								value="${Title}" /></td>
						</tr>
						<tr>
							<td height="23" align="center"><strong>副标题</strong></td>
							<td height="23" align="left"><input name="SubTitle"
								type="text" class="input1" id="SubTitle" size="50"
								value="${SubTitle}" /> <strong>作&nbsp;者 <input
								name="Author" type="text" class="input1" id="Author" size="34"
								value="${Author}" /> </strong></td>
						</tr>
						<tr></tr>

						<tr>
							<td height="6" align="center"><strong>关键字</strong></td>
							<td height="6" align="left">
							<p><input name="keyword" type="text" class="input1"
								id="Keyword" size="50" value="${Keyword}" /></p>
							</td>
						</tr>
						${CustomColumn}
					</table>
					<!--高级选项-->
					<div id="DivRedirect" style="display:none">
					<table width="100%" cellpadding="2" cellspacing="0">
						<tr>
							<td width="8%" height="23" align="center"><strong>转向链接</strong></td>
							<td height="23" align="left"><input name="RedirectURL"
								type="text" class="input1" id="RedirectURL" size="50"
								value="${RedirectURL}" /></td>
						</tr>
					</table>
					</div>
					<div id="DivContent">
					<table width="100%" height="400" cellpadding="2" cellspacing="0"
						class="dataTable" id="ORGTree">
						<tr>
							<td height="23" align="center"><textarea name="textarea"
								id="_Content" style="display:none">
				  ${Content}
				  </textarea> <!--系统类型，区分CMS/OA/CRM等--> <input type="hidden" id="System"
								name="System" value="CMS"> <script
								type="text/javascript">
					var sBasePath = Server.ContextPath+"/Editor/" ;
					var oFCKeditor = new FCKeditor( 'Content' ) ;
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.Width = 700 ;
					oFCKeditor.Height = 800 ;
					oFCKeditor.ToolbarSet = "View" ;
					oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:parent(xToolbar)' ;
					oFCKeditor.Value = htmlDecode($V("_Content"));
					//oFCKeditor.Value = "abc";
					oFCKeditor.Create() ;
					
				  </script></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</form>
			</td>
		</tr>
	</table>
	</div>
	</body>
	</html>
</z:init>
