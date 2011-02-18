package com.htsoft.oa.dao.info.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.InMessageDao;
import com.htsoft.oa.model.info.InMessage;
import com.htsoft.oa.model.info.ShortMessage;

public class InMessageDaoImpl extends BaseDaoImpl<InMessage> implements InMessageDao {
	public InMessageDaoImpl() {
		super(InMessage.class);
	}

	public InMessage findByRead(Long userId) {
		String hql = "from InMessage vo where vo.readFlag=0 and vo.delFlag=0 and vo.userId=?";
		Object[] objs = { userId };
		List list = findByHql(hql, objs);
		if (list.size() > 0) {
			return (InMessage) list.get(list.size() - 1);
		}
		return null;
	}

	public Integer findByReadFlag(Long userId) {
		String sql = "select count(*) from InMessage vo where vo.readFlag=0 and vo.delFlag=0 and vo.userId="
				+ userId;
		Query query = getSession().createQuery(sql);
		return Integer.valueOf(Integer.parseInt(query.list().iterator().next().toString()));
	}

	public List<InMessage> findAll(Long userId, PagingBean pb) {
		String hql = "from InMessage vo where vo.userId=?";
		Object[] objs = { userId };
		return findByHql(hql, objs, pb);
	}

	public List<InMessage> findByShortMessage(ShortMessage shortMessage, PagingBean pb) {
		String hql = "from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=?";
		Object[] objs = { shortMessage };
		return findByHql(hql, objs, pb);
	}

	public List findByUser(Long userId, PagingBean pb) {
		String hql = "select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.msgType=1 and vo2.senderId=? order by vo2.sendTime desc";
		Object[] objs = { userId };
		return findByHql(hql, objs, pb);
	}

	public List findByUser(Long userId) {
		String hql = "select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.senderId=?";
		Object[] objs = { userId };
		return findByHql(hql, objs);
	}

	public List searchInMessage(Long userId, InMessage inMessage, ShortMessage shortMessage,
			Date from, Date to, PagingBean pb) {
		StringBuffer hql = new StringBuffer(
				"select vo1 ,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.msgType=1 and vo2.senderId=?");
		ArrayList paramList = new ArrayList();
		paramList.add(userId);
		if (to != null) {
			hql.append("and vo2.sendTime <= ?");
			paramList.add(to);
		}
		if (from != null) {
			hql.append("and vo2.sendTime >= ?");
			paramList.add(from);
		}
		if ((shortMessage != null) && (shortMessage.getMsgType() != null)) {
			hql.append(" and vo2.msgType=?");
			paramList.add(shortMessage.getMsgType());
		}

		if ((inMessage != null) && (StringUtils.isNotEmpty(inMessage.getUserFullname()))) {
			hql.append(" and vo1.userFullname=?");
			paramList.add(inMessage.getUserFullname());
		}

		hql.append(" order by vo2.sendTime desc");

		return findByHql(hql.toString(), paramList.toArray(), pb);
	}

	public InMessage findLatest(Long userId) {
		String hql = "from InMessage vo where vo.delFlag=0 and vo.userId=?";
		Object[] objs = { userId };
		List list = findByHql(hql, objs);
		if (list.size() > 0) {
			return (InMessage) list.get(list.size() - 1);
		}
		return null;
	}
}


 
 
 
 
 