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
<script type="text/javascript">
	function exeAudit() {
		var dc = Form.getData($F("form1"));
		dc.add("SiteID",$V("SiteID"));
		Server.sendRequest("com.zving.bbs.PostAudit.exeAudit",dc,function(response){
		 	Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataList.loadData("list1");	
				}
		 	});
		});
	}

	function changeAudit(flag) {
		DataList.setParam("list1", "auditFlag", flag);
		DataList.setParam("list1", "SiteID", $V("SiteID"));
		DataList.loadData("list1");
	}
	
	function changeOption() {
		DataList.setParam("list1", "ForumID", $V("SiteID"));
		DataList.setParam("list1", "ForumID", $V("ForumOptions"));
		DataList.setParam("list1", "TypeID", $V("TypeOptions"));
		DataList.setParam("list1", "First", $V("First"));
		DataList.loadData("list1");
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
<z:init method="com.zving.bbs.MasterPanel.init">
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
                <li>${AllowEditForum}</li>
                <!--li><a href="#">版主推荐</a></li-->
              </ul>
            </div><br/>
            <h4>主题管理</h4>
             <hr class="shadowline"/>
            <div class="sideinner">
              <ul class="sidemenu">
                <li  class="current">${AllowVerfyPost}</li>
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
    <td>		<td><z:init method="com.zving.bbs.MasterPanel.searchUserInit">
			<form method="post" id="form1">
			<div class="forumbox"><span class="fr">带 <span
				style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span>
			<h4>帖子审核</h4>

			<table id="table1" width="100%" border="0" cellspacing="0"
				cellpadding="0" class="forumTable">
				<tr>
					<td><z:init method="com.zving.bbs.PostAudit.init">
		请选择：
		<z:select id="TypeOptions" style="width:100px;"
							onChange="changeOption();">
							<span value="N">未审核</span>
							<span value="X">已忽略</span>
						</z:select>
						<z:select id="ForumOptions" style="width:100px;"
							onChange="changeOption();">
							<span value="0">所有板块</span>${ForumOptions}</z:select>
					</z:init>
					<z:select id="First" style="width:100px;" onChange="changeOption();"><span value="Y">新主题</span><span value="N">新回复</span></z:select></td>
				</tr>
				<tr>
					<td>
					<div style="padding:5px;"><z:datalist id="list1"
						method="com.zving.bbs.PostAudit.getUnauditedPost" page="true"
						size="12">
						<div class="forumbox">
						<h4>${ForumName}>>${Subject}</h4>
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#eeeeee" class="forumTable">
							<tr>
								<td class="postauthor" valign="middle" align="center">
								<div id="userinfo17435_menu" style="valign:middle">
								<ul>
									<li>${AuditFlag}</li>
								</ul>
								</div>
								</td>
								<td class="postcontent" ondblclick="edit(${ID})">
								<div>
								<h5>${Subject}</h5>
								<div id="edit${ID}">作者：${AddUser} 时间：${AddTime}<br />
								${Message}</div>
								</div>
								</td>
							</tr>
						</table>
						</div>
					</z:datalist> <input type="hidden" id="auditFlag" value="Y"></div>
					<input type="button" name="submit" value="提交" onclick="exeAudit();">
					<a href="javascript:void(0)" onclick="changeAudit('Y');">全部通过</a>&nbsp;
					<a href="javascript:void(0)" onclick="changeAudit('N');">全部删除 </a>&nbsp;
					<a href="javascript:void(0)" onclick="changeAudit('X');">全部忽略</a></td>
				</tr>
			</table>
			<h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
                </div>
			</form>
		</z:init></td>
	</tr>
</table>
<z:pagebar target="list1"></z:pagebar>
</z:init>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
