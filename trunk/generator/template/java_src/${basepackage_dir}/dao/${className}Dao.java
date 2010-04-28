<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package com.hxzy.common.${classNameLower}.dao;

import com.hxzy.base.dao.BaseDao;
import ${basepackage}.${classNameLower}.model.${className};

<#include "/java_imports.include">
public interface ${className}Dao extends BaseDao<${className}> {

}
