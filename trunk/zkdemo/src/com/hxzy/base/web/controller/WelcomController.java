package com.hxzy.base.web.controller;

/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�Reika</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-1-12</p>
 * <p>���£�</p>
 */

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;



/**
 * 
 * <p>
 * Title: DisplayWelcomController
 * </p>
 * <p>
 * Description: ��ʾ��ӭҳ���Controller��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class WelcomController extends BaseController {


	/*
	 * @see com.quanda.common.web.controller.BaseController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap map = new HashMap();
		return new ModelAndView("common/welcome", map);
	}

}