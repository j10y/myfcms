<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.Config"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言列表</title>
<link href="../images/reset.css" rel="stylesheet" type="text/css" />
<link href="../images/common.css" rel="stylesheet" type="text/css" />
<link href="../images/list.css" rel="stylesheet" type="text/css" />
<script src="../images/common.js" type="text/javascript"></script>
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
<div id="container">
    
	<!-- 内容 -->
  <div id="list_content">
        <!-- 左 -->
      <div class="right">
          <div class="content">
            <div class="fabiaoliuyan"><a href="Message.jsp?SiteID=<%=request.getParameter("SiteID")%>"><span>发表留言</span></a></div>
            <table width="100%" border="1" bordercolor="#e5d9b8" cellspacing="0" cellpadding="0" class="liuyan">
              <z:datalist id="dg1" method="com.zving.cms.dataservice.MessageList.dg1DataList" size="5" page="true">
                <tr valign="top";>
                  <td width="28%" class="title">姓名：${AddUser}<br />电话：${Tel}<br />地址：<span title="${Address}" style="width:60px;display:inline;">${Address}</span><br />E-mail：${Email}</td>
                  <td width="72%"><h3>${Title}</h3>${Content}${Attach}
                    <div>[留言]　发表于：${AddTime}</div>
                    <div style="display:${Display}; color:#903">[回复]　${ReplyContent}</div>
                  </td>
                </tr>
              </z:datalist>
              <tr valign="middle">
                <td colspan="2" style="min-height:0px; height:20px;" align="center"><z:pagebar target="dg1" /></td>
              </tr>
            </table>
        </div>
</div>
        <!-- 右结束 -->
    </div>
    <!-- 内容结束 -->
</div>
</body>
</html>
