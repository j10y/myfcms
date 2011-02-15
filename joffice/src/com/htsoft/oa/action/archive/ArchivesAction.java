package com.htsoft.oa.action.archive;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchHasten;
import com.htsoft.oa.model.archive.ArchRecUser;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.archive.ArchivesDep;
import com.htsoft.oa.model.archive.ArchivesDoc;
import com.htsoft.oa.model.archive.ArchivesType;
import com.htsoft.oa.model.archive.DocHistory;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.archive.ArchHastenService;
import com.htsoft.oa.service.archive.ArchRecUserService;
import com.htsoft.oa.service.archive.ArchivesDepService;
import com.htsoft.oa.service.archive.ArchivesDocService;
import com.htsoft.oa.service.archive.ArchivesService;
import com.htsoft.oa.service.archive.ArchivesTypeService;
import com.htsoft.oa.service.archive.DocHistoryService;
import com.htsoft.oa.service.flow.TaskService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DepartmentService;
import com.htsoft.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;

public class ArchivesAction extends BaseAction {

	@Resource
	private ArchivesService archivesService;

	@Resource
	private ArchivesDocService archivesDocService;

	@Resource
	private ArchivesTypeService archivesTypeService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private ArchivesDepService archivesDepService;
	private Archives archives;

	@Resource
	private AppUserService appUserService;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private DocHistoryService docHistoryService;

	@Resource
	private ArchRecUserService archRecUserService;

	@Resource
	private TaskService taskservice;

	@Resource
	private ShortMessageService messageService;

	@Resource
	private ArchHastenService archHastenService;
	private Long archivesId;

	public Long getArchivesId() {
		return this.archivesId;
	}

	public void setArchivesId(Long archivesId) {
		this.archivesId = archivesId;
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<Archives> list = this.archivesService.getAll(filter);

		Type type = new TypeToken<List<Archives>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String cruList() {
		PagingBean pb = getInitPagingBean();
		AppUser appUser = ContextUtil.getCurrentUser();
		List<Archives> list = this.archivesService.findByUserOrRole(appUser.getUserId(), appUser
				.getRoles(), pb);
		Type type = new TypeToken<List<Archives>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				pb.getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archivesService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Archives archives = (Archives) this.archivesService.get(this.archivesId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(archives));
		sb.append("]}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String arcRecfileIds = getRequest().getParameter("archivesRecfileIds");
		String archDepId = getRequest().getParameter("archDepId");
		String handlerUids = getRequest().getParameter("signUserIds");
		AppUser appUser = ContextUtil.getCurrentUser();
		this.archives.setArchType(Archives.ARCHIVE_TYPE_RECEIVE);
		this.archives.setIssuerId(appUser.getUserId());
		this.archives.setIssuer(appUser.getFullname());
		this.archives.setHandlerUids(handlerUids);
		this.archives.setIssueDate(new Date());
		if (StringUtils.isNotEmpty(arcRecfileIds)) {
			this.archives.setFileCounts(Integer.valueOf(arcRecfileIds.split(",").length));
		}
		this.archivesService.save(this.archives);
		if (StringUtils.isNotEmpty(arcRecfileIds)) {
			List<ArchivesDoc> list = this.archivesDocService.findByAid(this.archives
					.getArchivesId());
			for (ArchivesDoc archivesDoc : list) {
				this.archivesDocService.remove(archivesDoc);
			}
			String[] fileIds = arcRecfileIds.split(",");
			for (String id : fileIds) {
				FileAttach fileAttach = (FileAttach) this.fileAttachService.get(new Long(id));
				ArchivesDoc archivesDoc = new ArchivesDoc();
				archivesDoc.setArchives(this.archives);
				archivesDoc.setFileAttach(fileAttach);
				archivesDoc.setDocName(fileAttach.getFileName());
				archivesDoc.setDocStatus((short) 1);
				archivesDoc.setCurVersion(Integer.valueOf(1));
				archivesDoc.setDocPath(fileAttach.getFilePath());
				archivesDoc.setCreatetime(new Date());
				archivesDoc.setUpdatetime(new Date());
				this.archivesDocService.save(archivesDoc);
			}

		}

		if (StringUtils.isNotEmpty(archDepId)) {
			ArchivesDep archivesDep = (ArchivesDep) this.archivesDepService
					.get(new Long(archDepId));
			archivesDep.setStatus(ArchivesDep.STATUS_SIGNED);
			this.archivesDepService.save(archivesDep);
		}

		setJsonString("{success:true,archivesId:" + this.archives.getArchivesId() + "}");
		return "success";
	}

	public String docs() {
		StringBuffer sb = new StringBuffer("{success:true,totalCounts:");

		if (this.archivesId != null) {
			this.archives = ((Archives) this.archivesService.get(this.archivesId));
			Gson gson = new Gson();
			Set docs = this.archives.getArchivesDocs();
			List<Archives> docList = new ArrayList();
			docList.addAll(docs);
			Type type = new TypeToken<List<Archives>>() {
			}.getType();
			sb.append(docs.size());
			sb.append(",results:").append(new Gson().toJson(docList, type));
		} else {
			sb.append("0,results:[]");
		}
		sb.append("}");

		setJsonString(sb.toString());

		return "success";
	}

	public String getIssue() {
		Archives archives = (Archives) this.archivesService.get(this.archivesId);

		JSONSerializer json = new JSONSerializer();

		StringBuffer sb = new StringBuffer("{success:true,data:[");

		sb.append(json.serialize(archives));
		sb.append("]}");
		setJsonString(sb.toString());

		return "success";
	}

	public String saveIssue() {
		String docs = getRequest().getParameter("docs");
		String status = getRequest().getParameter("status");
		AppUser curUser = ContextUtil.getCurrentUser();
		Set archivesDocSet = new HashSet();
		if (StringUtils.isNotEmpty(docs)) {
			Gson gson = new Gson();
			ArchivesDoc[] archivesDocs = (ArchivesDoc[]) gson.fromJson(docs, ArchivesDoc[].class);
			if (archivesDocs != null) {
				for (int i = 0; i < archivesDocs.length; ++i) {
					if ((archivesDocs[i].getDocId() == null)
							|| (archivesDocs[i].getDocId().longValue() == 0L)) {
						archivesDocs[i].setDocId(null);
						archivesDocs[i].initUsers(curUser);
						archivesDocs[i].setDocStatus(Short.valueOf(ArchivesDoc.STATUS_MODIFY));
						archivesDocs[i].setUpdatetime(new Date());
						archivesDocs[i].setCreatetime(new Date());
						archivesDocs[i].setFileAttach(this.fileAttachService
								.getByPath(archivesDocs[i].getDocPath()));
						this.archivesDocService.save(archivesDocs[i]);

						DocHistory newHistory = new DocHistory();
						newHistory.setArchivesDoc(archivesDocs[i]);
						newHistory.setFileAttach(archivesDocs[i].getFileAttach());
						newHistory.setDocName(archivesDocs[i].getDocName());
						newHistory.setPath(archivesDocs[i].getDocPath());
						newHistory.setVersion(Integer.valueOf(ArchivesDoc.ORI_VERSION));
						newHistory.setUpdatetime(new Date());
						newHistory.setMender(curUser.getFullname());
						this.docHistoryService.save(newHistory);
					} else {
						archivesDocs[i] = ((ArchivesDoc) this.archivesDocService
								.get(archivesDocs[i].getDocId()));
					}
					archivesDocSet.add(archivesDocs[i]);
				}
			}
		}

		if (this.archives.getArchivesId() == null) {
			this.archives.setIssuer(curUser.getFullname());
			this.archives.setIssuerId(curUser.getUserId());

			ArchivesType archivesType = (ArchivesType) this.archivesTypeService.get(this.archives
					.getArchivesType().getTypeId());
			this.archives.setArchivesType(archivesType);

			this.archives.setArchType(Archives.ARCHIVE_TYPE_DISPATCH);

			if (StringUtils.isNotEmpty(status))
				this.archives.setStatus(Short.valueOf(Short.parseShort(status)));
			else {
				this.archives.setStatus(Archives.STATUS_DRAFT);
			}
			this.archives.setCreatetime(new Date());
			this.archives.setIssueDate(new Date());

			this.archives.setFileCounts(Integer.valueOf(archivesDocSet.size()));
			this.archives.setArchivesDocs(archivesDocSet);
			this.archivesService.save(this.archives);
		} else {
			Archives orgArchives = (Archives) this.archivesService.get(this.archives
					.getArchivesId());

			ArchivesType archivesType = (ArchivesType) this.archivesTypeService.get(this.archives
					.getArchivesType().getTypeId());
			this.archives.setArchivesType(archivesType);
			this.archives.setTypeName(archivesType.getTypeName());

			if (StringUtils.isNotEmpty(status))
				this.archives.setStatus(Short.valueOf(Short.parseShort(status)));
			else {
				this.archives.setStatus(orgArchives.getStatus());
			}

			this.archives.setCreatetime(orgArchives.getCreatetime());

			this.archives.setFileCounts(Integer.valueOf(archivesDocSet.size()));
			this.archives.setArchivesDocs(archivesDocSet);

			this.archives.setIssueDate(new Date());
			this.archives.setArchType(orgArchives.getArchType());

			this.archives.setIssuer(orgArchives.getIssuer());
			this.archives.setIssuerId(orgArchives.getIssuerId());
			this.archivesService.merge(this.archives);
		}

		setJsonString("{success:true,archivesId:'" + this.archives.getArchivesId() + "'}");
		return "success";
	}

	public String handOut() {
		if (this.archivesId == null) {
			this.archivesId = this.archives.getArchivesId();
		}
		this.archives = ((Archives) this.archivesService.get(this.archivesId));

		String depIds = this.archives.getRecDepIds();
		StringBuffer msg = new StringBuffer("");
		if (StringUtils.isNotEmpty(depIds)) {
			String[] depIdArr = depIds.split("[,]");
			if (depIdArr != null) {
				StringBuffer recIds = new StringBuffer("");

				for (int i = 0; i < depIdArr.length; ++i) {
					Long depId = new Long(depIdArr[i]);
					Department department = (Department) this.departmentService.get(depId);
					ArchRecUser archRecUser = this.archRecUserService.getByDepId(depId);

					ArchivesDep archivesDep = new ArchivesDep();
					archivesDep.setSubject(this.archives.getSubject());
					archivesDep.setDepartment(department);
					archivesDep.setArchives(this.archives);
					archivesDep.setIsMain(ArchivesDep.RECEIVE_MAIN);
					archivesDep.setStatus(ArchivesDep.STATUS_UNSIGNED);
					if ((archRecUser != null) && (archRecUser.getUserId() != null)) {
						archivesDep.setSignUserID(archRecUser.getUserId());
						archivesDep.setSignFullname(archRecUser.getFullname());

						recIds.append(archRecUser.getUserId()).append(",");
					} else {
						msg.append(department.getDepName()).append(" 部门还未添加收文负责人");
					}

					this.archivesDepService.save(archivesDep);
				}

				if (StringUtils.isNotEmpty(recIds.toString())) {
					String content = "您有新的公文,请及时签收.";
					this.messageService.save(AppUser.SYSTEM_USER, recIds.toString(), content,
							ShortMessage.MSG_TYPE_TASK);
				}
			}
		}

		String archivesStatus = getRequest().getParameter("archivesStatus");
		if (StringUtils.isNotEmpty(archivesStatus)) {
			this.archives.setStatus(Short.valueOf(Short.parseShort(archivesStatus)));
		}
		this.archivesService.save(this.archives);
		return "success";
	}

	public String hasten() {
		String activityName = getRequest().getParameter("activityName");
		String archivesId = getRequest().getParameter("archivesId");
		String content = getRequest().getParameter("content");
		if ((StringUtils.isNotEmpty(activityName)) && (StringUtils.isNotEmpty(archivesId))) {
			Long arcId = new Long(archivesId);
			Date lastCruTime = this.archHastenService.getLeastRecordByUser(arcId);
			if (lastCruTime != null) {
				Date now = new Date();
				long time = now.getTime() - lastCruTime.getTime();
				if (time / 60000L < 30L) {
					this.jsonString = "{success:false,message:'催办过于频繁！'}";
					return "success";
				}
			}
			Archives archives = (Archives) this.archivesService.get(arcId);
			Set userIds = this.taskservice.getHastenByActivityNameVarKeyLongVal(activityName,
					"archives.archivesId", new Long(archivesId));
			StringBuffer strUsrIds = new StringBuffer();
			Iterator it = userIds.iterator();
			while (it.hasNext()) {
				ArchHasten ah = new ArchHasten();
				Long userId = (Long) it.next();
				AppUser appUser = (AppUser) this.appUserService.get(userId);
				ah.setContent(content);
				ah.setCreatetime(new Date());
				ah.setArchives(archives);
				ah.setHastenFullname(ContextUtil.getCurrentUser().getFullname());
				ah.setHandlerUserId(appUser.getUserId());
				ah.setHandlerFullname(appUser.getFullname());
				this.archHastenService.save(ah);
				strUsrIds.append(userId.toString()).append(",");
			}
			if (userIds.size() > 0) {
				strUsrIds.deleteCharAt(strUsrIds.length() - 1);
			}
			this.messageService.save(AppUser.SYSTEM_USER, strUsrIds.toString(), content,
					ShortMessage.MSG_TYPE_TASK);
		}
		this.jsonString = "{success:true}";
		return "success";
	}
}
