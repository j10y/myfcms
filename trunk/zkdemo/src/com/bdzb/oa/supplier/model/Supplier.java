/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 19, 2010</p>
 * <p>更新：</p>
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
 * 描述：供应商
 */
@Entity
@Table(name = "supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 所属种类
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;

	// 单位名称
	private String companyName;

	// 地址
	private String address;

	// 法人
	private String legalPerson;

	// 联系人
	private String reference;

	// 联系方式
	private String contacts;

	// 备注
	private String remarks;

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
	 * 返回 category
	 */
	public Dict getCategory() {
		return category;
	}

	/**
	 * 设置 category
	 */
	public void setCategory(Dict category) {
		this.category = category;
	}

	/**
	 * 返回 companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置 companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 返回 address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 返回 legalPerson
	 */
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * 设置 legalPerson
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * 返回 reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * 设置 reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * 返回 contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * 设置 contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
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

}
