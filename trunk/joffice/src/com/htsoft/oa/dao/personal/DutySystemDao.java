package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutySystem;
import java.util.List;

public abstract interface DutySystemDao extends BaseDao<DutySystem>
{
  public abstract void updateForNotDefult();

  public abstract List<DutySystem> getDefaultDutySystem();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutySystemDao
 * JD-Core Version:    0.5.4
 */