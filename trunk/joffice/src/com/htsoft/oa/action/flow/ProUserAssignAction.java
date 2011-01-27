package com.htsoft.oa.action.flow;

import com.google.gson.Gson;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProUserAssignService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class ProUserAssignAction extends BaseAction {

	@Resource
	private ProUserAssignService proUserAssignService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private ProDefinitionService proDefinitionService;
	private ProUserAssign proUserAssign;
	private Long assignId;

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public Long getAssignId() {
		return this.assignId;
	}

	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}

	public ProUserAssign getProUserAssign() {
		return this.proUserAssign;
	}

	public void setProUserAssign(ProUserAssign proUserAssign) {
		this.proUserAssign = proUserAssign;
	}

	public String list() {
		String defId = getRequest().getParameter("defId");

		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService
				.get(new Long(defId));

		List nodes = this.jbpmService.getTaskNodesByDefId(new Long(defId));

		List nodesAssignList = this.proUserAssignService.getByDeployId(proDefinition.getDeployId());

		StringBuffer buff = new StringBuffer("{result:[");

		for (int i = 0; i < nodes.size(); ++i) {
			String nodeName = ((Node) nodes.get(i)).getName();
			buff.append("{activityName:'").append(nodeName).append(
					"',deployId:'" + proDefinition.getDeployId()).append("'");
			for (int j = 0; j < nodesAssignList.size(); ++j) {
				ProUserAssign assign = (ProUserAssign) nodesAssignList.get(j);
				if (!nodeName.equals(assign.getActivityName()))
					continue;
				buff.append(",assignId:'").append(assign.getAssignId()).append("',userId:'")
						.append(assign.getUserId()).append("',username:'").append(
								assign.getUsername()).append("',roleId:'").append(
								assign.getRoleId()).append("',roleName:'").append(
								assign.getRoleName()).append("'");
				break;
			}

			buff.append("},");
		}

		if (!nodes.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}

		buff.append("]}");

		setJsonString(buff.toString());

		return "success";
	}

	public String get() {
		ProUserAssign proUserAssign = (ProUserAssign) this.proUserAssignService.get(this.assignId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proUserAssign));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String data = getRequest().getParameter("data");

		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			ProUserAssign[] assigns = (ProUserAssign[]) gson.fromJson(data, ProUserAssign[].class);
			for (ProUserAssign assign : assigns) {
				if (assign.getAssignId().longValue() == -1L) {
					assign.setAssignId(null);
				}
				this.proUserAssignService.save(assign);
			}
		}

		return "success";
	}
}

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.action.flow.ProUserAssignAction JD-Core
 * Version: 0.5.4
 */