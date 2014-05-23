<%@ page
	session="false"
	contentType="text/html; charset=UTF-8"
	import="java.io.*"
	import="java.util.*"
	import="org.apache.solr.html.Entities"
	import="org.apache.solr.client.solrj.SolrQuery"
	import="org.apache.solr.client.solrj.impl.HttpSolrServer"
	import="org.apache.solr.client.solrj.response.QueryResponse"
	import="org.apache.solr.common.SolrDocument"
	import="org.apache.solr.common.SolrDocumentList"
	import="org.apache.commons.lang.StringUtils"
 
%>
	<%!
	private String url = null;
	private HttpSolrServer solrServer = null;
	public void jspInit() {
		
	}%>
<%
	url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	solrServer = new HttpSolrServer(url);
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	
	SolrQuery query = new SolrQuery();
	query.setQuery("id"+":\""+id+"\"");
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
  String contentType =  "text/html";
  String content = String.valueOf(doc.getFieldValue("cached"));
%>
<!---->
<base href="<%=id%>">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
  out.flush();
%>
<h2 style="{color: rgb(255, 153, 0)}">网页快照</h2>
<h3>
<%=id%>
</h3>
<hr>
<% if (content != null && !content.equals("")) {%>
<%= content %>
<% } %>
