<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言页面</title>
<link href="../images/reset.css" rel="stylesheet" type="text/css" />
<link href="../images/common.css" rel="stylesheet" type="text/css" />
<link href="../images/list.css" rel="stylesheet" type="text/css" />
<script src="../images/common.js" type="text/javascript"></script>
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
	if(AddUser==""||Email==""||Title==""||Content==""||Tel==""){
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
<div id="container">    
	<!-- 内容 -->
    <div id="list_content">
        <!-- 左 -->
        <div class="left">
            
            <!-- 联系我们 -->
            <!-- 联系我们结束 -->
        </div>
        <!-- 左结束 -->
        
        <!-- 右 -->
        <div class="right">
            <div class="content">
            <div style="display:none">
            <iframe name="formTarget" src="javascript:void(0)"></iframe>
            </div>
            <form id="messageform" name="messageform" target="formTarget" method="POST" enctype="multipart/form-data">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
            	    <td width="9%"><b>姓名：</b></td>
            	    <td width="42%"><input type="text" name="AddUser" id="AddUser" size="15" value="" tabindex="3"></td>
                    <td width="10%"><b>电话：</b></td>
                    <td width="39%"><input type="text" name="Tel" id="Tel" size="25" value="" tabindex="3"></td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                  <tr>
            	    <td><b>地址：</b></td>
            	    <td><input type="text" name="Address" id="Address" size="45" value="" tabindex="3"></td>
                    <td><b>E-mail：</b></td>
                    <td><input type="text" name="Email" id="Email" size="25" value="" tabindex="3"></td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
             </table>
           	  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="fbly">
            	  <tr>
            	    <td width="9%"><b>标题：</b></td>
            	    <td width="91%"><input type="text" name="Title" id="Title" size="45" value="" tabindex="3"></td>
          	      </tr>
                  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            	  <tr>
            	    <td><b>内容：</b></td>
            	    <td>
                    	<div style=" background-color:#f3f3f3; padding:10px 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;">
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
            	    <td><input type="button" name="tiajiao" value="发表留言" onclick="doSubmit();" class="fabiao_btn" /></td>
          	    </tr>
          	  </table>
              </form>
            </div>
        </div>
        <!-- 右结束 -->
    </div>
    <!-- 内容结束 -->
</div>
</body>
</html>
