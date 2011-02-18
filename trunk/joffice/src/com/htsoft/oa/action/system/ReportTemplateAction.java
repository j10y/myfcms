package com.htsoft.oa.action.system;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.ReportParam;
import com.htsoft.oa.model.system.ReportTemplate;
import com.htsoft.oa.service.system.ReportParamService;
import com.htsoft.oa.service.system.ReportTemplateService;

public class ReportTemplateAction extends BaseAction {

	@Resource
	private ReportTemplateService reportTemplateService;
	private ReportTemplate reportTemplate;

	@Resource
	private ReportParamService reportParamService;
	private Long reportId;

	public Long getReportId() {
		return this.reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public ReportTemplate getReportTemplate() {
		return this.reportTemplate;
	}

	public void setReportTemplate(ReportTemplate reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ReportTemplate> list = this.reportTemplateService.getAll(filter);

		Type type = new TypeToken<List<ReportTemplate>>() {
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
				List<ReportParam> list = this.reportParamService.findByRepTemp(new Long(id));
				for (ReportParam rp : list) {
					this.reportParamService.remove(rp);
				}
				this.reportTemplateService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ReportTemplate reportTemplate = (ReportTemplate) this.reportTemplateService
				.get(this.reportId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(reportTemplate));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		if (this.reportTemplate.getReportId() == null) {
			this.reportTemplate.setCreatetime(new Date());
			this.reportTemplate.setUpdatetime(new Date());
		} else {
			this.reportTemplate.setUpdatetime(new Date());
		}
		this.reportTemplateService.save(this.reportTemplate);
		setJsonString("{success:true}");
		return "success";
	}

	public String load() {
		String strReportId = getRequest().getParameter("reportId");
		if (StringUtils.isNotEmpty(strReportId)) {
			List<ReportParam> list = this.reportParamService.findByRepTemp(new Long(strReportId));
			StringBuffer sb = new StringBuffer("[");
			for (ReportParam rp : list) {
				sb.append("{xtype:'label',text:'" + rp.getParamName() + "'},{");
				if ("date".equals(rp.getParamType()))
					sb.append("xtype:'datefield',format:'Y-m-d'");
				else if ("datetime".equals(rp.getParamType()))
					sb.append("xtype:'datetimefield',format:'Y-m-d H:i:s'");
				else {
					sb.append("xtype:'textfield'");
				}
				sb.append(",name:'" + rp.getParamKey() + "',value:'" + rp.getDefaultVal() + "'},");
			}
			if (list.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");
			setJsonString("{success:true,data:" + sb.toString() + "}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String submit() {
		Map map = getRequest().getParameterMap();
		Iterator it = map.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String[] value = (String[]) entry.getValue();
			sb.append("&" + key + "=" + value[0]);
		}
		setJsonString("{success:true,data:'" + sb.toString() + "'}");
		return "success";
	}
}


 
 
 
 
 