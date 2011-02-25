
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
