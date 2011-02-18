package com.htsoft.oa.service.communicate;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.MailFolder;

public abstract interface MailFolderService extends BaseService<MailFolder> {
	public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getFolderLikePath(String paramString);
}


 
 
 
 
 