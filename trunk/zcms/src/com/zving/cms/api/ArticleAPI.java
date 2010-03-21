package com.zving.cms.api;

import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.orm.Schema;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCArticleLogSchema;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZDColumnValueSchema;
import com.zving.schema.ZDColumnValueSet;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;

public class ArticleAPI implements APIInterface {
	private ZCArticleSchema article;
	private Mapx customData;
	private Mapx params;

	public boolean setSchema(Schema schema) {
		this.article = ((ZCArticleSchema) schema);
		return false;
	}

	public long insert() {
		Transaction trans = new Transaction();
		if (insert(trans) > 0L) {
			if (trans.commit())
				return 1L;
			return -1L;
		}

		return -1L;
	}

	public long insert(Transaction trans) {
		long articleID = NoUtil.getMaxID("DocID");
		this.article.setID(articleID);
		if (this.article.getCatalogID() == 0L) {
			return -1L;
		}
		this.article.setSiteID(CatalogUtil.getSiteID(this.article.getCatalogID()));

		String innerCode = CatalogUtil.getInnerCode(this.article.getCatalogID());
		this.article.setCatalogInnerCode(innerCode);
		if (this.article.getType() == null) {
			this.article.setType("1");
		}

		if (this.article.getTopFlag() == null) {
			this.article.setTopFlag("0");
		}

		if (this.article.getCommentFlag() == null) {
			this.article.setCommentFlag("1");
		}

		if (this.article.getContent() == null) {
			this.article.setContent("");
		}

		this.article.setStickTime(0L);

		this.article.setPriority("1");
		this.article.setTemplateFlag("0");

		this.article.setPublishFlag("1");

		long order = this.article.getPublishDate().getTime();
		while (true) {
			int count = new QueryBuilder("select count(*) from ZCArticle where OrderFlag=?", order)
					.executeInt();
			if (count <= 0)
				break;
			order += 1L;
		}

		this.article.setOrderFlag(order);
		this.article.setHitCount(0L);
		this.article.setStatus(20L);
		this.article.setAddTime(new Date(this.article.getOrderFlag()));

		if (this.article.getAddUser() == null) {
			this.article.setAddUser("admin");
		}

		trans.add(this.article, 1);

		String sqlArticleCount = "update zccatalog set total = total+1,isdirty=1 where id=?";
		trans.add(new QueryBuilder(sqlArticleCount, this.article.getCatalogID()));

		if (this.customData != null) {
			addCustomData(trans);
		}

		ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
		articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
		articleLog.setAction("INSERT");
		articleLog.setActionDetail("添加新文章");
		articleLog.setArticleID(articleID);

		articleLog.setAddUser("admin");
		articleLog.setAddTime(new Date());
		trans.add(articleLog, 1);

		return 1L;
	}

	private void addCustomData(Transaction trans) {
		ZDColumnValueSchema ColumnValue = null;
		ZDColumnValueSet ColumnValueSet = new ZDColumnValueSet();
		DataTable dt = new QueryBuilder(
				"select b.code code,b.listopt listopt,b.showmod showmod from zdcustomcolumnrela a, zdcustomcolumnitem b where a.type=2 and b.classcode='Sys_CMS' and a.customid=b.id and a.typeid=?",
				this.article.getCatalogID()).executeDataTable();
		if (dt.getRowCount() > 0) {
			for (int i = 0; i < dt.getRowCount(); ++i) {
				String code = "";
				String showMode = "";
				String textValue = "";
				String index = "";
				String[] list = (String[]) null;

				code = dt.getString(i, "code");
				showMode = dt.getString(i, "ShowMod");
				if (("1".equals(showMode)) || ("2".equals(showMode))) {
					textValue = this.customData.getString(code);
				}
				if (("3".equals(showMode)) || ("4".equals(showMode))) {
					list = dt.getString(i, "listopt").split("\n");
					textValue = this.customData.getString(code);
					index = String.valueOf(ArrayUtils.indexOf(list, textValue));
				}
				if ("5".equals(showMode)) {
					list = dt.getString(i, "listopt").split("\n");
					textValue = this.customData.getString(code);
					String[] values = textValue.split("\\|");
					for (int j = 0; j < values.length; ++j) {
						if (j != values.length - 1) {
							index = index + ArrayUtils.indexOf(list, values[j]) + "|";
						} else {
							index = index + ArrayUtils.indexOf(list, values[j]);
						}
					}
				}

				ColumnValue = new ZDColumnValueSchema();
				ColumnValue.setID(NoUtil.getMaxID("ColumnValueID"));
				ColumnValue.setColumnCode(code);
				textValue = (textValue == null) ? "" : textValue;
				ColumnValue.setTextValue(textValue);
				ColumnValue.setRelaID(String.valueOf(this.article.getID()));
				ColumnValueSet.add(ColumnValue);
			}
		}
		trans.add(new QueryBuilder(
				"delete from zdcolumnvalue where classcode='Sys_CMS' and articleid=?", this.article
						.getID()));
		trans.add(ColumnValueSet, 1);
	}

	public boolean update() {
		String articleID = this.params.getString("DocID");

		ZCArticleSchema article1 = new ZCArticleSchema();
		article1.setID(articleID);
		if (!(article1.fill())) {
			return false;
		}
		if (article1.getCatalogID() == 0L) {
			return false;
		}

		article1.setTitle(this.params.getString("Title"));
		article1.setAuthor(this.params.getString("Author"));
		String content = this.params.getString("Content");
		String publishDate = this.params.getString("PublishDate");

		if (StringUtil.isNotEmpty(this.params.getString("TopFlag"))) {
			article1.setTopFlag(this.params.getString("TopFlag"));
		}

		if (StringUtil.isNotEmpty(this.params.getString("CommentFlag"))) {
			article1.setTopFlag(this.params.getString("CommentFlag"));
		}

		if (StringUtil.isNotEmpty(this.params.getString("CommentFlag"))) {
			article1.setTopFlag(this.params.getString("CommentFlag"));
		}

		if (StringUtil.isNotEmpty(content)) {
			article1.setContent(content);
		}

		if (StringUtil.isNotEmpty(publishDate)) {
			try {
				article1.setPublishDate(DateUtil.parse(publishDate, "yyyy-MM-dd"));
			} catch (Exception localException) {
			}
		}
		article1.setModifyTime(new Date(article1.getOrderFlag()));

		article1.setModifyUser("wsdl");

		Transaction trans = new Transaction();

		trans.add(article1, 2);

		ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
		articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
		articleLog.setAction("UPDATE");
		articleLog.setActionDetail("编辑文章");
		articleLog.setArticleID(articleID);

		articleLog.setAddUser("wsdl");
		articleLog.setAddTime(new Date());
		trans.add(articleLog, 1);

		return trans.commit();
	}

	public boolean delete() {
		if (this.article == null) {
			return false;
		}

		long articleID = this.article.getID();
		Transaction trans = new Transaction();

		trans.add(this.article, 3);

		ZDColumnValueSchema colValue = new ZDColumnValueSchema();
		colValue.setRelaID(String.valueOf(articleID));
		ZDColumnValueSet colValueSet = colValue.query();
		if ((colValueSet != null) && (!(colValueSet.isEmpty()))) {
			trans.add(colValueSet, 3);
		}

		String sqlArticleCount = "update zccatalog set total = total-1,isdirty=1 where innercode in("
				+ CatalogUtil.getParentCatalogCode(this.article.getCatalogInnerCode()) + ")";
		trans.add(new QueryBuilder(sqlArticleCount));

		ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
		articleLog.setArticleID(articleID);
		articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
		articleLog.setAction("DELETE");
		articleLog.setActionDetail("删除。删除原因：wsdl删除");
		articleLog.setAddUser("wsdl");
		articleLog.setAddTime(new Date());
		trans.add(articleLog, 1);

		return (trans.commit());
	}

	public Mapx getCustomData() {
		return this.customData;
	}

	public void setCustomData(Mapx customData) {
		this.customData = customData;
	}

	public static DataTable getPagedDataTable(long catalogID, int pageSize, int pageIndex) {
		DataTable dt = new QueryBuilder("select * from zcarticle where catalogid=?", catalogID)
				.executePagedDataTable(pageSize, pageIndex);
		ColumnUtil.extendDocColumnData(dt, catalogID);
		return dt;
	}

	public static DataTable getDataTable(long catalogID) {
		return getPagedDataTable(catalogID, -1, -1);
	}

	public static String getURL(long artilceID) {
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(artilceID);
		if (!(article.fill())) {
			return null;
		}
		String url = SiteUtil.getPath(article.getSiteID()) + "/"
				+ CatalogUtil.getPath(article.getCatalogID()) + artilceID + ".shtml";
		url.replaceAll("//", "/");
		return url;
	}

	public static String getPreviewURL(long artilceID) {
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(artilceID);
		if (!(article.fill())) {
			return null;
		}

		String url = Config.getValue("Statical.TargetDir") + "/"
				+ SiteUtil.getAlias(article.getSiteID())
				+ CatalogUtil.getPath(article.getCatalogID()) + artilceID + ".shtml";
		url.replaceAll("//", "/");
		return url;
	}

	public static String getPublishedURL(long artilceID) {
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(artilceID);
		if (!(article.fill())) {
			return null;
		}

		String url = SiteUtil.getURL(article.getSiteID()) + "/"
				+ CatalogUtil.getPath(article.getCatalogID()) + artilceID + ".shtml";
		url.replaceAll("//", "/");
		return url;
	}

	public static void main(String[] args) {
		DataTable dt = getPagedDataTable(5954L, 2, 0);
		System.out.println(dt.toString());
	}

	public Mapx getParams() {
		return this.params;
	}

	public void setParams(Mapx params) {
		convertParams(params);
		this.params = params;
	}

	public void convertParams(Mapx params) {
		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			String value = params.getString(key);
			if ((StringUtil.isEmpty(value)) || ("null".equalsIgnoreCase(value)))
				params.put(key, "");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.api.ArticleAPI JD-Core Version: 0.5.3
 */