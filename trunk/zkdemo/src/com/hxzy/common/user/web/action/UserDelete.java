/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 1, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * �������û�ɾ��
 */
public class UserDelete extends ActionWindow {

	@Autowired
	private UserService userService;

	private Set<User> users = (Set<User>) Executions.getCurrent().getArg().get("users");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onBind() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for (User user : users) {
			userService.delete(user);
		}

		((ListWindow) this.getParent()).onFind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ɾ��");

		for (User user : users) {
			sb.append(user.getUsername());
			sb.append(",");
		}

		return sb.toString();
	}

}
