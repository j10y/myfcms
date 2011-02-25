package com.hxzy.common.fileAttach.web.action;

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
import com.hxzy.common.fileAttach.model.FileAttach;
import com.hxzy.common.fileAttach.service.FileAttachService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 
 */

public class FileAttachQuery extends ListWindow {

	@Autowired
	private FileAttachService fileAttachService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FileAttach.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(
					Restrictions.like("originalName", search.getValue(),MatchMode.ANYWHERE),			
					Restrictions.like("fileName", search.getValue(),MatchMode.ANYWHERE));
			LogicalExpression l2 = Restrictions.or(
					l1, 
					Restrictions.like("filePath", search.getValue(), MatchMode.ANYWHERE));			
			LogicalExpression l3 = Restrictions.or(
					l2, 
					Restrictions.like("createtime", search.getValue(), MatchMode.ANYWHERE));			
			LogicalExpression l4 = Restrictions.or(
					l3, 
					Restrictions.like("ext", search.getValue(), MatchMode.ANYWHERE));			
			LogicalExpression l5 = Restrictions.or(
					l4, 
					Restrictions.like("creator", search.getValue(), MatchMode.ANYWHERE));			
			
			detachedCriteria.add(l5);
		}
		Pagination pagination = fileAttachService.findPageByCriteria(detachedCriteria, pg.getPageSize(),
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
		Set fileAttachs = new HashSet();
		for (Listitem item : items) {
			fileAttachs.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("fileAttachs", fileAttachs);
		try {
			((Window) Executions.createComponents("/fileAttach/fileAttachDelete.zul", this, map)).doModal();
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

		Object fileAttach = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("fileAttach", fileAttach);

		try {
			((Window) Executions.createComponents("/fileAttach/fileAttachEdit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/fileAttach/fileAttachAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void onDetail() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("������ѡ��һ�����!");
			return;
		}

		Object o = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("fileAttach", o);

		try {
			((Window) Executions.createComponents("/fileAttach/fileAttachDetail.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
