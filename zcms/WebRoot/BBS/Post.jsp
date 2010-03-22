<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zving BBS</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css">
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
var url = window.location.search;
var flag = true;

Page.onLoad(function(){
	var dc = new DataCollection();
	dc.add("ThemeID",$V("ThemeID"))
	Server.sendRequest("com.zving.bbs.Post.addViewCount",dc);
	
	if(!$V("ThemeIDNext")){
		$('nextA').removeAttribute("href");
		$('nextA').disabled='disabled';
		$('nextA2').removeAttribute("href");
		$('nextA2').disabled='disabled';
	}
	if(!$V("ThemeIDPrevious")){
		$('previousA').removeAttribute("href");
		$('previousA').disabled='disabled';
		$('previousA2').removeAttribute("href");
		$('previousA2').disabled='disabled';
	}
	if($V("RuleReply")=="N"){
		$("postform").style.display = 'none';
	}
	
});

function isOperaterMember(ID, type) {
	var dc = new DataCollection();
	dc.add("ID", ID);
	
	Server.sendRequest("com.zving.bbs.Post.isOperaterMember",dc,function(response){
		if(response.Status==1) {
			switch(type) {
				case 1:	editPost(ID); return;
				case 2: del(ID); return;
				case 3: hide(ID);return;
			}
		} else {
			Dialog.alert("您无权对该用户进行操作！");
		}
		
	});
}



function save(){
	 var dc = Form.getData($F("postform"));
	 dc.add("ForumID",$V("ForumID"));
	 dc.add("ThemeID",$V("ThemeID"));
	 dc.add("SiteID",$V("SiteID"));
	 Server.sendRequest("com.zving.bbs.Post.add",dc,function(response){
			if(response.Status==1){
				Dialog.alert(response.Message);
				$S("Subject","");
				$S("Message","");
				DataList.loadData("list1");
			}else{
				Dialog.alert(response.Message);
			}
	});
}
function postNextOrPrevious(ThemeID,ForumID){
	var condition = $V("user");
	if(condition=="current"){
		condition = "&User=current";
	}else if(condition=="reply"){
		condition = "&User=reply";
	}else{
		condition="";
	}
	if($V("LastPoster").length>0 && $V("LastPoster")!="null"){
		condition = "&LastPoster="+$V("LastPoster");
	}
	window.location = "./Post.jsp?ThemeID="+ThemeID+"&ForumID="+ForumID+condition+"&SiteID="+$V("SiteID");
}

function reply(){
	if(url.indexOf("&ID=")!=-1){
		url = url.slice(0,url.lastIndexOf("&ID"));
	}
	window.location = "./PostAdd.jsp"+url;
}
function fastreply(layer,AddUser){
	$S("Subject","回复"+layer+"# "+AddUser+"的帖子");
	$("Message").focus();
}
function quote(ID){
	if(url.indexOf("&ID=")!=-1){
		url = url.slice(0,url.lastIndexOf("&ID"));
	}
	window.location = "./QuotePostAdd.jsp"+url+"&ID="+ID;
}
function del(ID){

	Dialog.confirm("确定删除选中的帖子吗？",function(){
		var dc = new DataCollection();
		dc.add("ID",ID);
		dc.add("SiteID",$V("SiteID"));
   		Server.sendRequest("com.zving.bbs.Post.del",dc,function(response){
			if(response.Status==1){
				DataList.loadData("list1");
			}else if(response.Status==2){
				window.location = "./Theme.jsp?ForumID="+$V("ForumID")+"&SiteID="+$V("SiteID");
			}
		});
	});
}
function edit(ID){
	if($V("editPriv")!="Y"){
		return;
	}

	if(flag){
		var dc = new DataCollection();
		dc.add("ID",ID);
		dc.add("SiteID",$V("SiteID"));
   		Server.sendRequest("com.zving.bbs.Post.edit",dc,function(response){
   			if(response.Status==1){
   				$("edit"+ID).innerHTML="<textarea height='400' rows='55' cols='140' id='EditTextarea'>"+response.get("Message")+"</textarea><br><input type='button' onclick='editSave("+ID+")' value='提交'><input type='button' onclick='cancel()' value='取消'>"
   				$("EditTextarea").focus();
   				flag = false;
   			}else{
   				Dialog.alert(response.Message);
   				DataList.loadData("list1");
   			}
   		});
   	}	
}
function editSave(ID){
	var dc = new DataCollection();
	dc.add("ID",ID);
	dc.add("SiteID",$V("SiteID"));
	dc.add("Message",$V("EditTextarea"));
	Server.sendRequest("com.zving.bbs.Post.editSave",dc,function(response){
		Dialog.alert(response.Message,function(){
			DataList.loadData("list1");
			flag = true;
		});
	});
}
function cancel(){
	DataList.loadData("list1");
	flag = true;
}
function editPost(ID){
	if(url.indexOf("&ID=")>=0){
		url = url.slice(0,url.lastIndexOf("&ID"));
	}
	window.location = "./EditPost.jsp"+url+"&ID="+ID;
}
function hide(ID){
		var dc = new DataCollection();
		dc.add("ID",ID);
   		Server.sendRequest("com.zving.bbs.Post.hide",dc,function(response){
			if(response.Status==1){
				DataList.loadData("list1");
			}
		});
}
function downLoad(attid){
	if(!attid||attid==""){
		return;
	}else{
		window.open(Server.ContextPath + "BBS/AttachDownLoad.jsp?id="+attid,"_blank");	
	}
}
function userinfo(UserName){
	var dc = new DataCollection();
	dc.add("UserName",UserName);
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.UserPersonalInfo.checkPriv",dc,function(response){
		if(response.Status==1){
			window.open("UserPersonalInfo.jsp?UserName="+UserName+"&SiteID="+$V("SiteID"));
		}else{
			
		}
	});
}

function resetSize(imgEle) {
	var width = imgEle.width;
	var height = imgEle.height;
	if(width>=height && width>500) {
		imgEle.width = 500;
		//imgEle.height = height*imgEle.width/width;
	}
	else if(width<height && height>800){
		imgEle.height = 800;
		imgEle.width = width *imgEle.height/height;
	}
}
</script>
<style type="text/css">
.defaultpost { height: auto !important; height:120px; min-height:120px !important; }
.signatures { height: expression(signature(this)); max-height: 100px; }
</style>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<div class="wrap">
<z:init method="com.zving.bbs.Post.init">
<div id="menu" class="forumbox"><span class="fl"><a href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; <a href="Theme.jsp?ForumID=${ForumID}&SiteID=${SiteID}">${Name}</a> &raquo; ${Subject} </span> <span class="fr">${Priv} </span></div>
<div id="foruminfo">
<div id="headsearch"></div>
</div>
<div style="padding:5px;">
<a href="javascript:postNextOrPrevious(${ThemeIDPrevious},${ForumIDPrevious})" id="previousA"> &lsaquo;&lsaquo; 上一主题 </a> |<a href="javascript:postNextOrPrevious(${ThemeIDNext},${ForumIDNext})" id="nextA"> 下一主题 &rsaquo;&rsaquo;</a></div>
	<input  type="hidden"  value="${ThemeIDPrevious}"  id="ThemeIDPrevious" />
	<input  type="hidden"  value="${ThemeIDNext}"  id="ThemeIDNext" />
	<input  type="hidden"  value="${ForumIDPrevious}" id="ForumIDPrevious">
	<input  type="hidden"  value="${ForumIDNext}"  id="ForumIDNext" />
	<input  type="hidden"  value="${ForumID}" id="ForumID">
	<input  type="hidden"  value="${ID}"  id="ThemeID" />
	<input  type="hidden"  value="${editPriv}"  id="editPriv" />
	<input  type="hidden"  value="${RuleReply}"  id="RuleReply" />
	<input  type="hidden"  value="${SiteID}"  id="SiteID" />
	

<z:datalist id="list1" method="com.zving.bbs.Post.getList" page="true" size="12">
	<form method="post">
	<div class="mainbox viewthread forumbox"><span class="fr"> <!-- a href="my.jsp?item=favorites&amp;tid=6390" id="ajax_favorite" onClick="ajaxmenu(event, this.id, 3000, 0)">收藏</a> | <a href="my.jsp?item=subscriptions&amp;subadd=6390" id="ajax_subscription" onClick="ajaxmenu(event, this.id, 3000, null, 0)">订阅</a> | <a href="misc.jsp?action=emailfriend&amp;tid=6390" id="emailfriend" onClick="ajaxmenu(event, this.id, 9000000, null, 0)">推荐</a> | <a href="viewtheme.jsp?action=printable&amp;tid=6390" target="_blank">打印</a--> </span>
	${FirstSubject}
	<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
		<tr>
			<td class="postauthor">
			<div class="avatar"><img src='${HeadImage}' onload="if(this.width>128)this.width='128'"></div>
			<cite><a href="javascript:void(0)" onclick="userinfo('${AddUser}')">${AddUser}</a></cite>
			<p><span style="color:${Color}"><b>${GroupName}</b></span></p>
			<div id="userinfo17435_menu">
			<ul>
				<li>昵称：${NickName}&nbsp;</li>
				<li>发帖数:${ThemeCount}&nbsp;</li>
				<li>回帖数:${ReplyCount}&nbsp;</li>
				<li>积分:${ForumScore}&nbsp;</li>
				<li>注册时间:${RegisterTime}&nbsp;</li>
				<li>上次登录:${LastLoginTime}&nbsp;</li>
				<!--li>最后登录：2009-6-25&nbsp;</li-->
			</ul>
			</div>
			</td>
			<td class="postcontent" ondblclick="edit(${ID})">
			<div class="postactions fr">
			<p>${Button}|<a href="#"><strong onClick="window.scrollTo(0,0);" title="顶部" >TOP</strong></a> </p></div>
			<div class="postinfo"> <strong title="" id="" onClick="">${Layer}<sup>#</sup></strong> 发表于 ${AddTime}&nbsp;</div>
            <div ><h5>${Subject}</h5>
			<div id="edit${ID}">			
				${Message}
			</div>
			<br>
			${File}
			</div>
			</td>
		</tr>
	</table>
	</div>

	</form>
</z:datalist>
<z:pagebar target="list1"></z:pagebar>
<div style="padding:5px;">
<div><span id="newspecial" class="fr">${NewReplyButton}&nbsp;&nbsp;${NewThemeButton}</span></div>
<a href="javascript:postNextOrPrevious(${ThemeIDPrevious},${ForumIDPrevious})" id="previousA2"> &lsaquo;&lsaquo; 上一主题 </a> | <a href="javascript:postNextOrPrevious(${ThemeIDNext},${ForumIDNext})" id="nextA2"> 下一主题 &rsaquo;&rsaquo;</a></div>
<input type="hidden" value="<%=request.getParameter("User")%>" id="user" >
<input type="hidden" value=<%=request.getParameter("LastPoster")%> id="LastPoster" >
</z:init>
<form method="post" id="postform">
<div id="quickpost" class="forumbox">
<h4>快速回复主题</h4>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
	<tr valign="top">
		<td width="160">
		<!-- div>
		<h5>选项</h5>
		<p><label> <input type="checkbox" name="parseurloff" id="parseurloff" value="1"> 禁用 URL 识别</label></p>
		<p><label> <input type="checkbox" name="smileyoff" id="smileyoff" value="1"> 禁用 <a href="faq.jsp?action=message&amp;id=32" target="_blank">表情</a></label></p>
		<p><label> <input type="checkbox" name="bbcodeoff" id="bbcodeoff" value="1"> 禁用 <a href="faq.jsp?action=message&amp;id=18" target="_blank">Discuz!代码</a></label></p>
		<p><label> <input type="checkbox" name="usesig" value="1"> 使用个人签名</label></p>
		<p><label> <input type="checkbox" name="emailnotify" value="1"> 接收新回复邮件通知</label></p>
		</div-->
		</td>
	</tr>
	<tr>
		<td class="edit_content_text" >
		标题：<input name="Subject" type="text" class="text" id="Subject" style="width:200px;" />
	
		</td>
	</tr>
	<tr>
		<td>内容：<textarea name="Message" rows="10" cols="20" id="Message" class="textarea" style="height:100px;width:800px;" verify="NotNull"></textarea></td>
	</tr>
	<tr>
		<td  align="middle"><input name="replysubmit" type="button" id="postsubmit" tabindex="3" value="发表帖子" onClick="save()"><input type="reset" value="清空内容" ></td>
	</tr>
	
</table>
</div>
</form>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
