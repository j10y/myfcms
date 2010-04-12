/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 23, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.dict.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	// 字典名称
	private String name;

	// 字典编码
	@Column(unique = true)
	private String code;

	// 描述
	private String remarks;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Dict parent;

	/**
	 * 描述:所有的子节点
	 */
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<Dict> childrens;

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

	/**
	 * 返回 code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回 childrens
	 */
	public Set<Dict> getChildrens() {
		return childrens;
	}

	/**
	 * 设置 childrens
	 */
	public void setChildrens(Set<Dict> childrens) {
		this.childrens = childrens;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Dict other = (Dict) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
