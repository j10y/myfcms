package com.htsoft.oa.dao.flow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.ProcessRunDao;
import com.htsoft.oa.model.flow.ProcessRun;

public class ProcessRunDaoImpl extends BaseDaoImpl<ProcessRun> implements ProcessRunDao {
	public ProcessRunDaoImpl() {
		super(ProcessRun.class);
	}

	public ProcessRun getByPiId(String piId) {
		String hql = "from ProcessRun pr where pr.piId=?";
		return (ProcessRun) findUnique(hql, new Object[] { piId });
	}

	public List<ProcessRun> getByDefId(Long defId, PagingBean pb) {
		String hql = " from ProcessRun pr where pr.proDefinition.defId=? ";
		return findByHql(hql, new Object[] { defId }, pb);
	}

	public List<ProcessRun> getByUserIdSubject(Long userId, String subject, PagingBean pb) {
		ArrayList params = new ArrayList();
		String hql = "select pr from ProcessRun as pr join pr.processForms as pf where pf.creatorId=? group by pr.runId order by pr.createtime desc";
		params.add(userId);
		if (StringUtils.isNotEmpty(subject)) {
			hql = hql + " and pr.subject like ?";
			params.add("%" + subject + "%");
		}

		return findByHql(hql, params.toArray(), pb);
	}
}


 
 
 
 
 