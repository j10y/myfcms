<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script src="../Framework/Spell.js"></script>
</head>
<body>
<z:init method="com.zving.bbs.Theme.initBrightDialog">
<form id="form1">
	<table width="100%" border="0" cellspacing="6" cellpadding="0" >
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" >
				<tr>
					<td align="left">选择高亮的颜色:(不选择则会取消高亮显示)</td>
				</tr>
				<tr>
					<td align="left">${Color}</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</z:init>
</body>
</html>