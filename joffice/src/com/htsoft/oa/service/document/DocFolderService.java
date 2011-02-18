package com.htsoft.oa.service.document;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.document.DocFolder;

public abstract interface DocFolderService extends BaseService<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<DocFolder> getFolderLikePath(String paramString);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);
}


 
 
 
 
 