<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章浏览日志</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">

function exportExcel(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		var at = DataGrid.getParam("dg1","ArticleTitle");
		var rn = DataGrid.getParam("dg1","RealName");
		var sd = DataGrid.getParam("dg1","StartDate");
		var ed = DataGrid.getParam("dg1","EndDate");
		window.location="ArticleVisitLogExport.jsp?at="+at+"&rn="+rn+"&sd="+sd+"&ed="+ed;
	}else{
		var ids = arr.join(",");
		window.location="ArticleVisitLogExport.jsp?ids="+ids;
	}
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的记录！");
		return;
	}
	Dialog.confirm("确定要删除记录吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.project.cptdc.ArticleVisitLog.del",dc,function(response){
			if(response.Status==1){
				Dialog.alert(response.Message);
				DataGrid.loadData("dg1");
			}else{
				Dialog.alert(response.Message);
			}
		});
	})
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'searchlog'){
			doSearch();
		}
	}
}

function doSearch(){
	var ArticleTitle = $V("ArticleTitle");
	var RealName = $V("RealName");
	var StartDate = $V("StartDate");
	var EndDate = $V("EndDate");
	DataGrid.setParam("dg1","ArticleTitle",ArticleTitle);
	DataGrid.setParam("dg1","RealName",RealName);
	DataGrid.setParam("dg1","StartDate",StartDate);
	DataGrid.setParam("dg1","EndDate",EndDate);
	DataGrid.loadData("dg1");
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
    <tr valign="top">
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
            <tr>
                <td width="15%" valign="middle" class="blockTd"><img src="../Icons/icon003a1.gif" />文章浏览日志</td>
            </tr>
            <tr>
            	<td style="padding:0 8px 4px;">
                <z:tbutton onClick="exportExcel()"><img src="../Icons/icon024a1.gif" />导出Excel</z:tbutton>
                <z:tbutton onClick="del()"><img src="../Icons/icon024a3.gif" />删除</z:tbutton>
                <div style="float:right">
                标题：<input type="text" id="ArticleTitle" value="" style="width:100px;"/>
                姓名：<input type="text" id="RealName" value="" style="width:100px;"/>
                时间：<input type="text" id="StartDate" value="" ztype='date' style="width:70px;"/>至
                <input type="text" id="EndDate" value="" ztype='date' style="width:70px;"/>
                <input type="button" id="searchlog" value="检索" onClick="doSearch();"/>
                </div>
                </td>
            </tr>
            <tr>
                <td style="padding:0px 8px;" colspan="2">
                <z:datagrid id="dg1" method="com.zving.project.cptdc.ArticleVisitLog.dg1DataBind" size="15">
                <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
                    <tr ztype="head" class="dataTableHead">
                        <td width="3%" ztype="RowNo">&nbsp;</td>
                        <td width="3%" ztype="selector" field="id">&nbsp;</td>
                        <td width="8%"><b>文章ID</b></td>
                        <td width="33%"><b>文章标题</b></td>
                        <td width="20%"><b>访问用户</b></td>
                        <td width="14%"><b>访问IP</b></td>
                        <td width="19%"><b>访问时间</b></td>
                    </tr>
                    <tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>${ArticleID}</td>
                        <td>${ArticleTitle}</td>
                        <td>${RealName}</td>
                        <td>${IP}</td>
                        <td>${AddTime}</td>
                    </tr>
                    <tr ztype="pagebar">
                    	<td colspan="7">${PageBar}</td>
                    </tr>
                </table>
                </z:datagrid>
                </td>
            </tr>
        </table>
        </td>
    </tr>
</table>
</body>
</html>