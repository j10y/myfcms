/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 2, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.List;

import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class UserAdd extends ActionWindow {

	@Autowired
	private UserService userService;

	private User user;

	private Textbox username;

	private Textbox truename;

	private Textbox password;
	
	private Textbox password2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		user = new User();

		password2.setConstraint(new Constraint(){
			public void validate(Component comp, Object value) throws WrongValueException {
				if(!password.getValue().equals(value)){
					throw new WrongValueException(comp, "两次密码不同！");
				}
			}
		});

		// 用户名重复判断
		username.setConstraint(new Constraint(){
			public void validate(Component comp, Object value) throws WrongValueException {
				List list = userService.findByProperty("username",value);
				if (list != null && list.size() != 0) {
					throw new WrongValueException(username, "该用户名已经存在!");
				}
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
		user.setUsername(username.getValue());
		user.setTruename(truename.getValue());
		user.setPassword(password.getValue());
		user.setLocked(false);
		user.setLoginFrequency(0L);
		user.setType(1L);		

		userService.save(user);

		((ListWindow) this.getParent()).onFind();
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "增加"+username.getText();
	}

}
