package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Dictionary;

public abstract interface DictionaryService extends BaseService<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);
}


 
 
 
 
 