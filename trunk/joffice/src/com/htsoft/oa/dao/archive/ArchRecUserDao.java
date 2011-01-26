package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchRecUser;
import java.util.List;

public abstract interface ArchRecUserDao extends BaseDao<ArchRecUser>
{
  public abstract List findDepAll();

  public abstract ArchRecUser getByDepId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.ArchRecUserDao
 * JD-Core Version:    0.5.4
 */