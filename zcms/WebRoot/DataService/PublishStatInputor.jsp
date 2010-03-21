<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.dataservice.PublishStaff.initInputor">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-ID" content="text/html; charset=utf-8" />
<title>人员发布统计</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">

function statInputor(){
	DataGrid.setParam("dg11","Username",$V("Inputor"));
	DataGrid.setParam("dg11","startDate",$V("startDate"));
	DataGrid.setParam("dg11","endDate",$V("endDate"));
	DataGrid.setParam("dg11",Constant.PageIndex,0);
	DataGrid.loadData("dg11");
}

function statInputorAll(){
	DataGrid.setParam("dg11","Username",$V("Inputor"));
	DataGrid.setParam("dg11","startDate","");
	DataGrid.setParam("dg11","endDate","");
	DataGrid.setParam("dg11",Constant.PageIndex,0);
	DataGrid.loadData("dg11");
}

function exportInputor(){
	DataGrid.toExcel("dg11",0);
}

/*
function statInputorColumn(innerCode,catalogID){
	document.body.style.overflow="hidden";
	var username=$V("Inputor");
	var url="PublishStatInputorColumn.jsp?ColumnInputor="+username+"&CatalogInnerCode="+innerCode+"&CatalogID="+catalogID;
	document.getElementById("InputorColumnStat").src=url;
	document.getElementById("InputorColumnStat").style.display="block";
	document.getElementById("InputorBody").style.display="none";
}
*/
function statInputorColumn(innerCode,catalogID){
	var diag = new Dialog("Diag1");
	diag.Width = 850;
	diag.Height = 460;
	diag.Title = "${RealName}发布文章列表";
	var username=$V("Inputor");
	diag.URL = "DataService/PublishStatInputorColumn.jsp?ColumnInputor="+username+
				"&CatalogInnerCode="+innerCode+"&CatalogID="+catalogID;
	diag.OKEvent = function (){$D.close();};
	diag.show();
}


function backToStaff(){
	document.body.innerHTML="";
	document.body.style.display="none";
	parent.document.getElementById("StaffStat").style.display="block";
	parent.document.getElementById("InputorStat").style.display="none";
	parent.document.body.style.overflow="auto";
}
</script>
</head>
<body>
	<div id="InputorBody">
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				
				<tr>
					<td style="padding:4px 8px;" class="blockTd"><b>${RealName}发布文章统计</b></td>
				</tr>
				<tr>
					<td style="padding:4px 8px;">
						<span style="float: left;width:300px">
						统计时间：从
						<input value="${today}" type="text" id="startDate" 
							name="startDate" ztype="Date"  class="inputText" size="14" >
						到<input value="${today}" type="text" id="endDate" 
							name="endDate" ztype="Date"  class="inputText" size="14" >
						</span>
						
						<z:tbutton onClick="statInputor()"> <img src="../Icons/icon031a1.gif" />统计</z:tbutton>
						<z:tbutton onClick="statInputorAll()"> <img src="../Icons/icon031a15.gif" />全部统计</z:tbutton>
						<z:tbutton onClick="exportInputor()"> <img src="../Icons/icon031a7.gif" />导出EXCEL</z:tbutton>
						<z:tbutton onClick="backToStaff()"> <img src="../Icons/icon031a13.gif" />返回</z:tbutton>
						<input type="hidden" name="Inputor" id="Inputor" value="${Username}" />
					</td>
				</tr>
				
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg11" method="com.zving.cms.dataservice.PublishStaff.dg11DataBind" page="false">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="10%" ztype="RowNo"><b>序号</b></td>
								<td width="50%">栏目</td>
								<td width="20%" ><b>文章数</b></td>
								<td width="20%" ><b>发布数</b></td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC">
								<td>&nbsp;</td>
								<td style="padding-left: ${Padding}px"><a href="#" onClick="statInputorColumn('${InnerCode}','${ID}')">${CatalogName}</a></td>
								<td>${ArticleCount}</td>
								<td>${PublishCount}</td>
							</tr>
						</table>
					</z:datagrid>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
</z:init>
