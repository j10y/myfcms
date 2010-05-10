/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 9, 2010</p>
 * <p>���£�</p>
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
 * �������û���ɫ������
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
	 * ���� roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * ���� roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * ���� userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * ���� userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ���� user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * ���� user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * ���� username
	 */
	public Label getUsername() {
		return username;
	}

	/**
	 * ���� username
	 */
	public void setUsername(Label username) {
		this.username = username;
	}

	/**
	 * ���� listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}

	/**
	 * ���� listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}

	/**
	 * ���� pg
	 */
	public Paging getPg() {
		return pg;
	}

	/**
	 * ���� pg
	 */
	public void setPg(Paging pg) {
		this.pg = pg;
	}

	/**
	 * ���� roles
	 */
	public List getRoles() {
		return roles;
	}

	/**
	 * ���� roles
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

		return "�����ɫ" + sb.toString() + "��" + user.getUsername();
	}

}
