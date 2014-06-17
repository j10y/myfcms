package org.apache.solr.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.html.Entities;

public final class Search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4188118962602139881L;
	private String url = null;
	private HttpSolrServer solrServer = null;
	private Logger log = Logger.getLogger("Search");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/";

		solrServer = new HttpSolrServer(url);


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

		out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");

		out.flush();

		out.write("<html lang=\"zh-CN\">\r\n");
		out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
		out.write("\t<head>\r\n");
		out.write("\t\t<title>");
		out.print(q);
		out.write("_组工搜索</title>\r\n");
		out.write("\t\t<link rel=\"icon\" href=\"img/favicon.ico\" type=\"image/x-icon\" />\r\n");
		out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\r\n");
		out.write("\t\t<script type=\"text/javascript\">\r\n");
		out.write("\t\t\tfunction queryfocus() { document.search.q.focus(); }\r\n");
		out.write("\t\t</script>\r\n");
		out.write("\t</head>\r\n");
		out.write("\t<body onLoad=\"queryfocus();\">\r\n");
		out.write("\t\t<p style=\"height: 20px\" />\r\n");
		out.write("\t\t<nobr>\r\n");
		out.write("\t\t<form name=\"search\" class=\"fm\" action=\"./search\" method=\"get\">\r\n");
		out.write("\t\t\t<input type=\"hidden\" name=\"rows\" value=\"");
		out.print(rows);
		out.write("\">\r\n");
		out.write("\t\t\t<span class=\"bg s_ipt_wr\">\r\n");
		out.write("\t\t\t<input name=\"q\" maxlength=\"100\" \r\n");
		out.write("\t\t\tclass=\"s_ipt\" id=\"kw\" name=\"wd\" autocomplete=\"off\" value=\"");
		out.print(htmlQueryString);
		out.write("\"></span>\r\n");
		out.write("\t\t\t<span class=\"bg s_btn_wr\">\r\n");
		out.write("\t\t\t<input type=\"submit\" id=\"su\" value=\"搜索一下\" \r\n");
		out.write("\t\t\tclass=\"bg s_btn\" onmousedown=\"this.className='bg s_btn s_btn_h'\"\r\n");
		out.write("\t\t\t\t\t\t\t onmouseout=\"this.className='bg s_btn'\">\r\n");
		out.write("\t\t\t</span>\r\n");
		out.write("\t\t</form>\r\n");
		out.write("\t\t</nobr>\r\n");
		out.write("\t\t");

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
		int end = Math.min(start + rows, start + docs.size());
		int length = Math.min(rows, docs.size());

		log.warn("requestfrom: " + request.getRemoteAddr());
		log.warn("queryString: " + q);
		log.warn("NumFound:    " + docs.getNumFound());

		out.write("\r\n");
		out.write("\t\t第<b>");
		out.print(new Long((end == 0) ? 0 : (start + 1)));
		out.write('-');
		out.print(new Long(end));
		out.write("</b>项(共有");
		out.print(new Long(docs.getNumFound()));
		out.write("项搜索结果):\r\n");
		out.write("\t\t");

		// be responsive
		out.flush();

		out.write("<br>\r\n");
		out.write("\t\t<br>\r\n");
		out.write("\t\t<div align=\"left\" style=\"width: 640px;\">\r\n");
		out.write("\t\t\t\t\t");

		for (int i = 0; i < length; i++) { // display the
			// hits
			SolrDocument doc = docs.get(i);

			if (String.valueOf(doc.getFieldValue("content")).length() > 500) {
				doc.setField("content", String.valueOf(doc.getFieldValue("content")).substring(0, 500));
			}

			String id = String.valueOf(doc.get("id"));
			Map<String, List<String>> highlight = hightlightMap.get(id);
			if (null != highlight) {
				if (null != highlight.get("title"))
					doc.setField("title", StringUtils.join(highlight.get("title"), ""));
				if (null != highlight.get("content"))
					doc.setField("content", StringUtils.join(highlight.get("content"), ""));
			}

			String url = String.valueOf(doc.get("url"));
			String title = String.valueOf(doc.get("title"));
			String summary = String.valueOf(doc.get("content"));

			boolean showSummary = true;

			if (StringUtils.isBlank(title) || title.equals("null")) {
				title = url;
			}

			out.write("<b><a href=\"");
			out.print(url);
			out.write("\" target=\"_blank\">");
			out.print(title);
			out.write("</a>\r\n");
			out.write("\t\t\t\t\t</b>\r\n");
			out.write("\t\t\t\t\t");

			if (!"".equals(summary) && showSummary) {

				out.write("<br><font style=\"line-height: 150%;font-size: 14px;\">");
				out.print(summary);
				out.write("</font>\r\n");
				out.write("\t\t\t\t\t");

			}

			out.write("<br>\r\n");
			out.write("\t\t\t\t\t<font style=\"line-height: 150%;font-size: 14px;\">\r\n");
			out.write("\t\t\t\t\t<span class=\"url\">");
			out.print(Entities.encode(url));
			out.write("</span>\r\n");
			out.write("\t\t\t\t\t(<a target=\"_blank\" href=\"./cached?id=");
			out.print(id);
			out.write("\">网页快照</a>)\r\n");
			out.write("\t\t\t\t\t");
			out.write("</font>\r\n");
			out.write("\t\t\t\t\t<br>\r\n");
			out.write("\t\t\t\t\t<br>\r\n");
			out.write("\t\t\t\t\t");

		}

		out.write("<table align=\"left\" >\r\n");
		out.write("\t\t\t<tr>\r\n");
		out.write("\t\t\t\t<td>\r\n");
		out.write("\t\t\t\t\t");

		// 显示上一页按钮
		if (start >= rows) {

			out.write("<form name=\"pre\" action=\"./search\" method=\"get\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"start\" value=\"");
			out.print(start - rows);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"rows\" value=\"");
			out.print(rows);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"q\" value=\"");
			out.print(htmlQueryString);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"&lt;上一页\">\r\n");
			out.write("\t\t\t\t\t\t");

		}

		out.write("</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t");

		// 显示页码按钮
		int startnum = 1;
		// 页面中最前面的页码编号，我设定（满足）共10页，当页为第6页
		// 若果结果大于等于11页，每次显示11个按钮，当前按钮在最中间，即第6个
		if ((int) (start / rows) >= 5)
			startnum = (int) (start / rows) - 4;
		for (int i = rows * (startnum - 1), j = 0; i <= docs.getNumFound() && j <= 10;) {

			if (docs.getNumFound() % 10 == 0 && i == docs.getNumFound())
				break; // 搜索结果为整数的时候处理

			out.write("<td>\r\n");
			out.write("\t\t\t\t\t<form name=\"next\" action=\"./search\" method=\"get\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"start\" value=\"");
			out.print(i);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"rows\" value=\"");
			out.print(rows);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"q\" value=\"");
			out.print(htmlQueryString);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t");

			if (i / rows + 1 == new Long((end == 0) ? 0 : (start / 10 + 1))) {

				out.write("<span>&nbsp;");
				out.print(i / rows + 1);
				out.write("&nbsp;</span>\r\n");
				out.write("\t\t\t\t\t\t");

			} else {

				out.write("<input style=\"width: 30px\" type=\"submit\"\r\n");
				out.write("\t\t\t\t\t\t\tvalue=\"");
				out.print(i / rows + 1);
				out.write("\" />\r\n");
				out.write("\t\t\t\t\t\t");

			}

			out.write("</form>\r\n");
			out.write("\t\t\t\t</td>\r\n");
			out.write("\t\t\t\t");

			i = i + rows;
			j++;
		}// for

		out.write("<td>\r\n");
		out.write("\t\t\t\t\t");

		// 显示下一页按钮
		if ((end < docs.getNumFound()) // more hits to show
				|| ((docs.size() > start + rows))) {

			out.write("<form name=\"next\" action=\"./search\" method=\"get\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"start\" value=\"");
			out.print(end);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"rows\" value=\"");
			out.print(length);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"q\" value=\"");
			out.print(htmlQueryString);
			out.write("\">\r\n");
			out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"下一页&gt;\">\r\n");
			out.write("\t\t\t\t\t</form>\r\n");
			out.write("\t\t\t\t\t");

		}

		out.write("</td>\r\n");
		out.write("\t\t\t</tr>\r\n");
		out.write("\t\t</table>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\t</body>\r\n");
		out.write("</html>\r\n");
		out.flush();
        out.close();
	}

}
