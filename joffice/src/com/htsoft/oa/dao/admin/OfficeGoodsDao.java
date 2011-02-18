package com.htsoft.oa.dao.admin;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.OfficeGoods;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods> {
	public abstract List<OfficeGoods> findByWarm();
}


 
 
 
 