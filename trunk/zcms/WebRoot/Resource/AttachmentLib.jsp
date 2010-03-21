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
Page.onLoad(function(){
	init();
	$("dg1").afterEdit = function(tr,dr){
		if(Verify.hasError()){
	 		return;
    	}
		dr.set("Name",$V("Name"));
		return true;
	}
});

function init(){
	var flag = false;
	if(!Cookie.get("Resource.LastAttachLib")||Cookie.get("Resource.LastAttachLib")=="0"){
		flag = true;
	}else{
		Tree.select("tree1","cid",Cookie.get("Resource.LastAttachLib"));
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
		if(Application.getPriv("video",Application.CurrentSite,"attach_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
			menu.show();
		}
	}else{
		if(Application.getPriv("video",ele.getAttribute("InnerCode"),"attach_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
			menu.show();
		}
	}
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
	dc.add("CatalogType","7");
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
	diag.Height = 130;
	diag.Title = "修改附件分类";
	diag.URL = "Resource/AttachmentLibEditDialog.jsp?ID="+cid;
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
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.AttachmentLibEdit",dc,function(response){
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
	Cookie.set("Resource.LastAttachLib",cid,"2100-01-01")
	$("uploadButton").enable();
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","CatalogID",cid);
	DataGrid.loadData("dg1");
	Application.setAllPriv("attach",ele.getAttribute("InnerCode"));
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
		catalogName = "附件库";
	}
	var diag = new Dialog("Diag1");
	diag.Width = 410;
	diag.Height = 150;
	diag.Title = "新建附件分类";
	diag.URL = "Resource/AttachmentLibAddDialog.jsp?ParentID="+ ParentID;
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
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.add",dc,function(response){
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
	diag.Width = 500;
	diag.Height = 450;
	diag.Title = "上传附件";
	diag.URL = "Resource/AttachmentLibDialog.jsp?CatalogID="+CatalogID;
	diag.onLoad = function(){
		$DW.$("AttachmentBrowse").hide();
	};
    diag.OKEvent=OK;
	diag.show();
}

function OK(){
	var currentTab = $DW.Tab.getCurrentTab();
	if(currentTab==$DW.Tab.getChildTab("AttachmentUpload")){
 		currentTab.contentWindow.upload();
	}else if(currentTab==$DW.Tab.getChildTab("AttachmentBrowse")){
	 	currentTab.contentWindow.onFileReturnBack();
	}
}

function save(){
	DataGrid.save("dg1","com.zving.cms.resource.AttachmentLib.dg1Edit",function(){
		DataGrid.setParam("dg1","Name",$V("SearchName"));
		DataGrid.loadData('dg1');
	});
}

function copy(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的附件！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "复制附件";
	diag.URL = "Resource/AttachmentCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的附件！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("附件不能复制到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("附件已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Attachment.copy",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
			}
		});
	});
}

function RepeatUpload(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要重新上传的附件！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 100;
	diag.Title = "重新上传";
	diag.URL = "Resource/AttachRepeatUpload.jsp?ID="+arr[0];
	diag.show();
	diag.OKButton.hide();
	diag.CancelButton.value="关闭";
}

function transfer(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的附件！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "转移附件";
	diag.URL = "Resource/AttachmentCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.onLoad = function(){
		$DW.Selector.setValueEx($DW.$("CatalogID"),Tree.CurrentItem.getAttribute("cid"),Tree.CurrentItem.innerText);
	}
	diag.OKEvent = transferSave;
	diag.show();
}

function transferSave(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的附件！");
		return;
	}
	if(!$DW.$V("CatalogID")||$DW.$V("CatalogID")=="0"){
		Dialog.alert("附件不能转移到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("附件已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Attachment.transfer",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				if ($V("SearchName") != "请输入附件名称") {
					DataGrid.setParam("dg1","Name",$V("SearchName"));
				} else {
					DataGrid.setParam("dg1","Name","");
				}
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
	Dialog.confirm("该分类下的附件都将被删除，你确认要删除此附件分类吗？",function(){
		var dc = new DataCollection();
		dc.add("catalogID",catalogID);
		Server.sendRequest("com.zving.cms.resource.AttachmentLib.delLib",dc,function(response){
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
		Dialog.alert("请先选择要删除的附件！");
		return;
	}
	Dialog.confirm("这些附件可能与文章或者图片有关联，你确认要删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.resource.Attachment.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					if ($V("SearchName") != "请输入附件名称") {
						DataGrid.setParam("dg1","Name",$V("SearchName"));
					} else {
						DataGrid.setParam("dg1","Name","");
					}
					DataGrid.loadData("dg1");
				}
			});
		})
	});
}

function onFileUploadCompleted(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));
	if ($V("SearchName") != "请输入附件名称") {
		DataGrid.setParam("dg1","Name",$V("SearchName"));
	} else {
		DataGrid.setParam("dg1","Name","");
	}
	DataGrid.loadData("dg1");
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
	if (keyWord == "请输入附件名称") {
		$S("SearchName","");
	}
}

function doSearch(){
	var name = "";
	if ($V("SearchName") != "请输入附件名称") {
		name = $V("SearchName");
	}
	
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","SearchName",name);
	DataList.setParam("dg1","StartDate",$V("StartDate"));
	DataList.setParam("dg1","EndDate",$V("EndDate"));
	DataGrid.loadData("dg1");
}

function publish(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(arr == null || arr.length==0){
		Dialog.alert("请先选择要发布的附件！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.publish",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			var p = new Progress(response.get("TaskID"),"正在发布...");
			p.show();
		}
	});
}

function download(attid){
	if(!attid||attid==""){
		return;
	}else{
		window.open(Server.ContextPath + "Services/AttachDownLoad.jsp?id="+attid,"_blank");	
	}
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
		cid = Cookie.get("Resource.LastAttachLib");
	}
	dc.add("CatalogID",cid);
	DataGrid.showLoading("dg1");
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.sortColumn",dc,function(response){
		if(response.Status==1){
			DataGrid.loadData("dg1");
		}else{
			Dialog.alert(response.Message);	
		}
	});
}

function ImageBrowse(){
    var arr=DataGrid.getSelectedValue("dg1");
  	var diag = new Dialog("Diag2");
	diag.Width = 400;
	diag.Height = 180;
	diag.Title = "查看/修改缩略图";
	diag.URL = "Resource/AttachmentImageEditDialog.jsp?ID="+arr[0];
	diag.onLoad = function(){
	$DW.$("Name").focus();		
	};
	diag.OKEvent = ImageEditSave;
	diag.show();
}

function ImageEditSave(){
	var dc = Form.getData($DW.$F("form2"));

	if($DW.Verify.hasError()){
	  return;
     }
	 
	Server.sendRequest("com.zving.cms.resource.AttachmentLib.dialogEdit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
			}
		});
	});	
}


</script>
</head>
<body onContextMenu="return false;">
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd"><z:tree id="tree1" style="height:450px;width:160px;" method="com.zving.cms.resource.AttachmentLib.treeDataBind">
					<p cid='${ID}' cname='${Name}' InnerCode='${InnerCode}' onClick="onTreeClick(this);" onContextMenu="showMenu(event,this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<tr>
				<td style="padding:8px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="addLibButton" priv="attach_manage" onClick="add()">
					<img src="../Icons/icon003a2.gif" />新建分类</z:tbutton> <z:tbutton id="uploadButton" priv="attach_modify" onClick="upload()">
					<img src="../Icons/icon003a8.gif" />上传</z:tbutton> <z:tbutton id="uploadButton" priv="attach_modify" onClick="RepeatUpload()">
					<img src="../Icons/icon003a8.gif" />重新上传</z:tbutton> <z:tbutton id="editButton" priv="attach_modify" onClick="save()">
					<img src="../Icons/icon003a4.gif" />保存</z:tbutton> <z:tbutton id="publishButton" priv="attach_modify" onClick="publish()">
					<img src="../Icons/icon003a4.gif" />发布</z:tbutton> <z:tbutton id="copyButton" priv="attach_modify" onClick="copy()">
					<img src="../Icons/icon003a17.gif" />复制</z:tbutton> <z:tbutton id="transferButton" priv="attach_modify" onClick="transfer()">
					<img src="../Icons/icon003a7.gif" />转移</z:tbutton> <z:tbutton id="delButton" priv="attach_modify" onClick="del()">
					<img src="../Icons/icon003a3.gif" />删除</z:tbutton></div>
				</td>
			</tr>
			<tr>
				<td style="padding:4px 10px;">列出： 从 <input type="text" id="StartDate" style="width:100px; " ztype='date'> 至 <input type="text" id="EndDate" style="width:100px; " ztype='date'> &nbsp;关键词: <input name="SearchName" type="text" id="SearchName" onFocus="delKeyWord();" style="width:110px"> <input type="button" name="Submitbutton" value="查询" onClick="doSearch()" id="Submitbutton"></td>
			</tr>
			<tr>
				<td style="padding:0px 5px;">
				<z:datagrid id="dg1" method="com.zving.cms.resource.AttachmentLib.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0" class="dataTable" afterDrag="afterRowDragEnd">
						<tr ztype="head" class="dataTableHead">
							<td width="3%" ztype="RowNo" drag="true"><img
								src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
							<td width="3%" ztype="selector" field="id">&nbsp;</td>
							<td width="18%"><b>附件名称</b></td>
							<td width="6%" align="center"><b>大小</b></td>
							<td width="5%"><b>格式</b></td>
							<td width="11%"><b>创建者</b></td>
							<td width="10%"><b>上传时间</b></td>
							<td width="15%"><b>操作</b></td>
							<td width="5%"><b>下载</b></td>
						</tr>
						<tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${Name}${LockImage}</td>
							<td align="center">${FileSize}</td>
							<td>${SuffixImage}</td>
							<td>${AddUser}</td>
							<td title="${AddTime}">${AddTimeName}</td>
							<td><a href="javascript:ImageBrowse()">查看/编辑索引图</a></td>
							<td><a href="${AttachmentLink}" target="_blank"><img src="../Framework/Images/icon_down.gif" width="15" height="15" style="cursor:pointer;" title="下载次数:${Prop1}"/></a></td>
						</tr>
						<tr ztype="edit">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><input name="text" type="text" class="input1" id="Name" size="30" value="${Name}"></td>
							<td align="center">${FileSize}</td>
							<td>${SuffixImage}</td>
							<td>${AddUser}</td>
							<td title="${AddTime}">${AddTimeName}</td>
							<td><a href="javascript:ImageBrowse()">查看/编辑索引图</a></td>
							<td><img src="../Framework/Images/icon_down.gif" width="15" height="15" /></td>
						</tr>
						<tr ztype="pagebar">
							<td colspan="10" align="center">${PageBar}</td>
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
