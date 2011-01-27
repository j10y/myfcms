package com.htsoft.oa.action.archive;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.ArchHasten;
import com.htsoft.oa.service.archive.ArchHastenService;

public class ArchHastenAction extends BaseAction {

	@Resource
	private ArchHastenService archHastenService;
	private ArchHasten archHasten;
	private Long record;

	public Long getRecord() {
		return this.record;
	}

	public void setRecord(Long record) {
		this.record = record;
	}

	public ArchHasten getArchHasten() {
		return this.archHasten;
	}

	public void setArchHasten(ArchHasten archHasten) {
		this.archHasten = archHasten;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.archHastenService.getAll(filter);

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
				this.archHastenService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchHasten archHasten = (ArchHasten) this.archHastenService.get(this.record);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archHasten));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.archHastenService.save(this.archHasten);
		setJsonString("{success:true}");
		return "success";
	}
}
