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
 * �������û���
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	
	/**
	 * ����: ��ԱID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ����: ��Ա��¼��
	 */
	@Column(unique = true)
	private String username;

	/**
	 * ����: ��Ա����
	 */
	private String truename;

	/**
	 * ����: ��Ա����
	 */
	private String password;

	/**
	 * ����: ��Ա���� 0��������Ա 1��ͨ
	 */
	@Column(updatable = false)
	private Long type;

	/**
	 * ����: �û����ߴ���
	 */
	private Long loginFrequency;

	/**
	 * ����: �û��������ʱ��
	 */
	private Date lastTime;

	/**
	 * ����: �Ƿ�����״̬
	 */	
	@Column(columnDefinition="BOOLEAN")
	private Boolean locked;

	/**
	 * ����: ����ʱ��
	 */
	private Date lockedTime;

	/**
	 * ����: �û�ӵ�еĽ�ɫ
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;

	/**
	 * ���� id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ���� id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ���� username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * ���� username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * ���� truename
	 */
	public String getTruename() {
		return truename;
	}

	/**
	 * ���� truename
	 */
	public void setTruename(String truename) {
		this.truename = truename;
	}

	/**
	 * ���� password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ���� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ���� type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * ���� type
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * ���� loginFrequency
	 */
	public Long getLoginFrequency() {
		return loginFrequency;
	}

	/**
	 * ���� loginFrequency
	 */
	public void setLoginFrequency(Long loginFrequency) {
		this.loginFrequency = loginFrequency;
	}

	/**
	 * ���� lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * ���� lastTime
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * ���� locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * ���� locked
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * ���� lockedTime
	 */
	public Date getLockedTime() {
		return lockedTime;
	}

	/**
	 * ���� lockedTime
	 */
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	/**
	 * ���� roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * ���� roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}