/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 31, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.listener;

import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

import com.hxzy.base.constant.Constant;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class WebAppInitListener implements WebAppInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.WebAppInit#init(org.zkoss.zk.ui.WebApp)
	 */
	public void init(WebApp webapp) throws Exception {

		ServletContext servletContext = (ServletContext) webapp.getNativeContext();
		// 创建在线用户表
		servletContext.setAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO, new TreeMap());

	}

}
