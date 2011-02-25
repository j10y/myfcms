
package com.hxzy.base.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
/**
 * <p>
 * ����: WebAppUtil
 * </p>
 * <p>
 * ����: WebӦ�ó��򹤾���
 * </p>
 */
public class WebAppUtil {

    /**
     * ����: �û���¼��־��session�е���������
     */
    public static final String LOGIN_FLAG = "loginFlag";

    /**
     * ����: �û��ѵ�¼��־ֵ
     */
    public static final String LOGINED = "logined";
    
    /**
     * ����: �û���ʶ
     */
    public static final String USER_FLAG = "userFlag";
    
    /**
     * ����: �û�Ϊ����Ա�ı�ʶ
     */
    public static final String USER_ADMIN = "admin";

    /**
     * ����: �û���Ϣ��session�е���������
     */
    public static final String USER_INFO = "userInfo";

    /**
     * ����: �����û�����servletContext�е���������
     */
    public static final String ONLINE_USER_INFO = "onlineUserInfo";

    /**
     * ����: spring��web application context��servletContext�е���������
     */
    public static final String WEB_APPLICATION_CONTEXT = "webApplicationContext";

    /**
     * ����: ��������ʵ����request�е���������
     */
    public static final String TASK_INSTANCE = "taskInstance";

    /**
     * ����: ϵͳ��ʾ��Ϣ��request�е���������
     */
    public static final String SYSTEM_MESSAGE = "systemMessage";

    /**
     * ����: ����url��request�е���������
     */
    public static final String REQUEST_URL = "requestUrl";

    /**
     * ����: ����ҳ��url��������
     */
    public static final String RETURN_URL = "returnUrl";

    /**
     * ����: ��ȡ�û���Ϣ
     * 
     * @param request
     *            �û��������
     * @return �û���Ϣ����
     */
    public static Object getUserInfo(HttpServletRequest request) {
        return request.getSession().getAttribute(USER_INFO);
    }

    /**
     * ����: �����û���¼��־
     * 
     * @param request
     *            �û��������
     */
    public static void setUserLoginFlag(HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_FLAG, LOGINED);
    }

    /**
     * ����: �ж��û��Ƿ��¼
     * 
     * @param request
     *            �û��������
     * @return �ѵ�¼����true,���򷵻�false
     */
    public static boolean isUserLogined(HttpServletRequest request) {
        return LOGINED.equals(request.getSession().getAttribute(LOGIN_FLAG));
    }

    /**
     * ����: ��ȡ�����û���Ϣ
     * 
     * @param request
     *            �û��������
     * @return �����û���Ϣ
     */
    public static Map getOnlineUserInfo(ServletContext servletContext) {
        return (Map) servletContext.getAttribute(ONLINE_USER_INFO);
    }

    /**
     * ����: ����spring��WebApplicationContext��servletContext
     * 
     * @param servletContext
     * @param webApplicationContext
     */
    public static void setWebApplicationContext(ServletContext servletContext,
            WebApplicationContext webApplicationContext) {
        servletContext.setAttribute(WEB_APPLICATION_CONTEXT,
                webApplicationContext);
    }

    /**
     * ����: ��ȡspring��WebApplicationContext
     * 
     * @param servletContext
     * @return
     */
    public static WebApplicationContext getWebApplicationContext(
            ServletContext servletContext) {
        return (WebApplicationContext) servletContext
                .getAttribute(WEB_APPLICATION_CONTEXT);
    }

    /**
     * ����: ����ϵͳ��ʾ��Ϣ��request
     * 
     * @param request
     *            �û��������
     * @param message
     *            ϵͳ��ʾ��Ϣ
     */
    public static void setSystemMessage(HttpServletRequest request,
            String message) {
        request.setAttribute(SYSTEM_MESSAGE, message);
    }

    /**
     * ����: ��request�л�ȡϵͳ��ʾ��Ϣ
     * 
     * @param request
     *            �û��������
     * @return ϵͳ��ʾ��Ϣ
     */
    public static String getSystemMessage(HttpServletRequest request) {
        return (String) request.getAttribute(SYSTEM_MESSAGE);
    }

    /**
     * ����: �����û�����url��request
     * 
     * @param request
     *            �û��������
     * @param requestUrl
     *            �����û�����url
     */
    public static void setRequestUrl(HttpServletRequest request,
            String requestUrl) {
        request.setAttribute(REQUEST_URL, requestUrl);
    }

    /**
     * ����: ��request�л�ȡ�û�����url
     * 
     * @param request
     *            �û��������
     * @return �û�����url
     */
    public static String getRequestUrl(HttpServletRequest request) {
        return (String) request.getAttribute(REQUEST_URL);
    }
    
    /**
     * ����: ȡվ�����ʵ·��
     * 
     * @param request
     * @return
     */
    public static String getWebRealPath(HttpServletRequest request){
        return (String)request.getSession().getServletContext().getRealPath ("/");        
    }
}
