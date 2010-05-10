package com.bdzb.oa.member.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
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

public class MemberEdit extends ActionWindow {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DictService dictService;

	private Member member = (Member) Executions.getCurrent().getArg().get("member");

	private Textbox	companyName;
	private Combobox combobox;
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
		
		list = dictService.findByProperty("parent.code", "productCategory");
		binder.loadComponent(combobox);
		
		if (member != null) {
			companyName.setValue(member.getCompanyName());	
			contacts.setValue(member.getContacts());	
			joinTime.setValue(member.getJoinTime());	
			endTime.setValue(member.getEndTime());	
			
			combobox.setItemRenderer(new ComboitemRenderer() {

				public void render(Comboitem item, Object o) throws Exception {
					Dict d = (Dict) o;
					item.setValue(d);
					item.setLabel(d.getName());
					Dict category = member.getCategory();
					
					if (d.equals(category)) {
						combobox.setSelectedItem(item);
					}

				}

			});
		}
		
		
		
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

		memberService.update(member);

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

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "修改"+member.getCompanyName();
	}

	
}
