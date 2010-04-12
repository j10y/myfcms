/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
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
 * 描述：
 */
@Entity
@Table(name = "expert")
public class Expert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 所属种类
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;

	// 姓名
	private String name;

	// 职称
	private String titles;

	// 工作单位
	private String department;

	// 联系电话
	private String telephone;

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
	 * 返回 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 titles
	 */
	public String getTitles() {
		return titles;
	}

	/**
	 * 设置 titles
	 */
	public void setTitles(String titles) {
		this.titles = titles;
	}

	/**
	 * 返回 department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 设置 department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 返回 telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置 telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
