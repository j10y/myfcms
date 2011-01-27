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
import com.htsoft.oa.model.archive.LeaderRead;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.archive.ArchivesService;
import com.htsoft.oa.service.archive.LeaderReadService;
import com.htsoft.oa.service.system.AppUserService;

import flexjson.JSONSerializer;

public class LeaderReadAction extends BaseAction {

	@Resource
	private LeaderReadService leaderReadService;
	private LeaderRead leaderRead;

	@Resource
	private ArchivesService archivesService;

	@Resource
	private AppUserService appUserService;
	private Long readId;
	private String leaderOpinion;
	private Short isPass;
	private Archives archives;

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	public String getLeaderOpinion() {
		return this.leaderOpinion;
	}

	public void setLeaderOpinion(String leaderOpinion) {
		this.leaderOpinion = leaderOpinion;
	}

	public Long getReadId() {
		return this.readId;
	}

	public void setReadId(Long readId) {
		this.readId = readId;
	}

	public LeaderRead getLeaderRead() {
		return this.leaderRead;
	}

	public void setLeaderRead(LeaderRead leaderRead) {
		this.leaderRead = leaderRead;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		filter.addFilter("Q_archives.archType_SN_EQ", Archives.ARCHIVE_TYPE_RECEIVE.toString());
		List list = this.leaderReadService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime",
				"archives.issueDate", "archives.createtime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.leaderReadService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		LeaderRead leaderRead = (LeaderRead) this.leaderReadService.get(this.readId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(leaderRead));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String strArchivesId = getRequest().getParameter("archivesId");
		if (StringUtils.isNotEmpty(strArchivesId)) {
			LeaderRead leader = new LeaderRead();
			Archives archives = (Archives) this.archivesService.get(new Long(strArchivesId));
			AppUser user = ContextUtil.getCurrentUser();
			leader.setArchives(archives);
			leader.setLeaderOpinion(this.leaderOpinion);
			leader.setIsPass(this.isPass);
			leader.setUserId(user.getUserId());
			leader.setLeaderName(user.getFullname());
			leader.setCreatetime(new Date());
			this.leaderReadService.save(leader);
			archives.setStatus(Archives.STATUS_DISPATCH);
			this.archivesService.save(archives);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String saveDep() {
		this.archives = ((Archives) this.archivesService.get(this.archives.getArchivesId()));
		String archivesStatus = getRequest().getParameter("archivesStatus");
		if (StringUtils.isNotEmpty(archivesStatus)) {
			this.archives.setStatus(Short.valueOf(Short.parseShort(archivesStatus)));
		}
		this.archivesService.save(this.archives);

		this.leaderRead.setLeaderName(ContextUtil.getCurrentUser().getFullname());
		this.leaderRead.setUserId(ContextUtil.getCurrentUserId());
		this.leaderRead.setArchives(this.archives);
		this.leaderRead.setCreatetime(new Date());
		this.leaderRead.setIsPass(LeaderRead.IS_PASS);
		this.leaderReadService.save(this.leaderRead);

		setJsonString("{success:true,readId:" + this.leaderRead.getReadId() + "}");
		return "success";
	}
}
