<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html style="overflow:auto">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=Config.getAppCode()%><%=Config.getAppName()%></title>
<link rel="shortcut icon" href="Include/favicon.ico" type="image/x-icon" />
<link href="Include/Default.css" rel="stylesheet" type="text/css">
<script src="Framework/Main.js" type="text/javascript"></script>
<script>
function search(){
	DataGrid.setParam("dg1","Keyword",$V("keyword"));
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.loadData("dg1");
}

var Priv = {};
var privTypes = [];
var privTypeItems = [];

function getAllPriv() {
	var dc = new DataCollection();
	Server.sendRequest("com.zving.platform.Login.getAllPriv",dc,function(response){
		parserPriv(response);
	});
}
 
function parserPriv(response){
	Priv.isBranchAdmin = response.get("isBranchAdmin")=="Y"? true:false;
	if(!Priv.isBranchAdmin){
		privTypes = response.get("privTypes").split(',');
		privTypeItems = response.get("privTypeItems").split(',');
		for(var k=0;k<privTypes.length;k++){
			Priv[privTypes[k]+"DT"] = response.get(privTypes[k]+"DT");
		}
		var roleCodes = response.get("roleCodes");
		if(roleCodes){
			var roleCodeArray = roleCodes.split(",");
			Priv.roleCodes = roleCodeArray;
			if(roleCodeArray.length>0&&roleCodeArray[0]){
				for(var i=0;i< roleCodeArray.length;i++){
					for(var j=0;j<privTypes.length;j++){
						Priv[roleCodeArray[i]+privTypes[j]+"DT"] = response.get(roleCodeArray[i]+privTypes[j]+"DT");
					}
				}
			}
		}else{
			Priv.roleCodes = [];//用户可能不属于任何角色
		}
	}
}

Priv.setBtn = function(privType,id,code,button){
	if(Priv.getPriv(privType,id,code)){
		button.enable();
	}else{
		button.disable();
	}
}

Priv.getPriv = function(privType,id,code){
	if(Priv.isBranchAdmin){
		return true;
	}
	var flag = false;
	if(privTypeItems){
		for(var i=0;i<privTypeItems.length;i++){
			if(privTypeItems[i]==code){
				flag = true;
				break;
			}
		}
	}
	if(!flag){
		return false;
	}
	var value = Priv.getUserPriv(privType,id,code);
	if(value=="-1"){
		return false;
	}else if(value=="1"){
		return true;
	}else {
		return Priv.getRolePriv(privType,id,code)=="1"? true:false;
	}
}

Priv.getUserPriv = function(privType,id,code){
	if(privType=="site"){
		var dt = Priv.siteDT;
		if(dt){
			for(var i=0;i < dt.getRowCount(); i++){
				var dr = new DataRow(dt,i);
				if(dr.get("id")==id&&dr.get("code")==code){
					return dr.get("value");
				}
			}
		}
		return null;
	}else {
		var dt = Priv[privType+"DT"];
		if(dt){
			for(var i=0;i < dt.getRowCount(); i++){
				var dr = new DataRow(dt,i);
				if(dr.get("id")==id&&dr.get("code")==code){
					return dr.get("value");
				}
			}
			if(id.length>13){
				// 查找上级的权限
				return Priv.getUserPriv(privType,id.substring(0,id.length-6),code);
			}else{
				// 查找站点的权限
				return Priv.getUserPriv("site",$V("_SiteSelector"),code);
			}
		}else{
			// 查找站点的权限
			return Priv.getUserPriv("site",$V("_SiteSelector"),code);
		}
	}
}

Priv.getRolePriv = function(privType,id,code){
	var value = null;
	if(privType=="site"){
		for(var i=0;i< Priv.roleCodes.length;i++){
			var roleCode = Priv.roleCodes[i];
			var dt = Priv[roleCode+"siteDT"];
			//alert(roleCode+"site:"+dt);
			if(dt){
				for(var j=0;j < dt.getRowCount(); j++){
					var dr = new DataRow(dt,j);
					if(dr.get("id")==id&&dr.get("code")==code){
						value = dr.get("value");
						if(value=="1"){
							return value;
						}
					}
				}
			}
		}
		return "0";
	}else {
		for(var i=0;i< Priv.roleCodes.length;i++){
			var roleCode = Priv.roleCodes[i];
			var dt = Priv[roleCode+privType+"DT"];
			if(dt){
				for(var j=0;j < dt.getRowCount(); j++){
					var dr = new DataRow(dt,j);
					if(dr.get("id")==id&&dr.get("code")==code){
						value = dr.get("value");
						if(value=="1"){
							return value;
						}
					}
				}
			}
		}
		if(value!=null){
			return value;
		}
		if(id.length>13){
			// 查找上级的权限
			return Priv.getRolePriv(privType,id.substring(0,id.length-6),code);
		}else{
			// 查找站点的权限
			return Priv.getRolePriv("site",$V("_SiteSelector"),code);
		}
	}
}

Page.onLoad(function(){
	getNewMessageCount();
	setInterval(getNewMessageCount, 1000*60*10);
});

//获取短消息
function getNewMessageCount(){
	var dc = new DataCollection();
	Server.sendRequest("com.zving.cms.document.Message.getNewMessage",dc,function(response){
		var count = response.get("Count");
		$("MsgCount").innerHTML = count;
	});
}

//定位到短消息菜单
function getMessage(){
	var mainEle = $("_Menu_120");
	Application.onMainMenuClick(mainEle);
	var ele = $("_ChildMenuItem_132");
	Application.onChildMenuClick(ele)
}

</script>
</head>
<body style="min-width:1003px">
<z:init method="com.zving.platform.Application.init">
	<table id="_TableHeader" width="100%" border="0" cellpadding="0"
		cellspacing="0" class="bluebg"
		style="background:#3388bb url(Platform/Images/vistaBlue.jpg) repeat-x left bottom;">
		<tr>
			<td height="70" valign="bottom">
			<table height="70" border="0" cellpadding="0" cellspacing="0"
				style="position:relative;">
				<tr>
					<td style="padding:0 0 0 10px"><img
						src="Platform/Images/logo.gif"></td>
				</tr>
				<tr>
					<td valign="bottom" nowrap="nowrap">
					<div
						style=" float:left; background:url(Platform/Images/selectsitebg.gif) no-repeat right top; color:#666666; padding:6px 25px 0 10px;"><span
						id="selectsite" style="display:inline">当前站点： <z:select
						id="_SiteSelector" style="width:150px;" listWidth="250"
						onChange="Application.onParentSiteChange();">
					 ${Sites} 
               </z:select> </span>&nbsp;<img src="Platform/Images/selectsite_hide.gif"
						width="13" height="13" align="absmiddle" style="cursor:pointer;"
						title="隐藏"
						onClick="a=$('selectsite').style.display=='none'?false:true;$('selectsite').style.display=a?'none':'inline';this.src=a?'Platform/Images/selectsite_show.gif':'Platform/Images/selectsite_hide.gif';this.title=a?'展开':'隐藏'"></div>
					</td>
				</tr>
			</table>
			</td>
			<td valign="bottom">
			<div style="text-align:right; margin:0 20px 10px 0;">当前用户：<b><%=User.getUserName()%></b>
			&nbsp;[&nbsp;<a href="javascript:void(0);" onClick="getMessage()">短消息(<span
				id="MsgCount">0</span>)</a> | <a href="javascript:void(0);"
				onClick="Application.logout();">退出登录</a> | <a
				href="javascript:Application.changePassword(0);">修改密码</a> ]</div>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center">
					<ul id="_Navigation" class="navigation">
						${Menu}
					</ul>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding:5px 3px 3px 3px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2" height="2"
						style="background:url(Platform/Images/jiao.gif) no-repeat right bottom;"></td>
					<td width="100%" id="_HMenutable" class="tabpageBar"></td>
				</tr>
				<tr valign="top">
					<td align="right" id="_VMenutable" class="verticalTabpageBar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" valign="bottom">
							<div id="_ChildMenu"></div>
							</td>
						</tr>
					</table>
					</td>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="maintable"
						style="border-bottom:#999999 1px solid; border-right:#999999 1px solid;">
						<tr>
							<td><iframe id='_MainArea' frameborder="0" width="100%"
								height="500" src='about:blank' scrolling="auto"></iframe></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var privDC = new DataCollection();
		privDC.parseXML(toXMLDOM(htmlDecode("${Privileges}")));
		parserPriv(privDC);
	</script>
</z:init>
</body>
</html>
