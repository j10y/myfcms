package com.htsoft.oa.dao.hrm.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.Constants;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.hrm.StandSalaryDao;
import com.htsoft.oa.model.hrm.StandSalary;

public class StandSalaryDaoImpl extends BaseDaoImpl<StandSalary> implements StandSalaryDao {
	public StandSalaryDaoImpl() {
		super(StandSalary.class);
	}

	public boolean checkStandNo(final String standardNo) {
		String hql = "select count(*) from StandSalary ss where ss.standardNo = ?";
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session
						.createQuery("select count(*) from StandSalary ss where ss.standardNo = ?");
				query.setString(0, standardNo);
				return query.uniqueResult();
			}
		});
		return count.longValue() == 0L;
	}

	public List<StandSalary> findByPassCheck() {
		String hql = "from StandSalary vo where vo.status=?";
		Object[] objs = { Constants.FLAG_PASS };
		return findByHql(hql, objs);
	}
}


 
 
 
 
 