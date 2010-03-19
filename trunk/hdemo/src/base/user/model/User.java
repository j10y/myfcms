package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Date;
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
 * <p>
 * 类名: Person
 * </p>
 * <p>
 * 描述: 人员类
 * </p>
 */
@Entity
@Table(name = "base_user")
public class User implements Serializable {

	/**
	 * 描述: 人员ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 描述: 人员代码
	 */
	private String code;

	/**
	 * 描述: 人员名称
	 */
	private String name;

	/**
	 * 描述: 人员口令
	 */
	private String password;

	/**
	 * 描述: 人员类型
	 */
	private Long userFlag;

	/**
	 * 描述: 用户上线次数
	 */
	private Long loginFrequency;

	/**
	 * 描述: 用户最后上线时间
	 */
	private Date lastTime;

	/**
	 * 描述: 是否有效用户 0：有效，1：无效 锁定状态
	 */
	private Long isLocked;

	/**
	 * 描述: 锁定时间
	 */
	private Date lockedTime;
	
	/**
	 * 描述: 用户拥有的角色
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)   
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="user_id")},
			inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Role> roles;
	

	public User() {
	}

	/**
	 * 描述: 返回 code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 描述: 设置 code 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 描述: 返回 id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 描述: 设置 id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 描述: 返回 isLocked
	 */
	public Long getIsLocked() {
		return isLocked;
	}

	/**
	 * 描述: 设置 isLocked 
	 */
	public void setIsLocked(Long isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * 描述: 返回 lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * 描述: 设置 lastTime 
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * 描述: 返回 lockedTime
	 */
	public Date getLockedTime() {
		return lockedTime;
	}

	/**
	 * 描述: 设置 lockedTime 
	 */
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	/**
	 * 描述: 返回 loginFrequency
	 */
	public Long getLoginFrequency() {
		return loginFrequency;
	}

	/**
	 * 描述: 设置 loginFrequency 
	 */
	public void setLoginFrequency(Long loginFrequency) {
		this.loginFrequency = loginFrequency;
	}

	/**
	 * 描述: 返回 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 描述: 设置 name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述: 返回 password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 描述: 设置 password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 描述: 返回 userFlag
	 */
	public Long getUserFlag() {
		return userFlag;
	}

	/**
	 * 描述: 设置 userFlag 
	 */
	public void setUserFlag(Long userFlag) {
		this.userFlag = userFlag;
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
		final User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}