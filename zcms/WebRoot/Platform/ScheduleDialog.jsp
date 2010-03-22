<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function onPlanChange(){
	if($NV("PlanType")=="Period"){
		$("Period").enable();
		$("PeriodType").enable();
		$("trCron").hide();
	}else{
		$("Period").disable();
		$("PeriodType").disable();
		$("trCron").show();
	}
}
function onTypeChange(){//id参数仅供修改时初始化使用
	if($V("TypeCode")!=""){
		var dc = new DataCollection();
		dc.add("TypeCode",$V("TypeCode"));
		Server.sendRequest("com.zving.platform.Schedule.getUsableTask",dc,function(response){
			$("SourceID").clear();
			for(var i=0;i<response.size();i++){
				var k = response.getKey(i);
				var v = response.get(i);
				if(!k.startWith("_")){
					$("SourceID").add(v,k);
				}
			}
			if(window.SourceID){
				$S("SourceID",window.SourceID);
				window.SourceID = false;
			}
		});
	}
}
</script>
</head>
<body>
<form id="form2"><z:init
	method="com.zving.platform.Schedule.init">
	<table width="100%" height="100%" border="0">
		<tr>
			<td>
			<table width="590" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td width="168" height="10" class="tdgrey2"><input name="hidden2" type="hidden" id="ID" value=""></td>
					<td width="412" class="tdgrey2"></td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">任务类别：</td>
					<td class="tdgrey2">
					<z:select id='TypeCode' onChange="onTypeChange()"
						verify="NotNull"> ${TypeCode}</z:select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">选择任务：</td>
					<td class="tdgrey2">
					<z:select id='SourceID' verify="NotNull"> ${SourceID}</z:select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">起始时间：</td>
					<td class="tdgrey1"><span class="tdgrey2"> <input
						name="StartDate" type="text" class="inputText" id="StartDate"
						ztype="Date" size=14 verify="NotNull" /> </span><span class="tdgrey2">
					<input name="StartTime" type="text" class="inputText"
						id="StartTime" ztype="Time" size=14 verify="NotNull" /> </span></td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">执行周期：</td>
					<td class="tdgrey2"><label><input name="PlanType"
						type="radio" value="Period" onClick="onPlanChange();" checked>每隔</label>
					<input name="Period" type="text" value="1" class="inputText"
						id="Period" size=4 verify="NotNull" />
					<z:select id="PeriodType" style="width:50px;"><span
						value="Minute">分钟</span> <span value="Hour">小时</span> <span
						value="Day" selected="true">天</span> <span value="Month">月</span>
					</z:select>

					</td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1"></td>
					<td class="tdgrey2"><label><input type="radio"
						name="PlanType" value="Cron" onClick="onPlanChange();">使用Cron表达式</label>
					<div id="trCron" style="display:none"><input
						name="CronExpression" type="text" value="" class="inputText"
						id="CronExpression" size=40 verify="NotNull"
						condition="$NV('PlanType')=='Cron'" /></div>
					</td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">启用状态：</td>
					<td class="tdgrey2"><label> <input type="radio"
						name="IsUsing" value="Y" id="IsUsing1" checked> 
					启用</label> <label>
					<input type="radio" name="IsUsing" value="N" id="IsUsing0">
					停用</label></td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">描述：</td>
					<td class="tdgrey2"><input name="Description" type="text"
						value="" class="inputText" id="Description" size=40 /></td>
				</tr>
				<tr>
					<td height="30" align="right" class="tdgrey1">&nbsp;</td>
					<td class="tdgrey2">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</z:init></form>
</body>
</html>
