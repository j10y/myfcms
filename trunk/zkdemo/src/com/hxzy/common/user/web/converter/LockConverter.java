/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 1, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

/**
 * @author xiacc
 *
 * 描述：
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
				return "锁定";
			else
				return "正常";
		}
		return null;
	}

}
