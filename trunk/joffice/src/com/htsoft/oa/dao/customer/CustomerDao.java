package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.Customer;

public abstract interface CustomerDao extends BaseDao<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}


 
 
 
 