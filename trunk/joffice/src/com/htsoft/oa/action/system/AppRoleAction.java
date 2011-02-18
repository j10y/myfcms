package com.htsoft.oa.action.system;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppFunction;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.service.system.AppFunctionService;
import com.htsoft.oa.service.system.AppRoleService;

public class AppRoleAction extends BaseAction {

	@Resource
	private AppFunctionService appFunctionService;
	private static String IS_COPY = "1";

	@Resource
	private AppRoleService appRoleService;
	private AppRole appRole;
	private Long roleId;

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AppRole getAppRole() {
		return this.appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<AppRole> list = this.appRoleService.getAll(filter);

		Type type = new TypeToken<List<AppRole>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();

		return "success";
	}

	public String tree() {
		StringBuffer buff = new StringBuffer("[");
		List<AppRole> listRole = this.appRoleService.getAll();
		for (AppRole role : listRole) {
			buff.append("{id:'" + role.getRoleId() + "',text:'" + role.getRoleName()
					+ "',leaf:true},");
		}
		if (!listRole.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				AppRole appRole = (AppRole) this.appRoleService.get(new Long(id));
				appRole.getAppUsers().remove(appRole);
				appRole.getFunctions().remove(appRole);
				this.appRoleService.remove(appRole);
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String grant() {
		AppRole appRole = (AppRole) this.appRoleService.get(this.roleId);
		String rights = getRequest().getParameter("rights");

		if (rights == null) {
			rights = "";
		}

		if (!rights.equals(appRole.getRights())) {
			appRole.setRights(rights);

			appRole.getFunctions().clear();

			String[] funs = rights.split("[,]");

			for (int i = 0; i < funs.length; ++i) {
				if (funs[i].startsWith("_")) {
					AppFunction af = this.appFunctionService.getByKey(funs[i]);
					if (af != null) {
						appRole.getFunctions().add(af);
					}
				}

			}

			this.appRoleService.save(appRole);

			AppUtil.reloadSecurityDataSource();
		}

		return "success";
	}

	public String grantXml() {
		Document grantMenuDoc = AppUtil.getGrantMenuDocument();
		setJsonString(grantMenuDoc.asXML());
		return "success";
	}

	public String get() {
		AppRole appRole = (AppRole) this.appRoleService.get(this.roleId);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appRole));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String isCopy = getRequest().getParameter("isCopy");
		if ((StringUtils.isNotEmpty(isCopy)) && (IS_COPY.equals(isCopy))) {
			AppRole role = new AppRole();
			role.setIsDefaultIn((short) 0);
			role.setRoleDesc(this.appRole.getRoleDesc());
			role.setStatus(this.appRole.getStatus());
			role.setRoleName(this.appRole.getRoleName());
			this.appRole = ((AppRole) this.appRoleService.get(this.appRole.getRoleId()));
			Set set = new HashSet(this.appRole.getFunctions());
			role.setFunctions(set);
			role.setRights(this.appRole.getRights());
			this.appRoleService.save(role);
		} else {
			this.appRole.setIsDefaultIn((short) 0);
			this.appRoleService.save(this.appRole);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String check() {
		String roleName = getRequest().getParameter("roleName");
		AppRole appRole = this.appRoleService.getByRoleName(roleName);
		if (appRole == null)
			setJsonString("{success:true}");
		else {
			setJsonString("{success:false}");
		}
		return "success";
	}
}


 
 
 
 
 