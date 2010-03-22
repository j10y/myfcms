<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="ForumInit.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ZVING</title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<link href="forum.css" rel="stylesheet" type="text/css">
<link href="skins/zving/default.css" rel="stylesheet" type="text/css">
<script>
 var global = null; 
 Page.onLoad(function(){
	if($V("ShowBox")=="Y"){
		//$("mainbox").style.display = '';
		table1.rows[1].cells[1].style.display = '';
		var tds = $("table1");
		for(var i = 0;i<tds.rows.length;i++){
			 tds.rows.cells[1].style.display = 'none';	
		}
	}
 });
 
	function orderBy(){
		var dc = Form.getData($F("form3"));
		DataList.setParam("list1",Constant.PageIndex,0);
		DataList.setParam("list1","ForumID",$V("ForumID"));
		DataList.setParam("list1","SiteID",$V("SiteID"));
		DataList.setParam("list1","addtime",dc.get("time"));
		DataList.setParam("list1","orderby",dc.get("orderby"));
		DataList.setParam("list1","ascdesc",dc.get("ascdesc"));
		DataList.loadData("list1");
	}

	function del(){
		if(!$NV("Themes")||$NV("Themes").length==0){
			Dialog.alert("请先选择要设为精华的行！");
			return;
		}
		Dialog.confirm("确定删除选中的项吗？",function(){
			var arr = $NV("Themes");
			var dc = new DataCollection();	
			dc.add("IDs",arr.join());
			dc.add("SiteID",$V("SiteID"));
			Server.sendRequest("com.zving.bbs.Theme.del",dc,function(response){
				if(response.Status==0){
					Dialog.alert(response.Message);
				}else{
					Dialog.alert(response.Message);
					DataList.loadData("list1");
				}
			});
		});
}
function move(){
	if(!$NV("Themes")||$NV("Themes").length==0){
		Dialog.alert("请先选择要移动的行！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "移动主题";
	diag.URL = "BBS/ThemeMoveDialog.jsp?ForumID="+$V("ForumID")+"&SiteID="+$V("SiteID");
	diag.OKEvent = moveSave;
	diag.show();
}
function moveSave(){
	var arr = $NV("Themes");
	var dc = Form.getData($DW.$F("form1"));
	dc.add("IDs",arr.join());
    dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.Theme.move",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			Dialog.alert(response.Message);
			DataList.loadData("list1");
		}
	});
}
function bright(){
	if(!$NV("Themes")||$NV("Themes").length==0){
		Dialog.alert("请先选择要设为高亮的行！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "设置高亮主题";
	diag.URL = "BBS/ThemeColorChoose.jsp";
	diag.OKEvent = brightSave;
	diag.show();
}
function brightSave(){
	var arr = $NV("Themes");
	var dc = new DataCollection();
	dc.add("Color",$DW.$NV("Color"));
	dc.add("IDs",arr.join());
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.Theme.brightSave",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			Dialog.alert(response.Message);
			DataList.loadData("list1");
		}
	});
}

function upOrDown(){
	if(!$NV("Themes")||$NV("Themes").length==0){
		Dialog.alert("请先选择要提升/下沉的行！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "提升/下沉主题";
	diag.URL = "BBS/ThemeUpOrDownDialog.jsp";
	diag.OKEvent = upOrDownSave;
	diag.show();
}
function upOrDownSave(){
	var arr = $NV("Themes");
	var dc = new DataCollection();
	dc.add("UpOrDown",$DW.$NV("UpOrDown"));
	dc.add("IDs",arr.join());
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.Theme.upOrDownSave",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			Dialog.alert(response.Message);
			DataList.loadData("list1");
		}
	});
}
function top(){
	if(!$NV("Themes")||$NV("Themes").length==0){
		Dialog.alert("请先选择要置顶的行！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "顶置主题";
	diag.URL = "BBS/ThemeTopDialog.jsp";
	diag.OKEvent = topSave;
	diag.show();
}
function topSave(){
	var arr = $NV("Themes");
	var dc = new DataCollection();
	dc.add("TopTheme",$DW.$NV("TopTheme"));
	dc.add("IDs",arr.join());
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.Theme.topTheme",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			Dialog.alert(response.Message);
			DataList.loadData("list1");
		}
	});
}
function best(){
	if(!$NV("Themes")||$NV("Themes").length==0){
		Dialog.alert("请先选择要设为精华的行！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 60;
	diag.Title = "设为精华";
	diag.URL = "BBS/ThemeBestDialog.jsp";
	diag.OKEvent = bestSave;
	diag.show();
}
function bestSave(){
	var arr = $NV("Themes");
	var dc = new DataCollection();
	dc.add("BestTheme",$DW.$NV("BestTheme"));
	dc.add("IDs",arr.join());
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.bbs.Theme.bestTheme",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			$D.close();
			Dialog.alert(response.Message);
			DataList.loadData("list1");
		}
	});
}

function searchPost(type) {
	DataList.setParam("list1",Constant.PageIndex,0);
	DataList.setParam("list1","postType",type);
	DataList.loadData("list1");
	
}
 
 function   liClick(li){  
    if(global){
		global.className = "";  
    }else{
    	document.getElementById("all").className = "";  
    }
    li.className = "current";  
    global = li;  
}  	
var alreadyCheck = false;
function checkAll(){
	var allBox = $("allBox");
	var arr = $N("Themes");
	if(alreadyCheck){
		for(var i = 0;i<arr.length;i++){
		   arr[i].checked = allBox.checked;
		}
		alreadyCheck = false;
	}else{
		for(var i = 0;i<arr.length;i++){
			arr[i].checked = allBox.checked;
		}
		alreadyCheck = true;
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

</script>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.bbs.Theme.init">
<div class="wrap">
<input id="ForumID"  type="hidden" value="${ID}">
<input id="SiteID"  type="hidden" value="${SiteID}">
<input id="ShowBox"  type="hidden" value="${ShowBox}">
<div id="menu" class="forumbox"><span class="fl"><a id="forumlist" href="Index.jsp?SiteID=${SiteID}">Zving BBS</a> &raquo; ${Name}</span> <span class="fr">${Priv}</span></div>
<div id="foruminfo">
	<div id="nav">
	</div></div>
<div id="headfilter">
	<ul class="tabs">
		<li class="current" onclick="liClick(this)" id='all'><a href="javascript:void(0)" onclick="searchPost('all');">全部</a></li>
		<li onclick="liClick(this)"><a href="javascript:void(0)" onclick="searchPost('best');" >精华</a></li>
		<!-- li onclick="liClick(this)"><a href="forumdisplay.php?fid=4&amp;filter=poll">投票</a></li>
		<li onclick="liClick(this)"><a href="forumdisplay.php?fid=4&amp;filter=trade">商品</a></li>
		<li onclick="liClick(this)"><a href="forumdisplay.php?fid=4&amp;filter=reward">悬赏</a></li>	
		<li onclick="liClick(this)"><a href="forumdisplay.php?fid=4&amp;filter=activity">活动</a></li-->
	</ul>
</div><div class="mainbox threadlist forumbox">
<h4><a >ZvingCMS</a></h4>
<form method="post" name="moderate" action="topicadmin.jsp?action=moderate&amp;fid=5">
		<table id="table1" width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="forumTable">
		<thead class="category"><tr ztype="head" class="dataTableHead">
			<th width="36">&nbsp;类型</th>
			<th id="mainbox" class="boxes" name="boxes" width="30"><input type="checkbox"  id="allBox" onclick="checkAll()"></td>
			<th>主题</th>
			<th width="150">作者</th>
			<th width="60">回复/浏览</th>
		</tr></thead>
    <tbody>
	<z:datalist  id="list1" method="com.zving.bbs.Theme.getList" page="true" size="12" >
	<!--从要循环的数据行开始插入datalist标签 -->
		<tr>
			<td>[${Type}]</td>
			<td class='boxes' name="boxes"><input type="checkbox" value="${ID}" id="Themes" name="Themes" ></td>
			<td valign="middle" nowrap="nowrap"><a href="./Post.jsp?ThemeID=${ID}&ForumID=${ForumID}&SiteID=${SiteID}" ><span style="color:${Bright}" id="${ID}">${Subject}</span></a>${TopTheme}${Best}</td>
			<td><cite><a href="javascript:void(0)"  onclick="userinfo('${AddUser}')">${AddUser}</a></cite><br> <em>${Addtime}</em></td>
			<td>${ReplyCount}/${ViewCount}</td>
		</tr>
	</z:datalist>
        </tbody>
		</table>
	<div class="footoperation">${Operate}</div>
</form>
</div>
		<z:pagebar target="list1">
		<div>${PageBar}</div>
        </z:pagebar>
<div style="padding:5px;">
<div><span id="newspecial" class="fr"> ${NewThemeButton} </span></div>
<div id="footfilter">
<form method="get"  id="form3"><input type="hidden" name="fid" value="5"> 查看 <select name="filter" id="time">
	<option value="0">全部主题</option>
	<option value="86400000">1 天以来主题</option>
	<option value="172800000">2 天以来主题</option>
	<option value="604800000">1 周以来主题</option>
	<option value="2592000000">1 个月以来主题</option>
	<option value="7948800000">3 个月以来主题</option>
	<option value="15897600000">6 个月以来主题</option>
	<option value="31536000000">1 年以来主题</option>
</select> 排序方式 <select name="orderby" id="orderby">
	<option value="LastPostTime" >回复时间</option>
	<option value="AddTime">发布时间</option>
	<option value="ReplyCount">回复数量</option>
	<option value="ViewCount">浏览次数</option>
</select> <select name="ascdesc" id="ascdesc">
	<option value="DESC"  >按降序排列</option>
	<option value="ASC">按升序排列</option>
</select> &nbsp;
<button type="button" onclick="orderBy()">提交</button>
</form>

</div></div></div>
</z:init>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>
