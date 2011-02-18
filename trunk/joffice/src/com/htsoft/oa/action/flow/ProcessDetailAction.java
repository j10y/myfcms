package com.htsoft.oa.action.flow;

import javax.annotation.Resource;

import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.service.flow.ProDefinitionService;

public class ProcessDetailAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;
	private ProDefinition proDefinition;

	public ProDefinition getProDefinition() {
		return this.proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	public String execute() throws Exception {
		String defId = getRequest().getParameter("defId");
		this.proDefinition = ((ProDefinition) this.proDefinitionService.get(new Long(defId)));
		return "success";
	}
}
