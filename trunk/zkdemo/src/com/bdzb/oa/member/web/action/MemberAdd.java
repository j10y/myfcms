package com.bdzb.oa.member.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;

import com.bdzb.oa.member.model.Member;
import com.bdzb.oa.member.service.MemberService;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 会员
 */

public class MemberAdd extends ActionWindow {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DictService dictService;

	private Member member;
	
	private Textbox	companyName;
	private Combobox  combobox;
	private Textbox	contacts;
	private Datebox	joinTime;
	private Datebox	endTime;
	
	private List list;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		member = new Member();
		list = dictService.findByProperty("parent.code", "productCategory");
		binder.loadComponent(combobox);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Dict category = null;
		if (combobox.getSelectedItem() != null) {
			category = (Dict) combobox.getSelectedItem().getValue();
		}
		
		member.setCompanyName(companyName.getValue());
		member.setCategory(category);
		member.setContacts(contacts.getValue());
		member.setJoinTime(joinTime.getValue());
		member.setEndTime(endTime.getValue());

		memberService.save(member);

		((ListWindow) this.getParent()).onFind();
	}

	/**
	 * 返回 list
	 */
	public List getList() {
		return list;
	}

	/**
	 * 设置 list
	 */
	public void setList(List list) {
		this.list = list;
	}
	
	

}
