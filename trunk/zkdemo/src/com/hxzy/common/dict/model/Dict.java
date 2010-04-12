/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 23, 2010</p>
 * <p>���£�</p>
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
 * �����������ֵ�
 */
@Entity
@Table(name = "dict")
public class Dict {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// �ֵ�����
	private String name;

	// �ֵ����
	@Column(unique = true)
	private String code;

	// ����
	private String remarks;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Dict parent;

	/**
	 * ����:���е��ӽڵ�
	 */
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<Dict> childrens;

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

	/**
	 * ���� code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ���� code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ���� childrens
	 */
	public Set<Dict> getChildrens() {
		return childrens;
	}

	/**
	 * ���� childrens
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
