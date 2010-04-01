/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 1, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.RequestUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;

import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * ������
 */
public class UserDelete extends ActionWindow {

	private Label username;

	@Autowired
	private UserService userService;

	private User user = (User)Executions.getCurrent().getArg().get("user");    

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onBind() {		
		
		if(user == null){
			this.onClose();
			throw new ApplicationException("�����ݲ����ڣ�");
		}
		
		username.setValue(user.getTruename());
		
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		userService.delete(user);
		((UserQuery)this.getParent()).onFind();
		this.onClose();		
	}

}
