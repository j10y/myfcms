<#include "/java_copyright.include">
<#assign className = table.className>   
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
	<#list table.columns as column>
		<#if column.isNotIdOrVersionField>
			<#if column_index == 1>
			LogicalExpression l1 = Restrictions.or(
					Restrictions.like("${column.columnNameLower}", search.getValue(),MatchMode.ANYWHERE),			
			<#elseif column_index == 2>
					Restrictions.like("${column.columnNameLower}", search.getValue(),MatchMode.ANYWHERE));
			<#else>
			LogicalExpression l${column_index} = Restrictions.or(
					l${column_index -1}, 
					Restrictions.like("${column.columnNameLower}", search.getValue(), MatchMode.ANYWHERE));
			<#assign n = column_index >
			</#if>
		</#if>
	</#list>
			
			detachedCriteria.add(l${n});
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
	
}
