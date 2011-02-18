package com.htsoft.oa.action.flow;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.Constants;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ExtFormItem;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.service.flow.FormDefService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;

public class FormDefAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private FormDefService formDefService;
	private FormDef formDef;
	private Long formDefId;
	private String defId;

	public String getDefId() {
		return this.defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public Long getFormDefId() {
		return this.formDefId;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	public FormDef getFormDef() {
		return this.formDef;
	}

	public void setFormDef(FormDef formDef) {
		this.formDef = formDef;
	}

	public String nodes() {
		List<Node> nodes = this.jbpmService.getTaskNodesByDefId(new Long(this.defId));

		StringBuffer sb = new StringBuffer("{data:['");

		for (Node node : nodes) {
			sb.append("'").append(node.getName()).append("',");
		}
		sb.append("]}");
		setJsonString(sb.toString());
		return "success";
	}

	public String addAll() {
		List<Node> nodes = this.jbpmService.getFormNodes(new Long(this.defId));
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService.get(new Long(
				this.defId));

		for (Node node : nodes) {
			FormDef formDef = this.formDefService.getByDeployIdActivityName(proDefinition
					.getDeployId(), node.getName());
			if (formDef == null) {
				formDef = new FormDef();
				formDef.setActivityName(node.getName());
				formDef.setColumns(FormDef.DEFAULT_COLUMNS);
				formDef.setFormName(node.getName() + "-表单");
				formDef.setIsEnabled(Constants.ENABLED);
				formDef.setDeployId(proDefinition.getDeployId());
				this.formDefService.save(formDef);
			}
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String select() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<FormDef> list = this.formDefService.getAll(filter);

		Type type = new TypeToken<List<FormDef>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String list() {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService.get(new Long(
				this.defId));

		List nodes = this.jbpmService.getFormNodes(new Long(this.defId));

		List<FormDef> formDefs = this.formDefService.getByDeployId(proDefinition.getDeployId());

		StringBuffer buff = new StringBuffer("{result:[");

		for (int i = 0; i < nodes.size(); ++i) {
			String nodeName = ((Node) nodes.get(i)).getName();
			buff.append("{activityName:'").append(nodeName).append(
					"',deployId:'" + proDefinition.getDeployId()).append("'");

			for (FormDef def : formDefs) {
				if (nodeName.equals(def.getActivityName())) {
					buff.append(",formDefId:'").append(def.getFormDefId()).append("',formName:'")
							.append(def.getFormName()).append("'");
					break;
				}
			}
			buff.append("},");
		}

		if (nodes.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}

		buff.append("]}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.formDefService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		FormDef formDef = (FormDef) this.formDefService.get(this.formDefId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formDef));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String activityName = getRequest().getParameter("activityName");
		FormDef formDef = (FormDef) this.formDefService.get(this.formDefId);

		String extDef = getRequest().getParameter("extDef");
		formDef.setExtDef(extDef);
		this.formDefService.save(formDef);

		String extFormDef = getRequest().getParameter("extFormDef");
		String formItemDef = getRequest().getParameter("formItemDef");

		this.logger.info("extFormDef:" + extFormDef);
		this.logger.info("formItemDef:" + formItemDef);

		ProDefinition proDefinition = this.proDefinitionService
				.getByDeployId(formDef.getDeployId());
		String formPath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName();

		File flowDirPath = new File(formPath);
		if (!flowDirPath.exists()) {
			flowDirPath.mkdirs();
		}
		Gson gson = new Gson();

		ExtFormItem[] formItems = (ExtFormItem[]) gson.fromJson("[" + formItemDef + "]",
				ExtFormItem[].class);
		StringBuffer xmlBuf = new StringBuffer();

		if (formItems != null) {
			xmlBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xmlBuf.append("<fields>\n");
			for (ExtFormItem item : formItems) {
				xmlBuf.append("\t<field name=\"" + item.getName() + "\" label=\""
						+ item.getFieldLabel() + "\" type=\"" + item.getType() + "\" length=\""
						+ item.getMaxLength() + "\" isShowed=\"" + item.getIsShowed() + "\"/>\n");
			}
			xmlBuf.append("</fields>\n");
		}

		if (xmlBuf.length() > 0) {
			String fieldFilePath = formPath + "/" + activityName + "-fields.xml";
			FileUtil.writeFile(fieldFilePath, xmlBuf.toString());
		}

		if (proDefinition != null) {
			String extFilePath = formPath + "/" + activityName + ".vm";
			FileUtil.writeFile(extFilePath, extFormDef);
		}
		return "success";
	}

	public String saveVmXml() {
		String deployId = getRequest().getParameter("deployId");
		String activityName = getRequest().getParameter("activityName");

		String vmSources = getRequest().getParameter("vmSources");

		String xmlSources = getRequest().getParameter("xmlSources");

		ProDefinition proDefinition = this.proDefinitionService.getByDeployId(deployId);
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName() + "/" + activityName;

		String vmFilePath = filePath + ".vm";
		String xmlFilePath = filePath + "-fields.xml";

		FileUtil.writeFile(vmFilePath, vmSources);

		FileUtil.writeFile(xmlFilePath, xmlSources);

		setJsonString("{success:true}");

		return "success";
	}

	public String getVmXml() {
		String deployId = getRequest().getParameter("deployId");
		String activityName = getRequest().getParameter("activityName");

		ProDefinition proDefinition = this.proDefinitionService.getByDeployId(deployId);
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName() + "/" + activityName;

		String vmFilePath = filePath + ".vm";
		String xmlFilePath = filePath + "-fields.xml";

		String vmSources = FileUtil.readFile(vmFilePath);
		String xmlSources = FileUtil.readFile(xmlFilePath);
		Gson gson = new Gson();

		setJsonString("{success:true,vmSources:" + gson.toJson(vmSources) + ",xmlSources:"
				+ gson.toJson(xmlSources) + "}");

		return "success";
	}
}
