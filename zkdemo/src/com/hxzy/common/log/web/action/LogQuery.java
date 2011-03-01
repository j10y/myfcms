package com.hxzy.common.log.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Window;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.log.service.LogService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 
 */

public class LogQuery extends ListWindow {

	@Autowired
	private LogService logService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Log.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(
					Restrictions.like("username", search.getValue(),MatchMode.ANYWHERE),			
					Restrictions.like("ip", search.getValue(),MatchMode.ANYWHERE));						
			LogicalExpression l2 = Restrictions.or(
					l1, 
					Restrictions.like("logAction", search.getValue(), MatchMode.ANYWHERE));			
			LogicalExpression l3 = Restrictions.or(
					l2, 
					Restrictions.like("detail", search.getValue(), MatchMode.ANYWHERE));			
			detachedCriteria.add(l3);			
		}
		
		detachedCriteria.addOrder(Order.desc("id"));
		
		Pagination pagination = logService.findPageByCriteria(detachedCriteria, pg.getPageSize(),
				pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onDelete() {		
		try {
			((Window) Executions.createComponents("/log/logDelete.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
