package com.zving.search.crawl;

import com.zving.cms.api.ArticleAPI;
import com.zving.cms.datachannel.Deploy;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.script.EvalException;
import com.zving.framework.script.ScriptEngine;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.RegexParser;
import com.zving.framework.utility.RegexParser.MatchedMap;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.ImageUtilEx;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCImageSet;
import com.zving.search.DocumentList;
import com.zving.search.SearchUtil;
import com.zving.search.WebDocument;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;

public class Crawler {
	private CrawlConfig config;
	private DocumentList list;
	private int startLevel;
	private FileDownloader fileDownloader;
	private UrlExtracter extracter;
	private int currentLevel;
	private int currentLevelCount;
	private int currentLevelDownloadedCount;
	private Filter retryFilter;
	public LongTimeTask task;
	Pattern framePattern;
	Pattern stylePattern;
	Pattern scriptPattern;
	Pattern linkPattern;
	Pattern titlePattern;
	private ScriptEngine se;
	private double total;

	public LongTimeTask getTask() {
		return this.task;
	}

	public Crawler() {
		this(null);
	}

	public Crawler(LongTimeTask ltt) {
		this.fileDownloader = new FileDownloader();

		this.extracter = new UrlExtracter();

		this.framePattern = Pattern.compile("<iframe.*?<\\/iframe>", 34);

		this.stylePattern = Pattern.compile("<style.*?><\\/style>", 34);

		this.scriptPattern = Pattern.compile("<script.*?<\\/script>", 34);

		this.linkPattern = Pattern.compile("<a .*?>(.*?)<\\/a>", 34);

		this.titlePattern = Pattern.compile("<.*?>", 34);

		this.total = 0.0D;

		this.task = ltt;
		if (ltt == null)
			this.task = LongTimeTask.createEmptyInstance();
	}

	public DocumentList crawl() {
		if (this.list == null) {
			String path = Config.getContextRealPath() + CrawlConfig.getWebGatherDir();
			if ((!(path.endsWith("/"))) && (!(path.endsWith("\\")))) {
				path = path + "/";
			}
			path = path + this.config.getID() + "/";
			File f = new File(path);
			if (!(f.exists())) {
				f.mkdirs();
			}
			this.list = new DocumentList(path);
		}
		try {
			this.list.setCrawler(this);
			this.fileDownloader
					.setDenyExtension(".gif.jpg.jpeg.swf.bmp.png.js.wmv.css.ico.avi.mpg.mpeg.mp3.mp4.wma.rm.rmvb.exe.tar.gz.zip.rar");
			this.fileDownloader.setThreadCount(this.config.getThreadCount());
			this.fileDownloader.setTimeout(this.config.getTimeout() * 1000);

			if (this.config.isProxyFlag()) {
				this.fileDownloader.setProxyFlag(this.config.isProxyFlag());
				this.fileDownloader.setProxyHost(this.config.getProxyHost());
				this.fileDownloader.setProxyPassword(this.config.getProxyPassword());
				this.fileDownloader.setProxyUserName(this.config.getProxyUserName());
				this.fileDownloader.setProxyPort(this.config.getProxyPort());
			} else if ("Y".equalsIgnoreCase(Config.getValue("Proxy.IsUseProxy"))) {
				this.fileDownloader.setProxyFlag(true);
				this.fileDownloader.setProxyHost(Config.getValue("Proxy.Host"));
				this.fileDownloader.setProxyPassword(Config.getValue("Proxy.Password"));
				this.fileDownloader.setProxyUserName(Config.getValue("Proxy.UserName"));
				this.fileDownloader.setProxyPort(Integer.parseInt(Config.getValue("Proxy.Port")));
			}

			prepareScript();
			for (int i = 0; (i < this.config.getUrlLevels().length)
					&& (i <= this.config.getMaxLevel()); ++i) {
				try {
					if ((i >= this.startLevel) || (this.task.checkStop())) {
						this.task.setCurrentInfo("正在处理第" + (i + 1) + "层级URL");
						this.currentLevel = i;
						dealOneLevel();
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			if (!this.task.checkStop())
				if (this.config.isCopyImageFlag()) {
					int maxPage = this.config.getMaxPageCount();
					this.config.setMaxPageCount(-1);
					this.fileDownloader
							.setDenyExtension(".html.htm.jsp.php.asp.shtml.swf.js.css.ico.avi.mpg.mpeg.mp3.mp4.wma.wmv.rm.rmvb.exe.tar.gz.zip.rar");
					this.currentLevel += 1;
					this.task.setCurrentInfo("正在处理第" + (this.currentLevel + 1) + "层级URL");
					String[] urls = this.config.getUrlLevels();
					urls = (String[]) ArrayUtils
							.add(
									urls,
									"${A}.gif\n${A}.jpg\n${A}.jpeg\n${A}.png\n${A}.bmp\n${A}.GIF\n${A}.JPG\n${A}.JPEG\n${A}.PNG\n${A}.BMP");
					this.config.setUrlLevels(urls);
					dealOneLevel();
					this.config.setMaxPageCount(maxPage);
					this.fileDownloader
							.setDenyExtension(".gif.jpg.jpeg.swf.bmp.png.js.wmv.css.ico.avi.mpg.mpeg.mp3.mp4.wma.rm.rmvb.exe.tar.gz.zip.rar");
				}
			retryWithFilter();
			writeArticle();
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} finally {
			this.list.save();
			this.list.close();
		}
		return this.list;
	}

	public void writeArticle() {
		if (this.config.getType() == 1) {
			QueryBuilder imageQB = new QueryBuilder(
					"select id from zccatalog where type=4 and siteid=?", CatalogUtil
							.getSiteID(this.config.getCatalogID()));
			String imageCatalogID = imageQB.executeString();
			if (StringUtil.isEmpty(CatalogUtil.getSiteID(this.config.getCatalogID()))) {
				LogUtil.getLogger().warn("文档采集的目的栏目不存在：ID=" + this.config.getCatalogID());
				return;
			}
			String sitePath = SiteUtil.getAbsolutePath(CatalogUtil.getSiteID(this.config
					.getCatalogID()));
			String imagePath = "upload/Image/" + CatalogUtil.getAlias(imageCatalogID) + "/";

			RegexParser rp = this.config.getTemplate("Ref1");
			RegexParser[] filters = this.config.getFilterBlocks();
			this.list.moveFirst();
			WebDocument doc = null;
			int cSuccess = 0;
			int cFailure = 0;
			int cLost = 0;
			while ((doc = this.list.next()) != null) {
				if (this.task.checkStop()) {
					return;
				}
				if ((doc.isTextContent()) && (doc.getContent() != null)) {
					String text = doc.getContentText();
					RegexParser.MatchedMap[] maps = rp.getMatchedMaps(text);

					if (maps.length > 0) {
						String title = maps[0].get("Title");
						String content = maps[0].get("Content");
						String author = maps[0].get("Author");
						String source = maps[0].get("Source");
						String strDate = maps[0].get("PublishDate");
						Date publishDate = doc.getLastmodifiedDate();
						if ((StringUtil.isNotEmpty(strDate))
								&& (StringUtil.isNotEmpty(this.config.getPublishDateFormat()))) {
							publishDate = DateUtil.parse(strDate, this.config
									.getPublishDateFormat());
						}
						ArticleAPI api = new ArticleAPI();
						try {
							ZCArticleSchema article = new ZCArticleSchema();
							if (StringUtil.isNotEmpty(title)) {
								title = this.titlePattern.matcher(title).replaceAll("").trim();
								article.setTitle(title);
							} else {
								++cLost;
							}
							if (StringUtil.isNotEmpty(content)) {
								content = content.trim();
								if (this.config.isCleanLinkFlag()) {
									content = this.framePattern.matcher(content).replaceAll("");
									content = this.stylePattern.matcher(content).replaceAll("");
									content = this.scriptPattern.matcher(content).replaceAll("");
									content = this.linkPattern.matcher(content).replaceAll("$1");
								}
								if (filters != null) {
									for (int k = 0; k < filters.length; ++k) {
										content = filters[k].replace(content, "");
									}
								}

								String str = dealImage(content, doc.getUrl(), sitePath, imagePath,
										imageCatalogID);
								article.setContent(str);
							} else {
								++cLost;
							}
							if (StringUtil.isNotEmpty(author)) {
								article.setAuthor(author);
							}
							if (StringUtil.isNotEmpty(source)) {
								article.setReferName(source);
							}
							article.setReferURL(doc.getUrl());
							article.setPublishDate(publishDate);
							article.setCatalogID(this.config.getCatalogID());
							Date date = (Date) new QueryBuilder(
									"select PublishDate from ZCArticle where ReferURL=? and CatalogID=?",
									doc.getUrl(), this.config.getCatalogID()).executeOneValue();
							if (date != null) {
								if (date.getTime() < doc.getLastDownloadTime()) {
									QueryBuilder qb = new QueryBuilder(
											"update ZCArticle set Title=?,Content=? where CatalogID=? and ReferURL=?");
									qb.add(title);
									qb.add(content);
									qb.add(this.config.getCatalogID());
									qb.add(doc.getUrl());
									qb.executeNoQuery();
								}
								++cSuccess;
							}
							api.setSchema(article);
							if (api.insert() > 0L) {
								++cSuccess;
							}
							++cFailure;
						} catch (Exception e) {
							++cFailure;
							e.printStackTrace();
						}
					} else {
						LogUtil.getLogger().info("未能匹配" + doc.getUrl());
						++cLost;
					}
				} else {
					++cLost;
				}
				label794: label847: this.task.setCurrentInfo("正在转换文档, <font color='green'>"
						+ cSuccess + "</font> 个成功, <font color='red'>" + cFailure
						+ "</font> 个失败, <font color='green'>" + cLost + "</font> 个未匹配");
			}
		}
	}

	public String dealImage(String content, String baseUrl, String sitePath, String imagePath,
			String imageCatalogID) {
		Matcher m = SearchUtil.resourcePattern1.matcher(content);
		int lastEndIndex = 0;
		StringBuffer sb = new StringBuffer();
		String url;
		String ext;
		String fullUrl;
		WebDocument tdoc;
		byte[] data;
		String imageFilePath;
		while (m.find(lastEndIndex)) {
			url = m.group(2);
			ext = ServletUtil.getUrlExtension(url);
			if ((SearchUtil.isRightUrl(url)) && (StringUtil.isNotEmpty(ext))
					&& (".gif.jpg.jpeg.bmp.png".indexOf(ext) >= 0)) {
				fullUrl = SearchUtil.normalizeUrl(url, baseUrl);
				tdoc = this.list.get(fullUrl);
				if ((tdoc != null) && (!(tdoc.isTextContent()))) {
					data = tdoc.getContent();
					sb.append(content.substring(lastEndIndex, m.start()));
					imageFilePath = saveImage(data, sitePath, imagePath, imageCatalogID, ext,
							fullUrl);
					sb.append(StringUtil.replaceEx(m.group(0), url, imageFilePath));
				} else {
					sb.append(content.substring(lastEndIndex, m.end()));
				}
			} else {
				sb.append(content.substring(lastEndIndex, m.end()));
			}
			lastEndIndex = m.end();
		}
		sb.append(content.substring(lastEndIndex));
		content = sb.toString();

		sb = new StringBuffer();
		m = SearchUtil.resourcePattern2.matcher(content);
		lastEndIndex = 0;
		while (m.find(lastEndIndex)) {
			url = m.group(3);
			ext = ServletUtil.getUrlExtension(url);
			if ((SearchUtil.isRightUrl2(url)) && (StringUtil.isNotEmpty(ext))
					&& (".gif.jpg.jpeg.bmp.png".indexOf(ext) >= 0)) {
				fullUrl = SearchUtil.normalizeUrl(url, baseUrl);
				tdoc = this.list.get(fullUrl);
				if ((tdoc != null) && (!(tdoc.isTextContent()))) {
					data = tdoc.getContent();
					sb.append(content.substring(lastEndIndex, m.start()));
					imageFilePath = saveImage(data, sitePath, imagePath, imageCatalogID, ext,
							fullUrl);
					sb.append(StringUtil.replaceEx(m.group(0), url, imageFilePath));
				} else {
					sb.append(content.substring(lastEndIndex, m.end()));
				}
			} else {
				sb.append(content.substring(lastEndIndex, m.end()));
			}
			lastEndIndex = m.end();
		}
		sb.append(content.substring(lastEndIndex));
		return sb.toString();
	}

	public static String saveImage(byte[] data, String path1, String path2, String catalogID,
			String ext, String imageURL) {
		ZCImageSchema image = new ZCImageSchema();
		image.setSourceURL(imageURL);
		boolean flag = true;
		ZCImageSet set = image.query();
		if (set.size() > 0) {
			image = set.get(0);
			File f = new File(path1 + path2 + image.getSrcFileName());
			if (f.exists()) {
				if (f.length() == data.length) {
					flag = false;
				}
				FileUtil.writeByte(f, data);
			}
		} else {
			long imageID = NoUtil.getMaxID("DocID");
			int random = NumberUtil.getRandomInt(10000);
			String srcFileName = imageID + random + ext;
			FileUtil.writeByte(path1 + path2 + srcFileName, data);
			image.setID(imageID);
			image.setCatalogID(catalogID);
			image.setCatalogInnerCode(CatalogUtil.getInnerCode(catalogID));
			image.setName(String.valueOf(imageID + random));
			image.setOldName(String.valueOf(imageID + random));
			image.setSiteID(CatalogUtil.getSiteID(catalogID));
			image.setPath(path2);
			image.setFileName(imageID + NumberUtil.getRandomInt(10000) + ".jpg");
			image.setSrcFileName(srcFileName);
			image.setSuffix(ext);
			image.setCount(1L);
			image.setWidth(0L);
			image.setHeight(0L);
			image.setHitCount(0L);
			image.setStickTime(0L);
			image.setAuthor("Crawler");
			image.setSystem("CMS");
			image.setOrderFlag(OrderUtil.getDefaultOrder());
			image.setAddTime(new Date());
			image.setAddUser("SYS");
			image.setSiteID(CatalogUtil.getSiteID(image.getCatalogID()));
			image.insert();
		}
		if (flag) {
			try {
				ArrayList imageList = ImageUtilEx.afterUploadImage(image, path1 + path2);
				Deploy d = new Deploy();
				d.addJobs(image.getSiteID(), imageList);

				return Config.getContextPath() + Config.getValue("UploadDir") + "/"
						+ SiteUtil.getAlias(CatalogUtil.getSiteID(catalogID)) + "/"
						+ image.getPath() + "1_" + image.getFileName().replaceAll("//", "/");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(CatalogUtil.getSiteID(catalogID)) + "/" + image.getPath()
				+ image.getSrcFileName().replaceAll("//", "/");
	}

	public void retryWithFilter() {
		if (this.retryFilter != null) {
			LogUtil.getLogger().info("Retry Downloading URL ........");
			WebDocument doc = this.list.next();
			while (doc != null) {
				if (this.retryFilter.filter(doc)) {
					FileDownloader.downloadOne(doc);
					if (this.list.hasContent(doc.getUrl())) {
						this.list.put(doc);
					}
				}
				doc = this.list.next();
			}
		}
	}

	private void prepareScript() throws EvalException {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotEmpty(this.config.getScript())) {
			this.se = new ScriptEngine(this.config.getLanguage());
			this.se.importPackage("com.zving.framework.data");
			this.se.importPackage("com.zving.framework.utility");
			if (this.config.getLanguage() == 1) {
				sb.append("StringBuffer _Result = new StringBuffer();\n");
				sb.append("void write(String str){_Result.append(str);}\n");
				sb.append("void writeln(String str){_Result.append(str+\"\\n\");}\n");
				sb.append(this.config.getScript());
				sb.append("\nreturn _Result.toString();\n");
			} else {
				sb.append("var _Result = [];\n");
				sb.append("function write(str){_Result.push(str);}\n");
				sb.append("function writeln(str){_Result.push(str+\"\\n\");}\n");
				sb.append(this.config.getScript());
				sb.append("\nreturn _Result.join('');\n");
			}
			this.se.compileFunction("_EvalScript", sb.toString());
		}
	}

	public void executeScript(String when, CrawlScriptUtil util) {
		this.currentLevelDownloadedCount += 1;
		this.task.setCurrentInfo("正在抓取" + util.getCurrentUrl());
		if (this.total == 0.0D) {
			for (int i = 0; i < this.config.getUrlLevels().length + 1; ++i) {
				this.total += (i + 1) * (i + 1) * 400;
			}
		}
		double t = (this.currentLevel + 1) * (this.currentLevel + 1) * 400;
		t = t / this.total + (this.currentLevel + 1) * (this.currentLevel + 2) / this.total
				* this.currentLevelDownloadedCount * 1.0D / this.currentLevelCount;
		int percent = new Double(t * 100.0D).intValue();
		this.task.setPercent(percent);
		if (StringUtil.isNotEmpty(this.config.getScript())) {
			this.se.setVar("Util", util);
			this.se.setVar("When", when);
			this.se.setVar("Level", new Integer(this.currentLevel));
			try {
				this.se.executeFunction("_EvalScript");
			} catch (EvalException e) {
				e.printStackTrace();
			}
		}
	}

	private void dealOneLevel() {
		String[] arr = this.config.getUrlLevels()[this.currentLevel].trim().split("\n");
		this.task.setCurrentInfo("正在生成第" + (this.currentLevel + 1) + "层级URL");
		this.currentLevelCount = 0;
		if (this.currentLevel != 0)
			this.extracter.extract(this.list, this.currentLevel, this.fileDownloader);
		else {
			for (int i = 0; i < arr.length; ++i) {
				String url = arr[i];
				if (StringUtil.isEmpty(url)) {
					continue;
				}
				if (!(this.list.containsKey(url))) {
					WebDocument doc = new WebDocument();
					doc.setUrl(url);
					doc.setLevel(this.currentLevel);
					this.list.put(doc);
				}
			}
		}
		this.currentLevelCount = this.list.size();
		this.fileDownloader.downloadList(this.list, this.currentLevel);
	}

	public long getTaskID() {
		return this.config.getID();
	}

	public int getStartLevel() {
		return this.startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}

	public Filter getRetryFilter() {
		return this.retryFilter;
	}

	public void setRetryFilter(Filter retryFilter) {
		this.retryFilter = retryFilter;
	}

	public DocumentList getList() {
		return this.list;
	}

	public void setList(DocumentList list) {
		this.list = list;
	}

	public FileDownloader getFileDownloader() {
		return this.fileDownloader;
	}

	public CrawlConfig getConfig() {
		return this.config;
	}

	public void setConfig(CrawlConfig config) {
		this.config = config;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.search.crawl.Crawler JD-Core Version: 0.5.3
 */