package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.Customer;

public abstract interface CustomerDao extends BaseDao<Customer>
{
  public abstract boolean checkCustomerNo(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.CustomerDao
 * JD-Core Version:    0.5.4
 */