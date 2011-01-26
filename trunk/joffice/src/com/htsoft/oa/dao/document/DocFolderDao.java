package com.htsoft.oa.dao.document;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderDao extends BaseDao<DocFolder>
{
  public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);

  public abstract List<DocFolder> getFolderLikePath(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.DocFolderDao
 * JD-Core Version:    0.5.4
 */