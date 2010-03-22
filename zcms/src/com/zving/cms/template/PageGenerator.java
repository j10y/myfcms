package com.zving.cms.template;

import com.zving.Product;
import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.cms.site.Keyword;
import com.zving.framework.Config;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.orm.Schema;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Errorx;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCAttachmentSchema;
import com.zving.schema.ZCAttachmentSet;
import com.zving.schema.ZCAudioSchema;
import com.zving.schema.ZCAudioSet;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCCatalogSet;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCImageSet;
import com.zving.schema.ZCKeywordSchema;
import com.zving.schema.ZCKeywordSet;
import com.zving.schema.ZCPageBlockSchema;
import com.zving.schema.ZCPageBlockSet;
import com.zving.schema.ZCSiteSchema;
import com.zving.schema.ZCVideoSchema;
import com.zving.schema.ZCVideoSet;
import com.zving.statical.TemplateParser;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class PageGenerator {
	private String templateDir;
	private String staticDir;
	private ArrayList fileList;
	private Product product;
	private LongTimeTask task;

	public PageGenerator() {
		this(null);
	}

	public PageGenerator(LongTimeTask ltt) {
		this.fileList = new ArrayList();

		this.product = new Product();

		String contextRealPath = Config.getContextRealPath();
		this.templateDir = contextRealPath
				+ Config.getValue("Statical.TemplateDir").replace('\\', '/');
		this.staticDir = contextRealPath + Config.getValue("Statical.TargetDir").replace('\\', '/');
		this.staticDir = this.staticDir.replaceAll("///", "/");
		this.staticDir = this.staticDir.replaceAll("//", "/");

		this.task = ltt;
		if (ltt == null)
			this.task = LongTimeTask.createEmptyInstance();
	}

	public boolean staticCatalog(ZCCatalogSchema catalog, boolean isGenerateDetail) {
		return staticCatalog(catalog, isGenerateDetail, 0);
	}

	public boolean staticCatalog(ZCCatalogSchema catalog, boolean isGenerateDetail, int publishPages) {
		TemplateCache.clear();
		long t = System.currentTimeMillis();
		int level = (int) catalog.getTreeLevel();
		int type = (int) catalog.getType();
		String detailType = null;
		boolean flag = true;
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(catalog.getSiteID());
		site.fill();
		task.setCurrentInfo("\u6B63\u5728\u53D1\u5E03\u680F\u76EE\"" + catalog.getName() + "\"");
		staticPageBlock(site, catalog, 0);
		String siteAlias = site.getAlias();
		String rootPath = staticDir + "/" + siteAlias + "/";
		rootPath = rootPath.replaceAll("///", "/");
		rootPath = rootPath.replaceAll("//", "/");
		if (type == 1 || type == 3 || type == 2 || type == 8)
			detailType = "article";
		else if (type == 4)
			detailType = "image";
		else if (type == 5)
			detailType = "video";
		else if (type == 6)
			detailType = "audio";
		else if (type == 7)
			detailType = "attachment";
		else if (type == 9 || type == 10)
			detailType = "goods";
		CmsTemplateData catalogTemplateData = new CmsTemplateData();
		catalogTemplateData.setLevel(level);
		catalogTemplateData.setSite(site);
		catalogTemplateData.setCatalog(catalog);
		String indexTemplate = catalog.getIndexTemplate();
		if (StringUtil.isNotEmpty(indexTemplate) && !"Y".equals(catalog.getSingleFlag())) {
			indexTemplate = templateDir + "/" + siteAlias + "/" + indexTemplate;
			indexTemplate = indexTemplate.replaceAll("//", "/");
			String path = rootPath + CatalogUtil.getPath(catalog.getID());
			TemplateParser tp = getParser(site.getID(), indexTemplate, level);
			if (tp == null) {
				task.addError("\u9996\u9875\u6A21\u677F" + indexTemplate + "\u4E0D\u5B58\u5728");
				return false;
			}
			tp.define("site", catalogTemplateData.getSite());
			tp.define("TemplateData", catalogTemplateData);
			tp.define("catalog", catalogTemplateData.getCatalog());
			String fileName = "index.shtml";
			String statScript = "";
			if ("Y".equals(site.getAutoStatFlag())) {
				String serviceUrl = Config.getValue("ServicesContext");
				String statUrl = "SiteID=" + site.getID() + "&Type=Article&CatalogInnerCode="
						+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
				statScript = getStatScript(statUrl);
			}
			statScript = statScript + "\n<!-- Published by " + product.getAppCode() + "("
					+ product.getAppName() + ") " + product.getMainVersion() + " PublishDate "
					+ DateUtil.getCurrentDateTime() + "-->";
			if (!writeHTML(tp, path, fileName, statScript))
				return false;
		}
		String rssTemplate = catalog.getRssTemplate();
		if (StringUtil.isNotEmpty(rssTemplate)) {
			rssTemplate = templateDir + "/" + siteAlias + "/" + rssTemplate;
			rssTemplate = rssTemplate.replaceAll("//", "/");
			String path = rootPath + CatalogUtil.getPath(catalog.getID());
			TemplateParser tp = getParser(site.getID(), rssTemplate, level);
			if (tp != null) {
				tp.define("site", catalogTemplateData.getSite());
				tp.define("TemplateData", catalogTemplateData);
				tp.define("catalog", catalogTemplateData.getCatalog());
				task.setCurrentInfo("\u6B63\u5728\u5904\u7406\u680F\u76EE\"" + catalog.getName()
						+ "\"");
				if (!writeHTML(tp, path, "rss.xml", ""))
					return false;
			} else {
				task.addError("\u680F\u76EE\u201C" + catalog.getName() + "\u201DRss\u6A21\u677F"
						+ rssTemplate + "\u4E0D\u5B58\u5728");
				return false;
			}
		}
		TemplateParser listParser = null;
		if (!"Y".equals(catalog.getSingleFlag())) {
			String listTemplate = catalog.getListTemplate();
			if (StringUtil.isNotEmpty(indexTemplate) && StringUtil.isEmpty(listTemplate)) {
				staticPageBlock(site, catalog, 4);
				return true;
			}
			if (StringUtil.isEmpty(listTemplate))
				task.addError("\u680F\u76EE\u201C" + catalog.getName()
						+ "\u201D\u5217\u8868\u9875\u6A21\u677F\u4E0D\u80FD\u4E3A\u7A7A");
			listTemplate = templateDir + "/" + siteAlias + "/" + listTemplate;
			listTemplate = listTemplate.replaceAll("//", "/");
			listParser = getParser(site.getID(), listTemplate, level);
			if (listParser == null) {
				task.addError("\u680F\u76EE\u201C" + catalog.getName()
						+ "\u201D\u5217\u8868\u9875\u6A21\u677F" + listTemplate
						+ "\u4E0D\u5B58\u5728");
				return true;
			}
		}
		if (StringUtil.isEmpty(catalog.getDetailTemplate()) && !"Y".equals(catalog.getSingleFlag())
				&& StringUtil.isEmpty(catalog.getIndexTemplate()))
			task.addError("\u680F\u76EE\u201C" + catalog.getName()
					+ "\u201D\u8BE6\u7EC6\u9875\u6A21\u677F\u4E0D\u80FD\u4E3A\u7A7A");
		String detailTemplate = templateDir + "/" + siteAlias + "/" + catalog.getDetailTemplate();
		detailTemplate = detailTemplate.replaceAll("//", "/");
		int detailLevel = level;
		if (StringUtil.isNotEmpty(catalog.getDetailNameRule()))
			detailLevel = CatalogUtil.getPathLevel(catalog.getDetailNameRule(), level);
		TemplateParser catalogDetailParser = getParser(site.getID(), detailTemplate, detailLevel);
		String listNameRule = null;
		if (StringUtil.isEmpty(indexTemplate))
			listNameRule = "index.shtml";
		else
			listNameRule = "list.shtml";
		int pageSize = 20;
		String displayLevel = "current";
		if (!"Y".equals(catalog.getSingleFlag())) {
			Object obj = listParser.getDefineMap().get("PageSize");
			pageSize = obj != null ? Integer.parseInt(obj.toString()) : (int) catalog
					.getListPageSize();
			displayLevel = (String) listParser.getDefineMap().get("DisplayLevel");
		}
		if (pageSize == 0)
			pageSize = (int) catalog.getListPageSize();
		String catalogWherePart = null;
		String param = (new StringBuffer(String.valueOf(catalog.getID()))).toString();
		if ("current".equalsIgnoreCase(displayLevel))
			catalogWherePart = " catalogid=?";
		else if ("child".equalsIgnoreCase(displayLevel)) {
			catalogWherePart = " cataloginnercode like ?";
			param = catalog.getInnerCode() + "%";
		} else if ("all".equalsIgnoreCase(displayLevel)) {
			catalogWherePart = " cataloginnercode like ?";
			param = catalog.getInnerCode() + "%";
		} else {
			catalogWherePart = " catalogid=?";
		}
		if (type == 1 || type == 3 || type == 2) {
			QueryBuilder qb = null;
			String wherePart = null;
			if (StringUtil.isNotEmpty(catalog.getWorkflow())) {
				wherePart = " where " + catalogWherePart + " and status in(" + 20 + "," + 30
						+ ") and publishdate<='" + DateUtil.getCurrentDateTime()
						+ "'  order by topflag desc,orderflag desc,publishdate desc,id desc";
			} else {
				String allowStatus = CatalogUtil.getAllowStatus(catalog.getID());
				if (StringUtil.isEmpty(allowStatus))
					allowStatus = "20,30";
				wherePart = " where " + catalogWherePart + " and status in(" + allowStatus
						+ ") and publishdate<='" + DateUtil.getCurrentDateTime()
						+ "'  order by topflag desc,orderflag desc,publishdate desc,id desc";
			}
			qb = new QueryBuilder("select * from zcarticle " + wherePart, param);
			DataTable dtArticle = null;
			CmsTemplateData detailTemplateData = new CmsTemplateData();
			detailTemplateData.setLevel(detailLevel);
			detailTemplateData.setSite(site);
			detailTemplateData.setCatalog(catalog);
			if ("Y".equals(catalog.getSingleFlag())) {
				dtArticle = qb.executePagedDataTable(1, 0);
				ColumnUtil.extendDocColumnData(dtArticle, catalog.getID());
				if (dtArticle.getRowCount() < 1) {
					ZCArticleSchema row = new ZCArticleSchema();
					row.setPublishDate(new Date());
					row
							.setContent("\u8BE5\u680F\u76EE\u6CA1\u6709\u6DFB\u52A0\u6587\u7AE0\uFF0C\u8BF7\u6DFB\u52A0\u6587\u7AE0\u3002");
					row.setSiteID(catalog.getSiteID());
					row.setCatalogID(catalog.getID());
					row.setCatalogInnerCode(catalog.getInnerCode());
					dtArticle.insertRow(row.toDataRow());
				}
				if (dtArticle.getRowCount() > 0) {
					DataRow article = dtArticle.getDataRow(0);
					String detailTemplateName = "";
					TemplateParser detailParser;
					if ("1".equals(article.getString("TemplateFlag"))) {
						detailTemplateName = article.getString("Template");
						detailTemplateName = templateDir + "/" + siteAlias + detailTemplateName;
						detailTemplateName = detailTemplateName.replaceAll("//", "/");
						detailParser = getParser(site.getID(), detailTemplateName, level);
						if (detailParser == null)
							detailParser = catalogDetailParser;
					} else {
						detailParser = catalogDetailParser;
					}
					String imagePath = Config.getContextPath() + Config.getValue("UploadDir") + "/"
							+ SiteUtil.getAlias(catalog.getSiteID()) + "/";
					imagePath = imagePath.replaceAll("///", "/");
					imagePath = imagePath.replaceAll("//", "/");
					article.set("Content", article.getString("Content").replaceAll(imagePath,
							CatalogUtil.getLevelStr(detailLevel)));
					HtmlNameParser nameParser = new HtmlNameParser(site.toDataRow(), catalog
							.toDataRow(), article, catalog.getDetailNameRule());
					HtmlNameRule h = nameParser.getNameRule();
					String dirPath = rootPath + h.getDirPath();
					staticDoc("Article", detailParser, detailTemplateData, article, dirPath, h
							.getFileName());
				}
			} else {
				int total = (new QueryBuilder("select count(1) from zcarticle "
						+ wherePart.substring(0, wherePart.indexOf("order by")), param))
						.executeInt();
				if (total > 0) {
					int pageCount = total % pageSize != 0 ? total / pageSize + 1 : total / pageSize;
					catalogTemplateData.setTotal(total);
					catalogTemplateData.setPageCount(pageCount);
					catalogTemplateData.setPageSize(pageSize);
					int rowNo = 1;
					int pageIndex = 0;
					int batchPages = 10;
					if ("true".equals(Config.getValue("App.MinimalMemory")))
						batchPages = 3;
					int fetchCount = total / (batchPages * pageSize) + 1;
					if (publishPages != 0) {
						fetchCount = publishPages % batchPages != 0 ? publishPages / batchPages + 1
								: publishPages / batchPages;
						batchPages = publishPages <= batchPages ? publishPages : batchPages;
					}
					int dataSize = pageSize * batchPages;
					String cols = null;
					if (isGenerateDetail)
						cols = "*";
					else
						cols = "ID,SiteID,CatalogID,CatalogInnerCode,BranchInnerCode,Title,SubTitle,ShortTitle,TitleStyle,ShortTitleStyle,Author,type,URL,RedirectURL,Status,Summary,TopFlag,TemplateFlag,Template,CommentFlag,OrderFlag,ReferName,ReferURL,Keyword,RelativeArticle,RecommendArticle,ReferType,ReferSourceID,HitCount,PublishFlag,Priority,LockUser,PublishDate,DownlineDate,WorkFlowID,IssueID";
					for (int i = 0; i < fetchCount; i++) {
						qb = new QueryBuilder("select " + cols + " from zcarticle " + wherePart,
								param);
						dtArticle = qb.executePagedDataTable(dataSize, i);
						ColumnUtil.extendDocColumnData(dtArticle, catalog.getID());
						dtArticle.insertColumn("Link");
						int dtArticleSize = dtArticle.getRowCount();
						dtArticle.insertColumn("_RowNo");
						for (int j = 0; j < dtArticleSize; j++) {
							if (task.checkStop())
								return true;
							dtArticle.set(j, "_RowNo", rowNo++);
							DataRow article = dtArticle.getDataRow(j);
							String detailTemplateName = null;
							TemplateParser detailParser;
							if ("1".equals(article.getString("TemplateFlag"))) {
								detailTemplateName = article.getString("Template");
								detailTemplateName = templateDir + "/" + siteAlias
										+ detailTemplateName;
								detailTemplateName = detailTemplateName.replaceAll("//", "/");
								detailParser = getParser(site.getID(), detailTemplateName, level);
								if (detailParser == null) {
									task
											.addError("\u6587\u7AE0\u8BE6\u7EC6\u9875\u6307\u5B9A\u6A21\u677F\uFF1A"
													+ detailTemplateName + " \u4E0D\u5B58\u5728");
									detailParser = catalogDetailParser;
								}
							} else {
								detailParser = catalogDetailParser;
							}
							if ("4".equals(article.getString("Type"))) {
								dtArticle.getDataRow(j).set("Link",
										article.getString("RedirectURL"));
							} else {
								DataRow catalogRow = null;
								boolean currentCatalog = catalog.getID() == article
										.getLong("CatalogID");
								if (currentCatalog)
									catalogRow = catalog.toDataRow();
								HtmlNameParser nameParser = new HtmlNameParser(site.toDataRow(),
										catalogRow, article, catalog.getDetailNameRule());
								HtmlNameRule h = nameParser.getNameRule();
								level = h.getLevel();
								if (isGenerateDetail && currentCatalog) {
									String imagePath = Config.getContextPath()
											+ Config.getValue("UploadDir") + "/"
											+ SiteUtil.getAlias(catalog.getSiteID()) + "/";
									imagePath = imagePath.replaceAll("///", "/");
									imagePath = imagePath.replaceAll("//", "/");
									article.set("Content", article.getString("Content").replaceAll(
											imagePath, detailTemplateData.getLevelStr()));
									article
											.set("HitCount",
													getClickScript(article.getString("ID")));
									String dirPath = rootPath + h.getDirPath();
									if (StringUtil.isEmpty(h.getDirPath()))
										dirPath = rootPath;
									if (detailParser == null)
										continue;
									if (!staticDoc("Article", detailParser, detailTemplateData,
											article, dirPath, h.getFileName()))
										return false;
								}
								String link = catalogTemplateData.getLevelStr() + h.getFullPath();
								dtArticle.getDataRow(j).set("Link", link);
							}
							detailParser = catalogDetailParser;
						}

						for (int k = 0; k < batchPages; k++) {
							catalogTemplateData.setPageIndex(pageIndex);
							int count = pageSize;
							if ((k + 1) * pageSize > dtArticleSize)
								count = dtArticleSize - k * pageSize;
							if (count <= 0)
								break;
							Object values[][] = new Object[count][dtArticle.getColCount()];
							for (int j = 0; j < count; j++)
								values[j] = dtArticle.getDataRow(k * pageSize + j).getDataValues();

							catalogTemplateData.setListTable(new DataTable(dtArticle
									.getDataColumns(), values));
							String statScript = "";
							if ("Y".equals(site.getAutoStatFlag())) {
								String serviceUrl = Config.getValue("ServicesContext");
								String statUrl = "SiteID=" + site.getID()
										+ "&Type=Article&CatalogInnerCode="
										+ catalog.getInnerCode() + "&Dest=" + serviceUrl
										+ "/Stat.jsp";
								statScript = getStatScript(statUrl);
							}
							String dirPath = rootPath + CatalogUtil.getPath(catalog.getID());
							if (!writeListHTML(site.getID(), listParser, catalogTemplateData,
									dirPath, listNameRule, pageIndex, statScript))
								return false;
							pageIndex++;
						}

					}

				} else {
					catalogTemplateData.setTotal(total);
					catalogTemplateData.setPageCount(0);
					catalogTemplateData.setPageSize(pageSize);
					catalogTemplateData.setPageIndex(0);
					catalogTemplateData.setListTable(new DataTable());
					String statScript = "";
					if ("Y".equals(site.getAutoStatFlag())) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + site.getID()
								+ "&Type=Article&CatalogInnerCode=" + catalog.getInnerCode()
								+ "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}
					String dirPath = rootPath + CatalogUtil.getPath(catalog.getID());
					if (!writeListHTML(site.getID(), listParser, catalogTemplateData, dirPath,
							listNameRule, 0, statScript))
						return false;
				}
			}
		} else if (type == 4) {
			rootPath = rootPath + CatalogUtil.getPath(catalog.getID());
			String detailPath = rootPath;
			ZCImageSchema image = new ZCImageSchema();
			ZCImageSet set = image.query(new QueryBuilder(" where " + catalogWherePart
					+ " order by id desc", param));
			DataTable dt = set.toDataTable();
			if (dt != null && dt.getRowCount() > 0) {
				if (catalogDetailParser != null) {
					dt.insertColumn("Link");
					dt.insertColumn("PrevLink");
					dt.insertColumn("NextLink");
					dt.insertColumn("PrevFileName");
					dt.insertColumn("NextFileName");
					dt.insertColumn("articleLink");
					DataRow preRow = null;
					for (int j = 0; j < dt.getRowCount(); j++) {
						DataRow row = dt.getDataRow(j);
						row.set("Link", row.get("ID") + ".shtml");
						if (j == 0) {
							row.set("PrevLink", "#");
							row.set("PrevFileName", row.get("FileName"));
						} else {
							row.set("PrevLink", preRow.get("Link"));
							row.set("PrevFileName", preRow.get("FileName"));
						}
						if (j != dt.getRowCount() - 1) {
							row.set("NextLink", dt.getDataRow(j + 1).get("ID") + ".shtml");
							row.set("NextFileName", dt.getDataRow(j + 1).get("FileName"));
						} else {
							row.set("NextLink", "#");
							row.set("NextFileName", row.get("FileName"));
						}
						DataTable imageRelaTable = (new QueryBuilder(
								"select a.id as imageID,b.id,b.catalogid,b.publishdate from zcimagerela a ,zcarticle b where a.relatype='ArticleImage' and a.relaid=b.id and a.id=?",
								row.get("ID"))).executeDataTable();
						if (imageRelaTable != null && imageRelaTable.getRowCount() > 0) {
							String articleCatalogID = imageRelaTable.getString(0, "catalogid");
							String nameRule = CatalogUtil.getTemplateRule(articleCatalogID);
							if (StringUtil.isNotEmpty(nameRule)) {
								HtmlNameParser nameParser = new HtmlNameParser(site.toDataRow(),
										null, imageRelaTable.get(0), nameRule);
								HtmlNameRule h = nameParser.getNameRule();
								row.set("articlelink", catalogTemplateData.getLevelStr()
										+ h.getFullPath());
							} else {
								String path = catalogTemplateData.getLevelStr()
										+ CatalogUtil.getPath(articleCatalogID)
										+ imageRelaTable.getString(0, "id") + ".shtml";
								row.set("articlelink", path);
							}
						} else {
							row.set("articlelink", "#");
						}
						preRow = row;
						catalogDetailParser.define("TemplateData", catalogTemplateData);
						catalogDetailParser.define(detailType, row);
						catalogDetailParser.define("site", catalogTemplateData.getSite());
						catalogDetailParser.define("catalog", catalogTemplateData.getCatalog());
						String nameRule = row.getString("ID") + ".shtml";
						String statScript = "";
						if ("Y".equals(site.getAutoStatFlag())) {
							String serviceUrl = Config.getValue("ServicesContext");
							String statUrl = "SiteID=" + site.getID()
									+ "&Type=Image&CatalogInnerCode=" + catalog.getInnerCode()
									+ "&LeafID=" + row.getString("ID") + "&Dest=" + serviceUrl
									+ "/Stat.jsp";
							statScript = getStatScript(statUrl);
						}
						writeHTML(catalogDetailParser, detailPath, nameRule, statScript);
					}

				}
				int dtSize = dt.getRowCount();
				int total = dtSize;
				int pageCount = total % pageSize != 0 ? total / pageSize + 1 : total / pageSize;
				catalogTemplateData.setTotal(total);
				catalogTemplateData.setPageCount(pageCount);
				catalogTemplateData.setPageSize(pageSize);
				int pageIndex = 0;
				for (int k = 0; k < pageCount; k++) {
					catalogTemplateData.setPageIndex(pageIndex);
					int count = pageSize;
					if ((k + 1) * pageSize > dtSize)
						count = dtSize - k * pageSize;
					if (count <= 0)
						break;
					Object values[][] = new Object[count][dt.getColCount()];
					for (int j = 0; j < count; j++)
						values[j] = dt.getDataRow(k * pageSize + j).getDataValues();

					catalogTemplateData.setListTable(new DataTable(dt.getDataColumns(), values));
					String statScript = "";
					if ("Y".equals(site.getAutoStatFlag())) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + site.getID() + "&Type=Image&CatalogInnerCode="
								+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}
					if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
							listNameRule, pageIndex, statScript))
						return false;
					pageIndex++;
				}

			} else {
				catalogTemplateData.setTotal(0);
				catalogTemplateData.setPageCount(0);
				catalogTemplateData.setPageSize(pageSize);
				catalogTemplateData.setPageIndex(0);
				catalogTemplateData.setListTable(new DataTable());
				String statScript = "";
				if ("Y".equals(site.getAutoStatFlag())) {
					String serviceUrl = Config.getValue("ServicesContext");
					String statUrl = "SiteID=" + site.getID() + "&Type=Article&CatalogInnerCode="
							+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
					statScript = getStatScript(statUrl);
				}
				if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
						listNameRule, 0, statScript))
					return false;
			}
		} else if (type == 5) {
			rootPath = rootPath + CatalogUtil.getPath(catalog.getID());
			String detailPath = rootPath;
			ZCVideoSchema video = new ZCVideoSchema();
			ZCVideoSet set = video.query(new QueryBuilder(" where " + catalogWherePart
					+ " order by id desc", param));
			DataTable dt = set.toDataTable();
			if (dt != null && dt.getRowCount() > 0) {
				if (catalogDetailParser != null) {
					dt.insertColumn("Link");
					dt.insertColumn("filePath");
					for (int j = 0; j < dt.getRowCount(); j++) {
						dt.set(j, "Link", dt.getString(j, "ID") + ".shtml");
						int count = Integer.parseInt(dt.getString(j, "Count"));
						String path = "";
						for (int i = 0; i < count; i++) {
							if (i != 0)
								path = path + "|";
							path = path + dt.getString(j, "path") + i + "_"
									+ dt.getString(j, "FileName");
						}

						dt.set(j, "filePath", path);
						catalogDetailParser.define("TemplateData", catalogTemplateData);
						catalogDetailParser.define(detailType, dt.getDataRow(j));
						catalogDetailParser.define("site", catalogTemplateData.getSite());
						catalogDetailParser.define("catalog", catalogTemplateData.getCatalog());
						String nameRule = dt.getDataRow(j).getString("ID") + ".shtml";
						String statScript = "";
						if ("Y".equals(site.getAutoStatFlag())) {
							String serviceUrl = Config.getValue("ServicesContext");
							String statUrl = "SiteID=" + site.getID()
									+ "&Type=Video&CatalogInnerCode=" + catalog.getInnerCode()
									+ "&LeafID=" + dt.getDataRow(j).getString("ID") + "&Dest="
									+ serviceUrl + "/Stat.jsp";
							statScript = getStatScript(statUrl);
						}
						writeHTML(catalogDetailParser, detailPath, nameRule, statScript);
					}

				}
				int dtSize = dt.getRowCount();
				int total = dtSize;
				int pageCount = total % pageSize != 0 ? total / pageSize + 1 : total / pageSize;
				catalogTemplateData.setTotal(total);
				catalogTemplateData.setPageCount(pageCount);
				catalogTemplateData.setPageSize(pageSize);
				int pageIndex = 0;
				for (int k = 0; k < pageCount; k++) {
					catalogTemplateData.setPageIndex(pageIndex);
					int count = pageSize;
					if ((k + 1) * pageSize > dtSize)
						count = dtSize - k * pageSize;
					if (count <= 0)
						break;
					Object values[][] = new Object[count][dt.getColCount()];
					for (int j = 0; j < count; j++)
						values[j] = dt.getDataRow(k * pageSize + j).getDataValues();

					catalogTemplateData.setListTable(new DataTable(dt.getDataColumns(), values));
					String statScript = "";
					if ("Y".equals(site.getAutoStatFlag())) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + site.getID() + "&Type=Video&CatalogInnerCode="
								+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}
					if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
							listNameRule, pageIndex, statScript))
						return false;
					pageIndex++;
				}

			} else {
				catalogTemplateData.setTotal(0);
				catalogTemplateData.setPageCount(0);
				catalogTemplateData.setPageSize(pageSize);
				catalogTemplateData.setPageIndex(0);
				catalogTemplateData.setListTable(new DataTable());
				String statScript = "";
				if ("Y".equals(site.getAutoStatFlag())) {
					String serviceUrl = Config.getValue("ServicesContext");
					String statUrl = "SiteID=" + site.getID() + "&Type=Article&CatalogInnerCode="
							+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
					statScript = getStatScript(statUrl);
				}
				if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
						listNameRule, 0, statScript))
					return false;
			}
		} else if (type == 6) {
			rootPath = rootPath + CatalogUtil.getPath(catalog.getID());
			String detailPath = rootPath;
			ZCAudioSchema audio = new ZCAudioSchema();
			ZCAudioSet set = audio.query(new QueryBuilder(" where " + catalogWherePart
					+ " order by id desc", param));
			DataTable dt = set.toDataTable();
			if (dt != null && dt.getRowCount() > 0) {
				if (catalogDetailParser != null) {
					for (int j = 0; j < set.size(); j++) {
						audio = set.get(j);
						catalogDetailParser.define("TemplateData", catalogTemplateData);
						catalogDetailParser.define(detailType, dt.getDataRow(j));
						catalogDetailParser.define("site", catalogTemplateData.getSite());
						catalogDetailParser.define("catalog", catalogTemplateData.getCatalog());
						String nameRule = dt.getDataRow(j).getString("ID") + ".shtml";
						String statScript = "";
						if ("Y".equals(site.getAutoStatFlag())) {
							String serviceUrl = Config.getValue("ServicesContext");
							String statUrl = "SiteID=" + site.getID()
									+ "&Type=Audio&CatalogInnerCode=" + catalog.getInnerCode()
									+ "&LeafID=" + dt.getDataRow(j).getString("ID") + "&Dest="
									+ serviceUrl + "/Stat.jsp";
							statScript = getStatScript(statUrl);
						}
						writeHTML(catalogDetailParser, detailPath, nameRule, statScript);
					}

				}
				int dtSize = dt.getRowCount();
				int total = dtSize;
				int pageCount = total % pageSize != 0 ? total / pageSize + 1 : total / pageSize;
				catalogTemplateData.setTotal(total);
				catalogTemplateData.setPageCount(pageCount);
				catalogTemplateData.setPageSize(pageSize);
				int pageIndex = 0;
				for (int k = 0; k < pageCount; k++) {
					catalogTemplateData.setPageIndex(pageIndex);
					int count = pageSize;
					if ((k + 1) * pageSize > dtSize)
						count = dtSize - k * pageSize;
					if (count <= 0)
						break;
					Object values[][] = new Object[count][dt.getColCount()];
					for (int j = 0; j < count; j++)
						values[j] = dt.getDataRow(k * pageSize + j).getDataValues();

					catalogTemplateData.setListTable(new DataTable(dt.getDataColumns(), values));
					String statScript = "";
					if ("Y".equals(site.getAutoStatFlag())) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + site.getID() + "&Type=Audio&CatalogInnerCode="
								+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}
					if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
							listNameRule, pageIndex, statScript))
						return false;
					pageIndex++;
				}

			} else {
				catalogTemplateData.setTotal(0);
				catalogTemplateData.setPageCount(0);
				catalogTemplateData.setPageSize(pageSize);
				catalogTemplateData.setPageIndex(0);
				catalogTemplateData.setListTable(new DataTable());
				String statScript = "";
				if ("Y".equals(site.getAutoStatFlag())) {
					String serviceUrl = Config.getValue("ServicesContext");
					String statUrl = "SiteID=" + site.getID() + "&Type=Article&CatalogInnerCode="
							+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
					statScript = getStatScript(statUrl);
				}
				if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
						listNameRule, 0, statScript))
					return false;
			}
		} else if (type == 7) {
			rootPath = rootPath + CatalogUtil.getPath(catalog.getID());
			String detailPath = rootPath;
			ZCAttachmentSchema attach = new ZCAttachmentSchema();
			ZCAttachmentSet set = attach.query(new QueryBuilder(" where " + catalogWherePart
					+ " order by id desc", param));
			DataTable dt = set.toDataTable();
			if (dt != null && dt.getRowCount() > 0) {
				if (catalogDetailParser != null) {
					for (int j = 0; j < set.size(); j++) {
						attach = set.get(j);
						catalogDetailParser.define("TemplateData", catalogTemplateData);
						catalogDetailParser.define(detailType, dt.getDataRow(j));
						catalogDetailParser.define("site", catalogTemplateData.getSite());
						catalogDetailParser.define("catalog", catalogTemplateData.getCatalog());
						String nameRule = dt.getDataRow(j).getString("ID") + ".shtml";
						String statScript = "";
						if ("Y".equals(site.getAutoStatFlag())) {
							String serviceUrl = Config.getValue("ServicesContext");
							String statUrl = "SiteID=" + site.getID()
									+ "&Type=Attachment&CatalogInnerCode=" + catalog.getInnerCode()
									+ "&LeafID=" + dt.getDataRow(j).getString("ID") + "&Dest="
									+ serviceUrl + "/Stat.jsp";
							statScript = getStatScript(statUrl);
						}
						writeHTML(catalogDetailParser, detailPath, nameRule, statScript);
					}

				}
				int dtSize = dt.getRowCount();
				int total = dtSize;
				int pageCount = total % pageSize != 0 ? total / pageSize + 1 : total / pageSize;
				catalogTemplateData.setTotal(total);
				catalogTemplateData.setPageCount(pageCount);
				catalogTemplateData.setPageSize(pageSize);
				int pageIndex = 0;
				for (int k = 0; k < pageCount; k++) {
					catalogTemplateData.setPageIndex(pageIndex);
					int count = pageSize;
					if ((k + 1) * pageSize > dtSize)
						count = dtSize - k * pageSize;
					if (count <= 0)
						break;
					Object values[][] = new Object[count][dt.getColCount()];
					for (int j = 0; j < count; j++)
						values[j] = dt.getDataRow(k * pageSize + j).getDataValues();

					catalogTemplateData.setListTable(new DataTable(dt.getDataColumns(), values));
					String statScript = "";
					if ("Y".equals(site.getAutoStatFlag())) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + site.getID()
								+ "&Type=Attachment&CatalogInnerCode=" + catalog.getInnerCode()
								+ "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}
					if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
							listNameRule, pageIndex, statScript))
						return false;
					pageIndex++;
				}

			} else {
				catalogTemplateData.setTotal(0);
				catalogTemplateData.setPageCount(0);
				catalogTemplateData.setPageSize(pageSize);
				catalogTemplateData.setPageIndex(0);
				catalogTemplateData.setListTable(new DataTable());
				String statScript = "";
				if ("Y".equals(site.getAutoStatFlag())) {
					String serviceUrl = Config.getValue("ServicesContext");
					String statUrl = "SiteID=" + site.getID() + "&Type=Article&CatalogInnerCode="
							+ catalog.getInnerCode() + "&Dest=" + serviceUrl + "/Stat.jsp";
					statScript = getStatScript(statUrl);
				}
				if (!writeListHTML(site.getID(), listParser, catalogTemplateData, rootPath,
						listNameRule, 0, statScript))
					return false;
			}
		}
		staticInnerPageBlock(site, catalog);
		staticPageBlock(site, catalog, 4);
		LogUtil.info("\u751F\u6210\u680F\u76EE" + catalog.getName() + "\u8017\u65F6"
				+ (System.currentTimeMillis() - t));
		return flag;
	}

	public boolean staticChildCatalog(long parentID, boolean detail, int publishSize) {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		ZCCatalogSet catalogSet = catalog.query(new QueryBuilder("where parentid=?", parentID));
		for (int i = 0; i < catalogSet.size(); ++i) {
			catalog = catalogSet.get(i);
			if (!(staticCatalog(catalog, true, publishSize))) {
				return false;
			}
			if (!(staticChildCatalog(catalogSet.get(i).getID(), detail, publishSize))) {
				return false;
			}
		}
		return true;
	}

	public boolean staticChildCatalog(long parentID, boolean detail) {
		return staticChildCatalog(parentID, detail, 0);
	}

	public boolean staticSite(long siteID) {
		TemplateCache.clear();

		this.task.setCurrentInfo("正在处理区块");
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(siteID);
		site.fill();

		if (!(staticPageBlock(site, null, 0))) {
			return false;
		}
		this.task.setPercent(4);

		this.task.setCurrentInfo("正在处理站点首页");
		if (!(staticSiteIndex(site))) {
			return false;
		}

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		ZCCatalogSet catalogSet = catalog.query(new QueryBuilder(
				"where siteid=? and parentid=0 and type in (1,9)", siteID));
		for (int i = 0; i < catalogSet.size(); ++i) {
			if (this.task.checkStop()) {
				return true;
			}
			catalog = catalogSet.get(i);
			this.task.setCurrentInfo("正在生成栏目" + catalog.getName());
			if (!(staticCatalog(catalog, true))) {
				return false;
			}
			if (!(staticChildCatalog(catalog.getID(), true))) {
				return false;
			}
			LogUtil.info("percent:" + (4 + i * 90 / catalogSet.size()));
			this.task.setPercent(4 + i * 90 / catalogSet.size());
		}
		this.task.setPercent(98);
		return true;
	}

	public boolean staticSiteIndex(long siteID) {
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(siteID);
		if (!(site.fill())) {
			return false;
		}
		this.task.setCurrentInfo("发布站点首页");

		if (!(staticPageBlock(site, null, 0))) {
			return false;
		}

		if (this.task.getPercent() < 40) {
			this.task.setPercent(40);
		}

		return staticSiteIndex(site);
	}

	public boolean staticSiteIndex(ZCSiteSchema site) {
		long t = System.currentTimeMillis();
		TemplateCache.clear();

		String template = this.templateDir + "/" + site.getAlias() + "/" + site.getIndexTemplate();
		if (template == null) {
			return false;
		}
		template = template.replaceAll("//", "/");
		TemplateParser tp = getParser(site.getID(), template, 0);
		LogUtil.info("编译中间脚本:" + (System.currentTimeMillis() - t));

		if (tp == null) {
			return false;
		}

		CmsTemplateData templateData = new CmsTemplateData();
		templateData.setSite(site);
		tp.define("TemplateData", templateData);
		tp.define("site", site.toDataRow());
		String path = SiteUtil.getAbsolutePath(site.getID()) + "/";
		String fileName = "index.shtml";

		String statScript = "";
		if ("Y".equals(site.getAutoStatFlag())) {
			String serviceUrl = Config.getValue("ServicesContext");
			String statUrl = "SiteID=" + site.getID() + "&Type=Article&Dest=" + serviceUrl
					+ "/Stat.jsp";
			statScript = getStatScript(statUrl);
		}

		statScript = statScript + "\n<!-- Published by " + this.product.getAppCode() + "("
				+ this.product.getAppName() + ") " + this.product.getMainVersion()
				+ " PublishDate " + DateUtil.getCurrentDateTime() + "-->";

		this.task.setCurrentInfo("生成首页文件：" + fileName);

		boolean flag = writeHTML(tp, path, fileName, statScript);

		template = template.replaceAll(this.staticDir, "");
		int level = 0;
		ZCPageBlockSet blockSet = new ZCPageBlockSchema().query(new QueryBuilder(
				"where id in (select blockid from ZCTemplateBlockRela where filename='" + template
						+ "_" + level + "')"));
		if ((blockSet != null) && (blockSet.size() > 0)) {
			staticPageBlock(blockSet);
		}

		LogUtil.info("生成首页耗时:" + (System.currentTimeMillis() - t));
		return flag;
	}

	public boolean staticDoc(String docType, Schema doc) {
		TemplateCache.clear();

		DataRow docDataRow = doc.toDataRow();
		String templateName = "";
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(docDataRow.getString("CatalogID"));
		if (!(catalog.fill())) {
			return false;
		}

		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(catalog.getSiteID());
		site.fill();

		DataTable dt = new DataTable();
		dt.insertRow(docDataRow);
		ColumnUtil.extendDocColumnData(dt, catalog.getID());
		docDataRow = dt.get(0);

		templateName = catalog.getDetailTemplate();

		String siteCode = site.getAlias();
		if ("1".equals(docDataRow.getString("TemplateFlag"))) {
			templateName = docDataRow.getString("Template");
		}

		templateName = this.templateDir + "/" + siteCode + templateName;
		templateName = templateName.replaceAll("//", "/");

		String imagePath = Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(catalog.getSiteID()) + "/";
		imagePath = imagePath.replaceAll("///", "/");
		imagePath = imagePath.replaceAll("//", "/");
		String rootPath = this.staticDir + "/" + site.getAlias() + "/";

		HtmlNameParser nameParser = new HtmlNameParser(site.toDataRow(), catalog.toDataRow(),
				docDataRow, catalog.getDetailNameRule());
		HtmlNameRule h = nameParser.getNameRule();
		int level = h.getLevel();
		rootPath = rootPath + h.getDirPath();

		if (StringUtil.isNotEmpty(docDataRow.getString("Content"))) {
			docDataRow.set("Content", docDataRow.getString("Content").replaceAll(imagePath,
					CatalogUtil.getLevelStr(level)));
		}

		if (StringUtil.isNotEmpty(docDataRow.getString("HitCount"))) {
			docDataRow.set("HitCount", getClickScript(docDataRow.getString("ID")));
		}

		TemplateParser parser = getParser(site.getID(), templateName, level);
		if (parser == null) {
			this.task.addError("没有找到对应的模板文件" + catalog.getDetailTemplate());
			return false;
		}

		CmsTemplateData templateData = new CmsTemplateData();
		templateData.setLevel(level);
		templateData.setSite(site);
		templateData.setCatalog(catalog);

		staticDoc(docType, parser, templateData, docDataRow, rootPath, h.getFileName());
		return true;
	}

	private boolean staticDoc(String docType, TemplateParser parser, CmsTemplateData templateData,
			DataRow docDataRow, String rootPath, String fileName) {
		parser.define("site", templateData.getSite());
		parser.define("catalog", templateData.getCatalog());

		File f = new File(rootPath);
		if (!(f.exists())) {
			f.mkdirs();
		}

		if ("article".equalsIgnoreCase(docType)) {
			String content = docDataRow.getString("Content");

			String cid = docDataRow.getString("CatalogID");
			String keyWordFlag = CatalogUtil.getHotWordFlag(cid);
			while ((!("Y".equals(keyWordFlag))) && (!("N".equals(keyWordFlag)))
					&& (!("0".equalsIgnoreCase(cid)))) {
				cid = CatalogUtil.getParentID(cid);
				keyWordFlag = CatalogUtil.getHotWordFlag(cid);
			}

			boolean keywordflag = false;
			if ("Y".equals(keyWordFlag))
				keywordflag = true;
			else {
				keywordflag = false;
			}
			ZCKeywordSet keywordSet = (ZCKeywordSet) Keyword.KeyWordMap.get(docDataRow
					.getString("SiteID"));
			if ((keywordflag) && (keywordSet != null) && (keywordSet.size() > 0)) {
				String serviceUrl = Config.getValue("ServicesContext");
				String context = serviceUrl;
				if (serviceUrl.endsWith("/")) {
					context = serviceUrl.substring(0, serviceUrl.length() - 1);
				}
				int index = context.lastIndexOf(47);
				if (index != -1) {
					context = context.substring(0, index);
				}
				String searchUrl = context + "/Search/Result.jsp";
				Mapx keyWordCache = new Mapx();
				for (int i = 0; i < keywordSet.size(); ++i) {
					ZCKeywordSchema keyword = keywordSet.get(i);
					String word = keyword.getKeyword();
					String url = keyword.getLinkUrl();
					if (StringUtil.isEmpty(url)) {
						url = searchUrl + "?site=" + docDataRow.getString("SiteID") + "&query="
								+ word;
					}
					String target = keyword.getLinkTarget();
					String alt = keyword.getLinkAlt();
					if (StringUtil.isEmpty(alt)) {
						alt = word;
					}
					String text = "<a href='" + url + "' target='" + target + "' title='" + alt
							+ "'>" + word + "</a>";
					String md5data = StringUtil.md5Hex(String.valueOf(i));
					keyWordCache.put(md5data, text);
					content = content.replaceAll(word, md5data);
				}

				DataTable dtCache = keyWordCache.toDataTable();
				for (int i = 0; i < keywordSet.size(); ++i) {
					content = content.replaceAll(dtCache.getString(i, 0), dtCache.getString(i, 1));
				}

			}

			String[] pages = content.split("<!--_ZVING_PAGE_BREAK_-->");
			if (pages.length > 0) {
				templateData.setPageCount(pages.length);
				templateData.setPageSize(1);
				templateData.setTotal(pages.length);
				templateData.setFirstFileName(fileName);

				int index = fileName.lastIndexOf(".");
				String otherPageName = null;
				if (index != -1)
					otherPageName = fileName.substring(0, index) + "_@INDEX"
							+ fileName.substring(index);
				else {
					otherPageName = otherPageName + "_@INDEX";
				}
				templateData.setOtherFileName(otherPageName);

				for (int k = 0; k < pages.length; ++k) {
					docDataRow.set("Content", pages[k]);
					templateData.setPageIndex(k);
					templateData.setDoc(docDataRow);

					parser.define("TemplateData", templateData);
					parser.define(docType.toLowerCase(), docDataRow);
					parser.define("page", templateData.getPageRow());
					String pageName = fileName;
					if (k > 0) {
						pageName = otherPageName.replaceAll("@INDEX", String.valueOf(k + 1));
					}

					String statScript = "";
					if ("Y".equals(templateData.getSite().getString("AutoStatFlag"))) {
						String serviceUrl = Config.getValue("ServicesContext");
						String statUrl = "SiteID=" + docDataRow.getString("SiteID") + "&Type="
								+ docType + "&CatalogInnerCode="
								+ docDataRow.getString("CatalogInnerCode") + "&LeafID="
								+ docDataRow.getString("ID") + "&Dest=" + serviceUrl + "/Stat.jsp";
						statScript = getStatScript(statUrl);
					}

					statScript = statScript + "\n<!-- Published by " + this.product.getAppCode()
							+ " " + this.product.getMainVersion() + " PublishDate "
							+ DateUtil.getCurrentDateTime() + "-->";
					if (!(writeHTML(parser, rootPath, pageName, statScript)))
						return false;
				}
			}
		} else {
			templateData.setDoc(docDataRow);
			parser.define("TemplateData", templateData);
			parser.define(docType.toLowerCase(), docDataRow);
			String pageName = fileName;

			String statScript = "";
			if ("Y".equals(templateData.getSite().getString("AutoStatFlag"))) {
				String serviceUrl = Config.getValue("ServicesContext");
				String statUrl = "SiteID=" + docDataRow.getString("SiteID") + "&Type=" + docType
						+ "&CatalogInnerCode=" + docDataRow.getString("CatalogInnerCode")
						+ "&LeafID=" + docDataRow.getString("ID") + "&Dest=" + serviceUrl
						+ "/Stat.jsp";
				statScript = getStatScript(statUrl);
			}

			statScript = statScript + "\n<!-- Published by " + this.product.getAppCode() + " "
					+ this.product.getMainVersion() + " PublishDate "
					+ DateUtil.getCurrentDateTime() + "-->";

			if (!(writeHTML(parser, rootPath, pageName, statScript))) {
				return false;
			}
		}

		return true;
	}

	public boolean staticPageBlock(ZCPageBlockSet set) {
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(set.get(0).getSiteID());
		site.fill();

		Mapx catalogMap = new Mapx();

		for (int i = 0; i < set.size(); ++i) {
			ZCCatalogSchema catalog = (ZCCatalogSchema) catalogMap.get(set.get(i).getCatalogID());
			if (catalog == null) {
				catalog = new ZCCatalogSchema();
				catalog.setID(set.get(i).getCatalogID());
				if (!(catalog.fill())) {
					catalog = null;
				}

				catalogMap.put(set.get(i).getCatalogID(), catalog);
			}

			if (!(staticOnePageBlock(site, catalog, set.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean staticPageBlock(ZCSiteSchema site, ZCCatalogSchema catalog, int type) {
		if (site == null) {
			return false;
		}
		QueryBuilder qb = null;
		String wherePart = (type == 4) ? "where type=4" : "where type<>4";
		if (catalog != null)
			wherePart = wherePart + " and catalogid=" + catalog.getID();
		else {
			wherePart = wherePart + " and catalogid is null and siteid=" + site.getID();
		}
		qb = new QueryBuilder(wherePart);
		ZCPageBlockSet set = new ZCPageBlockSchema().query(qb);
		if (set.size() > 0) {
			for (int i = 0; i < set.size(); ++i) {
				ZCPageBlockSchema block = set.get(i);
				if ((!(staticOnePageBlock(site, catalog, block))) && (type != 4)) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean staticInnerPageBlock(ZCSiteSchema site, ZCCatalogSchema catalog) {
		if (site == null) {
			return false;
		}
		String alias = site.getAlias();

		String indexTemplate = catalog.getIndexTemplate();
		if (StringUtil.isNotEmpty(indexTemplate)) {
			indexTemplate = "/" + alias + indexTemplate + "_" + catalog.getTreeLevel();
			staticInnerPageBlock(site, catalog, indexTemplate);
		}

		String listTemplate = catalog.getListTemplate();
		if (StringUtil.isNotEmpty(listTemplate)) {
			listTemplate = "/" + alias + listTemplate + "_" + catalog.getTreeLevel();
			staticInnerPageBlock(site, catalog, listTemplate);
		}

		String detailTemplate = catalog.getDetailTemplate();
		if (StringUtil.isNotEmpty(detailTemplate)) {
			detailTemplate = "/" + alias + detailTemplate + "_"
					+ CatalogUtil.getPathLevel(catalog.getDetailNameRule(), 0);
			staticInnerPageBlock(site, catalog, detailTemplate);
		}

		return true;
	}

	private boolean staticInnerPageBlock(ZCSiteSchema site, ZCCatalogSchema catalog,
			String templateFile) {
		QueryBuilder qb = new QueryBuilder(
				"where exists (select blockid from  ZCTemplateBlockRela where filename='"
						+ templateFile + "' and blockid=zcpageblock.id)");
		ZCPageBlockSet set = new ZCPageBlockSchema().query(qb);
		if (set.size() > 0) {
			for (int i = 0; i < set.size(); ++i) {
				ZCPageBlockSchema block = set.get(i);
				String targetFile = this.templateDir + "/" + site.getAlias() + "/"
						+ block.getFileName();
				File f = new File(targetFile);
				if ((!(f.exists())) && (!(staticOnePageBlock(site, catalog, block)))) {
					return false;
				}
			}

		}

		return true;
	}

	public boolean staticOnePageBlock(ZCSiteSchema site, ZCCatalogSchema catalog,
			ZCPageBlockSchema block) {
		TemplateCache.clear();
		String templateName = block.getTemplate();
		int level = StringUtil.count(block.getFileName(), "/");
		if (level > 0) {
			--level;
		}
		if (block.getType() == 4L) {
			String code = block.getCode();
			if (code.lastIndexOf("_") != -1) {
				level = Integer.parseInt(code.substring(code.lastIndexOf("_") + 1));
			}
		}

		if (block.getType() != 3L) {
			templateName = this.templateDir + "/" + site.getAlias() + "/" + block.getTemplate();
			templateName = templateName.replace('\\', '/').replaceAll("//", "/");
		}

		String filename = block.getFileName();
		String path = SiteUtil.getAbsolutePath(block.getSiteID());
		if (block.getType() == 3L) {
			File f = new File(path);
			if (!(f.exists())) {
				f.mkdirs();
			}
			String fullPath = path + filename;
			FileUtil.writeText(fullPath, block.getContent());
		} else {
			TemplateParser parser = getParser(site.getID(), templateName, level, true);

			if (parser == null) {
				if (block.getType() == 4L) {
					return true;
				}
				this.task.addError("没有找到附带发布" + block.getName() + "对应的模板文件" + templateName);
				Errorx.addError("没有找到附带发布" + block.getName() + "对应的模板文件" + templateName);
				return false;
			}

			CmsTemplateData templateData = new CmsTemplateData();
			templateData.setLevel(level);
			templateData.setSite(site);
			templateData.setBlock(block.toDataRow());

			if (catalog != null) {
				templateData.setCatalog(catalog);
				parser.define("catalog", templateData.getCatalog());
			}
			parser.define("site", templateData.getSite());
			parser.define("TemplateData", templateData);

			this.task.setCurrentInfo("区块文件：" + filename);

			if (!(writeHTML(parser, path, filename, ""))) {
				if (block.getType() == 4L) {
					return true;
				}
				this.task.addError("生成区块发生错误。生成文件：" + filename);
				return false;
			}

		}

		String includeFile = this.staticDir + "/" + site.getAlias() + "/" + block.getFileName();
		includeFile = includeFile.replace('\\', '/').replaceAll("//", "/");
		generateIncludeFiles(includeFile);

		return true;
	}

	private TemplateParser getParser(long siteID, String template, int level) {
		return getParser(siteID, template, level, false);
	}

	private TemplateParser getParser(long siteID, String template, int level, boolean isPageBlock) {
		if ((template == null) || ("".equals(template))) {
			return null;
		}
		TemplateParser parser = ParserCache.get(siteID, template, level, isPageBlock);

		return parser;
	}

	private void generateIncludeFiles(String includeFile) {
		String fileName = includeFile.substring(includeFile.lastIndexOf("/") + 1, includeFile
				.lastIndexOf("."));
		String includeDir = includeFile.substring(0, includeFile.lastIndexOf("/"));
		File files = new File(includeDir);
		if (files.exists()) {
			File[] fileList = files.listFiles();
			for (int i = 0; i < fileList.length; ++i) {
				File tmpFile = fileList[i];
				String tmpFileName = tmpFile.getName();
				if ((!(tmpFileName.startsWith(fileName + "_")))
						|| (tmpFileName.lastIndexOf("_") == -1))
					continue;
				String levelStr = "";
				if (tmpFileName.lastIndexOf(".") != -1)
					levelStr = tmpFileName.substring(tmpFileName.lastIndexOf("_") + 1, tmpFileName
							.lastIndexOf("."));
				else {
					levelStr = tmpFileName.substring(tmpFileName.lastIndexOf("_") + 1);
				}

				if (!(StringUtil.isDigit(levelStr))) {
					continue;
				}

				int level = Integer.parseInt(levelStr);
				String levelString = "";
				for (int j = 0; j < level; ++j) {
					levelString = levelString + "../";
				}
				TagParser parser = new TagParser();
				parser.setPageBlock(false);
				String includeContent = parser.dealResource(FileUtil.readText(includeFile),
						levelString);
				FileUtil.writeText(tmpFile.getAbsolutePath(), includeContent);
			}
		}
	}

	public boolean writeHTML(TemplateParser tp, String path, String nameRule, String statScript) {
		long t = System.currentTimeMillis();
		try {
			tp.generate();
		} catch (Exception e) {
			String errorMessage = "模板解析错误，请检查模板" + tp.getFileName() + "是否正确。错误信息：" + e.getMessage();
			Errorx.addError(errorMessage);
			this.task.addError(errorMessage);
			LogUtil.info(errorMessage);
			return false;
		}
		LogUtil.info("解析页面耗时：" + (System.currentTimeMillis() - t));

		String html = tp.getResult();
		html = html + statScript;

		String fileName = path + "/" + nameRule;

		fileName = fileName.replaceAll("///", "/");
		fileName = fileName.replaceAll("//", "/");

		String filePath = fileName.substring(0, fileName.lastIndexOf(47));
		File f = new File(filePath);
		if (!(f.exists())) {
			f.mkdirs();
		}

		FileUtil.writeText(fileName, html);
		LogUtil.info("生成" + fileName + " 页面耗时：" + (System.currentTimeMillis() - t));

		this.fileList.add(fileName);

		return true;
	}

	public boolean writeListHTML(long siteID, TemplateParser tp, CmsTemplateData templateData,
			String path, String listNameRule, int pageIndex, String statScript) {
		String firstFileName = listNameRule;

		int index = firstFileName.lastIndexOf(".");
		String otherFileName;
		if (index != -1)
			otherFileName = firstFileName.substring(0, index) + "_@INDEX"
					+ firstFileName.substring(index);
		else {
			otherFileName = firstFileName + "_@INDEX";
		}
		templateData.setFirstFileName(firstFileName);
		templateData.setOtherFileName(otherFileName);

		tp.define("site", templateData.getSite());
		tp.define("catalog", templateData.getCatalog());
		tp.define("TemplateData", templateData);
		tp.define("page", templateData.getPageRow());

		long t = System.currentTimeMillis();
		try {
			tp.generate();
		} catch (Exception e) {
			this.task.addError("模板" + tp.getFileName() + "解析错误，请检查模板变量是否正确");
			return false;
		}
		LogUtil.info("解析页面：" + (System.currentTimeMillis() - t));

		String html = tp.getResult();
		html = html + statScript;

		String fileName = null;
		if (pageIndex == 0)
			fileName = path + firstFileName;
		else {
			fileName = path + otherFileName.replaceAll("@INDEX", String.valueOf(pageIndex + 1));
		}

		String FilePath = fileName.substring(0, fileName.lastIndexOf(47));
		File f = new File(FilePath);
		if (!(f.exists())) {
			f.mkdirs();
		}

		html = html + "\n<!-- Published by " + this.product.getAppCode() + "("
				+ this.product.getAppName() + ") " + this.product.getMainVersion()
				+ " PublishDate " + DateUtil.getCurrentDateTime() + "-->";

		FileUtil.writeText(fileName, html);
		LogUtil.info(fileName + " 耗时：" + (System.currentTimeMillis() - t));

		this.fileList.add(fileName);

		return true;
	}

	public String getStatScript(String statUrl) {
		String serviceUrl = Config.getValue("ServicesContext");
		String statScript = "\n<script src=\"" + serviceUrl
				+ "/Stat.js\" type=\"text/javascript\"></script>\n";
		statScript = statScript + "<script>\n";
		statScript = statScript + "if(window._zcms_stat)_zcms_stat(\"" + statUrl + "\");\n";
		statScript = statScript + "</script>\n";
		return statScript;
	}

	public String getClickScript(String articleID) {
		String serviceUrl = Config.getValue("ServicesContext");
		String clickScript = "\n<script src=\"" + serviceUrl + "/Counter.jsp?Type=Article&ID="
				+ articleID + "\" type=\"text/javascript\"></script>\n";
		return clickScript;
	}

	public ArrayList getFileList() {
		return this.fileList;
	}

	public void setFileList(ArrayList fileList) {
		this.fileList = fileList;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.template.PageGenerator JD-Core Version: 0.5.3
 */