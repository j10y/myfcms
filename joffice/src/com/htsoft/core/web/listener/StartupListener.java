package com.htsoft.core.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.htsoft.core.util.AppUtil;

public class StartupListener extends ContextLoaderListener {
	private static Log logger = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		AppUtil.init(event.getServletContext());

		boolean isAynMenu = AppUtil.getIsSynMenu();

		if (isAynMenu)
			AppUtil.synMenu();
	}
}