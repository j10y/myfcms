<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>Zving BBS</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script src="../Framework/Main.js"></script>
</head>
<script type="text/javascript">
	function perInfoSave() {
		var dc = Form.getData($F("postform"));
		dc.add("UserImageOption",$NV("UserImageOption"));
		dc.add("SiteID",$V("SiteID"));
		Server.sendRequest("com.zving.bbs.ControlPanel.selfSettingSave",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.location="SelfSetting.jsp?SiteID=" + $V("SiteID");
				}
			});
		});
	}
function afterUpload(path){
	$S("HeadImage",path);
	var dc = Form.getData("postform");
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.ControlPanel.doSave",dc,function(response){
		if(response.Status!=1){
			alert(response.Message);
		}else{
			$("ImagePic").src = path;
		}
	});
}
</script>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.bbs.ControlPanel.init">
	<div class="wrap">
		<div id="menu" class="forumbox"> <span class="fl"> <b> <a id="viewpro">${UserName},个人信息管理 </a> </b></span> <span class="fr"> ${Priv} </span> </div><div id="nav"><a href="Index.jsp?SiteID=${SiteID}">ZvingBBS</a>  &raquo; 控制面板</div>
		<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" value="${SiteID}" id="SiteID">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>会员中心</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="ControlPanel.jsp?SiteID=${SiteID}">个人资料</a></li>
                <li class="current"><a href="SelfSetting.jsp?SiteID=${SiteID}">个性化设定</a></li>
              </ul>
            </div>
            <!--div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <li><a href="#;">站内短信</a></li>
              </ul>
            </div-->
            <div class="sideinner">
            <hr class="shadowline"/>
              <ul class="sidemenu">
                <!--li><a href="#;">发表文章</a></li-->
                <li><a href="MyArticles.jsp?SiteID=${SiteID}">我发表的文章</a></li>
              </ul>
            </div>
            <!--hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li><a href="#;">我的好友</a></li>
                <li><a href="#">我的空间</a></li>
                <li><a href="#;">我的收藏</a></li>
              </ul>
            </div-->
            <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <!--li><a href="MyPriv.jsp">我的权限</a></li-->
                <li><a href="MyScore.jsp?SiteID=${SiteID}">我的积分</a></li>
              </ul>

            </div>
          </div></td>

    <td width="20">&nbsp;</td>
    <td>
	<div style="display:none"><iframe name="formTarget"  src="javascript:void(0)"></iframe></div>
            <!--h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">设置头像</h5-->
    <form method="post" id="postform"  target="formTarget"  enctype="multipart/form-data" action="ImageUpload.jsp?Path=/BBS/Images/UserHeadImage/">
	<div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>个性化设定</h4>
      <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">用户图像</h5><table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <tr>
    <td width="200"><img id="ImagePic" src="${HeadImage}" width="100" height="100" style="border:#DFDFDF 1px solid"/></td>
    <td>自定义头像:<input type="file" id="LogoFile" name="LogoFile"/><input type="submit" name="upload" id="upload" value="上传" ></td>
  </tr><input type="hidden" id="UserName" name="UserName" value="${UserName}"/>
     <input type="hidden" id="HeadImage" name="HeadImage" value="${HeadImage}"/>
  <tr>
   	<td>${UserImageOption}</td>	<td></td>
  </tr>
  <tr><td>昵称：<input type="text" id="NickName" name="NickName" value="${NickName}"></td></tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">个性签名</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <input type="hidden" id="UserName" value="${UserName}" />
  <tr>
	  <td width="30"></td>
	  <td>
	  		${ForumSign}
	  </td>
  </tr>
  <tr>
  	  <td></td>
	  <td>重新填写:<br/><textArea id="ForumSign" cols="120"></textArea></td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
	<div style=" padding-left:120px;">
        <button type="button" name="tiajiao" class="submit" onclick="perInfoSave();">保存</button> &nbsp; 
    </div>
    </div>
</form>
</td>
  </tr>
</table>

</div>
</z:init>
<%@ include file="../Include/foot.jsp" %>

</body>

</html>