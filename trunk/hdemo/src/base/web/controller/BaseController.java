/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-20</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hxzy.base.util.ApplicationParameter;
import com.hxzy.base.util.WebAppUtil;
import com.hxzy.base.web.interceptor.Authenticatable;
import com.hxzy.base.web.interceptor.Authorizable;
import com.hxzy.base.web.interceptor.RequestUrlPreparable;
import com.hxzy.common.log.service.LogService;
import com.hxzy.common.user.model.UserInfo;


/**
 * <p>
 * ����: BaseController
 * </p>
 * <p>
 * ����: Ӧ�ó����AbstractController�Ļ��࣬������߱�AbstractController ���ܵ��඼�̳��ڴ���
 * </p>
 */
public class BaseController extends AbstractController implements
		Authenticatable, Authorizable, RequestUrlPreparable {

	/**
     * ����: ������־Manager
     */
    protected LogService logService;   

	/**
	 * ����: ���ܴ���
	 */
	private String functionCode;

	/**
	 * ����: �Ƿ���Ҫ�����û������֤
	 */
	private String needAuthentication;

	/**
	 * ����: �Ƿ���Ҫ����Ȩ����֤
	 */
	private String needAuthorization;

	/**
	 * ����: �Ƿ���Ҫ����ǰҳ���Reuquest url����request���ԣ�������ѯ������
	 */
	private String needRequestUrl;

	/**
	 * ����: Ӧ�ó������
	 */
	private ApplicationParameter applicationParameter;

	/**
	 * ����: ǰ̨�Ƿ���Ҫ�����û������֤
	 */
	private String needOutAuthentication;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * ����: ��ȡ�ַ�����Դ
	 * 
	 * @param code
	 *            ��Դ����
	 * @param request
	 *            request����
	 * @return �ַ�����Դ
	 */
	protected String getMessage(String code, HttpServletRequest request) {
		return this.getApplicationContext().getMessage(code, null,
				"no message", request.getLocale());
	}

	/*
	 * @see ddyx.web.interceptor.Authenticatable#needAuthentication()
	 */
	public boolean needAuthentication() {
		if ("false".equals(needAuthentication))
			return false;
		else
			return true;
	}

	/*
	 * @see ddyx.web.interceptor.Authorizable#needAuthorization()
	 */
	public boolean needAuthorization() {
		if ("false".equals(needAuthorization))
			return false;
		else
			return true;
	}

	/*
	 * @see ddyx.web.interceptor.RequestUrlPreparable#needRequestUrl()
	 */
	public boolean needRequestUrl() {
		if ("true".equals(needRequestUrl))
			return true;
		else
			return false;
	}

	public boolean needOutAuthentication() {
		if ("false".equals(needOutAuthentication))
			return false;
		else
			return true;
	}

	/**
	 * ����: ��ȡ��ǰ�����û���Ϣ
	 * 
	 * @param request
	 *            request����
	 * @return ��ǰ�����û���Ϣ
	 */
	protected UserInfo getCurrentUserInfo(HttpServletRequest request) {
		return (UserInfo) WebAppUtil.getUserInfo(request);
	}

	/*
	 * @see com.quanda.common.web.interceptor.Authorizable#authorize()
	 */
	public boolean authorize() {
		return false;
	}

	/**
	 * ����: ���� needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * ����: ���� needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * ����: ���� needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * ����: ���� needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}

	/**
	 * ����: ���� needRequestUrl
	 */
	public String getNeedRequestUrl() {
		return needRequestUrl;
	}

	/**
	 * ����: ���� needRequestUrl
	 */
	public void setNeedRequestUrl(String needRequestUrl) {
		this.needRequestUrl = needRequestUrl;
	}

	/**
	 * ����: ���� applicationParameter
	 */
	public ApplicationParameter getApplicationParameter() {
		return applicationParameter;
	}

	/**
	 * ����: ���� applicationParameter
	 */
	public void setApplicationParameter(
			ApplicationParameter applicationParameter) {
		this.applicationParameter = applicationParameter;
	}


	/**
	 * ����: ���� needOutAuthentication
	 */
	public String getNeedOutAuthentication() {
		return needOutAuthentication;
	}

	/**
	 * ����: ���� needOutAuthentication
	 */
	public void setNeedOutAuthentication(String needOutAuthentication) {
		this.needOutAuthentication = needOutAuthentication;
	}

	/**
	 * ����: ���� functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * ����: ���� functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * ���� logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * ���� logService
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
}