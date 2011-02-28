/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：May 13, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.web.action;

import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.user.model.UserInfo;
import com.hxzy.common.user.web.action.UserQuery;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class LogOut extends ActionWindow {

	protected Button submit;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onCreate()
	 */
	@Override
	public void onCreate() {

		this.setClosable(true);

		submit.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
				logging();
			}
		});

		cancel.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				LogOut.this.detach();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		HttpSession session = (HttpSession) Sessions.getCurrent().getNativeSession();

		log = new Log();
		log.setIp(Executions.getCurrent().getRemoteAddr());
		UserInfo userInfo = (UserInfo) Executions.getCurrent().getSession().getAttribute(
				Constant.ATTRIBUTE_USER_INFO);
		if (userInfo != null) {
			log.setUsername(userInfo.getUser().getTruename());
		}
		log.setLogAction(this.getClass().getSimpleName());
		log.setLogTime(new Date());

		UserInfo user = (UserInfo) session.getAttribute(Constant.ATTRIBUTE_USER_INFO);

		log.setDetail(user.getUser().getUsername() + "退出系统");
		logService.save(log);

		TreeMap onlineUsers = (TreeMap) session.getServletContext().getAttribute(
				Constant.ATTRIBUTE_ONLINE_USER_INFO);
		if (onlineUsers.get(user.getUser().getUsername()) != null)
			onlineUsers.remove(user.getUser().getUsername());

		session.invalidate();

		Executions.getCurrent().sendRedirect("/login.zul");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
