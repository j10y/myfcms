/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 31, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
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
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：用户查询
 */
public class UserQuery extends ListWindow {

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);

		if (StringUtils.hasText(search.getValue())) {
			detachedCriteria.add(Restrictions.or(Restrictions.like("username", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("truename", search.getValue(),
					MatchMode.ANYWHERE)));

			detachedCriteria.add(Restrictions.or(Restrictions.like("password", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("username", search.getValue(),
					MatchMode.ANYWHERE)));
		}
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, pg.getPageSize(),
				pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onDelete() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Set<Listitem> items = listbox.getSelectedItems();
		Set users = new HashSet();
		for (Listitem item : items) {
			users.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("users", users);
		try {
			((Window) Executions.createComponents("/user/userDelete.zul", UserQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object user = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("user", user);

		try {
			((Window) Executions.createComponents("/user/userEdit.zul", UserQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onLock() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}
		Set<Listitem> items = listbox.getSelectedItems();
		for (Listitem item : items) {
			User user = (User) item.getValue();
			user.setLocked(!user.getLocked());

			userService.update(user);
		}
		this.onFind();
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/user/userAdd.zul", UserQuery.this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void onGrantRole(){
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object user = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("user", user);

		try {
			((Window) Executions.createComponents("/user/grantRole.zul", UserQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
