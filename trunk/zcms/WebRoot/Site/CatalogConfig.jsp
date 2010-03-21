<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	if($NV("PlanType")=="Period"){
		var expr = $V("CronExpression");
		var arr = expr.split(" ");
		if(arr[0].indexOf("/")>0){
			$S("PeriodType","Minute");
			$S("Period",arr[0].substring(arr[0].indexOf("/")+1));
		}
		if(arr[1].indexOf("/")>0){
			$S("PeriodType","Hour");
			$S("Period",arr[1].substring(arr[1].indexOf("/")+1));
		}
		if(arr[2].indexOf("/")>0){
			$S("PeriodType","Day");
			$S("Period",arr[2].substring(arr[2].indexOf("/")+1));
		}
		if(arr[3].indexOf("/")>0){
			$S("PeriodType","Month");
			$S("Period",arr[3].substring(arr[3].indexOf("/")+1));
		}
		if(arr[4].indexOf("/")>0){
			$S("PeriodType","Year");
			$S("Period",arr[4].substring(arr[4].indexOf("/")+1));
		}
	}
	onPlanChange();
	
});

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

function save(){
	if(Verify.hasError()){
		return;
	}
	var dc = Form.getData($F("form2"));
	Server.sendRequest("com.zving.cms.site.CatalogConfig.save",dc,function(response){
		Dialog.alert(response.Message);
	});
}
</script>
</head>
<body>
<z:init method="com.zving.cms.site.CatalogConfig.init">
	<div style="padding:2px;">
	<table width="100%">
		<tr>
			<td><z:tbutton onClick="save();"><img src="../Icons/icon002a2.gif" width="20" height="20" />保存</z:tbutton></td>
		</tr>
	</table>
    <form id="form2">
	<input name="SiteID" type="hidden" id="SiteID" value="${SiteID}"  />
	<input name="CatalogID" type="hidden" id="CatalogID" value="${CatalogID}" />
    <input name="ID" type="hidden"  id="ID" value="${ID}" />
	<table width="100%" border="1" cellpadding="4" cellspacing="0" bordercolor="#eeeeee" style="display:${display};">
		<tr id="tr_SiteID">
			<td width="14%"><p><b>状态设置</b>：</p>
		    </td>
			<td width="32%">可发布文档的状态：</td>
			<td width="54%">${AllowStatusOptions}</td>
		</tr>
		<tr style="display:${sitedisplay};">
			<td>&nbsp;</td>
			<td>发布文档状态沿用：</td>
		  <td align="left"><z:select id="SiteStatusExtend" style="width:150px;"> <span value="1">不沿用</span> <span value="2">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
		<tr style="display:${catalogdisplay};">
			<td>&nbsp;</td>
			<td>发布文档状态沿用：</td>
		  <td align="left"><z:select id="CatalogStatusExtend" style="width:150px;"> <span value="1">仅本栏目</span> <span value="2">所有子栏目沿用相同设置</span> <span value="3">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
		<tr>
			<td >&nbsp;</td>
			<td >已发布文档编辑后状态：</td>
		  <td><z:select id="AfterEditStatus">${AfterEditStatusOptions}</z:select></td>
		</tr>
		<tr style="display:${sitedisplay};">
			<td>&nbsp;</td>
			<td>已发布文档编辑后状态沿用：</td>
		  <td align="left"><z:select id="SiteEditExtend" style="width:150px;"> <span value="1">不沿用</span> <span value="2">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
		<tr style="display:${catalogdisplay};">
			<td>&nbsp;</td>
			<td>已发布文档编辑后状态沿用：</td>
		  <td align="left"><z:select id="CatalogEditExtend" style="width:150px;"> <span value="1">仅本栏目</span> <span value="2">所有子栏目沿用相同设置</span> <span value="3">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
	</table>
	<table width="100%" border="1" cellpadding="4" cellspacing="0" bordercolor="#eeeeee">
		<tr>
			<td width="14%"  class="tdgrey1"><b>定时发布</b>：</td>
			<td width="32%"  class="tdgrey1">起始时间：</td>
			<td width="54%" class="tdgrey1"><span class="tdgrey2">
              <input name="StartDate" type="text" class="inputText" id="StartDate" ztype="Date" value="${StartDate}" size=14/>
			  </span> <span class="tdgrey2">
              <input name="StartTime" type="text" class="inputText"id="StartTime" ztype="Time" value="${StartTime}" size=14/>
	      </span></td>
		</tr>
		<tr>
			<td class="tdgrey1">&nbsp;</td>
			<td class="tdgrey1">执行周期：</td>
			<td class="tdgrey2"><label>
			  <input name="PlanType" type="radio" value="Period" onClick="onPlanChange();" ${PeriodCheck}>
			  每隔</label>
                <input name="Period" type="text" value="1" class="inputText" id="Period" size=4 verify="NotNull" />
                <z:select id="PeriodType" style="width:50px;"> <span value="Minute">分钟</span> <span value="Hour">小时</span> <span value="Day" selected="true">天</span> <span value="Month">月</span> </z:select></td>
		</tr>
		<tr>
			<td class="tdgrey1"></td>
			<td class="tdgrey1"></td>
			<td class="tdgrey2"><label>
			  <input type="radio" name="PlanType" value="Cron" onClick="onPlanChange();" ${CronCheck}>
			  使用Cron表达式</label>
                <div id="trCron" style="display:none">
                  <input name="CronExpression" type="text" value="${CronExpression}" class="inputText" id="CronExpression" size=40 verify="NotNull"/>
                </div></td>
		</tr>
		<tr>
			<td  class="tdgrey1">&nbsp;</td>
			<td  class="tdgrey1">启用状态：</td>
			<td class="tdgrey2"><label for="IsUsing1">
			  <input type="radio" name="IsUsing" value="Y" id="IsUsing1" ${IsUsingYCheck}>
			  启用</label>
                <label for="IsUsing0">
                  <input type="radio" name="IsUsing" value="N" id="IsUsing0" ${IsUsingNCheck}>
                  停用</label></td>
		</tr>
	</table>
	<table width="100%" border="1" cellpadding="4" cellspacing="0" bordercolor="#eeeeee">
		<tr>
			<td width="14%"><b>其他设置</b>：</td>
			<td width="32%">归档期限：</td>
		  <td width="54%"><z:select id="ArchiveTime"> ${ArchiveTimeOptions}</z:select></td>
		</tr>
		<tr style="display:${sitedisplay};">
			<td>&nbsp;</td>
			<td>归档期限沿用：</td>
		  <td align="left"><z:select id="SiteArchiveExtend" style="width:150px;"> <span value="1">不沿用</span> <span value="2">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
		<tr style="display:${catalogdisplay};">
			<td>&nbsp;</td>
			<td>归档期限沿用：</td>
		  <td align="left"><z:select id="CatalogArchiveExtend" style="width:150px;"> <span value="1">仅本栏目</span> <span value="2">所有子栏目沿用相同设置</span> <span value="3">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
        <tr>
            <td class="tdgrey1">&nbsp;</td>
            <td class="tdgrey1">热点词汇处理：</td>
            <td class="tdgrey2"><label for="KeyWordUsing1">
              <input type="radio" name="HotWordFlag" value="Y" id="KeyWordUsing1" ${KeyWordYCheck}>
              处理热点词汇</label>
                <label for="KeyWordUsing0">
                  <input type="radio" name="HotWordFlag" value="N" id="KeyWordUsing0" ${KeyWordNCheck}>
                  不处理热点词汇</label>
                <label for="KeyWordUsing2">
                  <input type="radio" name="HotWordFlag" value="S" id="KeyWordUsing2" ${KeyWordSCheck}>
                  继承上级栏目设置</label>
            </td>
        </tr>
        <tr style="display:${sitedisplay};">
			<td>&nbsp;</td>
			<td>热点词汇处理沿用：</td>
		  <td align="left"><z:select id="SiteHotWordExtend" style="width:150px;"> <span value="1">不沿用</span> <span value="2">所有栏目沿用相同设置</span> </z:select></td>
        </tr>
		<tr style="display:${catalogdisplay};">
			<td>&nbsp;</td>
			<td>热点词汇处理沿用：</td>
		  <td align="left"><z:select id="CatalogHotWordExtend" style="width:150px;"> <span value="1">仅本栏目</span> <span value="2">所有子栏目沿用相同设置</span> <span value="3">所有栏目沿用相同设置</span> </z:select></td>
		</tr>
		<tr>
			<td class="tdgrey1">&nbsp;</td>
			<td class="tdgrey1">附件下载是否使用原始文件名：</td>
			<td class="tdgrey2">${AttachDownFlagRadios}</td>
		</tr>
	</table>
    </form>
	</div>
</z:init>
</body>
</html>
