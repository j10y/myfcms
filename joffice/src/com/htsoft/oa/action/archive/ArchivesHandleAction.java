package com.htsoft.oa.action.archive;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.archive.ArchivesHandle;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.archive.ArchivesHandleService;
import com.htsoft.oa.service.archive.ArchivesService;

import flexjson.JSONSerializer;

public class ArchivesHandleAction extends BaseAction {

	@Resource
	private ArchivesHandleService archivesHandleService;
	private ArchivesHandle archivesHandle;

	@Resource
	private ArchivesService archivesService;
	private Long handleId;
	private String handleOpinion;
	private Short isPass;
	private Long archiveId;

	public Long getArchiveId() {
		return this.archiveId;
	}

	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}

	public String getHandleOpinion() {
		return this.handleOpinion;
	}

	public void setHandleOpinion(String handleOpinion) {
		this.handleOpinion = handleOpinion;
	}

	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	public Long getHandleId() {
		return this.handleId;
	}

	public void setHandleId(Long handleId) {
		this.handleId = handleId;
	}

	public ArchivesHandle getArchivesHandle() {
		return this.archivesHandle;
	}

	public void setArchivesHandle(ArchivesHandle archivesHandle) {
		this.archivesHandle = archivesHandle;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId().toString());

		List list = this.archivesHandleService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime",
				"archives.issueDate", "archives.createtime" });
		buff.append(serializer.exclude(new String[] { "archives.archRecType", "class" }).serialize(
				list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archivesHandleService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchivesHandle archivesHandle = (ArchivesHandle) this.archivesHandleService
				.get(this.handleId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archivesHandle));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		ArchivesHandle arh = new ArchivesHandle();
		AppUser user = ContextUtil.getCurrentUser();
		Archives archives = (Archives) this.archivesService.get(this.archiveId);
		arh.setArchives(archives);
		arh.setCreatetime(new Date());
		arh.setFillTime(new Date());
		arh.setHandleOpinion(this.handleOpinion);
		arh.setIsPass(this.isPass);
		arh.setUserId(user.getUserId());
		arh.setUserFullname(user.getFullname());
		this.archivesHandleService.save(arh);
		String signIds = getRequest().getParameter("handlerUserIds");
		if (StringUtils.isNotEmpty(signIds)) {
			String[] signId = signIds.split(",");
			int size = signId.length;
			if (size < 2) {
				archives.setStatus(Archives.STATUS_LEADERREAD);
			} else {
				int handlerSize = this.archivesHandleService.countHandler(archives.getArchivesId());
				if (size == handlerSize)
					archives.setStatus(Archives.STATUS_LEADERREAD);
				else {
					archives.setStatus(Archives.STATUS_HANDLEING);
				}
			}
			this.archivesService.save(archives);
		}
		setJsonString("{success:true}");
		return "success";
	}
}
