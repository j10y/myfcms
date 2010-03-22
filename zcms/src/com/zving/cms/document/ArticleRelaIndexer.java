package com.zving.cms.document;

import com.zving.framework.Config;
import com.zving.framework.data.DBUtil;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.search.SearchParameters;
import com.zving.search.SearchResult;
import com.zving.search.index.Indexer;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class ArticleRelaIndexer extends Indexer {
	private static final int PageSize = 100;

	public ArticleRelaIndexer() {
		setPath(Config.getContextRealPath() + "WEB-INF/data/index/keyword/");
	}

	public void create() {
		QueryBuilder qb = new QueryBuilder(
				"select InnerCode,KeywordSetting from ZCCatalog where KeywordFlag='Y' and KeywordSetting<>''");
		Mapx map = qb.executeDataTable().toMapx(0, 1);
		Object[] ks = map.keyArray();
		for (int i = 0; i < ks.length; ++i) {
			String k = ks[i].toString();
			boolean existParentFlag = false;
			while (k.length() > 4) {
				k = k.substring(0, k.length() - 4);
				if (map.containsKey(k)) {
					existParentFlag = true;
					break;
				}
			}
			k = ks[i].toString();
			if (!(existParentFlag)) {
				qb = new QueryBuilder(
						"select ID,CatalogInnerCode,Title,Keyword from ZCArticle where CatalogInnerCode like '"
								+ k + "%'");
				int count = DBUtil.getCount(qb);
				for (int j = 0; j <= count / 100; ++j) {
					DataTable dt = qb.executePagedDataTable(100, j);
					for (int m = 0; m < dt.getRowCount(); ++m) {
						String CatalogInnerCode = dt.getString(m, "CatalogInnerCode");
						String Keyword = dt.getString(m, "Keyword");
						String Title = dt.getString(m, "Title");
						String ID = dt.getString(m, "ID");
						if (StringUtil.isEmpty(Keyword))
							Keyword = Title;
						else {
							Keyword = Keyword + " " + Title;
						}
						if (ID.equals("224803")) {
							System.out.println(ID);
						}
						k = CatalogInnerCode;
						String Setting = map.getString(k);
						while ((StringUtil.isEmpty(Setting)) && (k.length() >= 4)) {
							k = k.substring(0, k.length() - 4);
							Setting = map.getString(k);
						}
						if (StringUtil.isEmpty(Setting)) {
							continue;
						}
						Setting = "," + Setting + ",";
						Document doc = new Document();
						doc.add(new Field("CATALOGINNERCODE", CatalogInnerCode, Field.Store.YES,
								Field.Index.NOT_ANALYZED));
						doc.add(new Field("ID", ID, Field.Store.YES, Field.Index.NOT_ANALYZED));
						doc.add(new Field("SETTING", Setting, Field.Store.YES,
								Field.Index.NOT_ANALYZED));
						doc
								.add(new Field("KEYWORD", Keyword, Field.Store.NO,
										Field.Index.ANALYZED));
						doc.add(new Field("TITLE", Title, Field.Store.YES, Field.Index.NO));
						try {
							this.writer.addDocument(doc);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void update() {
		QueryBuilder qb = new QueryBuilder(
				"select InnerCode,KeywordSetting from ZCCatalog where KeywordFlag='Y' and KeywordSetting<>''");
		Mapx map = qb.executeDataTable().toMapx(0, 1);

		DataTable catalogs = new QueryBuilder(
				"select distinct CatalogID from ZCArticle where (addtime>=? or modifytime>=?) and status=30",
				this.lastDate, this.lastDate).executeDataTable();
		for (int j = 0; j < catalogs.getRowCount(); ++j) {
			qb = new QueryBuilder(
					"select ID,CatalogInnerCode,Title,Keyword from ZCArticle where CatalogID=? and (addtime>=? or modifytime>=?) and status=30",
					catalogs.getString(j, "CatalogID"), this.lastDate);
			qb.add(this.lastDate);
			int total = DBUtil.getCount(qb);
			for (int i = 0; i * 100 < total; ++i) {
				DataTable dt = qb.executePagedDataTable(100, i);
				for (int m = 0; m < dt.getRowCount(); ++m) {
					String CatalogInnerCode = dt.getString(m, "CatalogInnerCode");
					String Keyword = dt.getString(m, "Keyword");
					String Title = dt.getString(m, "Title");
					String ID = dt.getString(m, "ID");
					if (StringUtil.isEmpty(Keyword))
						Keyword = Title;
					else {
						Keyword = Keyword + " " + Title;
					}
					String k = CatalogInnerCode;
					String Setting = map.getString(k);
					while ((StringUtil.isEmpty(Setting)) && (k.length() >= 4)) {
						k = k.substring(0, k.length() - 4);
						Setting = map.getString(k);
					}
					if (StringUtil.isEmpty(Setting)) {
						continue;
					}
					Setting = "," + Setting + ",";
					Document doc = new Document();
					doc.add(new Field("CATALOGINNERCODE", CatalogInnerCode, Field.Store.YES,
							Field.Index.NOT_ANALYZED));
					doc.add(new Field("ID", ID, Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc
							.add(new Field("SETTING", Setting, Field.Store.YES,
									Field.Index.NOT_ANALYZED));
					doc.add(new Field("KEYWORD", Keyword, Field.Store.NO, Field.Index.ANALYZED));
					doc.add(new Field("TITLE", Title, Field.Store.YES, Field.Index.NO));
					try {
						this.writer.updateDocument(new Term("ID", ID), doc);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		DataTable dt = new QueryBuilder(
				"select id from BZCArticle where status=30 and backuptime>?", this.lastDate)
				.executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i)
			try {
				this.writer.deleteDocuments(new Term("ID", dt.getString(i, 0)));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static SearchResult search(SearchParameters sps) {
		try {
			long t = System.currentTimeMillis();
			IndexSearcher searcher = new IndexSearcher(FSDirectory.open(new File(Config
					.getContextRealPath()
					+ "WEB-INF/data/index/keyword")), true);

			if (sps.getBooleanQuery().clauses().size() == 0) {
				sps.addLeftLikeField("CatalogInnerCode", "");
			}

			DataTable dt = new DataTable();
			int start = sps.getPageIndex() * sps.getPageSize();
			TopDocs docs = null;
			if (sps.getSort() != null)
				docs = searcher.search(sps.getBooleanQuery(), sps.getDateRangeFilter(), start
						+ sps.getPageSize(), sps.getSort());
			else {
				docs = searcher.search(sps.getBooleanQuery(), sps.getDateRangeFilter(), start
						+ sps.getPageSize());
			}

			for (int i = start; (i < start + sps.getPageSize()) && (i < docs.scoreDocs.length); ++i) {
				Document doc = searcher.doc(docs.scoreDocs[i].doc);
				if (i == start) {
					Object[] fields = doc.getFields().toArray();
					for (int j = 0; j < fields.length; ++j) {
						String name = ((Field) fields[j]).name();
						dt.insertColumn(name);
					}
				}
				dt.insertRow(new String[dt.getColCount()]);
				for (int j = 0; j < dt.getColCount(); ++j) {
					dt.set(i - start, j, doc.get(dt.getDataColumn(j).getColumnName()));
				}
			}
			searcher.close();

			SearchResult r = new SearchResult();
			r.Data = dt;
			r.Total = docs.totalHits;
			r.UsedTime = NumberUtil.round((System.currentTimeMillis() - t) * 1.0D / 1000.0D, 3);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SearchResult getRelaArticles(String setting, String title, String keywords) {
		return getRelaArticles(0L, setting, title, keywords);
	}

	public static SearchResult getRelaArticles(long articleID, String setting, String title,
			String keywords) {
		SearchParameters sps = new SearchParameters();
		sps.addFulltextField("Keyword", keywords + " " + title);
		sps.addLikeField("Setting", "," + setting + ",");
		sps.addNotEqualField("ID", String.valueOf(articleID));
		SearchResult sr = search(sps);
		return sr;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		for (int i = 0; i < 1; ++i) {
			SearchResult sr = getRelaArticles(224803L, "科学", "我国22日下午4点可观测天琴座流星雨", "英国 科学家");
			System.out.println(sr.Data);
			System.out.println(sr.Total);
		}
		System.out.println(System.currentTimeMillis() - t);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.document.ArticleRelaIndexer JD-Core Version: 0.5.3
 */