<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form id="F1">
<table width="100%" height="100%" border="0">
  <tr>
    <td valign="middle"><table width="390" align="center" cellpadding="2" cellspacing="0">

      <tr>
        <td align="right" >数据库别名：</td>
        <td><input name="Name" type="text" id="Name" size=20 verify="NotNull"/>
          <input name="ID" type="hidden" id="ID"></td>
      </tr>
      <tr>
        <td align="right" >备注：</td>
        <td><input name="Memo" type="text" id="Memo" size=40/></td>
      </tr>
      <tr>
        <td align="right" >服务器类型：</td>
        <td>
		<z:select id="ServerType" onChange="onServerTypeChange();"> 
		<select>
			<option value="ORACLE">Oracle</option> 
			<option value="DB2">DB2</option> 
			<option value="MSSQL2000">SQL Server 2000</option> 
			<option value="MSSQL">SQL Server 2005</option> 
			<option value="MYSQL" selected="true">Mysql</option>
		</select>
		</z:select></td>
      </tr>
      <tr>
        <td width="28%" align="right">服务器地址：</td>
        <td width="72%"><input name="Address" type="text" value="" id="Address" size=20  verify="NotNull" condition="$NV('Type')=='Link'"/></td>
      </tr>
      <tr>
        <td align="right">服务器端口：</td>
        <td><input name="Port" type="text" value="" id="Port" size=20 verify="NotNull&&Int" condition="$NV('Type')=='Link'"/></td>
      </tr>
      <tr>
        <td align="right">用户名：</td>
        <td><input name="UserName" type="text" value="" id="UserName" size=20 verify="NotNull" condition="$NV('Type')=='Link'"/></td>
      </tr>
      <tr>
        <td align="right">密码：</td>
        <td><input name="Password" type="password" value="" id="Password" size=20 verify="NotNull" condition="$NV('Type')=='Link'"/></td>
      </tr>
      <tr>
        <td align="right">数据库名称：</td>
        <td><input name="DBName" type="text" value="" id="DBName" size=20 verify="NotNull" condition="$NV('Type')=='Link'"/></td>
      </tr>
      <tr>
        <td align="right">测试表名：</td>
        <td><input name="TestTable" type="text" value="" id="TestTable" size=20 verify="NotNull" condition="$NV('Type')=='Link'"/></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
