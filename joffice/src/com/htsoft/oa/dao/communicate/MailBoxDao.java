package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.MailBox;
import java.util.List;

public abstract interface MailBoxDao extends BaseDao<MailBox>
{
  public abstract Long CountByFolderId(Long paramLong);

  public abstract List<MailBox> findByFolderId(Long paramLong);

  public abstract List<MailBox> findBySearch(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.MailBoxDao
 * JD-Core Version:    0.5.4
 */