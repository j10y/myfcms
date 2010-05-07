<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.web.action;

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
import ${basepackage}.${classNameLower}.model.${className};
import ${basepackage}.${classNameLower}.service.${className}Service;

<#include "/java_imports.include">
public class ${className}Query extends ListWindow {

	@Autowired
	private ${className}Service ${classNameLower}Service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(${className}.class);

		if (StringUtils.hasText(search.getValue())) {
			<#assign n = 1 >
		<#list clazz.fields as field>
		<#if field.fieldName != "id">
			<#if n == 1>
			LogicalExpression l1 = Restrictions.or(
					Restrictions.like("${field.fieldName}", search.getValue(),MatchMode.ANYWHERE),			
			<#elseif n == 2>
					Restrictions.like("${field.fieldName}", search.getValue(),MatchMode.ANYWHERE));
			<#else>
			LogicalExpression l${n-1} = Restrictions.or(
					l${n-2}, 
					Restrictions.like("${field.fieldName}", search.getValue(), MatchMode.ANYWHERE));			
			</#if>
			<#assign n = n+1 >
		</#if>
		</#list>
			
			detachedCriteria.add(l${n-2});
		}
		Pagination pagination = ${classNameLower}Service.findPageByCriteria(detachedCriteria, pg.getPageSize(),
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
		Set ${classNameLower}s = new HashSet();
		for (Listitem item : items) {
			${classNameLower}s.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("${classNameLower}s", ${classNameLower}s);
		try {
			((Window) Executions.createComponents("/${classNameLower}/${classNameLower}Delete.zul", this, map)).doModal();
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

		Object ${classNameLower} = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("${classNameLower}", ${classNameLower});

		try {
			((Window) Executions.createComponents("/${classNameLower}/${classNameLower}Edit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/${classNameLower}/${classNameLower}Add.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void onDetail() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object o = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("${classNameLower}", o);

		try {
			((Window) Executions.createComponents("/${classNameLower}/${classNameLower}Detail.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
