/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 1, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

/**
 * @author xiacc
 *
 * ������
 */
public class LockConverter implements TypeConverter{

	/* (non-Javadoc)
	 * @see org.zkoss.zkplus.databind.TypeConverter#coerceToBean(java.lang.Object, org.zkoss.zk.ui.Component)
	 */
	public Object coerceToBean(Object arg0, Component arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zkplus.databind.TypeConverter#coerceToUi(java.lang.Object, org.zkoss.zk.ui.Component)
	 */
	public Object coerceToUi(Object o, Component arg1) {
		if (o instanceof Boolean) {
			Boolean locked = (Boolean) o;
			if (locked)
				return "����";
			else
				return "����";
		}
		return null;
	}

}
