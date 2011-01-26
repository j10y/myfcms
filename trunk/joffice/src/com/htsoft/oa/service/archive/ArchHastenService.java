package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenService extends BaseService<ArchHasten>
{
  public abstract Date getLeastRecordByUser(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchHastenService
 * JD-Core Version:    0.5.4
 */