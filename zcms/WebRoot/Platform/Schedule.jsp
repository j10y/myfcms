<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定时任务</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "新建定时计划";
	diag.ShowMessageRow = true;
	diag.MessageTitle = "新建定时计划";
	diag.Message = "选择可执行任务，并设置执行计划";
	diag.URL = "Platform/ScheduleDialog.jsp";
	diag.onLoad = function(){
	};
	diag.OKEvent = save;
	diag.show();
}

function save(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.platform.Schedule.save",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message);
			$D.close();
			DataGrid.setParam("dg1",Constant.PageIndex,0);
		    DataGrid.loadData("dg1");
		}else{
			Dialog.alert(response.Message);
		}
	});
}

function edit(dr){
	if(!dr){
		var dt = DataGrid.getSelectedData("dg1");
		if(dt.getRowCount()==0){
			Dialog.alert("请先选择要修改的行!");
			return;
		}
		dr = dt.getDataRow(0);
	}
	var diag = new Dialog("Diag1");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "修改定时计划";
	diag.ShowMessageRow = true;
	diag.MessageTitle = "修改定时计划";
	diag.Message = "选择可执行任务，并设置执行计划";
	diag.URL = "Platform/ScheduleDialog.jsp";
	diag.onLoad = function(){
		$DW.SourceID = dr.get("SourceID");
		$DW.Form.setValue(dr,"form2");
		var time = dr.get("StartTime");
		$DW.$S("StartDate",time.split(" ")[0]);
		$DW.$S("StartTime",time.split(" ")[1]);
		if(dr.get("PlanType")=="Period"){
			var expr = dr.get("CronExpression");
			var arr = expr.split(" ");
			if(arr[0].indexOf("/")>0){
				$DW.$S("PeriodType","Minute");
				$DW.$S("Period",arr[0].substring(arr[0].indexOf("/")+1));
			}
			if(arr[1].indexOf("/")>0){
				$DW.$S("PeriodType","Hour");
				$DW.$S("Period",arr[1].substring(arr[1].indexOf("/")+1));
			}
			if(arr[2].indexOf("/")>0){
				$DW.$S("PeriodType","Day");
				$DW.$S("Period",arr[2].substring(arr[2].indexOf("/")+1));
			}
			if(arr[3].indexOf("/")>0){
				$DW.$S("PeriodType","Month");
				$DW.$S("Period",arr[3].substring(arr[3].indexOf("/")+1));
			}
			if(arr[4].indexOf("/")>0){
				$DW.$S("PeriodType","Year");
				$DW.$S("Period",arr[4].substring(arr[4].indexOf("/")+1));
			}
		}		
		$DW.onPlanChange();
		$DW.$("TypeCode").disable();
		$DW.$("SourceID").disable();
	}
	diag.OKEvent = save;
	diag.show();
}

function execute(){
	var dt = DataGrid.getSelectedData("dg1");
	if(dt.getRowCount()==0){
		Dialog.alert("请先选择要修改的行!");
		return;
	}
	dr = dt.getDataRow(0);
	var dc = new DataCollection();
	dc.add("ID",dr.get("ID"));
	Server.sendRequest("com.zving.platform.Schedule.execute",dc,function(response){
		Dialog.alert(response.Message);
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确定要删除该配置项吗？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.platform.Schedule.del",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert("删除成功");
				DataGrid.setParam("dg1",Constant.PageIndex,0);
        DataGrid.loadData("dg1");
			}
		});
	});
} 
</script>
</head>
<z:init method="com.zving.platform.Schedule.init">
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
      <tr valign="top">
        <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
            <tr>
              <td valign="middle" class="blockTd"><img src="../Icons/icon020a1.gif" width="20" height="20" /> 定时计划列表</td>
            </tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon020a2.gif" width="20" height="20" />新建</z:tbutton>
                  <z:tbutton onClick="edit()"> <img src="../Icons/icon020a4.gif" width="20" height="20" />修改</z:tbutton>
                  <z:tbutton onClick="del()"> <img src="../Icons/icon020a3.gif" width="20" height="20" />删除</z:tbutton>
                  <z:tbutton onClick="execute()"> <img src="../Icons/icon020a9.gif" width="20" height="20" />手动执行</z:tbutton>
			 </td>
            </tr>
            <tr>
              <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
			  <z:datagrid id="dg1" method="com.zving.platform.Schedule.dg1DataBind" size="15">
                <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
                  <tr ztype="head" class="dataTableHead">
                    <td  width="3%" align="center" ztype="RowNo">&nbsp;</td>
                    <td width="3%" align="center" ztype="selector" field="id">&nbsp;</td>
                    <td width="12%"><b>类别</b></td>
                    <td width="20%"><b>任务名称</b></td>
                    <td width="8%">是否启用</td>
                    <td width="16%"><b>下次运行时间</b></td>
                    <td width="32%"><b>任务描述</b></td>
                  </tr>
                  <tr onDblClick="edit();">
                    <td align="center">&nbsp;</td>
                    <td align="center">&nbsp;</td>
                    <td>${TypeCodeName}</td>
                    <td>${SourceIDName}</td>
                    <td>${IsUsingName}</td>
                    <td>${NextRunTime}</td>
                    <td>${Description}</td>
                  </tr>
                  <tr ztype="pagebar">
                    <td colspan="7">${PageBar}</td>
                  </tr>
                </table>
              </z:datagrid></td>
            </tr>
        </table></td>
      </tr>
    </table>
	</body>
</z:init>
</html>
