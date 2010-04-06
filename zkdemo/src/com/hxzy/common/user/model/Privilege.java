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
 * ����: Privilege
 * </p>
 * <p>
 * ����: Ȩ����
 * </p>
 */
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

	/**
	 * ����: Ȩ��ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ������Ȩ�޵ı���
	 */	
	private String privCode;

	/**
	 * ����: Ȩ������
	 */
	private String privName;

	/**
	 * ����: ���ڵ�
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Privilege parent;

	/**
	 * ����:���е��ӽڵ�
	 */
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
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
	 * ���� parent
	 */
	public Privilege getParent() {
		return parent;
	}

	/**
	 * ���� parent
	 */
	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	/**
	 * ���� privCode
	 */
	public String getPrivCode() {
		return privCode;
	}

	/**
	 * ���� privCode
	 */
	public void setPrivCode(String privCode) {
		this.privCode = privCode;
	}

	/**
	 * ���� privName
	 */
	public String getPrivName() {
		return privName;
	}

	/**
	 * ���� privName
	 */
	public void setPrivName(String privName) {
		this.privName = privName;
	}

	/**
	 * ���� childrens
	 */
	public Set<Privilege> getChildrens() {
		return childrens;
	}

	/**
	 * ���� childrens
	 */
	public void setChildrens(Set<Privilege> childrens) {
		this.childrens = childrens;
	}

}