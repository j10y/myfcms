<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目权限</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function onSiteChange(){
	window.parent.OldSiteID = $V("SiteID");
	$("AllSelect").checked = false;
	init();
}

Page.onLoad(init);

function init(){
	$("AllSelect").checked = false;
	var dc = new DataCollection();
	dc.add("UserName",$V("UserName"));
	dc.add("SiteID",$V("SiteID"));
	Server.sendRequest("com.zving.platform.UserTabMenu.getCheckedMenu",dc,function(response){
		var checkedMenu= response.get("checkedMenu");
		var menuTree = $N("dg1_TreeRowCheck");
		var menuTreeLength = menuTree.length;
		if(!checkedMenu){
			for (var i=0;i<menuTreeLength;i++) {
				menuTree[i].checked = false;
			}
			return;
		}
		var checkedArray = checkedMenu.split(",");
		var checkedArrayLength = checkedArray.length;
		for(var i=0;i<menuTreeLength;i++){
			menuTree[i].checked = false;
			for(var j = 0;j<checkedArrayLength;j++){
				if(menuTree[i].getAttribute("value")==checkedArray[j].substring(checkedArray[j].indexOf("-")+1)){
					menuTree[i].checked = true;
					break;
				}
			}
		}
	});
}

function save(){
	if(!$V("UserName")){
		Dialog.alert("请先选择一个用户！");
		return;
	}
	var dc = new DataCollection();
	var arr = $N("dg1_TreeRowCheck");
	var dt = new DataTable();
	var cols=[];
	cols.push(["ID","1"]);
	cols.push(["<%=Priv.MENU_BROWSE%>","1"]);
	var values =[];
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			values.push([arr[i].value,"1"]);
		}else{
			values.push([arr[i].value,"0"]);
		}
	}
	dt.init(cols,values);
	dc.add("UserName",$V("UserName"));
	dc.add("SiteID",$V("SiteID"));
	dc.add("dt",dt,"DataTable");
	Server.sendRequest("com.zving.platform.UserTabMenu.save",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				init();					
			}
		});
	});
}

function treeCheckBoxClick(ele){
	var id = ele.id;
	var index = id.substring(id.lastIndexOf("_")+1);
	var checked = ele.checked;
	var level = ele.getAttribute("level");
	var arr = $N(ele.name);
	var length = arr.length;
	// 选中
	if(checked){
		for(var i=index-2;i>=0;i--){
			if(arr[i].getAttribute("level")<level){
				arr[i].checked = true;
				level = arr[i].getAttribute("level");
				if(level==0){
					break;
				}
			}
		}
		level = ele.getAttribute("level");
		for(var i=index;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = true;
			}else {
				break;
			}
		}
	}else{
	// 取消选中
		for(var i=index-2;i>=0;i--){
			if(arr[i].getAttribute("level")<level){
				var check_flag = false;
				var tmp_index = arr[i].id.substring(arr[i].id.lastIndexOf("_")+1);
				for(var j=tmp_index;j<length;j++){
					if(level<=arr[j].getAttribute("level")){
						if(arr[j].checked){
							check_flag = true;
							break;
						}
					}else{
						break;
					}
				}
				arr[i].checked = check_flag;
				
				level = arr[i].getAttribute("level");
				if(level==0){
					break;
				}
			}
		}
		level = ele.getAttribute("level");
		for(var i=index;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = false;
			}else{
				break;
			}
		}
	}
}

function clickAllSelect(){
	var f = $("AllSelect").checked;
	var menuTree = $N("dg1_TreeRowCheck");
	var menuTreeLength = menuTree.length;
	for(var i=0;i<menuTreeLength;i++){
		menuTree[i].checked = f;
	}
}
</script>
</head>
<body>
<table  width="100%" border='0' cellpadding='0' cellspacing='0'>
  <tr>
  <z:init method="com.zving.platform.UserTabMenu.init">
    <td style="padding:4px 5px;"><z:tbutton onClick="save()"><img src="../Icons/icon018a4.gif"/>保存</z:tbutton>
      <input type = "hidden" id ="UserName" value = "${UserName}">
      站点：<z:select id="SiteID" style="width:150px;" onChange="onSiteChange();"> ${SiteID}</z:select>
      <span style="line-height:24px;">&nbsp;&nbsp;&nbsp;用户：<span id="UserNameSpan" style="color:red;"></span>&nbsp;&nbsp;&nbsp;<label>全选&nbsp;<input type="checkbox" id="AllSelect" onClick="clickAllSelect();"/></label></span>
  	</td>
  </z:init>
  </tr>
  <tr>   <td style="padding:0px 5px;">
<z:datagrid id="dg1" method="com.zving.platform.UserTabMenu.dg1DataBind">
			  <table width="100%" cellpadding="2" cellspacing="0"  class="dataTable">
				<tr ztype="head" class="dataTableHead">
				  <td  width="6%" ztype="RowNo">序号</td>
				  <td width="94%" ztype="tree"  field="ID" level="treelevel">菜单名称</td>
			    </tr>
				<tr>
				  <td >&nbsp;</td>
				  <td><img src="../${Icon}" align="absmiddle"/>&nbsp;${Name}</td>
			    </tr>
			  </table>
			</z:datagrid></td>
  </tr>
</table>
</body>
</html>
