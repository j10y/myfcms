
package com.hxzy.base.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
/**
 * <p>
 * 类名: WebAppUtil
 * </p>
 * <p>
 * 描述: Web应用程序工具类
 * </p>
 */
public class WebAppUtil {

    /**
     * 描述: 用户登录标志在session中的属性名称
     */
    public static final String LOGIN_FLAG = "loginFlag";

    /**
     * 描述: 用户已登录标志值
     */
    public static final String LOGINED = "logined";
    
    /**
     * 描述: 用户标识
     */
    public static final String USER_FLAG = "userFlag";
    
    /**
     * 描述: 用户为管理员的标识
     */
    public static final String USER_ADMIN = "admin";

    /**
     * 描述: 用户信息在session中的属性名称
     */
    public static final String USER_INFO = "userInfo";

    /**
     * 描述: 在线用户表在servletContext中的属性名称
     */
    public static final String ONLINE_USER_INFO = "onlineUserInfo";

    /**
     * 描述: spring的web application context在servletContext中的属性名称
     */
    public static final String WEB_APPLICATION_CONTEXT = "webApplicationContext";

    /**
     * 描述: 流程事务实例在request中的属性名称
     */
    public static final String TASK_INSTANCE = "taskInstance";

    /**
     * 描述: 系统提示信息在request中的属性名称
     */
    public static final String SYSTEM_MESSAGE = "systemMessage";

    /**
     * 描述: 请求url在request中的属性名称
     */
    public static final String REQUEST_URL = "requestUrl";

    /**
     * 描述: 返回页面url参数名称
     */
    public static final String RETURN_URL = "returnUrl";

    /**
     * 描述: 获取用户信息
     * 
     * @param request
     *            用户请求对象
     * @return 用户信息对象
     */
    public static Object getUserInfo(HttpServletRequest request) {
        return request.getSession().getAttribute(USER_INFO);
    }

    /**
     * 描述: 设置用户登录标志
     * 
     * @param request
     *            用户请求对象
     */
    public static void setUserLoginFlag(HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_FLAG, LOGINED);
    }

    /**
     * 描述: 判断用户是否登录
     * 
     * @param request
     *            用户请求对象
     * @return 已登录返回true,否则返回false
     */
    public static boolean isUserLogined(HttpServletRequest request) {
        return LOGINED.equals(request.getSession().getAttribute(LOGIN_FLAG));
    }

    /**
     * 描述: 获取在线用户信息
     * 
     * @param request
     *            用户请求对象
     * @return 在线用户信息
     */
    public static Map getOnlineUserInfo(ServletContext servletContext) {
        return (Map) servletContext.getAttribute(ONLINE_USER_INFO);
    }

    /**
     * 描述: 设置spring的WebApplicationContext到servletContext
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
     * 描述: 获取spring的WebApplicationContext
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
     * 描述: 设置系统提示信息到request
     * 
     * @param request
     *            用户请求对象
     * @param message
     *            系统提示信息
     */
    public static void setSystemMessage(HttpServletRequest request,
            String message) {
        request.setAttribute(SYSTEM_MESSAGE, message);
    }

    /**
     * 描述: 从request中获取系统提示信息
     * 
     * @param request
     *            用户请求对象
     * @return 系统提示信息
     */
    public static String getSystemMessage(HttpServletRequest request) {
        return (String) request.getAttribute(SYSTEM_MESSAGE);
    }

    /**
     * 描述: 设置用户请求url到request
     * 
     * @param request
     *            用户请求对象
     * @param requestUrl
     *            设置用户请求url
     */
    public static void setRequestUrl(HttpServletRequest request,
            String requestUrl) {
        request.setAttribute(REQUEST_URL, requestUrl);
    }

    /**
     * 描述: 从request中获取用户请求url
     * 
     * @param request
     *            用户请求对象
     * @return 用户请求url
     */
    public static String getRequestUrl(HttpServletRequest request) {
        return (String) request.getAttribute(REQUEST_URL);
    }
    
    /**
     * 描述: 取站点的真实路径
     * 
     * @param request
     * @return
     */
    public static String getWebRealPath(HttpServletRequest request){
        return (String)request.getSession().getServletContext().getRealPath ("/");        
    }
}
