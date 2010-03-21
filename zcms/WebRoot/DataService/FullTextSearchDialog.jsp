<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function onTypeChange(){
	loadTreeData();
}
function onCheck(ele){
	ele = $(ele);
	var checked = ele.checked;
	if(ele.value=="-1"){
	
	}
	var p = ele.getParent("P");
	var level = p.$A("level");
	var arr = $("tree1").$T("P");
	var flag = true;
	for(var i=0;i<arr.length;i++){
		var c = arr[i];
		var cid = c.$A("cid");
		if(cid){
			if(cid!="-1"&&ele.value=="-1"){
				if(checked){
					$("Catalog_"+cid).disable();
				}else{
					$("Catalog_"+cid).enable();
				}
			}else{
				if(c!=p&&flag){
					continue;
				}
				if(c==p){
					flag = false;
					continue;
				}
				if(c.$A("level")>level){
					$("Catalog_"+cid).checked = checked;
				}else{
					break;
				}
			}
		}
	}
}

function loadTreeData(){
	Tree.setParam("tree1","Type",$NV("Type"));
	Tree.loadData("tree1",function(){
		if(window.RelaStr){
			var rela = ","+window.RelaStr+",";
			var arr = $N("Catalog");;
			for(var i=0;i<arr.length;i++){
				if(rela.indexOf(","+arr[i].value+",")>=0){
					arr[i].checked = true;
				}
			}
			if(rela.indexOf(",-1,")>=0){
				onCheck(arr[0]);
			}
		}
	});
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<z:init method="com.zving.cms.dataservice.FullText.init">
<form id="F1">
  <table width="100%" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td height="15" colspan="3" align="right" >&nbsp;</td>
      </tr>
    <tr>
      <td width="12%" height="30" align="right" >类型：</td>
      <td colspan="2" align="left" >	  	
	  	<input type="radio" name="Type" id="TypeArticle" value="Article" checked onClick="onTypeChange()">
        <label for="TypeArticle">文章检索</label>
        &nbsp;&nbsp;
        <input type="radio" name="Type" id="TypeImage" value="Image" onClick="onTypeChange()">
        <label for="TypeImage">图片检索</label>
        &nbsp;&nbsp;
        <input type="radio" name="Type" id="TypeVideo" value="Video" onClick="onTypeChange()">
        <label for="TypeVideo">视频检索</label>
        &nbsp;&nbsp;
        <input type="radio" name="Type" id="TypeRadio" value="Radio" onClick="onTypeChange()">
        <label for="TypeRadio">音频检索</label>
		<input name="ID" type="hidden" id="ID">		
		</td>
      </tr>
	</table>
	<table width="100%" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td height="30" colspan="2" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="28%" height="30" align="right" >代码：</td>
          <td width="72%" height="30"><input name="Code" type="text" class="inputText" id="Code" style="width:130px" verify="NotNull"/></td>
        </tr>
        <tr>
          <td height="30" align="right" >名称：</td>
          <td height="30"><input name="Name" type="text" class="inputText" id="Name" style="width:130px" verify="NotNull"/>          </td>
        </tr>
        <tr>
          <td height="30" align="right" >备注：</td>
          <td height="30"><input name="Memo" type="text" class="inputText" id="Memo" style="width:130px" /></td>
        </tr>
      </table></td>
      <td width="60%">
	  <z:tree id="tree1" style="height:320px;width:300px" method="com.zving.cms.dataservice.FullText.treeDataBind" level="2" lazy="true" expand="true">
		  	<p cid='${ID}' level="${Level}"><input type="checkbox" name="Catalog" id='Catalog_${ID}' value='${ID}' onClick="onCheck(this);"><label for="Catalog_${ID}"><span id="span_${ID}">${Name}</span></label></p>
	  </z:tree>	  
	  </td>
    </tr>
</table>
</form>
</z:init>
</body>
</html>
