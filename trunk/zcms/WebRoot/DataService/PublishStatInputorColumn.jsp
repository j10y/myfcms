<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<z:init method="com.zving.cms.dataservice.PublishStaff.initInputorColumn">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-ID" content="text/html; charset=utf-8" />
<title>栏目发布统计</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
function statInputorColumn(){
	DataGrid.setParam("dg12","ColumnInputor",$V("ColumnInputor"));
	DataGrid.setParam("dg12","CatalogInnerCode",$V("CatalogInnerCode"));
	DataGrid.setParam("dg12","startDate",$V("startDate"));
	DataGrid.setParam("dg12","endDate",$V("endDate"));
	DataGrid.setParam("dg12",Constant.PageIndex,0);
	DataGrid.loadData("dg12");
}

function statInputorColumnAll(){
	DataGrid.setParam("dg12","ColumnInputor",$V("ColumnInputor"));
	DataGrid.setParam("dg12","CatalogInnerCode",$V("CatalogInnerCode"));
	DataGrid.setParam("dg12","startDate","");
	DataGrid.setParam("dg12","endDate","");
	DataGrid.setParam("dg12",Constant.PageIndex,0);
	DataGrid.loadData("dg12");
}

function exportInputorColumn(){
	DataGrid.toExcel("dg12",0);
}

</script>
</head>
<body>
	<input type="hidden" id="ID" value="${ID}" />
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				
				<tr>
					<td style="padding:4px 8px;" class="blockTd"><b>${RealName}&nbsp;&nbsp;发布栏目&nbsp;&nbsp;"${CatalogName}"&nbsp;&nbsp;文章统计</b></td>
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
						
						<z:tbutton onClick="statInputorColumn()"> <img src="../Icons/icon031a1.gif" />统计</z:tbutton>
						<z:tbutton onClick="statInputorColumnAll()"> <img src="../Icons/icon031a15.gif" />全部统计</z:tbutton>
						<z:tbutton onClick="exportInputorColumn()"> <img src="../Icons/icon031a7.gif" />导出EXCEL</z:tbutton>
						
						<input type="hidden" name="ColumnInputor" id="ColumnInputor" value="${ColumnInputor}" />
						<input type="hidden" name="CatalogID" id="CatalogID" value="${CatalogID}" />
						<input type="hidden" name="CatalogInnerCode" id="CatalogInnerCode" value="${CatalogInnerCode}" />
					</td>
				</tr>
				<tr>
					<td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
					<z:datagrid id="dg12" method="com.zving.cms.dataservice.PublishStaff.dg12DataBind" size="14">
						<table width="100%" cellpadding="2" cellspacing="0"
							class="dataTable">
							<tr ztype="head" class="dataTableHead">
								<td width="8%" ztype="RowNo"><b>序号</b></td>
								<td width="8%">编号</td>
								<td width="34%">标题</td>
								<td width="10%">状态</td>
								<td width="20%">修改时间</td>
								<td width="20%">发布时间</td>
							</tr>
							<tr style1="background-color:#FFFFFF"
								style2="background-color:#F9FBFC" >
								<td align="center">&nbsp;</td>
								<td>${ID}</td>
								<td><a href="../Document/Preview.jsp?ArticleID=${ID}" target="_blank" title="${Title}">${Title}</a></td>
								<td>${StatusName}</td>
								<td>${LastModifyTime}</td>
								<td>${PublishDate}</td>
							</tr>
							<tr ztype="pagebar">
								<td colspan="5">${PageBar}</td>
								<td></td>
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
</z:init>