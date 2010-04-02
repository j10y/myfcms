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

import com.hxzy.base.web.intercepter.Authenticatable;
import com.hxzy.base.web.intercepter.Authorizable;

/**
 * @author xiacc
 * 
 * 描述：增加或者修改弹出窗口基类，所有修改窗口继承该类
 */
public abstract class ActionWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * 描述: 数据绑定器
	 */
	protected AnnotateDataBinder binder;

	/**
	 * 描述: 提交按钮
	 */
	protected Button submit;
	
	/**
	 * 描述: 取消按钮
	 */
	protected Button cancel;

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
		
		this.setClosable(true);

		submit.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
			}
		});
		
		cancel.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ActionWindow.this.onClose();
			}
			
		});
		
		this.addEventListener("onOK", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
			}
		});
		
		this.addEventListener("onCancel", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ActionWindow.this.onClose();
			}
		});

		// 绑定数据
		onBind();		
	}
	
	

	public abstract void onBind();

	public abstract void onSubmit();

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
