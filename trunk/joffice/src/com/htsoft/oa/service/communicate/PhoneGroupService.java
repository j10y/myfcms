package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupService extends BaseService<PhoneGroup>
{
  public abstract Integer findLastSn(Long paramLong);

  public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> getAll(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.PhoneGroupService
 * JD-Core Version:    0.5.4
 */