package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfService extends BaseService<ArchFlowConf>
{
  public abstract ArchFlowConf getByFlowType(Short paramShort);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchFlowConfService
 * JD-Core Version:    0.5.4
 */