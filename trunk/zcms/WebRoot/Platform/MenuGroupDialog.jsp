<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>用户日志查询</title>
<script src="../Framework/Main.js"></script>
<script>
function treeCheckBoxClick(ele){
	var id = ele.id;
	var index = id.substring(id.lastIndexOf("_")+1);
	var checked = ele.checked;
	var level = ele.getAttribute("level");
	var arr = $N(ele.name);
	var length = arr.length;
	// 选中
	if(checked){
		for(var i=index-2;i>=0;i--){
			if(arr[i].getAttribute("level")<level){
				arr[i].checked = true;
				level = arr[i].getAttribute("level");
				if(level==0){
					break;
				}
			}
		}
		level = ele.getAttribute("level");
		for(var i=index;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = true;
			}else {
				break;
			}
		}
	}else{
	// 取消选中
		for(var i=index-2;i>=0;i--){
			if(arr[i].getAttribute("level")<level){
				var check_flag = false;
				var tmp_index = arr[i].id.substring(arr[i].id.lastIndexOf("_")+1);
				for(var j=tmp_index;j<length;j++){
					if(level<=arr[j].getAttribute("level")){
						if(arr[j].checked){
							check_flag = true;
							break;
						}
					}else{
						break;
					}
				}
				arr[i].checked = check_flag;
				
				level = arr[i].getAttribute("level");
				if(level==0){
					break;
				}
			}
		}
		level = ele.getAttribute("level");
		for(var i=index;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = false;
			}else{
				break;
			}
		}
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<z:init method="com.zving.platform.MenuGroup.initDialog">
<body class="dialogBody">
<form id="form2">
<div style="overflow:auto;height:expression((document.body.clientHeight-40)+'px')">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td width="335" height="10" ></td>
      <td></td>
    </tr>
    <tr>
      <td align="right" ><strong>菜单组名称：</strong> </td>
      <td width="70%" align="left">&nbsp;&nbsp;
        <input name="Name"  type="text"  class="input1" id="Name" />
        <input name="MenuGroupID"  type="hidden"  class="input1" id="MenuGroupID" value = "${MenuGroupID}"/>
      </td>
    </tr>
    <tr>
      <td align="right" ><strong>备注：</strong></td>
      <td align="left">&nbsp;&nbsp;
          <input name="Memo" type="text"  class="input1" id="Memo" value=""/>
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" ><strong><br>
      菜单项：</strong> </td>
      <td align="center">
			<z:datagrid id="dg1" method="com.zving.platform.Menu.dg1DataBind" edit="false">
			  <table width="100%" cellpadding="2" cellspacing="0" >
				<tr>
				  <td height="1"></td>
				  <td ztype="tree"  field="id" level="level" sql = "select MenuID from ZDMenuGroupDetail where MenuGroupID = ${MenuGroupID}"></td>
			    </tr>
				<tr>
				  <td width="5" >&nbsp;</td>
				  <td align="left"><img src="../${Icon}" align="absmiddle"/>&nbsp;${Name}</td>
			    </tr>
			  </table>
			</z:datagrid>
	  </td>
    </tr>
 </table>
</div>
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td height="5" colspan="2" align="center"></td>
    </tr>
    <tr>
      <td height="35" colspan="2" align="center"><input name="button" type="button" class="button1" id="button" value=" 保 存 "/>
        &nbsp;
      <input name="button2" type="button" class="button1" onClick="window.close();" value=" 返 回 "/></td>
    </tr>

</table>
</form>
</body>
</z:init>
</html>