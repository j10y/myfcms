<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.framework.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>招聘</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css">
<link href="../Include/front-end.css" rel="stylesheet" type="text/css" />
<script src="<%=Config.getContextPath()%>Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript">

function save(){
    if(Verify.hasError()){
		return;		     
    }
    if(!$V("Picture")){
    	Dialog.alert("请上传图片后再保存！",function(){
    		var url = window.location+'';
			window.location = url.substring(0,url.lastIndexOf('#'))+'#'+'PicturePath';
    	});
    	return;
    }
	var dc = Form.getData($F("myform"));
	Server.sendRequest("com.zving.cms.dataservice.Apply.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				window.location = "http://www.zving.com";
			}
		});
	});
}

function doUpload(){
	if(!$V("PictureFile")){
		Dialog.alert("请先浏览图片");
		return;
	}
	$F("myform").method="POST";
	$F("myform").action="./UploadSave.jsp";
	$F("myform").submit();
}

function afterSave(returnStr,path,picturePath){
	if(returnStr){
		$("PicturePath").src = picturePath;
		$S("Picture",path);
	}else{
		alert("图片上传失败，请重新上传！");
	}
}
</script>

</head>
<body>
<%@ include file="../Include/head.jsp" %>
<z:init method="com.zving.cms.dataservice.Apply.init">
<div style="display:none"><iframe name="formTarget"  src="javascript:void(0)"></iframe></div>
<div class="wrap">
 
  <form id="myform" target="formTarget" enctype="multipart/form-data">
	<div class="forumbox">
	  <span class="fr">带 <span style=" font-family:'宋体'; color:#F60">*</span> 标示为必填项</span><h4>招聘</h4>
           	  <h5 style="border-bottom:1px dotted #ccc; color:#333; padding:3px; margin:5px;">基本信息</h5>
      <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="forumTable">
  		<tr>
  		<td width="100" align="right">应聘岗位：</td>
			<td width="330"><input
				name="WillPosition" type="text" class="text" id="WillPosition"
				 value="${Position}" verify="NotNull" /></td>
			
		</tr>
  <tr>
			<td width="100" align="right">姓名：</td>
			<td width="330"><input name="Name"
				type="text" class="text" id="Name" verify="NotNull" /></td>
			
			
			<td width="300" colspan="3" rowspan="10" align="center" valign="top" class="edit_content_text"
				style="text-align: center;vertical-align: middle;"><div style="background-color:#f9f9f9; border:1px solid #ddd; padding:20px;"><img
				src="../Images/nophoto.jpg" width="150" height="150" id="PicturePath"
				style="border-width:0px;border: 1px solid #999; margin-top: 2px; margin-bottom: 3px;" /><br />
			<span class="STYLE8" style="color: #FF3300">请上传本人照片</span><br>
			<input name="PictureFile" type="file" id="PictureFile" tabIndex="-1"/><input
				type="button" value="上传" onClick="doUpload();" tabIndex="-1"/> <br>
		  <input type="hidden" id="Picture" name="Picture" value=""/></div></td>
		</tr>

		<tr>
			<td width="100" align="right">性别：</td>
			<td width="330"> ${Gender}</td>
		</tr>
		<tr>
			<td width="100" align="right">出生日期：</td>
			<td width="330"><input
				name="BirthDate" type="text" class="text" id="BirthDate"
				style="width:80px;" value="" ztype="Date"
				verify="NotNull&&Date"/></td>
		</tr>
		<tr>
			<td width="100" align="right">身份证号：</td>
			<td width="330"><input
				name="CertNumber" type="text" class="text" id="CertNumber"
				style="width:130px;" value=""
				verify="身份证号码不正确!|NotNull" maxlength=18/></td>
		</tr>
		<tr>
			<td width="100" align="right">民族：</td>
			<td width="330"><z:select
				id="Ethnicity" name="Ethnicity" style="width:80px;" className="text"
				verify="NotNull"> ${Ethnicity} </z:select></td>
		</tr>
		<tr>
			<td width="100" align="right">籍贯：</td>
			<td width="330"><z:select 
				id="NativePlace" name="NativePlace" className="text" 
				style="width:120px;" verify="NotNull">${NativePlace}</z:select></td>
		</tr>
		<tr>
			<td width="100" align="right">政治面貌：</td>
			<td width="330"><z:select
				id="Political" name="Political" style="width:180px;" className="text"
				verify="NotNull"> ${Political} </z:select></td>
		</tr>
		<tr>
			<td width="100" align="right">最高学历：</td>
			<td width="330"><z:select
				id="EduLevel" name="EduLevel" style="width:180px;" className="text" verify="NotNull">${EduLevel}</z:select>			</td>
		</tr>
		<tr>
			<td width="100" align="right">手机：</td>
			<td width="330"><input name="Mobile"
				type="text" class="text" id="Mobile" style="width:80px;" value=""
				maxlength=11 verify="手机号码不正确,应为11位数字|Regex=1\d{10}&&NotNull" /></td>
		</tr>
		<tr>
			<td width="100" align="right">固定电话：</td>
			<td width="330"><input name="Phone"
				type="text" class="text" id="Phone" style="width:85px;" value="" maxlength=12
				/> <span style="color:#F00">形如010-62121234</span></td>
		</tr>
		<tr>
			<td width="100" align="right">联系地址：</td>
			<td width="330"><input name="Address"
				type="text" class="text" id="Address" style="width:300px;" value=""
				maxlength="25" verify="NotNull" /></td>
		</tr>
		<tr>
			<td width="100" align="right">电子邮箱：</td>
			<td width="330"><input name="Email" type="text"
				class="text" id="Email" style="width:200px;" value="" maxlength="150"
				verify="NotNull&&Email" /></td>
			<td width="100" align="left" colspan="3">邮编：<span class="edit_content_text">
			  <input name="Postcode" type="text"
				class="text" id="Postcode" style="width:45px;" value="" maxlength=6
				verify="邮编不正确,应为6位数字|Regex=\d{6}&&NotNull" />
			</span></td>
		</tr>
		<tr>
			<td width="100" align="right">毕业院校：</td>
			<td width="330"><input
				name="University" type="text" class="text" id="University"
				style="width:150px;" verify="NotNull"/></td>
			<td width="100" align="left" colspan="3">专业：<span class="edit_content_text">
			  <input
				name="Speacility" type="text" class="text" id="Speacility"
				value="" maxlength="25" verify="NotNull" />
			</span></td>
		</tr>
		<tr>
			<td width="100" align="right">户籍所在地：</td>
			<td class="edit_content_text" colspan="3"><input
				name="RegisteredPlace" type="text" class="text" id="RegisteredPlace"
				style="width:300px;" value=""/></td>
		</tr>
		<tr>
			<td width="100" align="right">资格认证：</td>
			<td class="edit_content_text" colspan="5"><input
				name="Authentification" type="text" class="text"
				id="Authentification" style="width:300px;" verify="Length<200"
				value="" /></td>
		</tr>
		<tr>
			<td width="100" align="right">个人简介：</td>
			<td colspan="5" class="edit_content_text"><textarea
				name="PersonIntro" rows="10" cols="20" id="PersonIntro"
				class="textarea" style="height:180px;width:500px;"
				verify="NotNull&&Length<500"></textarea> <span
				style="color: #ff6600">(1000字内)</span></td>
		</tr>
		<tr>
			<td width="100" align="right" style="height: 55px;">
			获奖情况：</td>
			<td class="edit_content_text" colspan="5" style="height: 55px"><span
				class="edit_content_text" style="height: 55px"> <textarea
				name="Honour" rows="4" cols="20" id="Honour" class="textarea"
				style="height:180px;width:500px;" verify="Length<500"></textarea>
			</span><span style="color: #ff6600">(1000字内)</span></td>
		</tr>
		<tr>
			<td width="100" align="right" style="height: 55px;">
			工作经历：</td>
			<td class="edit_content_text" colspan="5" style="height: 55px">
			<textarea name="SHLPracticeExperience" rows="4" cols="20"
				id="SHLPracticeExperience" class="textarea"
				style="height:180px;width:500px;" verify="Length<500"></textarea>
			<span style="color: #ff6600">(1500字内)</span></td>
		</tr>
		<tr>
			<td  width="100"  align="middle">&nbsp;</td>
			<td colspan="5" ><input name="buttonsubmit"
				type="button" value="保 存" onClick="save();"></td>
		</tr>
</table>

</form>
</div>
<%@ include file="../Include/foot.jsp" %>
</z:init>
</body>
</html>