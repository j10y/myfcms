<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.document.Article.init">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章编辑：${Title}</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Controls/StyleToolbar.js"></script>
<script src="../Editor/fckeditor.js"></script>
<style type="text/css">
<!--
html,body{overflow:auto !important; overflow:hidden; margin:0; padding:0;}
body{ text-align:left; font-size:12px;color:#666666; margin:0px; background:#F6F9FD;}
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
textarea,form,select,input,button,option,iframe{font-size:12px;	background-color: transparent;}
#otherbtn a, #otherbtn a:visited{
display:block;
width:85px;
height:22px;
line-height:22px;
padding:0;
text-align: left;
text-decoration:none;
background:url(Images/btbg.gif) no-repeat center center;
}
#otherbtn img,#otherbtn img{margin:0 10px 0 5px;}
#otherbtn a:hover{background:url(Images/btbgo.gif) no-repeat center center;}
.tbSwitch{height: 26px;position:absolute;background:url("Images/paggingbar.gif");}
#wrapper{margin:0; border:0; height:100%; overflow:hidden;}
#pageBarDiv{position:fixed;_position:absolute; bottom:0; left:0; width:100%;}
-->
</style>
	<script>
var CatalogID = '${CatalogID}';
var InnerCode = '${InnerCode}';
var ChannelID = '';
var TempArticleID = '';

var pages = ${Pages};
var currentPage = 1;
var contents;
var PAGE_SPLIT ="<!--_ZVING_PAGE_BREAK_-->";
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

function save(entryId,actionId){
	var dc = Form.getData("form1");
	dc.add("entryId",entryId);
	dc.add("actionId",actionId);

	if(Verify.hasError()){
		return;
    }
	
	editor = FCKeditorAPI.GetInstance('Content');
    contents[currentPage-1]=editor.GetXHTML(false);
    var content = contents.join(PAGE_SPLIT);
	dc.add("Content",content);

	dc.add("Type",$V("NewsType"));
	dc.add("Attribute",$NV("Attribute"));
	
	if(!$("TopFlag").checked){
		dc.add("TopFlag",0);
	}
	if(!$("TemplateFlag").checked){
		dc.add("TemplateFlag",0);
	}
	if(!$("CommentFlag").checked){
		dc.add("CommentFlag",0);
	}
  
  Dialog.wait("正在处理...");
	Server.sendRequest("com.zving.cms.document.Article.save",dc,function(response){
		Dialog.closeEx();
		if(response.Status==1){
			var st = response.get("SaveTime");
			$("SaveTime").innerHTML = st;
			$S("Method","UPDATE"); //保存后将状态设置为更新
			isDirty = false;
			
			Dialog.alert("文章保存成功");
			var buttonDiv = response.get("buttonDiv");
			if(buttonDiv){
				$("buttonDiv").innerHTML=buttonDiv;
			}else{
				$("buttonDiv").innerHTML="";
			}
			opener.DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
			opener.DataGrid.setParam("dg1",Constant.PageIndex,0);
     		opener.DataGrid.loadData("dg1");
     		if("Y"==response.get("ContentChanged")){
     			FCKeditorAPI.GetInstance('Content').SetHTML(response.get("Content"),true);
     		}
			$S("Keyword",response.get("Keyword"));
		}else{
			Dialog.alert(response.Message);
		}
	});
}


function autoSave(){
	var content = editor.GetXHTML(false);
	isDirty = (content==contents[currentPage-1]) ? isDirty:true;
	
	if(!isDirty){
		return;
	}
	if($V("Title")==""){
		return;
	}
	
	var dc = Form.getData($F("form1"));
    contents[currentPage-1] = editor.GetXHTML(false);
	var content = contents.join(PAGE_SPLIT);;
	dc.add("Content",content);
	
	dc.add("Type",$V("NewsType"));

	if(!$("TopFlag").checked){
		dc.add("TopFlag",0);
	}
	if(!$("TemplateFlag").checked){
		dc.add("TemplateFlag",0);
	}
	if(!$("CommentFlag").checked){
		dc.add("CommentFlag",0);
	}
  
	Server.sendRequest("com.zving.cms.document.Article.saveVersion",dc,function(response){
		if(response.Status==1){
			var st = response.get("SaveTime");
			$("SaveTime").innerHTML = st;
		}
	});
}

function doAction(entryId,actionId){
	var dc = new DataCollection();
	dc.add("entryId",entryId);
	dc.add("actionId",actionId);
	Server.sendRequest("com.zving.cms.document.Article.doAction",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				var buttonDiv = response.get("buttonDiv");
				if(buttonDiv){
					$("buttonDiv").innerHTML=buttonDiv;
				}else{
					$("buttonDiv").innerHTML="";
				}
			}
		});
	});
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
	diag.Width = 700;
	diag.Height = 400;
	diag.Title ="浏览模板";
	diag.URL = "Site/TemplateExplorer.jsp?SiteID="+$V("SiteID");
	goBack = function(params){
		$S(ele,params);
	}
	diag.OKEvent = afterSelect;
	diag.show();
}

function afterSelect(){
	$DW.onOK();
}

function changeDocType(){
    var ele = $("NewsType");
	if(ele.value=="4"){
		$("DivRedirect").style.display="";
		$("DivContent").style.display="none";
	} else {
		$("DivRedirect").style.display="none";
		$("DivContent").style.display="";
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
	diag.Width = 500;
	diag.Height = 370;
	diag.Title = "另存为";
	diag.URL = "Site/CatalogListDialog.jsp?Type=0";
	diag.onLoad = function(){
	};
	diag.OKEvent = doSaveAs;
	diag.show();
}

function doSaveAs(){
	var arrDest = $DW.$NV("CatalogID");
  if(arrDest == null || arrDest.length==0){
		Dialog.alert("请先选栏目！");
		return;
	}else{
		var otherCatalogID = arrDest[0];
		var catalogName = $DW.$("span_"+otherCatalogID).innerHTML;
		$S("CatalogID",otherCatalogID);
		$("CatalogName").innerHTML = catalogName;
		$D.close();
	}
}

//复制
function copyDialog(){
	var diag = new Dialog("Diag1");
	diag.Width = 500;
	diag.Height = 410;
	diag.Title = "选择复制到的栏目";
	diag.URL = "Site/CatalogListDialog.jsp?Type=1&Action=copy";
	diag.onLoad = function(){
	};
	diag.OKEvent = addCopy;
	diag.show();
}

function addItem(name,id){
	var tableEle = $("CopyCatalogs");
	var len = tableEle.rows.length;
	var row = tableEle.insertRow(len);
	row.insertCell(0).innerHTML = name;
	row.insertCell(1).innerHTML = "<img src=\"Images/button_delete_small.gif\""
	                            + " border=0 style=\"cursor: pointer;\" "
	                            + "alt=\"删除当前引用到的栏目\" onClick=\"delItem(this,"+id+");\">";
}

function delItem(ele,id){
	var tableEle = $("CopyCatalogs");
	var rowIndex;
	/*
	if(isIE){
		rowIndex = ele.parentElement.parentElement.rowIndex;
	}else{
		rowIndex = ele.parentElement.parentElement.parentElement.rowIndex;
	}*/
	rowIndex=ele.parentElement.parentElement.rowIndex;
	tableEle.deleteRow(rowIndex);
	var str = $V("Copy2Article");
	var arr = str.split(",");
	arr.remove(id);
	$S("Copy2Article",arr.join(","));
}


function addCopy(){
  var arrDest = $DW.$NV("CatalogID");
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
			var catalogName = $DW.$("span_"+otherCatalogID).innerHTML;
			addItem(catalogName,otherCatalogID);
			arr.push(otherCatalogID);
		}
	}
	
	$S("ReferType",$DW.$NV("ReferType"));
	$S("Copy2Article",arr.join(","));
	$D.close();
}

function version(){
	var diag = new Dialog("Diag1");
	diag.Width = 680;
	diag.Height = 380;
	diag.Title = "版本";
	diag.URL = "Document/ArticleVersionDialog.jsp?ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
	};
	diag.show();
	$E.hide(diag.OKButton);
	diag.CancelButton.value = "确 定";
}

function articleLog(){
	var diag = new Dialog("Diag1");
	diag.Width = 680;
	diag.Height = 350;
	diag.Title = "处理历史";
	diag.URL = "Document/ArticleLogDialog.jsp?ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
	};
	diag.show();
	$E.hide(diag.OKButton);
	diag.CancelButton.value = "确 定";
}

function closeX(){
	window.close();
}

function note(){
	var diag = new Dialog("Diag1");
	diag.Width = 650;
	diag.Height = 350;
	diag.Title = "批注";
	diag.URL = "Document/ArticleNoteDialog.jsp?ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
	};
	diag.ShowButtonRow = false;
	diag.show();
}

function del(){
	if($V("Method") == "ADD"){
		return;
	}
	Dialog.confirm("确认删除吗？点击”确认“删除，点击”取消“返回。",function(){
		var dc = new DataCollection();
		dc.add("ArticleID",$V("ArticleID"));
		Server.sendRequest("com.zving.cms.document.Article.del",dc,function(response){
			if(response.Status==1){
				Dialog.alert("文章删除成功");
		    window.close();
			}else{
				Dialog.alert(response.Message);
			}
		});
	});
}

function addRelaArticle(){
	var diag = new Dialog("Diag1");
	diag.Width = 550;
	diag.Height = 340;
	diag.Title = "相关文章";
	diag.URL = "Document/RelaDocDialog.jsp?RelativeArticle="+$V("RelativeArticle")+"&ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
	};
	diag.OKEvent = addRelaArticleSave;
	diag.show();
}

function addRelaArticleSave(){
	$S("RelativeArticle",$DW.$V("ArticleIDs"));
	$D.close();
}

function addRecArticle(){
	var diag = new Dialog("Diag1");
	diag.Width = 550;
	diag.Height = 340;
	diag.Title = "推荐文章";
	diag.URL = "Document/RecommendDocDialog.jsp?RecommendArticle="+$V("RecommendArticle")+"&ArticleID="+$V("ArticleID");
	diag.onLoad = function(){
		
	};
	diag.OKEvent = addRecArticleSave;
	diag.show();
}

function addRecArticleSave(){
	$S("RecommendArticle",$DW.$V("ArticleIDs"));
	$D.close();
}

//发布文章
function publish(workflow){
	if(workflow){
	  var dc = new DataCollection();
		dc.add("ArticleID",$V("ArticleID"));
		Server.sendRequest("com.zving.cms.document.Article.publish",dc,function(response){
			if(response.Status==1){
				Dialog.alert("文章发布成功");
			}else{
				Dialog.alert(response.Message);
			}
		});
	}else{
		var dc = Form.getData("form1");
	  
		if(Verify.hasError()){
		  return;
	  }
		
		
		editor = FCKeditorAPI.GetInstance('Content');
	 	contents[currentPage-1]=editor.GetXHTML(false);
	  
		var content = contents.join(PAGE_SPLIT);
		
		dc.add("Content",content);
		
		dc.add("Type",$V("NewsType"));
		
		if(!$("TopFlag").checked){
			dc.add("TopFlag",0);
		}
		if(!$("TemplateFlag").checked){
			dc.add("TemplateFlag",0);
		}
		if(!$("CommentFlag").checked){
			dc.add("CommentFlag",0);
		}
	  	
		Dialog.wait("正在发布...");
		Server.sendRequest("com.zving.cms.document.Article.saveAndPublish",dc,function(response){
			Dialog.closeEx();
			if(response.Status==1){
				var st = response.get("SaveTime");
				$("SaveTime").innerHTML = st;
				$S("Method","UPDATE"); //保存后将状态设置为更新
				isDirty = false;
				
				Dialog.alert("文章发布成功");
				var buttonDiv = response.get("buttonDiv");
				if(buttonDiv){
					$("buttonDiv").innerHTML=buttonDiv;
				}else{
					$("buttonDiv").innerHTML="";
				}
				opener.DataGrid.setParam("dg1","CatalogID",$V("CatalogID"));
				opener.DataGrid.setParam("dg1",Constant.PageIndex,0);
	     		opener.DataGrid.loadData("dg1");
				FCKeditorAPI.GetInstance('Content').SetHTML(response.get("Content"),true);			
			}else{
				Dialog.alert(response.Message);
			}
		});
	}
}

function htmlDecode(str) {
	return str.replace(/\&quot;/g,"\"").replace(/\&lt;/g,"<").replace(/\&gt;/g,">").replace(/\&nbsp;/g," ").replace(/\&amp;/g,"&");
}


//分页处理
function initPages(){
	var pageList = $("pageList");
	//contents[0] = htmlDecode(contents[0]);
	for(var i = 1;i < pages;i++){
		var li = document.createElement("li");
		li.innerHTML = "<b>页 " + (i+1)+"</b>";
		li.id = "p" + (i+1);
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
			
		var currentTab = $("p"+i);
		currentTab.insertAdjacentElement("afterEnd",li);
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
	
	contents.insert(currentPage,"<p></p>");

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
	
  editor = FCKeditorAPI.GetInstance('Content');
	var content = editor.GetXHTML(false);
	
	//检查是否修改
	isDirty = (content==contents[currentPage-1]) ? isDirty:true;
	
	for (var i=0;i<pages;i++){
		var tab = $("p"+(i+1));
		if (tab.className=="pagetabCurrent"){
			tab.className = "";
		  contents[currentPage-1] = content;
			break;
		}
	}

	currentTab.className = "pagetabCurrent";
	if(editorMode==0){
		var tmpContent = contents[page-1].trim();
		editor.SetHTML(tmpContent);
	}else{
		editorMode = 0;
	}
	currentPage = page;
}

function deletePage(){
	if (pages==1){
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

function addBold(){
	var title = $V("Title");
	if(!title)return;
	if(title.indexOf("<b>")>-1||title.indexOf("</b>")>-1){
		title = title.replace("<b>","");
		title = title.replace("</b>","");
	}else{
		title = "<b>"+title+"</b>";
	}
	$S("Title",title);
}

//关键词选择
function keywordSelect(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 300;
	diag.Title = "选择关键词";
	diag.URL = "Document/ArticleKeywordDialog.jsp";
	diag.onLoad = function(){
		var keyword = $V("Keyword").replace("，"," ");
		keyword = keyword.replace(" "," ");
		$DW.setSelectedKeyword(keyword);
	};
	diag.OKEvent = addSelectKeyword;
	diag.show();
}

//添加关键词
function addSelectKeyword(){
	var keywordSelected = $DW.getSelectedKeyword();
	var nowKeywordStr = $V("Keyword").replace("，"," ");
	nowKeywordStr = nowKeywordStr.replace(","," ");
	nowKeywordStr = nowKeywordStr.replace(" "," ");
	
	if(nowKeywordStr == ""){
		$S("Keyword",keywordSelected.join(" "));
	}else{
		nowKeyword = nowKeywordStr.split(" ");
		var newKeyword = nowKeywordStr.split(" ");
		for(var i = 0;i<keywordSelected.length;i++){
		    for(var j = 0;j<nowKeyword.length;j++){
			   if(nowKeyword[j] == keywordSelected[i]){
			      break;
			   }
			   if(j == nowKeyword.length-1){
			   	newKeyword.push(keywordSelected[i]);
			   }
			}
		}
		$S("Keyword",newKeyword.join(" "));
	}
	$D.close();
}

//添加最近的关键词
function addRecentKeyword(word){
	var nowKeywordStr = $V("Keyword").replace("，"," ");
	nowKeywordStr = nowKeywordStr.replace(","," ");
	nowKeywordStr = nowKeywordStr.replace(" "," ");
	if(nowKeywordStr == ""){
		$S("Keyword",word);
	}else{
		var nowKeyword = nowKeywordStr.split(" ");
		for(var i = 0;i < nowKeyword.length;i++){
			 if(nowKeyword[i] == word){
			      break;
			 }
			 if(i == nowKeyword.length-1){
			 	nowKeywordStr = nowKeywordStr+" "+word;
			 }
		}
		$S("Keyword",nowKeywordStr);
	}
}


function FCKeditor_OnComplete( editorInstance ){
   editorInstance.LinkedField.form.onsubmit = save;
   return;
}

function checkDirty(){
	return FCKeditorAPI.GetInstance('Content').IsDirty();
}

function ChangeColor(){
	var color = $V("hTitleColor");
}

Page.onLoad(function(){
	//设置按钮的权限
	Application.setAllPriv("article",$V("InnerCode"));
	
	var st = new StyleToolbar('TitleStyle',$('Title'),'FontColor');
	st.show();
	
	st = new StyleToolbar('ShortTitleStyle',$('ShortTitle'),"FontColor,Bold,Italic,UnderLine");
	st.show();
	
	var color = $V("hTitleStyle");
	if(color!=""){
		$S("TitleStyle",color);
	}
	
	color = $V("hShortTitleStyle");
	if(color!=""){
		$S("ShortTitleStyle",color);
	}	
	
	if($V("hNewsType")==""){
		$S("NewsType","1");
	}else{
		$S("NewsType",$V("hNewsType"));
	}
	
	changeDocType();
	$S("TopFlag",$V("hTopFlag"));
	$S("CommentFlag",$V("hCommentFlag"));
	if($V("ArticleID") == "" || $V("ArticleID") == "null"){
		$("CommentFlag").checked = true;
	}
	if($V("hPriority")==""){
		$S("Priority","1");
	}else{
		$S("Priority",$V("hPriority"));
	}
	$S("TemplateFlag",$V("hTemplateFlag"));
	showTemplate();

	editor = FCKeditorAPI.GetInstance('Content');
	contents = $V("_Contents").split(PAGE_SPLIT);
	if($V("CopyImageFlag")==""||$V("CopyImageFlag")=="null"||$V("CopyImageFlag")=="Y"){
		$("LocalImageFlag").checked = true;
	}
	initPages();

    //设置自动保存
	setInterval(autoSave, 1000*60*5);
 
	var ieHeight = 103;
	var height = (window.document.body.clientHeight-ieHeight)+"px";
	$("_DivContainer").style.height=height;
});

window.onresize = function(){
	var ieHeight = 103;
	var height = (window.document.body.clientHeight-ieHeight)+"px";
	$("_DivContainer").style.height=height;
}

function reloadArticle(){
	var dc = new DataCollection();
  	dc.add("ArticleID",$V("ArticleID"));
  	Server.sendRequest("com.zving.cms.document.Article.getArticle",dc,function(response){
		if(response.Status==1){
			var pageList = $("pageList");
			for(var i = 1;i<pages;i++){
			    var tab = $("p"+(i+1));
			    pageList.removeChild(tab);
	    	}
	    
		    pages = parseInt(response.get("Pages"));
		    currentPage = 1;
			contents = eval("["+response.get("ContentPages")+"]");
			initPages();
				
			$S("NewsType",response.get("NewsType"));
		  	changeDocType();
			  
		  	if(response.get("TopFlag")=="1"){
				$("TopFlag").checked = true;
			}else{
				$("TopFlag").checked = false;
			}
		  	if(response.get("CommentFlag")=="1"){
				$("CommentFlag").checked = true;
			}else{
				$("CommentFlag").checked = false;
			}
	
			$S("Priority",response.get("Priority"));
			var templateFlag = response.get("TemplateFlag");
			if(templateFlag=="1"){
				$("TemplateFlag").checked = true;
			}else{
				$("TemplateFlag").checked = false;
			}
			showTemplate();
				
			$S("Template",response.get("Template"));
				
			$S("Summary",response.get("Summary"));
				
			editor.SetHTML(contents[0]);
			
			$S("ShortTitle",response.get("ShortTitle"));
			$S("Title",response.get("Title"));
			$S("SubTitle",response.get("SubTitle"));
			$S("Keyword",response.get("Keyword"));
			$S("Author",response.get("Author"));
			$S("RedirectURL",response.get("RedirectURL"));
			
			setActivePage(1);
		}else{
		}
	});
}

function preview(){
	if($V("Method")=="ADD"){
		Dialog.alert("文章尚未保存，不能预览");
		return;
	}else{
	  window.open(Server.ContextPath+"Document/Preview.jsp?ArticleID="+$V("ArticleID"));
  }
}

/***图片上传**/
var customPicName; //自定义图片库上传
function upload(colsName){
	if(colsName){
		customPicName = colsName;
	}
	var CatalogID = $V("CatalogID");
	var diag = new Dialog("Diag1");
	diag.Width = 780;
	diag.Height = 460;
	diag.Title = "图片浏览/上传";
	diag.URL = "Resource/ImageLibDialog.jsp?Search=Search&SelectType=checkbox";
	diag.onLoad = function(){
	};
	diag.OKEvent = OK;	
	diag.CancelEvent = function(){
		FCKeditorAPI.GetInstance("Content").Focus();
		$D.close();
	};
	diag.show();
}

function OK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("ImageUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("ImageBrowse")){
	 	currentTab.contentWindow.onReturnBack();
	}
}

function onUploadCompleted(returnID){
	onReturnBack(returnID);
}

function onReturnBack(returnID){
	if(customPicName!=null){
		var dc = new DataCollection();
		dc.add("PicID",returnID);
		dc.add("Custom","1");
		dc.add("CatalogID", $V("CatalogID"));
		Server.sendRequest("com.zving.cms.document.Article.getPicSrc",dc,function(response){
			$S(customPicName,response.get("1_picSrc"));
			$("Img"+customPicName).src = response.get("1_picSrc");
			customPic = 0;
		})
	}else{
		var dc = new DataCollection();
		dc.add("PicID",returnID);
		dc.add("CatalogID", $V("CatalogID"));
		Server.sendRequest("com.zving.cms.document.Article.getPicSrc",dc,function(response){
			editor = FCKeditorAPI.GetInstance('Content');
			editor.InsertHtml( response.get("1_picSrc") || "" ) ;
			FCKeditorAPI.GetInstance("Content").Focus();
		})
	}
}



/***文件上传**/
var customFileName; //自定义上传
function uploadFile(colsName){
	if(colsName){
		customFileName = colsName;
	}
	var CatalogID = $V("CatalogID");
	var diag = new Dialog("Diag1");
	diag.Width = 760;
	diag.Height = 400;
	diag.Title = "文件浏览/上传";
	diag.URL = "Resource/AttachmentLibDialog.jsp?Search=Search&SelectType=checkbox";
	diag.onLoad = function(){
	};
	diag.OKEvent = FileOK;	
	diag.CancelEvent = function(){
		FCKeditorAPI.GetInstance("Content").Focus();
		$D.close();
	};
	diag.show();
}

function FileOK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("AttachmentUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("AttachmentBrowse")){
	 	currentTab.contentWindow.onFileReturnBack();
	}
}

function onFileUploadCompleted(returnID){
	onFileReturnBack(returnID);
}

function onFileReturnBack(returnID){
	if(customFileName!=null){
		$S(customFileName,returnID);
		customFile = 0;
	}else{
		var dc = new DataCollection();
		dc.add("FileID",returnID);
		dc.add("CatalogID", $V("CatalogID"));
		Server.sendRequest("com.zving.cms.document.Article.getFilePath",dc,function(response){
			editor = FCKeditorAPI.GetInstance('Content');
			editor.InsertHtml( response.get("FilePath") || "" ) ;
			FCKeditorAPI.GetInstance("Content").Focus();
		})
	}
}

/***Flash上传**/
var customFlashName; //自定义Flash上传
function uploadFlash(colsName){
	if(colsName){
		customFlashName = colsName;
	}
	var CatalogID = $V("CatalogID");
	var diag = new Dialog("Diag1");
	diag.Width = 760;
	diag.Height = 400;
	diag.Title = "Flash浏览/上传";
	diag.URL = "Resource/FlashLibDialog.jsp?Search=Search&SelectType=checkbox";
	diag.onLoad = function(){
	};
	diag.OKEvent = FlashOK;	
	diag.CancelEvent = function(){
		FCKeditorAPI.GetInstance("Content").Focus();
		$D.close();
	};
	diag.show();
}

function FlashOK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("FlashUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("FlashBrowse")){
	 	currentTab.contentWindow.onFlashReturnBack();
	}
}

function onFlashUploadCompleted(returnID){
	onFlashReturnBack(returnID);
}

function onFlashReturnBack(returnID){
	if(customFlashName!=null){
		$S(customFlashName,returnID);
		customFile = 0;
	}else{
		var dc = new DataCollection();
		dc.add("FlashID",returnID);
		dc.add("CatalogID", $V("CatalogID"));
		Server.sendRequest("com.zving.cms.document.Article.getFlashPath",dc,function(response){
			editor = FCKeditorAPI.GetInstance('Content');
			editor.InsertHtml( response.get("FlashPath") || "" ) ;
			FCKeditorAPI.GetInstance("Content").Focus();
		})
	}
}

/**视频
*/
function uploadVideo(){
	var CatalogID = $V("CatalogID");
	var diag = new Dialog("Diag1");
	diag.Width = 760;
	diag.Height = 460;
	diag.Title = "视频浏览/上传";
	diag.URL = "Resource/VideoLibDialog.jsp?Search=Search&SelectType=checkbox";
	diag.onLoad = function(){
	};
	diag.OKEvent = uploadVideoOK;	
	diag.CancelEvent = function(){
		FCKeditorAPI.GetInstance("Content").Focus();
		$D.close();
	};	
	diag.show();
}

function uploadVideoOK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("VideoUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("VideoBrowse")){
	 	currentTab.contentWindow.onVideoReturnBack();
	}
}

function onUploadVideoCompleted(returnID){
	onVideoReturnBack(returnID);
}

function onVideoReturnBack(returnID){
	var dc = new DataCollection();
	dc.add("VideoID",returnID);
	dc.add("CatalogID", $V("CatalogID"));
	Server.sendRequest("com.zving.cms.document.Article.getVideoPath",dc,function(response){
		editor = FCKeditorAPI.GetInstance('Content');
		editor.InsertHtml( response.get("VideoPath") || "" ) ;
		FCKeditorAPI.GetInstance("Content").Focus();
	});
}

function insertVote(){
	var CatalogID = $V("CatalogID");
	var diag = new Dialog("Diag1");
	diag.Width = 700;
	diag.Height = 400;
	diag.Title = "插入调查投票";
	diag.URL = "DataService/VoteListDialog.jsp";
	diag.ShowMessageRow = true;
	diag.MessageTitle = "在文章中插入调查投票";
	diag.Message = "在文章中插入调查后，该调查的'JS代码'会插入到文章的源代码当中。";
	diag.OKEvent = insertVoteOK;	
	diag.show();
}

function insertVoteOK(){
	var arr = $DW.DataGrid.getSelectedValue("dg1");
	if(!arr){
		Dialog.alert("请先选择行!");
		return;
	}
	$D.close();
	var dc = new DataCollection();
	dc.add("ID",arr.join());
	dc.add("CatalogID", $V("CatalogID"));
	Server.sendRequest("com.zving.cms.document.Article.getJSCode",dc,function(response){
		editor = FCKeditorAPI.GetInstance('Content');
		editor.InsertHtml( response.get("JSCode") || "" ) ;
	});
}

function create(){
	if($V("Method")=="ADD"){
		Dialog.confirm("文章尚未保存，确认新建文章？",function(){
			window.location="Article.jsp?CatalogID="+$V("CatalogID");
		});
	}else{
		window.location="Article.jsp?CatalogID="+$V("CatalogID");
	}
}

function getKeywordOrSummary(type){
	var dc = new DataCollection();
	editor = FCKeditorAPI.GetInstance('Content');
	contents[currentPage-1]=editor.GetXHTML(false);	  
	var content = contents.join(PAGE_SPLIT);		
	dc.add("Content",content);
	dc.add("Title",$V("Title"));
	dc.add("Type",type);
	Server.sendRequest("com.zving.cms.document.Article.getKeywordOrSummary",dc,function(response){
		if(response.Message==0){
			Dialog.alert(response.Message);
		}else{
			$S(type,response.get("Text"));
			$(type).focus();
			Dialog.alert("提取成功!");
		}
	});
}


</script>
	</head>
	<body>
	<div id="wrapper">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<div id="buttonDiv" class="divbtn">${buttonDiv}</div>
			<z:tbutton priv="article_modify" onClick="note()">
				<img src="../Icons/icon018a4.gif" />批注</z:tbutton> <z:tbutton
				priv="article_modify" onClick="version()">
				<img src="../Icons/icon026a12.gif" width="20" height="20" />版本</z:tbutton> <z:tbutton
				priv="article_modify" onClick="articleLog()">
				<img src="../Icons/icon_column.gif" width="20" height="20" />处理历史</z:tbutton>
				<z:tbutton priv="article_modify" onClick="getKeywordOrSummary('Keyword')">
				<img src="../Icons/icon029a17.gif" width="20" height="20" />提取关键字</z:tbutton>
				<z:tbutton priv="article_modify" onClick="getKeywordOrSummary('Summary')">
				<img src="../Icons/icon029a28.gif" width="20" height="20" />提取摘要</z:tbutton>
				 <z:tbutton
				priv="article_modify" onClick="preview()">
				<img src="../Icons/icon403a3.gif" width="20" height="20" />预览</z:tbutton> <z:tbutton
				onClick="closeX()">
				<img src="../Icons/icon403a11.gif" />关闭</z:tbutton> 
				<z:tbutton priv="article_modify" onClick="create()">
				<img src="../Icons/icon003a2.gif" width="20" height="20" />新建</z:tbutton>
				
			</td>
		</tr>
	</table>
	<form method="post" id="form1"><input type="hidden" id="SiteID"
		value="${SiteID}"> <input type="hidden" id="CatalogID"
		value="${CatalogID}"> <input type="hidden" id="InnerCode"
		value="${InnerCode}"> <input type="hidden" id="IssueID"
		value="${IssueID}"> <input type="hidden" id="hNewsType"
		value="${Type}"> <input type="hidden" id="hTopFlag"
		value="${TopFlag}"> <input type="hidden" id="hPriority"
		value="${Priority}"> <input type="hidden" id="hCommentFlag"
		value="${CommentFlag}"> <input type="hidden"
		id="hTemplateFlag" value="${TemplateFlag}"> <input
		type="hidden" id="ArticleID" value="${ID}"> <input
		type="hidden" id="RelativeArticle" value="${RelativeArticle}">
	<input type="hidden" id="RecommendArticle" value="${RecommendArticle}">
	<input type="hidden" id="Method" value="${Method}"> <input
		type="hidden" id="hTitleStyle" value="${TitleStyle}"> <input
		type="hidden" id="hShortTitleStyle" value="${ShortTitleStyle}">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#F6F9FD" style="border:#B7D8ED 1px solid;">
		<tr>
			<td colspan="3">
			<div id="xToolbar" style="height:52px;"></div>
			</td>
		</tr>
		<tr>
			<td valign='top'>
			<div id="_DivContainer"
				style="text-align:center;overflow:auto;height:1200px;width:100%;background-color:#666666; position:relative">
			<table id="_Table1" width="750" border="0" cellpadding="20"
				bgcolor="#FFFFFF" style="margin:5px auto">
				<tr>
					<td valign="top">
					<table width="100%" cellpadding="2" cellspacing="0">
						<tr>
							<td width="8%" align="center"><strong>标题</strong></td>
							<td align="left"><input name="Title" type="text"
								class="input1" id="Title" size="87" style="${TitleStyle}"
								value="${Title}" verify="标题|NotNull" /></td>
						</tr>
						<tr>
							<td width="8%" align="center"><strong>短标题</strong></td>
							<td align="left"><input name="ShortTitle" type="text"
								class="input1"
								style="background:url(Images/rule.gif) repeat-x left bottom;${ShortTitleStyle}"
								id="ShortTitle" size="50" value="${ShortTitle}" /></td>
						</tr>
						<tr>
							<td align="center"><strong>副标题</strong></td>
							<td align="left"><input name="SubTitle" type="text"
								class="input1" id="SubTitle" size="50" value="${SubTitle}" /> <strong>作&nbsp;者
							<input name="Author" type="text" class="input1" id="Author"
								size="34" value="${Author}" /> </strong></td>
						</tr>
						<tr></tr>
						<tr>
							<td align="center"><strong>关键字</strong></td>
							<td align="left">
							<p><input name="keyword" type="text" class="input1"
								id="Keyword" size="50" value="${Keyword}" /> <input
								name="Button" type="button" class="input2" id="Button"
								value="选择..." onClick="keywordSelect()">
							${RecentKeyword}</p>
							</td>
						</tr>
						<tr>
							<td align="center"><strong>来源</strong></td>
							<td align="left"><input name="ReferName" id="ReferName"
								size="50" type="text" value="${ReferName}"></td>
						</tr>
                        <tr>
							<td align="center"><strong>属性</strong></td>
							<td align="left">${Attribute}</td>
						</tr>
						${CustomColumn}
					</table>
					<!--高级选项-->
					<div id="DivRedirect" style="display:none">
					<table width="100%" cellpadding="2" cellspacing="0">
						<tr>
							<td width="8%" align="center"><strong>转向链接</strong></td>
							<td align="left"><input name="RedirectURL" type="text"
								class="input1" id="RedirectURL" size="50" value="${RedirectURL}" />
							</td>
						</tr>
					</table>
					</div>
					<div id="DivContent">
					<table width="100%" height="400" cellpadding="2" cellspacing="0">
						<tr>
							<td align="center">
							<textarea name="textarea" id="_Content" style="display:none">${Content}</textarea>
							<textarea name="textarea" id="_Contents" style="display:none">${Contents}</textarea>
							<script type="text/javascript">
								var sBasePath = Server.ContextPath+"Editor/" ;
								var oFCKeditor = new FCKeditor( 'Content' ) ;
								oFCKeditor.BasePath	= sBasePath ;
								oFCKeditor.Width = 650 ;
								oFCKeditor.Height = 800 ;
								oFCKeditor.Config['EditorAreaCSS'] = '${CssPath}';
								oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:xToolbar' ;
								oFCKeditor.Value = $V("_Content");
								oFCKeditor.Create() ;
							</script>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</div>
			</td>
			<td width="6" rowspan="3" align="right" bgcolor="#F6F9FD"
				style="border-left: 1px solid #91A9BD;border-right: 1px solid #CFE6F2"><img
				src="Images/right_close.gif" width="6" height="60"
				style="cursor:pointer; display:inline;" title="输入其它信息"
				onClick="displayAdvance('id_AdvanceTd',this,'Images/left_close.gif','Images/right_close.gif');"></td>
			<td width="220" valign="top" id="id_AdvanceTd"
				style="padding-top: 2px; width:220px;">
			<table width="100%" border="0" cellspacing="4" class="cellspacing"
				cellpadding="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="themleft">
						<tr>
							<td class="themright"
								onClick="displayAdvance('id_AdvanceTd2','id_AdvanceImg2','Images/thembitopen.gif','Images/thembitclose.gif');">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="5">&nbsp;</td>
									<td class="windowname">基本属性</td>
									<td width="20"><img src="Images/thembitclose.gif"
										name="id_img2" width="13" height="13" id="id_AdvanceImg2"></td>
									<td width="5">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td align="center" valign="top" class="thembg" id="id_AdvanceTd2">
							<table width="95%" border="0" cellpadding="0" cellspacing="4"
								class="cellspacing">
								<tr align="left">
									<td>类型</td>
									<td><z:select id="NewsType" onChange="changeDocType()">
										<span value="1">普通新闻</span>
										<span value="4">标题新闻</span>
									</z:select></td>
								</tr>
								<tr align="left">
									<td>置顶</td>
									<td><label for="TopFlag"> <input name="TopFlag"
										type="checkbox" id="TopFlag" value="1"> 新闻置顶</label></td>
								</tr>
								<tr align="left">
									<td>评论</td>
									<td><label for="CommentFlag"> <input
										type="checkbox" name="CommentFlag" value="1" id="CommentFlag">
									允许评论</label> &nbsp;</td>
								</tr>
								<tr align="left" valign="middle">
									<td width="50" height="28" nowrap>所属栏目</td>
									<td valign="middle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="left" valign="middle">
											<td width="110">
											<div id="CatalogName" align="center">${CatalogName}</div>
											</td>
											<td width="51"><a href="#" onClick="saveAsDialog()"></a>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr align="left" valign="top" id="trQuoteChannel">
									<td width="50"><a href="#" onClick="copyDialog()">复制到</a>
									<input type="hidden" id="Copy2Article" value="${Copy2Article}">
									<input type="hidden" id="ReferType" value="${ReferType}">
									</td>
									<td>
									<div style="overflow:auto;height:80px;width:140px;">
									<table width="85%" border="1" cellpadding="2" cellspacing="0"
										bordercolor="#dddddd" id="CopyCatalogs">
										<tr bgcolor="#ddeeff">
											<td>栏目名称</td>
											<td width="30">操作</td>
										</tr>
										${CopyCatalogList}
									</table>
									</div>
									</td>
								</tr>
								<tr align="left">
									<td>敏感度</td>
									<td><z:select id="Priority">
										<span value="1" selected>一般</span>
										<span value="2">敏感</span>
										<span value="3">特别敏感</span>
									</z:select></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="themleft">
						<tr>
							<td class="themright"
								onClick="displayAdvance('id_AdvanceTd3','id_AdvanceImg3','Images/thembitopen.gif','Images/thembitclose.gif');">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="5">&nbsp;</td>
									<td class="windowname">其他属性</td>
									<td width="20"><img src="Images/thembitclose.gif"
										width="13" height="13" id="id_AdvanceImg3"></td>
									<td width="5">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr id="LeftContent_TR2">
							<td align="center" valign="top" class="thembg" id="id_AdvanceTd3">
							<table width="95%" border="0" cellpadding="0" cellspacing="4"
								class="cellspacing">
								<tr align="left" valign="top">
									<td width="16%" nowrap>模板</td>
									<td width="84%"><label for="TemplateFlag"> <input
										type="checkbox" name="TemplateFlag" value="1"
										id="TemplateFlag" onClick="showTemplate()"> 独立模板</label></td>
								</tr>
								<tr align="left" valign="top" id="DivTemplate"
									style="display:none">
									<td width="16%" height="20">&nbsp;</td>
									<td><input name="Template" type="text" class="input1"
										id="Template" size="13" value="${Template}"
										verify="独立模板|NotNull"
										condition="$('TemplateFlag').checked==true" /> <input
										name="Button22" type="button" class="input2" id="Button22"
										value="选择" onClick="browse('Template')"></td>
								</tr>
								<tr align="left" valign="top">
									<td>上线</td>
									<td width="84%"><input name="text" type="text"
										class="input1" id="PublishDate" value="${PublishDate}"
										size="14" ztype="Date"
										style=" font-family:Arial;font-size:10px;" /> <input
										name="text" type="text" class="input1" id="PublishTime"
										value="${PublishTime}" size="10" ztype="Time"
										style="font-family:Arial;font-size:9px;" /> <label
										for="label"></label></td>
								</tr>
								<tr align="left" valign="top">
									<td>下线</td>
									<td><input name="text3" type="text" class="input1"
										id="DownlineDate" value="${DownlineDate}" size="14"
										ztype="Date" style="font-family:Arial;font-size:10px;" /> <input
										name="text2" type="text" class="input1" id="DownlineTime"
										value="${DownlineTime}" size="10" ztype="Time"
										style="font-family:Arial;font-size:9px;" /></td>
								</tr>
								<tr align="left" valign="top">
									<td valign="middle">摘要</td>
									<td><textarea name="Summary" cols="25" rows="3"
										id="Summary" style="width:160px">${Summary}</textarea></td>
								</tr>
								<tr align="left" valign="top">
									<td>图片</td>
									<td><input name="LocalImageFlag" type="checkbox" id="LocalImageFlag" value="1"> <label for="LocalImageFlag">自动下载远程图片</label><input type="hidden" id="CopyImageFlag" name="CopyImageFlag" value="${CopyImageFlag}"/></td>
								</tr>
								<tr align="left" valign="top">
									<td>符号</td>
									<td><input name="SBCFlag" type="checkbox"
										id="SBCFlag" value="1" > <label
										for="SBCFlag">半角转全角</label></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="4" cellspacing="4"
						class="cellspacing" id="otherbtn">
						<tr>
							<td align="center" valign="middle"><a href="#;"
								onClick="addRelaArticle()"><img src="Images/icon2.gif"
								width="9" height="9">相关文章</a></td>
							<td align="center" valign="middle"><a href="#;"
								onClick="addRecArticle()"><img src="Images/icon4.gif"
								width="9" height="9">推荐文章</a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</form>
	</div>
	<div id="pageBarDiv" style="padding-right:230px;">
	<table width="100%" id="pageBarTable">
		<tr>
			<td valign="middle" bgcolor="#F7F8FD" class="pagetab">
			<ul id="pageList">
				<li onClick="changePage(event)" onMouseOver="onOverPage(event)"
					onMouseOut="onOutPage(event)" class="pagetabCurrent" id="p1"
					name="tabs"><b>页 1</b></li>
			</ul>
			<span class="add"><a href="#;" onClick="addPage()"
				alt="在当前页后插入"><img src="../Framework/Images/icon_plus.gif"
				border="0" alt="在当前页后插入"></a></span> <span class="add"><a
				href="#;" onClick="deletePage()" alt="删除当前页"><img
				src="../Framework/Images/icon_minus.gif" border="0" alt="删除当前页"></a></span></td>
			<td width="250" valign="middle" align="right"
				style="padding-right:10px;" bgcolor="#F7F8FD" class="pagetab">最后保存时间:<span
				id="SaveTime">${LastModify}</span></td>
		</tr>
	</table>
	</div>
	</body>
	</html>
</z:init>

