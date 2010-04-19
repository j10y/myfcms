/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 19, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.supplier.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 * 
 * ��������Ӧ��
 */
@Entity
@Table(name = "supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// ��������
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;

	// ��λ����
	private String companyName;

	// ��ַ
	private String address;

	// ����
	private String legalPerson;

	// ��ϵ��
	private String reference;

	// ��ϵ��ʽ
	private String contacts;

	// ��ע
	private String remarks;

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
	 * ���� address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * ���� address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ���� legalPerson
	 */
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * ���� legalPerson
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * ���� reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * ���� reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
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

}
