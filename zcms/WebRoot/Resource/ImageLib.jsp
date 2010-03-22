<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片分类</title>
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
	if(!Cookie.get("Resource.LastImageLib")||Cookie.get("Resource.LastImageLib")=="0"){
		flag = true;
	}else{
		Tree.select("tree1","cid",Cookie.get("Resource.LastImageLib"));
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
		if(Application.getPriv("image",Application.CurrentSite,"image_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
		}
	}else{
		if(Application.getPriv("image",ele.getAttribute("InnerCode"),"image_manage")){
			menu.setEvent(event);
			menu.addItem("新建",add,"/Icons/icon018a2.gif");
			menu.addItem("修改",change,"/Icons/icon018a4.gif");
			menu.addItem("删除",delLib,"/Icons/icon018a3.gif");
			menu.addItem("排序",sortLib,"/Icons/icon400a13.gif");
		}
	}
	menu.show();
}

function showImageMenu(event,imageID){
	var menu = new Menu();
	menu.setEvent(event);
	menu.setParam(imageID);
	menu.addItem("设为封面",setImageCover,"/Icons/icon039a12.gif");
	menu.addItem("置顶",setTop,"/Icons/icon039a12.gif");
	menu.show();
}

function setImageCover(imageID){
	var dc = new DataCollection();
	dc.add("ID",imageID);
	Server.sendRequest("com.zving.cms.resource.ImageLib.setImageCover",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
			
			}
		});
	});	
}

function setTop(imageID){
	var dc = new DataCollection();
	dc.add("ID",imageID);
	Server.sendRequest("com.zving.cms.resource.ImageLib.setTopper",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				if ($V("Name") != "请输入图片名称") {
					DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
			}
		});
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
	diag.Title = "修改图片分类";
	diag.URL = "Resource/ImageLibEditDialog.jsp?ID="+cid;
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
	 
	Server.sendRequest("com.zving.cms.resource.ImageLib.ImageLibEdit",dc,function(response){
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
	Cookie.set("Resource.LastImageLib",cid,"2100-01-01");
	$("uploadButton").enable();
	DataList.setParam("dg1",Constant.PageIndex,0);
	DataList.setParam("dg1","CatalogID",cid);
	DataList.setParam("dg1","searchType","Normal");
	DataList.loadData("dg1");
	Application.setAllPriv("image",ele.getAttribute("InnerCode"));
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
		catalogName = "图片库";
	}
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 150;
	diag.Title = "新建图片分类";
	diag.URL = "Resource/ImageLibAddDialog.jsp?ParentID=" +ParentID;
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
	Server.sendRequest("com.zving.cms.resource.ImageLib.add",dc,function(response){
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
    
	diag.Width = 780;
	diag.Height = 460;
	diag.Title = "图片上传";
	diag.URL = "Resource/ImageLibDialog.jsp?CatalogID="+CatalogID;
	diag.onLoad = function(){
		$DW.$("ImageBrowse").hide();
	};
	diag.OKEvent = OK;	
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

function onUploadCompleted(){
	DataList.setParam("dg1",Constant.PageIndex,0);
	DataList.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));
	if ($V("Name") != "请输入图片名称") {
		DataList.setParam("dg1","Name",$V("Name"));
	} else {
		DataList.setParam("dg1","Name","");
	}
	DataList.loadData("dg1");
}

function edit(imageID){
	if(!imageID){
		imageID = "";
		var arr = $NV("ImageID");
		if(!arr||arr.length==0){
			Dialog.alert("请先选择要编辑的图片！");
			return;
		}
		for(var i=0;i<arr.length;i++){
			imageID += arr[i];
			if(i!=arr.length-1){
				imageID += ",";
			}
		}
	  }	
	
		var diag = new Dialog("Diag1");
		diag.Width = 750;
		diag.Height = 480;
		diag.Title = "编辑图片";
		diag.URL = "Resource/ImageEditDialog.jsp?IDs="+imageID;
		diag.OKEvent = editSave;
		diag.onLoad = function(){
			$DW.$("Name").focus();
		};
		diag.show();
	
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	Server.sendRequest("com.zving.cms.resource.Image.imagesEdit",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				if ($V("Name") != "请输入图片名称") {
					DataList.setParam("dg1","Name",$V("Name"));
				} else {
					DataList.setParam("dg1","Name","");
				}
				DataList.loadData("dg1");
			}
		});
	});
}

function copy(){
	var arr = $NV("ImageID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的图片！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "复制图片到另一个图片分类";
	diag.URL = "Resource/ImageCopyDialog.jsp?CatalogID="+Tree.CurrentItem.getAttribute("cid");
	diag.OKEvent = copySave;
	diag.show();
}

function copySave(){
	var arr = $NV("ImageID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要复制的图片！");
		return;
	}
	if(!$DW.$V("CatalogID")){
		Dialog.alert("图片不能复制到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("图片已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Image.copy",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
			}
		});
	});
}

function transfer(){
	var arr = $NV("ImageID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的图片！");
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 60;
	diag.Title = "转移图片到另一个图片分类";
	diag.URL = "Resource/ImageCopyDialog.jsp";
	diag.OKEvent = transferSave;
	diag.show();
}

function transferSave(){
	var arr = $NV("ImageID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要转移的图片！");
		return;
	}
	if(!$DW.$V("CatalogID")){
		Dialog.alert("图片不能转移到站点下！");
		return;
	}
	if($DW.$V("CatalogID")==Tree.CurrentItem.getAttribute("cid")){
		Dialog.alert("图片已经在所属分类下了，请重新选择另一个所属分类！");
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	dc.add("CatalogID",$DW.$V("CatalogID"));
	Server.sendRequest("com.zving.cms.resource.Image.transfer",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataList.setParam("dg1",Constant.PageIndex,0);
				DataList.setParam("dg1","CatalogID",Tree.CurrentItem.getAttribute("cid"));
				if ($V("Name") != "请输入图片名称") {
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
	Dialog.confirm("该分类下的图片都将被删除，你确认要删除此图片分类吗？",function(){
		var dc = new DataCollection();
		dc.add("catalogID",catalogID);
		Server.sendRequest("com.zving.cms.resource.ImageLib.delLib",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.location.reload();
				}
			})
		});
	});
}

function del(imageID){
	var count=1;
	if(!imageID){
	
		var arr = $NV("ImageID");
		if(!arr||arr.length==0){
			Dialog.alert("请先选择要删除的图片！");
			return;
		}
		imageID = arr.join();
		count=arr.length;
	}
	Dialog.confirm("你确认删除这"+count+"张图片吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",imageID);
		Server.sendRequest("com.zving.cms.resource.Image.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					if ($V("Name") != "请输入图片名称") {
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
	if ($V("Name") != "请输入图片名称") {
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
		DataList.setParam("dg1","CatalogID","0");
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
	if (keyWord == "请输入图片名称") {
		$S("Name","");
	}
}

function clickImage(ele){
	var box = $("ImageID_box"+$(ele).$A("ImageID"));
	if(box.checked){
		box.checked = false;
	}else{
		box.checked = true;
	}
}

function selectAll(){
	var arr = $N("ImageID");
	if(arr){
		for(var i=0;i< arr.length;i++){
			arr[i].checked = $("AllCheck").checked;
	  }
	}
}

function view(id){
  var url= "Resource/ImageViewDialog.jsp?ID="+id;
  zoom(url);
}

function clickView(id){
	view(id);
}

function clickEdit(id){
	edit(id);
}

function clickDelete(id){
	del(id);
}

function search(){
	//Tree.setPa
	//Tree.loadData("tree1");
}


function zoom(url){
var pw = window.top;
var doc = pw.document;
var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);;
var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);;
var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;
	var zoomdiv = pw.$("_zoomdiv");
	if(!zoomdiv){
		zoomdiv = pw.document.createElement("div");	
		zoomdiv.id = "_zoomdiv";
		$E.hide(zoomdiv);
	 	pw.document.getElementsByTagName("body")[0].appendChild(zoomdiv);
		zoomdiv.innerHTML="\
			<div id='_zoomBGDiv' style='background-color:#222;position:absolute;top:0px;left:0px;opacity:0.9;filter:alpha(opacity=90);width:" + sw + "px;height:" + sh + "px;z-index:800'></div>\
			<iframe id='_zoomIframe' src='picturezoom.htm' frameborder='0' allowtransparency='true' style='background-color:#transparent;position:absolute;top:0px;left:0px;width:" + sw + "px;height:" + sh + "px;z-index:820;'></iframe>\
			"
	}
	pw.$("_zoomIframe").src=url;
	$E.show(zoomdiv);
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
	dc.add("CatalogType","4");
	Server.sendRequest("com.zving.cms.site.Catalog.sortCatalog",dc,function(response){
		if(response.Status==1){
			Tree.loadData("tree1");
			$D.close();
		}
	});	
}

function publish(){
	var arr = $NV("ImageID");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要发布的图片！");
		return;
	}
	
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.cms.resource.ImageLib.publish",dc,function(response){
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
				<td style="padding:6px;" colspan="2" class="blockTd"><z:tree id="tree1" style="height:450px;width:160px;" method="com.zving.cms.resource.ImageLib.treeDataBind" level="2" lazy="true" expand="false">
					<p cid='${ID}' cname='${Name}' InnerCode='${InnerCode}' onClick="onTreeClick(this);" oncontextmenu="showMenu(event,this);">${Name}</p>
				</z:tree></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="blockTable">
			<tr>
				<td style="padding:6px 10px;" class="blockTd">
				<div style="float: left"><z:tbutton id="addLibButton" priv="image_manage" onClick="add()">
					<img src="../Icons/icon030a1.gif" />新建分类</z:tbutton> <z:tbutton id="uploadButton" priv="image_modify" onClick="upload()">
					<img src="../Icons/icon030a2.gif" />上传</z:tbutton> <z:tbutton id="editButton" priv="image_modify" onClick="edit()">
					<img src="../Icons/icon030a4.gif" />编辑</z:tbutton> <z:tbutton id="publishButton" priv="image_modify" onClick="publish()">
					<img src="../Icons/icon030a5.gif" />发布</z:tbutton> <z:tbutton id="copyButton" priv="image_modify" onClick="copy()">
					<img src="../Icons/icon030a12.gif" />复制</z:tbutton> <z:tbutton id="transferButton" priv="image_modify" onClick="transfer()">
					<img src="../Icons/icon030a7.gif" />转移</z:tbutton> <z:tbutton id="delButton" priv="image_modify" onClick="del()">
					<img src="../Icons/icon030a3.gif" />删除</z:tbutton></div>
				</td>
			</tr>
			<tr>
				<td style="padding:2px 10px;"><label> <input name="checkbox" type="checkbox" id="AllCheck" onClick="selectAll()"> 全选</label> &nbsp;&nbsp;列出： 从 <input type="text" id="StartDate" style="width:100px; " ztype='date'> 至 <input type="text" id="EndDate" style="width:100px; " ztype='date'> &nbsp;关键词: <input name="Name" type="text" id="Name" onFocus="delKeyWord();" style="width:110px"> <input type="button" name="Submitbutton" value="查询" onClick="doSearch()" id="Submitbutton"></td>
			</tr>
			<tr>
				<td style="padding:0 5px">
				<ul class="img-wrapper" style="height:380px; overflow:hidden; display:block; margin:0;">
					<z:datalist id="dg1" method="com.zving.cms.resource.ImageLib.dg1DataList" size="14" page="true">
						<li>
						<dl>
							<dt><a href='#;' hidefocus><em><img imageid='${id}' src='${alias}${path}s_${filename}?${ModifyTime}' title='${name}' oncontextmenu='showImageMenu(event,"${id}");' onClick='clickImage(this)' onDblClick="clickView('${id}');"></em></a></dt>
							<dd><em><label><input id='ImageID_box${id}' name='ImageID' type='checkbox' value='${id}'>${name}</label></em><a href='#;'><img src='../Framework/Images/icon_zoomout.gif' alt='view' title='预览' onClick="clickView('${id}');"></a><a href='#;'><img src='../Framework/Images/icon_edit15.gif' alt='edit' title='编辑' onClick="clickEdit('${id}');"></a><a href='#;'><img src='../Framework/Images/icon_delete15.gif' alt='delete' title='删除' onClick="clickDelete('${id}');"></a></dd>
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
