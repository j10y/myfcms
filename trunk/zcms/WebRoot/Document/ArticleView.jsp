<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.document.ArticleHistory.init">
	<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>编辑历史---${Title}</title>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<script src="../Framework/Main.js"></script>
	<style type="text/css">
<!--
td{
	border-collapse:collapse;
	border-spacing:0;
	border-color:#ddd;
	font-family: Verdana, Arial, Tahoma, Helvetica, "宋体", sans-serif;
	font-size:12px;
	line-height: 150%;
	word-break: break-all;
}

.windowname{
color:#000;
padding:4px 10px;
}
.themleft{
background:url(Images/themtitleft.gif) no-repeat left top;
}
.themright{
background:url(Images/themtitright.gif) no-repeat right top;
cursor: pointer;
}
.thembg{
background:#FFFFFF url(Images/themtitbg.gif) repeat-x left top;
border-bottom:#b8c9d6 1px solid;
border-right:#b8c9d6 1px solid;
}
#id_AdvanceTd{
/*
background:#FFFFFF url(Images/thembg.gif) repeat-y center top;
*/
}
textarea,form,select,input,button,option,iframe{
	font-size:12px;
	background-color: transparent;
}
#otherbtn a, #otherbtn a:visited{
display:block;
width:85px;
height:22px;
padding:5px 0 0 0;
text-align: left;
text-decoration:none;
background:url(Images/btbg.gif) no-repeat center center;
}
#otherbtn img,#otherbtn img{
margin:0 10px 0 5px;
}
#otherbtn a:hover{
background:url(Images/btbgo.gif) no-repeat center center;
}

.tbSwitch{
	height: 26px;
	position:absolute;
    	background:	url("Images/paggingbar.gif");
}
-->
</style>
	<script>
var CatalogID = '${CatalogID}';
var ChannelID = '';
var TempArticleID = '';

var pages = ${Pages};
var currentPage = 1;
var contents = [${ContentPages}];
var PAGE_SPLIT ="--abcdefghijklmnopqrstuvwxyz--";
var editorMode = 0;

var isDirty = false; //是否已经修改

var editor;

var INFO_ATTACH_PIC = "(图)";
function onAttachPic(_oSelectElement){
	var sValue = $("DocTitle").value;
	if(_oSelectElement.checked){			
		if(sValue.indexOf(INFO_ATTACH_PIC) < 0){
			sValue += INFO_ATTACH_PIC;
		}
	}else{
		sValue = sValue.replace( INFO_ATTACH_PIC , "");
	}

	$("DocTitle").value = sValue;
}

function displayAdvance(_sAdvanceId, _elContolImg, _contract, _outspread){
	var oAdvanceTd = $(_sAdvanceId);
	if(oAdvanceTd == null){
		return;
	}
	var bDisplayed = (oAdvanceTd.style.display != "none");
	isIE?(oAdvanceTd.style.display = (bDisplayed?"none":"inline")):(oAdvanceTd.style.display = (bDisplayed?"none":"table-cell"))
	$(_elContolImg).src = (bDisplayed?_contract:_outspread);
	$(_elContolImg).title	= (bDisplayed?"展开":"收缩");

}

function showTemplate(){
	if($("TemplateFlag").checked){
		$E.show("DivTemplate");
	} else {
		$E.hide("DivTemplate");
		$S("Template","");
	}
}


function browse(ele){
	var diag  = new Dialog("Diag3");
	diag.Width = 800;
	diag.Height = 600;
	diag.Title ="浏览模板";
	diag.URL = "CallTemplateFrame.jsp?siteID="+$V("SiteID");
	goBack = function(params){
		$S(ele,params);
	}
	diag.show();
}



function changeDocType(){
    var ele = $("NewsType");
	if(ele.value=="4"){
		$("f1").contentWindow.$("DivRedirect").style.display="";
	} else {
		$("f1").contentWindow.$("DivRedirect").style.display="none";
	}
}

function goback(){
	window.location = "ArticleList.jsp?CatalogID="+CatalogID;
}


//添加引用
function setReferName(ele){
   $S("ReferName",$(ele).value);
}

//另存为
function saveAsDialog(){
  var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 520;
	diag.Title = "另存为";
	diag.URL = "CatalogListDialog.jsp?Type=0&SiteID="+$V("SiteID");
	diag.onLoad = function(){
		try{
		  $DW.$("btnOK").onclick = doSaveAs;
		}catch(ex){
	  }
	};
	diag.show();
}

function doSaveAs(){
	var arrDest = $DW.$("CatalogList").contentWindow.$NV("catalog");
  if(arrDest == null || arrDest.length==0){
		alert("请先选栏目！");
		return;
	}else{
		var otherCatalogID = arrDest[0];
		var catalogName = $DW.$("CatalogList").contentWindow.$("span_"+otherCatalogID).innerHTML;
		$S("CatalogID",otherCatalogID);
		$("CatalogName").innerHTML = catalogName;
		$D.close();
	}
}

//复制
function copyDialog(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 520;
	diag.Title = "选择复制到的栏目";
	diag.URL = "CatalogListDialog.jsp?Type=1&SiteID="+$V("SiteID");
	diag.onLoad = function(){
		try{
		  $DW.$("btnOK").onclick = addCopy;
	  }catch(ex){
	  }
	};
	diag.show();
}

function addItem(name,id){
	var tableEle = $("CopyCatalogs");
	var len = tableEle.rows.length;
	var row = tableEle.insertRow(len);
	row.insertCell(0).innerHTML = "<td>"+name+"</td>";
	row.insertCell(1).innerHTML = "<td><img src=\"Images/button_delete_small.gif\""
	                            + " border=0 style=\"cursor: pointer;\" "
	                            + "alt=\"删除当前引用到的栏目\" onClick=\"delItem(this,"+id+");\"></td>";
}

function delItem(ele,id){
	var tableEle = $("CopyCatalogs");
	var rowIndex;
	if(isIE){
		rowIndex = ele.parentElement.parentElement.rowIndex;
	}else{
		rowIndex = ele.parentElement.parentElement.parentElement.rowIndex;
	}
	tableEle.deleteRow(rowIndex);
	var str = $V("Copy2Article");
	var arr = str.split(",");
	arr.remove(id);
	$S("Copy2Article",arr.join(","));
}


function addCopy(){
  var arrDest = $DW.$("CatalogList").contentWindow.$NV("catalog");
  if(arrDest == null || arrDest.length==0){
		alert("请先选择复制的目标栏目！");
		return;
	}
	
	//去掉本栏目id
	
	var tableEle = $("CopyCatalogs");
	var len = tableEle.rows.length;
	var lastRow = tableEle.rows[len-1];
	var cell= lastRow.cells[0];
	if(cell.innerHTML==""){
		tableEle.deleteRow(len-1);
	}
	var arr= [];
	for(var i = 0;i<arrDest.length;i++){
		if($V("CatalogID") != arrDest[i]){
			var otherCatalogID = arrDest[i];
			var catalogName = $DW.$("CatalogList").contentWindow.$("span_"+otherCatalogID).innerHTML;
			addItem(catalogName,otherCatalogID);
			arr.push(otherCatalogID);
		}
	}
	
	$S("Copy2Article",arr.join(","));
	$D.close();
}

function historyVersion(){
	var diag = new Dialog("Diag1");
	diag.Width = 850;
	diag.Height = 550;
	diag.Title = "历史版本";
	diag.URL = "ArticleVersionDialog.jsp?ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
	};
	diag.show();
}

function closeX(){
	window.close();
}

function note(){
	//alert(contents)
}

function htmlDecode(str) {
	return str.replace(/\&quot;/g,"\"").replace(/\&lt;/g,"<").replace(/\&gt;/g,">").replace(/\&nbsp;/g," ").replace(/\&amp;/g,"&");
}


//分页处理
function initPages(){
	var pageList = $("pageList");
	contents[0] = htmlDecode(contents[0]);
	for(var i = 2;i < pages+1;i++){
		var li = document.createElement("li");
		li.innerHTML = "<b>页 " + i+"</b>";
		li.id = "p" + i;
		li.setAttribute("name","tabs");
		li.onclick = function(evt){
			changePage(evt);
		};	
		li.onmouseover = function(evt){
		  onOverPage(evt);
	  };
	  li.onmouseout = function(evt){
			onOutPage(evt);
		};
			
		var currentTab = $("p"+(i-1));
		currentTab.insertAdjacentElement("afterEnd",li);
		
		contents[i-1] = htmlDecode(contents[i-1]);
	}

}

function addPage(){
	pages = pages+1;
	
	for (var i=pages;i>currentPage+1;i--){
		var tab = $("p"+(i-1))
		tab.innerHTML = "<b>页 " + i+"</b>";
		tab.id = "p" + i;
	}
	
	var pageList = $("pageList");
	var li = document.createElement("li");
	li.innerHTML = "<b>页 " + (currentPage+1)+"</b>";
	li.id = "p" + (currentPage+1);
	li.setAttribute("name","tabs");
	li.onclick = function(evt){
		changePage(evt);
	};
	li.onmouseover = function(evt){
		onOverPage(evt);
	}
  li.onmouseout = function(evt){
		onOutPage(evt);
	}
	
	var currentTab = $("p"+currentPage);
	currentTab.insertAdjacentElement("afterEnd",li);
	
	contents.insert(currentPage," ");

  setActivePage(currentPage+1);
}

function setPageContent(pageNum, content){
	contents[pageNum-1] = content;
	if (currentPage == pageNum){
		editor.SetHTML(contents[pageNum-1]);
	}
}	

function setActivePage(page){
	var currentTab = $("p"+page);
	if(currentPage == page && currentTab.className == "pagetabCurrent"){
		return;
	}

	var content = editor.GetXHTML(false);
	
	//检查是否修改
	isDirty = (content==contents[currentPage-1]) ? isDirty:true;
	
	for (var i=0;i<pages;i++){
		var tab = $("p"+(i+1));
		if (tab.className=="pagetabCurrent"){
			tab.className = "";
			if(content!=""){
				contents[currentPage-1] = content;
			}
			break;
		}
	}
	currentTab.className = "pagetabCurrent";
	if(editorMode==0){
		editor.SetHTML(contents[page-1]);
  }else{
  	editorMode = 0;
  }
	
	currentPage = page;
}

function deletePage(){
	if (currentPage==1){
		return;
	}
	var pageList = $("pageList");
	var currentTab = $("p"+currentPage);

	pageList.removeChild(currentTab);
	
  contents.splice(currentPage-1, 1);
  
	for(var i=currentPage;i<pages;i++){
		var tab = $("p"+(i+1));
		tab.id = "p" + i;
		tab.innerHTML = "<b>页 " + i +"</b>";
	}
	
	pages = pages-1;
	if (currentPage>pages){
		 currentPage = pages;
	}
	setActivePage(currentPage);
}

function mergePage(){
	if (currentPage==1){
		return;
	}
	var pageList = $("pageList");
	var currentTab = $("p"+currentPage);

	pageList.removeChild(currentTab);
	
	contents[currentPage-1] = contents[currentPage-1] + editor.getXHTML(false);
  contents.splice(currentPage-1, 1);
  
	for(var i=currentPage;i<pages;i++){
		var tab = $("p"+(i+1));
		tab.id = "p" + i;
		tab.innerHTML = "<b>页 " + i +"</b>";
	}
	
	pages = pages-1;
	if (currentPage>pages){
		currentPage = pages;
	}
	setActivePage(currentPage);
}

function changePage(evt){
  var ele = getEvent(evt).srcElement;
  if(ele.nodeName == "B"){
  	ele = ele.parentElement;
  }
  
	var arr = ele.parentElement.getElementsByTagName("li");
	for(var i=0,j=arr.length;i<j;i++){
		 if (arr[i].className=="pagetabCurrent"){
	     arr[i].className = "";
	   }
	}
	ele.className="pagetabCurrent";
	
	var eleId = ele.id;
	var pageNum = eleId.substr(1);
	pageNum = parseInt(pageNum);

	setActivePage(pageNum);
}

function onOverPage(evt){
	var ele = getEvent(evt).srcElement;
  if(ele.nodeName == "B"){
  	ele = ele.parentElement;
  }
	if(ele.className==""){
		ele.className="pagetabOver";
	}
}

function onOutPage(evt){
  var ele = getEvent(evt).srcElement;
  if(ele.nodeName == "B"){
  	ele = ele.parentElement;
  }
	if(ele.className=="pagetabOver"){
	   ele.className="";
	}
}


Page.onLoad(function(){
  $S("NewsType",$V("hNewsType"));
  changeDocType();
  
  $S("TopFlag",$V("hTopFlag"));
  $S("CommentFlag",$V("hCommentFlag"));
	if($V("ArticleID") == "" || $V("ArticleID") == "null"){
		$("CommentFlag").checked = true;
	}
	$S("Priority",$V("hPriority"));
	$S("TemplateFlag",$V("hTemplateFlag"));
	showTemplate();
	
	var editorWindow = $("f1").contentWindow;
	editorWindow.$("ArticleID").value = $("ArticleID");
	editorWindow.$("ID").value = $("ArticleID");
	
  editor = editorWindow.FCKeditorAPI.GetInstance('Content');
  
  initPages();
 	
});
</script>
	<style>
body{ text-align:left; font-size:12px;color:#666666; margin:0px; background:#F6F9FD;}
</style>
	</head>
	<body scroll="no">
	<form method="Post" id="form2"><input type="hidden" ID="SiteID"
		value="${SiteID}"> <input type="hidden" ID="CatalogID"
		value="${CatalogID}"> <input type="hidden" id="hNewsType"
		value="${Type}"> <input type="hidden" ID="hTopFlag"
		value="${TopFlag}"> <input type="hidden" ID="hPriority"
		value="${Priority}"> <input type="hidden" ID="hCommentFlag"
		value="${CommentFlag}"> <input type="hidden"
		ID="hTemplateFlag" value="${TemplateFlag}"> <input
		type="hidden" ID="ArticleID" value="${ID}"> <input
		type="hidden" ID="Method" value="${Method}">
	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#F6F9FD" style="border:#B7D8ED 1px solid;">
		<TR>
			<td colspan="3">
			<div id="xToolbar"></div>
			</td>
		</TR>
		<TR>
			<td valign="top">
			<div><iframe id="f1"
				src="ArticleViewEditor.jsp?ArticleID=<%=request.getParameter("ArticleID")%>&CatalogID=<%=request.getParameter("CatalogID")%>&BackupNo=<%=request.getParameter("BackupNo")%>"
				style="border:none;" width="100%" height="720" /></iframe></div>

			<div id="pageBarDiv">
			<TABLE id="pageBarTable" width="100%">
				<tr>
					<td width="78%" height="33" valign="middle" bgcolor="#F7F8FD"
						class="pagetab">
					<ul id="pageList">
						<li onClick="changePage(event)" onMouseOver="onOverPage(event)"
							onMouseOut="onOutPage(event)" class="pagetabCurrent" id="p1"
							name="tabs"><b>页 1</b></li>
					</ul>
					<span class="add"><a href="#;" onClick="addPage()"
						alt="在当前页后插入"></a></span></td>
					<td width="22%" valign="middle" bgcolor="#F7F8FD" class="pagetab">&nbsp;</td>
				</tr>
			</TABLE>
			</div>
			</td>
			<TD width="6" rowspan="3" align="right" bgcolor="#F6F9FD"
				style="border-left: 1px solid #91A9BD;border-right: 1px solid #CFE6F2"><img
				src="Images/right_close.gif" width="6" height="60"
				style="cursor:pointer; display:inline;" title="输入其它信息"
				onClick="displayAdvance('id_AdvanceTd',this,'Images/left_close.gif','Images/right_close.gif');"></TD>
			<TD width="220" valign="top" id="id_AdvanceTd"
				style="padding-top: 2px">
			<TABLE width="100%" border="0" cellspacing="4" cellpadding="0">
				<TR>
					<TD>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="themleft">
						<TR>
							<TD class="themright"
								onClick="displayAdvance('id_AdvanceTd2','id_AdvanceImg2','Images/thembitopen.gif','Images/thembitclose.gif');">
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
								<TR>
									<TD width="5">&nbsp;</TD>
									<TD class="windowname">基本属性</TD>
									<TD width="20"><img src="Images/thembitclose.gif"
										name="id_img2" width="13" height="13" id="id_AdvanceImg2"></TD>
									<TD width="5">&nbsp;</TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
						<TR>
							<TD align="center" valign="top" class="thembg" id="id_AdvanceTd2">
							<TABLE width="95%" border="0" cellpadding="0" cellspacing="4">
								<TR align="left">
									<TD height="23">类型</TD>
									<TD><select name="NewsType" style="width:" id="NewsType"
										onChange="changeDocType()">
										<option value="1">普通新闻</option>
										<option value="2">图片新闻</option>
										<option value="3">视频新闻</option>
										<option value="4">标题新闻</option>
									</select></TD>
								</TR>
								<TR align="left">
									<TD height="23">头条</TD>
									<TD><label for="TopFlag"><input name="TopFlag"
										type="checkbox" id="TopFlag" value="1"> 头条新闻</label></TD>
								</TR>
								<TR align="left">
									<TD height="23">评论</TD>
									<TD><label for="CommentFlag"> <input
										type="checkbox" name="checkbox2" value="1" id="CommentFlag">
									允许评论</label> &nbsp;</TD>
								</TR>
								<TR align="left">
									<TD height="23">来源</TD>
									<TD>${ReferName}</TD>
								</TR>
								<TR align="left">
									<TD height="23">来源URL</TD>
									<TD><input name="ReferURL" type="text" class="input1"
										id="ReferURL" size="20" value="${ReferURL}" /></TD>
								</TR>
								<TR align="left" valign="middle">
									<TD width="50" height="28">所属频道</TD>
									<TD valign="middle">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR align="left" valign="middle">
											<TD width="110">
											<div id="CatalogName" align="center">${CatalogName}</div>
											</TD>
											<TD width="51"><a href="#" onClick="saveAsDialog()"></a>
											</TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
								<TR align="left" valign="top" id="trQuoteChannel">
									<TD width="50"><A href="#" onClick="copyDialog()">引用到</A>
									<input type="hidden" id="Copy2Article"></TD>
									<TD>
									<div style="overflow:auto;height:80px;width:120px;">

									<table width="100%" border="1" cellpadding="2" cellspacing="1"
										id="CopyCatalogs" style="border-collapse:collapse">
										<tr bgcolor="#BFD0ED">
											<td>栏目名称</td>
											<td width="30">操作</td>
										</tr>
										<tr>
											<td></td>
											<td width="30">&nbsp;</td>
										</tr>
									</table>
									</div>
									</TD>
								</TR>
								<TR align="left">
									<TD height="23">敏感度</TD>
									<TD><select name="select" id="Priority">
										<option value="1" selected>一般</option>
										<option value="2">敏感</option>
										<option value="3">特别敏感</option>
									</select></TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
				<tr>
					<td height="2"></td>
				</tr>
				<TR>
					<TD>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="themleft">
						<TR>
							<TD class="themright"
								onClick="displayAdvance('id_AdvanceTd3','id_AdvanceImg3','Images/thembitopen.gif','Images/thembitclose.gif');">
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
								<TR>
									<TD width="5">&nbsp;</TD>
									<TD class="windowname">其他属性</TD>
									<TD width="20"><img src="Images/thembitclose.gif"
										width="13" height="13" id="id_AdvanceImg3"></TD>
									<TD width="5">&nbsp;</TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
						<TR id="LeftContent_TR2">
							<TD align="center" valign="top" class="thembg" id="id_AdvanceTd3">
							<TABLE width="95%" border="0" cellpadding="0" cellspacing="4">
								<TR align="left" valign="top">
									<TD width="18%" height="23">模板</TD>
									<TD width="82%"><label for="TemplateFlag"> <input
										type="checkbox" name="checkbox" value="1" id="TemplateFlag"
										onClick="showTemplate()"> 独立模板</label></TD>
								</TR>
								<TR align="left" valign="top" id="DivTemplate"
									style="display:none">
									<TD width="18%" height="33">&nbsp;</TD>
									<TD><input name="Template" type="text" class="input1"
										id="Template" size="13" value="${Template}" /></TD>
								</TR>

								<TR align="left" valign="top">
									<TD height="23">上线</TD>
									<TD width="82%"><input name="text" type="text"
										class="input1" id="PublishDate" value="${PublishDate}"
										size="14" ztype="Date"
										style=" font-family:Arial;font-size:10px;" /> <input
										name="text" type="text" class="input1" id="PublishTime"
										value="${PublishTime}" size="7" ztype="Time"
										style="font-family:Arial;font-size:10px;" /> <label
										for="label"></label></TD>
								</TR>
								<TR align="left" valign="top">
									<TD height="23">下线</TD>
									<TD><input name="text3" type="text" class="input1"
										id="DownlineDate" value="${DownlineDate}" size="14"
										ztype="Date" style="font-family:Arial;font-size:10px;" /> <input
										name="text2" type="text" class="input1" id="DownlineTime"
										value="${DownlineTime}" size="7" ztype="Time"
										style="font-family:Arial;font-size:10px;" /></TD>
								</TR>
								<TR align="left" valign="top">
									<TD>摘要 <br>
									</TD>
									<TD><textarea name="Summary" style="width:120px;height:80px"
										id="Summary">${Summary}</textarea></TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
				<TR></TR>
			</TABLE>
			</TD>
		</TR>
	</TABLE>

	</form>
	</body>
	</html>
</z:init>

