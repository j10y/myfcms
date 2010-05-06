/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 23, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.member.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.org.framework.generator.annotation.Description;

import com.hxzy.base.model.Revisable;
import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 * 
 * ��������ҵ��Ա��
 */
@Entity
@Table(name = "member")
@Description("��Ա")
public class Member extends Revisable {

	// ��˾����
	@Description("��˾����")
	private String companyName;

	// ��������
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@Description("���")
	private Dict category;

	// ��ϵ��ʽ
	@Description("��ϵ��ʽ")
	private String contacts;

	// ���ʱ��
	@Description("���ʱ��")
	private Date joinTime;

	// ����ʱ��
	@Description("����ʱ��")
	private Date endTime;

	/**
	 * ���� companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * ���� companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * ���� category
	 */
	public Dict getCategory() {
		return category;
	}

	/**
	 * ���� category
	 */
	public void setCategory(Dict category) {
		this.category = category;
	}

	/**
	 * ���� contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * ���� contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * ���� joinTime
	 */
	public Date getJoinTime() {
		return joinTime;
	}

	/**
	 * ���� joinTime
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * ���� endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * ���� endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
