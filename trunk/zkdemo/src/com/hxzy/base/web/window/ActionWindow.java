/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 29, 2010</p>
 * <p>���£�</p>
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
 * ���������ӻ����޸ĵ������ڻ��࣬�����޸Ĵ��ڼ̳и���
 */
public abstract class ActionWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * ����: ���ݰ���
	 */
	protected AnnotateDataBinder binder;

	/**
	 * ����: �ύ��ť
	 */
	protected Button submit;
	
	/**
	 * ����: ȡ����ť
	 */
	protected Button cancel;

	/**
	 * ����: ���ܴ���
	 */
	private String functionCode;

	/**
	 * ����: �Ƿ���Ҫ�����֤
	 */
	private String needAuthentication;

	/**
	 * ����: �Ƿ���Ҫ��Ȩ
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

		// ������
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
	 * ���� functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * ���� functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * ���� needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * ���� needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * ���� needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * ���� needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}

}
