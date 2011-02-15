package com.htsoft.oa.action.hrm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.Job;
import com.htsoft.oa.service.hrm.JobService;

import flexjson.JSONSerializer;

public class JobAction extends BaseAction {

	@Resource
	private JobService jobService;
	private Job job;
	private Long jobId;

	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.jobService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer serializer = new JSONSerializer();
		buff.append(serializer.exclude(new String[] { "class", "department.appUser" }).serialize(
				list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				Job removeJob = (Job) this.jobService.get(new Long(id));
				removeJob.setDelFlag(Short.valueOf(Job.DELFLAG_HAD));
				this.jobService.save(removeJob);
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Job job = (Job) this.jobService.get(this.jobId);

		JSONSerializer json = new JSONSerializer();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(json.exclude(new String[] { "class" }).serialize(job));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		this.job.setDelFlag(Short.valueOf(Job.DELFLAG_NOT));
		this.jobService.save(this.job);
		setJsonString("{success:true}");
		return "success";
	}

	public String combo() {
		String strDepId = getRequest().getParameter("depId");
		if (StringUtils.isNotEmpty(strDepId)) {
			List<Job> list = this.jobService.findByDep(new Long(strDepId));
			StringBuffer sb = new StringBuffer("[");
			for (Job job : list) {
				sb.append("['").append(job.getJobId()).append("','").append(job.getJobName())
						.append("'],");
			}
			if (list.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");
			setJsonString(sb.toString());
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String recovery() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				Job deleteJob = (Job) this.jobService.get(new Long(id));
				deleteJob.setDelFlag(Short.valueOf(Job.DELFLAG_NOT));
				this.jobService.save(deleteJob);
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}
}
