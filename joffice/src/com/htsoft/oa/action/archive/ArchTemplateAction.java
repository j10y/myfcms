package com.htsoft.oa.action.archive;

import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.ArchTemplate;
import com.htsoft.oa.service.archive.ArchTemplateService;

import flexjson.JSONSerializer;

public class ArchTemplateAction extends BaseAction {

	@Resource
	private ArchTemplateService archTemplateService;
	private ArchTemplate archTemplate;
	private Long templateId;

	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public ArchTemplate getArchTemplate() {
		return this.archTemplate;
	}

	public void setArchTemplate(ArchTemplate archTemplate) {
		this.archTemplate = archTemplate;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.archTemplateService.getAll(filter);

		JSONSerializer jsonSerializer = JsonUtil.getJSONSerializer(new String[0]);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		buff.append(jsonSerializer.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archTemplateService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchTemplate archTemplate = (ArchTemplate) this.archTemplateService.get(this.templateId);

		JSONSerializer jsonSerializer = JsonUtil.getJSONSerializer(new String[0]);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(jsonSerializer.serialize(archTemplate));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.archTemplateService.save(this.archTemplate);
		setJsonString("{success:true}");
		return "success";
	}
}
