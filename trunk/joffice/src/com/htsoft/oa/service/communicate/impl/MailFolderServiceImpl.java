package com.htsoft.oa.service.communicate.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.communicate.MailFolderDao;
import com.htsoft.oa.model.communicate.MailFolder;
import com.htsoft.oa.service.communicate.MailFolderService;

public class MailFolderServiceImpl extends BaseServiceImpl<MailFolder> implements MailFolderService {
	private MailFolderDao dao;

	public MailFolderServiceImpl(MailFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<MailFolder> getUserFolderByParentId(Long curUserId, Long parentId) {
		return this.dao.getUserFolderByParentId(curUserId, parentId);
	}

	public List<MailFolder> getAllUserFolderByParentId(Long userId, Long parentId) {
		return this.dao.getAllUserFolderByParentId(userId, parentId);
	}

	public List<MailFolder> getFolderLikePath(String path) {
		return this.dao.getFolderLikePath(path);
	}
}


 
 
 
 
 