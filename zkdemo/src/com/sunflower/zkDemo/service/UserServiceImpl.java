package com.sunflower.zkDemo.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sunflower.zkDemo.domain.User;

public class UserServiceImpl implements UserService {
	public List<User> findAllUser() {
		// 仅作为演示，实际操作，访问数据库
		List<User> userList = new ArrayList<User>();
		User user = null;
		Random random = new Random();
		DecimalFormat decimalFormat = new DecimalFormat("0000000");
		for (int i = 0; i < 50; i++) {
			user = new User();
			user.setId(i);
			user.setName("Sunflower" + i);
			user.setPassword(decimalFormat.format(random.nextInt(600000)));
			user.setSex(random.nextInt(2));
			user.setAge(random.nextInt(90));
			userList.add(user);
		}
		return userList;
	}
}
