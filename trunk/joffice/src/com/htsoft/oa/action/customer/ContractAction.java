package com.htsoft.oa.action.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.customer.Contract;
import com.htsoft.oa.model.customer.ContractConfig;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.customer.ContractConfigService;
import com.htsoft.oa.service.customer.ContractService;
import com.htsoft.oa.service.system.FileAttachService;
import flexjson.JSONSerializer;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class ContractAction extends BaseAction {

	@Resource
	private ContractService contractService;

	@Resource
	private ContractConfigService contractConfigService;

	@Resource
	private FileAttachService fileAttachService;
	private Contract contract;
	private Long contractId;
	private String contractAttachIDs;
	private String data;

	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contactId) {
		this.contractId = contactId;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getContractAttachIDs() {
		return this.contractAttachIDs;
	}

	public void setContractAttachIDs(String contractAttachIDs) {
		this.contractAttachIDs = contractAttachIDs;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.contractService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "signupTime" });
		buff.append(json.exclude(new String[] { "class", "contractConfigs" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.contractService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Contract contract = (Contract) this.contractService.get(this.contractId);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");

		sb.append(gson.toJson(contract));
		sb.append(",projectId:" + contract.getProjectId());
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.contract.getValidDate().getTime() <= this.contract.getExpireDate().getTime())
			pass = true;
		else {
			buff.append("msg:'合同失效日期不能早于生效日期,请重新填写!',");
		}

		if (pass) {
			this.contract.setCreator(ContextUtil.getCurrentUser().getFullname());
			this.contract.setCreatetime(new Date());

			String[] fileIDs = getContractAttachIDs().split(",");
			Set contractAttachs = new HashSet();
			for (String id : fileIDs) {
				if (!id.equals("")) {
					contractAttachs.add((FileAttach) this.fileAttachService.get(new Long(id)));
				}
			}
			this.contract.setContractFiles(contractAttachs);
			this.contractService.save(this.contract);
			if (StringUtils.isNotEmpty(this.data)) {
				Gson gson = new Gson();
				ContractConfig[] contractConfigs = (ContractConfig[]) gson.fromJson(this.data,
						ContractConfig[].class);
				for (ContractConfig contractConfig : contractConfigs) {
					if (contractConfig.getConfigId().longValue() == -1L) {
						contractConfig.setConfigId(null);
						contractConfig.setContractId(this.contract.getContractId());
					}
					this.contractConfigService.save(contractConfig);
				}
			}
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return "success";
	}

	public String removeFile() {
		setContract((Contract) this.contractService.get(this.contractId));
		Set contractFiles = this.contract.getContractFiles();
		FileAttach removeFile = (FileAttach) this.fileAttachService.get(new Long(
				this.contractAttachIDs));
		contractFiles.remove(removeFile);
		this.contract.setContractFiles(contractFiles);
		this.contractService.save(this.contract);
		setJsonString("{success:true}");
		return "success";
	}
}
