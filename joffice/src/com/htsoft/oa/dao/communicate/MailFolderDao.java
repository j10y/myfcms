package com.htsoft.oa.dao.communicate;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.MailFolder;

public abstract interface MailFolderDao extends BaseDao<MailFolder> {
	public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getFolderLikePath(String paramString);
}


 
 
 
 
 