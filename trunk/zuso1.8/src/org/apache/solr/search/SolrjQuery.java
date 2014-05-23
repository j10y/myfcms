package org.apache.solr.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrjQuery extends HttpServlet {

	private String url = "http://127.0.0.1:8080/zusoweb/";

	private HttpSolrServer solrServer = null;
	private final static String ASC = "asc";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String q = req.getParameter("q");
		if (q == null)
			q = "我省工业投资去年接近万亿";

		int start = 11; // first hit to display
		String startString = req.getParameter("start");
		if (startString != null)
			start = Integer.parseInt(startString);

		int rows = 10; // number of hits to display
		String rowsString = req.getParameter("rows");
		if (rowsString != null)
			rows = Integer.parseInt(rowsString);

		SolrQuery query = new SolrQuery();
		// 设置搜索字段

		query.setQuery(q);
		query.setStart(start);
		query.setRows(rows);
		query.setHighlight(true);
		query.addHighlightField("title");
		query.addHighlightField("content");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		query.setHighlightFragsize(70);
		query.setHighlightSnippets(3);

		try {
			QueryResponse qrsp = solrServer.query(query);
			SolrDocumentList docs = qrsp.getResults();
			Map<String, Map<String, List<String>>> hightlightMap = qrsp.getHighlighting();

			PrintWriter out = resp.getWriter();

			int end = (int) Math.min(docs.size(), start + rows);
			int length = end - start;
			int realEnd = (int) Math.min(docs.size(), start);

			

			for (int i = 0; i < docs.size(); i++) {
				SolrDocument doc = docs.get(i);

				String id = String.valueOf(doc.get("id"));
				Map<String, List<String>> highlight = hightlightMap.get(id);

				if (null != highlight) {
					if (null != highlight.get("title"))
						doc.setField("title", StringUtils.join(highlight.get("title"), ""));
					if (null != highlight.get("content"))
						doc.setField("content", StringUtils.join(highlight.get("content"), ""));
				}

				String url = doc.getFieldValue("url").toString();
				String title = doc.getFieldValue("title").toString();
				String summary = doc.getFieldValue("content").toString();

				System.out.println("title:" + title + "   summary:" + summary + "    url:" + url);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() throws ServletException {
		super.init();
		solrServer = new HttpSolrServer(url);
	}

}
