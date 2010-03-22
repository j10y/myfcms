package com.zving.cms.site;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.pub.CatalogUtil;
import com.zving.framework.Config;
import com.zving.framework.CookieImpl;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.Transaction;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.schema.ZCSiteSchema;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CatalogSite extends Page {
	public static Mapx init(Mapx params) {
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(Application.getCurrentSiteID());
		site.fill();
		Mapx map = site.toMapx();
		if (StringUtil.isEmpty(site.getIndexTemplate())) {
			map.put("edit", " ");
		} else {
			String s1 = "<a id='editLink' href='javascript:void(0);' onclick=\"openEditor('"
					+ site.getIndexTemplate()
					+ "')\"><img src='../Platform/Images/icon_edit.gif' width='14' height='14'></a>";
			map.put("edit", s1);
		}
		return map;
	}

	public void publishIndex() {
		long id = publishTask(Application.getCurrentSiteID());
		this.Response.setStatus(1);
		$S("TaskID", id);
	}

	private long publishTask(final long siteID) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishIndex(siteID, this);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public void changeTemplate() {
		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(Application.getCurrentSiteID());
		if (!(site.fill())) {
			this.Response.setLogInfo(0, "更新首页模板失败!");
			return;
		}
		String indexTemplate = $V("IndexTemplate");
		if (indexTemplate == null) {
			this.Response.setLogInfo(0, "请选择模板!");
			return;
		}
		String fileName = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir")
				+ "/" + Application.getCurrentSiteAlias() + indexTemplate;
		fileName = fileName.replaceAll("///", "/");
		fileName = fileName.replaceAll("//", "/");
		File file = new File(fileName);
		if (!(file.exists())) {
			this.Response.setLogInfo(0, "该模板文件不存在!");
			return;
		}
		site.setIndexTemplate(indexTemplate);
		Transaction trans = new Transaction();
		trans.add(site, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "更新首页模板成功!");
		else
			this.Response.setLogInfo(0, "更新首页模板失败!");
	}

	public static void onRequestBegin(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("Type");
		if (StringUtil.isEmpty(type)) {
			CookieImpl ci = new CookieImpl(request);
			String id = ci.getCookie("DocList.LastCatalog");
			if (StringUtil.isNotEmpty(id)) {
				String siteID = String.valueOf(Application.getCurrentSiteID());
				if (!(siteID.equals(CatalogUtil.getSiteID(id))))
					return;
				try {
					request.getRequestDispatcher("CatalogBasic.jsp?CatalogID=" + id).forward(
							request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.site.CatalogSite JD-Core Version: 0.5.3
 */