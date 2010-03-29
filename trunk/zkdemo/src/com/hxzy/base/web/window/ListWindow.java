package com.hxzy.base.web.window;


import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;

import com.hxzy.base.util.Pagination;

/**
 * @author xiacc
 *
 * 描述：查询列表窗口基类，所有的查询窗口继承该类
 */
public abstract class ListWindow extends Window implements AfterCompose {
	
	protected Listbox listbox;
	
	protected Pagination list;
	
	protected AnnotateDataBinder binder;
	
	protected Paging pg;
	
	

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.ext.AfterCompose#afterCompose()
	 */
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}
	
	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);
		pg.addEventListener(org.zkoss.zul.event.ZulEvents.ON_PAGING, new EventListener(){

			public void onEvent(Event arg0) throws Exception {
				onFind();
			}
			
		});
	}
	
	public abstract void onFind();

	/**
	 * 返回 listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}



	/**
	 * 设置 listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}



	/**
	 * 返回 list
	 */
	public List getList() {
		return list;
	}

	/**
	 * 设置 list
	 */
	public void setList(Pagination list) {
		this.list = list;
	}

	/**
	 * 设置 binder
	 */
	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}
}
