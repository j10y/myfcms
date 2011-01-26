package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanService extends BaseService<CusLinkman>
{
  public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.CusLinkmanService
 * JD-Core Version:    0.5.4
 */