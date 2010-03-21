<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Config.getValue("App.Name")%></title>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script>
function add(m){
	var diag =new Dialog("diag1");
	diag.Width = 460;
	diag.Height = 160;
	diag.Title = "新备忘";
	diag.URL = "Document/NotesDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Title").focus();
		$DW.$S("DateOfMonth",m);		
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
		return;
	}
	dc.add("nowDate",$V("hnowDate"));
	Server.sendRequest("com.zving.cms.document.Notes.save",dc,function(response){
		if(response.Status==1){
			window.location = "Notes.jsp?cDate="+$V('hnowDate')+"&Flag=0";
      		$D.close();;
		}
	})
}

function edit(m,id){
	var diag =new Dialog("diag1");
	diag.Width = 460;
	diag.Height = 160;
	diag.Title = "修改备忘";
	diag.URL = "Document/NotesDialog.jsp?ID="+id;
	diag.onLoad = function(){
		$DW.$("Title").focus();
		$DW.$S("DateOfMonth",m);		
		$DW.$S("ID",id);
	};
	diag.OKEvent = addSave;
	diag.show();
	diag.OKButton.value = "保存";
	diag.addButton("Delete","删除",function(){
		del(id);
	});
}

function del(id){
	Dialog.confirm("确定要删除该条备忘吗？",function(){
	var dc = new DataCollection();	
	dc.add("ID",id);
	Server.sendRequest("com.zving.cms.document.Notes.del",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			Dialog.alert("删除成功",function(){
				window.location = "Notes.jsp?cDate="+$V('hnowDate')+"&Flag=0";
			});
		}
	})
	})
}

function ChangeDate(i){
	window.location = "Notes.jsp?cDate="+$V('hnowDate')+"&Flag="+i;
}

//右键菜单
function showMenu(event,m,id){
	var menu = new Menu();
	menu.setEvent(event);
	var param = [];
	param.push(m);
	param.push(id);
	menu.setParam(param);
	menu.addItem("添加备忘",menuAdd,"/Icons/icon016a2.gif");
	menu.addItem("修改备忘",menuEdit,"/Icons/icon016a8.gif");
	menu.addItem("删除备忘",menuDel,"/Icons/icon016a3.gif");
	menu.show();
}

function menuAdd(param){
	add(param[0]);
}
function menuEdit(param){
	edit(param[0],param[1]);
}
function menuDel(param){
	del(param[1]);
}
</script>
</head>
<z:init method="com.zving.cms.document.Notes.init">
	<body oncontextmenu="return false;">
	<table width="100%" border="0" cellspacing="6" cellpadding="0"
		style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				class="blockTable">
				<tr>
					<td valign="middle" class="blockTd" style=" padding-bottom:0;"><img
						src="../Icons/icon016a1.gif" width="20" height="20" />个人备忘列表</td>
				</tr>
				<tr>
					<td align="right" style="padding:0;">
					<table width="260" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100"><z:tbutton onClick="ChangeDate(-1);">
								<img src="../Icons/icon403a17.gif" />上一个月</z:tbutton></td>
							<td width="70" align="center">${nowDate}</td>
							<td width="100"><z:tbutton onClick="ChangeDate(1);">
								<img src="../Icons/icon403a18.gif" />下一个月</z:tbutton></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>${NoteContent}</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<input name="hnowDate" id="hnowDate" value="${hnowDate}" type="hidden" />
	</body>
</z:init>
</html>
