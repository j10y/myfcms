package org.apache.solr.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class Cached extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1434231215903629897L;

	private String url = null;
	private HttpSolrServer solrServer = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		solrServer = new HttpSolrServer(url);
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		SolrQuery query = new SolrQuery();
		query.setQuery("id" + ":\"" + id + "\"");
		QueryResponse qrsp = null;
		SolrDocumentList docs = null;
		SolrDocument doc = null;

		try {
			qrsp = solrServer.query(query);
			docs = qrsp.getResults();
			doc = docs.get(0);
		} catch (Exception e) {
			doc = new SolrDocument();
		}
		String content = String.valueOf(doc.getFieldValue("cached"));

		out.write("<!---->\r\n");
		out.write("<base href=\"");
		out.print(id);
		out.write("\">\r\n");
		out.write("<link rel=\"shortcut icon\" href=\"img/favicon.ico\" type=\"image/x-icon\"/>\r\n");
		out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");

		out.flush();

		out.write("<h2 style=\"{color: rgb(255, 153, 0)}\">网页快照</h2>\r\n");
		out.write("<h3>\r\n");
		out.print(id);
		out.write("</h3>\r\n");
		out.write("<hr>\r\n");
		if (!content.isEmpty() && !content.equals("null")) {
			out.print(content);
		} else {
			out.print("");
		}

	}

}
