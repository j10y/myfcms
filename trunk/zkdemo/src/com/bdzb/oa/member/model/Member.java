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

import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 *
 * 描述：商业会员类
 */
public class Member {
	
	private Long id;

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
	
	

}
