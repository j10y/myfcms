package com.htsoft.oa.action.customer;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.customer.ContractConfig;
import com.htsoft.oa.service.customer.ContractConfigService;

public class ContractConfigAction extends BaseAction {

	@Resource
	private ContractConfigService contractConfigService;
	private ContractConfig contractConfig;
	private Long configId;

	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public ContractConfig getContractConfig() {
		return this.contractConfig;
	}

	public void setContractConfig(ContractConfig contractConfig) {
		this.contractConfig = contractConfig;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.contractConfigService.getAll(filter);

		Type type = new TypeToken() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.contractConfigService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ContractConfig contractConfig = (ContractConfig) this.contractConfigService
				.get(this.configId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(contractConfig));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.contractConfigService.save(this.contractConfig);
		setJsonString("{success:true}");
		return "success";
	}
}
