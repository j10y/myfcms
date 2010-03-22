package com.zving.datachannel;

import com.zving.cms.pub.CatalogUtil;
import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCWebGatherSchema;
import com.zving.schema.ZCWebGatherSet;
import com.zving.search.DocumentList;
import com.zving.search.crawl.CrawlConfig;
import com.zving.search.crawl.Crawler;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Date;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class FromWeb extends Page {
	public static Mapx init(Mapx map) {
		DataTable dt = new QueryBuilder("select code,id,name from ZCCustomTable")
				.executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			dt.set(i, 0, dt.getString(i, 0) + "(" + dt.getString(i, 2) + ")");
		}
		map.put("CustomTables", HtmlUtil.dataTableToOptions(dt));
		return map;
	}

	public static Mapx initDialog(Mapx map) {
		String id = map.getString("ID");
		ZCWebGatherSchema wg = new ZCWebGatherSchema();
		wg.setID(Long.parseLong(id));
		wg.fill();
		return map;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String sql = "select ID,Name,Intro,Type,Addtime,Adduser,ConfigXML from ZCWebGather where SiteID=?";
		DataTable dt = new QueryBuilder(sql, Application.getCurrentSiteID()).executeDataTable();
		Mapx map = new Mapx();
		map.put("D", "文档采集");
		map.put("T", "自定义采集");
		dt.decodeColumn("Type", map);
		dt.insertColumn("StartURL");
		dt.insertColumn("ThreadCount");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String xml = dt.getString(i, "ConfigXML");
			CrawlConfig cc = new CrawlConfig();
			cc.parseXML(xml);
			dt.set(i, "StartURL", cc.getUrlLevels()[0]);
			dt.set(i, "ThreadCount", cc.getThreadCount());
		}
		dga.bindData(dt);
	}

	public void getCatalogName() {
		String catalogID = this.Request.getString("_Param0");
		String name = CatalogUtil.getName(catalogID);
		$S("Name", name);
	}

	public void add() {
		ZCWebGatherSchema wg = new ZCWebGatherSchema();
		String id = $V("ID");
		if (StringUtil.isNotEmpty(id)) {
			wg.setID(Long.parseLong(id));
			wg.fill();
		} else {
			wg.setID(NoUtil.getMaxID("GatherID"));
			wg.setSiteID(Application.getCurrentSiteID());
			wg.setAddTime(new Date());
			wg.setAddUser(User.getUserName());
		}
		wg.setValue(this.Request);

		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(Constant.GlobalCharset);
		Element root = doc.addElement("configs");
		Element ele = root.addElement("config");
		ele.addAttribute("key", "CopyImage");
		ele.addAttribute("value", $V("CopyImage"));

		ele = root.addElement("config");
		ele.addAttribute("key", "CleanLink");
		ele.addAttribute("value", $V("CleanLink"));

		ele = root.addElement("config");
		ele.addAttribute("key", "PublishDateFormat");
		ele.addAttribute("value", $V("PublishDateFormat"));

		ele = root.addElement("config");
		ele.addAttribute("key", "CatalogID");
		ele.addAttribute("value", $V("CatalogID"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ThreadCount");
		ele.addAttribute("value", $V("ThreadCount"));

		ele = root.addElement("config");
		ele.addAttribute("key", "MaxPageCount");
		ele.addAttribute("value", $V("MaxPageCount"));

		ele = root.addElement("config");
		ele.addAttribute("key", "RetryTimes");
		ele.addAttribute("value", $V("RetryTimes"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ProxyFlag");
		ele.addAttribute("value", $V("ProxyFlag"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ProxyHost");
		ele.addAttribute("value", $V("ProxyHost"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ProxyPort");
		ele.addAttribute("value", $V("ProxyPort"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ProxyUserName");
		ele.addAttribute("value", $V("ProxyUserName"));

		ele = root.addElement("config");
		ele.addAttribute("key", "ProxyPassword");
		ele.addAttribute("value", $V("ProxyPassword"));

		ele = root.addElement("config");
		ele.addAttribute("key", "TimeOut");
		ele.addAttribute("value", $V("TimeOut"));

		ele = root.addElement("config");
		ele.addAttribute("key", "FilterFlag");
		ele.addAttribute("value", $V("FilterFlag"));

		ele = root.addElement("script");
		ele.addAttribute("language", $V("Lang"));
		ele.addCDATA($V("Script"));

		ele = root.addElement("filterExpr");
		ele.addCDATA($V("FilterExpr"));

		Object[] ks = this.Request.keyArray();
		Object[] vs = this.Request.valueArray();
		for (int i = 0; i < this.Request.size(); ++i) {
			String key = ks[i].toString();
			String content;
			if (key.startsWith("RefCode")) {
				String code = vs[i].toString();
				content = this.Request.getString(key.replaceAll("RefCode", "Template"));
				ele = root.addElement("template");
				ele.addAttribute("code", code);
				ele.addCDATA(content);
			}
			String level;
			if (key.startsWith("URL")) {
				level = key.substring(3);
				content = this.Request.getString(key);
				ele = root.addElement("urls");
				ele.addAttribute("level", level);
				ele.addCDATA(content);
			}
			if (key.startsWith("FilterBlock")) {
				level = key.substring(11);
				content = this.Request.getString(key);
				ele = root.addElement("filterBlock");
				ele.addAttribute("no", level);
				ele.addCDATA(content);
			}
		}

		StringWriter sw = new StringWriter();
		try {
			XMLWriter output = new XMLWriter(sw, format);
			output.write(doc);
			output.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		wg.setConfigXML(sw.toString());
		boolean flag = (StringUtil.isEmpty(id)) ? wg.insert() : wg.update();
		if (flag)
			this.Response.setMessage("提交成功");
		else
			this.Response.setError("提交失败");
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction trans = new Transaction();
		ZCWebGatherSchema wg = new ZCWebGatherSchema();
		ZCWebGatherSet set = wg.query(new QueryBuilder("where id in (" + ids + ")"));
		trans.add(set, 5);
		trans.add(new QueryBuilder("delete from ZDSchedule where SourceID in (" + ids
				+ ") and TypeCode='WebCrawl'"));
		if (trans.commit()) {
			String path = Config.getContextRealPath() + CrawlConfig.getWebGatherDir();
			for (int i = 0; i < set.size(); ++i) {
				wg = set.get(i);
				String str = path + wg.getID() + "/";
				if (new File(str).exists()) {
					DocumentList list = new DocumentList(str);
					list.delete();
				}
			}
			this.Response.setStatus(1);
			this.Response.setMessage("删除成功！");
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void delResult() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		ZCWebGatherSchema wg = new ZCWebGatherSchema();
		ZCWebGatherSet set = wg.query(new QueryBuilder("where id in (" + ids + ")"));
		try {
			String path = Config.getContextRealPath() + CrawlConfig.getWebGatherDir();
			if ((!(path.endsWith("/"))) && (!(path.endsWith("\\")))) {
				path = path + "/";
			}
			for (int i = 0; i < set.size(); ++i) {
				wg = set.get(i);
				String str = path + wg.getID() + "/";
				if (new File(str).exists()) {
					DocumentList list = new DocumentList(str);
					list.delete();
				}
			}
			this.Response.setStatus(0);
			this.Response.setMessage("清空采集结果成功!");
		} catch (Exception e) {
			this.Response.setStatus(0);
			this.Response.setMessage("清空采集结果时发生错误:任务\"" + wg.getName() + "\"正在执行中!");
		}
	}

	public void execute() {
		final long id = Long.parseLong($V("ID"));
		LongTimeTask ltt = LongTimeTask.getInstanceByType("WebGather" + id);
		if (ltt != null) {
			this.Response.setError("相关任务正在运行中，请先中止！");
			return;
		}
		ltt = new LongTimeTask() {

			public void execute() {
				ZCWebGatherSchema wg = new ZCWebGatherSchema();
				wg.setID(id);
				wg.fill();
				CrawlConfig cc = new CrawlConfig();
				cc.parse(wg);
				Crawler crawler = new Crawler(this);
				crawler.setConfig(cc);
				crawler.crawl();
				setPercent(100);
			}
		};
		ltt.setType("WebGather" + id);
		ltt.setUser(User.getCurrent());
		ltt.start();
		$S("TaskID", ltt.getTaskID());
	}

	public void cancel() {
		String id = $V("ID");
		this.Response.setMessage(LongTimeTask.cancelByType("WebGather" + id));
	}

	public static void main(String[] args) {
		ZCWebGatherSchema wg = new ZCWebGatherSchema();
		wg.setID(5L);
		wg.fill();
		CrawlConfig cc = new CrawlConfig();
		cc.parse(wg);
		Crawler crawler = new Crawler();
		crawler.setConfig(cc);
		crawler.crawl();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.datachannel.FromWeb JD-Core Version: 0.5.3
 */