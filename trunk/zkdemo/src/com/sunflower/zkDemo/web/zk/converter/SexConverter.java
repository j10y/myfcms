package com.sunflower.zkDemo.web.zk.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class SexConverter implements TypeConverter {
	// ��ͼ��bean��ת��
	public Object coerceToBean(Object arg0, Component arg1) {
		return null;
	}

	// bean����ͼ��ת��
	public Object coerceToUi(Object intSex, Component arg1) {
		if (intSex instanceof Integer) {
			int sex = (Integer) intSex;
			if (sex == 0)
				return "Ů";
			else
				return "��";
		}
		return null;
	}

}
