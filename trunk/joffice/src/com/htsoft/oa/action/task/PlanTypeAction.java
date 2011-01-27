package com.htsoft.oa.action.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.PlanType;
import com.htsoft.oa.service.task.PlanTypeService;
import com.htsoft.oa.service.task.WorkPlanService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class PlanTypeAction extends BaseAction {

	@Resource
	private PlanTypeService planTypeService;
	private PlanType planType;

	@Resource
	private WorkPlanService workPlanService;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public PlanType getPlanType() {
		return this.planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public String combo() {
		StringBuffer sb = new StringBuffer();

		List<PlanType> planTypeList = this.planTypeService.getAll();
		sb.append("[");
		for (PlanType planType : planTypeList) {
			sb.append("['").append(planType.getTypeId()).append("','").append(
					planType.getTypeName()).append("'],");
		}
		if (planTypeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return "success";
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.planTypeService.getAll(filter);

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
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_planType.typeId_L_EQ", id);
				List list = this.workPlanService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'类型下还有计划，请移走该类型的计划任务后，再删除类型！'}";
					return "success";
				}
				this.planTypeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		PlanType planType = (PlanType) this.planTypeService.get(this.typeId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(planType));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.planTypeService.save(this.planType);
		setJsonString("{success:true}");
		return "success";
	}
}

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.action.task.PlanTypeAction JD-Core Version:
 * 0.5.4
 */