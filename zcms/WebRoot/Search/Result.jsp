<%@ page session="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" 
	import="com.zving.search.*"
	import="com.zving.cms.api.*" 
	import="com.zving.framework.*"
	import="com.zving.framework.utility.*"
	import="com.zving.framework.data.*"
%>
<%@ taglib uri="controls" prefix="z"%>
<%
	request.setCharacterEncoding(Constant.GlobalCharset);
    response.setContentType("text/html;charset=utf-8"); 
	
	String keyword = request.getParameter("keyword");
	String query = request.getParameter("query");
	if (StringUtil.isEmpty(query)) {
		query = keyword;//和旧版本保持一致
	}
	query = StringUtil.isEmpty(query)?"":query;

	String strpage = request.getParameter("page");
	strpage = StringUtil.isEmpty(strpage)?"1":strpage;
	
	String order = request.getParameter("order");
	order = StringUtil.isEmpty(order)?"rela":order;

	String time = request.getParameter("time");
	time = StringUtil.isEmpty(time)?"all":time;
	
	String site = request.getParameter("site");
	site = StringUtil.isEmpty(site)?"":site;

	String id = request.getParameter("id");
	id  = StringUtil.isEmpty(id)?"":id; 

	String size = request.getParameter("size");
	size  = StringUtil.isEmpty(size)?"10":size; 
	
	int pageSize = Integer.parseInt(size);
	int pageIndex = Integer.parseInt(strpage) - 1;
	
	Mapx map = ServletUtil.getParameterMap(request);
	SearchResult r = ArticleSearcher.search(map);
	if(r==null){
		out.println("<br><br><br><b><font color='red'>全文检索索引未建立。</font></b>");
		out.println("<br><br>请在“站点管理”-“站点列表”中选择相应站点，开启“自动建立索引”选项。");
		return;
	}
	DataTable dt = r.Data;
	int total = r.Total;
	double usedTime = r.UsedTime;
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>搜索结果_<%=query%></title>
<style>
<!--
body {font-size: 12px; font-family: "宋体";	background: #FFFFFF; margin: 0px; padding: 0px;	color: #444;}
a:link {color: #0066CC;	text-decoration: underline;}
a:visited {color: #0066CC; text-decoration: underline;}
a:hover {color: #CC0000; text-decoration: none;}
td {font-size: 12px; line-height: 18px;	color: #444;}
.f14 {font-size: 14px}
.f10 {font-size: 10.5pt}
.f16 {font-size: 16px; font-family: Arial}
.c {color: #7777CC;}
.p1 {line-height: 120%;	margin-left: -12pt}
.p2 {width: 100%; line-height: 120%;	margin-left: -12pt}
.i {font-size: 16px}
.t {color: #0000cc;text-decoration: none}
a.t:hover {text-decoration: underline}
.p {padding-left: 18px;	font-size: 14px; word-spacing: 4px;}
.f {line-height: 120%; font-size: 100%;	width: 32em; padding-left: 15px; word-break: break-all;	word-wrap: break-word;}
.h {margin-left: 8px; width: 100%}
.s {width: 8%; padding-left: 10px; height: 25px;}
.m,a.m:link {color: #666666; font-size: 100%;}
a.m:visited {color: #660066;}
.g {color: #008000;	font-size: 12px;}
.r {word-break: break-all; cursor: hand; width: 225px;}
.bi {margin-bottom: 12px}
.pl {padding-left: 3px;	height: 8px; padding-right: 2px; font-size: 14px;}
.Tit {height: 21px;	font-size: 14px;}
.fB {font-weight: bold;}
.mo,a.mo:link,a.mo:visited {color: #666666;	font-size: 100%; line-height: 10px;}
.htb {margin-bottom: 5px;}
#ft {clear: both; background: #E6E6E6; text-align: center; color: #777;	font-size: 12px; font-family: Arial; line-height: 24px;	width: 100%;}
#ft span {color: #666}
.g1 {margin-top: 1em; margin-bottom: 0em;}
.l {font-size: 16px; font-family: Verdana, Geneva, sans-serif;}
.j {line-height: 22px}
#mleft {float: left;}
.STYLE13 {color: #336600;}
-->
</style>
<script>
var query = "<%=query%>";
var order = "<%=order%>";
var time = "<%=time%>";
var site = "<%=site%>";
var id = "<%=id%>";
function searchInResult(){
	if(document.getElementById("query").value!=query){
		document.getElementById("query").value  = query+" "+document.getElementById("query").value;
	}
	document.getElementById("search").submit();
}
function searchInResult2(){
	if(document.getElementById("query2").value!=query){
		document.getElementById("query2").value  = query+" "+document.getElementById("query2").value;
	}
	document.getElementById("search2").submit();
}
</script>
</head>
<body link="#261CDC">
<table width="100%" height="71" align="center" cellpadding="0" cellspacing="0">
<form id="search">
	<tr valign=middle>
		<td width="200" align="center" valign="middle" nowrap style="padding-left: 10px;">
		<img src="tit.gif" width="200" height="50" alt="搜索结果"></td>
		<td>&nbsp;</td>
		<td width="100%" valign="middle">
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" nowrap>
					<input name=query class="i" id="query" value="<%=query%>" size="35" maxlength="100">
					<input name="site" type="hidden" id="site" value="<%=site%>">
					<input name="id" type="hidden" id="id" value="<%=id%>">
					<input type="submit" value="搜索一下">
					<input type="button" value="结果中找" onClick="searchInResult()">&nbsp;&nbsp;&nbsp;
				</td>
				<td valign="middle" nowrap>
					<a href=# target="_blank">帮助</a>&nbsp;|&nbsp;
					<a href="AdvanceResult.jsp?query=<%=query%>&site=<%=site%>">高级搜索</a>				</td>
			</tr>
		</table>
		</td>
		<td></td>
	</tr>
</form>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="bi">
	<tr>
		<td width="81%" valign="bottom" nowrap>
		<div id=div1
			style="background-color: #eee; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd; CLEAR: right; Z-INDEX: 20; FLOAT: left; WIDTH: 100%; HEIGHT: 24px; FONT-SIZE: 13px;">
		<div style="padding: 0 1em; border-top: 1px solid #fff;"><span
			style="float: right;">约有<b><%=total%></b>项符合搜索<b> <%=query%>
		</b>的查询结果，耗时<%=usedTime%>秒，以下是第<b><%=(pageIndex * pageSize + 1)%></b>-<b><%=((pageIndex + 1) * pageSize)%></b>项。</span>
		</div>
		</div>
		</td>
	</tr>
</table>
<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff"	align="right" width="26%">
	<tbody>
		<tr>
			<td>&nbsp;</td>
			<td bgcolor="#cccccc" width="1"><img height="1" width="1" alt="" /></td>
			<td>&nbsp;</td>
			<td>
			<div style="line-height: 25px; margin-right: 10px">
			<p><b>排序方式</b><br />
			<nobr>
			<%=SearchAPI.getURL(request, "相关度", "order", "rela")%>
			<%=SearchAPI.getURL(request, "发布时间", "order", "time")%>
			</nobr></p>
			<p><b>按时间筛选结果</b><br />
			<nobr>
			<%=SearchAPI.getURL(request, "一周内", "time", "week")%>
			<%=SearchAPI.getURL(request, "一月内", "time", "month")%>
			<%=SearchAPI.getURL(request, "三月内", "time", "quarter")%>
			<%=SearchAPI.getURL(request, "全部", "time", "all")%>
			</nobr></p>
			</div>
			</td>
		</tr>
	</tbody>
</table>
<div id="mleft" style="width: 700px; padding: 20px;padding-top:0px;">
<div><span class="f14">搜索<span class="STYLE13"> 
<%
 	if ("week".equals(time)) {
 		out.println("一周内");
 	}
 	if ("month".equals(time)) {
 		out.println("一月内");
 	}
 	if ("quarter".equals(time)) {
 		out.println("三月内");
 	}
 	if ("all".equals(time)) {
 		out.println("全部");
 	}
 %> 
 </span> 的文章，按<span class="STYLE13"> 
 <%
 	if ("time".equals(order)) {
 		out.println("发布时间");
 	}
 	if ("rela".equals(order)) {
 		out.println("相关度");
 	}
 %>
</span>排序</span>：
 <%
	for (int i = 0; i < dt.getRowCount(); i++) {
		DataRow dr = dt.getDataRow(i);
%>
	<p class=g1><a class=l href="<%=dr.getString("URL")%>" target=_blank><%=dr.getString("Title")%></a>
	<table cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td class=j><span style="font-size: 13px;"><%=dr.getString("Content")%>...<br>
			<span style="color: #875"><%=dr.getString("URL")%> - <%=dr.getString("PublishDate")%></span></span></td>
		</tr>
	</table>
<%
	}
%>
</div>
</div>
<br clear=all>
<table style="margin-left: 20px;">
	<tr>
		<td nowrap><span style="font-size: 13px;">结果页码:&nbsp;</span></td>
		<%
			int pageCount = new Double(Math.ceil(total * 1.0 / pageSize)).intValue();
			int start = pageIndex - 9 < 1 ? 1 : pageIndex - 9;
			int end = pageIndex + 9 > pageCount ? pageCount : pageIndex + 9;
			if (pageIndex > 1) {
				out.println(" <td nowrap><a href='"+ SearchAPI.getPageURL(request, pageIndex)+ "'>上一页</a></td>");
			}
			for (int i = start; i <= end; i++) {
				if (i == pageIndex + 1) {
					out.println("<td nowrap><span class=i>" + i	+ "</span></td>");
				} else {
					out.println("<td nowrap><a href='"+ SearchAPI.getPageURL(request, i) + "'>[" + i+ "]</a></td>");
				}
			}
			if (pageIndex < pageCount - 1) {
				out.println(" <td nowrap><a href='"+ SearchAPI.getPageURL(request, pageIndex + 2)+ "'>下一页</a></td>");
			}
		%>
	</tr>
</table>
<form id="search2">
<table cellpadding="0" cellspacing="0" style="margin-left: 20px; height: 60px;">
	<tr valign="middle">
		<td nowrap>
			<input name="query" id="query2" size="35" class="i" value="<%=query%>" maxlength=100>
			<input name="site" type="hidden" id="site2" value="<%=site%>">
			<input name="id" type="hidden" id="id2" value="<%=id%>">
			<input type="submit" value="搜索一下">
			<input type="button" value="结果中找" onClick="searchInResult2()"> &nbsp;&nbsp;&nbsp;
		</td>
		<td nowrap></td>
	</tr>
</table>
</form>
<div id="ft">&copy;2006-2009 www.zving.com <span>此内容系ZCMS根据您的指令自动搜索的结果.</span></div>
<script src="<%=Config.getContextPath()%>Services/Stat.js" type="text/javascript"></script>
<script type="text/javascript">
_zcms_stat("SiteID=<%=site%>&Type=Article&Dest=<%=Config.getContextPath()%>Services/Stat.jsp");
</script>
</body>
</html>
