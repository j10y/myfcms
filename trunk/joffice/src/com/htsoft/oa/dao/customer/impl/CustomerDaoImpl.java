package com.htsoft.oa.dao.customer.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.customer.CustomerDao;
import com.htsoft.oa.model.customer.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	public CustomerDaoImpl() {
		super(Customer.class);
	}

	public boolean checkCustomerNo(final String customerNo) {
		String hql = "select count(*) from Customer c where c.customerNo = ?";
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session
						.createQuery("select count(*) from Customer c where c.customerNo = ?");
				query.setString(0, customerNo);
				return query.uniqueResult();
			}
		});
		return count.longValue() == 0L;
	}
}


 
 
 
 
 