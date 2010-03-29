/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 29, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.window;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

/**
 * @author xiacc
 * 
 * 描述：增加或者修改弹出窗口基类，所有修改窗口继承该类
 */
public abstract class ActionWindow extends Window implements AfterCompose {

	protected AnnotateDataBinder binder;

	protected Button submit;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.ext.AfterCompose#afterCompose()
	 */
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		submit.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
			}
		});
		
		//绑定数据
		onBind();
	}
	
	public abstract void onBind();

	public abstract void onSubmit();

}
