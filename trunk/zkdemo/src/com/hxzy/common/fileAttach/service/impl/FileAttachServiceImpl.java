/**
 * Project Name:
 * CopyRight (c)
 * Version:1.0
 * Date:Apr 8, 2010
 * Update:
 */

package com.hxzy.common.fileAttach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hxzy.common.fileAttach.dao.FileAttachDao;
import com.hxzy.common.fileAttach.model.FileAttach;
import com.hxzy.common.fileAttach.service.FileAttachService;
import com.hxzy.base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 
 */

public class FileAttachServiceImpl extends BaseServiceImpl<FileAttach,FileAttachDao> implements FileAttachService {
	
	@Autowired
	private FileAttachDao fileAttachDao;

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected FileAttachDao getDao() {
		return fileAttachDao;
	}

	public FileAttachDao getFileAttachDao() {
		return fileAttachDao;
	}

	public void setFileAttachDao(FileAttachDao fileAttachDao) {
		this.fileAttachDao = fileAttachDao;
	}

	
}
