<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.utility.ServletUtil"%>
<%@page import="com.zving.member.Member"%>
<%@page import="com.zving.framework.User"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.zving.member.Login"%>
<%Login.checkAndLogin(request);%>

<z:init method="com.zving.member.Login.initSiteLinks">
  <div style="background-color:#F9FCFF">
    <div style="width:950px; margin:auto; background-color:#EEF5FC">
      <div style="text-align:right; line-height:24px;"><span style="display:${display}"><a href="../Member/Login.jsp?SiteID=${SiteID}">会员登陆</a> | <a href="../Member/Register.jsp?SiteID=${SiteID}">注册</a> | </span><span style=" margin-right:12px;"><a href="../BBS/Index.jsp?SiteID=${SiteID}">论坛首页</a>| <a href="../Member/MemberInfo.jsp?SiteID=${SiteID}">会员中心</a> | <a href="#">站点地图</a></span></div>
      <h1 style="height:50px; line-height:50px;font-size:48px; color:#000; font-family:'Arial Black', Gadget, sans-serif; padding-left:10px;"><img src="../Images/logo.gif" width="330" height="30"></h1>
    </div>
  </div>
  <div style="background-color:#f7f8f9">
    <div style="width:950px; margin:auto;color:#eee; line-height:34px; background:#0066aa url(../Images/navBg.jpg) no-repeat 0 0;" class="navigation"><span style=" margin-left:12px;">${SiteLinks}</span></div>
  </div>
</z:init>
