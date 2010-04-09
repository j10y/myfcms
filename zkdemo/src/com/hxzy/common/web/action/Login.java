/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 9, 2010</p>
 * <p>���£�</p>
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
 * ������
 */
public class Login extends Window implements AfterCompose {

	@Autowired
	private UserService userService;

	private Textbox username;

	private Textbox password;

	private Label error;

	/**
	 * ����: �ύ��ť
	 */
	protected Button submit;

	/**
	 * ����: ȡ����ť
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

		// ������
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
			error.setValue("��Ч���û���Ϣ��");
			return;
		}

		User user = users.get(0);

		if (user != null && user.getLocked()) {
			if (compareLockTime(user.getLockedTime())) {
				user.setLocked(false);
				user.setLockedTime(null);
				userService.update(user);
			} else {
				error.setValue("���û��ʺ��Ѿ����������������Ա��ϵ�������");
				return;
			}
		}

		if (!password.getValue().equals(user.getPassword())) {
			int loginFrequency = 1;
			Map loginMap = null;
			// ��session��ȡ�û��б�
			if (session.getAttribute("loginMap") != null)
				loginMap = (Map) session.getAttribute("loginMap");
			else
				loginMap = new HashMap();
			
			if (session.getAttribute("loginMap") != null
					&& loginMap.get(user.getUsername()) != null)
				loginFrequency = ((Integer) loginMap.get(user.getUsername())).intValue() + 1;

			// �ж��Ƿ񳬹�5��
			if (loginFrequency >= 5) {
				user.setLocked(true);
				user.setLockedTime(DateUtil.getNowPreciseToMin());
				userService.update(user);
				loginMap.put(user.getUsername(), null);
				session.setAttribute("loginMap", loginMap);
				error.setValue("���û��ʺ��Ѿ����������������Ա��ϵ�������");
			} else {
				loginMap.put(user.getUsername(), new Integer(loginFrequency));
				session.setAttribute("loginMap", loginMap);
				error.setValue("��Ч���û���Ϣ��������"+(5 - loginFrequency)+"�λ������룡");
			}
			return;
		}

		user.setLoginFrequency(new Long(user.getLoginFrequency().intValue() + 1));
		user.setLastTime(DateUtil.getNowPreciseToMin());
		userService.update(user);

		// �û���Ϣ����UserInfo��
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(user);

		userInfo.setRoles(user.getRoles());

		// ��ȡ�û�����Ȩ��
		Map<String, Privilege> privileges = new HashMap<String, Privilege>();

		for (Role role : user.getRoles()) {
			for (Privilege p : role.getPrivileges()) {
				privileges.put(p.getPrivCode(), p);
			}
		}

		userInfo.setUserFunPriv(privileges);

		// ���û�����Session
		session.setAttribute(Constant.ATTRIBUTE_USER_INFO, userInfo);

		// �����û���¼��־
		session.setAttribute(WebAppUtil.LOGIN_FLAG, WebAppUtil.LOGINED);

		Executions.getCurrent().sendRedirect("user/userQuery.zul");

	}

	/**
	 * ����: �ж��Ƿ񵽽���ʱ��
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
