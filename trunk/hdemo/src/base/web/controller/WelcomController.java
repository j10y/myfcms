package com.hxzy.base.web.controller;

/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：Reika</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-1-12</p>
 * <p>更新：</p>
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
 * Description: 显示欢迎页面的Controller类
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