/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 31, 2010</p>
 * <p>���£�</p>
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
 * ������
 */
public class WebAppInitListener implements WebAppInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.WebAppInit#init(org.zkoss.zk.ui.WebApp)
	 */
	public void init(WebApp webapp) throws Exception {

		ServletContext servletContext = (ServletContext) webapp.getNativeContext();
		// ���������û���
		servletContext.setAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO, new TreeMap());

	}

}
