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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class UserQuery extends ListWindow {
	
	@Autowired
	private UserService userService;
	
	private Textbox search;

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);

		if(StringUtils.hasText(search.getValue())){
			detachedCriteria.add(
					Restrictions.or(
							Restrictions.like("username",search.getValue(),MatchMode.ANYWHERE),
							Restrictions.like("truename",search.getValue(),MatchMode.ANYWHERE)
					));
			
			detachedCriteria.add(
					Restrictions.or(
							Restrictions.like("password",search.getValue(),MatchMode.ANYWHERE),
							Restrictions.like("username",search.getValue(),MatchMode.ANYWHERE)
					));
		}
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, pg.getPageSize(), pg.getActivePage()+1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;		
		binder.loadComponent(listbox);

	}
	
	public void onDelete(){
		if(listbox.getSelectedItem() == null){
			try {
				Messagebox.show("请至少选择一个数据");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		
		Set<Listitem> items = listbox.getSelectedItems();
		Set users = new HashSet();
		for(Listitem item:items){
			users.add(item.getValue());
		}
		
		Map map = new HashMap();
		map.put("users",users);
		try {
			((UserDelete)Executions.createComponents("userDelete.zul", UserQuery.this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}
