package com.zving.cms.resource;

import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCAudioRelaSchema;
import com.zving.schema.ZCAudioRelaSet;
import com.zving.schema.ZCAudioSchema;
import com.zving.schema.ZCAudioSet;
import com.zving.schema.ZCCatalogSchema;
import java.io.File;
import java.util.Date;

public class Audio extends Page {
	public static Mapx initEditDialog(Mapx params) {
		ZCAudioSchema Audio = new ZCAudioSchema();
		Audio.setID(params.getString("ID"));
		Audio.fill();
		params.putAll(Audio.toMapx());
		return params;
	}

	public static Mapx initUploadDialog(Mapx params) {
		String CatalogID = params.getString("CatalogID");
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(CatalogID);
		catalog.fill();
		params.putAll(catalog.toMapx());
		params.put("CustomColumn", ColumnUtil.getHtml("1", String.valueOf(catalog.getID())));
		if (StringUtil.isNotEmpty(params.getString("CustomColumn"))) {
			params.put("hasColumn", "yes");
		}
		return params;
	}

	public void copy() {
		long catalogID = Long.parseLong($V("CatalogID"));
		String Alias = Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		String catalogPath = CatalogUtil.getPath(catalogID);
		String InnerCode = new QueryBuilder("select InnerCode from zccatalog where ID = ?",
				catalogID).executeString();
		String newPath = "Audio/" + catalogPath;
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
		ZCAudioSet AudioSet = new ZCAudioSchema().query(new QueryBuilder(" where ID in (" + IDs
				+ ")"));
		boolean flag = true;
		for (int i = 0; i < AudioSet.size(); ++i) {
			ZCAudioSchema Audio = AudioSet.get(i);
			String oldPath = Alias + Audio.getPath();
			String oldFileName = oldPath + Audio.getFileName();
			Audio.setID(NoUtil.getMaxID("DocID"));
			Audio.setCatalogID(catalogID);
			Audio.setCatalogInnerCode(InnerCode);
			Audio.setPath(newPath);
			Audio.setFileName(Audio.getID()
					+ Audio.getFileName().substring(Audio.getFileName().lastIndexOf(".")));
			Audio.setAddTime(new Date());
			Audio.setAddUser(User.getUserName());
			File directory = new File(Config.getContextRealPath() + Alias + newPath);
			if (!(directory.exists())) {
				directory.mkdirs();
			}
			if (!(FileUtil.copy(Config.getContextRealPath() + oldFileName, Config
					.getContextRealPath()
					+ Alias + newPath + Audio.getFileName()))) {
				flag = false;
				break;
			}
		}
		if ((flag) && (AudioSet.insert()))
			this.Response.setLogInfo(1, "复制音频成功");
		else
			this.Response.setLogInfo(0, "复制音频失败");
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
		trans.add(new QueryBuilder(
				"update ZCAudio set catalogid=? ,CatalogInnerCode=? where ID in (" + IDs + ")",
				catalogID, CatalogUtil.getInnerCode(catalogID)));
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
		ZCAudioSet AudioSet = new ZCAudioSchema().query(new QueryBuilder(" where id in (" + IDs
				+ ")"));
		ZCAudioRelaSet AudioRelaSet = new ZCAudioRelaSchema().query(new QueryBuilder(
				" where id in (" + IDs + ")"));
		for (int i = 0; i < AudioSet.size(); ++i) {
			FileUtil.delete(Config.getContextRealPath() + Alias + AudioSet.get(i).getPath()
					+ AudioSet.get(i).getFileName());
		}
		Transaction trans = new Transaction();
		trans.add(AudioSet, 5);
		trans.add(AudioRelaSet, 5);
		trans
				.add(new QueryBuilder(
						"update zccatalog set total = (select count(*) from zcaudio where catalogID =?) where ID =?",
						AudioSet.get(0).getCatalogID(), AudioSet.get(0).getCatalogID()));
		if (trans.commit())
			this.Response.setLogInfo(1, "删除音频成功");
		else
			this.Response.setLogInfo(0, "删除音频失败");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.resource.Audio JD-Core Version: 0.5.3
 */