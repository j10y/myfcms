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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author xiacc
 * 
 * ������
 */
@MappedSuperclass
public abstract class Revisable {

	/**
	 * ������Ψһid
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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
	 * �������Ƿ��Ѿ�ɾ��
	 */
	@Column(columnDefinition = "BOOLEAN")
	private boolean deleted;

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
	 * ���� deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * ���� deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
