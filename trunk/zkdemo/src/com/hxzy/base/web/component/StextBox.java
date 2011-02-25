/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：May 14, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.component;

import java.util.Set;

import org.zkoss.zul.Textbox;

/**
 * @author xiacc
 *
 * 描述：集合输入框
 */
public class StextBox extends Textbox {
	
	private Set values;
	
	public void onCreate(){
		this.setReadonly(true);
	}

	/**
	 * 返回 values
	 */
	public Set getValues() {
		return values;
	}

	/**
	 * 设置 values
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
