package com.htsoft.oa.action.flow;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.FormData;
import com.htsoft.oa.service.flow.FormDataService;

public class FormDataAction extends BaseAction {

	@Resource
	private FormDataService formDataService;
	private FormData formData;
	private Long dataId;

	public Long getDataId() {
		return this.dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public FormData getFormData() {
		return this.formData;
	}

	public void setFormData(FormData formData) {
		this.formData = formData;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.formDataService.getAll(filter);

		Type type = new TypeToken() {
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
				this.formDataService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		FormData formData = (FormData) this.formDataService.get(this.dataId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formData));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.formDataService.save(this.formData);
		setJsonString("{success:true}");
		return "success";
	}
}
