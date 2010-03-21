<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
  <form id="FChangePassword">
  <table width="100%" height="100%" border="0">
  <tr>
    <td valign="middle" style="text-align:center"><table width="390" >
      <tr>
        <td width="140" align="right">
          旧密码：</td>
        <td width="250" align="left"><input name="OldPassword"  type="password" value="" class="input1" id="OldPassword"  verify="NotNull" /></td>
      </tr>
      <tr >
        <td align="right">
         新密码：</td>
        <td align="left"><input name="Password" type="password" class="input1" id="Password" verify="NotNull" /></td>
      </tr>
      <tr >
        <td align="right">
          重复新密码：</td>
        <td align="left"><input name="ConfirmPassword" type="password" class="input1" id="ConfirmPassword" verify="NotNull&&两次输入的密码不一致|Script=$V('ConfirmPassword')==$V('Password')" /></td>
      </tr>

    </table></td>
  </tr>
</table>
  </form>
</body>
</html>