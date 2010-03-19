/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-22</p>
 * <p>更新：</p>
 */
package com.hxzy.base.util;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 类名: ApplicationParameter
 * </p>
 * <p>
 * 描述: 应用程序参数类
 * </p>
 */
public class ApplicationParameter implements Serializable {

	/**
	 * 描述:
	 */
	private static final long serialVersionUID = 0L;

	/**
	 * 描述: 应用程序参数map
	 */
	private Map parameters;

	/**
	 * 描述: 根据参数名查询参数
	 * 
	 * @param name
	 *            参数名
	 * @return 参数
	 */
	public Object findParameterByName(String name) {
		return parameters.get(name);
	}

	/**
	 * 描述: 返回 parameters
	 */
	public Map getParameters() {
		return parameters;
	}

	/**
	 * 描述: 设置 parameters
	 */
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}
