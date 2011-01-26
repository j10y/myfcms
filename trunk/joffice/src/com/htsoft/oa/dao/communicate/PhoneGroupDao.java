package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupDao extends BaseDao<PhoneGroup>
{
  public abstract Integer findLastSn(Long paramLong);

  public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> getAll(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.PhoneGroupDao
 * JD-Core Version:    0.5.4
 */