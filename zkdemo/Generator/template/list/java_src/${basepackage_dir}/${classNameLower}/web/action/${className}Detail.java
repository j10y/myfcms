<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.web.action;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import ${basepackage}.${classNameLower}.model.${className};
import com.hxzy.base.web.window.ActionWindow;

<#include "/java_imports.include">
public class ${className}Detail extends ActionWindow {
	
	private ${className} ${classNameLower} = (${className}) Executions.getCurrent().getArg().get("${classNameLower}");

	private Grid grid;
	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		binder.loadComponent(grid);
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		// TODO Auto-generated method stub

	}

	/**
	 * ���� ${classNameLower}
	 */
	public ${className} get${className}() {
		return ${classNameLower};
	}

	/**
	 * ���� ${classNameLower}
	 */
	public void set${className}(${className} ${classNameLower}) {
		this.${classNameLower} = ${classNameLower};
	}

	/**
	 * ���� grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * ���� grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	

}
