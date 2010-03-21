package com.zving.cms.datachannel;

import com.zving.framework.data.QueryBuilder;
import com.zving.framework.schedule.GeneralTask;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCArticleSet;
import org.apache.commons.logging.Log;

public class PublishTask extends GeneralTask {
	public void execute() {
		Publisher p = new Publisher();

		ZCArticleSchema article = new ZCArticleSchema();
		ZCArticleSet set = article.query(new QueryBuilder(
				" where publishdate<=? and publishdate is not null and status in (20,0)", DateUtil
						.getCurrentDateTime()));

		if ((set != null) && (set.size() > 0)) {
			LogUtil.getLogger().info("需发布文章篇数：" + set.size());
			p.publishArticle(set);
		}

		set = article.query(new QueryBuilder("where downlinedate<=? and status=?", DateUtil
				.getCurrentDateTime(), 30));
		if ((set != null) && (set.size() > 0)) {
			LogUtil.getLogger().info("需下线文章篇数：" + set.size());

			Mapx catalogMap = new Mapx();
			for (int i = 0; i < set.size(); ++i) {
				set.get(i).setStatus(40L);
				set.get(i).setTopFlag("0");
				catalogMap.put(String.valueOf(set.get(i).getCatalogID()), String.valueOf(set.get(i)
						.getCatalogID()));
			}

			if (set.update()) {
				p.deletePubishedFile(set);
				Object[] vs = catalogMap.valueArray();
				for (int i = 0; i < catalogMap.size(); ++i)
					p.publishCatalog(Long.parseLong(vs[i].toString()), false, false);
			}
		}
	}

	public String getCronExpression() {
		return "* * * * *";
	}

	public long getID() {
		return 200904241314L;
	}

	public String getName() {
		return "发布指定日期的文章";
	}

	public static void main(String[] args) {
		PublishTask p = new PublishTask();
		p.execute();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.datachannel.PublishTask JD-Core Version: 0.5.3
 */