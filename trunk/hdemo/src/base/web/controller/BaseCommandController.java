package com.hxzy.base.web.controller;

/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-20</p>
 * <p>更新：</p>
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.hxzy.base.util.ApplicationParameter;
import com.hxzy.base.util.DateUtil;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.util.WebAppUtil;
import com.hxzy.base.web.interceptor.Authenticatable;
import com.hxzy.base.web.interceptor.Authorizable;
import com.hxzy.base.web.interceptor.RequestUrlPreparable;
import com.hxzy.common.log.service.LogService;
import com.hxzy.common.user.model.UserInfo;


/**
 * <p>
 * 类名: BaseCommandController
 * </p>
 * <p>
 * 描述: Command Controller基类，所有Command Controller从此类继承
 * </p>
 */
public class BaseCommandController extends AbstractCommandController implements
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
     * 描述: 是否需要进行流程处理
     */
    private String needProcessConduct;

    /**
     * 描述: Controller ID
     */
    private String ControllerId;

    /**
     * 描述: 应用程序参数
     */
    private ApplicationParameter applicationParameter;    

	/**
	 * 描述: 前台是否需要进行用户身份认证
	 */
	private String needOutAuthentication;    

    /*
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    protected void onBind(HttpServletRequest request, Object o,
            BindException errors) throws Exception {
        // 如果输入的查询日期时间条件非法，则设置窗口类相应属性为空
        if (o instanceof BaseQueryForm) {
            BaseQueryForm form = (BaseQueryForm) o;
            if (form.getBeginDateTime() != null
                    && !"".equals(form.getBeginDateTime())
                    && DateUtil.toDateFromYearMonthDayHourMinPattern(form
                            .getBeginDateTime()) == null)
                form.setBeginDateTime(null);
            if (form.getEndDateTime() != null
                    && !"".equals(form.getEndDateTime())
                    && DateUtil.toDateFromYearMonthDayHourMinPattern(form
                            .getEndDateTime()) == null)
                form.setEndDateTime(null);
            if (form.getBeginDate() != null
                    && !"".equals(form.getBeginDate())
                    && DateUtil.toDateFromYearMonthDayPattern(form
                            .getBeginDate()) == null)
                form.setBeginDate(null);
            if (form.getEndDate() != null
                    && !"".equals(form.getEndDate())
                    && DateUtil
                            .toDateFromYearMonthDayPattern(form.getEndDate()) == null)
                form.setEndDate(null);
        }
    }

    /*
     * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView handle(HttpServletRequest request,
            HttpServletResponse response, Object o, BindException errors)
            throws Exception {
        return null;
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

    /**
     * 描述: 对相关参数进行处理 <br>
     * 参数说明 <br>
     * recPerPage: 每页记录数 <br>
     * pageNo: 页号 <br>
     * recCount: 总记录数
     * 
     * @param map
     *            参数Map,需符合参数说明中的名称
     * @return 重新设置的参数Map
     */
    protected Map processParameter(Map map) {
    	 if (map == null)
    	      map = new HashMap();
    	    //获取每页记录数
    	    Long recPerPage = StringUtil.stringToLong((String) map.get("recPerPage"));
    	    //判断每页记录数据是否合法，如不合法设置为应用程序参数值
    	    if (recPerPage == null || recPerPage.longValue() <= 0)
    	      recPerPage = StringUtil.stringToLong((String)this.getApplicationParameter()
    	          .findParameterByName("recPerPage"));
    	    //获取页号
    	    Long pageNo = StringUtil.stringToLong((String) map.get("pageNo"));
    	    //判断页号是否合法，如不合法设置为1
    	    if (pageNo == null || pageNo.longValue() <= 1)
    	      pageNo = new Long(1);
    	    //获取记录总数
    	    Long recCount = StringUtil.stringToLong((String) map.get("recCount"));
    	    //判断总记录数是否合法,如不合法设置为0
    	    if (recCount == null)
    	      recCount = new Long(0);
    	    //计算可能查询的最大页号
    	    long maxPageNo = (recCount.longValue() % recPerPage.longValue()) == 0 ? (recCount
    	        .longValue() / recPerPage.longValue())
    	        : (recCount.longValue() / recPerPage.longValue() + 1);
    	    //判断请求页号是否合理
    	    if (pageNo.longValue() > maxPageNo)
    	      pageNo = new Long(maxPageNo);
    	    if (pageNo.longValue() < 1)
    	      pageNo = new Long(1);
    	    //重新设置参数
    	    map.put("recPerPage", recPerPage);
    	    map.put("pageNo", pageNo);
    	    map.put("recCount", recCount);
    	    //计算开始记录号
    	    long beginRecNo = (pageNo.longValue() - 1) * recPerPage.longValue() + 1;
    	    //计算结束记录号
    	    long endRecNo = beginRecNo + recPerPage.longValue() - 1;
    	    map.put("beginRecNo", new Long(beginRecNo));
    	    map.put("endRecNo", new Long(endRecNo));
    	    return map;
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
    

	public boolean needOutAuthentication() {
		if ("false".equals(needOutAuthentication))
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

    /*
     * @see ddyx.web.interceptor.ProcessConductable#needProcessConduct()
     */
    public boolean needProcessConduct() {
        if ("true".equals(needProcessConduct))
            return true;
        else
            return false;
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
     * 描述: 返回 needProcessConduct
     */
    public String getNeedProcessConduct() {
        return needProcessConduct;
    }

    /**
     * 描述: 设置 needProcessConduct
     */
    public void setNeedProcessConduct(String needProcessConduct) {
        this.needProcessConduct = needProcessConduct;
    }

    /**
     * 描述: 返回 controllerId
     */
    public String getControllerId() {
        return ControllerId;
    }

    /**
     * 描述: 设置 controllerId
     */
    public void setControllerId(String controllerId) {
        ControllerId = controllerId;
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