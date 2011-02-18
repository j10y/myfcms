package com.htsoft.oa.action.system;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.FunUrl;
import com.htsoft.oa.service.system.FunUrlService;

public class FunUrlAction extends BaseAction {

	@Resource
	private FunUrlService funUrlService;
	private FunUrl funUrl;
	private Long urlId;

	public Long getUrlId() {
		return this.urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public FunUrl getFunUrl() {
		return this.funUrl;
	}

	public void setFunUrl(FunUrl funUrl) {
		this.funUrl = funUrl;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<FunUrl> list = this.funUrlService.getAll(filter);

		Type type = new TypeToken<List<FunUrl>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.funUrlService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		FunUrl funUrl = (FunUrl) this.funUrlService.get(this.urlId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(funUrl));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.funUrlService.save(this.funUrl);
		setJsonString("{success:true}");
		return "success";
	}
}


 
 
 
 
 