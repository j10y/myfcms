package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 类名：Role
 * 描述：角色类
 * @author xiacc
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	/**
	 * 描述: 角色id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 描述: 角色名称
	 */
	private String roleName;
	
	/**
	 * 描述：角色所拥有的权限
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)   
	@JoinTable(name="role_privilege",joinColumns={@JoinColumn(name="role_id")},
			inverseJoinColumns={@JoinColumn(name="privilege_id")})
	private Set<Privilege> privileges;
	
	/**
	 * 描述：拥有该角色的所有的用户
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)   
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="role_id")},
			inverseJoinColumns={@JoinColumn(name="user_id")})
	private Set<User> baseUsers;
	
	/**
	 * 描述：公开标志，表面该角色是否公开
	 */
	private Long publicFlag;

	/**
	 * 构造函数
	 */
	public Role() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 描述: 返回 roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 描述: 设置 roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 返回 privileges
	 */
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * 设置 privileges
	 */
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * 返回 baseUsers
	 */
	public Set<User> getBaseUsers() {
		return baseUsers;
	}

	/**
	 * 设置 baseUsers
	 */
	public void setBaseUsers(Set<User> baseUsers) {
		this.baseUsers = baseUsers;
	}

	/**
	 * 返回 publicFlag
	 */
	public Long getPublicFlag() {
		return publicFlag;
	}

	/**
	 * 设置 publicFlag
	 */
	public void setPublicFlag(Long publicFlag) {
		this.publicFlag = publicFlag;
	}
}
