<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单组</title>
<script src="../Framework/Main.js"></script>
<script>
Page.onLoad(function(){
	$("dg1").afterEdit = function(tr,dr){
		dr.set("Name",$V("Name"));
		dr.set("Code",$V("Code"));
		dr.set("Memo",$V("Memo"));
		return true;
	}
});

function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 800;
	diag.Title = "新建菜单组";
	diag.URL = "MenuGroupDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Name").focus();
		$DW.$("button").onclick = addSave;
	};
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	var arr = $DW.DataGrid.getSelectedTreeValue("dg1");
	if(!arr||arr.length==0){
		alert("您还没有选择菜单项！");
		return;
	};
	var v = new Verify();
	v.add($DW.$("Name"),"菜单组名称","NotNull");
	if(!v.doVerify()){
		return;
	}
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.platform.MenuGroup.add",dc,function(response){
		alert(response.Message);
		if(response.Status==1){
			$D.close();
			window.location="MenuGroup.jsp";
		}
	});
}


function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		alert("请先选择要删除的行！");
		return;
	}

	if(!confirm("您确认要删除吗？")){
		return;
	}
	var dc = new DataCollection();
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.platform.MenuGroup.del",dc,function(response){
		alert(response.Message);
		if(response.Status==1){
			window.location="MenuGroup.jsp";
		}
	});
}

function showEditDialog(dr){
	var diag = new Dialog("Diag1");
	diag.Width = 400;
	diag.Height = 800;
	diag.Title = "修改"+this.document.title+"";
	diag.URL = "MenuGroupDialog.jsp?MenuGroupID="+dr.get("MenuGroupID");
	diag.onLoad = function(){
		$DW.$S("Name",dr.get("Name"));
		$DW.$S("Memo",dr.get("Memo"));
		$DW.$("Name").focus();
		$DW.$("button").value = " 保  存 ";
		$DW.$("button").onclick = save;
	};
	diag.show()
}

function save(){
	var dc = Form.getData($DW.$F("form2"));
	var arr = $DW.DataGrid.getSelectedTreeValue("dg1");
	if(!arr||arr.length==0){
		alert("您还没有选择菜单项！");
		return;
	};
	var v = new Verify();
	v.add($DW.$("Name"),"菜单组名称","NotNull");
	if(!v.doVerify()){
		return;
	}
	dc.add("IDs",arr.join());
	Server.sendRequest("com.zving.platform.MenuGroup.save",dc,function(response){
		alert(response.Message);
		if(response.Status==1){
			$D.close();
			window.location="MenuGroup.jsp";
		}
	});
}
</script>
</head>
<body scroll="no">
<z:tbar>
	<z:tbutton onClick="add()"><img src="../Icons/icon018a2.gif"/>新建</z:tbutton>
	<z:tbutton onClick="del()"><img src="../Icons/icon018a3.gif"/>删除</z:tbutton>
</z:tbar>

<div id="maincontent">
<z:datagrid id="dg1" method="com.zving.platform.MenuGroup.dg1DataBind"  editAction="showEditDialog" size="20">
  <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
    <tr ztype="head" class="dataTableHead">
      <td  width="11">&nbsp;</td>
      <td width="5%" ztype="selector" field="MenuGroupID">&nbsp;</td>
      <td width="40%" ><strong>菜单组名称</strong></td>
      <td width="40%"><strong>备注</strong></td>
      <td><strong>添加时间</strong></td>
    </tr>
    <tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>${Name}</td>
      <td>${Memo}</td>
      <td>${AddTime}</td>
    </tr>
	<tr ztype="pagebar">
	  <td colspan="5" align="center">${PageBar}</td>
	 </tr>
  </table>
</z:datagrid>
</div>
</body>
</html>
