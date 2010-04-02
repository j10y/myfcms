/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 2, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author xiacc
 *
 * 描述：
 */
@MappedSuperclass
public abstract class Revisable {
	
	/**
	 * 描述：创建时间
	 */
	@Column(updatable = false)
	private Date createTime;
	
	/**
	 * 描述：修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 描述：创建人
	 */
	private String creator;
	
	/**
	 * 描述：最后修改人
	 */
	private String lastModify;

	/**
	 * 返回 createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置 modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 返回 creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * 设置 creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 返回 lastModify
	 */
	public String getLastModify() {
		return lastModify;
	}

	/**
	 * 设置 lastModify
	 */
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}
	
	

}
