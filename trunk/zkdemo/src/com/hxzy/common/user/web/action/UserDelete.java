/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 1, 2010</p>
 * <p>更新：</p>
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
 * 描述：
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
			throw new ApplicationException("该数据不存在！");
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
