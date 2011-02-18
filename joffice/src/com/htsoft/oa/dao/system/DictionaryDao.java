package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Dictionary;

public abstract interface DictionaryDao extends BaseDao<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);
}


 
 
 
 