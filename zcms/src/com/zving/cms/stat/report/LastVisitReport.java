package com.zving.cms.stat.report;

import com.zving.cms.stat.Visit;
import com.zving.cms.stat.impl.LeafStat;
import com.zving.framework.Page;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.Queuex;
import com.zving.platform.Application;
import java.util.Date;

public class LastVisitReport extends Page {
	public static void dg1DataBind(DataGridAction dga) {
		dga.bindData(getLastVisit(Application.getCurrentSiteID(), 50, 0));
	}

	public static DataTable getLastVisit(long siteID, int pageSize, int pageIndex) {
		Queuex q = LeafStat.getLastVisitQueue(siteID);
		DataTable dt = null;
		if ((q == null) || (q.size() == 0)) {
			dt = new QueryBuilder("select * from ZCVisitLog where SiteID=? order by AddTime desc",
					siteID).executePagedDataTable(pageSize, pageIndex);
		} else {
			Visit[] arr = (Visit[]) null;
			synchronized (q) {
				arr = new Visit[(q.size() > pageSize) ? pageSize : q.size()];
				for (int i = q.size() - 1; i >= q.size() - arr.length; --i) {
					arr[(arr.length - q.size() + i)] = ((Visit) q.get(i));
				}
			}
			dt = new DataTable();
			dt.insertColumn("URL");
			dt.insertColumn("IP");
			dt.insertColumn("OS");
			dt.insertColumn("Browser");
			dt.insertColumn("Screen");
			dt.insertColumn("District");
			dt.insertColumn("AddTime");

			Mapx map = new QueryBuilder(
					"select code,name from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or TreeLevel<3")
					.executeDataTable().toMapx(0, 1);

			for (int i = arr.length - 1; i >= 0; --i) {
				Visit v = arr[i];
				String district = map.getString(v.District);
				if ((!(v.District.startsWith("00"))) && (!(v.District.endsWith("0000")))) {
					String prov = map.getString(v.District.substring(0, 2) + "0000");
					if ((prov.startsWith("黑龙江")) || (prov.startsWith("内蒙古")))
						prov = prov.substring(0, 3);
					else {
						prov = prov.substring(0, 2);
					}
					String city = map.getString(v.District);
					if (city == null) {
						city = map.getString(v.District.substring(0, 4) + "00");
					}
					district = prov + ((city == null) ? "" : city);
				}
				dt.insertRow(new Object[] { v.URL, v.IP, v.OS, v.Browser, v.Screen, district,
						new Date(v.VisitTime) });
			}
			dt.sort("AddTime");
		}
		for (int i = 0; i < dt.getRowCount(); ++i) {
			dt.set(i, "AddTime", DateUtil.toString(dt.getDate(i, "Addtime"), "HH:mm:ss"));
		}
		dt.getDataColumn("AddTime").setColumnType(1);
		return dt;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.stat.report.LastVisitReport JD-Core Version: 0.5.3
 */