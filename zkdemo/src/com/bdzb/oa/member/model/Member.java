/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 23, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.member.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hxzy.base.model.Revisable;
import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 *
 * 描述：商业会员类
 */
@Entity
@Table(name = "member")
public class Member extends Revisable{

	//公司名称
	private String companyName;
	
	// 所属种类
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;
	
	//联系方式
	private String contacts;
	
	//入会时间
	private Date joinTime;
	
	//到期时间
	private Date endTime;

	/**
	 * 返回 companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置 companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 返回 category
	 */
	public Dict getCategory() {
		return category;
	}

	/**
	 * 设置 category
	 */
	public void setCategory(Dict category) {
		this.category = category;
	}

	/**
	 * 返回 contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * 设置 contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * 返回 joinTime
	 */
	public Date getJoinTime() {
		return joinTime;
	}

	/**
	 * 设置 joinTime
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * 返回 endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置 endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
