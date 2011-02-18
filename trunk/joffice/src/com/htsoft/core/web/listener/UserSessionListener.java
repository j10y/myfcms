package com.htsoft.core.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.htsoft.core.util.AppUtil;

public class UserSessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		String sessionId = event.getSession().getId();
		AppUtil.removeOnlineUser(sessionId);
	}
}
