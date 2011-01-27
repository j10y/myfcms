package com.htsoft.oa.dao.hrm.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.hrm.EmpProfileDao;
import com.htsoft.oa.model.hrm.EmpProfile;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmpProfileDaoImpl extends BaseDaoImpl<EmpProfile> implements EmpProfileDao {
	public EmpProfileDaoImpl() {
		super(EmpProfile.class);
	}

	public boolean checkProfileNo(final String profileNo) {
		String hql = "select count(*) from EmpProfile ep where ep.profileNo = ?";
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session
						.createQuery("select count(*) from EmpProfile ep where ep.profileNo = ?");
				query.setString(0, profileNo);
				return query.uniqueResult();
			}
		});
		return count.longValue() == 0L;
	}
}

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.dao.hrm.impl.EmpProfileDaoImpl JD-Core Version:
 * 0.5.4
 */