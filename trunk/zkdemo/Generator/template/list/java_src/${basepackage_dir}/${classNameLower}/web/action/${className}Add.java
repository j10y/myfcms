<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.web.action;

import java.util.List;

import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
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
public class ${className}Add extends ActionWindow {

	@Autowired
	private ${className}Service ${classNameLower}Service;

	private ${className} ${classNameLower};
	
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
		${classNameLower} = new ${className}();
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

		${classNameLower}Service.save(${classNameLower});

		((ListWindow) this.getParent()).onFind();
	}
	
	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "add"+${classNameLower};
	}

}
