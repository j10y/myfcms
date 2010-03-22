package com.zving.search.crawl;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.zving.framework.Config;
import com.zving.framework.data.DataTable;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.search.DocumentList;
import com.zving.search.HtmlTextExtracter;
import com.zving.search.SearchResult;
import com.zving.search.WebDocument;
import com.zving.search.ZvingAnalyzer;

public class CrawlIndexer {
	protected static IndexSearcher searcher;
	protected static Analyzer writeAnalyzer = new ZvingAnalyzer();
	protected static Analyzer queryAnalyzer1 = new IKAnalyzer();
	protected static Analyzer queryAnalyzer2 = new ChineseAnalyzer();
	protected static QueryParser qp1 = new QueryParser("CONTENT", queryAnalyzer1);
	protected static QueryParser qp2;
	protected static QueryParser qp3;

	static {
		qp1.setDefaultOperator(QueryParser.Operator.AND);
		qp1.setPhraseSlop(1);

		qp3 = new QueryParser("TITLE", queryAnalyzer1);
		qp3.setDefaultOperator(QueryParser.Operator.AND);
		qp3.setPhraseSlop(1);

		qp2 = new QueryParser("CONTENT", queryAnalyzer2);
		qp2.setDefaultOperator(QueryParser.Operator.AND);
		qp2.setPhraseSlop(1);
	}

	public static void index(long id) {
		try {
			long t = System.currentTimeMillis();
			IndexWriter writer = new IndexWriter(Config.getContextRealPath()
					+ "WEB-INF/data/index/Crawl" + id, writeAnalyzer, true);
			DocumentList list = new DocumentList(Config.getContextRealPath() + "WEB-INF/data/" + id
					+ "/");
			System.out.println("正在建立索引,共" + list.size() + "个文件......");
			int size = list.size();
			int i = 0;
			WebDocument wdoc = list.next();
			while (wdoc != null) {
				long percent = Math.round(i * 10000.0D / size);
				if (percent % 100L == 0L) {
					System.out.println("Indexing Html Files, Percent : " + (percent / 100L) + " %");
				}
				if (wdoc.isTextContent()) {
					String content = wdoc.getContentText();
					HtmlTextExtracter hte = new HtmlTextExtracter();
					hte.setHtml(content);
					hte.setUrl(wdoc.getUrl());
					String text = hte.getContentText();
					if (StringUtil.isNotEmpty(text)) {
						String title = StringUtil.getHtmlTitle(content);
						if ((StringUtil.isNotEmpty(title)) && (text.length() > 100)) {
							Document idoc = new Document();
							idoc.add(new Field("CONTENT", text, Field.Store.YES,
									Field.Index.TOKENIZED));
							idoc.add(new Field("TITLE", StringUtil.getHtmlTitle(content),
									Field.Store.YES, Field.Index.TOKENIZED));
							idoc.add(new Field("URL", wdoc.getUrl(), Field.Store.YES,
									Field.Index.UN_TOKENIZED));
							idoc.add(new Field("DATE", DateUtil.toDateTimeString(wdoc
									.getLastmodifiedDate()), Field.Store.YES,
									Field.Index.UN_TOKENIZED));

							writer.addDocument(idoc);
						}
					} else {
						System.out.println("未能提取正文:" + wdoc.getUrl());
					}
				}
				++i;
				wdoc = list.next();
			}
			writer.optimize();
			writer.close();
			System.out.println("共耗时：" + (System.currentTimeMillis() - t));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SearchResult search(long crawlID, String keyword, int pageSize, int pageIndex) {
		Mapx map = new Mapx();
		map.put("orderType", "AES");
		return search(crawlID, keyword, pageSize, pageIndex, map);
	}

	public static SearchResult search(long crawlID, String keyword, int pageSize, int pageIndex,
			Mapx map) {
		try {
			long t = System.currentTimeMillis();
			if (searcher == null) {
				searcher = new IndexSearcher(Config.getContextRealPath()
						+ "WEB-INF/data/index/Crawl" + crawlID);
			}

			String orderType = map.getString("orderType");
			if (StringUtil.isEmpty(orderType)) {
				orderType = "AES";
			}

			Query q1 = qp1.parse(keyword);

			Query q3 = qp3.parse(keyword);
			BooleanQuery q = new BooleanQuery();
			q.add(q3, BooleanClause.Occur.SHOULD);
			q.add(q1, BooleanClause.Occur.SHOULD);

			SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
			Highlighter highlighter = new Highlighter(formatter, new QueryScorer(q));
			int abstractLength = 150;
			highlighter.setTextFragmenter(new SimpleFragmenter(abstractLength));
			DataTable dt = new DataTable();
			Hits hits = null;
			if (orderType.equals("Date"))
				hits = searcher.search(q, new Sort(new SortField("DATE", 5, !("AES"
						.equals(orderType)))));
			else {
				hits = searcher.search(q);
			}
			int start = pageIndex * pageSize;
			for (int i = start; (i < start + pageSize) && (i < hits.length()); ++i) {
				Document doc = hits.doc(i);
				if (i == start) {
					Object[] fields = doc.getFields().toArray();
					for (int j = 0; j < fields.length; ++j) {
						String name = ((Field) fields[j]).name();
						dt.insertColumn(name);
					}
				}
				String title = doc.get("TITLE");
				TokenStream tokenStream = queryAnalyzer1.tokenStream("TITLE", new StringReader(
						title));
				String tmp = highlighter.getBestFragment(tokenStream, title);
				if (StringUtil.isNotEmpty(tmp)) {
					title = tmp;
				}
				String content = doc.get("CONTENT");
				content = content.replaceAll("\\<", "&gt;");
				content = content.replaceAll("\\>", "&lt;");
				tokenStream = queryAnalyzer1.tokenStream("CONTENT", new StringReader(content));
				tmp = highlighter.getBestFragment(tokenStream, content);
				if (StringUtil.isNotEmpty(tmp)) {
					content = tmp.trim();
				} else if (content.length() > abstractLength) {
					content = content.substring(0, abstractLength);
				}

				dt.insertRow(new String[dt.getColCount()]);
				for (int j = 0; j < dt.getColCount(); ++j) {
					dt.set(i - start, j, doc.get(dt.getDataColumn(j).getColumnName()));
				}
				dt.set(i - start, "TITLE", title);
				dt.set(i - start, "CONTENT", content);
			}
			SearchResult r = new SearchResult();
			r.Data = dt;
			r.Total = hits.length();
			r.UsedTime = NumberUtil.round((System.currentTimeMillis() - t) * 1.0D / 1000.0D, 3);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCache(long id, String url) {
		DocumentList list = new DocumentList(Config.getContextRealPath() + "WEB-INF/data/" + id);
		WebDocument doc = list.get(url);
		String content = doc.getContentText();
		list.close();
		return "<base href='" + url + "'>\n" + content;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		index(8L);
		System.out.println(System.currentTimeMillis() - t);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.search.crawl.CrawlIndexer JD-Core Version: 0.5.3
 */