package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.MailFolder;
import java.util.List;

public abstract interface MailFolderService extends BaseService<MailFolder>
{
  public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getFolderLikePath(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.MailFolderService
 * JD-Core Version:    0.5.4
 */