<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.*" import="java.util.*"
	import="java.net.*" import="javax.servlet.http.*"
	import="javax.servlet.*" import="org.apache.nutch.html.Entities"
	import="org.apache.nutch.metadata.Nutch"
	import="org.apache.nutch.searcher.*" import="org.apache.nutch.plugin.*"
	import="org.apache.nutch.clustering.*"
	import="org.apache.hadoop.conf.*"
	import="org.apache.nutch.util.NutchConfiguration"%><%!/**
	 * Number of hits to retrieve and cluster if clustering extension is available
	 * and clustering is on. By default, 100. Configurable via nutch-conf.xml.
	 */
	private int HITS_TO_CLUSTER;

	/**
	 * Maximum hits per page to be displayed.
	 */
	private int MAX_HITS_PER_PAGE;

	/**
	 * An instance of the clustering extension, if available.
	 */
	private OnlineClusterer clusterer;

	/**
	 * Nutch configuration for this servlet.
	 */
	private Configuration nutchConf;

	/**
	 * Initialize search bean.
	 */
	public void jspInit() {
		//super.jspInit();

		final ServletContext application = getServletContext();
		nutchConf = NutchConfiguration.get(application);
		HITS_TO_CLUSTER = nutchConf.getInt("extension.clustering.hits-to-cluster", 100);
		MAX_HITS_PER_PAGE = nutchConf.getInt("searcher.max.hits.per.page", -1);

		try {
			clusterer = new OnlineClustererFactory(nutchConf).getOnlineClusterer();
		} catch (PluginRuntimeException e) {
			super.log("Could not initialize online clusterer: " + e.toString());
		}
	}%>

<%--
// Uncomment this to enable query refinement.
// Do the same to "refine-query.jsp" below.,
<%@ include file="./refine-query-init.jsp" %>
--%>

<%
	// The Nutch bean instance is initialized through a ServletContextListener 
	// that is setup in the web.xml file
	NutchBean bean = NutchBean.get(application, nutchConf);
	// set the character encoding to use when interpreting request values 
	request.setCharacterEncoding("UTF-8");

	bean.LOG.info("query request from " + request.getRemoteAddr());

	// get query from request
	String queryString = request.getParameter("query");
	if (queryString == null)
		queryString = "";
	String htmlQueryString = Entities.encode(queryString);

	// a flag to make the code cleaner a bit.
	boolean clusteringAvailable = (clusterer != null);

	String clustering = "";
	if (clusteringAvailable && "yes".equals(request.getParameter("clustering")))
		clustering = "yes";

	int start = 0; // first hit to display
	String startString = request.getParameter("start");
	if (startString != null)
		start = Integer.parseInt(startString);

	int hitsPerPage = 10; // number of hits to display
	String hitsString = request.getParameter("hitsPerPage");
	if (hitsString != null)
		hitsPerPage = Integer.parseInt(hitsString);
	if (MAX_HITS_PER_PAGE > 0 && hitsPerPage > MAX_HITS_PER_PAGE)
		hitsPerPage = MAX_HITS_PER_PAGE;

	int hitsPerSite = 0; // max hits per site
	String hitsPerSiteString = request.getParameter("hitsPerSite");
	if (hitsPerSiteString != null)
		hitsPerSite = Integer.parseInt(hitsPerSiteString);

	String sort = request.getParameter("sort");
	boolean reverse = sort != null && "true".equals(request.getParameter("reverse"));

	String params = "&hitsPerPage=" + hitsPerPage
			+ (sort == null ? "" : "&sort=" + sort + (reverse ? "&reverse=true" : ""));

	int hitsToCluster = HITS_TO_CLUSTER; // number of hits to cluster

	// get the lang from request
	String queryLang = request.getParameter("lang");
	if (queryLang == null) {
		queryLang = "";
	}
	Query query = Query.parse(queryString, queryLang, nutchConf);
	query =  Query.parse(query.toString().replace("\"", ""),queryLang, nutchConf);
	bean.LOG.info("queryString: " + queryString);
	bean.LOG.info("query: " + query);
	bean.LOG.info("lang: " + queryLang);

	String language = "zh";
	// ResourceBundle.getBundle("org.nutch.jsp.search", request.getLocale())
	// .getLocale().getLanguage();
	String requestURI = HttpUtils.getRequestURL(request).toString();
	//String base = requestURI.substring(0, requestURI.lastIndexOf('/'));
	String rss = "../opensearch?query=" + htmlQueryString + "&hitsPerSite=" + hitsPerSite + "&lang=" + queryLang + params;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	// To prevent the character encoding declared with 'contentType' page
	// directive from being overriden by JSTL (apache i18n), we freeze it
	// by flushing the output buffer. 
	// see http://java.sun.com/developer/technicalArticles/Intl/MultilingualJSP/
	out.flush();
%>
<html lang="zh-CN">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<head>
		<title><%=queryString%>_组工搜索</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
		<link rel="alternate" type="application/rss+xml" title="RSS" href="<%=rss%>" />
		


		<link rel="stylesheet" type="text/css" href="include/style2.css" />

		<script type="text/javascript">
<!--
function queryfocus() { document.search.query.focus(); }
// -->
		</script>
		<style id="style_super_inline">
.bg {
	background-image:
		url(./img/icons_37d13939.png);
	background-repeat: no-repeat;
	_background-image:
		url(./img/icons_5e61a51c.gif);
	background-repeat: no-repeat
}

.s_ipt_wr {
	width: 536px;
	height: 30px;
	display: inline-block;
	margin-right: 5px;
	background-position: 0 -96px;
	border: 1px solid #b6b6b6;
	border-color: #7b7b7b #b6b6b6 #b6b6b6 #7b7b7b;
	vertical-align: top
}

.s_ipt {
	width: 523px;
	height: 22px;
	font: 16px/ 22px arial;
	margin: 5px 0 0 7px;
	padding: 0;
	background: #fff;
	border: 0;
	outline: 0;
	-webkit-appearance: none
}

.s_btn {
	width: 95px;
	height: 32px;
	padding-top: 2px\9;
	font-size: 14px;
	padding: 0;
	background-color: #ddd;
	background-position: 0 -48px;
	border: 0;
	cursor: pointer
}

.s_btn_h {
	background-position: -240px -48px
}

.s_btn_wr {
	width: 97px;
	height: 34px;
	display: inline-block;
	background-position: -120px -48px; *
	position: relative;
	z-index: 0;
	vertical-align: top
}
#u,#head,#tool,#search,#foot {
	font-size: 12px
}
#foot {
	height: 20px;
	line-height: 20px;
	color: #77c;
	background: #e6e6e6;
	text-align: center
}

#foot span {
	color: #666
}
</style>



	</head>

	<body onLoad="queryfocus();">
		<%--
<jsp:include page="include/header.html"/>
 --%>
		<p style="height: 20px" />
		<form name="search" class="fm" action="./search.jsp" method="get">
			<span class="bg s_ipt_wr"><input name="query" maxlength="100" class="s_ipt" id="kw" name="wd" autocomplete="off" value="<%=htmlQueryString%>"></span>
			<input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
			<input type="hidden" name="lang" value="<%=language%>">
			<span class="bg s_btn_wr"><input type="submit" id="su" value="搜索一下" class="bg s_btn" onmousedown="this.className='bg s_btn s_btn_h'"
							onmouseout="this.className='bg s_btn'">
			</span>
		</form>

		<%--
// Uncomment this to enable query refinement.
// Do the same to "refine-query-init.jsp" above.
<%@ include file="./refine-query.jsp" %>
--%>

		<%
			// how many hits to retrieve? if clustering is on and available,
			// take "hitsToCluster", otherwise just get hitsPerPage
			int hitsToRetrieve = (clusteringAvailable && clustering.equals("yes") ? hitsToCluster : hitsPerPage);

			if (clusteringAvailable && clustering.equals("yes")) {
				bean.LOG.info("Clustering is on, hits to retrieve: " + hitsToRetrieve);
			}

			// perform query
			// NOTE by Dawid Weiss:
			// The 'clustering' window actually moves with the start
			// position.... this is good, bad?... ugly?....
			Hits hits;
			try {
				query.getParams().initFrom(start + hitsToRetrieve, hitsPerSite, "site", sort, reverse);
				hits = bean.search(query);
			} catch (IOException e) {
				hits = new Hits(0, new Hit[0]);
			}
			int end = (int) Math.min(hits.getLength(), start + hitsPerPage);
			int length = end - start;
			int realEnd = (int) Math.min(hits.getLength(), start + hitsToRetrieve);

			Hit[] show = hits.getHits(start, realEnd - start);
			HitDetails[] details = bean.getDetails(show);
			Summary[] summaries = bean.getSummary(details, query);
			bean.LOG.info("total hits: " + hits.getTotal());
		%>

		第<b><%=new Long((end == 0) ? 0 : (start + 1))%>-<%=new Long(end)%></b>项(共有<%=new Long(hits.getTotal())%>项搜索结果):
		<%
			// be responsive
			out.flush();
		%>

		<br>
		<br>
		
		<div align="left" style="width: 640px;">
					<%
						for (int i = 0; i < length; i++) { // display the hits
							Hit hit = show[i];
							HitDetails detail = details[i];
							String title = detail.getColValue("title",query); 
							String url = detail.getValue("url");
							String id = "idx=" + hit.getIndexNo() + "&id=" + hit.getUniqueKey();
							String summary = summaries[i].toHtml(false);
							String caching = detail.getValue("cache");
							boolean showSummary = true;
							boolean showCached = true;
							if (caching != null) {
								showSummary = !caching.equals(Nutch.CACHING_FORBIDDEN_ALL);
								showCached = !caching.equals(Nutch.CACHING_FORBIDDEN_NONE);
							}

							if (title == null || title.equals("")) { // use url for docs w/o title
								title = url;
							}
					%>
					<b><a href="<%=url%>" target="_blank"><%=title%></a>
					</b>
					<%@ include file="more.jsp"%>
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
					<%
						if (showCached) {
					%>(<a target="_blank" href="./cached.jsp?<%=id%>">网页快照</a>)
					<%
						}
					%>
					</font>
					<!-- 
    (<a href="../explain.jsp?<%=id%>&query=<%=URLEncoder.encode(queryString, "UTF-8")%>&lang=<%=queryLang%>">explain</a>)
    (<a href="../anchors.jsp?<%=id%>">anchors</a>)
    -->
					<%
						if (hit.moreFromDupExcluded()) {
								String more = "query=" + URLEncoder.encode("site:" + hit.getDedupValue() + " " + queryString, "UTF8")
										+ params + "&hitsPerSite=" + 0 + "&lang=" + queryLang + "&clustering=" + clustering;
					%>
					(
					<a href="./search.jsp?<%=more%>">moreFrom <%=hit.getDedupValue()%></a>)
					<%
						}
					%>
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
						if (start >= hitsPerPage) {
					%>
					<form name="pre" action="./search.jsp" method="get">
						<input type="hidden" name="query" value="<%=htmlQueryString%>">
						<input type="hidden" name="lang" value="<%=queryLang%>">
						<input type="hidden" name="start" value="<%=start - hitsPerPage%>">
						<input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
						<input type="hidden" name="hitsPerSite" value="<%=hitsPerSite%>">
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
						if ((int) (start / hitsPerPage) >= 5)
							startnum = (int) (start / hitsPerPage) - 4;
						for (int i = hitsPerPage * (startnum - 1), j = 0; i <= hits.getTotal() && j <= 10;) {
							if (hits.getTotal() % 10 == 0 && i == hits.getTotal())
								break; //搜索结果为整数的时候处理
					%>
				
				<td>

					<form name="next" action="./search.jsp" method="get">
						<input type="hidden" name="query" value="<%=htmlQueryString%>">
						<input type="hidden" name="lang" value="<%=queryLang%>">
						<input type="hidden" name="start" value="<%=i%>">
						<input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
						<input type="hidden" name="hitsPerSite" value="<%=hitsPerSite%>">
						<%
							if (i / hitsPerPage + 1 == new Long((end == 0) ? 0 : (start / 10 + 1))) {
						%>
						<span>&nbsp;<%=i / hitsPerPage + 1%>&nbsp;</span>
						<%
							} else {
						%>
						<input style="width: 30px" type="submit"
							value="<%=i / hitsPerPage + 1%>" />
						<%
							}
						%>
					</form>
				</td>
				<%
					i = i + 10;
						j++;
					}//for
				%>
				<td>
					<%
						//显示下一页按钮
						if ((hits.totalIsExact() && end < hits.getTotal()) // more hits to show
								|| (!hits.totalIsExact() && (hits.getLength() > start + hitsPerPage))) {
					%>

					<form name="next" action="./search.jsp" method="get">
						<input type="hidden" name="query" value="<%=htmlQueryString%>">
						<input type="hidden" name="lang" value="<%=queryLang%>">
						<input type="hidden" name="start" value="<%=end%>">
						<input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
						<input type="hidden" name="hitsPerSite" value="<%=hitsPerSite%>">
						<input type="submit" value="下一页&gt;">
					</form>
					<%
						}
					%>
					<%-- 
         <span>为您找到结果共约 <%=new Long(hits.getTotal())%> 条结果</span> --%>
				</td>
			</tr>
		</table>
		</div>
		<%---------------------------------------------------%>
<!--  
		<div id="search">
			<form name="search" class="fm" action="./search.jsp" method="get">
				<span class="bg s_ipt_wr"><input name="query" maxlength="100" class="s_ipt" id="kw" name="wd" autocomplete="off" value="<%=htmlQueryString%>"></span>
				<input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
				<input type="hidden" name="lang" value="<%=language%>">
				<span class="bg s_btn_wr"><input type="submit" id="su" value="搜索一下" class="bg s_btn" onmousedown="this.className='bg s_btn s_btn_h'"
								onmouseout="this.className='bg s_btn'">
				</span>
			</form>

		</div>

		<div id="foot">
				
				<span>中共黄冈市委组织部信息管理科 ©2013</span>
		</div>
	-->	
	</body>
</html>
