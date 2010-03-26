/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 23, 2010</p>
 * <p>���£�</p>
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
 * �����������ֵ�
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
	 * ���� desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * ���� desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * ���� parent
	 */
	public Dict getParent() {
		return parent;
	}

	/**
	 * ���� parent
	 */
	public void setParent(Dict parent) {
		this.parent = parent;
	}
}
