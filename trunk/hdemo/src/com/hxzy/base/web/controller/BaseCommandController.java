package com.hxzy.base.web.controller;

/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-20</p>
 * <p>���£�</p>
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
 * ����: BaseCommandController
 * </p>
 * <p>
 * ����: Command Controller���࣬����Command Controller�Ӵ���̳�
 * </p>
 */
public class BaseCommandController extends AbstractCommandController implements
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
     * ����: �Ƿ���Ҫ�������̴���
     */
    private String needProcessConduct;

    /**
     * ����: Controller ID
     */
    private String ControllerId;

    /**
     * ����: Ӧ�ó������
     */
    private ApplicationParameter applicationParameter;    

	/**
	 * ����: ǰ̨�Ƿ���Ҫ�����û������֤
	 */
	private String needOutAuthentication;    

    /*
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    protected void onBind(HttpServletRequest request, Object o,
            BindException errors) throws Exception {
        // �������Ĳ�ѯ����ʱ�������Ƿ��������ô�������Ӧ����Ϊ��
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
     * ����: ��ȡ��ǰ�����û���Ϣ
     * 
     * @param request
     *            request����
     * @return ��ǰ�����û���Ϣ
     */
    protected UserInfo getCurrentUserInfo(HttpServletRequest request) {
        return (UserInfo) WebAppUtil.getUserInfo(request);
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

    /**
     * ����: ����ز������д��� <br>
     * ����˵�� <br>
     * recPerPage: ÿҳ��¼�� <br>
     * pageNo: ҳ�� <br>
     * recCount: �ܼ�¼��
     * 
     * @param map
     *            ����Map,����ϲ���˵���е�����
     * @return �������õĲ���Map
     */
    protected Map processParameter(Map map) {
    	 if (map == null)
    	      map = new HashMap();
    	    //��ȡÿҳ��¼��
    	    Long recPerPage = StringUtil.stringToLong((String) map.get("recPerPage"));
    	    //�ж�ÿҳ��¼�����Ƿ�Ϸ����粻�Ϸ�����ΪӦ�ó������ֵ
    	    if (recPerPage == null || recPerPage.longValue() <= 0)
    	      recPerPage = StringUtil.stringToLong((String)this.getApplicationParameter()
    	          .findParameterByName("recPerPage"));
    	    //��ȡҳ��
    	    Long pageNo = StringUtil.stringToLong((String) map.get("pageNo"));
    	    //�ж�ҳ���Ƿ�Ϸ����粻�Ϸ�����Ϊ1
    	    if (pageNo == null || pageNo.longValue() <= 1)
    	      pageNo = new Long(1);
    	    //��ȡ��¼����
    	    Long recCount = StringUtil.stringToLong((String) map.get("recCount"));
    	    //�ж��ܼ�¼���Ƿ�Ϸ�,�粻�Ϸ�����Ϊ0
    	    if (recCount == null)
    	      recCount = new Long(0);
    	    //������ܲ�ѯ�����ҳ��
    	    long maxPageNo = (recCount.longValue() % recPerPage.longValue()) == 0 ? (recCount
    	        .longValue() / recPerPage.longValue())
    	        : (recCount.longValue() / recPerPage.longValue() + 1);
    	    //�ж�����ҳ���Ƿ����
    	    if (pageNo.longValue() > maxPageNo)
    	      pageNo = new Long(maxPageNo);
    	    if (pageNo.longValue() < 1)
    	      pageNo = new Long(1);
    	    //�������ò���
    	    map.put("recPerPage", recPerPage);
    	    map.put("pageNo", pageNo);
    	    map.put("recCount", recCount);
    	    //���㿪ʼ��¼��
    	    long beginRecNo = (pageNo.longValue() - 1) * recPerPage.longValue() + 1;
    	    //���������¼��
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
     * ����: ���� needProcessConduct
     */
    public String getNeedProcessConduct() {
        return needProcessConduct;
    }

    /**
     * ����: ���� needProcessConduct
     */
    public void setNeedProcessConduct(String needProcessConduct) {
        this.needProcessConduct = needProcessConduct;
    }

    /**
     * ����: ���� controllerId
     */
    public String getControllerId() {
        return ControllerId;
    }

    /**
     * ����: ���� controllerId
     */
    public void setControllerId(String controllerId) {
        ControllerId = controllerId;
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