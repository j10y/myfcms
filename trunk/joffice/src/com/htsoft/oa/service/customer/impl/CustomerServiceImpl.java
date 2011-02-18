package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.CustomerDao;
import com.htsoft.oa.model.customer.Customer;
import com.htsoft.oa.service.customer.CustomerService;

public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
	private CustomerDao dao;

	public CustomerServiceImpl(CustomerDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkCustomerNo(String customerNo) {
		return this.dao.checkCustomerNo(customerNo);
	}
}


 
 
 
 
 