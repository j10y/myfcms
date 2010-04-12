/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.expert.model;

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
 * ������
 */
@Entity
@Table(name = "expert")
public class Expert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// ��������
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;

	// ����
	private String name;

	// ְ��
	private String titles;

	// ������λ
	private String department;

	// ��ϵ�绰
	private String telephone;

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
	 * ���� name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���� name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���� titles
	 */
	public String getTitles() {
		return titles;
	}

	/**
	 * ���� titles
	 */
	public void setTitles(String titles) {
		this.titles = titles;
	}

	/**
	 * ���� department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * ���� department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * ���� telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * ���� telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
