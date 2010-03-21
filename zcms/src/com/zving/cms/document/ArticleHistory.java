package com.zving.cms.document;

import com.zving.cms.dataservice.ColumnUtil;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.orm.SchemaUtil;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.BZCArticleSchema;
import com.zving.schema.BZCArticleSet;
import com.zving.schema.ZCArticleSchema;
import java.util.Date;

public class ArticleHistory extends Page {
	private static final String PAGE_SPLIT = "--abcdefghijklmnopqrstuvwxyz--";

	public static Mapx basicInit(Mapx params) {
		Mapx map = new Mapx();
		String catalogID = (String) params.get("CatalogID");
		String articleID = (String) params.get("ArticleID");
		String backupNo = (String) params.get("BackupNo");
		if ((articleID != null) && (!("".equals(articleID))) && (!("null".equals(articleID)))) {
			BZCArticleSchema article = new BZCArticleSchema();
			article.setID(Integer.parseInt(articleID));
			article.setBackupNo(backupNo);
			BZCArticleSet set = article.query();
			if (set.size() < 1) {
				return null;
			}
			article = set.get(0);
			catalogID = String.valueOf(article.getCatalogID());
			map.putAll(article.toMapx());

			String content = article.getContent();
			String[] pages = content.split("--abcdefghijklmnopqrstuvwxyz--");

			map.put("Pages", new Integer(pages.length));
			map.put("Content", StringUtil.htmlEncode(pages[0]));

			map.put("CustomColumn", ColumnUtil.getHtml("1", catalogID, "2", String.valueOf(article
					.getID())));
		}

		DataTable dtCatalog = new QueryBuilder("select siteid,name from zccatalog where id=?",
				catalogID).executeDataTable();
		String catalogName = dtCatalog.get(0, "name").toString();
		map.put("SiteID", dtCatalog.getString(0, "siteid"));
		map.put("CatalogName", catalogName);
		return map;
	}

	public static Mapx init(Mapx params) {
		Mapx map = new Mapx();
		String catalogID = (String) params.get("CatalogID");
		String articleID = (String) params.get("ArticleID");
		String backupNo = (String) params.get("BackupNo");
		if ((articleID != null) && (!("".equals(articleID))) && (!("null".equals(articleID)))) {
			BZCArticleSchema article = new BZCArticleSchema();
			article.setID(Integer.parseInt(articleID));
			article.setBackupNo(backupNo);
			BZCArticleSet set = article.query();
			if (set.size() < 1) {
				return null;
			}
			article = set.get(0);
			catalogID = String.valueOf(article.getCatalogID());
			map.putAll(article.toMapx());

			Date publishDate = article.getPublishDate();
			if (publishDate != null) {
				map.put("PublishDate", DateUtil.toString(publishDate, "yyyy-MM-dd"));
				map.put("PublishTime", DateUtil.toString(publishDate, "HH:mm:ss"));
			}

			Date DownlineDate = article.getDownlineDate();
			if (DownlineDate != null) {
				map.put("DownlineDate", DateUtil.toString(DownlineDate, "yyyy-MM-dd"));
				map.put("DownlineTime", DateUtil.toString(DownlineDate, "HH:mm:ss"));
			}

			String content = article.getContent();
			String[] pages = content.split("--abcdefghijklmnopqrstuvwxyz--");
			StringBuffer pageStr = new StringBuffer();
			for (int i = 0; i < pages.length; ++i) {
				pageStr.append("'" + StringUtil.htmlEncode(pages[i]) + "'");
				if (i != pages.length - 1) {
					pageStr.append(",");
				}
			}
			map.put("Pages", new Integer(pages.length));
			map.put("ContentPages", pageStr.toString());
			map.put("Content", pages[0]);

			map.put("Method", "UPDATE");
		}

		DataTable dtCatalog = new QueryBuilder("select siteid,name from zccatalog where id=?",
				catalogID).executeDataTable();
		String catalogName = dtCatalog.get(0, "Name").toString();
		map.put("SiteID", dtCatalog.getString(0, "siteid"));
		map.put("CatalogName", catalogName);

		return map;
	}

	public static void historyDataBind(DataGridAction dga) {
		String articleID = (String) dga.getParams().get("ArticleID");
		String sql = "select ID,BackupNo,Title,author,publishDate,Addtime, type,topflag,(case modifytime when null then addtime else modifytime end) as modifytime,backuptime,modifyUser from BZCArticle where id=? order by ID desc";

		if (dga.getTotal() == 0) {
			String sql2 = "select count(*) from BZCArticle where ID=?";
			dga.setTotal(Integer.parseInt(new QueryBuilder(sql2, articleID).executeString()));
		}

		dga.bindData(new QueryBuilder(sql, articleID).executePagedDataTable(dga.getPageSize(), dga
				.getPageIndex()));
	}

	public void del() {
		String ids = $V("BackupNo");
		if (!(StringUtil.checkID(ids))) {
			return;
		}
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction trans = new Transaction();
		BZCArticleSchema site = new BZCArticleSchema();
		BZCArticleSet set = site.query(new QueryBuilder("where BackupNo in (" + ids + ")"));
		trans.add(set, 3);

		if (trans.commit()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void addVersion() {
		String articleID = $V("ArticleID");
		long id = Long.parseLong(articleID);
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(id);

		if (!(article.fill())) {
			this.Response.setStatus(0);
			this.Response.setMessage("没有找到对应的文章");
			return;
		}

		Transaction trans = new Transaction();
		trans.add(article, 4);

		if (trans.commit()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void restore() {
		String articleID = $V("ArticleID");
		long id = Long.parseLong(articleID);
		String backupNo = $V("BackupNo");
		BZCArticleSchema article = new BZCArticleSchema();
		article.setID(id);
		article.setBackupNo(backupNo);

		if (!(article.fill())) {
			this.Response.setStatus(0);
			this.Response.setMessage("没有找到对应的历史版本");
			return;
		}

		ZCArticleSchema currentArticle = new ZCArticleSchema();

		currentArticle.setID(id);
		if (!(currentArticle.fill())) {
			this.Response.setStatus(0);
			this.Response.setMessage("没有找到对应的文档");
			return;
		}

		ZCArticleSchema currentArticleBak = (ZCArticleSchema) currentArticle.clone();

		currentArticle.setModifyTime(new Date());
		currentArticle.setModifyUser(User.getUserName());
		currentArticle.setStatus(currentArticleBak.getStatus());

		SchemaUtil.copyFieldValue(article, currentArticle);

		Transaction trans = new Transaction();
		trans.add(currentArticle, 2);
		trans.add(currentArticleBak, 4);

		if (trans.commit()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.document.ArticleHistory JD-Core Version: 0.5.3
 */