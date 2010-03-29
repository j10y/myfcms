/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-12-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.log.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.hxzy.common.user.model.User;

/**
 * <p>
 * ����: Log
 * </p>
 * <p>
 * ����: ϵͳ��־��
 * </p>
 */
@Entity
@Table(name = "log")
public class Log implements Serializable {

	/**
	 * ����: ��¼ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ����: �������û���
	 */
	private String username;
	
	
	/**
	 * ������������IP
	 */
	private String ip;

	
	/**
	 * ����: ����ʱ��
	 */
	private Date logTime = new Date();

	/**
	 * ����: ��������
	 */
	private String logObject;

	/**
	 * ����: ������ɾ�����޸ģ���������½���ǳ���
	 */
	private String logAction;

	/**
	 * ����: ��ϸ��Ϣ
	 */
	private String detail;

	/**
	 * ����: ���� detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * ����: ���� detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * ����: ���� logAction
	 */
	public String getLogAction() {
		return logAction;
	}

	/**
	 * ����: ���� logAction
	 */
	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	/**
	 * ����: ���� logObject
	 */
	public String getLogObject() {
		return logObject;
	}

	/**
	 * ����: ���� logObject
	 */
	public void setLogObject(String logObject) {
		this.logObject = logObject;
	}

	/**
	 * ����: ���� logTime
	 */
	public Date getLogTime() {
		return logTime;
	}

	/**
	 * ����: ���� logTime
	 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

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

}