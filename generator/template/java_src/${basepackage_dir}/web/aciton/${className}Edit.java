<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import ${basepackage}.${classNameLower}.model.${className};
import ${basepackage}.${classNameLower}.service.${className}Service;

<#include "/java_imports.include">
public class ${className}Edit extends ActionWindow {

	@Autowired
	private ${className}Service ${classNameLower}Service;

	private ${className} ${classNameLower} = (${className}) Executions.getCurrent().getArg().get("${classNameLower}");

	<#list table.columns as column>
	<#if column.isNotIdOrVersionField>
	private Textbox	${column.columnNameLower};
	</#if>
	</#list>

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		if (${classNameLower} != null) {
			<#list table.columns as column>
			<#if column.isNotIdOrVersionField>
			${column.columnNameLower}.setValue(${classNameLower}.get${column.columnName}());	
			</#if>
			</#list>
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		<#list table.columns as column>s
		<#if column.isNotIdOrVersionField>
		${classNameLower}.set${column.columnName}(${column.columnNameLower}.getValue());
		</#if>
		</#list>

		${classNameLower}Service.update(${classNameLower});

		 ((ListWindow) this.getParent()).onFind();
	}

}
