<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="com.zving.framework.*"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="com.zving.cms.pub.SiteUtil"%>
<%@page import="com.zving.framework.data.*"%>
<%@ taglib uri="controls" prefix="z"%>

<%!String header = "";
	String loop = "";
	String footer = "";%>
<%
	if (Config.isDebugMode() || "".equals(loop)) {
		File file = new File(application.getRealPath("Services/CommentListPage.jsp"));
		String text = FileUtil.readText(file);
		header = text.substring(text.indexOf("<!-- comment header begin -->") + 29, text.indexOf("<!-- comment header end -->"));
		loop = text.substring(text.indexOf("<!-- comment loop begin -->") + 27, text.indexOf("<!-- comment loop end -->"));
		footer = text.substring(text.indexOf("<!-- comment footer begin -->") + 29, text.indexOf("<!-- comment footer end -->"));
		if(User.isLogin()){
			String subFooter = footer.substring(footer.indexOf("<div id=\"textLogin\">"),footer.indexOf("<!-- end div -->"));
			footer = StringUtil.replaceEx(footer,subFooter,"");
		}
	}

	String relaID = request.getParameter("RelaID");
	String count = request.getParameter("Count");
	if (count == null || "".equals(count)) {
		count = "5";
	}
	int pageSize = Integer.parseInt(count);
	String siteID = request.getParameter("SiteID");
	boolean commentFlag = SiteUtil.getCommentAuditFlag(siteID);
	String WherePart = "";
	if (commentFlag) {
		WherePart = " and verifyflag='Y'"; //评论需要审核
	}
	String sql = "select * from zccomment where relaid=" + relaID + WherePart + " order by id desc ";
	DataTable dt = new QueryBuilder(sql).executePagedDataTable(pageSize, 0);
	int size = dt.getRowCount();
	StringBuffer sb = new StringBuffer();
	Map map = new HashMap();
	map.put("ServicesContext", Config.getValue("ServicesContext"));
	map.put("CommentActionURL", Config.getValue("CommentActionURL"));
	map.put("CommentCountJS", Config.getValue("CommentCountJS"));
	map.put("CommentListJS", Config.getValue("CommentListJS"));
	map.put("CommentListPageJS", Config.getValue("CommentListPageJS"));
	map.put("RelaID", request.getParameter("RelaID"));
	map.put("CatalogID", request.getParameter("CatalogID"));
	map.put("CatalogType", request.getParameter("CatalogType"));
	map.put("SiteID", siteID);
	sb.append(HtmlUtil.replacePlaceHolder(header, map, true));
	for (int i = 0; i < size; i++) {
		map.clear();
		map.put("AddUser", dt.getString(i, "AddUser"));
		map.put("AddTime", dt.getString(i, "AddTime"));
		String ip = dt.getString(i, "AddUserIP");
		ip = ip.substring(0, ip.lastIndexOf(".") + 1) + "*";
		map.put("AddUserIP", ip);
		map.put("Title", StringUtil.javaEncode(dt.getString(i, "Title")));
		map.put("Content", StringUtil.javaEncode(dt.getString(i, "Content")));
		sb.append(HtmlUtil.replacePlaceHolder(loop, map, true));
	}

	map.clear();
	map.put("ServicesContext", Config.getValue("ServicesContext"));
	map.put("CommentActionURL", Config.getValue("CommentActionURL"));
	map.put("CommentCountJS", Config.getValue("CommentCountJS"));
	map.put("CommentListJS", Config.getValue("CommentListJS"));
	map.put("CommentListPageJS", Config.getValue("CommentListPageJS"));
	map.put("RelaID", request.getParameter("RelaID"));
	map.put("CatalogID", request.getParameter("CatalogID"));
	map.put("CatalogType", request.getParameter("CatalogType"));
	map.put("SiteID", siteID);
	sb.append(HtmlUtil.replacePlaceHolder(footer, map, true));

	int index = sb.indexOf("\n");
	int lastIndex = -1;
	while (index != -1) {
		out.println("document.write(\'" + sb.substring(lastIndex + 1, index) + "\');");
		lastIndex = index;
		index = sb.indexOf("\n", index + 1);
	}
%>
