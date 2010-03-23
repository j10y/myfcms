/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 23, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.org.model;

import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 *
 * 描述：机构信息
 */
public class Org {

	/**
	 * 描述：id
	 */
	private Long id;
	
	/**
	 * 描述：名称
	 */
	private String name;
	
	/**
	 * 描述：说明
	 */
	private String desc;
	
	/**
	 * 描述：行业
	 */
	private Dict industry;
	
	/**
	 * 
	 */
	private Org parent;

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
	public Org getParent() {
		return parent;
	}

	/**
	 * 设置 parent
	 */
	public void setParent(Org parent) {
		this.parent = parent;
	}
	
	
	
}
