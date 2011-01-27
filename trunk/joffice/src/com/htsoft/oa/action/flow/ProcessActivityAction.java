package com.htsoft.oa.action.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.Transition;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.htsoft.core.jbpm.pv.ParamField;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.flow.Transform;
import com.htsoft.oa.service.flow.FormDataService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProcessFormService;
import com.htsoft.oa.service.flow.ProcessRunService;

import flexjson.JSONSerializer;

public class ProcessActivityAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProcessFormService processFormService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private FormDataService formDataService;

	@Resource
	VelocityEngine flowVelocityEngine;
	private String activityName;
	private Long runId;
	private Long taskId;
	private Long defId;

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Long getDefId() {
		return this.defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public String get() throws Exception {
		ProDefinition proDefinition = getProDefinition();
		String processName = proDefinition.getName();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService.getStartNodeName(proDefinition);
		}

		String tempLocation = ProcessActivityAssistant.getFormPath(processName, this.activityName);

		Map model = new HashMap();

		Map formDataMap = null;
		if (this.runId != null) {
			formDataMap = this.formDataService.getFromDataMap(this.runId, this.activityName);
		}

		Map fieldsMap = ProcessActivityAssistant.constructFieldMap(processName, this.activityName);

		Iterator fieldNames = fieldsMap.keySet().iterator();
		while (fieldNames.hasNext()) {
			String fieldName = (String) fieldNames.next();
			if (formDataMap != null) {
				Object fieldVal = formDataMap.get(fieldName);
				model.put(fieldName, fieldVal);
			}
			if (!model.containsKey(fieldName)) {
				model.put(fieldName, "");
			}

		}

		if (this.taskId != null) {
			ProcessRun processRun = this.processRunService.getByTaskId(this.taskId.toString());
			Map processRunVars = this.processFormService.getVariables(processRun.getRunId());

			List<Transition> trans = this.jbpmService
					.getTransitionsByTaskId(this.taskId.toString());

			List allTrans = new ArrayList();
			for (Transition tran : trans) {
				allTrans.add(new Transform(tran));
			}

			model.putAll(processRunVars);

			model.put("nextTrans", allTrans);
		}

		model.put("currentUser", ContextUtil.getCurrentUser());
		model.put("dateTool", new DateTool());
		String formUiJs = "";
		try {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine,
					tempLocation, "UTF-8", model);
		} catch (Exception ex) {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine,
					ProcessActivityAssistant.getCommonFormPath(this.activityName), "UTF-8", model);
		}

		if (StringUtils.isEmpty(formUiJs)) {
			formUiJs = "[]";
		}
		setJsonString(formUiJs);

		return "success";
	}

	public String check() {
		Task task = this.jbpmService.getTaskById(String.valueOf(this.taskId));

		if (task != null) {
			String assignId = task.getAssignee();
			Long curUserId = ContextUtil.getCurrentUserId();

			if (curUserId.toString().equals(assignId)) {
				this.jsonString = "{success:true,isValid:true,msg:''}";
			} else if (StringUtils.isNotEmpty(assignId)) {
				this.jsonString = "{success:true,isValid:false,msg:'该任务已经被其他成员锁定执行！'}";
			} else {
				this.jbpmService.assignTask(task.getId(), curUserId.toString());
				this.jsonString = "{success:true,isValid:true,msg:'该任务已经被您锁定执行!'}";
			}
		} else {
			this.jsonString = "{success:true,isValid:false,msg:'该任务已经完成了'}";
		}

		return "success";
	}

	public String save() {
		FlowRunInfo flowRunInfo = getFlowRunInfo();

		if (this.runId != null) {
			ProcessRun processRun = (ProcessRun) this.processRunService.get(this.runId);
			ProcessForm processForm = this.processFormService.getByRunIdActivityName(this.runId,
					this.activityName);
			if (processForm != null) {
				this.processRunService.saveProcessRun(processRun, processForm, flowRunInfo);
			}
		} else if (this.defId != null) {
			ProcessRun processRun = initNewProcessRun();
			ProcessForm processForm = initNewProcessForm(processRun);
			this.processRunService.saveProcessRun(processRun, processForm, flowRunInfo);
		}

		setJsonString("{success:true}");
		return "success";
	}

	protected ProcessRun initNewProcessRun() {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService.get(this.defId);

		return this.processRunService.initNewProcessRun(proDefinition);
	}

	protected ProcessForm initNewProcessForm(ProcessRun processRun) {
		ProcessForm processForm = new ProcessForm();
		processForm.setActivityName(this.activityName);
		processForm.setProcessRun(processRun);

		return processForm;
	}

	public String next() {
		FlowRunInfo flowRunInfo = getFlowRunInfo();

		this.processRunService.saveAndNextStep(flowRunInfo);

		setJsonString("{success:true}");

		return "success";
	}

	public String freeTrans() {
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		List<Transition> trans = this.jbpmService
				.getFreeTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			sb.append("[").append(gson.toJson(tran.getName())).append(",").append(
					gson.toJson(tran.getDestination().getName())).append("],");
		}

		if (trans.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");

		setJsonString(sb.toString());

		return "success";
	}

	public String trans() {
		List allTrans = new ArrayList();

		List<Transition> trans = this.jbpmService.getTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			allTrans.add(new Transform(tran));
		}

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		String result = serializer.serialize(allTrans);

		setJsonString("{success:true,data:" + result + "}");
		return "success";
	}

	protected Map<String, ParamField> constructFieldMap() {
		HttpServletRequest request = getRequest();
		ProDefinition proDefinition = getProDefinition();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService.getStartNodeName(proDefinition);
		}

		Map map = ProcessActivityAssistant.constructFieldMap(proDefinition.getName(),
				this.activityName);

		Iterator fieldNames = map.keySet().iterator();
		while (fieldNames.hasNext()) {
			String name = (String) fieldNames.next();
			ParamField pf = (ParamField) map.get(name);

			pf.setName(pf.getName().replace(".", "_"));
			pf.setValue(request.getParameter(name));
		}
		return map;
	}

	protected ProDefinition getProDefinition() {
		ProDefinition proDefinition = null;
		if (this.runId != null) {
			ProcessRun processRun = (ProcessRun) this.processRunService.get(this.runId);
			proDefinition = processRun.getProDefinition();
		} else if (this.defId != null) {
			proDefinition = (ProDefinition) this.proDefinitionService.get(this.defId);
		} else {
			ProcessRun processRun = this.processRunService.getByTaskId(this.taskId.toString());
			proDefinition = processRun.getProDefinition();
		}
		return proDefinition;
	}

	protected FlowRunInfo getFlowRunInfo() {
		FlowRunInfo info = new FlowRunInfo(getRequest());
		Map fieldMap = constructFieldMap();
		info.setParamFields(fieldMap);
		return info;
	}
}
