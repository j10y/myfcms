/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 23, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.org.model;

import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 *
 * ������������Ϣ
 */
public class Org {

	/**
	 * ������id
	 */
	private Long id;
	
	/**
	 * ����������
	 */
	private String name;
	
	/**
	 * ������˵��
	 */
	private String desc;
	
	/**
	 * ��������ҵ
	 */
	private Dict industry;
	
	/**
	 * 
	 */
	private Org parent;

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
	public Org getParent() {
		return parent;
	}

	/**
	 * ���� parent
	 */
	public void setParent(Org parent) {
		this.parent = parent;
	}
	
	
	
}
