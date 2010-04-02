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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.base.web.window.Message;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 *
 * 描述：角色查询
 */
public class RoleQuery extends ListWindow {
	
	@Autowired
	private RoleService roleService;
	
	private Textbox search;
	
	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ListWindow#onCreate()
	 */
	@Override
	public void onCreate() {		
		super.onCreate();
		
		this.addEventListener("onOK", new EventListener(){
			public void onEvent(Event arg0) throws Exception {
				onFind();
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);

		if(StringUtils.hasText(search.getValue())){
			detachedCriteria.add(Restrictions.eq("roleName", search.getValue()));
		}
		Pagination pagination = roleService.findPageByCriteria(detachedCriteria, pg.getPageSize(), pg.getActivePage()+1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;		
		binder.loadComponent(listbox);
	}
	
	public void onDelete(){
		if(listbox.getSelectedItem() == null){
			Message.showInfo("请至少选择一个数据!");
			return;
		}
		
		Set<Listitem> items = listbox.getSelectedItems();
		Set values = new HashSet();
		for(Listitem item:items){
			values.add(item.getValue());
		}
		
		Map map = new HashMap();
		map.put("roles",values);
		try {
			((Window)Executions.createComponents("roleDelete.zul", RoleQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public void onEdit(){
		if(listbox.getSelectedItem() == null){
			Message.showInfo("请至少选择一个数据!");
			return;
		}
		
		Object value = listbox.getSelectedItem().getValue();
		
		Map map = new HashMap();
		map.put("role",value);
		
		try {
			((Window)Executions.createComponents("roleEdit.zul", RoleQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	public void onAdd(){
		try {
			((Window)Executions.createComponents("roleAdd.zul", RoleQuery.this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
