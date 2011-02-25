
package com.hxzy.base.util;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * ����: ApplicationParameter
 * </p>
 * <p>
 * ����: Ӧ�ó��������
 * </p>
 */
public class ApplicationParameter implements Serializable {

	/**
	 * ����:
	 */
	private static final long serialVersionUID = 0L;

	/**
	 * ����: Ӧ�ó������map
	 */
	private Map parameters;

	/**
	 * ����: ���ݲ�������ѯ����
	 * 
	 * @param name
	 *            ������
	 * @return ����
	 */
	public Object findParameterByName(String name) {
		return parameters.get(name);
	}

	/**
	 * ����: ���� parameters
	 */
	public Map getParameters() {
		return parameters;
	}

	/**
	 * ����: ���� parameters
	 */
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}
