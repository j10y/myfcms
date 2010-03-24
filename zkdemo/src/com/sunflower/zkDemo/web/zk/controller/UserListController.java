package com.sunflower.zkDemo.web.zk.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Grid;

import com.sunflower.zkDemo.domain.User;
import com.sunflower.zkDemo.service.UserService;
import com.sunflower.zkDemo.service.UserServiceImpl;

@SuppressWarnings("serial")
public class UserListController extends Window implements AfterCompose {
	//绑定器：我起的名字，
	protected AnnotateDataBinder binder;
	//前端zul页面grid的id
	private Grid userlistGrid;
	//grid model属性绑定的数据，提供get set方法
	private List<User> userList = new ArrayList<User>();
	

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);
	}

	// 加载用户列表，与button组件的forward属性绑定
	public void onLoadUserList() {
		// 调用服务，如果与spring整合，可以使用SpringUtil.getBean("UserService");
		UserService userService = new UserServiceImpl();
		userList = userService.findAllUser();
		// 更新grid，显示数据
		binder.loadComponent(userlistGrid);
		
		//立即显示所有行，不延迟加载
		// userlistGrid.renderAll();
	}

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
