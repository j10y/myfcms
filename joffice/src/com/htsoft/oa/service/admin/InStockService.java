package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock>
{
  public abstract Integer findInCountByBuyId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.InStockService
 * JD-Core Version:    0.5.4
 */