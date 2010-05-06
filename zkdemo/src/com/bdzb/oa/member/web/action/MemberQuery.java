package com.bdzb.oa.member.web.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.base.web.window.Message;
import com.bdzb.oa.member.model.Member;
import com.bdzb.oa.member.service.MemberService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description ª·‘±
 */

public class MemberQuery extends ListWindow {

	@Autowired
	private MemberService memberService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(
					Restrictions.like("companyName", search.getValue(),MatchMode.ANYWHERE),			
					Restrictions.like("s.name", search.getValue(),MatchMode.ANYWHERE));
			LogicalExpression l2 = Restrictions.or(
					l1, 
					Restrictions.like("contacts", search.getValue(), MatchMode.ANYWHERE));						
			
			detachedCriteria.createAlias("category", "s").add(l2);
		}
		Pagination pagination = memberService.findPageByCriteria(detachedCriteria, pg.getPageSize(),
				pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onDelete() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("");
			return;
		}

		Set<Listitem> items = listbox.getSelectedItems();
		Set members = new HashSet();
		for (Listitem item : items) {
			members.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("members", members);
		try {
			((Window) Executions.createComponents("/member/memberDelete.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("");
			return;
		}

		Object member = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("member", member);

		try {
			((Window) Executions.createComponents("/member/memberEdit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/member/memberAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
