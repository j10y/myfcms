package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.UserSub;
import java.util.List;
import java.util.Set;

public abstract interface UserSubService extends BaseService<UserSub>
{
  public abstract Set<Long> findAllUpUser(Long paramLong);

  public abstract List<Long> subUsers(Long paramLong);

  public abstract List<Long> upUser(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.UserSubService
 * JD-Core Version:    0.5.4
 */