<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="controls" prefix="z" %>
<%@page import="com.zving.framework.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员中心</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css">
<link href="../Include/front-end.css" rel="stylesheet" type="text/css" />
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript">

function doLogout(){
	Dialog.confirm("您确认退出吗？",function(){
		var dc = new DataCollection();
		Server.sendRequest("com.zving.member.Login.Logout",dc,function(response){
			window.location = "Login.jsp?SiteID="+response.Message;
		});													
	});
}

function save(){
	var dc = Form.getData("InfoForm");
	Server.sendRequest("com.zving.member.MemberInfo.doDetailSave",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.location.reload();
			}								   
		});
	});
}

function UpdatePic(){
	if($V("PicFile")!=""){
		$("InfoForm").submit();
	}
}

function afterUpload(path){
	$S("Pic",path);
	$("companypic").src = path;
}
</script>
</head>
<body>
<%@ include file="../Include/head.jsp" %>
<%@ include file="Verify.jsp"%>
<div class="wrap">
<div id="menu" class="forumbox"> <span class="fl"> <b> <%=User.getValue("Name")%> </b> <a href="#;" onClick="doLogout();">退出</a> </span> <span class="fr"> <a href="#;">标签</a> | <a href="#;">帮助</a> </span> </div>
  <div id="nav"><a href="#">首页</a> &raquo; 编辑个人资料</div>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td width="200"><div class="side">
          <h4>会员中心</h4>
          <div class="sideinner">
            <hr class="shadowline"/>
            <ul class="sidemenu">
              <li class="current"><a href="#;">编辑个人资料</a></li>
              <li><a href="WriteArticle.jsp">网站文章投稿</a></li>
              <li><a href="MemberArticles.jsp">我投稿的文章</a></li>
              <li><a href="Password.jsp">修改密码</a></li>
            </ul>
          </div>
        </div></td>
      <td width="20">&nbsp;</td>
      <td>
      <z:init method="com.zving.member.MemberInfo.initDetail">
      <form id="InfoForm" target="formTarget" method="POST" enctype="multipart/form-data" action="Upload.jsp?Path=/Member/UserFiles/${UserName}/">
          <div class="forumbox"> <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span>
            <h4>编辑个人资料</h4>
            <ul class="tabs ">
              <li><a href="MemberInfo.jsp">基本资料</a></li>
              <li class="current"><a href="#;">详细资料</a></li>
              <li><a href="Logo.jsp">上传头像</a></li>
            </ul>
            <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">${MemberType}详细信息
            <input type="hidden" id="UserName" name="UserName" value="${UserName}"/>
            <input type="hidden" id="Pic" name="Pic" value="${Pic}"/></h5>
            <div style="display:none"><iframe name="formTarget" src="javascript:void(0)"></iframe></div>
             <table width="99%" border="0" cellspacing="0" cellpadding="0" class="forumTable">
              <%if(User.getValue("Type").equals("Person")){%>
              <tr>
                <td width="15%" align="right">昵称：</td>
                <td width="30%"><input id="NickName" name="NickName" type="text" class="textInput" size="30" value="${NickName}"/></td>
                <td width="55%"><div class="paraphrastic">3个以上字母或2个以上汉字。</div></td>
              </tr>
              <tr>
                <td align="right">生日：</td>
                <td><input id="Birthday" name="Birthday" type="text" class="textInput" size="30" value="${Birthday}"/></td>
                <td><div class="paraphrastic">您的出生日期</div></td>
              </tr>
              <tr>
                <td align="right">QQ：</td>
                <td><input id="QQ" name="QQ" type="text" class="textInput" size="30" value="${QQ}"/></td>
                <td><div class="paraphrastic">您的QQ号码</div></td>
              </tr>
              <tr>
                <td align="right">MSN：</td>
                <td><input id="MSN" name="MSN" type="text" class="textInput" size="30" value="${MSN}"/></td>
                <td><div class="paraphrastic">您的MSN账号</div></td>
              </tr>
              <%}else{ %>
              <tr>
              	<td width="15%" align="right">公司名称：</td>
                <td width="30%"><input id="CompanyName" name="CompanyName" type="text" class="textInput" size="30" value="${CompanyName}"/></td>
                <td width="55%"><input id="PicFile" name="PicFile" type="File" class="textInput" size="25" value=""/>
                <input type="button" onClick="UpdatePic();" value="更新形象照片"/></td>
              </tr>
              <tr>
              	<td width="15%" align="right">公司规模：</td>
                <td width="30%"><input id="Scale" name="Scale" type="text" class="textInput" size="30" value="${Scale}"/></td>
                <td width="55%" rowspan="5" valign="middle">企业形象照片:<br/>
                  <img id="companypic" width="240" height="180" src="${PicPath}" style="border:#DFDFDF 1px solid"/>&nbsp;建议240*180</td>
              </tr>
              <tr>
              	<td width="15%" align="right">主营业务：</td>
                <td width="30%"><input id="BusinessType" name="BusinessType" type="text" class="textInput" size="30" value="${BusinessType}"/></td>
                </tr>
              <tr>
              	<td width="15%" align="right">公司产品：</td>
                <td width="30%"><input id="Products" name="Products" type="text" class="textInput" size="30" value="${Products}"/></td>
                </tr>
              <tr>
              	<td width="15%" align="right">公司网站：</td>
                <td width="30%"><input id="CompanySite" name="CompanySite" type="text" class="textInput" size="30" value="${CompanySite}"/></td>
                </tr>
              <tr>
              	<td width="15%" align="right">传真：</td>
                <td width="30%"><input id="Fax" name="Fax" type="text" class="textInput" size="30" value="${Fax}"/></td>
                </tr>
              <tr>
              	<td width="15%" align="right">联系人：</td>
                <td width="30%"><input id="LinkMan" name="LinkMan" type="text" class="textInput" size="30" value="${LinkMan}"/></td>
                 <td width="55%" rowspan="4">公司简介：<br/><textarea id="Intro" name="Intro" cols="50" class="textInput">${Intro}</textarea></td>
                </tr>
              <%} %>
              <tr>
                <td align="right">E-mail：</td>
                <td><input id="Email" name="Email" type="text" class="textInput" size="30" value="${Email}"/></td>
                </tr>
              <tr>
                <td align="right">固定电话：</td>
                <td><input id="Tel" name="Tel" type="text" class="textInput" size="30" value="${Tel}"/></td>
                </tr>
              <tr>
                <td align="right">手机：</td>
                <td><input id="Mobile" name="Mobile" type="text" class="textInput" size="30" value="${Mobile}"/></td>
                </tr>
              <tr>
                <td align="right">详细联系地址：</td>
                <td><input id="Address" name="Address" type="text" class="textInput" size="30" value="${Address}"/></td>
                <td><div class="paraphrastic">您的详细联系地址</div></td>
              </tr>
              <tr>
                <td align="right">邮政编码：</td>
                <td><input id="ZipCode" name="ZipCode" type="text" class="textInput" size="30" value="${ZipCode}"/></td>
                <td><div class="paraphrastic">您的邮政编码</div></td>
              </tr>
            </table>
            <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;"></h5>
            <div style=" padding-left:140px;">
              <button type="button" onClick="save()" name="tiajiao" value="true" class="submit">提交</button>
              &nbsp;
              <button type="reset" name="reset" value="false" class="button">重 置</button>
            </div>
          </div>
        </form></z:init></td>
    </tr>
  </table>
</div>
<%@ include file="../Include/foot.jsp" %>
</body>
</html>