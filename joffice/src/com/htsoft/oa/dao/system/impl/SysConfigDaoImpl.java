package com.htsoft.oa.dao.system.impl;

import java.util.List;

import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.SysConfigDao;
import com.htsoft.oa.model.system.SysConfig;

public class SysConfigDaoImpl extends BaseDaoImpl<SysConfig> implements SysConfigDao {
	public SysConfigDaoImpl() {
		super(SysConfig.class);
	}

	public SysConfig findByKey(String key) {
		String hql = "from SysConfig vo where vo.configKey=?";
		Object[] objs = { key };
		List list = findByHql(hql, objs);
		return (SysConfig) list.get(0);
	}

	public List<SysConfig> findConfigByTypeName(String typeName) {
		String hql = "from SysConfig vo where vo.typeName=?";
		Object[] objs = { typeName };
		return findByHql(hql, objs);
	}

	public List findTypeNames() {
		String sql = "select vo.typeName from SysConfig vo group by vo.typeName";
		Query query = getSession().createQuery(sql);
		return query.list();
	}
}


 
 
 
 
 