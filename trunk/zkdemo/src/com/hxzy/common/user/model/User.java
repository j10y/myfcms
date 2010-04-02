package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Date;
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
 * @author xiacc
 *
 * 描述：用户类
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	
	/**
	 * 描述: 人员ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 描述: 人员登录名
	 */
	@Column(unique = true)
	private String username;

	/**
	 * 描述: 人员姓名
	 */
	private String truename;

	/**
	 * 描述: 人员口令
	 */
	private String password;

	/**
	 * 描述: 人员类型 0超级管理员 1普通
	 */
	@Column(updatable = false)
	private Long type;

	/**
	 * 描述: 用户上线次数
	 */
	private Long loginFrequency;

	/**
	 * 描述: 用户最后上线时间
	 */
	private Date lastTime;

	/**
	 * 描述: 是否锁定状态
	 */	
	@Column(columnDefinition="BOOLEAN")
	private Boolean locked;

	/**
	 * 描述: 锁定时间
	 */
	private Date lockedTime;

	/**
	 * 描述: 用户拥有的角色
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;

	/**
	 * 返回 id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回 truename
	 */
	public String getTruename() {
		return truename;
	}

	/**
	 * 设置 truename
	 */
	public void setTruename(String truename) {
		this.truename = truename;
	}

	/**
	 * 返回 password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回 type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * 设置 type
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * 返回 loginFrequency
	 */
	public Long getLoginFrequency() {
		return loginFrequency;
	}

	/**
	 * 设置 loginFrequency
	 */
	public void setLoginFrequency(Long loginFrequency) {
		this.loginFrequency = loginFrequency;
	}

	/**
	 * 返回 lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * 设置 lastTime
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * 返回 locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * 设置 locked
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * 返回 lockedTime
	 */
	public Date getLockedTime() {
		return lockedTime;
	}

	/**
	 * 设置 lockedTime
	 */
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	/**
	 * 返回 roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 设置 roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}