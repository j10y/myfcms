package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Region;
import java.util.List;

public abstract interface RegionService extends BaseService<Region>
{
  public abstract List<Region> getProvince();

  public abstract List<Region> getCity(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.RegionService
 * JD-Core Version:    0.5.4
 */