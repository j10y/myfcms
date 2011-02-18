package com.htsoft.oa.service.system.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.DictionaryDao;
import com.htsoft.oa.model.system.Dictionary;
import com.htsoft.oa.service.system.DictionaryService;

public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements DictionaryService {
	private DictionaryDao dao;

	public DictionaryServiceImpl(DictionaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<String> getAllItems() {
		return this.dao.getAllItems();
	}

	public List<String> getAllByItemName(String itemName) {
		return this.dao.getAllByItemName(itemName);
	}
}


 
 
 
 
 