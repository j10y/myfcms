<#assign className = clazz.className>   
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

<#list clazz.fields as field>
<#if field.fieldName != "id">
	private Textbox	${field.fieldName};
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
			<#list clazz.fields as field>
			<#if field.fieldName != "id">
			${field.fieldName}.setValue(${classNameLower}.get${field.fieldName?cap_first}());	
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
	<#list clazz.fields as field>
		<#if field.fieldName != "id">
		${classNameLower}.set${field.fieldName?cap_first}(${field.fieldName}.getValue());
		</#if>
	</#list>

		${classNameLower}Service.update(${classNameLower});

		 ((ListWindow) this.getParent()).onFind();
	}

}
