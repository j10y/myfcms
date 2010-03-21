<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.zving.framework.User"%>
<%@taglib uri="controls" prefix="z"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>评论</title>
<%
//String ip = request.getRemoteAddr(); 
//String user = request.getParameter("CmntUserName");
	String isLogin = "N";
	if(User.isLogin()){
		isLogin = "Y";
	}
%>

<script src="../Framework/Main.js" type="text/javascript"></script>
<script><!--
var flag = 0;
Page.onLoad(function(){
	if($V("isLogin")=="Y"){
		$("textLogin").style.display = "none";
	}
});
function checkComment(){
  var content = document.getElementById("CmntContent");
   if(content.value.trim() == ""){
     alert("评论内容不能为空!");
     content.focus();
     return false;
   }
  return true;
}

function restore(ID,CatalogID,RelaID,CatalogType,SiteID){
	if(flag==0) {
		var subStr="登录名：<input type='text' name='CmntUserName' "+
				"id='CmntUserName' class='txt' /> 密码：<input type='password' name='CmntPwd' "+
				"id='CmntPwd' class='txt' /> <input	type='checkbox' name='CmntCheckbox' checked "+
				"/>匿名发表";
		var str = "<form action='Comment.jsp' method='post' "+
				"onsubmit='return checkComment();'><input type='hidden' "+
				"id='RelaID' name='RelaID' value='"+RelaID+"' /> <input type='hidden' "+
				"id='CatalogID' name='CatalogID' value='"+ CatalogID +"' /> <input "+
				"type='hidden' id='CatalogType' name='CatalogType' value='"+CatalogType+"' /> "+
				"<input type='hidden' id='SiteID' name='SiteID' value='"+SiteID+"' /> "+
				"<input type='hidden' id='ParentID' name='ParentID' value='"+ID+"'/> "+
				"<textarea cols='78' rows='8' name='CmntContent' "+
				"id='CmntContent'></textarea><br/>";
		if(!($V("isLogin")=="Y")){
			str+=subStr;
		}
		str+="<input type='submit' align='right' name='CmntSubmit' "+
				"id='CmntSubmit' value='提交评论'	class='btn' /></form>";
		$(ID+"textarea").innerHTML=str;
		/*$(ID+"deliver").innerHTML="<a href=''>发表回复</a>";*/
		flag = 1;
	} else {
		$(ID+"textarea").innerHTML="";
		
		flag = 0;
	}
	
}

function updateComment(ID,AddUser,AddTime,AddUserIP) {
	var dc = new DataCollection();
	dc.add("ID",ID);
	dc.add("restoreContent",$V('CmntContent'));
	dc.add("restoreAddUser",AddUser);
	dc.add("restoreAddTime",AddTime);
	dc.add("restoreAddUserIP",AddUserIP);
	
	Server.sendRequest("com.zving.cms.dataservice.Comment.updateSupporterCount",dc,function(response){
		if(response.Status==1){	
			Dialog.alert(response.Message);
			window.parent.location.reload();
		}
	});
}

function addSupporterCount(ID){
	
	var id = ID+"supporter";
	var dc = new DataCollection();
	dc.add("ID",ID);
	
	Server.sendRequest("com.zving.cms.dataservice.Comment.addSupporterCount",dc,function(response){
		  if(response.Status==1){
		  	$(id).innerHTML = "(" + response.get("count") + ")";
    		// alert("您的评论提交成功！");
    		Dialog.alert(response.Message);
		  }
	});
}

function addAntiCount(ID){
	var id = ID+"anti";
	var dc = new DataCollection();
	dc.add("ID",ID);
	
	Server.sendRequest("com.zving.cms.dataservice.Comment.addAntiCount",dc,function(response){
	  	if(response.Status==1){
	  		$(id).innerHTML = "(" + response.get("count") + ")";
	  		// alert("您的评论提交成功！");
	  		Dialog.alert(response.Message);
	  	}
	});
}
--></script>
<!-- comment header begin -->
<style type="text/css">
.commentBox{
	border:1px solid #c8d8f2;
	background-color:#f5f8fd;
	padding:17px 10px;
	font:12px "宋体";
	margin:10px auto;
	text-align:left;
	max-width:950px
}
.commentBox h2{
	background:url(../Images/comment.gif) no-repeat left 2px;
	font-size:14px;
	margin:0;
	padding:0 0 9px 15px;
	border-bottom:1px dashed #c8d8f2;
}
.commentBox h2 a{
	 float:right;
	 _display:inline;
	 margin-top:-10px;
	 *margin-top:-30px;
	 font-size:12px;
	 color:#000099;
	 text-decoration:none;
}
.commentBox h2 a span{
	color:#cc0000;
}
.commentBox h2 a:hover, .commentBox h2 a:active{
	color:#c00;
	text-decoration:underline;
}
.commentContent{_padding-bottom:1px}
.commentContent .time{
	color:#666;
	line-height:20px;
	padding:4px 0 0 5px;
}
.commentContent .content{
	line-height:20px;
	padding:2px 0 2px 5px;
}
.commentContent .fenxiang{
	color:#0033cc;
	float:right;
	height:21px;
	padding:3px 3px 0;
	text-align:center;
}
.commentContent .fenxiang a{
	color:#0033cc;
	text-decoration:none;
}
.commentContent .fenxiang a:hover, .commentContent .fenxiang a:active{
	text-decoration:underline;
}
.commentContent .line{
	clear:both;
	background:url(../Images/line.gif) repeat-x left top;
	height:1px;
	font-size:1px;
}
.commentContent .page{
	margin:10px 0;
	padding:5px;
	text-align:center;
}
.commentContent .page a{
	font-weight:bold;;
	display:inline-block;
}
.commentContent .page a:link, .commentContent .page a:visited{
	color:#000;
}
.commentContent .page a:hover, .commentContent .page a:active{
	color:#cc0000;
	text-decoration:underline;
}
.commentContent .page a.pagefirst:link, .commentContent .page a.pagefirst:visited{
	text-decoration:underline;
	color:#cc0000;
}
.commentContent .page .pagebtn{
	display:inline-block;
	width:53px;
	border:1px solid #ccc;
	background-color:#fff;
	padding:2px 3px;
	font-weight:normal;
	text-decoration:none;
}
.commentContent form{
	margin:0;
}
.commentContent .textBox textarea{
	width:97%;
	height:100px;
	margin:10px 1%;
	_margin:10px 0.5%;
}
.commentContent input.txt{
	width:100px;
}
.commentContent input.btn{
	width:130px;
}
.comment{ background-color:#f5f8fd; text-align:center; padding:10px; text-align:left;}
.comment div,
.comment div.huifu{ padding:2px; margin:2px;}
.comment div.huifu{ border:1px solid #aaa; padding:2px; background-color:#FEFEED; margin:0 auto; text-align:left;}
.comment div.time,
.comment div.content{ margin:0; padding:2px;}
</style>
</head>

<body>
<z:init method="com.zving.cms.dataservice.CommentService.init">
	<div id="commentBox" class="commentBox">

	<h2>网友评论 <a
		href="${ServicesContext}${CommentListPageJS}?RelaID=${RelaID}&CatalogID=${CatalogID}&CatalogType=${CatalogType}&SiteID=${SiteID}"
		target="_blank">已有<span id="CmntCount"><script src="${ServicesContext}${CommentCountJS}?RelaID=${RelaID}"></script></span>位网友发表评论</a>
	</h2>

	<div id="commentContent" class="commentContent"><!-- comment header end --><z:datalist
		id="dg1" method="com.zving.cms.dataservice.CommentService.dg1DataBind"
		size="10" page="true">

		<!-- comment loop begin -->

		<div class="time">${AddUser} ${AddTime} IP:${AddUserIP}</div>
		<div class="comment">${Content}</div> 
		<!-- comment loop end -->
		<div>
        	<div class="fenxiang">
            	<img src="../Images/fenxiang.gif" width="11" height="11" />[<a href="javascript:void(1)" onclick="addAntiCount(${ID})">反对</a><span id="${ID}anti">(${antiCount})</span>]
            </div>
            <div class="fenxiang">
            	<img src="../Images/fenxiang.gif" width="11" height="11" />[<a href="javascript:void(1)" onclick="restore('${ID}','${CatalogID}','${RelaID}','${CatalogType}','${SiteID}')">回复</a>]
            </div>
            <div class="fenxiang">
            	<img src="../Images/fenxiang.gif" width="11" height="11" />[<a href="javascript:void(1)" onclick="addSupporterCount(${ID})">支持</a><span id="${ID}supporter">(${supporterCount})</span>]
            </div>
            <div id="${ID}textarea"></div>
            
            <div class="line"></div> 
        </div>
		

	</z:datalist>

	<div class="page"><z:pagebar target="dg1" type="1" /></div>

	<!-- comment footer begin -->
	<div class="textBox">

	<form action="${ServicesContext}${CommentActionURL}" method="post"
		onsubmit="return checkComment();"><input type="hidden"
		id="RelaID" name="RelaID" value="${RelaID}" /> <input type="hidden"
		id="CatalogID" name="CatalogID" value="${CatalogID}" /> <input
		type="hidden" id="CatalogType" name="CatalogType"
		value="${CatalogType}" /> <input type="hidden" id="SiteID"
		name="SiteID" value="${SiteID}" /> <textarea name="CmntContent"
		id="CmntContent"></textarea> <div id="textLogin">登录名：
		<input type="text" name="CmntUserName" id="CmntUserName" class="txt" /> 密码：
		<input type="password" name="CmntPwd" id="CmntPwd" class="txt" />
		<input type="checkbox" name="CmntCheckbox" checked/>匿名发表 </div><!-- end div -->		
		<input type="submit" name="CmntSubmit" id="CmntSubmit" value="提交评论" 
		class="btn" />
		<input type="hidden" value="<%=isLogin%>" id="isLogin"/>
		</form>

	</div>

	</div>

	</div>

	<!-- comment footer end -->

</z:init>

</body>

</html>
