package com.zving.cms.site;

import com.zving.cms.document.Article;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.schedule.CronMonitor;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCCatalogConfigSchema;
import com.zving.schema.ZCCatalogConfigSet;
import com.zving.schema.ZDScheduleSchema;
import com.zving.schema.ZDScheduleSet;
import java.util.Calendar;
import java.util.Date;

public class CatalogConfig extends Page {
	public static Mapx init(Mapx params) {
		Mapx AllowStatus = (Mapx) Article.STATUS_MAP.clone();
		AllowStatus.remove("10");
		AllowStatus.remove("40");
		AllowStatus.remove("50");

		Mapx AfterEditStatus = (Mapx) Article.STATUS_MAP.clone();
		AfterEditStatus.remove("40");
		AfterEditStatus.remove("50");
		String CatalogID = params.getString("CatalogID");
		if ((StringUtil.isNotEmpty(CatalogID))
				&& (StringUtil.isEmpty(CatalogUtil.getWorkflow(CatalogID)))) {
			AllowStatus.remove("10");
			AllowStatus.remove("20");
			AfterEditStatus.remove("10");
			AfterEditStatus.remove("20");
		}
		String CatalogType = params.getString("Type");
		ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
		String SiteID = String.valueOf(Application.getCurrentSiteID());
		params.put("SiteID", SiteID);
		if (StringUtil.isNotEmpty(CatalogID)) {
			config = new ZCCatalogConfigSchema().query(
					new QueryBuilder("where CatalogID = " + CatalogID)).get(0);
			params.put("catalogdisplay", "");
			params.put("sitedisplay", "none");
		} else {
			config = new ZCCatalogConfigSchema().query(
					new QueryBuilder("where CatalogID is null and SiteID = " + SiteID)).get(0);
			params.put("catalogdisplay", "none");
			params.put("sitedisplay", "");
		}
		if (config == null) {
			params.put("AllowStatusOptions", HtmlUtil.mapxToCheckboxes("AllowStatus", AllowStatus,
					new String[] { "30", "0" }));
			params.put("AfterEditStatusOptions", HtmlUtil.mapxToOptions(AfterEditStatus, "30"));
			params.put("PeriodCheck", "checked");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int minute = c.get(12);
			int hour = c.get(11);
			int day = c.get(5);
			params.put("CronExpression", minute + " " + hour + " " + day + "-" + (day - 1) + "/1"
					+ " * *");
			params.put("ArchiveTimeOptions", HtmlUtil.codeToOptions("ArchiveTime", "12"));
			params.put("IsUsingNCheck", "checked");
			params.put("KeyWordNCheck", "checked");
			params.put("AttachDownFlagRadios", HtmlUtil.codeToRadios("AttachDownFlag", "YesOrNo",
					"Y"));
		} else {
			String Time = DateUtil.toString(config.getStartTime(), "yyyy-MM-dd HH:mm:ss");
			params.putAll(config.toMapx());
			params.put("StartDate", Time.substring(0, 10));
			params.put("StartTime", Time.substring(11));
			if (config.getPlanType().equalsIgnoreCase("Period"))
				params.put("PeriodCheck", "checked");
			else {
				params.put("CronCheck", "checked");
			}
			if (StringUtil.isNotEmpty(config.getAllowStatus()))
				params.put("AllowStatusOptions", HtmlUtil.mapxToCheckboxes("AllowStatus",
						AllowStatus, config.getAllowStatus().split(",")));
			else {
				params.put("AllowStatusOptions", HtmlUtil.mapxToCheckboxes("AllowStatus",
						AllowStatus));
			}
			params.put("AfterEditStatusOptions", HtmlUtil.mapxToOptions(AfterEditStatus, config
					.getAfterEditStatus()));
			params.put("ArchiveTimeOptions", HtmlUtil.codeToOptions("ArchiveTime", config
					.getArchiveTime()));
			if (config.getIsUsing().equalsIgnoreCase("Y"))
				params.put("IsUsingYCheck", "checked");
			else {
				params.put("IsUsingNCheck", "checked");
			}
			if ("Y".equalsIgnoreCase(config.getHotWordFlag()))
				params.put("KeyWordYCheck", "checked");
			else if ("N".equalsIgnoreCase(config.getHotWordFlag()))
				params.put("KeyWordNCheck", "checked");
			else {
				params.put("KeyWordSCheck", "checked");
			}

			params.put("AttachDownFlagRadios", HtmlUtil.codeToRadios("AttachDownFlag", "YesOrNo",
					config.getAttachDownFlag()));
		}

		if ((StringUtil.isNotEmpty(CatalogType)) && (!(CatalogType.equals("1")))) {
			params.put("display", "none");
		}

		return params;
	}

	public void save() {
		String ID = $V("ID");
		String StartDate = $V("StartDate");
		String StartTime = $V("StartTime");
		String Period = $V("Period");
		Date Time = new Date();
		if (StringUtil.isNotEmpty(StartDate)) {
			if (StringUtil.isNotEmpty(StartTime))
				Time = DateUtil.parseDateTime(StartDate + " " + StartTime);
			else {
				Time = DateUtil.parseDateTime(StartDate + " " + "00:00:00");
			}
		}

		ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
		boolean exists = false;
		if (StringUtil.isEmpty(ID)) {
			config.setID(NoUtil.getMaxID("PublishConfigID"));
			config.setAddTime(new Date());
			config.setAddUser(User.getUserName());
		} else {
			exists = true;
			config.setID(ID);
			config.fill();
			config.setModifyTime(new Date());
			config.setModifyUser(User.getUserName());
		}
		config.setValue(this.Request);
		config.setCatalogInnerCode(CatalogUtil.getInnerCode(config.getCatalogID()));
		config.setStartTime(Time);

		Calendar c = Calendar.getInstance();
		c.setTime(Time);
		StringBuffer sb = new StringBuffer();
		if (config.getPlanType().equalsIgnoreCase("Period")) {
			int minute;
			if ($V("PeriodType").equalsIgnoreCase("Minute")) {
				minute = c.get(12);
				sb.append(minute);
				sb.append("-");
				if (minute == 0)
					sb.append("59");
				else {
					sb.append(minute - 1);
				}
				sb.append("/");
				sb.append(Period);
				sb.append(" * * * *");
			} else {
				int hour;
				if ($V("PeriodType").equalsIgnoreCase("Hour")) {
					minute = c.get(12);
					hour = c.get(11);
					sb.append(minute);
					sb.append(" ");
					sb.append(hour);
					sb.append("-");
					if (hour == 0)
						sb.append("23");
					else {
						sb.append(hour - 1);
					}
					sb.append("/");
					sb.append(Period);
					sb.append(" * * *");
				} else {
					int day;
					if ($V("PeriodType").equalsIgnoreCase("Day")) {
						minute = c.get(12);
						hour = c.get(11);
						day = c.get(5);
						sb.append(minute);
						sb.append(" ");
						sb.append(hour);
						sb.append(" ");
						sb.append(day);
						sb.append("-");
						sb.append(day - 1);
						sb.append("/");
						sb.append(Period);
						sb.append(" * *");
					} else if ($V("PeriodType").equalsIgnoreCase("Month")) {
						minute = c.get(12);
						hour = c.get(11);
						day = c.get(5);
						int month = c.get(2);
						sb.append(minute);
						sb.append(" ");
						sb.append(hour);
						sb.append(" ");
						sb.append(day);
						sb.append(" ");
						sb.append(month);
						sb.append("-");
						sb.append(month - 1);
						sb.append("/");
						sb.append(Period);
						sb.append(" *");
					}
				}
			}
			config.setCronExpression(sb.toString());
		}

		Transaction trans = new Transaction();
		if (exists)
			trans.add(config, 2);
		else {
			trans.add(config, 1);
		}

		if (StringUtil.isNotEmpty(String.valueOf(config.getCatalogID()))) {
			if ("2".equalsIgnoreCase("CatalogStatusExtend"))
				trans.add(new QueryBuilder(
						"update zccatalogconfig set AllowStatus=? where cataloginnercode like ?",
						config.getAllowStatus(), config.getCatalogInnerCode() + "%"));
			else if ("3".equalsIgnoreCase("CatalogStatusExtend")) {
				trans
						.add(new QueryBuilder(
								"update zccatalogconfig set AllowStatus=? where siteID=? and catalogID is not null",
								config.getAllowStatus(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("CatalogEditExtend"))
				trans
						.add(new QueryBuilder(
								"update zccatalogconfig set AfterEditStatus=? where cataloginnercode like ?",
								config.getAfterEditStatus(), config.getCatalogInnerCode() + "%"));
			else if ("3".equalsIgnoreCase("CatalogEditExtend")) {
				trans
						.add(new QueryBuilder(
								"update zccatalogconfig set AfterEditStatus=? where siteID=? and catalogID is not null",
								config.getAfterEditStatus(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("CatalogArchiveExtend"))
				trans.add(new QueryBuilder(
						"update zccatalogconfig set ArchiveTime=? where cataloginnercode like ?",
						config.getArchiveTime(), config.getCatalogInnerCode() + "%"));
			else if ("3".equalsIgnoreCase("CatalogArchiveExtend")) {
				trans
						.add(new QueryBuilder(
								"update zccatalogconfig set ArchiveTime=? where siteID=? and catalogID is not null",
								config.getArchiveTime(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("CatalogHotWordExtend"))
				trans.add(new QueryBuilder(
						"update zccatalogconfig set HotWordFlag=? where cataloginnercode like ?",
						config.getAllowStatus(), config.getCatalogInnerCode() + "%"));
			else if ("3".equalsIgnoreCase("CatalogHotWordExtend"))
				trans
						.add(new QueryBuilder(
								"update zccatalogconfig set HotWordFlag=? where siteID=? and catalogID is not null",
								config.getAllowStatus(), config.getSiteID()));
		} else {
			if ("2".equalsIgnoreCase("SiteStatusExtend")) {
				trans.add(new QueryBuilder(
						"update zccatalogconfig set AllowStatus=? where siteID=?", config
								.getAllowStatus(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("SiteEditExtend")) {
				trans.add(new QueryBuilder(
						"update zccatalogconfig set AfterEditStatus=? where siteID=?", config
								.getAllowStatus(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("SiteArchiveExtend")) {
				trans.add(new QueryBuilder(
						"update zccatalogconfig set ArchiveTime=? where siteID=?", config
								.getAllowStatus(), config.getSiteID()));
			}

			if ("2".equalsIgnoreCase("SiteHotWordExtend")) {
				trans.add(new QueryBuilder(
						"update zccatalogconfig set HotWordFlag=? where siteID=?", config
								.getAllowStatus(), config.getSiteID()));
			}
		}

		ZDScheduleSchema schedule = new ZDScheduleSchema();
		boolean scheduleExists = false;
		if (exists)
			schedule = new ZDScheduleSchema().query(
					new QueryBuilder("where Prop1='Config' and SourceID = " + config.getID())).get(
					0);
		else {
			schedule = null;
		}
		if (schedule == null) {
			schedule = new ZDScheduleSchema();
			schedule.setID(NoUtil.getMaxID("ScheduleID"));
			schedule.setSourceID(config.getID());
			schedule.setTypeCode("Publisher");
			schedule.setProp1("Config");
			schedule.setAddTime(new Date());
			schedule.setAddUser(User.getUserName());
		} else {
			scheduleExists = true;
			schedule.setModifyTime(new Date());
			schedule.setModifyUser(User.getUserName());
		}
		schedule.setCronExpression(config.getCronExpression());
		schedule.setPlanType(config.getPlanType());
		schedule.setStartTime(config.getStartTime());
		schedule.setIsUsing(config.getIsUsing());

		if (scheduleExists)
			trans.add(schedule, 2);
		else {
			trans.add(schedule, 1);
		}
		try {
			if (trans.commit()) {
				CronMonitor.getNextRunTime(schedule.getCronExpression());

				SiteUtil.update(config.getSiteID());

				if (StringUtil.isNotEmpty(String.valueOf(config.getCatalogID()))) {
					CatalogUtil.update(config.getCatalogID());
				}

				this.Response.setLogInfo(1, "保存成功");
				return;
			}
			this.Response.setLogInfo(0, "发生错误");
		} catch (Exception e) {
			this.Response.setError("发生错误:Cron表达式不正确!");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.site.CatalogConfig JD-Core Version: 0.5.3
 */