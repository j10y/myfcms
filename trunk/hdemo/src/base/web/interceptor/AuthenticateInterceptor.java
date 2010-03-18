package base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import base.util.WebAppUtil;


/**
 * <p>
 * ����: AuthenticateInterceptor
 * </p>
 * <p>
 * ����: �û���¼���Interceptor
 * </p>
 */
public class AuthenticateInterceptor implements HandlerInterceptor {

  /**
   * ����: ��¼ҳ��Url
   */
  public String loginUrl = "";

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) {
    Authenticatable handler = (Authenticatable) o;
    if (!handler.needAuthentication())
      return true;
    if (WebAppUtil.isUserLogined(request)) {
      return true;
    } else {
      try {
        response.sendRedirect(request.getContextPath() + loginUrl);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      return false;
    }

  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      java.lang.Exception)
   */
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {

  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.web.servlet.ModelAndView)
   */
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler, ModelAndView modelAndView) {

  }

  /**
   * ����: ���� loginUrl
   */
  public String getLoginUrl() {
    return loginUrl;
  }

  /**
   * ����: ���� loginUrl
   */
  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

}