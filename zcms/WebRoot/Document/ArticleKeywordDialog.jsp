<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<script src="../Framework/Main.js"></script>
<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function getSelectedKeyword(){
    return $NV("Keyword");
}

/**
选中已经有的关键词
**/
function setSelectedKeyword(keyword){
    if(keyword != ""){
	    var arrSelected = keyword.split(",");
		var arrKeys = $N("Keyword");
		for(var i = 0;i<arrKeys.length;i++){
		    for(var j = 0;j<arrSelected.length;j++){
			   if(arrKeys[i].value == arrSelected[j]){
			       arrKeys[i].checked = true;
			   }
			}
		}
	}
}
</script>
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
		<td>
		<div style="text-align:left; padding-left:25px;">关键词列表</div>
		</td>
	</tr>
	<tr>
		<td align="center">
		<div
			style='background-color:#f5fbfe; border:#c3ced9 1px solid; padding:4px 3px 10px 3px; overflow:auto;height:255px'>
		<table width="100%" border="0">
			<%
      ZCKeywordSchema keyword = new ZCKeywordSchema();
	  ZCKeywordSet set = keyword.query(new QueryBuilder(" where 1=1 order by id desc"));
	  for(int i = 0 ;i<set.size();i++){
	     keyword = set.get(i);
  %>
			<tr>
				<td align="left"><label for="<%=keyword.getID()%>"> <input
					name="Keyword" type="checkbox" value="<%=keyword.getKeyword()%>"
					id="<%=keyword.getID()%>"> <%=keyword.getKeyword()%> </label></td>
			</tr>
			<%
     }
  %>
		</table>
		</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>
