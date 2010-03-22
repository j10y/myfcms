<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%> 
<%@ taglib uri="controls" prefix="z"%>
<%@page import="com.zving.framework.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.platform.*"%>
<%@page import="com.zving.framework.license.*"%>
<%
if(Config.isDatabaseConfiged){
	return;
}
if(StringUtil.isNotEmpty(request.getParameter("SQL"))){
	com.zving.platform.pub.Install.generateSQL(request,response);
	return;
}
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>初始化ZCMS</title>
<link href="Include/Default.css" rel="stylesheet" type="text/css" />
<script src="Framework/Main.js"></script>
<script>
function onServerTypeChange(){
	var st = $V("ServerType");
	if(st=="ORACLE"){
		$S("Port","1521");
	}
	if(st=="DB2"){
		$S("Port","50000");
	}
	if(st.startWith("MSSQL")){
		$S("Port","1433");
	}
	if(st=="MYSQL"){
		$S("Port","3306");
	}
}

function execute(){
	if(Verify.hasError()){
		return;
	}
	Dialog.alert("请稍等......");
	var dc = Form.getData("F1");
	Server.sendRequest("com.zving.platform.pub.Install.execute",dc,function(response){
		Dialog.closeEx();
		if(response.Status==1){
			taskID = response.get("TaskID");
			var p = new Progress(taskID,"正在导入数据...",500,150);
			p.show(function(){
				window.location = Server.ContextPath+"Login.jsp";
			});			
			p.Dialog.OKButton.hide();
			p.Dialog.CancelButton.hide();
			p.Dialog.CancelButton.onclick = function(){}
		}else{
			Dialog.alert(response.Message);	
		}
	});
}

function onAutoCreateClick(){
	if(!$("AutoCreate").checked){
		$("trSQL").show();
	}else{
		$("trSQL").hide();
	}
}

function onJNDIPoolClick(){
	if($("isJNDIPool").checked){
		$("trJNDI").show();
		$("trAddress").hide();
		$("trPort").hide();
		$("trDBName").hide();
		$("trPassword").hide();
		$("trUserName").hide();
		
	}else{
		$("trJNDI").hide();
		$("trSQL").hide();
		$("trAddress").show();
		$("trPort").show();
		$("trDBName").show();
		$("trPassword").show();
		$("trUserName").show();
	}
}

function onImportDataClick(){
	if($NV("ImportData")=="0"){
		$("AutoCreate").checked = false;
		$("AutoCreate").disable();
	}else{
		$("AutoCreate").checked = true;
		$("AutoCreate").enable();
	}
}

Page.onLoad(function (){
	if(window.top.location != window.self.location){
		window.top.location = window.self.location;
	}
	onJNDIPoolClick();
	onAutoCreateClick();
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
body{ background-color:#F6FAFF;_position:relative;}
h4{ color:#226699}
a.zInputBtn {_position:relative;}
</style>
</head>
<body>
<form id="F1">
<table width="100%" height="100%" border="0">
  <tr>
    <td valign="middle"><div style="margin:0 auto; background-color:#fff; padding:1px; border:1px solid #d2dbe5; width:400px;"><table width="100%" align="center" cellpadding="2" cellspacing="0">
      <tr>
        <td colspan="2" height="30" align="center" style="background-color:#EAF0F6" ><h4>为ZCMS初始化数据库</h4></td>
        </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right" >服务器类型：</td>
        <td><div style="_position: relative;">
		<z:select id="ServerType" onChange="onServerTypeChange();"> 
			<span value="ORACLE">Oracle</span> 
			<span value="DB2">DB2</span> 
			<span value="MSSQL">SQL Server 2005</span> 
			<span value="MYSQL">Mysql</span>
		</z:select></div></td>
      </tr>
      <tr>
        <td height="30" align="right">&nbsp;</td>
           <td><label><input name="ImportData" type="radio" id="ImportData1" onClick="onImportDataClick()" value="1" checked>
初始化数据库</label><label><input name="ImportData" type="radio" id="ImportData2" onClick="onImportDataClick()" value="0">
仅配置连接</label></td>
      </tr>
      <tr>
        <td height="30" align="right">选项：</td>
        <td><label>
        <input name="AutoCreate" type="checkbox" id="AutoCreate" onClick="onAutoCreateClick()" value="1" checked>
自动创建数据库表结构</label></td>
      </tr>
      <tr>
        <td height="30" align="right">&nbsp;</td>
        <td><label>
          <input name="isJNDIPool" type="checkbox" id="isJNDIPool" onClick="onJNDIPoolClick()" value="1">
使用JNDI连接池</label></td>
      </tr>
      <tr style="display:none" id="trJNDI">
        <td height="30" align="right">JNDI名称：</td>
        <td><input name="JNDIName" type="text" id="JNDIName" value="jdbc/zcms" size=20  /></td>
      </tr>
      <tr style="display:none" id="trAddress">
        <td width="28%" height="30" align="right">服务器地址：</td>
        <td width="72%"><input name="Address" type="text" id="Address" value="" size=20  verify="NotNull" condition="!$('isJNDIPool').checked"/></td>
      </tr>
      <tr style="display:none" id="trPort">
        <td height="30" align="right">服务器端口：</td>
        <td><input name="Port" type="text" id="Port" value="" size=20 verify="NotNull&&Int" condition="!$('isJNDIPool').checked"/></td>
      </tr>
      <tr style="display:none" id="trUserName">
        <td height="30" align="right">用户名：</td>
        <td><input name="UserName" type="text" id="UserName" value="" size=20 verify="NotNull" condition="!$('isJNDIPool').checked"/></td>
      </tr>
      <tr style="display:none" id="trPassword">
        <td height="30" align="right">密码：</td>
        <td><input name="Password" type="password" id="Password" value="" size=20 verify="NotNull" condition="!$('isJNDIPool').checked"/></td>
      </tr>
      <tr style="display:none" id="trDBName">
        <td height="30" align="right">数据库名称：</td>
        <td><input name="DBName" type="text" id="DBName" value="" size=20 verify="NotNull" condition="!$('isJNDIPool').checked"/></td>
      </tr>
      <tr style="display:none" id="trSQL">
        <td height="50" align="right">SQL语句下载：</td>
        <td><p>请下载对应数据库的SQL文件并手动执行。</p>
          <p><a href="Install.jsp?SQL=1&Type=DB2"><strong>DB2</strong></a>，
		  <a href="Install.jsp?SQL=1&Type=ORACLE"><strong>Oracle</strong></a>，
		  <a href="Install.jsp?SQL=1&Type=MSSQL"><strong>SQLServer</strong></a>，
		  <a href="Install.jsp?SQL=1&Type=MYSQL"><strong>Mysql</strong></a></p></td>
      </tr>
      <tr>
        <td height="30" align="right">&nbsp;</td>
        <td><div style="_position:relative;"><input type="button" name="Submit" value="下一步" onClick="execute()"></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table>
    </div></td>
  </tr>
</table>
</form>
</body>
</html>
