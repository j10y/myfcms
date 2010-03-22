package com.zving.cms.stat.impl;

import com.zving.cms.stat.VisitCount;
import com.zving.cms.stat.VisitCount.ItemValue;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.ExitEventListener;

public class LeafExitEventListener extends ExitEventListener {
	protected String type;
	protected String subType;
	protected Object[][] arr = new Object[50][2];

	protected int index = 0;

	public LeafExitEventListener(String type, String subType) {
		this.type = type;
		this.subType = subType;
	}

	public void onExit(Object key, Object value) {
		synchronized (this) {
			this.arr[this.index][0] = key;
			this.arr[this.index][1] = value;
			if (this.index == 49) {
				update();
				this.index = 0;
			} else {
				this.index += 1;
			}
		}
	}

	public void update() {
		QueryBuilder qb = null;
		if (this.subType.equals("PV"))
			qb = new QueryBuilder("update ZC" + this.type + " set HitCount=HitCount+? where ID=?");
		else {
			qb = new QueryBuilder("update ZC" + this.type
					+ " set StickTime=(HitCount*StickTime+?)/(HitCount+?) where ID=?");
		}
		qb.setBatchMode(true);
		for (int i = 0; i < this.arr.length; ++i) {
			VisitCount.ItemValue v = (VisitCount.ItemValue) this.arr[i][1];
			if (this.subType.equals("PV")) {
				qb.add(v.Count);
				qb.add(this.arr[i][0]);
			} else {
				qb.add(v.Count);
				qb.add(v.Divisor);
				qb.add(this.arr[i][0]);
			}
			qb.addBatch();
		}
		qb.executeNoQuery();
	}
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.impl.LeafExitEventListener
 * JD-Core Version:    0.5.3
 */