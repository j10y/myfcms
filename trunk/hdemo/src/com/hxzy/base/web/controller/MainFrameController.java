/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�Reika</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-1-12</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: DisplayMainFrameController</p>
 * <p>Description: ��ʾϵͳ��ܴ��ڵ�Controller��</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MainFrameController extends BaseController {

  protected ModelAndView handleRequestInternal(HttpServletRequest parm1, HttpServletResponse parm2) throws java.lang.Exception {
    
	  return new ModelAndView("common/mainFrame");
  }

}