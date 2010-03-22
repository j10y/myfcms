<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	$("dg1").afterEdit = function(tr,dr){
		var v = new Verify();
		v.add($("Type"),"类别","NotNull");
		v.add($("Name"),"名称","NotNull");
		v.add($("Value"),"配置项值","NotNull&&Length<100");
		if(!v.doVerify()){
			return;
		}
		dr.set("Type",$V("Type"));
		dr.set("Name",$V("Name"));
		dr.set("Value",$V("Value"));
		return true;
	}
});

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 900;
	diag.Height = 420;
	diag.Title = "新建Web采集任务";
	diag.URL = "DataChannel/FromWebDialog.jsp";
	diag.onLoad = function(){
	};
	diag.OKEvent = addSave;
	diag.ShowButtonRow = true;
	window.EditFlag = false;
	diag.show();
}

function edit(tr){
	var dr;
	if(!tr){
		var dt = DataGrid.getSelectedData("dg1");
		if(dt.getRowCount()==0){
			Dialog.alert("请先选择要修改的行!");
			return;
		}
		dr = dt.getDataRow(0);
	}else{
		dr = $("dg1").DataSource.Rows[tr.rowIndex-1];
	}
	var xml = dr.get("ConfigXML");
	xml = xml.substr(xml.indexOf('\n')+1);
	var doc = toXMLDOM(xml);
	var nodes = doc.firstChild.childNodes;
	var len = nodes.length;
	var map = {};
	var j = 1,m=1;
	for(var i=0;i<len;i++){
		var ele = nodes[i];
		var name = ele.nodeName;
		if (name=="config") {
			map[ele.getAttribute("key")] = ele.getAttribute("value");
		}
		if (name=="urls") {
			map["URL" + ele.getAttribute("level")] = ele.firstChild.nodeValue;
		}
		if (name=="filterExpr") {
			map["FilterExpr"] = ele.firstChild.nodeValue;
		}
		if (name=="script") {
			map["ScriptLang"] = ele.getAttribute("language");
			map["Script"] = ele.firstChild.nodeValue;
		}
		if (name=="template") {
			map["RefCode" + j] = ele.getAttribute("code");
			map["Template" + j] = ele.firstChild.nodeValue;
			j++;
		}
		if (name=="filterBlock") {
			map["FilterBlock" + m] = ele.firstChild.nodeValue;
			m++;
		}
	}
	map["ID"] = dr.get("ID");
	map["Name"] = dr.get("Name");
	map["Intro"] = dr.get("Intro");
	map["Type"] = dr.get("Type");
	
	var diag = new Dialog("Diag1");
	diag.Width = 900;
	diag.Height = 420;
	diag.Title = "修改Web采集任务";
	diag.URL = "DataChannel/FromWebDialog.jsp?ID="+dr.get("ID");
	window.Map = map;
	window.EditFlag = true;
	diag.onLoad = function(){
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var w = $DW.Tab.getChildTab("Info").contentWindow;
	var dc = w.Form.getData("form2");
	$DW.Tab.onChildTabClick("Info");
	if(w.Verify.hasError()){
		return;
	}
	
	if(dc.get("Type")=="D"){
		w = $DW.Tab.getChildTab("Template").contentWindow;
		$DW.Tab.onChildTabClick("Template");
		if(w.Verify.hasError()){
			return;
		}
		var arr = w.$N("RefCode");
		var str;
		for(var i=0;i<arr.length;i++){
			var ele = arr[i];
			var id = ele.id.replace("RefCode","Template");
			str = null;
			if(w.$(id).getText){
				str = w.$(id).getText();
			}
			if(str==null||str.trim()==""){
				if(!isIE){
					Dialog.hideAllFlash(w);
				}
				Dialog.alert("匹配块内容为空！",function(){
					if(!isIE){
						Dialog.showAllFlash(w);
					}
				});
				return;
			}
			dc.add(ele.id,$V(ele));
			dc.add(id,str);
		}
		w = $DW.Tab.getChildTab("Filter").contentWindow;
		$DW.Tab.onChildTabClick("Filter");
		arr = w.$T("Table");
		for(var i=0;i<arr.length;i++){
			var ele = arr[i];
			var id = ele.id.replace("Table","FilterBlock");
			str = null;
			if(w.$(id)&&w.$(id).getText){
				str = w.$(id).getText();
			}
			if(str==null||str.trim()==""){
				continue;
			}
			dc.add(id,str);
		}
	}
	//脚本允许为空
	/*w = $DW.Tab.getChildTab("Script").contentWindow;
	if(w.$("ScriptEditor").getText){
		var str = w.$("ScriptEditor").getText();
		if(str==null||str.trim()==""){
			Dialog.alert("脚本不能为空！");
			$DW.Tab.onChildTabClick("Config");
			return;		
		}
	}
	dc.add("Script",str);
	*/
	dc.add("Lang",w.$NV("Lang"));
	Server.sendRequest("com.zving.datachannel.FromWeb.add",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message);
			$D.close();
			DataGrid.loadData("dg1");
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	var dc = new DataCollection();	
	dc.add("IDs",arr.join());
	Dialog.confirm("确定要删除该任务吗？",function(){
		Server.sendRequest("com.zving.datachannel.FromWeb.del",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert(response.Message);
				DataGrid.loadData("dg1");
			}
		});
	});
} 

function delResult(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要清空采集数据的行！");
		return;
	}
	Dialog.confirm("采集数据是指从指定URL下载的文本文件和<br>图片文件,<font color=red>不包括已经转入栏目的文章</font>。<br><br>确定要清空该任务的采集数据吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.datachannel.FromWeb.delResult",dc,function(response){
			Dialog.alert(response.Message);
		});											  
	},null,350);
} 

function save(){
	Dialog.alert("不能保存!");
}

function execute(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择行！");
		return;
	}
	var taskID;
	Dialog.confirm("确定要执行该任务吗？",function(){
		var dc = new DataCollection();	
		dc.add("ID",arr[0]);
		Server.sendRequest("com.zving.datachannel.FromWeb.execute",dc,function(response){
			if(response.Status==1){
				taskID = response.get("TaskID");
				var p = new Progress(taskID,"正在抓取Web文件...",700,150);
				p.show();
			}else{
				Dialog.alert(response.Message);	
			}
		});
	});
}

function cancel(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择行！");
		return;
	}
	Dialog.confirm("确定要中止执行该任务吗？",function(){
		var dc = new DataCollection();	
		dc.add("ID",arr[0]);
		Server.sendRequest("com.zving.datachannel.FromWeb.cancel",dc,function(response){
			Dialog.alert(response.Message);
		});
	});
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
			<tr>
				<td valign="middle" class="blockTd"><img src="../Icons/icon007a10.gif" width="20" height="20" /> Web采集任务列表</td>
			</tr>
			<tr>
				<td style="padding:0 8px 4px;">
				<z:tbutton onClick="add()"><img src="../Icons/icon007a2.gif" width="20" height="20" />新建</z:tbutton> 
				<z:tbutton onClick="edit()"><img src="../Icons/icon007a4.gif" width="20" height="20" />修改</z:tbutton> 
				<z:tbutton onClick="del()"><img src="../Icons/icon007a3.gif" width="20" height="20" />删除</z:tbutton> 
				<z:tbutton onClick="delResult()"><img src="../Icons/icon007a17.gif" width="20" height="20" />清空采集数据</z:tbutton>
				<z:tbutton onClick="execute()"><img src="../Icons/icon403a12.gif" width="20" height="20" />执行任务</z:tbutton> 
				<z:tbutton onClick="cancel()"><img src="../Icons/icon404a3.gif" width="20" height="20" />中止执行</z:tbutton></td>
			</tr>
			<tr>
				<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1" method="com.zving.datachannel.FromWeb.dg1DataBind" page="false">
					<table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="6%" ztype="RowNo"><b>序号</b></td>
							<td width="3%" ztype="selector" field="ID">&nbsp;</td>
							<td width="7%"><b>任务ID</b></td>
							<td width="21%"><b>名称</b></td>
							<td width="10%"><b>采集类别</b></td>
							<td width="43%"><b>起始地址</b></td>
							<td width="10%"><b>线程数</b></td>
						</tr>
						<tr onDblClick='edit(this);' style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>${ID}</td>
							<td>${Name}</td>
							<td>${TypeName}</td>
							<td>${StartURL}</td>
							<td>${ThreadCount}</td>
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
