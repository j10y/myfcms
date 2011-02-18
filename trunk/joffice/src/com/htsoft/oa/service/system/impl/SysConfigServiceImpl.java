package com.htsoft.oa.service.system.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.SysConfigDao;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.system.SysConfigService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
	private SysConfigDao dao;

	public SysConfigServiceImpl(SysConfigDao dao) {
		super(dao);
		this.dao = dao;
	}

	public SysConfig findByKey(String key) {
		return this.dao.findByKey(key);
	}

	public Map findByType() {
		List<String> list = this.dao.findTypeNames();
		Map cList = new HashMap();
		for (String typeName : list) {
			List confList = this.dao.findConfigByTypeName(typeName);
			cList.put(typeName, confList);
		}
		return cList;
	}
}
