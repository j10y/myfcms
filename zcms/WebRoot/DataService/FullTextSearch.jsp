<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 540;
	diag.Height = 400;
	diag.Title = "新建搜索类别";
	diag.URL = "DataService/FullTextSearchDialog.jsp";
	diag.onLoad = function(){
		$DW.onTypeChange();
	};
	diag.OKEvent = Save;
	diag.show();
	diag.OKButton.value = "保存";
}

function Save(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = $DW.Form.getData("F1");
	if(dc.get("Type")=="Article"){
		if(!$DW.$NV("Catalog")){
			Dialog.alert("没有选择任何文章栏目!");
			return;
		}
		dc.add("RelaText",$DW.$NV("Catalog"));
	}
	if(dc.get("Type")=="Media"){
		if(!$DW.$NV("ImageCatalog")){
			Dialog.alert("没有选择任何媒体栏目!");
			return;
		}
		dc.add("RelaText",$DW.$NV("ImageCatalog"));
	}
	Server.sendRequest("com.zving.cms.dataservice.FullText.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			$D.close();
			DataGrid.loadData("dg1");
		});		
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
	diag.Width = 540;
	diag.Height = 400;
	diag.Title = "修改搜索类别";
	diag.URL = "DataService/FullTextSearchDialog.jsp";
	diag.onLoad = function(){
		$DW.Form.setValue(dr,"F1");
		$DW.RelaStr = dr.get("RelaText");
		$DW.onTypeChange();
	};
	diag.OKEvent = Save;
	diag.show();
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择要删除的行！");
		return;
	}
	Dialog.confirm("确认删除吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Server.sendRequest("com.zving.cms.dataservice.FullText.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				DataGrid.loadData('dg1');
			});
		});
	})
}

function manual(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("请先选择行！");
		return;
	}
	Dialog.confirm("确认重新生成索引吗？",function(){
		var dc = new DataCollection();
		dc.add("IDs",arr.join());
		Dialog.wait("正在重新生成索引...");
		Server.sendRequest("com.zving.cms.dataservice.FullText.manual",dc,function(response){
			Dialog.closeEx();
			Dialog.alert(response.Message);
		});
	})
}
</script>
</head>
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
      <tr valign="top">
        <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
            <tr>
              <td valign="middle" class="blockTd"><img src="../Icons/icon033a15.gif" width="20" height="20" /> 全文检索管理 </td>
            </tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon033a2.gif" width="20" height="20"/>添加</z:tbutton>
				<z:tbutton onClick="edit()"><img src="../Icons/icon033a4.gif" width="20" height="20"/>修改</z:tbutton>
				<z:tbutton onClick="del()"><img src="../Icons/icon033a3.gif" width="20" height="20"/>删除</z:tbutton>
				<z:tbutton onClick="manual()"><img src="../Icons/icon033a16.gif" width="20" height="20"/>生成索引</z:tbutton>
			  </td>
            </tr>
            <tr>
              <td style="padding-top:0px;padding-left:6px;padding-right:6px;padding-bottom:8px;">
			  <z:datagrid id="dg1" method="com.zving.cms.dataservice.FullText.dg1DataBind" page="false">
                <table width="100%" cellpadding="2" cellspacing="0" class="dataTable" >
                  <tr ztype="head" class="dataTableHead">
                    <td  width="2%" ztype="rowno">&nbsp;</td>
                    <td width="3%" ztype="selector" field="id">&nbsp;</td>
                    <td width="14%">代码</td>
                    <td width="13%"><b>名称</b></td>
                    <td width="15%"><b>类型</b></td>
                    <td width="42%"><b>备注</b></td>
                    <td width="11%" ztype='editDialog' function='edit'>修改</td>
                  </tr>
                  <tr onDblClick="edit()" style1="background-color:#FFFFFF" style2="background-color:#F7F8FF">
                    <td align="center">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>${Code}</td>
                    <td>${Name}</td>
                    <td>${TypeName}</td>
                    <td>${Memo}</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
              </z:datagrid></td>
            </tr>
        </table></td>
      </tr>
    </table>
	</body>
</html>
