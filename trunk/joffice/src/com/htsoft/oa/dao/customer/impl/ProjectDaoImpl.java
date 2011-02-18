package com.htsoft.oa.dao.customer.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.customer.ProjectDao;
import com.htsoft.oa.model.customer.Project;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
	public ProjectDaoImpl() {
		super(Project.class);
	}

	public boolean checkProjectNo(final String projectNo) {
		String hql = "select count(*) from Project p where p.projectNo = ?";
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session
						.createQuery("select count(*) from Project p where p.projectNo = ?");
				query.setString(0, projectNo);
				return query.uniqueResult();
			}
		});
		return count.longValue() == 0L;
	}
}


 
 
 
 
 