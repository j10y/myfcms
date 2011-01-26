package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.MailFolder;
import java.util.List;

public abstract interface MailFolderDao extends BaseDao<MailFolder>
{
  public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getFolderLikePath(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.MailFolderDao
 * JD-Core Version:    0.5.4
 */