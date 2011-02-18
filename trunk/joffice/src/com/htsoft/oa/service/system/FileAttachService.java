package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.FileAttach;

public abstract interface FileAttachService extends BaseService<FileAttach> {
	public abstract void removeByPath(String paramString);

	public abstract FileAttach getByPath(String paramString);
}


 
 
 
 
 