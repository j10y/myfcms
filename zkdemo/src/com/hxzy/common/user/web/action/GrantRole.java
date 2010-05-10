/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 9, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.RoleService;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：用户角色授予类
 */
public class GrantRole extends ActionWindow {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	private User user = (User) Executions.getCurrent().getArg().get("user");

	private Label username;

	private Listbox listbox;

	private Paging pg;

	private List roles;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		username.setValue(user.getUsername());

		user = userService.loadById(user.getId());

		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		pg.addEventListener(org.zkoss.zul.event.ZulEvents.ON_PAGING, new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				onFind();
			}

		});

		listbox.setItemRenderer(new ListitemRenderer() {

			public void render(Listitem item, Object o) throws Exception {
				Role r = (Role) o;

				item.appendChild(new Listcell(r.getRoleName()));
				item.appendChild(new Listcell(r.getRemarks()));
				item.setValue(r);

				if (user.getRoles().contains(r)) {
					item.setSelected(true);
				}
			}

		});

		onFind();
	}

	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);

		// if(StringUtils.hasText(search.getValue())){
		// detachedCriteria.add(Restrictions.eq("roleName", search.getValue()));
		// }
		Pagination pagination = roleService.findPageByCriteria(detachedCriteria, pg.getPageSize(),
				pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.roles = pagination;
		binder.loadComponent(listbox);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Set<Listitem> items = listbox.getSelectedItems();

		Set<Role> roleSet = new HashSet<Role>();

		for (Listitem item : items) {
			roleSet.add((Role) item.getValue());
		}

		user.setRoles(roleSet);

		userService.update(user);
	}

	/**
	 * 返回 roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * 设置 roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 返回 userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 设置 userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 返回 user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置 user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 返回 username
	 */
	public Label getUsername() {
		return username;
	}

	/**
	 * 设置 username
	 */
	public void setUsername(Label username) {
		this.username = username;
	}

	/**
	 * 返回 listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}

	/**
	 * 设置 listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}

	/**
	 * 返回 pg
	 */
	public Paging getPg() {
		return pg;
	}

	/**
	 * 设置 pg
	 */
	public void setPg(Paging pg) {
		this.pg = pg;
	}

	/**
	 * 返回 roles
	 */
	public List getRoles() {
		return roles;
	}

	/**
	 * 设置 roles
	 */
	public void setRoles(List roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {

		Set<Listitem> items = listbox.getSelectedItems();

		StringBuilder sb = new StringBuilder();

		for (Listitem item : items) {
			Role r = (Role) item.getValue();
			sb.append(r.getRoleName());
			sb.append(",");
		}

		return "授予角色" + sb.toString() + "给" + user.getUsername();
	}

}
