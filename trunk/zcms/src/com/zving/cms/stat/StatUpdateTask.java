package com.zving.cms.stat;

import com.zving.framework.Config;
import com.zving.framework.schedule.GeneralTask;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.StringUtil;
import java.util.Date;

public class StatUpdateTask extends GeneralTask {
	private long LastUpdateTime = System.currentTimeMillis();

	public void execute() {
		long current = System.currentTimeMillis();
		VisitHandler.init(current);
		if (!(DateUtil.toString(new Date(current)).equals(DateUtil.toString(new Date(
				this.LastUpdateTime)))))
			VisitHandler.changePeriod(1, current);
		else {
			VisitHandler.update(System.currentTimeMillis(), false, false);
		}
		this.LastUpdateTime = current;
	}

	public String getCronExpression() {
		int c = 5;
		String sui = Config.getValue("StatUpdateInterval");
		if (StringUtil.isNotEmpty(sui))
			try {
				c = Integer.parseInt(sui);
				if (c > 15)
					c = 15;
			} catch (Exception exception) {
			}
		return "*/" + c + " * * * *";
	}

	public long getID() {
		return 200812191853L;
	}

	public String getName() {
		return "定时更新CMS统计信息";
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.stat.StatUpdateTask JD-Core Version: 0.5.3
 */