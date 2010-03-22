<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言板</title>
<link href="../images/zhuantilanmu.css" type="text/css" rel="stylesheet" />
<script>

function afterUpload(){
	window.location = "MessageList.jsp?SiteID=<%=request.getParameter("SiteID")%>";	
}

function doSubmit(){
	var AddUser = document.getElementById("AddUser").value.replace(/(^\s*)|(\s*$)/g,"");
	var Email = document.getElementById("Email").value.replace(/(^\s*)|(\s*$)/g,"");
	var Title = document.getElementById("Title").value.replace(/(^\s*)|(\s*$)/g,"");
	var Content = document.getElementById("Content").value.replace(/(^\s*)|(\s*$)/g,"");
	var Tel = document.getElementById("Tel").value.replace(/(^\s*)|(\s*$)/g,"");
	var Address = document.getElementById("Address").value;
	if(AddUser==""||Email==""||Title==""||Content==""){
		alert("信息不完整，请补充");
		return;
	}else{
		var Str = "?AddUser="+AddUser+"&Email="+Email+"&Title="+Title+"&Content="+Content+"&Address="+Address+"&Tel="+Tel+"&SiteID=<%=request.getParameter("SiteID")%>";
		document.getElementById("messageform").action = "SaveMessage.jsp"+Str;
		document.getElementById("messageform").submit();
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
            	<span class="twoTitle">留言</span> <span class="twoPosition">操作：<a href="MessageList.jsp?SiteID=<%=request.getParameter("SiteID")%>">查看留言</a></span>
            </div>

            <div class="twoContent" style="padding:20px 50px">
            <div style="display:none">
            <iframe name="formTarget" src="javascript:void(0)"></iframe>
            </div>
            <form id="messageform" name="messageform" target="formTarget" method="POST" enctype="multipart/form-data">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
            	    <td width="9%">姓名：</td>
            	    <td width="42%"><input type="text" name="AddUser" id="AddUser" size="15" value="" tabindex="3">
            	    *</td>
                    <td width="10%">电话：</td>
                    <td width="39%"><input type="text" name="Tel" id="Tel" size="25" value="" tabindex="3"></td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                  <tr>
            	    <td>E-mail：</td>
            	    <td><input type="text" name="Email" id="Email" size="25" value="" tabindex="3" />
            	      *</td>
                    <td>地址：</td>
                    <td><input type="text" name="Address" id="Address" size="45" value="" tabindex="3" /></td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
             </table>
           	  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="fbly">
            	  <tr>
            	    <td width="9%">标题：</td>
            	    <td width="91%"><input type="text" name="Title" id="Title" size="45" value="" tabindex="3">
            	    *</td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            	  <tr>
            	    <td valign="top">内容：</td>
            	    <td>
                   	<div style=" background-color:#f3f3f3; padding:5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;">
                              <textarea name="Content" id="Content" rows="10" cols="60" style="width:99%; padding:0; margin:5px 0; height:250px" tabindex="100"></textarea>
                              <fieldset style="clear:both; border:1px solid #ddd; padding:10px; -moz-border-radius: 3px; -webkit-border-radius: 3px;">
                                <legend>上传附件</legend>
                                    <label for="fileupload">选择文件:</label>
                                	<input type="file" value="" size="45" maxlength="262144" id="File" name="File" />
                                  </fieldset>

                              </div>
                    </td>
          	    </tr>
            	  <tr>
            	    <td>&nbsp;</td>
            	    <td><input type="button" name="tiajiao" value="发表留言" onclick="doSubmit();" class="fabiao_btn" style="margin-top:6px;" /></td>
          	    </tr>
          	  </table>
              </form>
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
