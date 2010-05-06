/**
 * Project Name:
 * CopyRight (c)
 * Version:1.0
 * Date:Apr 8, 2010
 * Update:
 */

package com.bdzb.oa.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdzb.oa.member.dao.MemberDao;
import com.bdzb.oa.member.model.Member;
import com.bdzb.oa.member.service.MemberService;
import com.hxzy.base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description ª·‘±
 */

public class MemberServiceImpl extends BaseServiceImpl<Member,MemberDao> implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected MemberDao getDao() {
		return memberDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	
}
