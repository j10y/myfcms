package com.zving.cms.stat.impl;

import com.zving.cms.stat.AbstractStat;
import com.zving.cms.stat.Visit;
import com.zving.cms.stat.VisitCount;
import com.zving.framework.utility.StringUtil;

public class CatalogStat extends AbstractStat {
	private static final String[] avgSubTypes = { "StickTime" };
	private static final String Type = "Catalog";

	public String getStatType() {
		return "Catalog";
	}

	public String[] getAverageSubTypes() {
		return avgSubTypes;
	}

	public void deal(Visit v) {
		if ((StringUtil.isEmpty(v.Type)) || ("AD".equals(v.Type)) || ("Other".equals(v.Type))) {
			return;
		}
		String code = v.CatalogInnerCode;
		if ((code == null) || (code.length() % 6 != 0)) {
			code = "";
		}
		if (v.LeafID == 0L) {
			if ("Unload".equals(v.Event))
				VisitCount.getInstance().addAverage(v.SiteID, "Catalog", "StickTime",
						code + "Index", v.StickTime);
			else {
				VisitCount.getInstance().add(v.SiteID, "Catalog", "PV", code + "Index");
			}
		}
		String[] codes = new String[code.length() / 6];
		for (int i = 0; i < codes.length; ++i) {
			codes[i] = code.substring(0, i * 6 + 6);
		}
		for (int i = 0; i < codes.length; ++i) {
			code = codes[i];
			if ("Unload".equals(v.Event))
				VisitCount.getInstance().addAverage(v.SiteID, "Catalog", "StickTime", code,
						v.StickTime);
			else {
				VisitCount.getInstance().add(v.SiteID, "Catalog", "PV", code);
			}
			code = code.substring(0, code.length() - 6);
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.stat.impl.CatalogStat JD-Core Version: 0.5.3
 */