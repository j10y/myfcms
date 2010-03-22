package com.zving.cms.dataservice;

import com.zving.framework.Config;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.schedule.GeneralTask;
import com.zving.framework.utility.StringUtil;

public class ADUpdating extends GeneralTask {
	public void execute() {
		DataTable dt = new QueryBuilder("select ID from ZCAdPosition").executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i)
			Advertise.CreateJSCode(dt.getString(i, 0));
	}

	public String getCronExpression() {
		int c = 30;
		String sui = Config.getValue("ADUpdateInterval");
		if (StringUtil.isNotEmpty(sui))
			try {
				c = Integer.parseInt(sui);
				if (c > 60)
					c = 60;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "*/" + c + " * * * *";
	}

	public long getID() {
		return 200911131314L;
	}

	public String getName() {
		return "更新各广告版位广告";
	}

	public static void main(String[] args) {
		ADUpdating ad = new ADUpdating();
		ad.execute();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.ADUpdating JD-Core Version: 0.5.3
 */