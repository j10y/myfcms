<#include "/java_copyright.include">
<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.dao.impl;

import ${basepackage}.${classNameLower}.dao.${className}Dao;
import ${basepackage}.${classNameLower}.model.${className};
import com.hxzy.base.dao.impl.BaseDaoImpl;

<#include "/java_imports.include">

public class ${className}DaoImpl extends BaseDaoImpl<${className}> implements ${className}Dao {

}
