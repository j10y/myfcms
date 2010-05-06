package com.bdzb.oa.member.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.bdzb.oa.member.model.Member;
import com.bdzb.oa.member.service.MemberService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description ª·‘±
 */

public class MemberDelete extends ActionWindow {	

	@Autowired
	private MemberService memberService;

	private Set<Member> members = (Set<Member>)Executions.getCurrent().getArg().get("members");    

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onBind() {		
				
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for(Member member:members){
			memberService.delete(member);
		}
		
		((ListWindow)this.getParent()).onFind();
	}

}
