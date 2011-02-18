package com.htsoft.oa.service.document.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.document.DocFolderDao;
import com.htsoft.oa.model.document.DocFolder;
import com.htsoft.oa.service.document.DocFolderService;

public class DocFolderServiceImpl extends BaseServiceImpl<DocFolder> implements DocFolderService {
	private DocFolderDao dao;

	public DocFolderServiceImpl(DocFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId) {
		return this.dao.getUserFolderByParentId(userId, parentId);
	}

	public List<DocFolder> getFolderLikePath(String path) {
		return this.dao.getFolderLikePath(path);
	}

	public List<DocFolder> getPublicFolderByParentId(Long parentId) {
		return this.dao.getPublicFolderByParentId(parentId);
	}
}


 
 
 
 
 