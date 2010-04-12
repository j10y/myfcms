/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.component;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Textbox;

/**
 * @author xiacc
 *
 * 描述：
 */
public class Searchbox extends Textbox {

	private String defaultValue;
	
	public void onCreate(){
		
		this.setValue(this.defaultValue);
		this.setStyle("");
		
		this.addEventListener("onFocus", new EventListener(){

			public void onEvent(Event evt) throws Exception {
				if(Searchbox.this.getValue().equals(defaultValue)){
					Searchbox.this.setValue("");
				}				
			}			
		});
		
		this.addEventListener("onBlur", new EventListener(){

			public void onEvent(Event evt) throws Exception {
				if(Searchbox.this.getValue().equals("")){
					Searchbox.this.setValue(defaultValue);
				}				
			}			
		});
	}
}
