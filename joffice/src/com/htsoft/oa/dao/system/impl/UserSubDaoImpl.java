package com.htsoft.oa.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.UserSubDao;
import com.htsoft.oa.model.system.UserSub;

public class UserSubDaoImpl extends BaseDaoImpl<UserSub> implements UserSubDao {
	public UserSubDaoImpl() {
		super(UserSub.class);
	}

	public List<Long> upUser(Long userId) {
		String hql = "from UserSub vo where vo.subAppUser.userId=?";
		Object[] objs = { userId };
		List<UserSub> list = findByHql(hql, objs);
		List idList = new ArrayList();
		for (UserSub sb : list) {
			idList.add(sb.getUserId());
		}
		return idList;
	}

	public List<Long> subUsers(Long userId) {
		String hql = "from UserSub vo where vo.userId=?";
		Object[] objs = { userId };
		List<UserSub> list = findByHql(hql, objs);
		List idList = new ArrayList();
		for (UserSub sb : list) {
			idList.add(sb.getSubAppUser().getUserId());
		}
		return idList;
	}
}


 
 
 
 
 