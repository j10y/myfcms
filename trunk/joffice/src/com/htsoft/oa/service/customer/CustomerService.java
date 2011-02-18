package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Customer;

public abstract interface CustomerService extends BaseService<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}


 
 
 
 
 