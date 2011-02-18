package com.htsoft.oa.dao.archive.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.archive.ArchivesDocDao;
import com.htsoft.oa.model.archive.ArchivesDoc;

public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc> implements ArchivesDocDao {
	public ArchivesDocDaoImpl() {
		super(ArchivesDoc.class);
	}

	public List<ArchivesDoc> findByAid(Long archivesId) {
		String hql = "from ArchivesDoc vo where vo.archives.archivesId=?";
		Object[] objs = { archivesId };
		return findByHql(hql, objs);
	}
}


 
 
 
 
 