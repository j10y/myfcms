package com.sunflower.zkDemo.web.zk.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class SexConverter implements TypeConverter {
	// 视图到bean的转换
	public Object coerceToBean(Object arg0, Component arg1) {
		return null;
	}

	// bean到视图的转换
	public Object coerceToUi(Object intSex, Component arg1) {
		if (intSex instanceof Integer) {
			int sex = (Integer) intSex;
			if (sex == 0)
				return "女";
			else
				return "男";
		}
		return null;
	}

}
