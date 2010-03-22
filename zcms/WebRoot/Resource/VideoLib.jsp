<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>视频列表</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
if(isIE){
	window.attachEvent('onresize',onTreeClick);
}else{
	window.addEventListener('resize',onTreeClick,false);
}

Page.onLoad(init);

function init(){
	var flag = false;
	if(!Cookie.get("Resource.LastVideoLib")||Cookie.get("Resource.LastVideoLib")=="0"){
		flag = true;
	}else{
		Tree.select("tree1","cid",Cookie.get("Resource.LastVideoLib"));
		if(Tree.CurrentItem!=null){
			onTreeClick();
		}else{
			flag = true;
		}
	}
	if(flag){
		var tree = $("tree1");
		var arr = tree.getElementsByTagName("p");
		for(var i=0;i<arr.length;i++){
			var p = arr[i];
			if(i==1){
				p.className = "cur";
				Tree.CurrentItem = p;
				p.onclick.apply(p);
				break;
			}
		}
	}
}

function showMenu(event,ele){
	var cid = ele.getAttribute("cid");
	var cname = ele.getAttribute("cname");
	var param = cid + "," + cname;
	var menu = new Menu();
	menu.setParam(param);
	if(!cid){
		if(Application.getPriv("video",Application.CurrentSite,"video_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
		}
	}else{
		if(Application.getPriv("video",ele.getAttribute("InnerCode"),"video_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
		}
	}
	menu.show();
}

function sortLib(param){
	if(!param){
		return;
	}
	var catalogID = param.substring(0, param.indexOf(","));
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 100;
	diag.Title = "栏目排序";
	diag.URL = "./Site/CatalogSort.jsp?CatalogID="+catalogID;
	diag.onLoad = function(){
		$DW.$("Move").focus();
	};
	diag.OKEvent = sortLibSave;
	diag.show();
}

function sortLibSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  	return;
    }
	dc.add("CatalogType","5");
	Server.sendRequest("com.zving.cms.site.Catalog.sortCatalog",dc,function(response){
		if(response.Status==1){
			Tree.loadData("tree1");
			$D.close();
		}
	});	
}

function showVideoMenu(event,videoID){
	var menu = new Menu();
	menu.setEvent(event);
	menu.setParam(videoID);
	menu.addItem("设为封面",setVideoCover,"/Icons/icon039a12.gif");
	menu.addItem("置顶",setTop,"/Icons/icon039a12.gif");
	menu.show();
}

function setVideoCover(videoID){
	var dc = new DataCollection();
	dc.add("ID",videoID);
	Server.sendRequest("com.zving.cms.resource.VideoLib.setVideoCover",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
			
			}
		});
	});	
}

function setTop(videoID){
	var dc = new DataCollection();
	dc.add("ID",videoID);
	Server.sendRequest("com.zving.cms.resource.VideoLib.setTopper",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				if ($V("Name") != "请输入视频名称") {
					DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
			}
		})
	})
}

function change(param){
	if(!param){
		return ;
	}
	
	var cid = param.substring(0, param.indexOf(","));
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 130;
	diag.Title = "修改视频分类";
	diag.URL = "Resource/VideoLibEditDialog.jsp?ID="+cid;
	diag.onLoad = function(){
		$DW.$("Name").focus();		
	};
	diag.OKEvent = changeSave;
	diag.show();
}

function changeSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("com.zving.cms.resource.VideoLib.VideoLibEdit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				Tree.loadData("tree1");
				$D.close();
			}
		});
	});	
}

function onTreeClick(){
	var ele = Tree.CurrentItem;
	if(window.top.document.body.clientWidth<=1084){
		DataList.setParam("dg1",Constant.Size,"8");
	}else if(window.top.document.body.clientWidth<=1236){
		DataList.setParam("dg1",Constant.Size,"10");
	}else if(window.top.document.body.clientWidth<=1388){
		DataList.setParam("dg1",Constant.Size,"12");
	}else{
		DataList.setParam("dg1",Constant.Size,"14");
	}
	var cid = ele.getAttribute("cid");
	if(!cid){
		$("addLibButton").enable();
		$("uploadButton").disable();
		$("editButton").disable();
		$("publishButton").disable();
		$("copyButton").disable();
		$("transferButton").disable();
		$("delButton").disable();
		return ;
	}
	Cookie.set("Resource.LastVideoLib",cid,"2100-01-01");
	$("uploadButton").enable();
	DataList.setParam("dg1",Constant.PageIndex,0);
	DataList.setParam("dg1","CatalogID",cid);
	DataList.setParam("dg1","searchType","Normal");
	DataList.loadData("dg1");
	Application.setAllPriv("video",ele.getAttribute("InnerCode"));
}

function add(param){
	var ParentID;
	var catalogName;
	if(!param){
		if(Tree.CurrentItem!=null){
			ParentID = Tree.CurrentItem.getAttribute("cid");
			catalogName = Tree.CurrentItem.innerText;
		}
	} else {
		ParentID = param.substring(0, param.indexOf(","));
		catalogName = param.substring(param.indexOf(",")+1);
	}
	if(!ParentID){
		ParentID = "";
		catalogName = "视频库";
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 150;
	diag.Title = "新建视频分类";
	diag.URL = "Resource/VideoLibAddDialog.jsp?ParentID=" + ParentID;
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("ParentID"),ParentID,catalogName);;
		$DW.$("Name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  return;
    }
	Server.sendRequest("com.zving.cms.resource.VideoLib.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				Tree.loadData("tree1");
				$D.close();
			}
		});
	});
}

function upload(){
	var CatalogID = Tree.CurrentItem.getAttribute("cid");
	var diag = new Dialog("Diag1");
	diag.Width = 700;
	diag.Height = 460;
	diag.Title = "上传视频";
	diag.URL = "Resource/VideoLibDialog.jsp?CatalogID="+CatalogID;
	diag.onLoad = function(){
		$DW.$("VideoBrowse").hide();
	};
	diag.OKEvent = OK;
	diag.show();
}

function OK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("VideoUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("VideoBrowse")){
	 	currentTab.contentWindow.onVideoReturnBack();
	}
}

function onUploadVideoCompleted(){
	DataList.setParam("dg1",Constant.PageIndex,0);
	DataList.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));
	if ($V("Name") != "请输入视频名称") {
		DataList.setParam("dg1","Name",$V("Name"));
	} else {
		DataList.setParam("dg1","Name","");
	}
	
	DataList.loadData("dg1");
}

function copy(){
	var arr = $NV("VideoID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的视频！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "复制视频";
	diag.URL = "Resource/VideoCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
	var arr = $NV("VideoID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的视频！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("视频不能复制到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("视频已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Video.copy",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
			$D.close();
			}
		});
	});
}

function transfer(){
	var arr = $NV("VideoID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的视频！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "转移视频";
	diag.URL = "Resource/VideoCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = transferSave;
	diag.show();
}

function transferSave(){
	var arr = $NV("VideoID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的视频！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("视频不能转移到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("视频已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Video.transfer",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				if ($V("Name") != "请输入视频名称") {
					DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
			}
		});
	});
}

function edit(videoID){
	if(!videoID){
		var arr = $NV("VideoID");
		if(!arr||arr.length==0){
			Dialog.alert("请先选择要编辑的视频！");
			return;
		}
		videoID = arr[0];
	}
	
	var diag = new Dialog("Diag1");
	diag.Width = 420;
	diag.Height = 460;
	diag.Title = "编辑视频信息";
	diag.URL = "Resource/VideoEditDialog.jsp?ID="+videoID;
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var Integral = $DW.$NV("Integral")
	if(isNaN(Integral)){
		Dialog.alert("积分只能是数字，请重新填写。");
		return;
	}
	var dc = Form.getData($DW.$F("form2"));
	dc.add("IsOriginal",$DW.$NV("IsOriginal"));
	var StartSecond = $DW.$V("StartSecond");
	Server.sendRequest("com.zving.cms.resource.Video.edit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				if ($V("Name") != "请输入视频名称") {
						DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
			}
		});
	});
}

function delLib(param){
	if(!param){
		return ;
	}
	var catalogID = param.substring(0, param.indexOf(","));
	Dialog.confirm("该分类下的视频都将被删除，你确认要删除此视频分类吗？",function(){
		var dc = new DataCollection();
		dc.add("catalogID",catalogID);
		Server.sendRequest("com.zving.cms.resource.VideoLib.delLib",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.location.reload();
				}
			})
		});
	});
}

function del(videoID){	
	if(!videoID){
		var arr = $NV("VideoID");
		if(!arr||arr.length==0){
			Dialog.alert("请先选择要删除的视频！");
			return;
		}
		videoID = arr.join();
	}
	
	Dialog.confirm("这些视频可能与文章或者视频播放器有关联，你确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",videoID);
		Server.sendRequest("com.zving.cms.resource.Video.del",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				if ($V("Name") != "请输入视频名称") {
					DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
				}
			});
		});
	});
}

function doSearch(){
	var name = "";
	if ($V("Name") != "请输入视频名称") {
		name = $V("Name");
	} 
	
	if(window.top.document.body.clientWidth<=1024){
		DataList.setParam("dg1","ClientPageSize","8");
	}else if(window.top.document.body.clientWidth<=1280){
		DataList.setParam("dg1",Constant.Size,"12");
	}else{
		DataList.setParam("dg1","ClientPageSize","14");
	}
	DataList.setParam("dg1",Constant.PageIndex,0);
	if(Tree.CurrentItem.getAttribute("cid")==null){
		DataList.setParam("dg1","CatalogID","");
	}else{
		DataList.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));	
	}
	DataList.setParam("dg1","Name",name);
	DataList.setParam("dg1","StartDate",$V("StartDate"));
	DataList.setParam("dg1","EndDate",$V("EndDate"));
	DataList.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'Name'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	var keyWord = $V("Name");
	if (keyWord == "请输入视频名称") {
		$S("Name","");
	}
}

function clickVideo(ele){
	var box = $("VideoID_box"+$(ele).$A("VideoID"));
	if(box.checked){
		box.checked = false;
	}else{
		box.checked = true;
	}
}


function selectAll(){
	var arr = $N("VideoID");
	if(arr){
		for(var i=0;i< arr.length;i++){
			arr[i].checked = $("AllCheck").checked;
	  }
	}
}

function clickPlay(videoID){
	play(videoID);
}

function play(videoID){
	var diag = new Dialog("Diag1");
	diag.Width = 360;
	diag.Height = 270;
	diag.Title = "播放视频";
	diag.URL = "Resource/VideoPlayDialog.jsp?ID="+videoID;
	diag.ShowButtonRow = false;
	diag.show();	
}

function clickEdit(videoID){
	edit(videoID);
};

function clickDelete(videoID){
	del(videoID);
}

function publish(){
	var arr = $NV("VideoID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要发布的视频！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.cms.resource.VideoLib.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			var p = new Progress(response.get("TaskID"),"正在发布...");
			p.show();
		}
	});
}

</script>
</head>
<body oncontextmenu="return false;">
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd"><z:tree id="tree1" style="height:450px;width:160px;" method="com.zving.cms.resource.VideoLib.treeDataBind">
					<p cid='${ID}' cname='${Name}' InnerCode='${InnerCode}' onClick="onTreeClick(this);" oncontextmenu="showMenu(event,this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="blockTable">
			<tr>
				<td style="padding:8px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="addLibButton" priv="video_manage" onClick="add()">
					<img src="../Icons/icon012a1.gif" />新建分类</z:tbutton> <z:tbutton id="uploadButton" priv="video_modify" onClick="upload()">
					<img src="../Icons/icon012a2.gif" />上传</z:tbutton> <z:tbutton id="editButton" priv="video_modify" onClick="edit()">
					<img src="../Icons/icon012a4.gif" />修改</z:tbutton> <z:tbutton id="publishButton" priv="video_modify" onClick="publish()">
					<img src="../Icons/icon012a5.gif" />发布</z:tbutton> <z:tbutton id="copyButton" priv="video_modify" onClick="copy()">
					<img src="../Icons/icon012a12.gif" />复制</z:tbutton> <z:tbutton id="transferButton" priv="video_modify" onClick="transfer()">
					<img src="../Icons/icon012a7.gif" />转移</z:tbutton> <z:tbutton id="delButton" priv="video_modify" onClick="del()">
					<img src="../Icons/icon012a3.gif" />删除</z:tbutton></div>
				</td>
			</tr>
			<tr>
				<td style="padding:2px 10px;"><label><input type="checkbox" id="AllCheck" onClick="selectAll()">全选</label> &nbsp;&nbsp;列出： 从 <input type="text" id="StartDate" style="width:100px; " ztype='date'> 至 <input type="text" id="EndDate" style="width:100px; " ztype='date'> &nbsp;关键词: <input name="Name" type="text" id="Name" onFocus="delKeyWord();" style="width:110px"> <input type="button" name="Submitbutton" value="查询" onClick="doSearch()" id="Submitbutton"></td>
			</tr>
			<tr>
				<td style="padding:0 5px;">
				<ul class="img-wrapper" style="height:380px; overflow:hidden; display:block; margin:0;">
					<z:datalist id="dg1" method="com.zving.cms.resource.VideoLib.dg1DataList" size="14" page="true">
						<li>
						<dl>
							<dt><a href='#;' hidefocus><em><img width="120" videoid='${ID}' src='${Alias}${Path}${ImageName}?${ModifyTime}' title='${Name}' onClick='clickVideo(this)' oncontextmenu='showVideoMenu(event,\"${ID}\");' onDblClick=\"clickPlay('${ID}');\"></em></a></dt>
							<dd><em><label><input id='VideoID_box${ID}' name='VideoID' type='checkbox' value='${ID}'>${Name}</label></em><a href='#;'><img src='../Icons/icon403a18.gif' alt='play' title='播放' onClick=\"clickPlay('${ID}');\"></a><a href='#;'><img src='../Framework/Images/icon_edit15.gif' alt='edit' title='编辑' onClick=\"clickEdit('${ID}');\"></a><a href='#;'><img src='../Framework/Images/icon_delete15.gif' alt='delete' title='删除' onClick=\"clickDelete('${ID}');\"></a></dd>
						</dl>
						</li>
					</z:datalist>
				</ul>
				</td>
			</tr>
			<tr>
				<td style="padding:0px 5px;"><z:pagebar target="dg1" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
