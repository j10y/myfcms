package com.htsoft.oa.service.document;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderService extends BaseService<DocFolder>
{
  public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<DocFolder> getFolderLikePath(String paramString);

  public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.DocFolderService
 * JD-Core Version:    0.5.4
 */