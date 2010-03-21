<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../../Framework/Main.js"></script>
<script src="../../Framework/Spell.js"></script>
<script>


function searchAll(){
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","UserGroup","");
	DataGrid.setParam("dg1","LowerScore","");
	DataGrid.setParam("dg1","UpperScore","");
	DataGrid.setParam("dg1","LowerThemeCount","");
	DataGrid.setParam("dg1","UpperThemeCount","");
	DataGrid.setParam("dg1","ForumUserName","");
	DataGrid.loadData("dg1");
}

function doSearch(){
	var name = "";
	if ($V("ForumUserName") != "查询用户名") {
		name = $V("ForumUserName").trim();
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","UserGroup",$V("UserGroup"));
	DataGrid.setParam("dg1","LowerScore",$V("LowerScore"));
	DataGrid.setParam("dg1","UpperScore",$V("UpperScore"));
	DataGrid.setParam("dg1","LowerThemeCount",$V("LowerThemeCount"));
	DataGrid.setParam("dg1","UpperThemeCount",$V("UpperThemeCount"));
	DataGrid.setParam("dg1","ForumUserName",name);
	DataGrid.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'ForumUserName'||ele.id == 'Submitbutton'){
			doSearch();
		}
	}
}


function delKeyWord() {
	if ($V("ForumUserName") == "查询用户名、姓名、姓名拼音") {
		$S("ForumUserName","");
	}
}
 function edit(){
	var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	dr = drs[0]; 
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height =300;
	diag.Title = "编辑用户组";
	diag.URL = "BBS/Admin/ForumUserEditDialog.jsp?UserName="+dr.get("UserName");
	diag.OKEvent = editSave;
	diag.show();
}
function editSave(){
	var dc = Form.getData($DW.$F("form1"));
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.bbs.admin.ForumUserInfo.editUserGroup",dc,function(response){
		Dialog.alert(response.Message, function() {
			if(response.Status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});
	});
}
/*function save(){
	var dt = DataGrid.getSelectedData("dg1");
	var drs = dt.Rows;
	if(!drs||drs.length==0){
		Dialog.alert("请双击修改后再进行保存！");
		return;
	}
	var dc = new DataCollection();
	dc.add("UserName",$V("UserName"));
	// alert(dc.get("UserName"));
	dc.add("Member",$V("MemberGroup"));
	// alert(dc.get("Member"));
	dc.add("System",$V("SystemGroup"));
	dc.add("Special",$V("SpecialGroup"));
	dc.add("ThemeCount",$V("ThemeCount"));
	dc.add("ForumScore",$V("ForumScore"));
	Server.sendRequest("com.zving.bbs.ForumUserInfo.editSave",dc,function(response){
		Dialog.alert(response.Message);
		if(response.Status==1){
			DataGrid.loadData('dg1');
		}
	});
}*/



</script>
</head>
<body>
<input type="hidden" id="ID" value="${ID}" />
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
      <tr valign="top">
        <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
            <tr>
              <td valign="middle" class="blockTd"><img src="../../Icons/icon018a6.gif" />论坛用户列表</td>
            </tr>
            <tr nowrap="nowrap">
            <z:init method="com.zving.bbs.admin.ForumUserInfo.init">
              	  	<td >
		                  <div style="float:left; white-space: nowrap;">
		                  <z:select id="UserGroup" style="width:100px;"><span value="0">选择组</span>${UserGroup}</z:select>
		                  积分介于：<input type="text" value="" id="LowerScore" name="" size="7">~<input type="text" value="" id="UpperScore" name="" size="7" />
		                  发帖介于：<input type="text" value="" id="LowerThemeCount" name="" size="7">~<input type="text" value="" id="UpperThemeCount" name="" size="7" />
		             	  用户名：<input name="ForumUserName" type="text" id="ForumUserName" value="查询用户名" size="18" onFocus="delKeyWord();" />
		            	  </div>
		             	  <z:tbutton onClick="doSearch()">
		             	  <img src="../../Icons/icon022a16.gif" width="20" height="20" />查询</z:tbutton> 
		            	  
			 		</td>
			 </z:init>
            </tr>
            <tr>
              <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
			  <z:datagrid id="dg1" method="com.zving.bbs.admin.ForumUserInfo.getList" size="15">
                <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
                  <tr ztype="head" class="dataTableHead">
  				    <td width="5%" ztype="RowNo"><strong>序号</strong></td>
                    <td width="3%" ztype="selector" field="UserName">&nbsp;</td>
                    <td width="9%"><b>用户名</b></td>
                    <td width="15%"><b>用户组</b></td>
                    <td width="15%"><b>管理组</b></td>
                    <td width="10%"><b>发帖数</b></td>
                    <td width="5%"><b>积分</b></td>
                  </tr>
                  <tr style1="background-color:#FFFFFF" ondblclick="edit()">        
					<td width="6%">&nbsp;</td>            
                    <td>&nbsp;</td>
                    <td>${UserName}</td>
                    <td>${Name}</td>
                    <td>${AdminName}</td>
                    <td>${ThemeCount}</td>
                    <td>${ForumScore}</td>
                  </tr>  
                             
                  <tr ztype="pagebar">
                    <td colspan="10">${PageBar}</td>
                  </tr>
                </table>
              </z:datagrid></td>
            </tr>
        </table></td>
      </tr>
    </table>

</body>
</html>

