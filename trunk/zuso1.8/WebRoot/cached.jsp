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
	
	/**
	 * Initialize
	 */
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
	


  String language = "zh";
  //  ResourceBundle.getBundle("org.nutch.jsp.cached", request.getLocale())
    //.getLocale().getLanguage();


  String contentType =  "text/html";

  String content = null;
  //String contentType = (String) metaData.get(Metadata.CONTENT_TYPE);
  if (contentType.startsWith("text/html")) {
    // FIXME : it's better to emit the original 'byte' sequence 
    // with 'charset' set to the value of 'CharEncoding',
    // but I don't know how to emit 'byte sequence' in JSP.
    // out.getOutputStream().write(bean.getContent(details)) may work, 
    // but I'm not sure.
    //String encoding = (String) metaData.get("CharEncodingForConversion");
	  String encoding = "UTF-8";
      content = String.valueOf(doc.getFieldValue("cache"));
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
<!-- 
   FIXME: have to sanitize 'content' : e.g. removing unncessary part
        of head elememt
-->
<% if (contentType.startsWith("text/html")) {%>

<% if (content != null && !content.equals("")) {%>
<%= content %>
<% } %>
<% } %>
<% } %>
