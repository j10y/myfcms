<%@ include file="../Include/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-ID" content="text/html; charset=utf-8" />
<title>订单列表</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type="text/javascript">
	function search(){
		var valid=/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/;
		if(!valid.test($V("startDate"))||!valid.test($V("endDate"))){
			Dialog.alert("请输入正确的日期!");
			return;
		}
		if($V("startDate")>$V("endDate")){
			Dialog.alert("选择时间段错误!");
			return;
		}
		DataGrid.setParam("dg1",Constant.PageIndex,0);
		DataGrid.setParam("dg1","startDate",$V("startDate"));
		DataGrid.setParam("dg1","endDate",$V("endDate"));
		DataGrid.setParam("dg1","branchCode",$V("branchCode"));
		DataGrid.setParam("dg1","catalogCode",$V("catalogCode"));
		DataGrid.loadData("dg1");
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
				<td style="padding:8px 10px;" class="blockTd"><z:init
					method="com.zving.project.sanxia.PublishStat.init">
						时间段：
						<input value="${today}" type="text" id="startDate"
						name="startDate" ztype="Date" class="inputText" verify="NotNull"
						size="14">
					<input value="${today}" type="text" id="endDate" name="endDate"
						ztype="Date" class="inputText" verify="NotNull" size="14">
						选择单位:
						<select id="branchCode">
						${branch}
					</select>
						选择栏目:
						<select id="catalogCode">
						${catalog}
					</select>
					<input type="button" name="Submit" value="查询" onClick="search()">
				</z:init></td>
			</tr>

			<tr>
				<td
					style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
				<z:datagrid id="dg1"
					method="com.zving.project.sanxia.PublishStat.dg1DataBind" size="15">
					<table width="100%" cellpadding="2" cellspacing="0"
						class="dataTable">
						<tr ztype="head" class="dataTableHead">
							<td width="5%" ztype="RowNo"><b>序号</b></td>
							<td width="20%">单位</td>
							<td width="45%"><b>标题</b></td>
							<td width="15%"><b>录入人</b></td>
							<td width="15%"><b>发布日期</b></td>
						</tr>
						<tr style1="background-color:#FFFFFF"
							style2="background-color:#F9FBFC">
							<td height="100" align="center">&nbsp;</td>
							<td>${BranchName}</td>
							<td>${Title}</td>
							<td>${Inputer}</td>
							<td>${PublishDate}</td>
						</tr>

						<tr ztype="pagebar">
							<td colspan="10">${PageBar}</td>
							<td></td>
						</tr>
					</table>
				</z:datagrid></td>
			</tr>

		</table>
		</td>
	</tr>

</table>
</body>

</html>
