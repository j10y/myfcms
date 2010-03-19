package com.hxzy.base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.util.WebAppUtil;


/**
 * <p>
 * ¿‡√˚: AuthenticateInterceptor
 * </p>
 * <p>
 * √Ë ˆ: ”√ªßµ«¬ººÏ≤ÈInterceptor
 * </p>
 */
public class AuthenticateInterceptor implements HandlerInterceptor {

  /**
   * √Ë ˆ: µ«¬º“≥√ÊUrl
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
   * √Ë ˆ: ∑µªÿ loginUrl
   */
  public String getLoginUrl() {
    return loginUrl;
  }

  /**
   * √Ë ˆ: …Ë÷√ loginUrl
   */
  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

}