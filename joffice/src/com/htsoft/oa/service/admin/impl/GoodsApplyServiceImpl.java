package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.GoodsApplyDao;
import com.htsoft.oa.model.admin.GoodsApply;
import com.htsoft.oa.service.admin.GoodsApplyService;

public class GoodsApplyServiceImpl extends BaseServiceImpl<GoodsApply> implements GoodsApplyService {
	private GoodsApplyDao dao;

	public GoodsApplyServiceImpl(GoodsApplyDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 