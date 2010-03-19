package com.hxzy.base.web.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.util.Util;
import com.hxzy.base.util.WebAppUtil;


/**
 * <p>
 * 类名: RequestUrlInterceptor
 * </p>
 * <p>
 * 描述:
 * </p>
 */
public class RequestUrlInterceptor implements HandlerInterceptor {

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) throws Exception {
    RequestUrlPreparable handler = (RequestUrlPreparable) o;
    if (handler.needRequestUrl()) {
      StringBuffer requestUrl = new StringBuffer("");
      // 获取Request URI
      requestUrl.append(request.getServletPath());
      // 获取查询参数
      boolean first1 = true;
      boolean first2 = true;
      Map map = request.getParameterMap();
      Iterator it = map.entrySet().iterator();
      Map.Entry entry = null;
      String paramName = null;
      String[] paramValues = null;
      int len = 0;
      while (it.hasNext()) {
        if (first1) {
          requestUrl.append("?");
          first1 = false;
        }
        entry = (Map.Entry) it.next();
        paramName = (String) entry.getKey();
        paramValues = (String[]) entry.getValue();
        len = paramValues.length;
        for (int i = 0; i < len; i++) {
          if (first2) {
            first2 = false;
          } else {
            requestUrl.append("&");
          }
          requestUrl.append(paramName);
          requestUrl.append("=");
          requestUrl.append(Util.urlEncoding(paramValues[i]));
        }
      }
      // System.out.println(requestUrl.toString);
      WebAppUtil
          .setRequestUrl(request, Util.urlEncoding(requestUrl.toString()));
    }
    return true;
  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.web.servlet.ModelAndView)
   */
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object o, ModelAndView mAv)
      throws Exception {
    // TODO Auto-generated method stub

  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      java.lang.Exception)
   */
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object o, Exception ex) throws Exception {
    // TODO Auto-generated method stub

  }

}
