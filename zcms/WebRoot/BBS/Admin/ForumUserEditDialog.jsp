<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../Framework/Main.js"></script>
<script src="../../Framework/Spell.js"></script>
<script type="text/javascript">
Page.onLoad(function (){
	iSView();
});

function iSView() {
	var IDs = $V("IDs").split(",");
	for(var i=0; i<IDs.length; i++) {
		if($V("UserGroup")==IDs[i]) {
			$("View").style.display='';
			return;
		}
	}
	$("View").style.display='none';
}
</script>
</head>
<z:init method="com.zving.bbs.admin.ForumUserInfo.initEditDialog">
<body>
<form id="form1">
	<input type="hidden" id="IDs" name="IDs" value="${IDs}">
	<input type="hidden" id="UserName" name="UserName" value="${UserName}"/>
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<table width="100%" border="0" cellspacing="6" cellpadding="0" >
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" >
				<tr>
					<td align="right" width="28%">用户名:</td>
					<td align="left">${UserName}</td>
				</tr>
				<tr>
					<input type="hidden" value="${ID}" id="ID">
					<td align="right">用户组:</td>
					<td><z:select name="UserGroup" onChange="iSView()" id="UserGroup" style="width:100px;"><span value="0">普通用户组</span>${UserGroup}</z:select>
					</td>
				</tr>
				<tr id="View">
					<input type="hidden" value="${ID}" id="ID">
					<td align="right">所属管理组:</td>
					<td><z:select name="AdminGroup" id="AdminGroup"
						style="width:100px;"><span value="0">无</span>${AdminGroup}</z:select>
					</td>
				</tr>
				<tr>
					<td align="right">发帖数:</td>
					<td><input type="text"  id="ThemeCount"  value="${ThemeCount}" Verify="NotNull&&Number"></td>
				</tr>
				<tr >
					<td align="right">积分:</td>
					<td><input type="text"  id="ForumScore"  value="${ForumScore}" Verify="NotNull&&Number"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</z:init>
</html>