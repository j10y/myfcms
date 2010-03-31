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
import com.hxzy.base.web.intercepter.Authenticatable;
import com.hxzy.base.web.intercepter.Authorizable;

/**
 * @author xiacc
 * 
 * 描述：查询列表窗口基类，所有的查询窗口继承该类
 */
public abstract class ListWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * 描述：列表控件
	 */
	protected Listbox listbox;

	/**
	 * 描述：页面显示的列表
	 */
	protected Pagination list;

	/**
	 * 描述：数据绑定器
	 */
	protected AnnotateDataBinder binder;

	/**
	 * 描述：翻页器
	 */
	protected Paging pg;

	/**
	 * 描述: 功能代码
	 */
	private String functionCode;

	/**
	 * 描述: 是否需要身份验证
	 */
	private String needAuthentication;

	/**
	 * 描述: 是否需要授权
	 */
	private String needAuthorization;

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
		pg.addEventListener(org.zkoss.zul.event.ZulEvents.ON_PAGING, new EventListener() {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.intercepter.Authenticatable#needAuthentication()
	 */
	public boolean needAuthentication() {
		if ("false".equals(needAuthentication))
			return false;
		else
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.intercepter.Authorizable#needAuthorization()
	 */
	public boolean needAuthorization() {
		if ("false".equals(needAuthorization))
			return false;
		else
			return true;
	}

	/**
	 * 返回 functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * 设置 functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * 返回 needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * 设置 needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * 返回 needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * 设置 needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}
	
	
}
