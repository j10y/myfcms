package com.htsoft.oa.action.info;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.AppTips;
import com.htsoft.oa.service.info.AppTipsService;

public class AppTipsAction extends BaseAction {

	@Resource
	private AppTipsService appTipsService;
	private AppTips appTips;
	private Long tipsId;

	public Long getTipsId() {
		return this.tipsId;
	}

	public void setTipsId(Long tipsId) {
		this.tipsId = tipsId;
	}

	public AppTips getAppTips() {
		return this.appTips;
	}

	public void setAppTips(AppTips appTips) {
		this.appTips = appTips;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		List<AppTips> list = this.appTipsService.getAll(filter);

		Type type = new TypeToken<List<AppTips>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		if (getRequest().getParameter("ids").equals("all")) {
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
			List list = appTipsService.getAll(filter);
			AppTips tips;
			for (Iterator iterator = list.iterator(); iterator.hasNext(); appTipsService
					.remove(tips))
				tips = (AppTips) iterator.next();

		} else {
			String ids[] = getRequest().getParameterValues("ids");
			if (ids != null) {
				String as[];
				int j = (as = ids).length;
				for (int i = 0; i < j; i++) {
					String id = as[i];
					appTipsService.remove(new Long(id));
				}

			}
		}
		jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		AppTips appTips = (AppTips) this.appTipsService.get(this.tipsId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appTips));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String data = getRequest().getParameter("data");
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			AppTips[] tips = (AppTips[]) gson.fromJson(data, AppTips[].class);
			for (AppTips tip : tips) {
				if (tip.getTipsId().longValue() == -1L) {
					tip.setTipsId(null);
					SimpleDateFormat date = new SimpleDateFormat("yyMMddHHmmssSSS");
					String customerNo = date.format(new Date());
					tip.setTipsName("tips" + customerNo);
					tip.setCreateTime(new Date());
				}
				tip.setAppUser(ContextUtil.getCurrentUser());
				this.appTipsService.save(tip);
			}
		}

		setJsonString("{success:true}");
		return "success";
	}
}
