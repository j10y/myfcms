/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 9, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.util.DateUtil;
import com.hxzy.base.util.WebAppUtil;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.model.UserInfo;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Login extends Window implements AfterCompose {

	@Autowired
	private UserService userService;

	private Textbox username;

	private Textbox password;

	private Label error;

	/**
	 * 描述: 提交按钮
	 */
	protected Button submit;

	/**
	 * 描述: 取消按钮
	 */
	protected Button cancel;

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {

		this.setClosable(true);

		submit.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
			}
		});

		cancel.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
			}

		});

		this.addEventListener("onOK", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				onSubmit();
			}
		});

		this.addEventListener("onCancel", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
			}
		});

		// 绑定数据
		onBind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	public void onBind() {
		this.setClosable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	public void onSubmit() {
		List<User> users = userService.findByProperty("username", username.getValue());

		HttpSession session = (HttpSession) Sessions.getCurrent().getNativeSession();

		if (users.isEmpty()) {
			error.setValue("无效的用户信息！");
			return;
		}

		User user = users.get(0);

		if (user != null && user.getLocked()) {
			if (compareLockTime(user.getLockedTime())) {
				user.setLocked(false);
				user.setLockedTime(null);
				userService.update(user);
			} else {
				error.setValue("该用户帐号已经被锁定，请与管理员联系解除锁定");
				return;
			}
		}

		if (!password.getValue().equals(user.getPassword())) {
			int loginFrequency = 1;
			Map loginMap = null;
			// 从session中取用户列表
			if (session.getAttribute("loginMap") != null)
				loginMap = (Map) session.getAttribute("loginMap");
			else
				loginMap = new HashMap();
			
			if (session.getAttribute("loginMap") != null
					&& loginMap.get(user.getUsername()) != null)
				loginFrequency = ((Integer) loginMap.get(user.getUsername())).intValue() + 1;

			// 判断是否超过5次
			if (loginFrequency >= 5) {
				user.setLocked(true);
				user.setLockedTime(DateUtil.getNowPreciseToMin());
				userService.update(user);
				loginMap.put(user.getUsername(), null);
				session.setAttribute("loginMap", loginMap);
				error.setValue("该用户帐号已经被锁定，请与管理员联系解除锁定");
			} else {
				loginMap.put(user.getUsername(), new Integer(loginFrequency));
				session.setAttribute("loginMap", loginMap);
				error.setValue("无效的用户信息，您还有"+(5 - loginFrequency)+"次机会输入！");
			}
			return;
		}

		user.setLoginFrequency(new Long(user.getLoginFrequency().intValue() + 1));
		user.setLastTime(DateUtil.getNowPreciseToMin());
		userService.update(user);

		// 用户信息放入UserInfo中
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(user);

		userInfo.setRoles(user.getRoles());

		// 获取用户功能权限
		Map<String, Privilege> privileges = new HashMap<String, Privilege>();

		for (Role role : user.getRoles()) {
			for (Privilege p : role.getPrivileges()) {
				privileges.put(p.getPrivCode(), p);
			}
		}

		userInfo.setUserFunPriv(privileges);

		// 将用户放入Session
		session.setAttribute(Constant.ATTRIBUTE_USER_INFO, userInfo);

		// 设置用户登录标志
		session.setAttribute(WebAppUtil.LOGIN_FLAG, WebAppUtil.LOGINED);

		Executions.getCurrent().sendRedirect("user/userQuery.zul");

	}

	/**
	 * 描述: 判断是否到解锁时间
	 * 
	 * @param lockedTime
	 * @return
	 */
	private boolean compareLockTime(Date lockedTime) {
		Date nowTime = DateUtil.getNowPreciseToMin();
		if (DateUtil.MinthAddInt(lockedTime, -30).before(nowTime))
			return true;
		else
			return false;
	}

}
