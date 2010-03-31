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
 * ��������ѯ�б��ڻ��࣬���еĲ�ѯ���ڼ̳и���
 */
public abstract class ListWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * �������б�ؼ�
	 */
	protected Listbox listbox;

	/**
	 * ������ҳ����ʾ���б�
	 */
	protected Pagination list;

	/**
	 * ���������ݰ���
	 */
	protected AnnotateDataBinder binder;

	/**
	 * ��������ҳ��
	 */
	protected Paging pg;

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
		pg.addEventListener(org.zkoss.zul.event.ZulEvents.ON_PAGING, new EventListener() {

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
