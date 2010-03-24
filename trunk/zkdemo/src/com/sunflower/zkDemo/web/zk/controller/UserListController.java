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
	//��������������֣�
	protected AnnotateDataBinder binder;
	//ǰ��zulҳ��grid��id
	private Grid userlistGrid;
	//grid model���԰󶨵����ݣ��ṩget set����
	private List<User> userList = new ArrayList<User>();
	

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);
	}

	// �����û��б���button�����forward���԰�
	public void onLoadUserList() {
		// ���÷��������spring���ϣ�����ʹ��SpringUtil.getBean("UserService");
		UserService userService = new UserServiceImpl();
		userList = userService.findAllUser();
		// ����grid����ʾ����
		binder.loadComponent(userlistGrid);
		
		//������ʾ�����У����ӳټ���
		// userlistGrid.renderAll();
	}

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
