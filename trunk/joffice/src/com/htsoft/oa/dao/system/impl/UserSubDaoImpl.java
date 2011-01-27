package com.htsoft.oa.dao.system.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.UserSubDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.UserSub;
import java.util.ArrayList;
import java.util.List;

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

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.dao.system.impl.UserSubDaoImpl JD-Core Version:
 * 0.5.4
 */