package com.htsoft.oa.model.system;

import java.util.HashSet;
import java.util.Set;

import org.jbpm.api.identity.Group;
import org.springframework.security.GrantedAuthority;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class AppRole extends BaseModel implements GrantedAuthority, Group {
	public static String ROLE_PUBLIC = "ROLE_PUBLIC";

	public static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

	public static final Long SUPER_ROLEID = Long.valueOf(-1L);
	public static final String SUPER_RIGHTS = "__ALL";

	@Expose
	private Long roleId;

	@Expose
	private String roleName;

	@Expose
	private String roleDesc;

	@Expose
	private Short status;

	@Expose
	private Short isDefaultIn;

	@Expose
	private String rights;
	private Set<AppFunction> functions = new HashSet();
	private Set<AppUser> appUsers = new HashSet();

	public Short getIsDefaultIn() {
		return this.isDefaultIn;
	}

	public void setIsDefaultIn(Short isDefaultIn) {
		this.isDefaultIn = isDefaultIn;
	}

	public Set<AppUser> getAppUsers() {
		return this.appUsers;
	}

	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getAuthority() {
		return this.roleName;
	}

	public int compareTo(Object o) {
		return 0;
	}

	public String getId() {
		return this.roleId.toString();
	}

	public String getName() {
		return this.roleName;
	}

	public String getType() {
		return "candidate";
	}

	public Set<AppFunction> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set<AppFunction> functions) {
		this.functions = functions;
	}
}


 
 
 
 