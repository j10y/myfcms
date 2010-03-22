package com.zving.cms.resource;

import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCAttachmentRelaSchema;
import com.zving.schema.ZCAttachmentRelaSet;
import com.zving.schema.ZCAttachmentSchema;
import com.zving.schema.ZCAttachmentSet;
import com.zving.schema.ZCCatalogSchema;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;

public class Attachment extends Page {
	public static Mapx init(Mapx params) {
		String CatalogID = params.getString("CatalogID");
		if (StringUtil.isEmpty(CatalogID)) {
			CatalogID = (String) new QueryBuilder(new StringBuffer(
					"select ID from ZCCatalog where Type = 7 and SiteID = ").append(
					Application.getCurrentSiteID()).append(" and Name = '默认附件'").toString())
					.executeOneValue();
		}
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(CatalogID);
		catalog.fill();
		DataTable dt = new QueryBuilder("select name,id from zccatalog ").executePagedDataTable(
				100, 0);
		params.put("Who", HtmlUtil.dataTableToOptions(dt));
		params.putAll(catalog.toMapx());

		String imagePath = "upload/Image/nopicture.jpg";
		params.put("ImagePath", imagePath);
		params.put("PicSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + imagePath);
		System.out.println(params);
		return params;
	}

	public static Mapx initEditDialog(Mapx params) {
		ZCAttachmentSchema Attachment = new ZCAttachmentSchema();
		Attachment.setID(params.getString("ID"));
		Attachment.fill();
		params.putAll(Attachment.toMapx());
		return params;
	}

	public void copy() {
		long catalogID = Long.parseLong($V("CatalogID"));
		String Alias = Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		String catalogPath = CatalogUtil.getPath(catalogID);
		String newPath = "Attachment/" + catalogPath;
		String InnerCode = new QueryBuilder("select InnerCode from zccatalog where ID = ?",
				catalogID).executeString();
		File dir = new File(Config.getContextRealPath() + Alias + newPath);
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		String IDs = $V("IDs");
		if (!(StringUtil.checkID(IDs))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		ZCAttachmentSet AttachmentSet = new ZCAttachmentSchema().query(new QueryBuilder(
				" where ID in (" + IDs + ")"));
		boolean flag = true;
		for (int i = 0; i < AttachmentSet.size(); ++i) {
			ZCAttachmentSchema Attachment = AttachmentSet.get(i);
			String oldFileName = Alias + Attachment.getPath() + Attachment.getFileName();
			Attachment.setID(NoUtil.getMaxID("DocID"));
			Attachment.setCatalogID(catalogID);
			Attachment.setCatalogInnerCode(InnerCode);
			Attachment.setPath(newPath);
			Attachment
					.setFileName(Attachment.getID()
							+ Attachment.getFileName().substring(
									Attachment.getFileName().lastIndexOf(".")));
			Attachment.setAddTime(new Date());
			Attachment.setAddUser(User.getUserName());
			File directory = new File(Config.getContextRealPath() + Alias + newPath);
			if (!(directory.exists())) {
				directory.mkdirs();
			}
			if (!(FileUtil.copy(Config.getContextRealPath() + oldFileName, Config
					.getContextRealPath()
					+ Alias + newPath + Attachment.getFileName()))) {
				flag = false;
				break;
			}
		}
		if ((flag) && (AttachmentSet.insert()))
			this.Response.setLogInfo(1, "复制附件成功！");
		else
			this.Response.setLogInfo(0, "复制附件失败！");
	}

	public void transfer() {
		long catalogID = Long.parseLong($V("CatalogID"));
		String IDs = $V("IDs");
		if (!(StringUtil.checkID(IDs))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction trans = new Transaction();
		trans
				.add(new QueryBuilder(
						"update ZCAttachment set catalogid=? ,CatalogInnerCode=? where ID in ("
								+ IDs + ")", catalogID, CatalogUtil.getInnerCode(catalogID)));
		if (trans.commit())
			this.Response.setLogInfo(1, "转移成功");
		else
			this.Response.setLogInfo(0, "转移失败");
	}

	public void del() {
		String IDs = $V("IDs");
		if (!(StringUtil.checkID(IDs))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		String Alias = Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		ZCAttachmentSet AttachmentSet = new ZCAttachmentSchema().query(new QueryBuilder(
				" where id in (" + IDs + ")"));
		ZCAttachmentRelaSet AttachmentRelaSet = new ZCAttachmentRelaSchema()
				.query(new QueryBuilder(" where id in (" + IDs + ")"));
		for (int i = 0; i < AttachmentSet.size(); ++i) {
			FileUtil.delete(Config.getContextRealPath() + Alias + AttachmentSet.get(i).getPath()
					+ AttachmentSet.get(i).getFileName());
		}
		Transaction trans = new Transaction();
		trans.add(AttachmentSet, 5);
		trans.add(AttachmentRelaSet, 5);
		trans
				.add(new QueryBuilder(
						"update zccatalog set total = (select count(*) from zcattachment where catalogID =?) where ID =?",
						AttachmentSet.get(0).getCatalogID(), AttachmentSet.get(0).getCatalogID()));
		if (trans.commit())
			this.Response.setLogInfo(1, "删除附件成功！");
		else
			this.Response.setLogInfo(0, "删除附件失败！");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.resource.Attachment JD-Core Version: 0.5.3
 */