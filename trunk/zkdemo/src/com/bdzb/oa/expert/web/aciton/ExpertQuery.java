/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.expert.web.aciton;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Window;

import com.bdzb.oa.expert.model.Expert;
import com.bdzb.oa.expert.service.ExpertService;
import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;

/**
 * @author xiacc
 * 
 * ������
 */
public class ExpertQuery extends ListWindow {

	@Autowired
	private ExpertService expertService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Expert.class);

		if (StringUtils.hasText(search.getValue())) {
			detachedCriteria.add(Restrictions.or(Restrictions.like("name", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("titles", search.getValue(),
					MatchMode.ANYWHERE)));

			detachedCriteria.add(Restrictions.or(Restrictions.like("department", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("telephone", search.getValue(),
					MatchMode.ANYWHERE)));
		}
		Pagination pagination = expertService.findPageByCriteria(detachedCriteria,
				pg.getPageSize(), pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/expert/expertAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
