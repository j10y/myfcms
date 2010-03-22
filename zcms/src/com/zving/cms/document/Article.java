package com.zving.cms.document;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.dataservice.Vote;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.PubFun;
import com.zving.cms.pub.SiteUtil;
import com.zving.cms.site.BadWord;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.controls.TButtonTag;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Errorx;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.Priv;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCArticleLogSchema;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCArticleSet;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCImageSet;
import com.zving.schema.ZCVoteSchema;
import com.zving.schema.ZCVoteSet;
import com.zving.schema.ZDColumnValueSchema;
import com.zving.schema.ZDColumnValueSet;
import com.zving.schema.ZWCurrentStepSchema;
import com.zving.schema.ZWCurrentStepSet;
import com.zving.search.index.IndexUtil;
import com.zving.workflow.ActionDescriptor;
import com.zving.workflow.Workflow;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article extends Page {
	public static final String TYPE_COMMON = "1";
	public static final String TYPE_IMAGE = "2";
	public static final String TYPE_VIDEO = "3";
	public static final String TYPE_URL = "4";
	public static final int STATUS_DRAFT = 0;
	public static final int STATUS_WORKFLOW = 10;
	public static final int STATUS_TOPUBLISH = 20;
	public static final int STATUS_PUBLISHED = 30;
	public static final int STATUS_OFFLINE = 40;
	public static final int STATUS_ARCHIVE = 50;
	public static final Mapx STATUS_MAP = new Mapx();

	private String siteAlias = SiteUtil.getAlias(Application.getCurrentSiteID());

	static {
		STATUS_MAP.put("0", "初稿");
		STATUS_MAP.put("10", "流转中");
		STATUS_MAP.put("20", "待发布");
		STATUS_MAP.put("30", "已发布");
		STATUS_MAP.put("40", "已下线");
		STATUS_MAP.put("50", "已归档");
	}

	public static Mapx init(Mapx params) {
		Mapx map = new Mapx();
		String catalogID = (String) params.get("CatalogID");
		String issueID = (String) params.get("IssueID");
		String articleID = (String) params.get("ArticleID");
		if ((articleID != null) && (!("".equals(articleID))) && (!("null".equals(articleID)))) {
			ZCArticleSchema article = new ZCArticleSchema();
			article.setID(Integer.parseInt(articleID));
			ZCArticleSet set = article.query();
			if (set.size() < 1) {
				return null;
			}
			article = set.get(0);
			catalogID = String.valueOf(article.getCatalogID());
			map.putAll(article.toMapx());

			String content = article.getContent();
			if (content == null) {
				content = "";
			}
			String[] pages = content.split("<!--_ZVING_PAGE_BREAK_-->");

			map.put("Pages", new Integer(pages.length));
			map.put("Content", pages[0]);
			map.put("Contents", content);

			String copyCatalogList = "<tr><td></td><td width=\"30\">&nbsp;</td></tr>";
			String idStr = "";

			String referType = "1";
			DataTable dt = new QueryBuilder(
					"select catalogid,refertype from zcarticle where refersourceid=?", articleID)
					.executeDataTable();
			if ((dt != null) && (dt.getRowCount() > 0)) {
				referType = dt.getString(0, "refertype");
				StringBuffer sb = new StringBuffer();
				StringBuffer ids = new StringBuffer();
				for (int i = 0; i < dt.getRowCount(); ++i) {
					long id = dt.getLong(i, "catalogid");
					String catalogName = CatalogUtil.getName(id);
					sb.append("<tr><td>");
					sb.append(catalogName);
					sb
							.append("</td><td width=\"30\"><img src=\"Images/button_delete_small.gif\" border=0 style=\"cursor: pointer;\" alt=\"删除栏目\" onClick=\"delItem(this,"
									+ dt.getLong(i, "catalogid") + ");\"></td></tr>\n");
					if (i != 0) {
						ids.append(",");
					}
					ids.append(id);
				}

				copyCatalogList = sb.toString();
				idStr = ids.toString();
			}

			map.put("CopyCatalogList", copyCatalogList);
			map.put("Copy2Article", idStr);
			map.put("ReferType", referType);

			map.put("CustomColumn", ColumnUtil.getHtml("1", catalogID, "2", String.valueOf(article
					.getID())));

			if (article.getAttribute() != null)
				map.put("Attribute", HtmlUtil.codeToCheckboxes("Attribute", "ArticleAttribute",
						article.getAttribute().split(",")));
			else {
				map.put("Attribute", HtmlUtil.codeToCheckboxes("Attribute", "ArticleAttribute"));
			}

			Date publishDate = article.getPublishDate();
			Date downlineDate = article.getDownlineDate();
			if (publishDate != null) {
				map.put("PublishDate", DateUtil.toString(publishDate, "yyyy-MM-dd"));
				map.put("PublishTime", DateUtil.toString(publishDate, "HH:mm:ss"));
			}

			if (downlineDate != null) {
				map.put("DownlineDate", DateUtil.toString(downlineDate, "yyyy-MM-dd"));
				map.put("DownlineTime", DateUtil.toString(downlineDate, "HH:mm:ss"));
			}

			Date lastModify = (article.getModifyTime() == null) ? article.getAddTime() : article
					.getModifyTime();
			map.put("LastModify", lastModify);

			map.put("Method", "UPDATE");

			DataAccess da = new DataAccess();
			String workflowDefID = new QueryBuilder("select workflow from zccatalog where id =?",
					catalogID).executeString();
			if (StringUtil.isNotEmpty(workflowDefID)) {
				Workflow workFlow = new Workflow(workflowDefID, da);
				long workFlowID = article.getWorkFlowID();
				ZWCurrentStepSet currentSteps = workFlow.findCurrentSteps(workFlowID);
				ZWCurrentStepSchema step = workFlow.getCurrentStep(10, currentSteps);
				if (step != null) {
					workFlow.populateTransientMap();
					ActionDescriptor[] actions = workFlow.getAvailableActions(step.getStepID());
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < actions.length; ++i) {
						sb.append(TButtonTag.getHtml("save('" + workFlowID + "','"
								+ actions[i].getId() + "')", "article_modify",
								"<img src='../Icons/icon003a16.gif'/>", actions[i].getName()));
					}
					map.put("buttonDiv", sb.toString());
				} else {
					map.put("buttonDiv", TButtonTag.getHtml("publish('workflow')",
							"article_modify", "<img src='../Icons/icon003a6.gif'/>", "发布")
							+ TButtonTag.getHtml("save()", "article_modify",
									"<img src='../Icons/icon003a6.gif'/>", "申请修改"));
				}
			} else {
				String buttonDiv = TButtonTag.getHtml("save()", "article_modify",
						"<img src='../Icons/icon003a16.gif'/>", "保存")
						+ TButtonTag.getHtml("publish()", "article_modify",
								"<img src='../Icons/icon003a6.gif'/>", "发布");
				map.put("buttonDiv", buttonDiv);
			}
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			map.put("CatalogID", catalogID);

			articleID = String.valueOf(NoUtil.getMaxID("DocID"));
			map.put("ID", articleID);
			map.put("Method", "ADD");
			map.put("CatalogID", catalogID);
			map.put("CommentFlag", "1");

			map.put("DownlineDate", "2099-12-31");
			map.put("DownlineTime", "23:59:59");

			map.put("Pages", new Integer(1));
			map.put("ContentPages", "''");
			map.put("Title", "");

			String copyCatalogList = "<tr><td></td><td width=\"30\">&nbsp;</td></tr>";
			String idStr = "";
			map.put("CopyCatalogList", copyCatalogList);
			map.put("Copy2Article", idStr);
			map.put("ReferType", "1");

			String buttonDiv = TButtonTag.getHtml("save()", "article_modify",
					"<img src='../Icons/icon003a16.gif'/>", "保存");
			String workflowDefID = new QueryBuilder("select workflow from zccatalog where id =?",
					catalogID).executeString();
			if (StringUtil.isEmpty(workflowDefID)) {
				buttonDiv = buttonDiv
						+ TButtonTag.getHtml("publish()", "article_modify",
								"<img src='../Icons/icon003a6.gif'/>", "发布");
			}
			map.put("buttonDiv", buttonDiv);

			map.put("CustomColumn", ColumnUtil.getHtml("1", catalogID));

			map.put("Attribute", HtmlUtil.codeToCheckboxes("Attribute", "ArticleAttribute"));
		}

		String siteID = CatalogUtil.getSiteID(catalogID);
		map.put("SiteID", CatalogUtil.getSiteID(catalogID));
		map.put("CatalogName", CatalogUtil.getName(catalogID));
		map.put("IssueID", issueID);
		map.put("InnerCode", CatalogUtil.getInnerCode(catalogID));

		String recentKeywordSQL = "select keyword from zckeyword order by id desc";
		DataTable dt = new QueryBuilder(recentKeywordSQL).executePagedDataTable(3, 0);
		StringBuffer keywordsStr = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String keyword = dt.getString(i, "keyword");
			keywordsStr.append("<a href=\"#\" onclick=\"addRecentKeyword('" + keyword + "')\">"
					+ keyword + "</a>   ");
		}
		map.put("RecentKeyword", keywordsStr.toString());

		String cssFile = new QueryBuilder("select editorcss from zcsite where id=?", siteID)
				.executeString();
		if (StringUtil.isNotEmpty(cssFile)) {
			String staticDir = Config.getContextPath()
					+ Config.getValue("Statical.TargetDir").replace('\\', '/');
			staticDir = staticDir + "/" + Application.getCurrentSiteAlias() + "/" + cssFile;
			staticDir = staticDir.replaceAll("///", "/");
			staticDir = staticDir.replaceAll("//", "/");
			map.put("CssPath", staticDir);
		} else {
			map.put("CssPath", Config.getContextPath() + "Editor/editor/css/fck_editorarea.css");
		}

		return map;
	}

	public static Mapx initPreview(Mapx params) {
		String articleID = (String) params.get("ArticleID");
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(Integer.parseInt(articleID));
		article.fill();
		params = article.toDataRow().toCaseIgnoreMapx();
		return params;
	}

	public static void relativeDg1DataBind(DataGridAction dga) {
		String relaIDs = (String) dga.getParams().get("RelativeArticle");
		if (!(StringUtil.checkID(relaIDs))) {
			return;
		}
		if ((relaIDs.indexOf("\"") >= 0) || (relaIDs.indexOf("'") >= 0)) {
			return;
		}

		if ("".equals(relaIDs)) {
			relaIDs = "''";
		}

		DataTable dt = new QueryBuilder("select title,id from zcarticle where id in (" + relaIDs
				+ ")").executeDataTable();
		dga.bindData(dt);
	}

	public static void recommendDg1DataBind(DataGridAction dga) {
		String recIDs = (String) dga.getParams().get("RecommendArticle");
		if (!(StringUtil.checkID(recIDs))) {
			return;
		}
		if ((recIDs.indexOf("\"") >= 0) || (recIDs.indexOf("'") >= 0)) {
			return;
		}

		if ("".equals(recIDs)) {
			recIDs = "''";
		}

		DataTable dt = new QueryBuilder(
				"select title,id,addtime,author from zcarticle where id in (" + recIDs + ")")
				.executeDataTable();
		dga.bindData(dt);
	}

	public boolean save() {
		if (!(Priv.getPriv("article", $V("InnerCode"), "article_modify"))) {
			this.Response.setLogInfo(0, "操作失败，你没有权限进行此操作！");
			return false;
		}
		DataAccess da = new DataAccess();
		try {
			Transaction trans = new Transaction();

			ZCArticleSchema article = new ZCArticleSchema();
			long articleID = Long.parseLong((String) this.Request.get("ArticleID"));
			article.setID(articleID);

			String method = $V("Method");

			if ("UPDATE".equals(method)) {
				article.fill();
			}

			long catalogID = Long.parseLong($V("CatalogID"));
			article.setCatalogID(catalogID);
			String siteID = CatalogUtil.getSiteID(catalogID);
			article.setSiteID(siteID);

			String innerCode = new QueryBuilder("select innercode from zccatalog where id=?",
					catalogID).executeString();
			article.setCatalogInnerCode(innerCode);
			article.setType($V("Type"));
			String title = $V("Title");
			article.setTitle(title);
			article.setTitleStyle($V("TitleStyle"));
			article.setShortTitle($V("ShortTitle"));
			article.setShortTitleStyle($V("ShortTitleStyle"));
			article.setSubTitle($V("SubTitle"));
			article.setReferURL($V("ReferURL"));
			article.setReferName($V("ReferName"));
			article.setRelativeArticle($V("RelativeArticle"));
			article.setRecommendArticle($V("RecommendArticle"));
			article.setTopFlag($V("TopFlag"));
			article.setCommentFlag($V("CommentFlag"));
			article.setPriority($V("Priority"));
			article.setAttribute($V("Attribute"));

			String author = $V("Author");
			article.setAuthor(author);
			article.setSummary($V("Summary"));

			String content = $V("Content");
			if (StringUtil.isEmpty(content)) {
				content = " ";
			}

			if ("1".equals($V("LocalImageFlag"))) {
				String tempContent = content;
				content = copyRemoteFiles(content, trans, articleID);
				if (!(tempContent.equals(content))) {
					$S("ContentChanged", "Y");
					$S("Content", content);
				}
				article.setContent(content);
				article.setCopyImageFlag("Y");
			} else {
				article.setContent(content);
				article.setCopyImageFlag("N");
			}

			dealKeyword(trans, article);
			$S("Keyword", article.getKeyword());

			String badMsg = checkArticleBadWord(article);
			if (StringUtil.isNotEmpty(badMsg)) {
				this.Response.setLogInfo(0, "保存失败,存在以下敏感词," + badMsg + "请检查修改.");
				return false;
			}

			if (dealRelaImage(content, articleID, trans)) {
				article.setType("2");
			}

			if (StringUtil.isNotEmpty($V("PublishDate"))) {
				String publishTime = $V("PublishTime");
				if (StringUtil.isEmpty(publishTime)) {
					publishTime = "00:00:00";
				}
				article.setPublishDate(DateUtil.parse($V("PublishDate") + " " + publishTime,
						"yyyy-MM-dd HH:mm:ss"));
			}

			if (StringUtil.isEmpty($V("DownlineDate"))) {
				article.setDownlineDate(DateUtil
						.parse("2099-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			} else {
				String downTime = $V("DownlineTime");
				if (StringUtil.isEmpty(downTime)) {
					downTime = "00:00:00";
				}
				article.setDownlineDate(DateUtil.parse($V("DownlineDate") + " " + downTime,
						"yyyy-MM-dd HH:mm:ss"));
			}

			article.setURL(PubFun.getArticleURL(article));
			article.setRedirectURL($V("RedirectURL"));
			article.setTemplateFlag($V("TemplateFlag"));
			article.setTemplate($V("Template"));

			String issueStr = $V("IssueID");
			if ((issueStr != null) && (!("".equals(issueStr)))) {
				article.setIssueID(Long.parseLong($V("IssueID")));
			}

			ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
			articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
			articleLog.setArticleID(articleID);

			String workflowDefID = new QueryBuilder("select workflow from zccatalog where id =?",
					catalogID).executeString();

			if ("UPDATE".equals(method)) {
				article.setModifyTime(new Date());
				article.setModifyUser(User.getUserName());
				articleLog.setAction("UPDATE");

				trans.add(article, 2);

				String sqlCatalog = "update zccatalog set isdirty=1 where id=" + catalogID;
				trans.add(new QueryBuilder(sqlCatalog));

				if (StringUtil.isNotEmpty(workflowDefID)) {
					String entryId = $V("entryId");
					String actionId = $V("actionId");
					Workflow workFlow;
					if ((StringUtil.isEmpty(entryId)) || (StringUtil.isEmpty(actionId))) {
						workFlow = new Workflow(workflowDefID, da);
						long workflowEntryID = workFlow.initialize(workflowDefID, 100);
						article.setWorkFlowID(workflowEntryID);
						article.setStatus(10L);
						if (workFlow.getState() == 4L)
							article.setStatus(20L);
					} else {
						try {
							workFlow = new Workflow(workflowDefID, da);
							workFlow.doAction(Long.parseLong(entryId), Long.parseLong(actionId));
							if (workFlow.getState() == 4L)
								article.setStatus(20L);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else if ("ADD".equals(method)) {
				article.setPublishFlag("1");
				article.setOrderFlag(OrderUtil.getDefaultOrder());
				article.setHitCount(0L);
				article.setStickTime(0L);
				article.setStatus(0L);
				article.setAddTime(new Date(article.getOrderFlag()));

				articleLog.setAction("INSERT");

				article.setAddUser(User.getUserName());
				if (StringUtil.isNotEmpty(workflowDefID)) {
					Workflow workFlow = new Workflow(workflowDefID, da);
					long workflowEntryID = workFlow.initialize(workflowDefID, 100);
					article.setWorkFlowID(workflowEntryID);
					article.setStatus(10L);
					if (workFlow.getState() == 4L) {
						article.setStatus(20L);
					}
				}

				trans.add(article, 1);
				String sqlArticleCount = "update zccatalog set total = total+1,isdirty=1 where id=?";
				trans.add(new QueryBuilder(sqlArticleCount, catalogID));
			}

			articleLog.setAddUser(User.getUserName());
			articleLog.setAddTime(new Date());
			trans.add(articleLog, 1);

			if ("UPDATE".equals(method)) {
				DataTable columnDT = ColumnUtil.getColumnValue("2", articleID);
				if (columnDT.getRowCount() > 0) {
					trans.add(new QueryBuilder(
							"delete from zdcolumnvalue where relatype=? and relaid = ?", "2",
							articleID));
				}
				trans.add(ColumnUtil.getValueFromRequest(catalogID, articleID, this.Request), 1);
			} else if ("ADD".equals(method)) {
				trans.add(ColumnUtil.getValueFromRequest(catalogID, articleID, this.Request), 1);
			}

			copyArticle(trans, article);

			trans.setDataAccess(da);
			if (trans.commit()) {
				this.Response.put("SaveTime", DateUtil.getCurrentDateTime());
				if (StringUtil.isNotEmpty(workflowDefID)) {
					long workFlowID = article.getWorkFlowID();
					Workflow workFlow = new Workflow(workflowDefID, da);
					ZWCurrentStepSet currentSteps = workFlow.findCurrentSteps(workFlowID);
					ZWCurrentStepSchema step = workFlow.getCurrentStep(10, currentSteps);
					if (step != null) {
						workFlow.populateTransientMap();
						ActionDescriptor[] actions = workFlow.getAvailableActions(step.getStepID());
						StringBuffer sb = new StringBuffer();
						for (int i = 0; i < actions.length; ++i) {
							sb.append(TButtonTag.getHtml("save('" + step.getEntryID() + "','"
									+ actions[i].getId() + "')", "article_modify",
									"<img src='../Icons/icon018a16.gif'/>", actions[i].getName()));
						}
						this.Response.put("buttonDiv", sb.toString());
					} else {
						this.Response.put("buttonDiv", TButtonTag.getHtml("publish()",
								"article_modify", "<img src='../Icons/icon018a6.gif'/>", "发布"));
					}

				} else {
					String buttonDiv = TButtonTag.getHtml("save()", "article_modify",
							"<img src='../Icons/icon018a16.gif'/>", "保存")
							+ TButtonTag.getHtml("publish()", "article_modify",
									"<img src='../Icons/icon018a6.gif'/>", "发布");
					this.Response.put("buttonDiv", buttonDiv);
				}

			}

			this.Response.setStatus(0);
			this.Response.setMessage("发生错误：文档保存失败！");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			this.Response.setStatus(0);
			this.Response.setMessage("发生错误：" + e.getMessage());
			return false;
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
				this.Response.setStatus(0);
				this.Response.setMessage("发生错误：" + e.getMessage());
				return false;
			}
		}
	}

	public static String checkArticleBadWord(ZCArticleSchema article) {
		String badMsg = "";
		String badTitle = BadWord.checkBadWord(article.getTitle(), article.getPriority());
		if (StringUtil.isNotEmpty(badTitle)) {
			badMsg = badMsg + "标题:<font color='red'>" + badTitle + "</font>,";
		}
		String badShortTitle = BadWord.checkBadWord(article.getShortTitle(), article.getPriority());
		if (StringUtil.isNotEmpty(badShortTitle)) {
			badMsg = badMsg + "短标题:<font color='red'>" + badShortTitle + "</font>,";
		}
		String badSubTitle = BadWord.checkBadWord(article.getSubTitle(), article.getPriority());
		if (StringUtil.isNotEmpty(badSubTitle)) {
			badMsg = badMsg + "副标题:<font color='red'>" + badSubTitle + "</font>,";
		}
		String badAuthor = BadWord.checkBadWord(article.getAuthor(), article.getPriority());
		if (StringUtil.isNotEmpty(badAuthor)) {
			badMsg = badMsg + "作者:<font color='red'>" + badAuthor + "</font>,";
		}
		String badKeyword = BadWord.checkBadWord(article.getKeyword(), article.getPriority());
		if (StringUtil.isNotEmpty(badKeyword)) {
			badMsg = badMsg + "关键字:<font color='red'>" + badKeyword + "</font>,";
		}
		String badReferName = BadWord.checkBadWord(article.getReferName(), article.getPriority());
		if (StringUtil.isNotEmpty(badReferName)) {
			badMsg = badMsg + "来源:<font color='red'>" + badReferName + "</font>,";
		}
		String badContent = BadWord.checkBadWord(article.getContent(), article.getPriority());
		if (StringUtil.isNotEmpty(badContent)) {
			badMsg = badMsg + "正文:<font color='red'>" + badContent + "</font>,";
		}
		String badSummary = BadWord.checkBadWord(article.getSummary(), article.getPriority());
		if (StringUtil.isNotEmpty(badSummary)) {
			badMsg = badMsg + "摘要:<font color='red'>" + badSummary + "</font>,";
		}
		return badMsg;
	}

	private void dealKeyword(Transaction trans, ZCArticleSchema article) {
		String keyword = $V("Keyword");
		if (StringUtil.isNotEmpty(keyword)) {
			keyword = keyword.trim().replaceAll("\\s+", " ");
			keyword = keyword.replaceAll("，", " ");
			keyword = keyword.replaceAll("；", " ");
			keyword = keyword.replaceAll(";", " ");
			keyword = keyword.replaceAll("\\,+", " ");
			if (",".equals(keyword)) {
				keyword = "";
			} else {
				if (keyword.indexOf(",") == 0) {
					keyword = keyword.substring(1);
				}
				if (keyword.lastIndexOf(",") == keyword.length() - 1)
					keyword = keyword.substring(0, keyword.length() - 1);
			}
		} else {
			String keywordFlag = new QueryBuilder("select keywordFlag from zccatalog where id=?",
					article.getCatalogID()).executeString();
			if ((StringUtil.isNotEmpty(keywordFlag)) && (!("N".equals(keywordFlag)))) {
				keyword = StringUtil.join(IndexUtil.getKeyword(article.getTitle() + " "
						+ article.getContent()), " ");
			}
		}

		article.setKeyword(keyword);
	}

	private void copyArticle(Transaction trans, ZCArticleSchema article) {
		long articleID = article.getID();
		long catalogID = article.getCatalogID();

		String catalogIDs = $V("Copy2Article");
		String referType = $V("ReferType");

		String otherCatalogID = $V("_C_DestCatalog");
		if (StringUtil.isNotEmpty(otherCatalogID)) {
			if (StringUtil.isEmpty(catalogIDs)) {
				catalogIDs = otherCatalogID;
				referType = "2";
			} else {
				catalogIDs = catalogIDs + "," + otherCatalogID;
			}

		}

		if (StringUtil.isEmpty(referType)) {
			referType = "1";
		}
		int refer = Integer.parseInt(referType);
		if (StringUtil.isNotEmpty(catalogIDs)) {
			String[] catalogArr = catalogIDs.split(",");
			String copySiteID = null;
			for (int j = 0; j < catalogArr.length; ++j) {
				long toCatalogID = Long.parseLong(catalogArr[j]);
				ZCArticleSchema articleRefer = new ZCArticleSchema();
				articleRefer.setReferSourceID(articleID);
				articleRefer.setCatalogID(toCatalogID);
				ZCArticleSet set = articleRefer.query();

				if ((set != null) && (set.size() > 0)) {
					articleRefer = set.get(0);
					articleRefer.setTitle(article.getTitle());
					articleRefer.setShortTitle(article.getShortTitle());
					articleRefer.setSubTitle(article.getSubTitle());
					articleRefer.setAuthor(article.getAuthor());
					articleRefer.setKeyword(article.getKeyword());
					articleRefer.setSummary(articleRefer.getSummary());

					if (refer == 1) {
						articleRefer.setContent(article.getContent());
					}

					trans.add(articleRefer, 2);
				} else {
					ZCArticleSchema articleColone = (ZCArticleSchema) article.clone();
					articleColone.setID(NoUtil.getMaxID("DocID"));

					articleColone.setCatalogID(toCatalogID);
					articleColone.setCatalogInnerCode(CatalogUtil.getInnerCode(toCatalogID));
					articleColone.setReferType(refer);
					articleColone.setReferSourceID(articleID);

					if (StringUtil.isEmpty(copySiteID)) {
						copySiteID = CatalogUtil.getSiteID(catalogArr[j]);
					}
					articleColone.setSiteID(copySiteID);

					articleColone.setTopFlag("0");
					articleColone.setTemplateFlag("0");
					articleColone.setTemplate("");

					if (refer == 2) {
						articleColone.setType("4");
						String path = Config.getContextPath()
								+ Config.getValue("Statical.TargetDir") + "/" + this.siteAlias
								+ "/";
						path = path.replaceAll("///", "/");
						path = path.replaceAll("//", "/");
						String referURL = path + CatalogUtil.getPath(catalogID) + articleID
								+ ".shtml";
						articleColone.setRedirectURL(referURL);
						articleColone.setContent("");
					}
					trans.add(articleColone, 1);
				}

				String sqlArticleCount = "update zccatalog set total = total+1 where id=?";
				trans.add(new QueryBuilder(sqlArticleCount, catalogArr[j]));

				ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
				articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
				articleLog.setArticleID(article.getID());
				articleLog.setAction("COPY");
				articleLog.setActionDetail("复制。从" + CatalogUtil.getName(article.getCatalogID())
						+ "复制到" + CatalogUtil.getName(toCatalogID) + "。");
				articleLog.setAddUser(User.getUserName());
				articleLog.setAddTime(new Date());
				trans.add(articleLog, 1);
			}
		}
	}

	private boolean dealRelaImage(String content, long articleID, Transaction trans) {
		String regex = "src=([\"|'])*?(.*?\\.(gif|jpg|jpeg|bmp|png))\\1";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		int imgIndex = 0;
		StringBuffer sb1 = new StringBuffer();
		boolean flag = false;

		while (matcher.find(imgIndex)) {
			int s = matcher.start();
			int e = matcher.end();
			imgIndex = e;
			String picSrc = content.substring(s, e);
			String fileName = picSrc.substring(picSrc.lastIndexOf("/") + 1, picSrc.length() - 1);
			if (fileName.indexOf("_") != -1) {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
			}
			sb1.append("'" + fileName + "',");
			flag = true;
		}

		if (flag) {
			String str = sb1.substring(0, sb1.length() - 1);

			String insertImage = "insert into zcimagerela(id,relaID,relaType,orderflag,adduser,addtime,modifyuser,modifytime) select id,?,'ArticleImage',?,?,?,null,null from zcimage where filename in ("
					+ str + ")";
			String deleteImage = "delete from zcimagerela where relaid=? and relaType=?";
			QueryBuilder insertQB = new QueryBuilder(insertImage);
			insertQB.add(articleID);
			insertQB.add(OrderUtil.getDefaultOrder());
			insertQB.add(User.getUserName());
			insertQB.add(new Date());

			trans.add(new QueryBuilder(deleteImage, articleID, "ArticleImage"));
			trans.add(insertQB);

			return true;
		}

		return false;
	}

	public void doAction() {
		String articleID = $V("ArticleID");
		String workflowDefID = new QueryBuilder(
				"select workflow from zccatalog where id =(select catalogid from zcarticle where id=?)",
				articleID).executeString();

		DataAccess da = new DataAccess();
		Transaction trans = new Transaction();
		trans.setDataAccess(da);
		String entryID = $V("EntryID");
		String actionID = $V("ActionID");
		try {
			Workflow workFlow = new Workflow(workflowDefID, da);
			if (!(workFlow.doAction(Long.parseLong(entryID), Long.parseLong(actionID)))) {
				this.Response.setStatus(0);
				this.Response.setMessage("发生错误");
				return;
			}
			if (workFlow.getState() == 4L) {
				trans.add(new QueryBuilder("update zcarticle set status=20 where id=?", articleID));
			}
			if (trans.commit()) {
				this.Response.setStatus(1);
				this.Response.setMessage("操作成功!");
			} else {
				this.Response.setStatus(0);
			}
			this.Response.setMessage("发生错误" + Errorx.printString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
				this.Response.setStatus(0);
				this.Response.setMessage("发生错误" + Errorx.printString());
			}
		}
	}

	private boolean getRemoteFile(String destUrl, String fileName) {
		if ("Y".equalsIgnoreCase(Config.getValue("Proxy.IsUseProxy"))) {
			System.setProperty("http.proxyHost", Config.getValue("Proxy.Host"));
			System.setProperty("http.proxyPort", Config.getValue("Proxy.Port"));
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Config.getValue("Proxy.UserName"),
							new String(Config.getValue("Proxy.Password")).toCharArray());
				}
			});
		}
		try {
			byte[] buf = new byte[8096];
			int size = 0;

			URL url = new URL(destUrl);

			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			bis.toString();

			FileOutputStream fos = new FileOutputStream(fileName);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}

			fos.close();
			bis.close();
		} catch (MalformedURLException e) {
			System.out.println("非法地址");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("没有找到对应的文件");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String copyRemoteFiles(String content, Transaction trans, long articleID) {
		Pattern varPattern = Pattern.compile(
				"src=([\"|'| ])*?(http.*?\\.(gif|jpg|jpeg|bmp|png))\\1", 34);

		Matcher m = varPattern.matcher(content);
		StringBuffer sb = new StringBuffer();
		int lastEndIndex = 0;
		while (m.find(lastEndIndex)) {
			int s = m.start();
			int e = m.end();
			sb.append(content.substring(lastEndIndex, s));
			String imagePath = m.group(2);
			String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);

			String local = Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
					+ this.siteAlias + "/upload/Image/default/";
			File file = new File(local);
			if (!(file.exists())) {
				file.mkdirs();
			}

			if (getRemoteFile(imagePath, local + fileName)) {
				String newFileName = AutoUpload.dealImage(local, fileName, trans);
				if (StringUtil.isNotEmpty(newFileName)) {
					String uploadPath = Config.getContextPath() + Config.getValue("UploadDir")
							+ "/" + this.siteAlias + "/upload/Image/default/";
					imagePath = uploadPath.replaceAll("//", "/") + newFileName;
					System.out.println("复制成功" + m.group(2));
				} else {
					System.out.println("复制失败" + imagePath);
				}
			} else {
				System.out.println("复制失败" + imagePath);
			}
			sb.append("src=\"" + imagePath + "\"");
			lastEndIndex = e;
		}
		sb.append(content.substring(lastEndIndex));
		return sb.toString();
	}

	public void saveVersion() {
		String articleID = $V("ArticleID");
		long id = Long.parseLong(articleID);
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(id);

		if (!(article.fill())) {
			article.setID(articleID);
			article.setValue(this.Request);
			long catalogID = Long.parseLong($V("CatalogID"));
			article.setCatalogID(catalogID);

			String siteID = CatalogUtil.getSiteID(catalogID);
			article.setSiteID(siteID);

			article.setCatalogInnerCode(CatalogUtil.getInnerCode(catalogID));
			article.setHitCount(0L);
			article.setPublishFlag("Y");
			article.setStickTime(0L);

			article.setOrderFlag(OrderUtil.getDefaultOrder());
			article.setAddTime(new Date());
			article.setAddUser(User.getUserName());
			String badMsg = checkArticleBadWord(article);
			if (StringUtil.isNotEmpty(badMsg)) {
				this.Response.setLogInfo(0, "保存失败,存在以下敏感词," + badMsg + "请检查修改.");
				return;
			}
		}

		Transaction trans = new Transaction();
		trans.add(article, 4);

		if (trans.commit()) {
			this.Response.setStatus(1);
			this.Response.put("SaveTime", DateUtil.getCurrentDateTime());
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public boolean publish() {
		ZCArticleSchema article = new ZCArticleSchema();
		int articleID = Integer.parseInt((String) this.Request.get("ArticleID"));

		ZCArticleSet set = article.query(new QueryBuilder(
				"where id =? or id in(select id from zcarticle where refersourceid=? )", articleID,
				articleID));

		Publisher p = new Publisher();
		if (p.publishArticle(set)) {
			this.Response.setStatus(1);
			return true;
		}
		this.Response.setStatus(0);
		this.Response.setMessage("发布发生错误 " + Errorx.printString());
		return false;
	}

	public void saveAndPublish() {
		if ((!(save())) || (publish()))
			return;
		this.Response.setStatus(1);
		this.Response.setMessage("保存成功，" + this.Response.Message);
	}

	public void getArticle() {
		ZCArticleSchema article = new ZCArticleSchema();
		long articleID = Long.parseLong((String) this.Request.get("ArticleID"));
		article.setID(articleID);
		if (article.fill()) {
			String content = article.getContent();
			String[] pages = content.split("<!--_ZVING_PAGE_BREAK_-->");
			StringBuffer pageStr = new StringBuffer();
			for (int i = 0; i < pages.length; ++i) {
				pageStr.append("'" + StringUtil.htmlEncode(pages[i]) + "'");
				if (i != pages.length - 1) {
					pageStr.append(",");
				}
			}

			this.Response.setStatus(1);
			this.Response.put("NewsType", article.getType());
			this.Response.put("TopFlag", article.getTopFlag());
			this.Response.put("CommentFlag", article.getCommentFlag());
			this.Response.put("Priority", article.getPriority());
			this.Response.put("TemplateFlag", article.getTemplate());
			this.Response.put("ShortTitle", article.getShortTitle());
			this.Response.put("Title", article.getTitle());
			this.Response.put("SubTitle", article.getSubTitle());
			this.Response.put("Keyword", article.getKeyword());
			this.Response.put("Summary", article.getSummary());
			this.Response.put("Template", article.getTemplate());
			this.Response.put("Author", article.getAuthor());
			this.Response.put("RedirectURL", article.getRedirectURL());

			this.Response.put("Pages", new Integer(pages.length));
			this.Response.put("ContentPages", pageStr.toString());
		}
	}

	public void del() {
		String id = $V("ArticleID");
		if ((id.indexOf("\"") >= 0) || (id.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}

		long articleID = Long.parseLong(id);
		Transaction trans = new Transaction();
		trans.setBackupMemo("DELETE");
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(articleID);
		if (!(article.fill())) {
			this.Response.setStatus(0);
			this.Response.setMessage("没有找到对应的文档");
			return;
		}
		trans.add(article, 5);

		ZDColumnValueSchema colValue = new ZDColumnValueSchema();
		colValue.setRelaID(String.valueOf(articleID));
		ZDColumnValueSet colValueSet = colValue.query();
		trans.add(colValueSet, 5);

		String sqlArticleCount = "update zccatalog set total = total-1,isdirty=1 where innercode in("
				+ CatalogUtil.getParentCatalogCode(article.getCatalogInnerCode()) + ")";
		trans.add(new QueryBuilder(sqlArticleCount));

		ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
		articleLog.setArticleID(articleID);
		articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
		articleLog.setAction("DELETE");
		articleLog.setActionDetail("删除。删除原因：" + $V("DeleteReason"));
		articleLog.setAddUser(User.getUserName());
		articleLog.setAddTime(new Date());
		trans.add(articleLog, 1);

		if (trans.commit()) {
			Publisher p = new Publisher();
			p.deletePubishedFile(article);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void getPicSrc() {
		String ids = $V("PicID");
		String customFlag = $V("Custom");
		String catalogID = $V("CatalogID");
		if ((!(StringUtil.checkID(ids))) || (!(StringUtil.checkID(catalogID)))) {
			return;
		}
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			return;
		}

		ZCImageSet imageSet = new ZCImageSchema().query(new QueryBuilder(" where id in (" + ids
				+ ") order by id"));

		String path = Config.getContextPath() + Config.getValue("UploadDir") + "/" + this.siteAlias;
		path = path.replaceAll("///", "/");
		path = path.replaceAll("//", "/");
		StringBuffer images = new StringBuffer();

		if ((StringUtil.isNotEmpty(customFlag)) && (imageSet.size() > 0)) {
			images.append(path + "/" + imageSet.get(0).getPath() + "1_"
					+ imageSet.get(0).getFileName());
			this.Response.put("1_picSrc", images.toString());
			images = new StringBuffer();
			images.append(path + "s_" + imageSet.get(0).getFileName());
			this.Response.put("s_picSrc", images.toString());
		} else {
			for (int i = 0; i < imageSet.size(); ++i) {
				images.append("<p style='text-align: center;'>");
				if (((StringUtil.isNotEmpty(Config.getValue("ArticleImageWidth"))) && (imageSet
						.get(i).getWidth() > Integer.parseInt(Config.getValue("ArticleImageWidth"))))
						|| ((StringUtil.isNotEmpty(Config.getValue("ArticleImageHeight"))) && (imageSet
								.get(i).getHeight() > Integer.parseInt(Config
								.getValue("ArticleImageHeight"))))) {
					images.append("<a alt='点击查看大图' href='" + path + "/" + imageSet.get(i).getPath()
							+ imageSet.get(i).getSrcFileName() + "' target='_blank' >");
				}
				images.append("<img border=0 src='" + path + "/" + imageSet.get(i).getPath() + "1_"
						+ imageSet.get(i).getFileName() + "'>");
				if (((StringUtil.isNotEmpty(Config.getValue("ArticleImageWidth"))) && (imageSet
						.get(i).getWidth() > Integer.parseInt(Config.getValue("ArticleImageWidth"))))
						|| ((StringUtil.isNotEmpty(Config.getValue("ArticleImageHeight"))) && (imageSet
								.get(i).getHeight() > Integer.parseInt(Config
								.getValue("ArticleImageHeight"))))) {
					images.append("</a>");
				}
				images.append("<p style='text-align: center;'>" + imageSet.get(i).getName()
						+ "</p>" + "</p>");
			}
			this.Response.put("1_picSrc", images.toString());
		}
	}

	public void getFilePath() {
		String ids = $V("FileID");
		String catalogID = $V("CatalogID");
		if ((!(StringUtil.checkID(ids))) || (!(StringUtil.checkID(catalogID)))) {
			return;
		}

		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			return;
		}
		DataTable dt = new QueryBuilder(
				"select id,name,suffix,path,filename,srcfilename from zcattachment where id in ("
						+ ids + ")").executeDataTable();

		StringBuffer filePath = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if ((StringUtil.isNotEmpty(catalogID))
					&& ("N".equals(CatalogUtil.getAttachDownFlag(catalogID))))
				filePath.append("<a href='" + CatalogUtil.getLevelStr(catalogID)
						+ dt.getString(i, "path") + dt.getString(i, "filename") + "'>"
						+ dt.getString(i, "name") + "." + dt.getString(i, "suffix") + "</a><p>");
			else if ("N".equals(SiteUtil.getAttachDownFlag(Application.getCurrentSiteID())))
				filePath.append("<a href='" + CatalogUtil.getLevelStr(catalogID)
						+ dt.getString(i, "path") + dt.getString(i, "filename") + "'>"
						+ dt.getString(i, "name") + "." + dt.getString(i, "suffix") + "</a><p>");
			else {
				filePath.append("<a href='" + Config.getValue("ServicesContext")
						+ "/AttachDownLoad.jsp?id=" + dt.getString(i, "id") + "'>"
						+ dt.getString(i, "name") + "." + dt.getString(i, "suffix") + "</a><p>");
			}
		}
		this.Response.put("FilePath", filePath.toString());
	}

	public void getFlashPath() {
		String ids = $V("FlashID");
		String catalogID = $V("CatalogID");
		if ((!(StringUtil.checkID(ids))) || (!(StringUtil.checkID(catalogID)))) {
			return;
		}
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			return;
		}
		DataTable dt = new QueryBuilder(
				"select id,name,suffix,path,filename,srcfilename from zcattachment where id in ("
						+ ids + ")").executeDataTable();

		StringBuffer flashs = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			flashs
					.append("<embed src='"
							+ new StringBuffer(String.valueOf(CatalogUtil.getLevelStr(catalogID)))
									.append(dt.get(i, "Path")).append(dt.get(i, "FileName"))
									.toString().replaceAll("//", "/")
							+ "' type='application/x-shockwave-flash' width='320' height='240' play='true' loop='true' menu='true'></embed>");
		}
		this.Response.put("FlashPath", flashs.toString());
	}

	public void getVideoPath() {
		String ids = $V("VideoID");
		String catalogID = $V("CatalogID");
		if ((!(StringUtil.checkID(ids))) || (!(StringUtil.checkID(catalogID)))) {
			return;
		}
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			return;
		}
		DataTable dt = new QueryBuilder(
				"select name,suffix,path,filename,srcfilename,imageName from zcvideo where id in ("
						+ ids + ")").executeDataTable();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < dt.getRowCount(); ++i) {
			sb.append("<div style='text-align: center;'><embed wmode=\"transparent\" ");
			sb.append("flashvars=\"file=" + CatalogUtil.getLevelStr(catalogID)
					+ dt.getString(i, "path") + "0_" + dt.getString(i, "filename") + "&image=."
					+ CatalogUtil.getLevelStr(catalogID) + dt.getString(i, "path")
					+ dt.getString(i, "imageName") + "&stretching=fill\"");
			sb
					.append(" src=\""
							+ CatalogUtil.getLevelStr(catalogID)
							+ "/images/player.swf\" quality=\"high\" allowfullscreen=\"true\" type=\"application/x-shockwave-flash\"");
			sb.append(" width=\"480\" height=\"360\"></embed><br/>");
			sb.append(dt.getString(i, "Name") + "<br/></div>");
		}
		this.Response.put("VideoPath", sb.toString());
	}

	public void getKeywordOrSummary() {
		String type = $V("Type");
		String title = $V("Title");
		String content = $V("Content");
		if ("Keyword".equals(type))
			$S("Text", StringUtil.join(IndexUtil.getKeyword(title + " " + content), " "));
		else
			$S("Text", IndexUtil.getTextAbstract(title, content));
	}

	public void getJSCode() {
		String ids = $V("ID");
		String catalogID = $V("CatalogID");
		if ((!(StringUtil.checkID(ids))) || (!(StringUtil.checkID(catalogID)))) {
			return;
		}
		ZCVoteSet set = new ZCVoteSchema().query(new QueryBuilder("where id in (" + ids + ")"));
		StringBuffer jsCodes = new StringBuffer();
		for (int i = 0; i < set.size(); ++i) {
			ZCVoteSchema vote = set.get(i);
			String code = "";
			code = code + "<div>调查：" + vote.getTitle() + "\n";
			code = code + "<!--" + vote.getTitle() + "-->\n";
			code = code + "<link href=\"" + CatalogUtil.getLevelStr(catalogID)
					+ "images/vote.css\" type=\"text/css\" rel=\"stylesheet\" />";
			code = code + "<script language='javascript' src='"
					+ CatalogUtil.getLevelStr(catalogID) + "images/vote.js'></script>";
			code = code
					+ "<script language='javascript' src='"
					+ new StringBuffer(String.valueOf(CatalogUtil.getLevelStr(catalogID))).append(
							"js/vote_").append(vote.getID()).toString().replaceAll("//", "/")
					+ ".js'></script>";
			code = code + "\n</div>";
			jsCodes.append(code);
			if (!(new File(new StringBuffer(String.valueOf(Config.getContextRealPath())).append(
					Config.getValue("Statical.TargetDir")).append("/").append(
					SiteUtil.getAlias(vote.getSiteID())).append("/js/").toString().replaceAll("//",
					"/")
					+ "vote_" + vote.getID() + ".js").exists())) {
				Vote.generateJS(vote.getID());
			}
		}
		$S("JSCode", jsCodes.toString());
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.document.Article JD-Core Version: 0.5.3
 */