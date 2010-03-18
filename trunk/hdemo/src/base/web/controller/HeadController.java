/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�Reika</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-1-12</p>
 * <p>���£�</p>
 */
package base.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * ����: HeadController
 * </p>
 * <p>
 * ����: ϵͳͷ��ҳ��Controller
 * </p>
 */
public class HeadController extends BaseController {

  /*
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
    return new ModelAndView("common/head", "today", format.format(new Date()));
  }

}