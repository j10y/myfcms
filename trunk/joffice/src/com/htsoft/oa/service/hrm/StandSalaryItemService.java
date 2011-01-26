package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import java.util.List;

public abstract interface StandSalaryItemService extends BaseService<StandSalaryItem>
{
  public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.StandSalaryItemService
 * JD-Core Version:    0.5.4
 */