package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.FileAttach;

public abstract interface FileAttachDao extends BaseDao<FileAttach> {
	public abstract void removeByPath(String paramString);

	public abstract FileAttach getByPath(String paramString);
}


 
 
 
 