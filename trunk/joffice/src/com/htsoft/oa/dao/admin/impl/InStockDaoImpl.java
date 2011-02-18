package com.htsoft.oa.dao.admin.impl;

import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.InStockDao;
import com.htsoft.oa.model.admin.InStock;

public class InStockDaoImpl extends BaseDaoImpl<InStock> implements InStockDao {
	public InStockDaoImpl() {
		super(InStock.class);
	}

	public Integer findInCountByBuyId(Long buyId) {
		String hql = "select vo.inCounts from InStock vo where vo.buyId=?";
		Query query = getSession().createQuery(hql);
		query.setLong(0, buyId.longValue());
		Integer inCount = Integer.valueOf(Integer.parseInt(query.list().iterator().next()
				.toString()));
		return inCount;
	}
}


 
 
 
 
 