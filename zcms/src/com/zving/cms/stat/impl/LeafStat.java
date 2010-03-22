package com.zving.cms.stat.impl;

import com.zving.cms.stat.AbstractStat;
import com.zving.cms.stat.Counter;
import com.zving.cms.stat.Visit;
import com.zving.cms.stat.VisitCount;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.Queuex;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCVisitLogSchema;
import com.zving.schema.ZCVisitLogSet;
import java.util.Date;

public class LeafStat extends AbstractStat {
	private Mapx siteMap = new Mapx();

	private static Mapx lastVisitMap = new Mapx();

	private static long LastVisitLogID = System.currentTimeMillis();

	private static long LastVisitLogUpdateTime = System.currentTimeMillis();

	public void init() {
	}

	public String getStatType() {
		return "Leaf";
	}

	public void deal(Visit v) {
		if (v.LeafID != 0L) {
			if (!(this.siteMap.containsKey(new Long(v.SiteID)))) {
				VisitCount.getInstance().initLRUMap(v.SiteID, "Article", "StickTime", 10000,
						new LeafExitEventListener("Article", "StickTime"));
				VisitCount.getInstance().initLRUMap(v.SiteID, "Image", "StickTime", 5000,
						new LeafExitEventListener("Image", "StickTime"));
				VisitCount.getInstance().initLRUMap(v.SiteID, "Video", "StickTime", 5000,
						new LeafExitEventListener("Video", "StickTime"));
				VisitCount.getInstance().initLRUMap(v.SiteID, "Article", "PV", 10000,
						new LeafExitEventListener("Article", "PV"));
				VisitCount.getInstance().initLRUMap(v.SiteID, "Image", "PV", 5000,
						new LeafExitEventListener("Image", "PV"));
				VisitCount.getInstance().initLRUMap(v.SiteID, "Video", "PV", 5000,
						new LeafExitEventListener("Video", "PV"));
				this.siteMap.put(new Long(v.SiteID), "");
			}
			if ("Unload".equals(v.Event)) {
				VisitCount.getInstance().addAverage(v.SiteID, v.Type, "StickTime",
						String.valueOf(v.LeafID), v.StickTime);
			} else if (StringUtil.isNotEmpty(v.Type)) {
				VisitCount.getInstance().add(v.SiteID, v.Type, "PV", String.valueOf(v.LeafID));
				Counter.add(v.Type, v.LeafID);
				Counter.addTotalHitCount(v.SiteID);
			}
		}

		if (!("Unload".equals(v.Event)))
			synchronized (lastVisitMap) {
				Queuex q = (Queuex) lastVisitMap.get(new Long(v.SiteID));
				if (q == null) {
					q = new Queuex(200);
					lastVisitMap.put(new Long(v.SiteID), q);
				}
				q.push(v);
				if (v.VisitTime - LastVisitLogUpdateTime > 60000L) {
					updateAllLastVisitlog();
					LastVisitLogUpdateTime = v.VisitTime;
				}
			}
	}

	public void onPeriodChange(int type, long current) {
		if (type == 1) {
			VisitCount.getInstance().clearType("AD", true);
			VisitCount.getInstance().clearType("Article", true);
			VisitCount.getInstance().clearType("Image", true);
			VisitCount.getInstance().clearType("Video", true);
		}
	}

	public void update(VisitCount vc, long current, boolean isNewMonth, boolean isNewPeriod) {
		QueryBuilder qbAD = new QueryBuilder(
				"update ZCAdvertisement set HitCount=HitCount+? where ID=?");
		QueryBuilder qbArticle = new QueryBuilder(
				"update ZCArticle set StickTime=(HitCount*StickTime+?*?)/(HitCount+?),HitCount=HitCount+? where ID=?");
		QueryBuilder qbImage = new QueryBuilder(
				"update ZCImage set StickTime=(HitCount*StickTime+?*?)/(HitCount+?),HitCount=HitCount+? where ID=?");
		QueryBuilder qbVideo = new QueryBuilder(
				"update ZCVideo set StickTime=(HitCount*StickTime+?*?)/(HitCount+?),HitCount=HitCount+? where ID=?");
		qbAD.setBatchMode(true);
		qbArticle.setBatchMode(true);
		qbImage.setBatchMode(true);
		qbVideo.setBatchMode(true);
		long[] sites = vc.getSites();
		for (int i = 0; i < sites.length; ++i) {
			String[] items = vc.getItems(sites[i], "AD", "PV");
			long count;
			for (int k = 0; k < items.length; ++k) {
				count = vc.get(sites[i], "AD", "PV", items[k]);
				if (count == 0L) {
					continue;
				}
				qbAD.add(count);
				qbAD.add(items[k]);
				qbAD.addBatch();
				VisitCount.getInstance().set(sites[i], "AD", "PV", items[k], 0);
			}
			items = vc.getItems(sites[i], "Article", "PV");
			long sticktime;
			for (int k = 0; k < items.length; ++k) {
				count = vc.get(sites[i], "Article", "PV", items[k]);
				if (count == 0L) {
					continue;
				}
				sticktime = vc.get(sites[i], "Article", "StickTime", items[k]);
				qbArticle.add(sticktime);
				qbArticle.add(count);
				qbArticle.add(count);
				qbArticle.add(count);
				qbArticle.add(items[k]);
				qbArticle.addBatch();
				VisitCount.getInstance().set(sites[i], "Article", "PV", items[k], 0);
			}
			items = vc.getItems(sites[i], "Image", "PV");
			for (int k = 0; k < items.length; ++k) {
				count = vc.get(sites[i], "Image", "PV", items[k]);
				if (count == 0L) {
					continue;
				}
				sticktime = vc.get(sites[i], "Image", "StickTime", items[k]);
				qbImage.add(sticktime);
				qbImage.add(count);
				qbImage.add(count);
				qbImage.add(count);
				qbImage.add(items[k]);
				qbImage.addBatch();
				VisitCount.getInstance().set(sites[i], "Image", "PV", items[k], 0);
			}
			items = vc.getItems(sites[i], "Video", "PV");
			for (int k = 0; k < items.length; ++k) {
				count = vc.get(sites[i], "Video", "PV", items[k]);
				if (count == 0L) {
					continue;
				}
				sticktime = vc.get(sites[i], "Video", "StickTime", items[k]);
				qbVideo.add(sticktime);
				qbVideo.add(count);
				qbVideo.add(count);
				qbVideo.add(count);
				qbVideo.add(items[k]);
				qbVideo.addBatch();
				VisitCount.getInstance().set(sites[i], "Video", "PV", items[k], 0);
			}
		}
		qbAD.executeNoQuery();
		qbArticle.executeNoQuery();
		qbImage.executeNoQuery();
		qbVideo.executeNoQuery();
	}

	public synchronized void updateAllLastVisitlog() {
		Object[] sites = lastVisitMap.keyArray();
		for (int i = 0; i < sites.length; ++i)
			updateLastVisitlog(((Long) sites[i]).longValue());
	}

	public void updateLastVisitlog(long siteID) {
		Queuex q = getLastVisitQueue(siteID);
		if (q == null) {
			return;
		}
		Visit[] arr = new Visit[q.size()];
		for (int i = 0; i < q.size(); ++i) {
			arr[i] = ((Visit) q.get(i));
		}
		Mapx map = new QueryBuilder(
				"select code,name from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or TreeLevel<3")
				.executeDataTable().toMapx(0, 1);
		ZCVisitLogSet set = new ZCVisitLogSet();
		for (int i = 0; i < arr.length; ++i) {
			Visit v = arr[i];
			String district = null;
			if (v.District.startsWith("00")) {
				district = map.getString(v.District);
			} else {
				String prov = map.getString(v.District.substring(0, 2) + "0000");
				if ((prov.startsWith("黑龙江")) || (prov.startsWith("内蒙古")))
					prov = prov.substring(0, 3);
				else {
					prov = prov.substring(0, 2);
				}
				if (v.District.endsWith("0000")) {
					district = prov;
				} else {
					String city = map.getString(v.District);
					if (city == null) {
						city = map.getString(v.District.substring(0, 4) + "00");
					}
					district = prov + ((city == null) ? "" : city);
				}
			}
			if (StringUtil.isNotEmpty(v.URL)) {
				ZCVisitLogSchema zv = new ZCVisitLogSchema();
				zv.setURL(v.URL);
				zv.setID(String.valueOf(LastVisitLogID++));
				zv.setIP(v.IP);
				zv.setAddTime(new Date(v.VisitTime));
				zv.setOS(v.OS);
				zv.setBrowser(v.Browser);
				zv.setScreen(v.Screen);
				zv.setDistrict(district);
				zv.setColorDepth(v.ColorDepth);
				zv.setCookieEnabled(String.valueOf(v.CookieEnabled));
				zv.setJavaEnabled(String.valueOf(v.JavaEnabled));
				zv.setFlashVersion(v.FlashVersion);
				zv.setSiteID(siteID);
				zv.setReferer(v.Referer);
				set.add(zv);
			}
		}
		new QueryBuilder("delete from ZCVisitLog where SiteID=?", siteID).executeNoQuery();
		set.insert();
	}

	public static Queuex getLastVisitQueue(long siteID) {
		return ((Queuex) lastVisitMap.get(new Long(siteID)));
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.stat.impl.LeafStat JD-Core Version: 0.5.3
 */