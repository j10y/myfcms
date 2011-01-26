package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchivesHandle;
import java.util.List;

public abstract interface ArchivesHandleService extends BaseService<ArchivesHandle>
{
  public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

  public abstract List<ArchivesHandle> findByAid(Long paramLong);

  public abstract int countHandler(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchivesHandleService
 * JD-Core Version:    0.5.4
 */