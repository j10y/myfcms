<script>
Page.onLoad(function(){
	var path = window.location.pathname;
	path = path.substring(path.lastIndexOf("/")+1);
	Tree.select("tree1","target",path);
});

function onTreeClick(){
	var target = $(Tree.CurrentItem).$A("target");
	if(target){
		window.location = target;
	}
}

function showTrend(type,subtype,item){
	var diag = new Dialog('TrendDialog');
	diag.URL = "Stat/TrendDialog.jsp?Type="+type+"&SubType="+subtype+"&Item="+htmlEncode(item);
	diag.Width = 720;
	diag.Height = 330;
	diag.Title = "时间趋势";
	diag.show();

}
</script>
<table width="160" border="0" cellspacing="0" cellpadding="6"
	class="blockTable">
	<tr>
		<td style="padding:6px;" class="blockTd"><z:tree id="tree1"
			style="width:150px;"
			method="com.zving.cms.stat.report.SummaryReport.treeDataBind"
			level="3">
			<p cid='${ID}' target='${Target}' onClick="onTreeClick();">${Name}</p>
		</z:tree></td>
	</tr>
</table>
