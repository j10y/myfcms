/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 29, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.window;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.web.intercepter.Authenticatable;
import com.hxzy.base.web.intercepter.Authorizable;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.log.service.LogService;
import com.hxzy.common.user.model.UserInfo;

/**
 * @author xiacc
 * 
 * ���������ӻ����޸ĵ������ڻ��࣬�����޸Ĵ��ڼ̳и���
 */
public abstract class ActionWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * ����: �ύ��ť
	 */
	protected Button submit;

	/**
	 * ����: ȡ����ť
	 */
	protected Button cancel;

	/**
	 * ���������ݰ���
	 */
	protected AnnotateDataBinder binder;

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
	
	@Autowired
	protected LogService logService; 
	
	protected Log log;

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
		logService = (LogService) SpringUtil.getBean("logService");
		
		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		this.setClosable(true);

		submit.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {							
				onSubmit();				
				logging();				
				ActionWindow.this.detach();
			}
		});

		cancel.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ActionWindow.this.detach();
			}

		});

		this.addEventListener("onOK", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
				logging();
				ActionWindow.this.detach();
			}
		});

		this.addEventListener("onCancel", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ActionWindow.this.detach();
			}
		});

		// ������
		onBind();
	}

	public abstract void onBind();

	public abstract void onSubmit();
	
	public abstract String toString();
	
	protected void logging(){
		log = new Log();
		log.setIp(Executions.getCurrent().getRemoteAddr());
		UserInfo userInfo = (UserInfo)Executions.getCurrent().getSession().getAttribute(Constant.ATTRIBUTE_USER_INFO);
		if(userInfo != null){
			log.setUsername(userInfo.getUser().getTruename());
		}
		log.setLogAction(ActionWindow.this.getClass().getSimpleName());
		log.setLogTime(new Date());	
		String detail = ActionWindow.this.toString();
		if(detail != null){
			log.setDetail(detail);
			logService.save(log);
		}
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

	/**
	 * ���� submit
	 */
	public Button getSubmit() {
		return submit;
	}

	/**
	 * ���� submit
	 */
	public void setSubmit(Button submit) {
		this.submit = submit;
	}

	/**
	 * ���� cancel
	 */
	public Button getCancel() {
		return cancel;
	}

	/**
	 * ���� cancel
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

}
