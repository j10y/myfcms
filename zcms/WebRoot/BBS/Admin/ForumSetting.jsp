<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../../Framework/Main.js"></script>
<script type="text/javascript">
function saveSetting() {
	var dc = Form.getData($F("form1"));
	
	Server.sendRequest("com.zving.bbs.admin.ForumSetting.add",dc,function(response){
		Dialog.alert(response.Message);
		if(response.Status==1){
			$D.close();
		}
	});
}
function DBInit() {		
	Server.sendRequest("com.zving.bbs.admin.DBInit.DBDataInit",null,function(response){
		Dialog.alert(response.Message);
	});
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="dialogBody">
<form id="form1" >
<z:init method="com.zving.bbs.admin.ForumSetting.init">
<table width="500" align="center" cellpadding="0" cellspacing="0">
	<tr>
      	<td><input type="hidden" value="${ID}" id="ID"></td>
    </tr>
    <tr><td>
        	<fieldset><legend><label>站点信息</label></legend>
	<table width="100%" cellpadding="0" align="left" cellspacing="0">
	<tr>
      	<td height="10" align="center" ></td>
      	<td></td>
    </tr>
    <tr>
      	<td height="50" align="right">论坛名称：</td>
      	<td>
      		<input name="Name" id="Name" type="text" value="${Name}" class="input1" id="Name" verify="NotNull" size="40"/>
        </td>
    </tr>
    
    <tr>
      	<td width="20%" height="25" align="right">二级域名：</td>
      	<td>
      		<input name="Subdomains" id="Subdomains" type="text" value="${Subdomains}" class="input1" id="Name" verify="NotNull" size="40"/>
        </td>
    </tr>
    <tr>
    	<td height="25" align="right">描述：</td>
    	<td><textArea id="Des" name="Des" cols="60">${Des}</textArea></td>
    </tr>
    <tr>
      	<td height="25" align="right">临时关闭论坛：</td>
      	<td>${TempCloseFlag}</td>
    </tr>
    <tr>
    	<td align="justify"><input type="button" id="submit" name="submit" value="提交" onclick="saveSetting();"/></td>
    	<td align="justify"><input type="button" name="submit" value="论坛数据初始化" onclick="DBInit();"/></td>
    </tr> 
</table>
</fieldset>
</td>
</tr>
</table>
</z:init>
</form>
</body>
</html>