/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 2, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author xiacc
 *
 * ������
 */
@MappedSuperclass
public abstract class Revisable {
	
	/**
	 * ����������ʱ��
	 */
	@Column(updatable = false)
	private Date createTime;
	
	/**
	 * �������޸�ʱ��
	 */
	private Date modifyTime;
	
	/**
	 * ������������
	 */
	private String creator;
	
	/**
	 * ����������޸���
	 */
	private String lastModify;

	/**
	 * ���� createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * ���� createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���� modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * ���� modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * ���� creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * ���� creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * ���� lastModify
	 */
	public String getLastModify() {
		return lastModify;
	}

	/**
	 * ���� lastModify
	 */
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}
	
	

}
