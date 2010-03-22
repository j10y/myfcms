<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zving BBS</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/StyleToolbar.js"></script>
<script src="../Editor/fckeditor.js"></script>
 <script src="../Framework/Spell.js"></script>
<script type="text/javascript">
iIndex = 0;
var annex = 0;

function afterUpload(pathAll){
    if(Verify.hasError()){
		return;		  
    }
	var dc = Form.getData($F("postform"));
	var indexs = "";
	for(var i=0;i<=annex;i++){
		if(dc.get("file"+i)){
			indexs += i+",";
		}
	}
	dc.add("indexs",indexs);
	dc.add("file",pathAll);	
	dc.add("SiteID",$V("SiteID"));		
	editor = FCKeditorAPI.GetInstance('Content');
    var content = editor.GetXHTML(false);
 
    dc.add("Message",content);
	Server.sendRequest("com.zving.bbs.PostAdd.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.location = "./Post.jsp"+window.location.search;
			}
		});
	});
}
function check(){
	var dc = Form.getData($F("postform"));
	editor = FCKeditorAPI.GetInstance('Content');
    var content = editor.GetXHTML(true);
    Dialog.alert(content.length);
}

function getIndex()
{
 iIndex = event.srcElement.parentElement.parentElement.rowIndex;
 return iIndex;
}


function insertRow(iPos)
{
 var myTable = document.getElementById("myTable");
 var otr=myTable.insertRow(annex);//插入一个tr

 var ocell=otr.insertCell(0);//插入一个td
    ocell.innerHTML="<label for='fileupload'>文件:</label> <input type='file'  id='file"+annex+"' size='30' name=annexValue(" + annex + ")>";
 
 var ocell=otr.insertCell(1);//插入一个td
 ocell.innerHTML=" <input type=button onclick=deleteRow(getIndex(this)) value='删除附件'>";
 
 annex++;
}

function deleteRow(iPos)
{
 var myTable = document.getElementById("myTable");
 myTable.deleteRow(iPos);
 annex--;
}


</script>

</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div class="wrap">
<z:init method="com.zving.bbs.PostAdd.init">
<input type="hidden" value="${SiteID}" id="SiteID">
<div id="menu" class="forumbox"><span class="fl"> <a href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; <a
	href="Theme.jsp?ForumID=${ForumID}&Name=${Name}&SiteID=${SiteID}">${Name}</a> &raquo; <a
	href="Post.jsp?ThemeID=${ID}&ForumID=${ForumID}&SiteID=${SiteID}">${Subject}</a> &raquo; 发表回复 </span> <span class="fr">${Priv}</span>
</div>
<div id="previewtable" style="display: none">
<h1>预览帖子</h1>
<table cellspacing="0" cellpadding="0">
	<tr>
		<td>达不溜哎爱嗯机</td>
		<td>
		<div id="previewmessage">
		<h2></h2>
		</div>
		</td>
	</tr>
</table>
</div>
<input type="hidden" value="${AddUser}" id="AddUser">
</z:init>
<br>
<z:init method="com.zving.bbs.PostAdd.initAddDialog">
<input type="hidden" value="${SiteID}" id="SiteID">
	<div style="display:none"><iframe name="formTarget"  src="javascript:void(0)"></iframe></div>
	<form method="post" id="postform"
		action="AttachmentUpload.jsp?Path=/BBS/Upload/File/"
		target="formTarget" 
		enctype="multipart/form-data"><input
		type="hidden" name="ForumID" id="ForumID" value="${ForumID}">
	<input type="hidden" name="ThemeID" id="ThemeID" value="${ThemeID}">
	<input type="hidden" name="PostID" id="PostID" value="${ID}">
	<div class="forumbox">
	<h4>发表回复</h4>
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
				tabindex="3" value="${subject}" verify="NotNull"></td>
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
                    <textarea id="_Content" name="_Content" style=" display:none;" >${Content}</textarea>
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
			<!--table align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right">
					< button type="button" id="checklength">字数检查</button>
					&nbsp;
					<button type="button" name="previewbutton" id="previewbutton"
						tabindex="102">预览帖子</button>
					&nbsp;
					<button type="button" tabindex="103" id="clearcontent">清空内容</button>
					</td>
				</tr>
			</table-->
			<fieldset  class="fields2" style="clear:both;"><legend>上传附件</legend>
				<input type="button" value="添加附件" onClick="insertRow(0)">
				<table id="myTable"></table>
			</fieldset>

			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td colspan="6" align="middle"><input name="topicsubmit"
				type="submit" value="发表新主题" > &nbsp;&nbsp; &nbsp;</td>
			<table id="myTable"></table>
		</tr>
	</table>
	</div>
	</td>
	</tr>
	</z:init>
	</table>
	</div></form>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
