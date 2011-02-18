package com.htsoft.oa.dao.document;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.document.DocFolder;

public abstract interface DocFolderDao extends BaseDao<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);

	public abstract List<DocFolder> getFolderLikePath(String paramString);
}


 
 
 
 
 