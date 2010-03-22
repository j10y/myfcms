<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function onTreeClick(ele){
	var cid=  ele.getAttribute("cid");
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","fid",cid);
	DataGrid.loadData("dg1");
	$S("CatalogID",cid);
}


function search(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","Keyword",$V("Keyword"));
	DataGrid.loadData("dg1");
}

</script>
</head>
<body>
	<input type="hidden" id="CatalogID">
	<input type="hidden" id="ListType" value='${ListType}'>
  <table width="100%" border="0" cellspacing="4" cellpadding="0" >
    <tr valign="top"><td width="180">
<table width="180" border="0" cellspacing="0" cellpadding="6" class="blockTable">
        <tr>
          <td valign="middle" class="blockTd"><img src="../Icons/icon018a1.gif" />板块列表</td>
        </tr>
        <tr>
          <td style="padding:6px;"> 	
		  <z:tree id="tree1" style="height:410px" method="com.zving.cms.dataservice.OuterDocument.bbstreeDataBind">
		  	<p cid='${fid}' onClick="onTreeClick(this);">${Name}</p>
		  </z:tree>
		  </td>
        </tr>
      </table>	
	</td>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
        <tr>
          <td colspan="2" valign="middle" class="blockTd"><img src="../Icons/icon018a1.gif" />帖子列表</td>
        </tr>
        <tr>
          <td align="right" style="padding:8px 10px;"><input name="Keyword" type="text" id="Keyword">
            <input type="button" name="Submit" value="查询" onClick="search()"></td>
        </tr>
        <tr>
          <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
		  <z:datagrid id="dg1" method="com.zving.cms.dataservice.OuterDocument.bbsDataBind" size="12">
              <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
                <tr ztype="head" class="dataTableHead">
                  <td width="4%" height="30" ztype="RowNo" drag="true">
				  	<img src="../Framework/Images/icon_drag.gif" width="16" height="16"></td>
                  <td width="6%" ztype="selector" field="id">&nbsp;</td>
                  <td width="73%" sortField="title"><b>标题</b></td>
                  <td width="17%" sortField="title">发帖人</td>
                </tr>
                <tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC" >
                  <td >&nbsp;</td>
                  <td>&nbsp;</td>
                  <td title="作者：${author} 发表时间：${addtime}"><a href='${link}' target="_blank">${subject}</a></td>
                  <td title="作者：${author} 发表时间：${addtime}">${author}</td>
                </tr>
                <tr ztype="pagebar">
                  <td colspan="10" align="left">${PageBar}</td>
                </tr>
              </table>
          </z:datagrid></td>
        </tr>
      </table></td>
    </tr>
    <tr height="35" class="dialogButtonBg">
      <td></td>
    </tr>
  </table>
</body>
</html>
