<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zving BBS</title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script>
function addAdminSave(ID){
		if(ForumAdmin == $V('ForumAdmin')){
			DataList.loadData('list1');
			flag = true;
			return;
		}else{
			var dc = new DataCollection();
			dc.add("ID",ID);
			dc.add("ForumAdmin",$V('ForumAdmin'));
			Server.sendRequest("com.zving.bbs.Forum.addAdmin",dc,function(response){
				Dialog.alert(response.Message);
				if(response.Status==1){
					DataList.loadData('list1');
					flag = true;
				}else{
					DataList.loadData('list1');
					flag = true;
				}
			});
		}
}
function del(ID){
	Dialog.confirm("确定要删除该板块以及板块下的所有帖子吗？",function(){
		var dc = new DataCollection();
		dc.add("ID",ID);
		Server.sendRequest("com.zving.bbs.Forum.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					DataList.loadData('list1');
				}
			});
		});

	});	
}
function checkForum(ID){
	var dc = new DataCollection();
	dc.add("ID",ID);
	Server.sendRequest("com.zving.bbs.Forum.check",dc,function(response){		
				if(response.Status==1){
					window.location = "./Theme.jsp?SiteID="+$V("SiteID")+"&ForumID="+ID;
				}
				if(response.Status==0){
					 password(ID);
				}
				if(response.Status==2){
					Dialog.alert(response.Message);
				}
		
		});
}
function password(ID){
var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "输入密码";
	diag.URL = "BBS/PasswordDialog.jsp?ID="+ID;
	diag.OKEvent = submitPassword;
	diag.show();
}

function submitPassword(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = Form.getData($DW.$F("form1"))
	Server.sendRequest("com.zving.bbs.Forum.checkPassword",dc,function(response){		
		if(response.Status==1){
				$D.close();
				window.location = "./Theme.jsp?ForumID="+dc.get("ID")+"&SiteID="+$V("SiteID");
		}
		if(response.Status==0){
			Dialog.alert(response.Message);
		}
		
	});
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
</script>
</head>
<body>
<%@ include file="../Include/head.jsp"%>
<z:init method="com.zving.bbs.Forum.init">
	<input type="hidden" id="SiteID" value="${SiteID}" >
	<div class="wrap">
	<div id="menu" class="forumbox"><span class="fl"> <b> <a id="viewpro">${AddUser}</a> &nbsp;&nbsp;&nbsp;&nbsp;</b> ${button}</span> <span class="fr">${Priv}</span></div>

	<z:simplelist method="com.zving.bbs.Forum.getList1">
		<div class="mainbox forumlist forumbox"><span class="headactions fr">${ForumAdmin}</span>
		<h4><a href="Index.jsp?ForumID=${ID}&SiteID=${SiteID}"><b>${Name}</b></a></h4>
		<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
			<thead class="category">
				<tr>
					<th>版块</th>
					<th width="40">主题</th>
					<th width="40">帖数</th>
					<th width="200">最后发表</th>
					<th width="100">版主</th>
				</tr>
			</thead>
			<z:simplelist method="com.zving.bbs.Forum.getList2">
				<tbody>
					<tr valign="middle">
						<td align="left"><a href="#" onclick="checkForum(${ID})"><b>${Name}</b></a>
						<div>${Info}</div>
						</td>
						<td align="center">${ThemeCount}</td>
						<td align="center">${PostCount}</td>
						<td nowrap="nowrap"><a href="Post.jsp?ForumID=${ID}&ThemeID=${LastThemeID}&SiteID=${SiteID}" title="${LastPost}">${LastPost}</a> <cite><br />
							by <a href="javascript:void(0)"  onclick="userinfo('${LastPoster}')">${LastPoster}</a></cite></td>
						<td>${ForumAdmin}</td>
					</tr>
				</tbody>
			</z:simplelist>
		</table>
		</div>
	</z:simplelist></div>
</z:init>
<%@ include file="../Include/foot.jsp"%>
</body>
</html>
