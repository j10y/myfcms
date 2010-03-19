<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="../../tagLib.jsp"%> 
<%@ include file="../calendar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><fmt:message key="mainFrame.title"/></title>
<link rel="stylesheet" href="styles/common/common.css" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(images/login/logn_r4_c6.png);
}
.STYLE4 {font-family: Arial, Helvetica, sans-serif}
-->
</style>
<SCRIPT LANGUAGE=JAVASCRIPT>
<!-- 
if (top.location != self.location)top.location=self.location;
// -->
</SCRIPT>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="45">&nbsp;</td>
  </tr>
</table>
<table width="762" height="402" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF"><table width="760" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="images/login/welcome_r1_c1.png" width="760" height="53" /></td>
      </tr>
      <tr>
        <td><table width="760" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="431"><img src="images/login/welcome_r2_c1.png" width="431" height="71" /></td>
              <td width="329" align="center" background="images/login/welcome_r2_c3.png"><table width="80%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="right" class="login_font"><fmt:message key="mainFrame.copyright"/></td>
                </tr>
              </table></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="760" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="100"><img src="images/login/welcome_r3_c1.png" width="100" height="244" /></td>
              <td width="560" align="left" valign="top" background="images/login/welcome_r3_c2.png">
              <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="70" align="center" colspan="4" valign="bottom">
                  <spring:bind path="formInfo.*"> 
		        <c:if test="${status.error}">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" class="billMsgTable">
				    <tr class="billMsgRow">
					    <td class="billMsgCellLogin">
						<c:forEach items="${status.errorMessages}" var="errorMessage"> 
						<c:out value="${errorMessage}"/><br>
		                </c:forEach>
						</td>
					</tr>
				</table>
				</c:if> 
		        </spring:bind>
				  </td>
                </tr>
				<form action="<c:url value="/login.do"/>" method="post" id="editForm">				
                <tr>
                  <td width="100" align="center" class="login_font">&nbsp;</td>
                  <td width="60" height="30" align="center" class="login_font"><fmt:message key="login.label.userCode"/></td>
                  <td align="left"><input name="code" type="text" size="12" value="<c:out value="${formInfo.code}"/>" tabindex="1"/></td>
                  <td rowspan="2" align="center"><input type="image" name="imageField" src="images/login/anb_r2_c2.png" tabindex="3"/></td>
                  </tr>
                <tr>
                  <td align="center" class="login_font">&nbsp;</td>
                  <td height="30" align="center" class="login_font"><fmt:message key="login.label.userPassword"/></td>
                  <td align="left"><input name="password" type="password" size="12" tabindex="2"/></td>
                  </tr>
                <tr>
                  <td align="center" class="login_font">&nbsp;</td>
                  <td height="50" align="center" class="login_font">&nbsp;</td>
                  <td width="100" align="left">&nbsp;</td>
                  <td width="100" height="50" align="left">&nbsp;</td>
                  </tr>
                  </form>
              </table></td>
              <td width="100"><img src="images/login/welcome_r3_c4.png" width="100" height="244" /></td>
            </tr>
            <tr>
              <td height="32" colspan="3" align="center" background="images/login/welcome_r4_c1.png"><span class="login_font">湖北全达信息科技有限公司 <span class="STYLE4">&copy; 版权所有</span></span></td>
              </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
  function submitForm() {
    var userCode = document.getElementById("code");
    if (userCode.value != "") {
      var expireDate = new Date();
      expireDate.setTime(expireDate.getTime() + 30*86400000);
      document.cookie = "userCode=" + escape(userCode.value) + "; path=/; expires=" + expireDate.toGMTString();
    }
    var editForm = document.getElementById("editForm");
    if (editForm != null)
      editForm.submit();
  }
  
  function doOnKeyDown() {
    if (event.keyCode == 13) {
      if (document.activeElement.id == "userName") {
        event.keyCode = 9;
      } else {
        submitForm();
      }
    }
  }
  
  if (document.cookie.indexOf("userCode=") != -1 && document.getElementById("code").value == "") {
    var userCode = document.getElementById("code");
    var cookieArray = document.cookie.split(";");
    for (var i = 0; i < cookieArray.length; i++) {
      if (cookieArray[i].indexOf("userCode") != -1) {
        userCode.value = unescape(cookieArray[i].substring(cookieArray[i].indexOf("=") + 1, 
          cookieArray[i].length));
      }
    }
  }
  
</script>