package com.zving.cms.datachannel;

import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.cms.template.PageGenerator;
import com.zving.framework.Config;
import com.zving.framework.User;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.orm.Schema;
import com.zving.framework.orm.SchemaSet;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Errorx;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCArticleSet;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCImageSchema;
import java.util.Date;

public class Publisher {
	public boolean publishAll(long siteID) {
		PageGenerator p = new PageGenerator();
		if (p.staticSite(siteID)) {
			Deploy d = new Deploy();
			d.addJobs(siteID, p.getFileList());
			return true;
		}
		return false;
	}

	public boolean publishAll(long siteID, LongTimeTask task) {
		PageGenerator p = new PageGenerator(task);
		if (p.staticSite(siteID)) {
			Deploy d = new Deploy();
			d.addJobs(siteID, p.getFileList());
			return true;
		}
		return false;
	}

	public boolean publishIndex(long siteID, LongTimeTask task) {
		PageGenerator p = new PageGenerator(task);
		if (p.staticSiteIndex(siteID)) {
			Deploy d = new Deploy();
			d.addJobs(siteID, p.getFileList());
			return true;
		}
		return false;
	}

	public boolean publishIndex(long siteID) {
		PageGenerator p = new PageGenerator();
		if (p.staticSiteIndex(siteID)) {
			Deploy d = new Deploy();
			d.addJobs(siteID, p.getFileList());
			return true;
		}
		return false;
	}

	public boolean publishArticle(ZCArticleSet articleSet, boolean generateCatalog,
			LongTimeTask task) {
		PageGenerator p = new PageGenerator(task);
		if (task != null) {
			task.setCurrentInfo("开始发布文章");
		}

		ZCArticleSet articlePubishSet = new ZCArticleSet();
		Deploy d = new Deploy();
		for (int i = 0; i < articleSet.size(); ++i) {
			ZCArticleSchema article = articleSet.get(i);

			if (article.getStatus() == 40L) {
				String msg = "文档处于下线状态，不能发布。如需发布，请先上线后再操作。";
				if (task != null) {
					task.addError(msg);
				}
				Errorx.addError(msg);
				return false;
			}

			String workflowName = CatalogUtil.getWorkflow(article.getCatalogID());
			if ((StringUtil.isNotEmpty(workflowName)) && (article.getStatus() == 10L)) {
				String msg = "文档需审核，不能发布。";
				if (task != null) {
					task.addError(msg);
				}
				Errorx.addError(msg);
				return false;
			}

			if (!(p.staticDoc("Article", (ZCArticleSchema) article.clone()))) {
				continue;
			}

			d.addJobs(articleSet.get(0).getSiteID(), p.getFileList());

			if (article.getPublishDate() == null) {
				article.setPublishDate(new Date());
			}
			article.setModifyTime(new Date());
			if (User.getCurrent() == null)
				article.setModifyUser("administrator");
			else {
				article.setModifyUser(User.getUserName());
			}

			article.setStatus(30L);
			articlePubishSet.add(article);

			if (task != null) {
				task.setPercent(30 + i / articleSet.size() * 50);
				task.setCurrentInfo("正在发布:" + article.getTitle());
			}
		}
		Transaction trans = new Transaction();
		trans.add(articlePubishSet, 2);
		if (!(trans.commit())) {
			Errorx.addError("更新文章状态失败。");
			return false;
		}

		if (generateCatalog) {
			Mapx catalogMap = new Mapx();
			for (int k = 0; k < articleSet.size(); ++k) {
				catalogMap.put(String.valueOf(articleSet.get(k).getCatalogID()), String
						.valueOf(articleSet.get(k).getCatalogID()));
			}

			Object[] vs = catalogMap.valueArray();
			for (int j = 0; j < catalogMap.size(); ++j) {
				publishCatalog(Long.parseLong(vs[j].toString()), false, false, 3);
				if (task != null) {
					task.setPercent(task.getPercent() + 5);
					task.setCurrentInfo("发布栏目页面");
				}
			}

		}

		return (!(Errorx.hasError()));
	}

	public boolean publishDoc(String docType, SchemaSet docSet, boolean generateCatalog,
			LongTimeTask task) {
		PageGenerator p = new PageGenerator(task);
		if (task != null) {
			task.setCurrentInfo("开始发布");
		}

		Transaction trans = new Transaction();
		Mapx catalogMap = new Mapx();
		long siteID = 0L;
		Deploy d = new Deploy();
		for (int i = 0; i < docSet.size(); ++i) {
			Schema doc = docSet.getObject(i);

			if (!(p.staticDoc(docType, (Schema) doc.clone()))) {
				continue;
			}

			DataRow row = doc.toDataRow();

			siteID = row.getLong("SiteID");
			d.addJobs(siteID, p.getFileList());

			Date publishDate = row.getDate("PublishDate");
			if (publishDate == null) {
				publishDate = new Date();
			}
			String modifyUser = null;
			if (User.getCurrent() == null)
				modifyUser = "sys";
			else {
				modifyUser = User.getUserName();
			}

			QueryBuilder qb = new QueryBuilder("update zc" + docType
					+ " set publishdate=?,modifytime=?,modifyuser=? where id=?");
			qb.add(publishDate);
			qb.add(new Date());
			qb.add(modifyUser);
			qb.add(row.get("ID"));
			trans.add(qb);

			catalogMap.put(row.getString("catalogid"), row.getString("catalogid"));

			if (task != null) {
				task.setPercent(30 + i / docSet.size() * 50);
				task.setCurrentInfo("正在发布:" + row.getString("Name"));
			}
		}

		if (!(trans.commit())) {
			Errorx.addError("更新文章状态失败。");
			return false;
		}

		if (generateCatalog) {
			Object[] vs = catalogMap.valueArray();
			for (int j = 0; j < catalogMap.size(); ++j) {
				publishCatalog(Long.parseLong(vs[j].toString()), false, false, 3);
				if (task != null) {
					task.setPercent(task.getPercent() + 5);
					task.setCurrentInfo("发布栏目页面");
				}
			}

		}

		return (!(Errorx.hasError()));
	}

	public boolean publishArticle(ZCArticleSet articleSet) {
		return publishArticle(articleSet, true, null);
	}

	public boolean publishArticle(ZCArticleSet articleSet, LongTimeTask task) {
		return publishArticle(articleSet, true, task);
	}

	public boolean publishCatalog(long catalogID, boolean child, boolean detail) {
		return publishCatalog(catalogID, child, detail, 0, null);
	}

	public boolean publishCatalog(long catalogID, boolean child, boolean detail, int publishSize) {
		return publishCatalog(catalogID, child, detail, publishSize, null);
	}

	public boolean publishCatalog(long catalogID, boolean child, boolean detail, LongTimeTask task) {
		return publishCatalog(catalogID, child, detail, 0, task);
	}

	public boolean publishCatalog(long catalogID, boolean child, boolean detail, int publishSize,
			LongTimeTask task) {
		PageGenerator p = new PageGenerator(task);
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(catalogID);
		catalog.fill();

		if (task != null) {
			task.setPercent(30);
		}

		if (!(p.staticCatalog(catalog, detail, publishSize))) {
			return false;
		}

		if (task != null) {
			task.setPercent(45);
		}

		if ((child) && (!(p.staticChildCatalog(catalogID, detail, publishSize)))) {
			return false;
		}

		if (task != null) {
			task.setPercent(75);
		}

		if (detail) {
			Transaction trans = new Transaction();
			trans.add(new QueryBuilder("update zcarticle set publishDate = '"
					+ DateUtil.getCurrentDateTime() + "',status=" + 30 + " where status=" + 20
					+ " and publishdate is null and  CatalogInnerCode like ?", catalog
					.getInnerCode()
					+ "%"));
			if (!(trans.commit())) {
				return false;
			}
		}

		Deploy d = new Deploy();
		d.addJobs(catalog.getSiteID(), p.getFileList());

		if (task != null) {
			task.setPercent(100);
		}

		return true;
	}

	public void deletePubishedFile(ZCImageSchema image) {
		String contextRealPath = Config.getContextRealPath();
		String staticDir = contextRealPath
				+ Config.getValue("Statical.TargetDir").replace('\\', '/');
		String filePath = staticDir + "/" + SiteUtil.getAlias(image.getSiteID()) + "/upload/"
				+ CatalogUtil.getPath(image.getCatalogID()) + "/" + image.getID() + ".shtml";
		FileUtil.delete(filePath);
	}

	public void deletePubishedFile(ZCArticleSchema article) {
		String contextRealPath = Config.getContextRealPath();
		String staticDir = contextRealPath
				+ Config.getValue("Statical.TargetDir").replace('\\', '/');
		String filePath = staticDir + "/" + SiteUtil.getAlias(article.getSiteID()) + "/"
				+ CatalogUtil.getPath(article.getCatalogID()) + "/" + article.getID() + ".shtml";
		FileUtil.delete(filePath);
	}

	public void deletePubishedFile(SchemaSet set) {
		String contextRealPath = Config.getContextRealPath();
		String staticDir = contextRealPath
				+ Config.getValue("Statical.TargetDir").replace('\\', '/');
		for (int i = 0; i < set.size(); ++i) {
			DataRow doc = set.getObject(i).toDataRow();
			String filePath = staticDir + "/" + SiteUtil.getAlias(doc.getLong("SiteID")) + "/"
					+ CatalogUtil.getPath(doc.getString("CatalogID")) + "/" + doc.getString("ID")
					+ ".shtml";
			FileUtil.delete(filePath);
		}
	}

	public long publishSetTask(final String type, final SchemaSet set) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishDoc(type, set, true, this);
				setPercent(100);
			}

		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public long deleteFileTask(final SchemaSet set) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				if (set != null && set.size() > 0) {
					p.deletePubishedFile(set);
					p.publishCatalog(set.getObject(0).toDataRow().getLong("CatalogID"), false,
							false, 5);
					setPercent(100);
				}
			}

		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.datachannel.Publisher JD-Core Version: 0.5.3
 */