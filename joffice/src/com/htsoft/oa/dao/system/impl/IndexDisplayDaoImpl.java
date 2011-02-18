package com.htsoft.oa.dao.system.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.IndexDisplayDao;
import com.htsoft.oa.model.system.IndexDisplay;

public class IndexDisplayDaoImpl extends BaseDaoImpl<IndexDisplay> implements IndexDisplayDao {
	public IndexDisplayDaoImpl() {
		super(IndexDisplay.class);
	}

	public List<IndexDisplay> findByUser(Long userId) {
		String hql = "from IndexDisplay vo where vo.appUser.userId=?";
		return findByHql(hql, new Object[] { userId });
	}
}


 
 
 
 
 