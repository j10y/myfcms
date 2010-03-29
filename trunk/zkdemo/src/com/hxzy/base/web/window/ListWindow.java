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
 * ��������ѯ�б��ڻ��࣬���еĲ�ѯ���ڼ̳и���
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
	 * ���� listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}



	/**
	 * ���� listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}



	/**
	 * ���� list
	 */
	public List getList() {
		return list;
	}

	/**
	 * ���� list
	 */
	public void setList(Pagination list) {
		this.list = list;
	}

	/**
	 * ���� binder
	 */
	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}
}
