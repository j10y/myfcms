/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�May 14, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.component;

import java.util.Set;

import org.zkoss.zul.Textbox;

/**
 * @author xiacc
 *
 * ���������������
 */
public class StextBox extends Textbox {
	
	private Set values;
	
	public void onCreate(){
		this.setReadonly(true);
	}

	/**
	 * ���� values
	 */
	public Set getValues() {
		return values;
	}

	/**
	 * ���� values
	 */
	public void setValues(Set values) {
		this.values = values;
		StringBuilder sb = new StringBuilder();
		for(Object o:values){
			sb.append(o);
			sb.append(";");
		}
		this.setValue(sb.toString());
	}
	
	public void addValue(Object o){
		this.values.add(o);
		
		StringBuilder sb = new StringBuilder();
		for(Object obj:values){
			sb.append(obj);
			sb.append(";");
		}	
		this.setValue(sb.toString());
	}	
}
