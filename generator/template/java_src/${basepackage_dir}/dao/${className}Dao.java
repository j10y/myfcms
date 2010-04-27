<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${classNameLower}.dao;

import ${basepackage}.${classNameLower}.model.${className};
import com.hxzy.base.dao.BaseDao;

<#include "/java_imports.include">
public interface ${className}Dao extends BaseDao<${className}> {

}
