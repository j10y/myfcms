package com.hxzy.base.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.model.SessionCount;
import com.hxzy.base.util.WebAppUtil;
import com.hxzy.common.user.model.OnlineUserInfo;
import com.hxzy.common.user.model.UserInfo;

/**
 * @author xiacc
 * 
 * 描述：应用程序Session监听器
 */
public class ApplicationHttpSessionListener implements HttpSessionAttributeListener,
		HttpSessionListener {

	public static HashMap sessionUserMap = new HashMap();

	/*
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		// 根据SessionId获取用户代码
		String userCode = (String) sessionUserMap.get(session.getId());
		sessionUserMap.remove(session.getId());
		if (userCode == null)
			return;
		// Session失效时将用户信息从在线用户表中删除
		Map map = (Map) WebAppUtil.getOnlineUserInfo(session.getServletContext());
		if (map == null) {
			return;
		}
		OnlineUserInfo onlineUserInfo = (OnlineUserInfo) map.get(userCode);
		if (onlineUserInfo != null && onlineUserInfo.getUserSession() == session) {
			map.remove(userCode);
		}
	}

	/**
	 * session 监听器 attributeAdded 添加session attribute值时触发
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (se.getName().equals(Constant.ATTRIBUTE_USER_INFO)) {
			UserInfo userInfo = (UserInfo) se.getValue();
			try {
				SessionCount.getInstance().addLogonUser(userInfo.getUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * session 监听器 attributeRemoved 当前session attribute值被删除时触发
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (se.getName().equals(Constant.ATTRIBUTE_USER_INFO)) {
			UserInfo userInfo = (UserInfo) se.getValue();
			SessionCount.getInstance().delLogonUser(userInfo.getUser().getId().toString());
		}

	}

	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}