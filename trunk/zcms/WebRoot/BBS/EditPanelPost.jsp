<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zving BBS</title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script src="../Framework/Controls/StyleToolbar.js"></script>
<script src="../Editor/fckeditor.js"></script>
 <script src="../Framework/Spell.js"></script>
<script type="text/javascript">
function save(){
    if(Verify.hasError()){
		return;		     
    }

	var dc = Form.getData($F("postform"));
	editor = FCKeditorAPI.GetInstance('Content');
    var content = editor.GetXHTML(false);
    dc.add("Message",content);
    dc.add("ID",$V("PostID"));
	Server.sendRequest("com.zving.bbs.PostAdd.editPost",dc,function(response){
		if(response.Status==1){
			window.location = "./MyPosts.jsp";
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function quit() {
	window.location = "./MyArticles.jsp";
}

Page.onLoad(function(){
	$("_Content").focus();
});

</script>

</head>
<body>

<br>
<z:init method="com.zving.bbs.PostAdd.initEdit">
	<form method="post" id="postform"
		action="post.jsp?action=newtheme&fid=5&extra=page%3D1&topicsubmit=yes"
		enctype="multipart/form-data"><input
		type="hidden" name="ForumID" id="ForumID" value="${ForumID}">
	<input type="hidden" name="ThemeID" id="ThemeID" value="${ThemeID}">
	<input type="hidden" name="PostID" id="PostID" value="${ID}">
	<div class="forumbox">
	<h4>修改回复</h4>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="table-layout: auto;"
		bordercolor="#eeeeee" class="forumTable">
		<thead>
			<tr>
				<th align="right" style="text-align:right" width="150">用户名</th>
				<td>${AddUser}</td>
			</tr>
		</thead>
		<tr>
			<th align="right" style="text-align:right"><label for="subject">标题</label></th>
			<td><input type="text" name="subject" id="subject" size="45"
				tabindex="3" value="${Subject}" verify="NotNull"></td>
		</tr>

		<tbody id="themetypes"></tbody>



		<tr>
			<td align="right" valign="top"><!--<label for="posteditor_textarea">
			<b>内容</b> </label>
			<div id="posteditor_left">
			<ul>
				<li>Html 代码 <em>禁用</em></li>
				<li><a href="faq.jsp?action=message&id=18" target="_blank">Discuz!代码</a>
				<em>可用</em></li>
				<li>[img] 代码 <em>可用</em></li>
			</ul>
			<div style="clear: both;">
			<ul>
				<li><label><input type="checkbox" name="parseurloff"
					id="parseurloff" value="1"> 禁用 URL 识别</label></li>
				<li><label><input type="checkbox" name="smileyoff"
					id="smileyoff" value="1"> 禁用 <a
					href="faq.jsp?action=message&id=32" target="_blank">表情</a></label></li>
				<li><label><input type="checkbox" name="bbcodeoff"
					id="bbcodeoff" value="1"> 禁用 <a
					href="faq.jsp?action=message&id=18" target="_blank">Discuz!代码</a></label></li>
				<li><label><input type="checkbox" name="tagoff"
					id="tagoff" value="1"> 禁用 标签解析</label></li>
				<li><label><input type="checkbox" name="usesig"
					value="1"> 使用个人签名</label></li>
				<li><label><input type="checkbox" name="emailnotify"
					value="1"> 接收新回复邮件通知</label></li>
			</ul>
			</div>-->
            </td>
			<td valign="top">
			<div id="posteditor">
			<div id="Toolbar" style="height:52px;"></div>
                    <textarea id="_Content" name="_Content" style=" display:none;" >${Message}</textarea>
                    <script type="text/javascript">
						var sBasePath = Server.ContextPath+"Editor/" ;
						var oFCKeditor = new FCKeditor( 'Content' ) ;
						oFCKeditor.BasePath	= sBasePath ;
						oFCKeditor.ToolbarSet="Mail"
						oFCKeditor.Width  = '100%';
						oFCKeditor.Height = 270;
						oFCKeditor.Config['EditorAreaCSS'] = '${CssPath}';
						oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:Toolbar' ;
						oFCKeditor.Value = $V("_Content");
						oFCKeditor.Create() ;
				    </script>
			<table align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right">
					<!-- button type="button" id="checklength">字数检查</button>
					&nbsp;
					<button type="button" name="previewbutton" id="previewbutton"
						tabindex="102">预览帖子</button>
					&nbsp;
					<button type="button" tabindex="103" id="clearcontent">清空内容</button>
					</td>
				</tr>
			</table>
			<fieldset class="fields2" style="clear:both;"><legend>上传附件</legend>
			<label for="fileupload">文件:</label> <input type="file"
				class="inputbox autowidth" value="" maxlength="262144"
				id="fileupload" name="fileupload"> <input type="submit"
				onClick="upload = true;" class="button2" value="添加文件"
				name="add_file"> <label for="filecomment">文件注释:</label> <input
				name="filecomment" type="text" id="filecomment" size="40"></fieldset-->

			</td>
		</tr>
		<tr>
			<td><input name="topicsubmit" type="button" value="确认修改" onClick="save();"></td>
			<td><input align="left" type="button" value="取消" onclick="quit();" /></td>
		</tr>
	</table>
	</div>
	</td>
	</tr>
	</table>
	</div></form>
	</z:init>
</body>
</html>
