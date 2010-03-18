package base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * ����: CharacterEncodingInterceptor
 * </p>
 * <p>
 * ����: �ַ�����ת��Interceptor
 * </p>
 */
public class CharacterEncodingInterceptor implements HandlerInterceptor {

  /**
   * ����: �����ַ���
   */
  public String encodingSchema = "";

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) throws Exception {
    request.setCharacterEncoding(encodingSchema);
    return true;
  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.web.servlet.ModelAndView)
   */
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
      Object arg2, ModelAndView arg3) throws Exception {
    // TODO Auto-generated method stub

  }

  /*
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      java.lang.Exception)
   */
  public void afterCompletion(HttpServletRequest arg0,
      HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    // TODO Auto-generated method stub

  }

  /**
   * ����: ���� encodingSchema
   */
  public String getEncodingSchema() {
    return encodingSchema;
  }

  /**
   * ����: ���� encodingSchema
   */
  public void setEncodingSchema(String encodingSchema) {
    this.encodingSchema = encodingSchema;
  }

}
