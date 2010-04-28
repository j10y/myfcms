<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.${classNameLower}.dao.${className}Dao;
import ${basepackage}.${classNameLower}.model.${className};
import ${basepackage}.${classNameLower}.service.${className}Service;
import com.hxzy.base.service.impl.BaseServiceImpl;

<#include "/java_imports.include">
public class ${className}ServiceImpl extends BaseServiceImpl<${className},${className}Dao> implements ${className}Service {
	
	@Autowired
	private ${className}Dao ${classNameLower}Dao;

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected ${className}Dao getDao() {
		return ${classNameLower}Dao;
	}

	public ${className}Dao get${className}Dao() {
		return ${classNameLower}Dao;
	}

	public void set${className}Dao(${className}Dao ${classNameLower}Dao) {
		this.${classNameLower}Dao = ${classNameLower}Dao;
	}

	
}
