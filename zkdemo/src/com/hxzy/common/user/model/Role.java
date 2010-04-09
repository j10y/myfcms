package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hxzy.base.model.Revisable;

/**
 * 类名：Role 描述：角色类
 * 
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
	@Column(unique = true)
	private String roleName;

	/**
	 * 描述：说明
	 */
	private String remarks;

	/**
	 * 描述：角色所拥有的权限
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "role_privilege", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "privilege_id") })
	private Set<Privilege> privileges;

	/**
	 * 描述：拥有该角色的所有的用户
	 */
	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	private Set<User> users;

	/**
	 * 描述：公开标志，表面该角色是否公开
	 */
	@Column(updatable = false, columnDefinition = "BOOLEAN")
	private Boolean common;

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
	 * @param id
	 *            the id to set
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
	 * 返回 users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * 设置 users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * 返回 common
	 */
	public Boolean getCommon() {
		return common;
	}

	/**
	 * 设置 common
	 */
	public void setCommon(Boolean common) {
		this.common = common;
	}

	/**
	 * 返回 remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置 remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
