<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.Config"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言列表</title>
<link href="../images/zhuantilanmu.css" type="text/css" rel="stylesheet" />
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<script>
function download(attid){
	if(!attid||attid==""){
		return;
	}else{
		window.location="MessageAttachDownload.jsp?id="+attid+"&SiteID=<%=request.getParameter("SiteID")%>";
	}
}
</script>
</head>

<body>
<table width="960" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div id="header">
        	·<a href="../">返回首页</a>·<a href="../jsp/Message.jsp?SiteID=<%=request.getParameter("SiteID")%>">在线留言</a>·<a href="../form.shtml">自定义表单</a>·<a href="#">帮助</a> <img width="14" height="12" src="../images/shouye.gif"/> <a href="#">设为首页</a>        </div> 
    </td>
  </tr>
  <tr>
    <td>
    	<div id="navi">
        	<a href="#"><img src="../images/logo.jpg" height="60" width="168" /></a>
            <p>&nbsp;</p><br />
        </div>
    </td>
  </tr>
  <tr>
    <td>
    <div id="twoContainer">
        	<div class="twoContentHeader">
            	<span class="twoTitle">留言列表</span> <span class="twoPosition">操作：<a href="Message.jsp?SiteID=<%=request.getParameter("SiteID")%>">我要留言</a></span>
            </div>

            <div class="twoContent" style="padding:20px 40px;">
            <div class="fabiaoliuyan"></div>
              <z:datalist id="dg1" method="com.zving.cms.dataservice.MessageList.dg1DataList" size="5" page="true">
            <table width="100%" border="1" bordercolor="#eeeeee" cellspacing="0" cellpadding="5" style="border-collapse: collapse; margin-bottom:10px;" class="liuyan">
                <tr valign="top";>
                  <td width="28%" bgcolor="#f9f9f9" class="title" style="line-height:1.6;">姓名：<b>${AddUser}</b><br />电话：${Tel}<br />地址：<span title="${Address}" style="width:60px;display:inline;">${Address}</span><br />E-mail：${Email}</td>
                  <td width="72%">
                                      <div>[留言]　发表于：${AddTime}</div>
<h3 style="margin:0 0 6px;">${Title}</h3>${Content}${Attach}
                    <div style="display:${Display}; color:#903">[回复]　${ReplyContent}</div>
                  </td>
                </tr>
            </table>
              </z:datalist>
            <table width="100%">
              <tr valign="middle">
                <td colspan="2" style="min-height:0px; height:20px;" align="center"><z:pagebar target="dg1" /></td>
              </tr>
            </table>
        </div>

    </div>
    </td>
  </tr>
  <tr>
    <td>
    	<div id="footernavi">
        	<a href="#">泽元软件简介</a>
            <a href="#">广告服务</a>
            <a href="#">联系我们</a>
            <a href="#">招聘信息</a>
            <a href="#">网站律师</a>
            <a href="#">ZVING English</a>
            <a href="#">会员注册</a>
            <a href="#">产品答疑</a>
        </div>
    </td>
  </tr>
  <tr>
    <td>
    	<div id="banquan">
        	泽元软件有限公司版权所   地址 ：北京市海淀区北京科技大学科技园D座311室   电话 ：010－52752668<br />
Copyright   2008 zving.com. All Rights Reserved
        </div>
    </td>
  </tr>
</table>
</body>
</html>
