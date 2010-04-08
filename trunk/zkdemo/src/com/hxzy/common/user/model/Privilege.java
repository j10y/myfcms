package com.hxzy.common.user.model;

import java.io.Serializable;
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
 * <p>
 * 类名: Privilege
 * </p>
 * <p>
 * 描述: 权限类
 * </p>
 */
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

	/**
	 * 描述: 权限ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 描述：权限的编码
	 */	
	private String privCode;

	/**
	 * 描述: 权限描述
	 */
	private String privName;

	/**
	 * 描述: 父节点
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Privilege parent;

	/**
	 * 描述:所有的子节点
	 */
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<Privilege> childrens;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 parent
	 */
	public Privilege getParent() {
		return parent;
	}

	/**
	 * 设置 parent
	 */
	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	/**
	 * 返回 privCode
	 */
	public String getPrivCode() {
		return privCode;
	}

	/**
	 * 设置 privCode
	 */
	public void setPrivCode(String privCode) {
		this.privCode = privCode;
	}

	/**
	 * 返回 privName
	 */
	public String getPrivName() {
		return privName;
	}

	/**
	 * 设置 privName
	 */
	public void setPrivName(String privName) {
		this.privName = privName;
	}

	/**
	 * 返回 childrens
	 */
	public Set<Privilege> getChildrens() {
		return childrens;
	}

	/**
	 * 设置 childrens
	 */
	public void setChildrens(Set<Privilege> childrens) {
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
		final Privilege other = (Privilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}