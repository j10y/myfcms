package com.hxzy.common.user.model;

import java.io.Serializable;

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
	 * 描述: 父节点
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Privilege parent;

	/**
	 * 描述: 权限描述
	 */
	private String privName;

	/**
	 * 描述: 排序标志
	 */
	private Long itemNo;

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
	 * @return the itemNo
	 */
	public Long getItemNo() {
		return itemNo;
	}

	/**
	 * @param itemNo
	 *            the itemNo to set
	 */
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

}