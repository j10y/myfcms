package com.htsoft.oa.dao.admin.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.OfficeGoodsDao;
import com.htsoft.oa.model.admin.OfficeGoods;

public class OfficeGoodsDaoImpl extends BaseDaoImpl<OfficeGoods> implements OfficeGoodsDao {
	public OfficeGoodsDaoImpl() {
		super(OfficeGoods.class);
	}

	public List<OfficeGoods> findByWarm() {
		String hql = "from OfficeGoods vo where ((vo.stockCounts<=vo.warnCounts and vo.isWarning=1) or (vo.stockCounts<=0))";
		return findByHql(hql);
	}
}


 
 
 
 
 