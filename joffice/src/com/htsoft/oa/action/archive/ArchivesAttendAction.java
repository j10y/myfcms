package com.htsoft.oa.action.archive;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.archive.ArchivesAttend;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.archive.ArchivesAttendService;
import com.htsoft.oa.service.archive.ArchivesDocService;
import com.htsoft.oa.service.archive.ArchivesService;
import com.htsoft.oa.service.archive.DocHistoryService;
import com.htsoft.oa.service.archive.LeaderReadService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.FileAttachService;

public class ArchivesAttendAction extends BaseAction {

	@Resource
	private ArchivesAttendService archivesAttendService;

	@Resource
	private ArchivesService archivesService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private LeaderReadService leaderReadService;

	@Resource
	private ArchivesDocService archivesDocService;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private DocHistoryService docHistoryService;
	private ArchivesAttend archivesAttend;
	private Long attendId;
	private Archives archives;

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public Long getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}

	public ArchivesAttend getArchivesAttend() {
		return this.archivesAttend;
	}

	public void setArchivesAttend(ArchivesAttend archivesAttend) {
		this.archivesAttend = archivesAttend;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.archivesAttendService.getAll(filter);

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
				this.archivesAttendService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchivesAttend archivesAttend = (ArchivesAttend) this.archivesAttendService
				.get(this.attendId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archivesAttend));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String archivesStatus = getRequest().getParameter("archivesStatus");
		this.archives = ((Archives) this.archivesService.get(this.archives.getArchivesId()));
		if (StringUtils.isNotEmpty(archivesStatus)) {
			this.archives.setStatus(Short.valueOf(Short.parseShort(archivesStatus)));
		}
		this.archivesService.save(this.archives);

		this.archivesAttend.setArchives(this.archives);
		this.archivesAttend.setUserID(ContextUtil.getCurrentUserId());
		this.archivesAttend.setFullname(ContextUtil.getCurrentUser().getFullname());
		this.archivesAttend.setExecuteTime(new Date());
		this.archivesAttendService.save(this.archivesAttend);

		setJsonString("{success:true,attendId:" + this.archivesAttend.getAttendId() + "}");
		return "success";
	}

	public String proof() {
		String archivesId = getRequest().getParameter("archivesId");
		String status = getRequest().getParameter("status");
		String docs = getRequest().getParameter("docs");
		Archives archives = (Archives) this.archivesService.get(new Long(archivesId));
		AppUser curUser = ContextUtil.getCurrentUser();

		archives.setStatus(Short.valueOf(Short.parseShort(status)));

		this.archivesService.save(archives);

		this.archivesAttend.setArchives(archives);
		this.archivesAttend.setExecuteTime(new Date());
		this.archivesAttend.setUserID(ContextUtil.getCurrentUserId());
		this.archivesAttend.setFullname(ContextUtil.getCurrentUser().getFullname());
		this.archivesAttendService.save(this.archivesAttend);

		setJsonString("{success:true}");
		return "success";
	}
}
