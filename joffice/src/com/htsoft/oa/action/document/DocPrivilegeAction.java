package com.htsoft.oa.action.document;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.service.document.DocPrivilegeService;
import com.htsoft.oa.service.system.AppRoleService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DepartmentService;

public class DocPrivilegeAction extends BaseAction {

	@Resource
	private DocPrivilegeService docPrivilegeService;
	private DocPrivilege docPrivilege;

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private DepartmentService departmentService;
	private Long privilegeId;

	public Long getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public DocPrivilege getDocPrivilege() {
		return this.docPrivilege;
	}

	public void setDocPrivilege(DocPrivilege docPrivilege) {
		this.docPrivilege = docPrivilege;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<DocPrivilege> list = this.docPrivilegeService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:[");
		for (DocPrivilege privilege : list) {
			Integer rights = privilege.getRights();
			String right = Integer.toBinaryString(rights.intValue());
			Integer read = null;
			Integer update = null;
			Integer delete = null;
			char[] cc = right.toCharArray();
			if ((cc.length == 1) && (cc[0] == '1')) {
				read = Integer.valueOf(1);
			}
			if (cc.length == 2) {
				if (cc[0] == '1') {
					update = Integer.valueOf(1);
				}
				if (cc[1] == '1') {
					read = Integer.valueOf(1);
				}
			}
			if (cc.length == 3) {
				if (cc[0] == '1') {
					delete = Integer.valueOf(1);
				}
				if (cc[1] == '1') {
					update = Integer.valueOf(1);
				}
				if (cc[2] == '1') {
					read = Integer.valueOf(1);
				}
			}
			buff.append("{'privilegeId':" + privilege.getPrivilegeId() + ",'udrId':"
					+ privilege.getUdrId() + ",'udrName':'" + privilege.getUdrName()
					+ "','folderName':'" + privilege.getDocFolder().getFolderName() + "','flag':"
					+ privilege.getFlag() + ",'rightR':" + read + ",'rightU':" + update
					+ ",'rightD':" + delete + "},");
		}
		if (list.size() > 0) {
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
				this.docPrivilegeService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		DocPrivilege docPrivilege = (DocPrivilege) this.docPrivilegeService.get(this.privilegeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(docPrivilege));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String change() {
		String strPrivilegeId = getRequest().getParameter("privilegeId");
		String strField = getRequest().getParameter("field");
		String strFieldValue = getRequest().getParameter("fieldValue");
		if (StringUtils.isNotEmpty(strPrivilegeId)) {
			this.docPrivilege = ((DocPrivilege) this.docPrivilegeService.get(Long.valueOf(Long
					.parseLong(strPrivilegeId))));
			Integer in = this.docPrivilege.getRights();
			if (in.intValue() > 0) {
				String str = Integer.toBinaryString(in.intValue());
				StringBuffer buff = new StringBuffer(str);
				if (buff.length() == 1) {
					buff.insert(0, "00");
				}
				if (buff.length() == 2) {
					buff.insert(0, "0");
				}
				if (buff.length() <= 0) {
					buff.insert(0, "000");
				}
				String rights = "";
				if ("rightR".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue))
						newBuff.append(buff.deleteCharAt(2).toString()).append("1");
					else {
						newBuff.append(buff.deleteCharAt(2).toString()).append("0");
					}
					rights = newBuff.toString();
				}

				if ("rightU".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue))
						newBuff.append(buff.charAt(0)).append("1").append(buff.charAt(2));
					else {
						newBuff.append(buff.charAt(0)).append("0").append(buff.charAt(2));
					}
					rights = newBuff.toString();
				}

				if ("rightD".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue))
						newBuff.append("1").append(buff.deleteCharAt(0).toString());
					else {
						newBuff.append("0").append(buff.deleteCharAt(0).toString());
					}
					rights = newBuff.toString();
				}
				Integer right = Integer.valueOf(Integer.parseInt(rights, 2));
				this.docPrivilege.setRights(right);
				this.docPrivilegeService.save(this.docPrivilege);
				setJsonString("{success:true}");
			}
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String save() {
		this.docPrivilegeService.save(this.docPrivilege);
		setJsonString("{success:true}");
		return "success";
	}

	public String add() {
		String strFolderId = getRequest().getParameter("folderId");
		String strRoleIds = getRequest().getParameter("roleIds");
		String strUserIds = getRequest().getParameter("userIds");
		String strDepIds = getRequest().getParameter("depIds");
		String strRightR = getRequest().getParameter("rightR");
		String strRightU = getRequest().getParameter("rightU");
		String strRightD = getRequest().getParameter("rightD");
		StringBuffer buff = new StringBuffer();

		if (StringUtils.isNotEmpty(strRightD))
			buff.append("1");
		else {
			buff.append("0");
		}
		if (StringUtils.isNotEmpty(strRightU))
			buff.append("1");
		else {
			buff.append("0");
		}
		if (StringUtils.isNotEmpty(strRightR))
			buff.append("1");
		else {
			buff.append("0");
		}
		Integer rights = Integer.valueOf(Integer.parseInt(buff.toString(), 2));
		if (StringUtils.isNotEmpty(strFolderId)) {
			Long folderId = Long.valueOf(Long.parseLong(strFolderId));
			if (StringUtils.isNotEmpty(strRoleIds)) {
				String[] roles = strRoleIds.split(",");
				if (roles.length > 0) {
					for (int i = 0; i < roles.length; ++i) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag((short) 3);
						Integer roleId = Integer.valueOf(Integer.parseInt(roles[i]));
						AppRole appRole = (AppRole) this.appRoleService.get(Long.valueOf(roleId
								.longValue()));
						docp.setUdrId(roleId);
						docp.setUdrName(appRole.getName());
						docp.setRights(rights);
						docp.setFdFlag((short) 0);
						this.docPrivilegeService.save(docp);
					}
				}
			}
			if (StringUtils.isNotEmpty(strUserIds)) {
				String[] userIds = strUserIds.split(",");
				if (userIds.length > 0) {
					for (int i = 0; i < userIds.length; ++i) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag((short) 1);
						Integer userId = Integer.valueOf(Integer.parseInt(userIds[i]));
						AppUser appUser = (AppUser) this.appUserService.get(Long.valueOf(userId
								.longValue()));
						docp.setUdrId(userId);
						docp.setUdrName(appUser.getFullname());
						docp.setRights(rights);
						docp.setFdFlag((short) 0);
						this.docPrivilegeService.save(docp);
					}
				}
			}
			if (StringUtils.isNotEmpty(strDepIds)) {
				String[] depIds = strDepIds.split(",");
				if (depIds.length > 0) {
					for (int i = 0; i < depIds.length; ++i) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag((short) 2);
						Integer depId = Integer.valueOf(Integer.parseInt(depIds[i]));
						Department department = (Department) this.departmentService.get(Long
								.valueOf(depId.longValue()));
						docp.setUdrId(depId);
						docp.setUdrName(department.getDepName());
						docp.setRights(rights);
						docp.setFdFlag((short) 0);
						this.docPrivilegeService.save(docp);
					}
				}
			}
		}
		setJsonString("{success:true}");
		return "success";
	}
}
