<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
var tabType="User";

Page.onLoad(function(){
	init();
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Name",$V("Name"));
		dr.set("Tag",$V("Tag"));
		return true;
	}
});

function init(){
	var flag = false;
	if(!Cookie.get("Resource.LastAudioLib")||Cookie.get("Resource.LastAudioLib")=="0"){
		flag = true;
	}else{
		Tree.select("tree1","cid",Cookie.get("Resource.LastAudioLib"));
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
		if(Application.getPriv("audio",Application.CurrentSite,"audio_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
		}
	}else{
		if(Application.getPriv("audio",ele.getAttribute("InnerCode"),"audio_manage")){
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
	dc.add("CatalogType","6");
	Server.sendRequest("com.zving.cms.site.Catalog.sortCatalog",dc,function(response){
		if(response.Status==1){
			Tree.loadData("tree1");
			$D.close();
		}
	});	
}

function change(param){
	if(!param){
		return ;
	}
	
	var cid = param.substring(0, param.indexOf(","));
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 220;
	diag.Title = "修改音频分类";
	diag.URL = "Resource/AudioLibEditDialog.jsp?ID="+cid;
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
	 
	Server.sendRequest("com.zving.cms.resource.AudioLib.AudioLibEdit",dc,function(response){
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
	Cookie.set("Resource.LastAudioLib",cid,"2100-01-01")
	$("uploadButton").enable();
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","CatalogID",cid);
	DataGrid.loadData("dg1");
	Application.setAllPriv("audio",ele.getAttribute("InnerCode"));
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
		catalogName = "音频库";
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 240;
	diag.Title = "新建音频分类";
	diag.URL = "Resource/AudioLibAddDialog.jsp?ParentID="+ ParentID;
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("ParentID"),ParentID,catalogName);
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
	Server.sendRequest("com.zving.cms.resource.AudioLib.add",dc,function(response){
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
	diag.Width = 800;
	diag.Height = 300;
	diag.Title = "上传音频";
	diag.URL = "Resource/AudioLibDialog.jsp?CatalogID="+CatalogID;
	diag.onLoad = function(){
		$DW.$("AudioBrowse").hide();
		
	};
	diag.OKEvent = OK;
	diag.show();
}

function OK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("AudioUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("AudioBrowse")){
	 	currentTab.contentWindow.onReturnBack();
	}
}

function onUploadCompleted(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));
	DataGrid.loadData("dg1");
}

function RepeatUpload(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要重新上传的音频！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 100;
	diag.Title = "重新上传";
	diag.URL = "Resource/AudioRepeatUpload.jsp?ID="+arr[0];
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value="关闭";
}

function save(){
	DataGrid.save("dg1","com.zving.cms.resource.AudioLib.dg1Edit",function(){
		DataGrid.loadData('dg1');
	});
}

function copy(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的音频！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "复制音频";
	diag.URL = "Resource/AudioCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的音频！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("音频不能转移到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("音频已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Audio.copy",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
			}
		});
	});
}

function transfer(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的音频！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "转移音频";
	diag.URL = "Resource/AudioCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = transferSave;
	diag.show();
}

function transferSave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的音频！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("音频不能转移到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("音频已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Audio.transfer",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		});
	});
}

function delLib(param){
	if(!param){
		return ;
	}
	var catalogID = param.substring(0, param.indexOf(","));
	Dialog.confirm("该分类下的音频都将被删除，你确认要删除此音频分类吗？",function(){
		var dc = new DataCollection();
		dc.add("catalogID",catalogID);
		Server.sendRequest("com.zving.cms.resource.AudioLib.delLib",dc,function(response){
		Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.location.reload();
				}
			})
		});
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的音频！");
		return;
	}
	Dialog.confirm("这些音频可能与文章或者音频播放器有关联，你确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.resource.Audio.del",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
				}
			});
		});
	})
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchName'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	var keyWord = $V("SearchName");
	if (keyWord == "请输入音频名称") {
		$S("SearchName","");
	}
}

function doSearch(){
	var name = "";
	if ($V("SearchName") != "请输入音频名称") {
		name = $V("SearchName");
	}
	
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Name",name);
	DataList.setParam("dg1","StartDate",$V("StartDate"));
	DataList.setParam("dg1","EndDate",$V("EndDate"));
	DataGrid.loadData("dg1");
}

function publish(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要发布的音频！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.cms.resource.AudioLib.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			var p = new Progress(response.get("TaskID"),"正在发布...");
			p.show();
		}
	});
}

function afterRowDragEnd(type,targetDr,sourceDr,rowIndex,oldIndex){
	if(rowIndex==oldIndex){
		return;
	}
	
	var order = $("dg1").DataSource.get(rowIndex-1,"OrderFlag");
	var target = "";
	var dc = new DataCollection();
	var ds = $("dg1").DataSource;
	var i = rowIndex-1;
	if(i!=0){
		target = ds.get(i-1,"OrderFlag");
		dc.add("Type","After");		
	}else{
		dc.add("Type","Before");
		target = $("dg1").DataSource.get(1,"OrderFlag");
	}
	dc.add("Target",target);
	dc.add("Orders",order);
	var cid = Tree.CurrentItem.getAttribute("cid");
	if (!cid) {
		cid = Cookie.get("Resource.LastAudioLib");
	}
	dc.add("CatalogID",cid);
	DataGrid.showLoading("dg1");
	Server.sendRequest("com.zving.cms.resource.AudioLib.sortColumn",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				DataGrid.loadData("dg1");
			}
		});
	});
}
</script>
</head>
<body oncontextmenu="return false;">
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="1" class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd"><z:tree id="tree1" style="height:450px;width:160px;" method="com.zving.cms.resource.AudioLib.treeDataBind">
					<p cid='${ID}' cname='${Name}' InnerCode='${InnerCode}' onClick="onTreeClick(this);" oncontextmenu="showMenu(event,this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<tr>
				<td style="padding:8px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="addLibButton" priv="audio_manage" onClick="add()">
					<img src="../Icons/icon051a2.gif" />新建分类</z:tbutton> <z:tbutton id="uploadButton" priv="audio_modify" onClick="upload()">
					<img src="../Icons/icon051a13.gif" />上传</z:tbutton> <z:tbutton id="uploadButton" priv="attach_modify" onClick="RepeatUpload()">
					<img src="../Icons/icon051a13.gif" />重新上传</z:tbutton><z:tbutton id="editButton" priv="audio_modify" onClick="save()">
					<img src="../Icons/icon051a4.gif" />保存</z:tbutton> <z:tbutton id="publishButton" priv="audio_modify" onClick="publish()">
					<img src="../Icons/icon051a5.gif" />发布</z:tbutton> <z:tbutton id="copyButton" priv="audio_modify" onClick="copy()">
					<img src="../Icons/icon051a8.gif" />复制</z:tbutton> <z:tbutton id="transferButton" priv="audio_modify" onClick="transfer()">
					<img src="../Icons/icon051a7.gif" />转移</z:tbutton> <z:tbutton id="delButton" priv="audio_modify" onClick="del()">
					<img src="../Icons/icon051a3.gif" />删除</z:tbutton></div>
				</td>
			</tr>
			<tr>
				<td style="padding:4px 10px;">列出： 从 <input type="text" id="StartDate" style="width:100px; " ztype='date'> 至 <input type="text" id="EndDate" style="width:100px; " ztype='date'> &nbsp;关键词: <input name="SearchName" type="text" id="SearchName" onFocus="delKeyWord();" style="width:110px"> <input type="button" name="Submitbutton" value="查询" onClick="doSearch()" id="Submitbutton"></td>
			</tr>
			<tr>
				<td style="padding:0px 5px;"><z:datagrid id="dg1" method="com.zving.cms.resource.AudioLib.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0" class="dataTable" afterDrag="afterRowDragEnd">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="5%" ztype="selector" field="id">&nbsp;</td>
							<td width="13%"><b>音频名称</b></td>
							<td width="9%" align="right"><b>大小</b></td>
							<td width="8%"><b>标签</b></td>
							<td width="9%" align="center" ztype="checkbox" field="IsOriginal" checkedvalue="Y"><b>是否原创</b></td>
							<td width="5%"><b>格式</b></td>
							<td width="8%"><b>创建者</b></td>
							<td width="18%"><b>上传时间</b></td>
							<td width="7%"><b>试听</b></td>
						</tr>
						<tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${Name}</td>
							<td align="right">${FileSize}</td>
							<td>${Tag}</td>
							<td align="center">&nbsp;</td>
							<td>${Suffix}</td>
							<td>${AddUser}</td>
							<td title="${AddTime}">${AddTimeName}</td>
							<td><embed name="movie" type="application/x-shockwave-flash" src="../Tools/musicplayer.swf" width="17" height="17" wmode="transparent" quality="high" flashvars="song_url=..${Alias}${Path}${FileName}"></embed></td>
						</tr>
						<tr ztype="edit">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><input name="text" type="text" class="input1" id="Name" value="${Name}"></td>
							<td align="right">${FileSize}</td>
							<td><input name="text" type="text" class="input1" id="Tag" value="${Tag}"></td>
							<td>&nbsp;</td>
							<td>${Suffix}</td>
							<td>${AddUser}</td>
							<td>${AddTime}</td>
							<td>&nbsp;</td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="11" align="center">${PageBar}</td>
						</tr>
					</table>
				</z:datagrid></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
