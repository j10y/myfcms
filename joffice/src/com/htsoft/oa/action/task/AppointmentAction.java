package com.htsoft.oa.action.task;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.task.Appointment;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.task.AppointmentService;

public class AppointmentAction extends BaseAction {

	// @Resource
	@Resource
	private AppointmentService appointmentService;
	private Appointment appointment;

	@Resource
	private AppUserService appUserService;
	private Long appointId;
	private List<Appointment> list;

	public List<Appointment> getList() {
		return this.list;
	}

	public void setList(List<Appointment> list) {
		this.list = list;
	}

	public Long getAppointId() {
		return this.appointId;
	}

	public void setAppointId(Long appointId) {
		this.appointId = appointId;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		List<Appointment> list = this.appointmentService.getAll(filter);

		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.appointmentService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Appointment appointment = (Appointment) this.appointmentService.get(this.appointId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appointment));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.appointment.setAppUser(ContextUtil.getCurrentUser());
		this.appointmentService.save(this.appointment);
		setJsonString("{success:true}");
		return "success";
	}

	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		filter.addSorted("appointId", "desc");
		List list = this.appointmentService.getAll(filter);
		getRequest().setAttribute("appointmentList", list);
		return "display";
	}
}


 
 
 
 
 