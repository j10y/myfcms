<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>Zving BBS</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../Framework/Main.js"></script>
</head> 
<script type="text/javascript">
	Page.onLoad(function() {
		if(!$V("FID")) { 
			$("table1").style.display = 'none';
		}
	});
	
	function searchForum() {
		if($V("ForumID")==0) {
			$("table1").style.display = 'none';
		}
		
		var dc = new DataCollection();
		dc.add("ForumID", $V("ForumID"));
		dc.add("SiteID",$V("SiteID"));
		Server.sendRequest("com.zving.bbs.MasterPanel.changeForum",dc,function(response){
			if(response.Status==1){
				$("table1").style.display = '';
				if(response.get("Info") != null) {
					$("Info").innerHTML = response.get("Info");
				}
			}
		});
	}
	
	function editInfoSave() {
		var dc = Form.getData($F("postform"));
		dc.add("SiteID",$V("SiteID"));
		if(Verify.hasError()){
		 	 return;
	    }
		Server.sendRequest("com.zving.bbs.MasterPanel.editForumSave",dc,function(response){
			Dialog.alert(response.Message, function(){
				if(response.Status==1){
					window.location="ForumEdit.jsp?SiteID="+$V("SiteID");
				}
			});
		});
	}
	
</script>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.bbs.MasterPanel.init">
	<div class="wrap">
		<div id="menu" class="forumbox"> <span class="fl"><a href="Index.jsp?SiteID=${SiteID}">ZvingBBS</a>  &raquo; 版主管理面板</span><span class="fr">${Priv}</span></div>
		<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" id="SiteID" value="${SiteID}">
  <tr valign="top">
    <td width="200"><div class="side">
            <h4>用户管理</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li>${AllowEditUser}</li>
                <li>${AllowForbidUser}</li>
                <!-- li><a href="#">用户权限</a></li-->
              </ul>
            </div><br/>
            <h4>板块管理</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li  class="current">${AllowEditForum}</li>
                <!--li><a href="#">版主推荐</a></li-->
              </ul>
            </div><br/>
            <h4>主题管理</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li>${AllowVerfyPost}</li>
                <!--li><a href="MyArticles.jsp">审核回复</a></li-->
              </ul>
            </div><br/>
            <h4>其他</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
              	<!--li><a href="#">管理日志</a></li-->
                <li><a href="Index.jsp">返回论坛</a></li>
                <li><a href="Index.jsp">退出管理</a></li>
              </ul>

            </div>
          </div>
          </td>
    <td width="20">&nbsp;</td>
    <td><z:init method="com.zving.bbs.MasterPanel.forumEditInit">
    <form method="post" id="postform">
	<div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>论坛板块选择</h4>
   <h5 style="border-bottom:1px dotted #ccc; color:#999999; padding:3px; margin:5px;"></h5><table width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
   <tr>
   	<input type="hidden" id="FID" value="${ForumID}" />
    <td width="50" align="right">板块：</td>
    <td width="200">
    	<z:select id="ForumID" onChange="searchForum();">
    		<span value="0">请选择板块</span>
    		${ForumOptions}
    	</z:select>
    </td>
    <td></td>
  </tr>  

</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">板块编辑</h5>
<table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
  <input type="hidden" id="ForumName" value="${Name}" />
  <tr>
    <td width="100" align="right">板块描述：</td>
    <td><textarea rows="30" cols="70" name="Info" id="Info">${Info}</textarea></td>
  </tr>
  <tr>
  	<td></td>
  	<td><input type="button" name="submit" id="submit" value='提交' onclick="editInfoSave();" /></td>
  </tr>
</table>
<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
	</div>
</form>
</z:init></td>
  </tr>
</table>
</div>
</z:init>
<%@ include file="../Include/foot.jsp" %>
</body>

</html>