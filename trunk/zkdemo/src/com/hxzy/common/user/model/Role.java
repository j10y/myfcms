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
 * ������Role ��������ɫ��
 * 
 * @author xiacc
 * 
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	/**
	 * ����: ��ɫid
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ����: ��ɫ����
	 */
	@Column(unique = true)
	private String roleName;

	/**
	 * ������˵��
	 */
	private String remarks;

	/**
	 * ��������ɫ��ӵ�е�Ȩ��
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "role_privilege", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "privilege_id") })
	private Set<Privilege> privileges;

	/**
	 * ������ӵ�иý�ɫ�����е��û�
	 */
	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	private Set<User> users;

	/**
	 * ������������־������ý�ɫ�Ƿ񹫿�
	 */
	@Column(updatable = false, columnDefinition = "BOOLEAN")
	private Boolean common;

	/**
	 * ���캯��
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
	 * ����: ���� roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * ����: ���� roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * ���� privileges
	 */
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * ���� privileges
	 */
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * ���� users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * ���� users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * ���� common
	 */
	public Boolean getCommon() {
		return common;
	}

	/**
	 * ���� common
	 */
	public void setCommon(Boolean common) {
		this.common = common;
	}

	/**
	 * ���� remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * ���� remarks
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
