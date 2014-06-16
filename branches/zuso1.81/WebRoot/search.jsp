<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.io.*" 
	import="java.util.*"
	import="java.net.*" 
	import="javax.servlet.http.*"
	import="javax.servlet.*" 
	import="org.apache.solr.html.Entities"
	import="org.apache.solr.client.solrj.SolrQuery"
	import="org.apache.solr.client.solrj.impl.HttpSolrServer"
	import="org.apache.solr.client.solrj.response.QueryResponse"
	import="org.apache.solr.common.SolrDocument"
	import="org.apache.solr.common.SolrDocumentList"
	import="org.apache.commons.lang.StringUtils"
	%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import="org.apache.lucene.search.Query"%>
<%@page import="org.apache.solr.client.solrj.SolrQuery.ORDER"%>
	<%!
	
	private String url = null;
	private HttpSolrServer solrServer = null;
	private Logger log = Logger.getLogger("Search");
	
	public void jspInit() {

		
	}%>
<%

    url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    
    solrServer = new HttpSolrServer(url);
    
	request.setCharacterEncoding("UTF-8");

	String q = request.getParameter("q");
	if (q == null)
		q = "";
	String htmlQueryString = Entities.encode(q);

	int start = 0; // first hit to display
	String startString = request.getParameter("start");
	if (startString != null)
		start = Integer.parseInt(startString);

	int rows = 10; // number of hits to display
	String rowsString = request.getParameter("rows");
	if (rowsString != null)
		rows = Integer.parseInt(rowsString);

	SolrQuery query = new SolrQuery();
	query.setQuery(q);
	query.setStart(start);
	query.setRows(rows);
	query.setParam("mm","2<-1 5<-2 6<90%");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	out.flush();
%>
<html lang="zh-CN">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<head>
		<title><%=q%>_组工搜索</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript">
			function queryfocus() { document.search.q.focus(); }
		</script>
	</head>
	<body onLoad="queryfocus();">
	<!-- 
	<p id="u_sp">
			<a href="./help.html" target="_blank">使用帮助</a>
			<a href="javascript:window.external.addFavorite('http://10.145.73.5/','组工搜索');">加入收藏</a>
	</p>
	 -->
	 <p id="lg" style="height: 40px"><a href="./" title="返回组工搜索首页"><img src="./img/sylogo.png" width="180" height="50"  style="border: 0px"></a></p>
	 <nobr>
		<form name="search" class="fm" action="./search.jsp" method="get">
			<input type="hidden" name="rows" value="<%=rows%>">
			<span class="bg s_ipt_wr">
			<input name="q" maxlength="100" 
			class="s_ipt" id="kw" name="wd" autocomplete="off" value="<%=htmlQueryString%>"></span>
			<span class="bg s_btn_wr">
			<input type="submit" id="su" value="搜索一下" 
			class="bg s_btn" onmousedown="this.className='bg s_btn s_btn_h'"
							 onmouseout="this.className='bg s_btn'">
			</span>
		</form>
		</nobr>
		<%
		    QueryResponse qrsp = null;
			SolrDocumentList docs = null;
			Map<String, Map<String, List<String>>> hightlightMap = null;
		
			try {
				qrsp = solrServer.query(query);
				docs = qrsp.getResults();
				hightlightMap = qrsp.getHighlighting();
			} catch (Exception e) {
				docs = new SolrDocumentList();
			}
			int end = Math.min(start+rows,start + docs.size());
			int length = Math.min(rows,docs.size());
			
			log.warn("requestfrom: " + request.getRemoteAddr());
			log.warn("queryString: " + q);
			log.warn("NumFound:    " + docs.getNumFound());
		%>
		第<b><%=new Long((end == 0) ? 0 : (start + 1))%>-<%=new Long(end)%></b>项(共有<%=new Long(docs.getNumFound())%>项搜索结果):
		<%
			// be responsive
			out.flush();
		%>
		<br>
		<br>
		<div align="left" style="width: 640px;">
					<%
						for (int i = 0; i < length; i++) { // display the hits
							SolrDocument doc = docs.get(i);
							
							String id = String.valueOf(doc.get("id"));
							Map<String, List<String>> highlight = hightlightMap.get(id);
							if(null != highlight){
								if(null != highlight.get("title"))							
									doc.setField("title",StringUtils.join(highlight.get("title"),""));
								if(null != highlight.get("content"))							
									doc.setField("content",StringUtils.join(highlight.get("content"),""));
							}
							
							String url = String.valueOf(doc.get("url"));
							String title = String.valueOf(doc.get("title")); 
							String summary = String.valueOf(doc.get("content"));
							
							boolean showSummary = true;
							boolean showCached = true;

							if (StringUtils.isBlank(title)||title.equals("null")) { // use url for docs w/o title
								title = url;
							}
							
							if(summary.length()>1100){
								summary = summary.substring(0,300);
							}
					%>
					<b><a href="<%=url%>" target="_blank"><%=title%></a>
					</b>
					<%-- 
					<%@ include file="more.jsp"%>
					--%>
					<%
						if (!"".equals(summary) && showSummary) {
					%>
					<br><font style="line-height: 150%;font-size: 14px;"><%=summary%></font>
					<%
						}
					%>
					<br>
					<font style="line-height: 150%;font-size: 14px;">
					<span class="url"><%=Entities.encode(url)%></span>
					(<a target="_blank" href="./cached.jsp?id=<%=id%>">网页快照</a>)
					<%-- 
					<br><%=doc.getFieldValue("score") %>
					--%>
					</font>
					<br>
					<br>
					<%
						}
					%>
		
		<%---------------------------------------------------%>
		<table align="left" >
			<tr>
				<td>
					<%
						// 显示上一页按钮
						if (start >= rows) {
					%>
					<form name="pre" action="./search.jsp" method="get">
						<input type="hidden" name="start" value="<%=start - rows%>">
						<input type="hidden" name="rows" value="<%=rows%>">
						<input type="hidden" name="q" value="<%=htmlQueryString%>">
						<input type="submit" value="&lt;上一页">
						<%
							}
						%>
					</form>

					<%
						//显示页码按钮
						int startnum = 1;
						//页面中最前面的页码编号，我设定（满足）共10页，当页为第6页
						//若果结果大于等于11页，每次显示11个按钮，当前按钮在最中间，即第6个
						if ((int) (start / rows) >= 5)
							startnum = (int) (start / rows) - 4;
						for (int i = rows * (startnum - 1), j = 0; i <= docs.getNumFound() && j <= 10;) {
							if (docs.getNumFound() % 10 == 0 && i == docs.getNumFound())
								break; //搜索结果为整数的时候处理
					%>
				
				<td>
					<form name="next" action="./search.jsp" method="get">
						<input type="hidden" name="start" value="<%=i%>">
						<input type="hidden" name="rows" value="<%=rows%>">
						<input type="hidden" name="q" value="<%=htmlQueryString%>">
						<%
							if (i / rows + 1 == new Long((end == 0) ? 0 : (start / 10 + 1))) {
						%>
						<span>&nbsp;<%=i / rows + 1%>&nbsp;</span>
						<%
							} else {
						%>
						<input style="width: 30px" type="submit"
							value="<%=i / rows + 1%>" />
						<%
							}
						%>
					</form>
				</td>
				<%
					i = i + rows;
						j++;
					}//for
				%>
				<td>
					<%
						//显示下一页按钮
						if (( end < docs.getNumFound()) // more hits to show
								|| ((docs.size() > start + rows))) {
					%>

					<form name="next" action="./search.jsp" method="get">
						<input type="hidden" name="start" value="<%=end%>">
						<input type="hidden" name="rows" value="<%=length%>">
						<input type="hidden" name="q" value="<%=htmlQueryString%>">
						<input type="submit" value="下一页&gt;">
					</form>
					<%
						}
					%>
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>
