/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-20</p>
 * <p>更新：</p>
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
 * 类名: BaseController
 * </p>
 * <p>
 * 描述: 应用程序的AbstractController的基类，所有需具备AbstractController 功能的类都继承于此类
 * </p>
 */
public class BaseController extends AbstractController implements
		Authenticatable, Authorizable, RequestUrlPreparable {

	/**
     * 描述: 操作日志Manager
     */
    protected LogService logService;   

	/**
	 * 描述: 功能代码
	 */
	private String functionCode;

	/**
	 * 描述: 是否需要进行用户身份认证
	 */
	private String needAuthentication;

	/**
	 * 描述: 是否需要进行权限认证
	 */
	private String needAuthorization;

	/**
	 * 描述: 是否需要将当前页面的Reuquest url放入request属性（包含查询参数）
	 */
	private String needRequestUrl;

	/**
	 * 描述: 应用程序参数
	 */
	private ApplicationParameter applicationParameter;

	/**
	 * 描述: 前台是否需要进行用户身份认证
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
	 * 描述: 获取字符串资源
	 * 
	 * @param code
	 *            资源代码
	 * @param request
	 *            request对象
	 * @return 字符串资源
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
	 * 描述: 获取当前操作用户信息
	 * 
	 * @param request
	 *            request对象
	 * @return 当前操作用户信息
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
	 * 描述: 返回 needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * 描述: 设置 needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * 描述: 返回 needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * 描述: 设置 needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}

	/**
	 * 描述: 返回 needRequestUrl
	 */
	public String getNeedRequestUrl() {
		return needRequestUrl;
	}

	/**
	 * 描述: 设置 needRequestUrl
	 */
	public void setNeedRequestUrl(String needRequestUrl) {
		this.needRequestUrl = needRequestUrl;
	}

	/**
	 * 描述: 返回 applicationParameter
	 */
	public ApplicationParameter getApplicationParameter() {
		return applicationParameter;
	}

	/**
	 * 描述: 设置 applicationParameter
	 */
	public void setApplicationParameter(
			ApplicationParameter applicationParameter) {
		this.applicationParameter = applicationParameter;
	}


	/**
	 * 描述: 返回 needOutAuthentication
	 */
	public String getNeedOutAuthentication() {
		return needOutAuthentication;
	}

	/**
	 * 描述: 设置 needOutAuthentication
	 */
	public void setNeedOutAuthentication(String needOutAuthentication) {
		this.needOutAuthentication = needOutAuthentication;
	}

	/**
	 * 描述: 返回 functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * 描述: 设置 functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * 返回 logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * 设置 logService
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
}