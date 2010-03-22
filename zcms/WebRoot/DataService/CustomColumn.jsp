<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自定义表单</title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 370;
	diag.Height = 190;
	diag.Title = "新建表单";
	diag.URL = "DataService/CustomColumnDialog.jsp";
	diag.onLoad = function(){
		$DW.$("ClassName").focus();
	};
	diag.OKEvent = Save;
	diag.show();
}
function Save(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = Form.getData($DW.$F("form2"));
	Server.sendRequest("com.zving.platform.CustomColumn.add",dc,function(response){
		Dialog.alert(response.Message);
		if(response.Status==1){			
			$D.close();
			DataGrid.setParam("dg1",Constant.PageIndex,0);
			DataGrid.loadData('dg1');
		}
	});
}
function showEditDialog(dr){
	var diag = new Dialog("Diag1");
	diag.Width = 365;
	diag.Height = 180;
	diag.Title = "修改表单";
	diag.URL = "Platform/CustomColumnDialog.jsp";
	diag.onLoad = function(){
		$DW.$S("ID",dr.get("ID"));
		$DW.$S("ClassName",dr.get("ClassName"));
		$DW.$S("ClassCode",dr.get("ClassCode"));
		$DW.$S("Memo",dr.get("Memo"));
		$DW.$("ClassName").focus();
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
			Server.sendRequest("com.zving.platform.CustomColumn.del",dc,function(response){
				if(response.Status==1){
					Dialog.alert("删除成功",function(){
						DataGrid.loadData('dg1');
					});
				}else{
					Dialog.alert(response.Message);
				}
			});
	})
}
function preview(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length!=1){
		Dialog.alert("请选择一行进行预览！");
		return;
	}
	if(screen.width==800){
		var width = 800,height = 600,leftm = 0,topm = 0;
	}
	else if(screen.width>800){
	  	var width  = Math.floor( screen.width  * .78 );
  		var height = Math.floor( screen.height * .8 );
  		var leftm  = Math.floor( screen.width  * .1);
 		var topm   = Math.floor( screen.height * .1);
	}
	else{
		return;
	}
 	var args = "toolbar=0,location=0,maximize=1,directories=0,status=0,menubar=0,scrollbars=0, resizable=1,left=" + leftm+ ",top=" + topm + ", width="+width+", height="+height;
  	var url="CustomColumnPreview.jsp?ID=" + arr[0];
  	window.open(url,"",args);
}
</script>
</head>
<body>
  <table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
    <tr valign="top">
      <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
          <tr>
            <td valign="middle" class="blockTd"><img src="../Icons/icon002a1.gif" />自定义表单列表</td>
          </tr>
				<tr>
					<td style="padding:0 8px 4px;"><z:tbutton onClick="add()"><img src="../Icons/icon024a2.gif"/>新建</z:tbutton>
					<z:tbutton onClick="del()"><img src="../Icons/icon024a3.gif"/>删除</z:tbutton>
					<z:tbutton onClick="preview()"><img src="../Icons/icon024a15.gif" />预览</z:tbutton>
             </td>
          </tr>
          <tr>   <td style="padding:0px 5px;">
<z:datagrid id="dg1" method="com.zving.platform.CustomColumn.dg1DataBind" size="15">
              <table width="100%" cellpadding="2" cellspacing="0" class="dataTable" >
                <tr ztype="head" class="dataTableHead">
                  <td  width="5">&nbsp;</td>
                  <td width="20" ztype="selector" field="id">&nbsp;</td>
                  <td width="120"><b>表单标题</b></td>
                  <td width="90"><b>表单名字</b></td>
                  <td width="150"><b>备注</b></td>
                </tr>
                <tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
                  <td  width="5">&nbsp;</td>
                  <td width="20">&nbsp;</td>
                  <td><a href="CustomColumnItem.jsp?ClassCode=${ClassCode}">${ClassName}</a></td>
                  <td>${ClassCode}</td>
                  <td>${Memo}</td>
                </tr>
                <tr ztype="pagebar">
                  <td colspan="5">${PageBar}</td>
                </tr>
              </table>
            </z:datagrid></td>
          </tr>
      </table></td>
    </tr>
  </table>
</body>
</html>