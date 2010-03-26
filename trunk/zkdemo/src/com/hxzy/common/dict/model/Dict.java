/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 23, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.dict.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author xiacc
 *
 * 描述：数据字典
 */
@Entity
@Table(name = "dict")
public class Dict {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String desc;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Dict parent;

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
	 * 返回 desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置 desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 返回 parent
	 */
	public Dict getParent() {
		return parent;
	}

	/**
	 * 设置 parent
	 */
	public void setParent(Dict parent) {
		this.parent = parent;
	}
}
