package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchivesDoc;
import java.util.List;

public abstract interface ArchivesDocService extends BaseService<ArchivesDoc>
{
  public abstract List<ArchivesDoc> findByAid(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchivesDocService
 * JD-Core Version:    0.5.4
 */